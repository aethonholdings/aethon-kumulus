package com.kumulus.services

import com.kumulus.domain.*
import grails.transaction.Transactional

@Transactional
class CaptureService {

    def springSecurityService
    def filesystemService
    
    def deleteNode(nodeID) {
        
        def node = Node.findById(nodeID)
        if(node) {
            def children = Node.findAllByParent(node)
            for (child in children) {
                deleteNode(child.id)
            }
            node.parent = null;
            node.delete(flush: true)
        }
    }
    
    def insertNode(parent, project, barcodeString, name, comment, type) {
        
        def barcode = Barcode.findByText(barcodeString)
        def nodeType = NodeType.findByName(type)
        if(project && nodeType) {
            def timestamp = new Date()            
            def node = new Node()
            node.creatorId = springSecurityService.principal.username
            node.lastUpdateId = springSecurityService.principal.username
            node.project = project
            node.status = Node.STATUS_OPEN
            node.type = nodeType
            node.parent = parent
            node.barcode = barcode
            node.name = name
            node.comment = comment
            node.createDatetime = timestamp
            node.lastUpdateDatetime = timestamp
            node.location = Node.LOCATION_CLIENT
            node.page = null
            node.save()
            
            barcode?.used = true
            barcode?.save()
            return(node)
        }
        return(null)
    }
    
    def updateNode(node, barcodeString, name, comment, type, status, location) {
        
        def barcode = Barcode.findByText(barcodeString)
        def nodeType = NodeType.findByName(type)
        if(node && !node.hasErrors() && nodeType && barcode){
            node.comment = comment
            node.barcode = barcode
            node.name = name
            node.status = status
            node.type = nodeType
            node.location = location
            node.save()
            
            barcode.used = true
            barcode.save()
        }
        return(node)
    }
    
    def renderNode(node) {
        
        if(node) {
            def treeNode = [
                key: node.id.toString(),
                title: node.name,
                isLazy: true,
                text: node.name,
                barcode: node.barcode.text,
                isFolder: node.type.isContainer,
                comment: node.comment,
                type: node.type.name,
                status: node.status(),
                location: node.location,
                id: node.id, 
                project: node.project.id
            ]
            return(treeNode)
        }
    }
    
    def renderRoot(project) {

        def children = []
        def root = [
            key: "#",
            title: "Root",
            isFolder: true,
            expand: true,
            select: true,
            isLazy: false,
            parent: null,
            text: null, 
            barcode: null,
            comment: null,
            status: null,
            location: null,
            children: children,
            type: "ROOT",
            id: "ROOT",
            project: project.id
        ]
        
        // get the top-level nodes
        def nodeList = Node.findAllByProjectAndParent(project, null)  // temporary solution, should be filtering out documents here
        nodeList.each { root.children.add renderNode(it) }
        return(root)
    }
    
    def renderNodeHierarchy(Node node) {
        def nodes = [renderNode(node)]
        while(node.parent!=null) {
            node = node.parent
            nodes.add(renderNode(node))
        }
        return(nodes)
    }

    def indexScan(parentNode, uFile, scanBatch, userId) {
        
        Document document

        if(parentNode && uFile && scanBatch && userId) {
            
            Date timestamp = new Date()
            def literal = filesystemService.generateLiteral()            

            // start by creating a new node in the node tree
            def node = new Node(
                name: literal,
                type: NodeType.findById(3),                                     // node type is page
                parent: parentNode,
                project: parentNode.project,
                creatorId: userId,
                lastUpdateId: userId,
                createDatetime: timestamp,
                lastUpdateDatetime: timestamp,
                status: Node.STATUS_CLOSED,
                location: Node.LOCATION_CLIENT
            )
            node.save()

            // create a document to hold the page
            document = new Document(
                company: null,
                date: null,
                literal: literal,
                status: Document.STATUS_IMPORTED,
                file: null,
                project: parentNode.project,
                type: DocumentType.findById(4),               
                ocrTask: null,
                pages: [],
                deleted: false
            )
            document.save()
            
            // now create a page for the scan
            def page = new Page(
                number: 1,
                first: true,
                last: true,
                literal: literal,
                lineItems: [],
                node: node, 
                scanBatch: scanBatch,
                document: document
            )

            // generate the image files for the page
            def images = filesystemService.indexImageInFilesystem(literal, page, uFile, timestamp)
            
            page.scanImage = images.scanImage
            page.viewImage = images.viewImage
            page.thumbnailImage = images.thumbnailImage
            document.addToPages(page)
            page.save(flush:true)
            
            // -- Kons - following relationship establishment does not work - not sure why 
            node.page = page
            node.save(flush:true)
            // -- 

            filesystemService.stagingFlush(uFile)
        }
        return(document)
    }
    
    def merge(documents) {
        
        Document newDocument

        if(documents?.size > 1) {
            // cycle through the pages to build the document
            def pages = []
            long pageCount = 0
            def project = documents[0].project
            boolean projectCheck = true                                         // check if all docs are from same project
            documents.each { document ->
                if(document.project == project && projectCheck) {                   
                    document.pages.each { page ->
                        pages.add(page)
                        pageCount++
                    }
                } else projectCheck = false
            }
            
            if(projectCheck) {
                // create a new document
                def documentType = DocumentType.findById(4)
                newDocument = new Document(
                    literal: filesystemService.generateLiteral(),
                    status: Document.STATUS_BUILT,
                    project: project,
                    type: documentType, 
                    deleted: false
                )
                newDocument.save()

                pages.eachWithIndex { page, i ->
                    // for each page, update the page domain class
                    page.document = newDocument
                    if(i==0) page.first = true else page.first = false
                    if(i==pageCount-1) page.last = true else page.last = false
                    page.number = i+1
                    page.save()
                }

                // delete the old documents
                documents.each { document ->
                    document.pages = []
                    document.deleted = true
                    document.save()
                }
            }
        } else if (documents?.size == 1) {
            // no processing of the document needed
            newDocument = documents[0]
        }
        return(newDocument)
    }
    def updateContainer(node,status){
     if(node && status){
          if(status=='0'){
             status='1'
             node.status=status
             node.save()
          }
      }  
        return(node)
    }
}

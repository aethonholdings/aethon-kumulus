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
    
    def insertNode(parent, project, barcodeString, name, comment, type, state) {
        
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
            node.state = state
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
        def parentId = "#"
        def thumbnailId = null
        def viewId = null
        
        if(node?.parent) parentId = node.parent.id 
        if(node?.page) {
            thumbnailId = node.page.thumbnailImage.id
            viewId = node.page.viewImage.id
        }
        
        if(node) {
            def treeNode = [
                key: node.id.toString(),
                title: node.name,
                isLazy: true,
                text: node.name,
                barcode: node?.barcode?.text,
                isFolder: node.type.isContainer,
                comment: node.comment,
                type: node.type.name,
                typeId: node.type.id,
                status: node.status(),
                location: node.location,
                stateId: node.state,
                state: node.state(),
                storeable: node.type.storeable,
                id: node.id, 
                project: node.project.id,
                parentId: parentId,
                thumbnailId: thumbnailId,
                viewId: viewId
            ]
            return(treeNode)
        }
    }
    
    def renderProject(project) {
        if(project) {
            def projectRender = [
                id: project.id,
                company: project.company,
                name: project.projectName,
                topLevelNodeIds: []
            ]
            def nodes = Node.findAllByProjectAndParent(project, null, [sort: "name", order: "asc"])
            nodes.each { node ->
                projectRender.topLevelNodeIds.add(node.id)
            }
            return(projectRender)
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
            stateId: null,
            state: null,
            storeable: false,
            children: children,
            type: "ROOT",
            id: "ROOT",
            project: project.id,
            parentId: null, 
            thumbnailId: null,
            viewId: null
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
                location: Node.LOCATION_CLIENT,
                state: parentNode.state
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
            
            def pages = []
            long pageCount = 0
            def project = documents[0].project
            
            // cycle through the pages to build the document
            // at the same time ensure that all docs are from same project
            boolean projectCheck = true                                         
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
    
    Project insertProject(String projectName, String clientName, String comment, String companyName, String username) {
        def project = new Project([
            projectName: projectName, 
            comment: comment, 
            status: "A", 
            company: companyName, 
            lineItems:[], 
            nodes:[], 
            created: new Date(),
            closed: null,
            ownerId: username
        ])
        filesystemService.newProject(project)
        updateProject(project, projectName, clientName, comment)
        return(project)
    }
    
    Project updateProject(Project project, String projectName, String clientName, String comment) {
        def client = Company.findByName(clientName)
        if (client == null) {
            client = new Company([name: clientName])
            client.save()
        }
        project.client = client
        project.projectName = projectName
        project.comment = comment
        project.save()
        return(project)
    }
}

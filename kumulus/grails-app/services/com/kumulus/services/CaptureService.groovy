package com.kumulus.services

import com.kumulus.domain.*
import java.text.SimpleDateFormat
import grails.transaction.Transactional

@Transactional
class CaptureService {

    def filesystemService
    def grailsApplication
    def permissionsService
    
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
            node.creatorId = permissionsService.getUsername()
            node.lastUpdateId = permissionsService.getUsername()
            node.project = project
            node.status = Node.STATUS_OPEN
            node.type = nodeType
            node.barcode = barcode
            node.name = name
            node.comment = comment
            node.createDatetime = timestamp
            node.lastUpdateDatetime = timestamp
            node.location = Node.LOCATION_CLIENT
            node.page = null
            node.parent = parent
            if(parent) node.state = parent.state else node.state = Node.STATE_CLIENT_OPEN
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
    
    def updateDocument(Document document, String companyName, DocumentType documentType, Date date, String identifier) {
        
        if(document && documentType) {    
            def company = Company.findByName(companyName)
            if(!company) {
                company = new Company(name: companyName)
                company.save()
            }
            document.company = company
            document.type = documentType
            document.date = date
            document.identifier = identifier
            document.save()
        }
        
        return(document)
        
    }
    
    def updateLineItem(String lineItemId, String pageId, Currency currency, Date date, String description, String quantity, String price, String amount) {
       
        def lineItem = LineItem.findById(lineItemId)
        if(!lineItem) lineItem = new LineItem()
        lineItem.page = Page.findById(pageId)
        lineItem.currency = currency
        lineItem.date = date
        lineItem.description = description
        if(quantity) lineItem.quantity = Float.parseFloat(quantity) else lineItem.quantity = 0
        if(price) lineItem.price = Float.parseFloat(price) else lineItem.price = 0
        lineItem.amount = Float.parseFloat(amount)
        lineItem.save()
        return(lineItem)
    }
}

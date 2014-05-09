package com.kumulus.services

import com.kumulus.domain.*
import grails.transaction.Transactional
import com.lucastex.grails.fileuploader.UFile
import org.compass.core.engine.SearchEngineQueryParseException
import com.sun.jersey.core.util.*

@Transactional
class AccessService {
    
    def searchableService
    def captureService
    def grailsApplication
    
    def getCSV(project) {
        if (project) {
            def ledger = new ArrayList()
            def documents = Document.findAll {
                project == project
                deleted == false
                status == Document.STATUS_FINAL
            }
            documents.each {document ->
                document.pages.each { page ->
                    page.lineItems.each { lineItem ->
                        
                        def extract = [
                            id: lineItem.id,
                            link: renderSourceLink(lineItem),
                            type: document.type.name,
                            page: page.number,
                            company: lineItem.page.document.company?.name,
                            date: lineItem.date,
                            description: lineItem.description, 
                            currency: lineItem.currency.shortName,
                            quantity: lineItem.quantity,
                            price: lineItem.price, 
                            amount: lineItem.amount
                        ]
                        ledger.add extract
                    }
                }
            }
            List fields = [
                "id", 
                "link", 
                "type", 
                "page",
                "company",
                "date",
                "description",
                "currency",
                "quantity",
                "price",
                "amount"
            ]
            Map labels = [
                id: "Line item id",
                link: "Source document",
                type: "Document type",
                page: "Page number",
                company: "Issuing company",
                date: "Date",
                description: "Description",
                currency: "Currency",
                quantity: "Quantity",
                price: "Unit price",
                amount: "Amount"
            ]
            def output = [
                ledger: ledger,
                fields: fields,
                labels: labels
            ]
            return(output)
        }
    }
    
    void renderFile(response, UFile ufile, String disposition) {
        def contentTypes = [
            jpg: "image/jpg",
            png: "image/png",
            pdf: "application/pdf"
        ]
        def contentType = contentTypes.get(ufile.extension)
        def file = new File(ufile.path)        
        
        ufile.downloads++
        ufile.save()
        response.setContentType(contentType)
        response.setHeader("Content-disposition", disposition + "; filename=${ufile.name}")
        response.outputStream << file.readBytes()
    }
    
    def renderFileInBase64(UFile ufile) {
        
        // get the image byte buffer
        FileInputStream inputStream = new FileInputStream(ufile.path)
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
        byte[] block = new byte[1024]
        int bytesRead = 0
        while ((bytesRead = inputStream.read(block)) != -1) {
            outputStream.write(block, 0, bytesRead);
        }
        byte[] imageBytes = outputStream.toByteArray();
        
        // convert to Base 64
        String render = imageBytes.encodeBase64().toString()

        return(render)
    }
    
    def search(Project project, String query) {
        // Konstantinos: this implementation is currently very inefficient
        def responseData = [
                total: 0,
                data: []
            ]
        try {    
            String searchTerm = "*" + query + "*"
            def searchResult = searchableService.search(searchTerm)
            responseData.total = searchResult.total
            int count = 0
            searchResult.results.each { result ->
                def resultClass = result.getClass()
                def node, documentId, documentName;
                if(resultClass == Node) {
                    node = Node.findById(result.id)
                    documentId = -1
                }
                if(resultClass == Document) {
                    def document = Document.findById(result.id)
                    node = document.pages[0].node.parent
                    documentId = document.id
                    documentName = document.identifier
                }
                if(node.project == project) {
                    String keypath = node.id.toString()
                    def parent = node.parent
                    while(parent) {
                        keypath = parent.id + "/" + keypath
                        parent = parent.parent
                    }
                    keypath = "#" + "/" + keypath
                    responseData.data.add([
                        nodeId: node.id,
                        nodeBarcode: node?.barcode?.text,
                        nodeName: node.name,
                        documentId: documentId,
                        documentName: documentName,
                        keypath: keypath
                    ])
                    count++
                }
            }
            responseData.total = count
        } catch (SearchEngineQueryParseException ex) {
            return(null)
        }
        return(responseData)
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
            select: false,
            isLazy: true,
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
        
        Node.findAllByParentAndProject (null, project, [sort: "name", order: "asc"]).each {
            children.add(renderNode(it))
        }
        
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
    
    String renderSourceLink(LineItem lineItem) {
        def g = grailsApplication.mainContext.getBean('org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib');
        return(g.createLink([controller: "document", action:"get", id: lineItem.id, absolute: true]))
    }
    
    def renderShipment(Shipment shipment) {
        return ([
            id: shipment.id,
            scheduled: shipment.scheduled.toTimestamp(),
            company: shipment.company.name
        ])
    }
    
}

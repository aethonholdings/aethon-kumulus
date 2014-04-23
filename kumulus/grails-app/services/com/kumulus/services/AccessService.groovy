package com.kumulus.services

import com.kumulus.domain.*
import grails.transaction.Transactional
import com.lucastex.grails.fileuploader.UFile
import org.compass.core.engine.SearchEngineQueryParseException

@Transactional
class AccessService {
    
    def searchableService
    def captureService
    
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
                            id: lineItem?.id,
                            documentId: document.id,
                            page: page.number,
                            company: lineItem?.page.document.company?.name,
                            date: lineItem?.date,
                            description: lineItem?.description, 
                            currency: lineItem?.currency?.shortName,
                            quantity: lineItem?.quantity,
                            price: lineItem?.price, 
                            amount: lineItem?.amount
                        ]
                        ledger.add extract
                    }
                }
            }
            List fields = [
                "id", 
                "documentId",
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
                id: "ID",
                documentId: "Document ID",
                page: "Page number",
                company: "Company name",
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
    
}

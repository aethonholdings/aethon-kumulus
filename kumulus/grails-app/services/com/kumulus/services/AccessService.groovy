package com.kumulus.services

import com.kumulus.domain.*
import grails.transaction.Transactional
import com.lucastex.grails.fileuploader.UFile

@Transactional
class AccessService {
    
    // should implement get abstract, then use helper classes to implement plugins for different export systems
    
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
    
}

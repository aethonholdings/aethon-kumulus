package com.kumulus.services

import grails.transaction.Transactional
import com.kumulus.domain.*

@Transactional
class ProjectService {
    
    def getCSV(project) {
        
        if (project) {
            def ledger = new ArrayList()
            def nodes = Node?.findAllByProjectAndType(project, "D")
            nodes.each {node ->
                node.documents.each {document ->
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
}

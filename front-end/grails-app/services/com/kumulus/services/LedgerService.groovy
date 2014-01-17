package com.kumulus.services

import grails.transaction.Transactional
import com.kumulus.domain.*

@Transactional
class LedgerService {
    
    def getCSV(project) {
        
        if (project) {
            def ledger = new ArrayList()
            def nodes = Nodes?.findAllByProjectAndType(project, "D")
            nodes.each {node ->
                node.documents.each {document ->
                    document.lineItems.each { lineItem ->
                        def extract = [
                            id: lineItem?.id,
                            documentId: document.id,
                            company: lineItem?.document.company,
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

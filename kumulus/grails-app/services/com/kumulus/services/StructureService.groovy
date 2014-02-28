package com.kumulus.services

import com.kumulus.domain.*
import java.text.SimpleDateFormat
import grails.transaction.Transactional

@Transactional
class StructureService {
    
    def filesystemService
    def permissionsService
    def grailsApplication
    
    def updateDocument(String documentId, String documentTypeId, Date date, String identifier) {
        def document = Document.get(documentId)
        def documentType = DocumentType.get(documentTypeId)
        if(document && documentType) {
            document.type = documentType
            document.date = date
            document.identifier = identifier
            document.save()
        }
        return(document)
    }
    
    def updateLineItem(String lineItemId, String pageId, Currency currency, Date date, String description, String quantity, String price, String amount) {
        def lineItem = LineItem.get(lineItemId)
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

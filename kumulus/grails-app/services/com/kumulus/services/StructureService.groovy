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
    
    def updateLineItem(String lineItemId, Page page, Currency currency, Date date, String description, float quantity, float price, float amount) {
        
        
    }
}

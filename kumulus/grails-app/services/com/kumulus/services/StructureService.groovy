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
        println(document)
        return(document)
    }
    
    def addLineItem(LineItem lineItem, Document document) {
        
        
    }
}

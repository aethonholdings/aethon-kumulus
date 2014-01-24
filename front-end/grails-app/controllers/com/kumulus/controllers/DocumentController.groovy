package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*
import grails.converters.*

class DocumentController {

    def userService
    def documentService
    
    @Secured(['ROLE_IMPORT'])
    def build() {
        def documentList = []
        def company = userService.getCompany()
        Project.findAllByCompany(company).each() { project ->
            Document.findAllByProjectAndStatus(project, Document.EDITABLE).each() { document ->
                documentList.add document
            }
        }
        render view: "build", layout: "home", model: [documents: documentList]
    }
    
    @Secured(['ROLE_IMPORT'])
    def merge() {
        
        def data = request.JSON
        Document mergedDocument
        def documents = []
        data?.documents.each {
            // NEED PERMISSIONS CHECKS HERE AGAINST THE TASKS
            def document = Document.findById(it)
            documents.add(document)
        }
        mergedDocument = documentService.merge(documents)
        if(mergedDocument) documentService.createTask(mergedDocument, Task.OCR_DOCUMENT, Task.READY_FOR_BATCH_INSTANCE)
        def response = [done: true]
        render response as JSON
    }

}

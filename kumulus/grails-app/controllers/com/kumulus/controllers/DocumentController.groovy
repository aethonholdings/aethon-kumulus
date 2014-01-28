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
        def project = Project.findById(params?.id)
        if(project) {
            Task.findAllByUserIdAndType(userService.getUsername(), Task.BUILD_DOCUMENT).each { task ->
                if(task.document.project==project) documentList.add task.document            
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

    @Secured(['ROLE_VIEW'])
    def access() {
        render view: "access", layout: "home"
    }
    
    @Secured(['ROLE_PROCESS'])
    def ocr() {
        def task = Task.findById(params?.id)
        def currencies = Currency.listOrderByFullName()
        def documentTypes = ["Invoice", "Receipt", "Account statement", "Other"]
        def document = task.document
        // SORT BY PAGE NUMBER!
        render view: "ocr", layout: "home", model:[document: document, currencies: currencies, documentTypes: documentTypes]
    }
}

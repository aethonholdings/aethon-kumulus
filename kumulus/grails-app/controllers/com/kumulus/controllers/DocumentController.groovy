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
        render view: "build", model: [documents: documentList]
    }
    
    @Secured(['ROLE_IMPORT'])
    def merge() {
        def data = request.JSON
        Document mergedDocument
        def documents = []
        data?.documents.each {
            def document = Document.findById(it)  // NEED PERMISSIONS CHECKS HERE AGAINST THE TASKS
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
    def process() {
        def task = Task.findById(params?.id)
        def currencies = Currency.listOrderByFullName()
        def documentTypes = DocumentType.listOrderByName()
        def document = task.document
        // SORT BY PAGE NUMBER!
        render view: "process", model:[document: document, currencies: currencies, documentTypes: documentTypes]
    }
    
    @Secured(['ROLE_PROCESS'])
    def update() {  
        def data = request.JSON
        def response = [done: false]
        def document = Document.findById(data?.id)
        documentService.update(document, data)
        render response as JSON
    }
}

package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*
import grails.converters.*
import com.lucastex.grails.fileuploader.*

@Secured(['ROLE_ADMIN', 'ROLE_PROCESS', 'ROLE_IMPORT', 'ROLE_REVIEW', 'ROLE_SUPERVISE', 'ROLE_VIEW'])
class DocumentController {
    
    def processManagementService
    def permissionsService
    def dataProcessingService
    def filesystemService
        
    def merge() {
        def data = request.JSON
        Document mergedDocument
        def documents = []
        data?.documents.each {
            def document = Document.findById(it)  // NEED PERMISSIONS CHECKS HERE AGAINST THE TASKS
            documents.add(document)
        }
        mergedDocument = dataProcessingService.merge(documents)
        if(mergedDocument) processManagementService.createTask(mergedDocument, Task.OCR_DOCUMENT, Task.READY_FOR_BATCH_INSTANCE)
        def response = [done: true]
        render response as JSON
    }
            
    def update() {  
        def data = request.JSON
        def response = [done: false]
        def document = Document.findById(data?.id)
        dataProcessingService.update(document, data)
        render response as JSON
    }
    
    def index() {
        def file = UFile?.findById(params?.ufileId)
        def document = Document?.findById(params?.documentId)
        filesystemService.indexDocument(document, file)
        render "hello"
    }
    
}

package com.kumulus.controllers

import com.kumulus.domain.*
import grails.converters.*

class DocumentController {
    
    def workflowService
    def permissionsService
    def captureService
    def structureService
    
    def merge() {
        def data = request.JSON
        def mergedDocument
        def response = [done: false]
        if (data?.documents) { 
            def documents = []
            def tasks = []
            Boolean permission = true
            data?.documents.each {
                def document = Document.findById(it)
                def task = Task.findByDocumentAndTypeAndCompleted(document, Task.BUILD_DOCUMENT, null)
                if(permission && task) {
                    documents.add(document)
                    tasks.add(task)
                } else permission = false
            }
            if(permission) {
                mergedDocument = captureService.merge(documents)
                if(mergedDocument) {
                    tasks.each { workflowService.completeTask(it) }
                    workflowService.createTask(mergedDocument, Task.OCR_DOCUMENT, permissionsService.getUsername())
                    response.done = true
                }
            }
        }
        render response as JSON
    }
            
    def update() {  
        def data = request.JSON
        def response = [done: false]
        def document = Document.findById(data?.id)
        structureService.update(document, data)
        render response as JSON
    }
        
}

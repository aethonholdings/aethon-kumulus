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
        def documents = []
        def tasks = []
        def response = [done: false]
        
        // check if the user has permission to these tasks, and if they are from the same project
        data?.tasks.each {
            def task = Task.findById(it)
            tasks.add(task)
            documents.add(task.document)
        }

        def document = captureService.merge(documents)
        tasks.each {
            workflowService.completeTask(it)
            workflowService.createTask(it.document, WorkItem.OCR_DOCUMENT, permissionsService.getUsername())
        }
        response.done = true
        
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

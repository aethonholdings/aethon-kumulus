package com.kumulus.controllers

import com.kumulus.domain.*
import grails.converters.*
import com.kumulus.jobs.SubmitDocumentJob

class DocumentController {
    
    def accessService
    def workflowService
    def permissionsService
    def captureService
    
    def merge() {
        def data = request.JSON
        def documents = []
        def tasks = []
        def response = [done: false]
        boolean proceed = true
        
        data?.tasks.each {

            if(it) {
                def task = Task.findById(it)
                if(permissionsService.checkPermissions(task.document) 
                                    && task.type==Task.TYPE_BUILD 
                                    && task.document.status==Document.STATUS_IMPORTED 
                                    && !task.document.deleted
                                    && !task.completed) {
                    tasks.add(task)
                    documents.add(task.document)
                } else {
                    proceed = false
                }
            }
        }
        
        if(tasks.size > 0 && documents.size > 0 && proceed) {
            def document = captureService.merge(documents)
            tasks.each { workflowService.completeTask(it) }
            workflowService.createTask(document, Task.TYPE_OCR, permissionsService.getUsername())
            SubmitDocumentJob.triggerNow()
            response.done = true
        }
        render response as JSON
    }

    def get() {
        def document = Document.findById(params?.id) 
        if(document && permissionsService.checkPermissions(document)) {
            if(document.status>=Document.STATUS_IMPORTED && document.file) {
                accessService.renderFile(response, document.file, "attachment")
            } else {
                if(document?.pages[0]) redirect controller: "image", action: "get", id: document.pages[0].viewImage.id
            }
        }
    }
    
    def view() {
        def document = Document.findById(params?.id) 
        if(document && permissionsService.checkPermissions(document)) {
            if(document.status>=Document.STATUS_IMPORTED && document.file) {
                accessService.renderFile(response, document.file, "inline")
            } else {
                if(document?.pages[0]) redirect controller: "image", action: "view", id: document.pages[0].viewImage.id
            }
        }
    }
}

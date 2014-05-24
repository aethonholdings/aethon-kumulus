package com.kumulus.controllers

import com.kumulus.domain.*
import com.lucastex.grails.fileuploader.UFile

class ImageController {

    def accessService
    def permissionsService
    def captureService
    def workflowService
        
    def upload() {
        def node = Node.findById(params?.id)
        if(node && params?.ufileId) {
            def userId = permissionsService.getUsername()
            def scanBatch = new ScanBatch(userId: userId, timestamp: new Date(), project: node.project)
            scanBatch.save()
            def document = captureService.indexScan(node, UFile.findById(params?.ufileId), scanBatch, permissionsService.getUsername(), null, null)
            def task = workflowService.createTask(document, Task.TYPE_BUILD, userId)
            if (document && task) {
                workflowService.assignTask(task, userId)
                redirect controller: "capture", action: "upload", id: node.project.id
            }
        }
    }

    def get() {
        // check for permissions here
        def image = Image.findById(params?.id)
        if(image && permissionsService.checkPermissions(image)) accessService.renderFile(response, image.file, "attachment")
    }
    
    def view() {
        def image = Image.findById(params?.id)
        if(image && permissionsService.checkPermissions(image)) accessService.renderFile(response, image.file, "inline")
    }
}

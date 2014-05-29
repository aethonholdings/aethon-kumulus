package com.kumulus.controllers.workflow

import grails.converters.*
import com.kumulus.domain.*
import java.text.DateFormat
import java.text.SimpleDateFormat

class ScanDo2Controller {

    def accessService
    def captureService
    def permissionsService
    def filesystemService
    def workflowService

    // action to handle authentication
    def authenticate() {
        def response = [success: true]
        render response as JSON  
    }
    
    def getNode() {
        def data = request.JSON
        def response = [
            success: false,
            data: []
        ]
        def node = Node.findById(data?.id)
        if(node && permissionsService.checkPermissions(node)) {
            response.success = true
            response.data = accessService.renderNode(node)
        }
        render response as JSON
    }
    
    def getNodeChildrenIds() {
        def data = request.JSON
        def response = [
            success: false,
            data: []
        ]
        def node = Node.findById(data?.id)
        if(node && permissionsService.checkPermissions(node)) {
            response.success = true
            def children = Node.findAllByParent(node, [sort: "name", order: "asc"])
            children.each {    
                response.data.add(it.id)
            }
        }
        render response as JSON
    }
    
    def getNodeIdFromBarcode() {
        def data = request.JSON
        def response = [
            success: false,
            data: [id: -1]
        ]
        def barcode = Barcode.findByText(data?.barcode)
        if(barcode) {
            def node = Node.findByBarcode(barcode) 
            if(node && permissionsService.checkPermissions(node)) {
                response.success = true
                response.data.id = node.id
            }
        }
        render response as JSON
    }
    
    def getImage() {
        def data = request.JSON
        def response = [
            success: false,
            data: [:]
        ]
        def image = Image.findById(data?.id)
        if(image && permissionsService.checkPermissions(image)) {
            response.success = true
            response.data.put ("id", image.id)
            response.data.put ("height", image.height)
            response.data.put ("width", image.width)
            response.data.put ("filename", image.file.name)
            response.data.put ("imageData", accessService.renderFileInBase64(image.file))
        }
        render response as JSON
    }
    
    def getProject() {
        def data = request.JSON
        def response = [
            success: false,
            data: [:]
        ]
        def project = Project.findById(data?.id)
        if(project && permissionsService.checkPermissions(project)) {
            response.success = true
            response.data = accessService.renderProject(project)
        }
        render response as JSON
    }
    
    def putImages() {
        def data = request.JSON
        def response = [
            success: false,
            data: [:]
        ]
        
        def parent = Node.findById(data.parent_id)
        def userId = permissionsService.getUsername()
        if(parent && data?.full && data?.view && data?.thumbnail) {
            def scanBatch = new ScanBatch(userId: userId, timestamp: new Date(), project: parent.project)
            scanBatch.save()
            def uFile = filesystemService.writeStringToImageFile(data.full, filesystemService.generateLiteral())
            def document = captureService.indexScan(parent, uFile, scanBatch, userId, data.view, data.thumbnail)
            def task = workflowService.createTask(document, Task.TYPE_BUILD, userId)
            if (document && task) { workflowService.assignTask(task, userId) }
            response.data.id = Page.findByDocument(document).node.id
            response.success = true
        }
        
        render response as JSON
    }
}

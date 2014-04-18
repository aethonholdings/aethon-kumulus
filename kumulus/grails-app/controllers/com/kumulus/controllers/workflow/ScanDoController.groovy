package com.kumulus.controllers.workflow

import grails.converters.*
import com.kumulus.domain.*
import java.text.DateFormat
import java.text.SimpleDateFormat

class ScanDoController {

    def scanDoService
    def captureService
    def permissionsService
    def filesystemService
    def workflowService

    def updateNodePropertiesList() { }
    
    def getChildNodeCount() { }
    
    def getEncodedActualImageString() { }    
    
    def updateNodeProperties() { }
    
    // action to handle authentication
    def authenticate() {
        
        def response = [success: true]
        render response as JSON  
        
    }
    
    def updateAttendance() {
    
        redirect(action: "authenticate")
        
    }
    
    def fetchProjectList(){  
        
        def projectlist =Project.list()         
        def responseData=[:]
        def projects=[]
        projectlist.each{ project ->
            responseData.project.id = it.projectName           
        }      
        render responseData as JSON  
    }
    
    
    
    def fetchChildNodeList() {
        def nodeList = []
        def data = request.JSON
        if(data[1].toString()=="null") {
            def project = Project.findById(data[0].toString())
            if(project) nodeList = Node.findAll { node -> 
                project == project
                parent == null 
            }
        } else {
            def parent = Node.findById(data[1].toString())
            if(parent) { 
                nodeList = Node.findAll { node -> 
                    parent == parent
                }
            }
        }
        
        def responsedata =[]
        nodeList.each{ node ->
            def renderNode = scanDoService.renderNode(node)    
            responsedata.add(renderNode)            
        }     
        render responsedata as JSON 
    }
    
    def getProjectBybarcode() {
        
        def data = request.JSON
        def responsedata =[
            'projectId': -1,
            'projectName': "Scan barcode"
       ]
        
        def barcode = Barcode.findByText(data?.barcode)
        if(barcode) {
            def node = Node.findByBarcode(barcode)
            if(node) {
                responsedata.projectId = node.project.id
                responsedata.projectName = node.project.projectName            
            }
        }
        render responsedata as JSON  
    }
    
    def fetchSessionData() {
        def sessiondata=[:]
        sessiondata = scanDoService.renderSessionData(params.username)    
        render sessiondata as JSON     
    }
    
    def getHierarchyFromSearchBarcode() {
        
        def data = request.JSON       
        String responseString 
        if(data?.searchBarcode) {
            responseString = "["
            def barcode = Barcode.findByText(data?.searchBarcode)
            if(barcode) { 
                def node = Node.findByBarcode(barcode)
                if(node) {
                    def projectName = node.project.projectName
                    def barcodes = [node.barcode?.text]
                    while(node.parent!=null) {
                        node = node.parent
                        barcodes.add(node.barcode?.text)
                    } 
                    barcodes.add(projectName)
                    ListIterator nodeslist = barcodes.listIterator(barcodes.size());
                    while (nodeslist.hasPrevious()) {
                        responseString = responseString + nodeslist.previous().toString()
                        if(nodeslist.hasPrevious()) responseString = responseString + ", "
                    }
                }
            }
            responseString = responseString + "]"
        }
        response.outputStream << responseString
    }
    
    def saveScannedImages() {
        
        def response = [:]
        def imageData = request.JSON
        def project
        def scanBatch
        
        imageData.each { data ->
            def parent = Node.findById(data?.parentNodeId)
            def userId = permissionsService.getUsername()
            if(parent && project!=parent.project) {
                project = parent.project
                scanBatch = new ScanBatch(userId: userId, timestamp: new Date(), project: project)
                scanBatch.save()
            }
            if(data?.encodeStringForImage && parent && scanBatch && data?.name) {
                try {
                    def uFile = filesystemService.writeStringToImageFile(data?.encodeStringForImage, filesystemService.generateLiteral(), request.locale)
                    def document = captureService.indexScan(parent, uFile, scanBatch, userId)
                    def task = workflowService.createTask(document, Task.TYPE_BUILD, userId)
                    if (document && task) { workflowService.assignTask(task, userId) }
                    response.put(data.actualImageName, true)
                } catch(Exception exception) {
                    response.put(data.actualImageName, false)
                }
            }
        }
        render response as JSON
    }
    
    def checkIfNodeIsUpdatedByOtherUser() { 
        
        def response = [false]
        render response as JSON  
        
    }
    
    def fetchNodeThumbnails() {
        
        def data = request.JSON
        
        def response = []
        def pageNode = Node.findById(data?.parentnodeId)
        def page = pageNode.page
        
        if(pageNode && page) {
            response = [ 
                [
                    "nodeId": page.literal,
                    "projectId": "" + pageNode.project.id,
                    "name": page.literal,
                    "type": null,
                    "barcode": null,
                    "comment": null,
                    "internalComment": null,
                    "status": null,
                    "parentNodeId": "" + pageNode.id,
                    "hierarchy": null,
                    "thumbnailImageName": page.thumbnailImage.file.name,
                    "actualImageName": page.viewImage.file.name,
                    "lastUpdateDateTime": pageNode.lastUpdateDatetime,
                    "documentSequenceNumber": page.number,
                    "userId": null,
                    "encodeStringForImage": null,
                    "encodeStringForThumbnail": filesystemService.renderFileInBase64(page.thumbnailImage.file),
                    "oldActualImageName": page.viewImage.file.name,
                    "oldThumbnailImageName": page.thumbnailImage.file.name,
                    "transactionStatus":"U"
                ]
            ]
        }
        
        render response as JSON
    }
    
}

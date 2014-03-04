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
    
    // action to handle authentication
    def authenticate() {
        
        def response = [true]
        render response as JSON  
        
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
    
    def updateNodeProperties() { 
        
        def responsedata=[:]
        def data = request.JSON 
        def parent = Node.findById(data?.parentNodeId)
        if(parent) {
            def node = captureService.insertNode(parent, parent.project, "", filesystemService.generateLiteral(), "", "Page")
            responsedata = scanDoService.renderNode(node)
        }
        render responsedata as JSON 
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
    
    def updateNodePropertiesList() { }
    
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
        
        data response = [:]
        def data = request.JSON
        if(data?.encodeStringForImage && data?.parentNodeId) {
            def node = Node.findById(data?.parentNodeId)
            def userId = permissionsService.getUsername()
            def document = scanDoService.uploadImage(data?.encodeStringForImage, node, request.locale, userId)
            def task = workflowService.createTask(document, Task.TYPE_BUILD, userId)
            if (document && task) { workflowService.assignTask(task, userId) }
            response.put(data.actualImageName, true)
        }
        render response as JSON
    }
    
    def checkIfNodeIsUpdatedByOtherUser() { 
        
        def response = [false]
        render response as JSON  
        
    }
    
    def getChildNodeCount() { }
    
    def updateAttendance() {
      redirect(action: "authenticate")
    }
    
    def getEncodedActualImageString() { }   
}

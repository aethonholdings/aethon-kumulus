package com.kumulus.controllers.workflow

import grails.converters.*
import com.kumulus.domain.*
import java.text.DateFormat;
import java.text.SimpleDateFormat

class ScanDoController {

    def scanDoService
    def captureService
    def permissionService
    def filesystemService
    
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
        def parent = Node.findById(data.parentNodeId)
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
            
            //  MERGE BELOW INTO SCANDO SERVICE
            
            def renderNode = [
                'nodeId': "" + node.id,
                'projectId': "" + node.project.id,
                'name': "" + node.barcode?.text,                                 // scando requires the barcode as name
                'type': "" + node.type.code,    
                'barcode': "" + node.barcode?.text,              
                'comment': node.comment,
                'internalComment': node.internalComment,
                'status': "" + node.status,
                'parentNodeId': node.parent?.id,
                'hierarchy': captureService.getScanDoNodeHierarchy(node),       // INJECT THE HIERARCHY HERE
                'thumbnailImageName': null,             
                'actualImageName': null,
                'lastUpdateDateTime': node.lastUpdateDatetime,
                'documentSequenceNumber': null,
                'userId': null,           
                'encodeStringForImage': null,
                'encodeStringForThumbnail': null,
                'oldActualImageName': null,
                'oldThumbnailImageName': null,
                'transactionStatus': "U"            
            ]                 
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
        HashMap<String,String> statusMap= new HashMap<String, String>();
        HashMap<String,String> nodeTypeMap= new HashMap<String, String>();
        statusMap.put("Page","P");
        statusMap.put("Box","B");
        statusMap.put("Container","C");
        nodeTypeMap.put("In Progress","0");
        nodeTypeMap.put("Done","1");
        nodeTypeMap.put("Sealed","2");                                                                                       
        sessiondata= [                                 
            'version': "v1.1.3",
            'userid': params.username,
            'projectId': "-1",
            'collectionRight':"N",
            'breathInterval':"5",
            'importRight':"Y",
            'separationRight': "N",
            'LocalStoragePath':null,
            'projectName':"Scan barcodes",
            'SeparationTarget':"0",
            'refreshInterval':"600000",
            'totalImagesToUploadAtOnce':"21",
            'setOverallTarget':"5000",
            'localThumbnailDirPath':null,
            'nodeTypeMap':nodeTypeMap ,
            'setStatusMap':statusMap,
        ]                                                                             
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
    
    def saveScannedImages() { }
    
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

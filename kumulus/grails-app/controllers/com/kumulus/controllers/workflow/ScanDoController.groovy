package com.kumulus.controllers.workflow

import grails.converters.*
import com.kumulus.domain.*
import java.text.DateFormat;
import java.text.SimpleDateFormat

class ScanDoController {

    def captureService
    def permissionService
    
    // action to handle authentication
    def authenticate() {
        def data = request.JSON
        println(data)
        def response = [true]
        render response as JSON  
    }
    
    def fetchProjectList(){  
        
        println("Json "+params)
        def projectlist =Project.list()         
        println("*******************project List"+projectlist)
        def responseData=[:]
        def projects=[]
        projectlist.each{ project ->
            responseData.project.id = it.projectName           
        }      
        render responseData as JSON  
    }
    
    
    def updateNodeProperties() { }
    
    def fetchChildNodeList() {
        def nodeList 
        def data = request.JSON
    
        if(data?.parentnodeId==null) {
            def project = Project.findById(data?.projectId)
            if(project) nodeList = project.nodes.findAll { node -> 
                parent == null 
                type.isContainer == true
            }
        } else {
            def parent = Node.findById(data?.nodeId)
            if(parent) { 
                nodeList = Node.findAll { node -> 
                    parent == parent
                    type.isContainer == true
                }
            }
        }
        def responsedata =[]
        nodeList.each{ node ->
            def list = new ArrayList()

            renderNode = [
                'nodeId':"" + node.id,
                'projectId':"" + node.project.id,
                'name':"" + node.barcode,                                       // scando requires the barcode as name
                'type': "" + node.type.code,    
                'barcode': "" + node.barcode,              
                'comment': node.comment,
                'internalComment': node.internalComment,
                'status': "" + node.status,
                'parentNodeId': node.parent,
                'hierarchy': captureService.getScanDoNodeHierarchy(node),                                   // INJECT THE HIERARCHY HERE
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
            // renderNode.hierarchy=list.toString()	     
            responsedata.add(renderNode)            
        }     
        println(responsedata)
        render responsedata as JSON 
    }
    
    def getProjectBybarcode() {
        
        def data = request.JSON
        def responsedata =[
            'projectId': -1,
            'projectName': "Scan barcode"
       ]
       
        def node = Node.findByBarcode(data?.barcode)
        if(node) {
            responsedata.projectId = node.project.id
            responsedata.projectName = node.project.projectName            
        }
        render responsedata as JSON  
    }
    
    
    def fetchSessionData() {
        println("***********"+params)
        def sessiondata=[:]
        HashMap<String,String> statusMap= new HashMap<String, String>();
        HashMap<String,String> nodeTypeMap= new HashMap<String, String>();
        statusMap.put("Document","D");
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
            def node = Node.findByBarcode(data?.searchBarcode)
            if(node) {
                def projectName = node.project.projectName
                def barcodes = [node.barcode]
                while(node.parent!=null) {
                    node = node.parent
                    barcodes.add(node.barcode)
                } 
                barcodes.add(projectName)
                ListIterator nodeslist = barcodes.listIterator(barcodes.size());
                while (nodeslist.hasPrevious()) {
                    responseString = responseString + nodeslist.previous().toString()
                    if(nodeslist.hasPrevious()) responseString = responseString + ", "
                }
            }
            responseString = responseString + "]"
        }
        response.outputStream << responseString
    }
    
    def saveScannedImages() { }
    
    def checkIfNodeIsUpdatedByOtherUser() { }
    
    def getChildNodeCount() { }
    def updateAttendance() {
      redirect(action: "authenticate")
    }
    
    def getEncodedActualImageString() { }   
}

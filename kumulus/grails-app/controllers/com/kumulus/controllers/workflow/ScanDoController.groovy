package com.kumulus.controllers.workflow

import grails.converters.*
import com.kumulus.domain.*
class ScanDoController {
    
    // action to handle authentication
    def authenticate() {
        def data = request.JSON
        println(data)
        def response = [true]
        render response as JSON  
    }
    
    def fetchProjectList(){     
        def projectlist =Project.list()         
        println("*******************project List"+projectlist)
         def responseData=[:]
         int i=1
         projectlist.each{
         responseData = [
          '1': it.projectName           
        ] 
        i++
         }        
       render responseData as JSON  
    }
    
    
    def updateNodeProperties() { }
    
    def fetchChildNodeList() {
        def nodeList 
        def data = request.JSON
        println("Project Id in fetchChildNodeList"+data)   
        if(data?.parentId==null) {
            def project = Project.findById(data?.projectId)
            if(project) nodeList = project.nodes.findAll { node -> node.parent == null }
        } else {
            def parent = Node.findById(data?.nodeId)
            nodeList = Node.findByParent(parent)
        }
        println("number of nodes"+nodeList.size())
        def responsedata =[]
        def list;        
        def renderNode =[hierarchy:'']
        nodeList.each{ node->
            list= new ArrayList()
            list.add(node.project.projectName)
            list.add(node.barcode)
            renderNode =[
              'barcode':node.barcode, 
              'name':node.name,
              'comment':node.comment,
              'internalComment':node.internalComment,
              'status':node.status,
              'projectId':node.project.id,
//            'hierarchy':[node.project.projectName,node.barcode],
              'lastUpdateDateTime':node.lastUpdateDatetime ,
              'parentNodeId': node.parent,
              'type': node.type.code,
              'nodeId':node.id,
              'documentSequenceNumber':null,
              'userId':null,
              'thumbnailImageName':null,
               'actualImageName':null,
               'documentSequenceNumber':null,
               'encodeStringForImage':null,
               'encodeStringForThumbnail':null,
               'oldActualImageName':null,
               'oldThumbnailImageName':null,
               'transactionStatus':"U"            
               
            ]
            
            renderNode.hierarchy=list.toString()	     
            responsedata.add(renderNode)            
        }     
        println(responsedata)
        render responsedata as JSON 
    }
    
    def getProjectBybarcode() {
        
        def data = request.JSON
        println("data is "+data)
        def responsedata =[
            'projectId': null,
            'projectName': null
       ]
       // def data = request.JSON
       
        def node = Node.findByBarcode(data?.barcode)
        if(node) {
            def project = node.project
            responsedata.projectId = project.id
            responsedata.projectName=project.projectName            
            println(project)
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
        String responseString = "["
        def data = request.JSON       
        def node = Node.findByBarcode(data?.searchBarcode)
        String projectName = Project.findById(data?.projectId).projectName                
        def nodes = [node.barcode]
        // def finalhierarchy =new ArrayList<String>()
        ArrayList<String> finalhierarchy = new ArrayList<String>()
        while(node.parent!=null) {            
            node = node.parent
            nodes.add(node.barcode)
         }
        nodes.add(projectName)
        
        ListIterator nodeslist = nodes.listIterator(nodes.size());
        while (nodeslist.hasPrevious()) {
            // finalhierarchy.add(nodeslist.previous().toString())
            responseString = responseString + nodeslist.previous().toString()
            if(nodeslist.hasPrevious()) responseString = responseString + ", "
        }
        responseString = responseString + "]"
        response.outputStream << responseString
    }
    
    def saveScannedImages() { }
    
    def checkIfNodeIsUpdatedByOtherUser() { }
    
    def getChildNodeCount() { }
    
    def getEncodedActualImageString() { }
    
}

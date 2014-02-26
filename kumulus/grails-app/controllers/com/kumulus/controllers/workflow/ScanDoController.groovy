package com.kumulus.controllers.workflow


import grails.converters.*
import com.kumulus.domain.*
class ScanDoController {
    
    
    // action to handle authentication
    def authenticate() {
       def results = "true"
     
       println("UserId "+params.username)
       println("password"+params.password)
       
       //authentication code goes here 
        def responseData = [
            'results': results,
         ]
        render responseData as JSON  
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
        def nodebyBarcode =Node.findByBarcode("AE9999990019Z")       
        def nodelist = Node.findAllByProject(nodebyBarcode.project)        
        println("number of nodes"+nodelist.size())
        def responsedata =[]
        def list;        
        def renderNode =[hierarchy:'']
        nodelist.each{node->
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
        
        render responsedata as JSON  
    
    }
    
    def fetchSessionData() {
       println("UserId "+params.username)
       println("password"+params.password)
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
             'projectId': "2",
             'collectionRight':"N",
             'breathInterval':"5",
             'importRight':"Y",
             'LocalStoragePath':null,
             'projectName':"New",
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
    
    def getHierarchyFromSearchBarcode() { }
    
    def saveScannedImages() { }
    
    def checkIfNodeIsUpdatedByOtherUser() { }
    
    def getChildNodeCount() { }
    
    def getEncodedActualImageString() { }
    
}

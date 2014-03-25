package com.kumulus.services

import com.kumulus.domain.*
import grails.transaction.Transactional

@Transactional
class ScanDoService {

    def captureService
    def filesystemService
    def permissionsService
    
    String getScanDoNodeHierarchy(Node node) {
        String hierarchy
        hierarchy = "["           
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
                hierarchy = hierarchy + nodeslist.previous().toString()
                if(nodeslist.hasPrevious()) hierarchy = hierarchy + ", "
            }
        }
        hierarchy = hierarchy + "]"
        return(hierarchy)
    }
    
    def renderNode(Node node) {
        def name 
        
        if(node.barcode?.text) name = node.barcode?.text else name = node.name
        
        def render = [
            'nodeId': "" + node.id,
            'projectId': "" + node.project.id,
            'name': "" + name,                                 // scando requires the barcode as name
            'type': "" + node.type.code,    
            'barcode': "" + node.barcode?.text,              
            'comment': node.comment,
            'internalComment': node.internalComment,
            'status': "" + node.status,
            'parentNodeId': node.parent?.id,
            'hierarchy': getScanDoNodeHierarchy(node),       // INJECT THE HIERARCHY HERE
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
        return(render)
        
    }
    
    def uploadImage (String encodedImageString, Node node, Locale locale, String userId) {
        
        def document
        if (node && encodedImageString) {
            def scanBatch = new ScanBatch(userId: userId, timestamp: new Date(), project: node.project)
            scanBatch.save()
            def uFile = filesystemService.writeStringToImageFile(encodedImageString, node.name + ".jpg", locale)
            document = captureService.indexScan(node, uFile, scanBatch, userId)
        }  
        return(document)
    }
    
    def renderSessionData(String username) {
        
        HashMap<String,String> statusMap= new HashMap<String, String>();
        HashMap<String,String> nodeTypeMap= new HashMap<String, String>();
        statusMap.put("Page","P");
        statusMap.put("Box","B");
        statusMap.put("Container","C");
        nodeTypeMap.put("In Progress","0");
        nodeTypeMap.put("Done","1");
        nodeTypeMap.put("Sealed","2");                                                                                       
        def sessiondata = [                                 
            'version': "v1.1.3",
            'userid': username,
            'projectId': "-1",
            'collectionRight':"N",
            'breathInterval':"5",
            'importRight':"Y",
            'separationRight': "N",
            'LocalStoragePath': null,
            'projectName':"Scan barcodes",
            'SeparationTarget':"0",
            'refreshInterval':"600000",
            'totalImagesToUploadAtOnce':"2",
            'setOverallTarget':"5000",
            'localThumbnailDirPath': null,
            'nodeTypeMap':nodeTypeMap ,
            'setStatusMap':statusMap,
        ]
        return(sessiondata)
    }
    
}

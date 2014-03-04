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
        def render = [
            
            'nodeId': "" + node.id,
            'projectId': "" + node.project.id,
            'name': "" + node.barcode?.text,                                 // scando requires the barcode as name
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
           
//            "nodeId": node.id,
//            "projectId": node.project.id,
//            "name": node.name,
//            "type": node.type,
//            "barcode": node.barcode?.text,
//            "comment": node.comment,
//            "internalComment": node.internalComment,
//            "status": node.status,
//            "parentNodeId": node.parent?.id,
//            "hierarchy": getScanDoNodeHierarchy(node),
//            "thumbnailImageName": null,
//            "actualImageName": null,
//            "lastUpdateDateTime": node.createDatetime,
//            "documentSequenceNumber": null,
//            "userId": node.creatorId,
//            "encodeStringForImage": null,
//            "encodeStringForThumbnail": null,
//            "oldActualImageName": null,
//            "oldThumbnailImageName": null,
//            "transactionStatus": "U"
        ]   
        return(render)
        
    }
    
    def uploadImage (String encodedImageString, Node node, Locale locale) {
        
        if (node && encodedImageString) {
            def userId = permissionsService.getUsername()
            def scanBatch = new ScanBatch(userId: userId, timestamp: new Date(), project: node.project)
            scanBatch.save()
            def uFile = filesystemService.writeStringToImageFile(encodedImageString, node.name + ".png", locale)
            captureService.indexScan(node.parent, uFile, scanBatch, userId)
            node.delete()                                                       // remove the temporary node the client requested
            def task = workflowService.createTask(document, Task.TYPE_BUILD, userId)
            if (document && task) {
                workflowService.assignTask(task, userId)
                redirect controller: "capture", action: "upload", id: node.project.id
            }
            return(task)
        }    
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
            'totalImagesToUploadAtOnce':"21",
            'setOverallTarget':"5000",
            'localThumbnailDirPath': null,
            'nodeTypeMap':nodeTypeMap ,
            'setStatusMap':statusMap,
        ]
        return(sessiondata)
    }
    
    
    
    
    def fakeImage() {
        return ("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QwBByDSGLRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAI33D9KydMH+nS/wC7/WtV/wDVt9KytMH+mynttoA16KKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAzzSMyoMscClooAKTJOMDHrmlooATA49qWiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKarFs5Urg4Ge/vTqACiiigAooqrdajZ2RAubhIyegY0AWqKZFLHNEskbBkYZBHen0AFFFFABRRRQAUUUUAFFFFABRRRQAUUUUANkcRxPI3RQSfwrF0LxPa65cXEESFHiPIJzkVsyhWhdW+6VINeS+C5/snja4jU/unkaPk9800B67RRRSAKKKiuLiG1hMs8ipGOpNAEtFVbHUbXUYy9rKHAOCO4q1QAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAMkjDlcswwc4Bxn604AKAAAAOwpaKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKilnjhIDnBPSgCWiozPEpAZgCexp+RxyOelAC0UUUAFFFFABRRRQAUUUUAFFFFABRRVLUDIQkaA8nJxQgLtFNTOwZ64p1ABRRRQAUUUUAFFGeaKACimuxVSQpY+gpVO5QSME9qAFooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACkXdzux14x6UtFABRRRQAVmalo8V+mCie4NadFAHnF7pN7o0jS2+5owc7fStbRfEm/CSHB7qa6y4jEsLIwyD2riNa0N4C1xboUOeAB1qtxW7HcQzJPGHQ5H8qkrz3SdfmtZRHMSpHHNdtZahFdoMEBiPzqQuXKKKKBhRRRQAUUUUAFFFFABRRRQAUUUUAFFFFADX/1bfQ1laWp+2SnsFxWs33TWXpvF5KM9ulAGrRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUVl3fiLSbGbybi9iWT+7nNAGpRWbZa9puoTmG2ukeTrtz1rSoAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiisrXtdtdC097idxux8i+poATX9etdB097id13gfIhPU15Tdvqvi+GXVoyYYlbAz0IHU1VWz1X4j660pLrYq+2Rh0A9q9Sfw2bfwm+kWjhWC4VlFNFPQb4Dlkl8MQ+YSdrFQSe1dNWZoGmHSNFt7NiC6L8xHc1p0mSFFFFABRQTgZPSuU8V+M7LQ7GRYpke6IwoB+7QBU8ZeO/8AhGrpLeKFZDjc7E9Pauo0fUl1fSbe+RSomQNg9q8e8N+GNV8ZauupaoGFir7vn/j9K9rhhjt4UhiQJGgwqjoKCpW2RJRRRQSFFFFABRRRQAV5LqGmNB8U7VE/dJJJ5igHANetVh6l4ejvtbstTBxJbtz9KaBG5RRRSAK81+I2rTy6ha6Lak7pCM49TXpDsEjZz0UEmvH9GuG1/wCJkkmciNyxU+goGu5oaZDf+D9dt47l2kimADHPBBr1EEEAjoa43x/GohsZz/DKB+tdbatutIWHQoD+lNgTUUUUhBRTDNEJfK8xPMP8G4Z/Kn0AFFFFABRRRQAUUUUAFFZPiDWo9F055iyeaR8iscV55LqHiiVTq08jQwggp2BH0osM9ZoqlpF219pVvcP950yeO9XaBBRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFACEgdTS0hUMRkZxyKzPEGrx6Jo8945XcE")
    }
    
}

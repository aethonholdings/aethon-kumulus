package com.kumulus.services

import com.kumulus.domain.*
import grails.transaction.Transactional

@Transactional
class ScanDoService {

    def captureService
    
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
            "nodeId": node.id,
            "projectId": node.project.id,
            "name": node.name,
            "type": node.type,
            "barcode": node.barcode?.text,
            "comment": node.comment,
            "internalComment": node.internalComment,
            "status": node.status,
            "parentNodeId": node.parent?.id,
            "hierarchy": getScanDoNodeHierarchy(node),
            "thumbnailImageName": null,
            "actualImageName": null,
            "lastUpdateDateTime": node.createDatetime,
            "documentSequenceNumber": null,
            "userId": node.creatorId,
            "encodeStringForImage": null,
            "encodeStringForThumbnail": null,
            "oldActualImageName": null,
            "oldThumbnailImageName": null,
            "transactionStatus": "U"
        ]       
    }
    
}

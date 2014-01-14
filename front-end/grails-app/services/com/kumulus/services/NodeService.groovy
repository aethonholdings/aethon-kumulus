package com.kumulus.services

import com.kumulus.domain.Nodes
import grails.transaction.Transactional

@Transactional
class NodeService {

    def deleteNode(nodeID) {
        def node = Nodes.findById(nodeID)
        if(node) {
            def children = Nodes.findAllByParent(node)
            for (child in children) {
                deleteNode(child.id)
            }
            node.parent = null;
            node.delete(flush: true)
        }
    }
    
    def saveNode(node, barcode, comment, internalComment, type, status) {
        if(node && !node.hasErrors()){
            def nodeType
            if(comment) node.comment = comment
            if(barcode) {
                node.barcode = barcode
                node.name = barcode
            }
            if(internalComment) node.internalComment = internalComment;
            if(status) node.status = status
            switch(type) {
                case 'Box':
                nodeType = 'B'
                break
                
                case 'Container':
                nodeType = 'C'
                break
                
                case 'Document':
                nodeType = 'D'
                break
            }
            node.type = nodeType
            node.save()
        }
    }
}

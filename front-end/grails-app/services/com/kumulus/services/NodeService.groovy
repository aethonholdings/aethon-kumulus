package com.kumulus.services

import com.kumulus.domain.Nodes
import grails.transaction.Transactional

@Transactional
class NodeService {

    def getNode(nodeID, expand) {
        def node = Nodes.findById(nodeID)
        def children = []
        if(node) {
            def childNodes = Nodes.findAllByParent(node)
            for (child in childNodes) {
                children.add(getNode(child.id, false))
            }
            
            if(node.type!='D') {
                def parentID = 'ROOT'
                def nodeType
                if(node.parent) parentID = node.parent.id
                switch(node.type) {
                    case 'B':
                    nodeType = "Box"
                    break

                    case 'C':
                    nodeType = "Container"
                    break
                }
                def treeNode = [
                    key: node.id,
                    title: node.comment,
                    isFolder: true,
                    expand: expand,
                    children: children,
                    parent: parentID,
                    text: node.name, 
                    barcode: node.barcode,
                    comment: node.internalComment,
                    type: nodeType,
                    id: node.id
                ]
                return(treeNode)
            }
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
            println(node)
        }
    }
    
}

package com.kumulus.services

import com.kumulus.domain.Nodes
import grails.transaction.Transactional

@Transactional
class NodeService {

    def getNode(nodeID) {
        def node = Nodes.findById(nodeID)
        def children = []
        if(node) {
            def childNodes = Nodes.findAllByParent(node)
            for (child in childNodes) {
                children.add(getNode(child.id))
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
                    expand: true,
                    children: children,
                    parent: parentID,
                    text: node.name, 
                    barcode: node.barcode,
                    type: nodeType
                ]
                return(treeNode)
            }
        }
    }
}

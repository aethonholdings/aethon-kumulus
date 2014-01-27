package com.kumulus.services

import com.kumulus.domain.Node
import grails.transaction.Transactional

@Transactional
class NodeService {

    def deleteNode(nodeID) {
        def node = Node.findById(nodeID)
        if(node) {
            def children = Node.findAllByParent(node)
            for (child in children) {
                deleteNode(child.id)
            }
            node.parent = null;
            node.delete(flush: true)
        }
    }
    
    def saveNode(node, barcode, name, comment, type, status) {
        if(node && !node.hasErrors()){
            def nodeType
            node.comment = comment
            node.barcode = barcode
            node.name = name
            node.status = status
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
    
    def renderNode(node) {
        if(node) {
            def nodeType, isFolder
            switch(node.type) {
                case 'B':
                    nodeType = "Box"
                    isFolder = true
                    break

                case 'C':
                    nodeType = "Container"
                    isFolder = true
                    break
                
                case 'D':
                    nodeType = "Document"
                    isFolder = false
                    break
            }
            def treeNode = [
                key: node.id,
                title: node.name,
                isLazy: true,
                text: node.name, 
                barcode: node.barcode,
                isFolder: isFolder,
                comment: node.comment,
                type: nodeType,
                id: node.id, 
                project: node.project.id
            ]
        }
    }
    
    def renderRoot(project) {
        
        def children = []
        def root = [
            key: "#",
            title: "Root",
            isFolder: true,
            expand: false,
            select: true,
            isLazy: false,
            parent: null,
            text: null, 
            barcode: null,
            comment: null,
            children: children,
            type: "ROOT",
            id: "ROOT",
            project: project.id
        ]
        
        // get the top-level nodes
        def nodeList = Node.findAllByProjectAndParent(project, null)  // temporary solution, should be filtering out documents here
        nodeList.each { root.children.add renderNode(it) }
        return(root)
    }
}

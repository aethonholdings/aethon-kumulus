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
    
    def getTree(nodeID, expand) {
        def node = Nodes.findById(nodeID)
        def children = []
        if(node) {
            def childNodes = Nodes.findAllByParent(node)
            for (child in childNodes) {
                children.add(getTree(child.id, false))
            }

            if(node.type!='D') {
                def nodeType
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
                    parent: node?.parent,
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
                title: node.comment,
                isLazy: true,
                text: node.name, 
                barcode: node.barcode,
                isFolder: isFolder,
                comment: node.internalComment,
                type: nodeType,
                id: node.id
            ]
        }
    }
    
    def renderRoot(project) {
        
        def children = []
        def root = [
            key: "#",
            title: "Root",
            isFolder: true,
            expand: true,
            select: true,
            isLazy: false,
            parent: null,
            text: null, 
            barcode: null,
            comment: null,
            children: children,
            type: "ROOT",
            id: "ROOT"
        ]
        
        // get the top-level nodes
        def nodeList = Nodes.findAllByProjectAndParent(project, null)  // temporary solution, should be filtering out documents here
        nodeList.each {
            root.children.add renderNode(it)
        }
        return(root)
    }
}

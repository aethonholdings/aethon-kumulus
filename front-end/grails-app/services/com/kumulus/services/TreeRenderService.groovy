package com.kumulus.services

import com.kumulus.domain.Nodes
import grails.transaction.Transactional

@Transactional
class TreeRenderService {

    def nodeService
    
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
            expand: true,
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
        def nodeList = Nodes.findAllByProjectAndParent(project, null)  // temporary solution, should be filtering out documents here
        nodeList.each {
            root.children.add renderNode(it)
        }
        return(root)
    }
    
}

package com.kumulus.services

import com.kumulus.domain.*
import grails.transaction.Transactional

@Transactional
class CaptureService {

    def springSecurityService
    
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
    
    def insertNode(parent, project, barcode, name, comment, type) {
        
        def nodeType = NodeType.findById(type)
        if(project && nodeType && name) {
            def timestamp = new Date()            
            def node = new Node()
            node.creatorId = springSecurityService.principal.username
            node.lastUpdateId = springSecurityService.principal.username
            node.project = project
            node.status = Node.STATUS_OPEN
            node.type = nodeType
            node.parent = parent
            node.barcode = barcode
            node.name = name
            node.comment = comment
            node.createDatetime = timestamp
            node.lastUpdateDatetime = timestamp
            node.save()
            return(node)
        }
        return(null)
    }
    
    def updateNode(node, barcode, name, comment, type, status) {
        def nodeType = NodeType.findById(type)
        if(node && !node.hasErrors() && nodeType){
            node.comment = comment
            node.barcode = barcode
            node.name = name
            node.status = status
            node.type = nodeType
            node.save()
        }
    }
    
    def renderNode(node) {
        if(node) {
            def treeNode = [
                key: node.id,
                title: node.name,
                isLazy: true,
                text: node.name, 
                barcode: node.barcode,
                isFolder: node.type.isContainer,
                comment: node.comment,
                type: node.type.name,
                id: node.id, 
                project: node.project.id
            ]
            return(treeNode)
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
        def nodeList = Node.findAllByProjectAndParent(project, null)  // temporary solution, should be filtering out documents here
        nodeList.each { root.children.add renderNode(it) }
        return(root)
    }
    def moveNode(sourceNodeId,targetNodeId){
        println("moving")
        
        
        
    }
}

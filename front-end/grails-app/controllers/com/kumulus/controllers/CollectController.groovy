package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*
import grails.converters.*

class CollectController {

    @Secured(['ROLE_COLLECT'])
    def index() { 
        def projectList = Project.findAllByStatus("A")
        render view:"index", layout:"home", model:[projects: projectList]
    }
    
    @Secured(['ROLE_COLLECT'])
    def workflow() {
        // this is not secured at user permission level yet
        def project = Project.findById(params.id)
        render view:"workflow", layout:"home", model:[project: project]
    }
    
    @Secured(['ROLE_COLLECT'])
    def refreshTree() {
        // this is not secured at user permission level yet
        def project = Project.findById(params.id)
        def nodeList = Nodes.findAllByProject(project)  // temporary solution, should be filtering out documents here
        def tree = []
        
        // add the root node
        def rootTreeNode = [
            id: 'ROOT',
            parent: '#',         
            text: project.projectName,
            barcode: "",
            comment: "",
            type: "ROOT"
        ]
        tree.add(rootTreeNode)
        
        // build the tree under the root node
        for(node in nodeList) {
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
                    id: node.id,
                    parent: parentID,
                    text: node.name, 
                    barcode: node.barcode,
                    comment: node.comment,
                    type: nodeType
                ]
                tree.add(treeNode)
            }
        }
        render tree as JSON        
    }
    
}

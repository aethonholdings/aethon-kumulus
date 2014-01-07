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
        def nodeList = Nodes.findAllByProject(Project.findById(params.id))
        def tree = []
        for(node in nodeList) {
            def parentId = "#"
            if(node.parent) parentId = node?.parent.id
            def treeNode = [
                id: node.id,
                text: node.name, 
                parent: parentId
            ]
            tree.add(treeNode)
        }
        render tree as JSON        
    }
    
}

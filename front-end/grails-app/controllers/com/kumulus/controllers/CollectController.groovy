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
        def nodeList = Nodes.findAllByProject(project)
        def tree = []
        def rootTreeNode = [
            id: 'root',
            text: project.projectName, 
            parent: '#', 
            state: [
                selected: true,
                opened: true
            ]
        ]
        tree.add(rootTreeNode)
        for(node in nodeList) {
            def parentID = 'root'
            if(node.parent) parentID = node.parent.id
            def treeNode = [
                id: node.id,
                text: node.name, 
                parent: parentID,
                state: [
                    opened: false
                ]
            ]
            tree.add(treeNode)
        }
        render tree as JSON        
    }
    
}

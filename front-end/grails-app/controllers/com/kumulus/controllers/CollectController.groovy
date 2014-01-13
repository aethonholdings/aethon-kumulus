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
        def project = Project.findById(params?.id)
        if(project) {
            def nodeList = Nodes.findAllByProject(project)  // temporary solution, should be filtering out documents here
            def tree = []

            // build the tree
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
                        key: node.id,
                        title: node.comment,
                        isFolder: true,
                        expand: true,
                        parent: parentID,
                        text: node.name, 
                        barcode: node.barcode,
                        
                        type: nodeType
                    ]
                    tree.add(treeNode)
                }
            }
            render tree as JSON  
        }
    }
    
    @Secured(['ROLE_COLLECT'])
    def update() {
        // this is not secured at user permission level yet
        def data = request.JSON
        def node = Nodes.findById(data?.id)
        if(node && !node.hasErrors()){
            def projectID
            def nodeType
            node.comment = data?.comment;    
            switch(data?.type) {
                case 'Box':
                    nodeType = 'B'
                    break
                case 'Container':
                    nodeType = 'C'
                    break
            }
            projectID = node.project.id
            node.type = nodeType
            node.save()
            redirect (action: "refreshTree", params: [id: projectID])
        }
        
    }
}

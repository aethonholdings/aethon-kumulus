package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*
import grails.converters.*

class CollectController {

    def nodeService
    
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
    def getProject() {
        def project = Project.findById(params?.id)
        if(project) {
            // get the root nodes
            def nodeList = Nodes.findAllByProjectAndParent(project, null)  // temporary solution, should be filtering out documents here
            def tree = []
            for(node in nodeList) {
                tree.add nodeService.getNode(node.id, false)
            }
            // build the tree
            render tree as JSON  
        }
    }
        
    @Secured(['ROLE_COLLECT'])
    def update() {
        // this is not secured at user permission level yet
        def data = request.JSON
        def node = Nodes.findById(data?.id)
        if(node) nodeService.saveNode(node, data?.barcode, data?.name, data?.comment, data?.type)
        render node as JSON
    }
    
    @Secured(['ROLE_COLLECT'])
    def insert() {
        // this is not secured at user permission level yet
        def data = request.JSON
        def parent = Nodes.findById(data?.id)
        def node = new Nodes()
        if(node && data?.barcode && data?.name && data?.comment && data?.type && parent) {
            node.parent = parent
            node.project = parent.project
            nodeService.saveNode(node, data?.barcode, data?.name, data?.comment, data?.type, 0)
            render node as JSON
        }
    }
    
    @Secured(['ROLE_COLLECT'])
    def delete() {
        def data = request.JSON
        if(data?.id) {
            def node = Nodes.findById(data?.id)
            node.delete()
        }
    }
}

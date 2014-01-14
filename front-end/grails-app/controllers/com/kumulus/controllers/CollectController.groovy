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
    def getRoot() {
        def rootNode = nodeService.renderRoot(Project?.findById(params?.id))    
        render rootNode as JSON  
    }    
    
    @Secured(['ROLE_COLLECT'])
    def getChildren() {
        def treeNodes = []
        def node = Nodes.findById(params?.id)         // should check permissions first
        if (node) {
            def children = Nodes.findAllByParent(node)
            children.each {treeNodes.add(nodeService.renderNode(it))}
        }
        render treeNodes as JSON  
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
        def response = [done: false, message: "Error"]
        def data = request.JSON
        println(data)
        def parent
        if(data?.parentID!="ROOT") parent = Nodes.findById(data.parentID) else parent = null
        def project = Project.findById(data?.project)
        def node = new Nodes()
        if(node && data?.barcode && data?.name && data?.comment && data?.type && project) {
            node.parent = parent
            node.project = project
            nodeService.saveNode(node, data?.barcode, data?.name, data?.comment, data?.type, 0)
            response.done = true
            response.message = "OK"
        }
        render response as JSON
    }
    
    @Secured(['ROLE_COLLECT'])
    def delete() {
        def data = request.JSON
        def response = [done: false]
        if(data?.id) {
            nodeService.deleteNode(data.id)
            response.done = true
        }
        render response as JSON
    }
}

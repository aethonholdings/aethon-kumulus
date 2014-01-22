package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*
import grails.converters.*

@Secured(['ROLE_COLLECT'])
class NodeController {

    def nodeService
    def springSecurityService
    def userService

    def collect() {
        def project = Project.findById(params?.id)
        if(project.company == userService.getCompany()) render view:"collect", layout:"home", model:[project: project]    
    }
        
    def getRoot() {
        def rootNode
        if(Project.findById(params?.id).company == userService.getCompany()) {
            rootNode = nodeService.renderRoot(Project?.findById(params?.id))   
            render rootNode as JSON  
        }
    }    
    
    def getChildren() {
        def treeNodes = []
        def node = Nodes.findById(params?.id)         // should check permissions first
        if (node?.project.company == userService.getCompany()) {
            def children = Nodes.findAllByParent(node)
            children.each { if(it?.type!='D') treeNodes.add(nodeService.renderNode(it)) }
        }
        render treeNodes as JSON  
    }
    
    def update() {
        def data = request.JSON
        def node = Nodes.findById(data?.id)
        if (node?.project.company == userService.getCompany()) {
            nodeService.saveNode(node, data?.barcode, data?.name, data?.comment, data?.type, 0)
            render node as JSON
        }
    }
    
    // need to move a lot of this code into service
    def insert() {
        
        def data = request.JSON
        def response = [done: false, message: "Error"]
        def project = Project.findById(data?.project)
        if(project.company == userService.getCompany()) {
            def parent = null
            if(data?.parentID!="ROOT") parent = Nodes.findById(data.parentID)

            def node = new Nodes()
            if(node && data?.barcode && data?.name && data?.type && project) {
                def map = [
                    project: project,
                    parent: parent,
                    status: 0,
                    hierarchy: null,
                    thumbnailImageName: null, 
                    actualImageName: null,
                    creatorId: springSecurityService.principal.username,
                    createDatetime: new Date(),
                    lastUpdateId: springSecurityService.principal.username,
                    lastUpdateDatetime: new Date(),
                    documentSequenceNumber: null,
                    creatorldapuid: springSecurityService.principal.username,
                    uploaded: false
                ]
                bindData(node, map)
                nodeService.saveNode(node, data?.barcode, data?.name, data?.comment, data?.type, 0)
                response.done = true
                response.message = "OK"
            }
            render response as JSON
        }
    }
    
    def delete() {
        def data = request.JSON
        def response = [done: false]
        if(data?.id && Nodes.findById(data?.id).project.company == userService.getCompany()) {
            nodeService.deleteNode(data.id)
            response.done = true
        }
        render response as JSON
    }

}

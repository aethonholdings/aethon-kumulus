package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*
import grails.converters.*

@Secured(['ROLE_ADMIN', 'ROLE_PROCESS', 'ROLE_IMPORT', 'ROLE_REVIEW', 'ROLE_SUPERVISE', 'ROLE_VIEW'])
class NodeController {

    def captureService
    def springSecurityService
    def permissionsService
        
    def getRoot() {
        def rootNode
        if(Project.findById(params?.id).company == permissionsService.getCompany()) {
            rootNode = captureService.renderRoot(Project?.findById(params?.id))   
            render rootNode as JSON
        }
    }    
    
    def getChildren() {
        def treeNodes = []
        def node = Node.findById(params?.id)         // should check permissions first
        if (node?.project.company == permissionsService.getCompany()) {
            def children = Node.findAllByParent(node)
            children.each { if(it?.type!='P') treeNodes.add(captureService.renderNode(it)) }
        }
        render treeNodes as JSON  
    }
    
    def update() {
        def data = request.JSON
        def node = Node.findById(data?.id)
        if (node?.project.company == permissionsService.getCompany()) {
            captureService.saveNode(node, data?.barcode, data?.name, data?.comment, data?.type, 0)
            render node as JSON
        }
    }
    
    // need to move a lot of this code into service
    def insert() {
        def data = request.JSON
        def response = [done: false, message: "Error"]
        def project = Project.findById(data?.project)
        if(project.company == permissionsService.getCompany()) {
            
            // BIT BELOW HERE NEEDS TO BE IN SERVICE
            
            def parent = null
            if(data?.parentID!="ROOT") parent = Node.findById(data.parentID)

            def node = new Node()
            if(node && data?.barcode && data?.name && data?.type && project) {
                def map = [
                    project: project,
                    parent: parent,
                    status: "0",
                    creatorId: springSecurityService.principal.username,
                    createDatetime: new Date(),
                    lastUpdateId: springSecurityService.principal.username,
                    lastUpdateDatetime: new Date(),
                ]
                bindData(node, map)
                captureService.saveNode(node, data?.barcode, data?.name, data?.comment, data?.type, 0)
                response.done = true
                response.message = "OK"
            }
            render response as JSON
        }
    }
    
    def delete() {
        def data = request.JSON
        def response = [done: false]
        if(data?.id && Node.findById(data?.id).project.company == permissionsService.getCompany()) {
            captureService.deleteNode(data.id)
            response.done = true
        }
        render response as JSON
    }
    
    def pickupNode(){
        println("dddddddddddddddd")
        
        
    }

}

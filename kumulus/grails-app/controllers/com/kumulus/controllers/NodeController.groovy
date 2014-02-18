package com.kumulus.controllers

import com.kumulus.domain.*
import grails.converters.*

class NodeController {

    def captureService
    def springSecurityService
    def permissionsService
        
    def getRoot() {
        def project = Project.findById(params?.id)
        if(permissionsService.checkPermissions(project)) {
            def rootNode = captureService.renderRoot(Project?.findById(params?.id))   
            render rootNode as JSON
        }
    }    
    
    def getChildren() {
        def treeNodes = []
        def node = Node.findById(params?.id)   
        if (permissionsService.checkPermissions(node)) {
            def children = Node.findAllByParent(node)
            children.each { if(it?.type!=Node.PAGE) treeNodes.add(captureService.renderNode(it)) }   
        }
        render treeNodes as JSON
    }
    
    def update() {
        def data = request.JSON
        def node = Node.findById(data?.id)
        if (permissionsService.checkPermissions(node)) {
            captureService.saveNode(node, data?.barcode, data?.name, data?.comment, data?.type, 0)
            render node as JSON
        }
    }
    
    // need to move a lot of this code into service
    def insert() {
        def data = request.JSON
        def response = [done: false, message: "Error"]
        def project = Project.findById(data?.project)
        if(permissionsService.checkPermissions(project)) {
            
            // BIT BELOW HERE NEEDS TO BE IN SERVICE
            
            def parent = null
            if(data?.parentID!="ROOT") parent = Node.findById(data.parentID)

            def node = new Node()
            if(node && data?.barcode && data?.name && data?.type && project) {
                def map = [
                    project: project,
                    parent: parent,
                    status: Node.STATUS_OPEN,
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
        }
        render response as JSON
    }
    
    def delete() {
        def data = request.JSON
        def response = [done: false]
        def node = Node.findById(data?.id)
        if(permissionsService.checkPermissions(node)) {
            captureService.deleteNode(data.id)
            response.done = true
        }
        render response as JSON
    }
    
    def list(){
        
        def query = Node.where {
            (project.company == permissionsService.getCompany() && type == Node.BOX && status == Node.STATUS_CLOSED)
        }
        def nodes = query.find()
        render nodes as JSON
    }

}

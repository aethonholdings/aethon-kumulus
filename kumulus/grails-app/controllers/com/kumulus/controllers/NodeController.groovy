package com.kumulus.controllers

import com.kumulus.domain.*
import grails.converters.*

class NodeController {

    def accessService
    def captureService
    def logisticsService
    def permissionsService
    
    def getRoot() {
        def project = Project.findById(params?.id)
        if(permissionsService.checkPermissions(project)) {
            def rootNode = accessService.renderRoot(Project?.findById(params?.id))   
            render rootNode as JSON
        }
    }    
    
    def getChildren() {
        def treeNodes = []
        def children
        if(params?.id && params.id!="#") { 
            def node = Node.findById(params?.id)   
            if (permissionsService.checkPermissions(node)) {
                children = Node.findAll ([sort: "name", order: "asc"]) {
                    (parent == node && type.isContainer == true)               // get all the non-page children nodes
                }
                children.each { treeNodes.add(accessService.renderNode(it)) }   
            }
        } else if(params?.projectId) {
            def project = Project.findById(params.projectId)
            if(project && permissionsService.checkPermissions(project)) {
                children = Node.findAll ([sort: "name", order: "asc"]) {
                    (project == project && parent == null && type.isContainer == true)  
                }
                children.each { treeNodes.add(accessService.renderNode(it)) }   
            }
        }
        render treeNodes as JSON
    }
    
    def update() {
        def data = request.JSON
        def node = Node.findById(data?.id)
        if (permissionsService.checkPermissions(node)) {
            captureService.updateNode(node, data?.barcode, data?.name, data?.comment, data?.type, Node.STATUS_OPEN, Node.LOCATION_CLIENT)
            render node as JSON
        }
    }
    
    def insert() {
        def data = request.JSON
        def response = [done: false, message: "Error"]
        def project = Project.findById(data?.project)
        def parent = null
        if(data?.parentId && data.parentId != "ROOT") parent = Node.findById(data?.parentId) 
        if(project && permissionsService.checkPermissions(project) && captureService.insertNode(parent, project, data?.barcode, data?.name, data?.comment, data?.type, Node.STATE_CLIENT_OPEN)) { 
            response.done = true
            response.message = "Success"
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
    
    def move(){
        def data = request.JSON
        def parent
        if(data?.targetId=="ROOT") parent = null else parent = Node.findById(data?.targetId)
        def child = Node.findById(data?.id)
        def response = [done: false]
        if(permissionsService.checkPermissions(child)) {
            if(parent && permissionsService.checkPermissions(parent)) child.parent = parent else child.parent = null
            child.save()
            response.done = true
        }
        render response as JSON
    }
    
    def getByBarcode() {
        def response = [
            success: false,
            data: []
        ]
        def node=Node.findByBarcode(Barcode.findByText(request.JSON?.barcode))
        if(node && permissionsService.checkPermissions(node)) {            
            response.success = true
            response.data = accessService.renderNode(node)
        }
        render response as JSON
    }
    
    def checkBarcode() {
        def status= ''
        def data = request.JSON
        def barcode = Barcode.findByText(data?.barcode)
        def node = Node.findByBarcode(barcode)
        if(node || !barcode) status = 'true' else status = 'false'
        def response = [status : status]
        render response as JSON
    }
    
    def getDocuments() {
        def data = request.JSON
        def response = []
        if(data?.node && data.node!="ROOT") {
            def nodes = Node.findAll() { node -> 
                parent.id == data.node.toLong()
                page != null
                page.document.status >= Document.STATUS_PROCESSED
                [order: "createDatetime", sort: "asc"]
            }
            nodes.page.groupBy({page -> page.document}).each { row ->
                def document = row.key
                def responseData = [
                    id: document.id, 
                    status: document.status(),
                    thumbnailImageId: document.pages[0].thumbnailImage.id
                ]
                response.add(responseData)
            }
        }
        render response as JSON
    }
    
    def pickup() {
        def data = request.JSON
        def response = [
            success: false,
            data: []
        ]
        def node = Node.findById(data?.nodeId)
        if (permissionsService.checkPermissions(node)) {
            logisticsService.pickup(node, (boolean)data?.flag)
            response.success = true
        }
        render response as JSON
    }
        
}

package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*
import grails.converters.*

class NodeController {

    def nodeService
    def springSecurityService

    @Secured(['ROLE_COLLECT'])
    def collect() {
        def project = Project.findById(params.id)
        render view:"collect", layout:"home", model:[project: project]
    }
        
    @Secured(['ROLE_COLLECT', 'ROLE_ACCESS'])
    def getRoot() {
        def rootNode = nodeService.renderRoot(Project?.findById(params?.id))    
        render rootNode as JSON  
    }    
    
    @Secured(['ROLE_COLLECT', 'ROLE_ACCESS'])
    def getChildren() {
        def treeNodes = []
        def node = Nodes.findById(params?.id)         // should check permissions first
        if (node) {
            def children = Nodes.findAllByParent(node)
            children.each { if(it?.type!='D') treeNodes.add(nodeService.renderNode(it)) }
        }
        render treeNodes as JSON  
    }
    
    @Secured(['ROLE_COLLECT'])
    def update() {
        // this is not secured at user permission level yet
        def data = request.JSON
        def node = Nodes.findById(data?.id)
        if(node) nodeService.saveNode(node, data?.barcode, data?.name, data?.comment, data?.type, 0)
        render node as JSON
    }
    
    // need to move a lot of this code into service
    @Secured(['ROLE_COLLECT'])
    def insert() {
        // this is not secured at user permission level yet
        def data = request.JSON
        def response = [done: false, message: "Error"]
        if(data?.parentID!="ROOT") def parent = Nodes.findById(data.parentID) else def parent = null
        def project = Project.findById(data?.project)
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

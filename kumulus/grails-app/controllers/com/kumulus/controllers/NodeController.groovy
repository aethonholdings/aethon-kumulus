



package com.kumulus.controllers

import com.kumulus.domain.*
import grails.converters.*

class NodeController {

    def captureService
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
            def children = Node.findAll {
                (parent == node && type.isContainer == true)               // get all the non-page children nodes
            }
            children.each { treeNodes.add(captureService.renderNode(it)) }   
        }
        render treeNodes as JSON
    }
    
    def update() {
        def data = request.JSON
        def node = Node.findById(data?.id)
        if (permissionsService.checkPermissions(node)) {
            captureService.updateNode(node, data?.barcode, data?.name, data?.comment, data?.type, Node.STATUS_OPEN)
            render node as JSON
        }
    }
    
    def insert() {
        def data = request.JSON
        def response = [done: false, message: "Error"]
        def project = Project.findById(data?.project)
        def parent
        if(data?.parentID && data.parentID != "ROOT") 
        parent = Node.findById(data?.parentID) else parent = null
        if(project && permissionsService.checkPermissions(project) && captureService.insertNode(parent, project, data?.barcode, data?.name, data?.comment, data?.type)) { 
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
    
    def list(){
        
        def nodes = []
        nodes = Node.findAll {
            (type == NodeType.findByName("Box") && status == Node.STATUS_CLOSED)
        }
        render nodes as JSON
    }

    def move(){
        def data = request.JSON
        def parent
        if(data?.targetId=="ROOT") parent = null else parent = Node.findById(data?.targetId)
        def child = Node.findById(data?.id)
        def response = [done: false]
        if(permissionsService.checkPermissions(parent) && permissionsService.checkPermissions(child)) {
            if(parent) child.parent = parent else child.parent = null
            child.save()
            response.done = true
        }
        render response as JSON
    }

    def test(){
        def data = request.JSON
        def response = [done: false]
        // handle this to send email request
        // need to create a logistics shipment instance
        render response as JSON
    }
    def getNodeFromTree(){
        def nodelist = []  
        println("barcode " +params.enterBarcode)      
        def barcodeNode = Node.findByBarcode(params?.enterBarcode)
        println("barcode is" + barcodeNode)

        def children = Node.findAllByParent(barcodeNode)//i.e node's child
        println("children is" + children)
     
        def parentNode = barcodeNode.parent//i.e node's parent
        println("parent is " + parentNode)
     
        def grandParent = parentNode.parent //i.e node's grand parent
        println("grand parent is " + grandParent)
        def parentNodeChild = Node.findAllByParent(parentNode) //i.e sibling
        println("parent child is " + parentNodeChild)
         
        def grandParentChild = Node.findAllByParent(grandParent) //i.e uncles/aunts
        println("grandparent child is " + grandParentChild)       
        
    }     
    
    def searchNode () {
        def data = request.JSON
        println "params : "+params
        println ">>>>"+data?.barCode
        def node=Node.findByBarcode(data?.barCode)
        println("Found node :" + node)
        println("Node name : " + node?.name)

        def response = [nodeName : node?.name]
        render  response as JSON
    }
    
    def checkBarcode() {
          def status=''
          def data = request.JSON
          def obj=Node.findByBarcode(data.barcode)
          if(obj)
          {
              status='true'
          }
          else{
              status='false'
          }
         def response = [status : status]
        render  response as JSON
        
    }
}

    

package com.kumulus.controllers.workflow

import com.kumulus.domain.*
import grails.converters.JSON

class CustomerController {
    
    def exportService
    def accessService
    def permissionsService
    def workflowService
    def captureService
    
    def index() {
        redirect(controller: "home")
    }
    
    def home() {
        def projectList = Project.findAllByCompany(permissionsService.getCompany().name, [sort: "created", order: "asc"])
        render(view:"home", model:[pageTitle: "Home", projectList: projectList,userId: permissionsService.getUsername()])    
    }
    
    def download() {
        def project = Project?.findById(params?.id)
        response.contentType = grailsApplication.config.grails.mime.types['csv']
        response.setHeader("Content-disposition", "attachment; filename=extract")
        def export = accessService.getCSV(project)
        if(export) exportService.export('csv', response.outputStream, export.ledger, export.fields, export.labels, [:], [:])
    }
    
    def collect() { 
       def project = Project.findById(params?.id)
        if(permissionsService.checkPermissions(project)) {
            def nodeTypes = NodeType.findAll {
                isContainer==true
            }
            render view: "collect", model:[project: project, nodeTypes: nodeTypes]
        }
    }
    
    def orderMaterials() {
        def products =  Product.getAll()
        render view: "orderMaterials", model: [products: products]
    }
    
    def createShipment() {       
        params.productId.eachWithIndex{prod, i ->
                def shipItemObj=new ShipmentItem()
                shipItemObj.type=ShipmentItem.TYPE_PRODUCT
                shipItemObj.itemId=Long.parseLong(prod.toString())
                shipItemObj.delivery=ShipmentItem.DELIVERY_DROP_OFF
                shipItemObj.quantity=Long.parseLong(params.Quantity[i])
                shipItemObj.shipment=Shipment.findById(Integer.parseInt(params.shipmentId))
                shipItemObj.save(flush:true,failOnError:true)           
          }
          redirect controller : "shipment" , action : "view" ,params:[id: params.shipmentId]
    }
       
    def view() {
        def project=  Project.findById(Integer.parseInt(params.id))
        render view:"view" , model:[project:project]
    }
    
    def update() {
        def project = Project.get(params?.id)
        if(project && permissionsService.checkPermissions(project) && params?.projectName && params?.ClientName)  {
            captureService.updateProject(project, params.projectName.toString(), params.ClientName.toString(), params?.comment?.toString())
        }
        redirect action: "view", id: params?.id
    }
    
    def create() {
        render view: "create", model:[project: new Project()]
    }
    
    def save() {
        if(params?.projectName && params?.ClientName)  {
            def project = captureService.insertProject(params.projectName, 
                                                            params.ClientName, 
                                                            params?.comment, 
                                                            permissionsService.getCompany().name, 
                                                            permissionsService.getUsername())
        }        
        redirect action: "home"
    }
    
    def delete() {
        def project = Project.findById(params?.id)
        if(permissionsService.checkPermissions(project) && project.status == "A") project.delete()
        redirec action: "home"
    }
    
    def close() {
        def project = Project.findById(params?.id)
        if(permissionsService.checkPermissions(project)) {
            project.status = Project.STATUS_CLOSED
            project.save()
        }
        redirect action: "home"
    }
    
    
    
}

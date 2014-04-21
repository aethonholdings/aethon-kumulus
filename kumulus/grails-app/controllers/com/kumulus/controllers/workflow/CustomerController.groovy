package com.kumulus.controllers.workflow

import org.compass.core.engine.SearchEngineQueryParseException
import com.kumulus.domain.*

class CustomerController {
    
    def exportService
    def accessService
    def permissionsService
    def workflowService
    def searchableService
    def captureService
    
    static String WILDCARD = "*"
    
    def index() {
        redirect(controller: "home")
    }
    
    
    def home() {
        def projectList = Project.findAll {
            company == permissionsService.getCompany()?.name
            status == Project.STATUS_ACTIVE
        }
        def shipmentList=Shipment.findAll()
        def userTasks = workflowService.getTaskQueues(permissionsService.getUsername())
        def backOfficeTasks = workflowService.getTaskQueues(null)
        render(view:"home", model:[pageTitle: "Home", projectList: projectList,shipmentList:shipmentList, userTasks: userTasks, backOfficeTasks: backOfficeTasks, userId: permissionsService.getUsername()])    
    }
    
    def search() { 
        def searchResult 
        if (!params.q?.trim()) {
            return [:]
        }
        try {
               String searchTerm = WILDCARD + params.q + WILDCARD
               searchResult = searchableService.search(searchTerm, params)
               return [searchResult: searchResult]
        } catch (SearchEngineQueryParseException ex) {
               return [parseException: true]
        }
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
            render view:"collect", model:[project: project, nodeTypes: nodeTypes]
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
          redirect controller :"shipment" , action :"view" ,params:[id: params.shipmentId]
    }
       
    def viewProject() {
        def project=  Project.findById(Integer.parseInt(params.id))
        render view:"viewProject" , model:[project:project]
    }
    
    def updateProject() {
        def project = Project.get(params?.id)
        if(project && permissionsService.checkPermissions(project) && params?.projectName && params?.ClientName)  {
            captureService.updateProject(project, params.projectName.toString(), params.ClientName.toString(), params?.comment?.toString())
        }
        redirect action: "viewProject", id: params?.id
    }
}

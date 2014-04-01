package com.kumulus.controllers

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import com.kumulus.domain.*
import java.text.SimpleDateFormat
import grails.converters.*


class ShipmentController {
    def permissionsService
    def logisticsService
    
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Shipment.list(params), model:[shipmentInstanceCount: Shipment.count()]
    }

    def show(Shipment shipmentInstance) {
        respond shipmentInstance
    }
    
    @Transactional
    def save(Shipment shipmentInstance) {
        if (shipmentInstance == null) {
            notFound()
            return
        }

        if (shipmentInstance.hasErrors()) {
            respond shipmentInstance.errors, view:'create'
            return
        }

        shipmentInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'shipmentInstance.label', default: 'Shipment'), shipmentInstance.id])
                redirect shipmentInstance
            }
            '*' { respond shipmentInstance, [status: CREATED] }
        }
    }

    def edit(Shipment shipmentInstance) {
        respond shipmentInstance
    }

    @Transactional
    def update(Shipment shipmentInstance) {
        if (shipmentInstance == null) {
            notFound()
            return
        }

        if (shipmentInstance.hasErrors()) {
            respond shipmentInstance.errors, view:'edit'
            return
        }

        shipmentInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Shipment.label', default: 'Shipment'), shipmentInstance.id])
                redirect shipmentInstance
            }
            '*'{ respond shipmentInstance, [status: OK] }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'shipmentInstance.label', default: 'Shipment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
    def create(){
       
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        if(params.scheduleDate){
            def newObj= new Shipment()
            newObj.fromCompany=permissionsService.getCompany()
            newObj.toCompany="kumulus"
            newObj.scheduled=formatter.parse(params.scheduleDate)
            //            newObj.started=formatter.parse(params.startDate)
            //            newObj.finished=formatter.parse(params.finishDate)
            newObj.notes=params.notes
            newObj.save(flush:true)
            if(params["Save and Create"]){
                redirect(action: "view" ,params:[id:newObj.id])
            }
            else{
                redirect(controller :"home", action: "index") 
            }
        
      
        }
 
        
    }
    
    def view() {

        def productList=[],nodeList=[]
        def shipmentObj=Shipment.findAllById(params.id)
        shipmentObj.shipmentItems[0].each{ it ->
            if(it.type==1){  
                nodeList<<[
                    id:it.id,
                    nodeObj: Node.findById(it.itemId),
                    quantity: it.quantity,
                    delivery:it.delivery
                ]
            }
            else{
                productList<<[
                    productObj: Product.findById(it.itemId),
                    quantity: it.quantity,
                    delivery:it.delivery
                ]
            }
      
        }

        [nodeList:nodeList,productList:productList,shipmentObj:shipmentObj]
    
    }
    
    def delete() {
        def obj=Shipment.findById(Integer.parseInt(params.id))
        obj.delete(flush:true)
        redirect controller :"home", action: "index" 
    }
    
    def addNodes() {
        def data = request.JSON
        def status=[done: true]
        
        def shipment = Shipment.findById(data?.shipmentId)
        if(shipment && data?.nodeIds) {
            data.nodeIds.each {
                def node = Node.findById(it)
                if(permissionsService.checkPermissions(node)) status.done = status.done && logisticsService.shipNode(node, shipment)
            }
        }
        render status as JSON
    }
    
    def removeNodes(){
        
        def data = request.JSON
        def status = [done: true]

        if(data?.shipmentItemIds) {
            data.shipmentItemIds.each {
                def node = Node.findById(ShipmentItem.findById(it)?.itemId)
                if(node && permissionsService.checkPermissions(node)) status.done = status.done && logisticsService.unshipNode(node)
            }
        }
        render status as JSON

    }
    
}

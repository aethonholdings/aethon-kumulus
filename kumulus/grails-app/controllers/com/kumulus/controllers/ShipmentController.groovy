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
    
    def create(){
       
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        if(params.scheduleDate){
            def newObj= new Shipment()
            newObj.fromCompany=permissionsService.getCompany()?.name
            newObj.toCompany="kumulus"
            newObj.scheduled=formatter.parse(params.scheduleDate)
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
    
    def remove() {

        def shipment=Shipment.findById(params?.id)
        println(params)
        if(shipment) {
            shipment.shipmentItems.each { shipmentItem ->
                def node = Node.findById(shipmentItem.itemId)
                if(node && permissionsService.checkPermissions(node)) logisticsService.unshipNode(node)
            }
            shipment.delete(flush:true, failOnError:true)
        }
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

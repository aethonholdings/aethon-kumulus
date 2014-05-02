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
        if(shipment) {
            shipment.shipmentItems.each { shipmentItem ->
                def node = Node.findById(shipmentItem.itemId)
                if(node && permissionsService.checkPermissions(node)) logisticsService.unshipNode(node)
            }
            shipment.delete(flush:true, failOnError:true)
        }
        redirect controller :"home", action: "index" 
        
    }
    
}

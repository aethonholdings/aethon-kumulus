package com.kumulus.services

import com.kumulus.domain.*
import grails.transaction.Transactional
import org.apache.commons.lang.RandomStringUtils
import org.krysalis.barcode4j.impl.code39.Code39Bean


@Transactional
class LogisticsService {
    
    def permissionsService
    
    def generateBarcode() {
        String literal = System.currentTimeMillis()
        literal += RandomStringUtils.random(2, true, true).toUpperCase()
        literal = "AE" + literal
        def barcode = new Barcode(
            text: literal,
            printed: false,
            used: false
        )
        barcode.save()
        return(barcode)
    }
    
    def pickup(Node node, boolean pickupFlag){
        
        // check node exists
        if(node) {
            // flag this node as ready to pick up
            if(pickupFlag) node.state = Node.STATE_FLAGGED_TO_SHIP else node.state = Node.STATE_CLIENT_OPEN                
            node.save()
            
            // flag all children as ready to pick up
            Node.findAllByParent(node).each { 
                pickup(it, pickupFlag)
            }
        }
        return(node)
    }
    
    def openNode(Node node) {
        
        // check node exists
        if(node) {
            // open this node
            node.status= Node.STATUS_OPEN
            node.save()
            
            // seal all children
            Node.findAllByParent(node).each { 
                openNode(it)
            }
        }
        return(node)
    }
    
    def shipNode(Node node, Shipment shipment) {
        
        if (node && shipment && (node.state == Node.STATE_CLIENT_SEALED || node.state == Node.STATE_IN_STORAGE)) {
        
            // create a shipment item and update the node state
            def shipItemObj = new ShipmentItem()
            shipItemObj.type = ShipmentItem.TYPE_NODE
            shipItemObj.itemId = node.id
            shipItemObj.quantity = 1
            shipItemObj.shipment = shipment
            if(node.state == Node.STATE_CLIENT_SEALED) {
                shipItemObj.delivery = ShipmentItem.DELIVERY_PICK_UP 
                changeNodeState(node, shipment, Node.STATE_FLAGGED_TO_SHIP)
            } else { 
                shipItemObj.delivery = ShipmentItem.DELIVERY_DROP_OFF
                changeNodeState(node, shipment, Node.STATE_FLAGGED_TO_FETCH)
            }
            
            shipItemObj.save(flush:true,failOnError:true) 
            return(true)
            
        }
        return(false)
           
    }
    
    def unshipNode(Node node) {
        
        if (node && (node.state == Node.STATE_FLAGGED_TO_SHIP || node.state == Node.STATE_FLAGGED_TO_FETCH)) {
        
            def shipmentItem = ShipmentItem.findByItemId(node.id)
            def shipment = shipmentItem.shipment
            shipment.shipmentItems.remove(shipmentItem)
            shipmentItem.delete(flush:true, failOnError:true) 

            if(node.state == Node.STATE_FLAGGED_TO_SHIP) {
                changeNodeState(node, null, Node.STATE_CLIENT_SEALED)
            } else { 
                changeNodeState(node, null, Node.STATE_IN_STORAGE)
            }
            return(true)
            
        }
        return(false)
           
    }
    
    private def changeNodeState(Node node, Shipment shipment, int state) {
           
        node.state = state
        node.shipment = shipment
        node.save(flush:true,failOnError:true)
        
        // recursively iterate through all children of the node to set the shipment state
        def children = Node.findAllByParent(node)
        children.each {
            changeNodeState(it, shipment, state)
        }
        return(node)
    }
}

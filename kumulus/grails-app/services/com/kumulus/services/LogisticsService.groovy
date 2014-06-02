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
    
    boolean pickup(Node node, boolean pickupFlag){
        
        // check node exists
        if(node) {
            if(pickupFlag) {
                node.state = Node.STATE_FLAGGED_TO_SHIP 
            } else {
                if(node.parent?.state != Node.STATE_FLAGGED_TO_SHIP ) {
                    node.state = Node.STATE_CLIENT_OPEN
                } else { 
                    return(false)
                }
            }
            node.save()
            // flag all children iteratively
            Node.findAllByParent(node).each { 
                pickup(it, pickupFlag)
            }
            return(true)
        }
        return(false)
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
        
            
            return(true)
            
        }
        return(false)
           
    }
    
    def unshipNode(Node node) {
        
        if (node && (node.state == Node.STATE_FLAGGED_TO_SHIP || node.state == Node.STATE_FLAGGED_TO_FETCH)) {
        
           
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
    
    def getFlaggedNodes() {
        def nodes = Node.findAll {
            state == Node.STATE_FLAGGED_TO_SHIP || state == Node.STATE_FLAGGED_TO_FETCH 
            shipment == null
        }
        return(nodes.groupBy({it.project.client}))
    }
    
    def getSchedule() {
        return(Shipment.getAll().groupBy({it.scheduled}))
    }
    
    def createShipment(Company company, Date date) {
        if(company && date) {
            Shipment shipment = new Shipment(company: company, scheduled: date)
            getFlaggedNodes().get(company).each { node ->
                shipment.addToNodes(node)
            }
            shipment.save()
        }
    }
    
    def deleteShipment(Shipment shipment) {
        if(shipment) {
            while(shipment.nodes.size() > 0) {
                shipment.removeFromNodes(shipment.nodes[0])
            }
            shipment.delete(flush:true, failOnError:true)
        }

    }
    
}

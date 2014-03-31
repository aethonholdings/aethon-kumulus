package com.kumulus.services

import com.kumulus.domain.*
import grails.transaction.Transactional
import org.apache.commons.lang.RandomStringUtils
import org.krysalis.barcode4j.impl.code39.Code39Bean


@Transactional
class LogisticsService {
    
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
    
    def sealNode(Node node){
        
        // check node exists
        if(node) {
            // seal this node
            node.status= Node.STATUS_CLOSED
            node.save()
            
            // seal all children
            Node.findAllByParent(node).each { 
                sealNode(it)
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
    
}

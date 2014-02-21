package com.kumulus.services

import grails.transaction.Transactional

@Transactional
class LogisticsService {

    def generateBarcodeSheet(company) {
        
    }
    
    def generateBarcode() {
        String literal = System.currentTimeMillis()
        literal += RandomStringUtils.random(2, true, true).toUpperCase()
        literal = "AE" + literal
        return(literal)
    }
}

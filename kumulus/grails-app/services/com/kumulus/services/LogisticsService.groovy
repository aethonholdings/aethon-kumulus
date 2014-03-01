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
}

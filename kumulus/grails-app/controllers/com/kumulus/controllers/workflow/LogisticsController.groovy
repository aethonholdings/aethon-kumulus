package com.kumulus.controllers.workflow

import com.kumulus.domain.*

class LogisticsController {

    def logisticsService
    def permissionsService
    
    def generateBarcodeSheet() {     
        def barcodes = []
        for(i in 0..29) { barcodes.add(logisticsService.generateBarcode()) }
        render view: "generateBarcodeSheet", model: [barcodes: barcodes]
    }
    
    def barcode() {
        renderBarcodePng("code39Generator", Barcode.findById(params?.id).text)
    }
    
}       

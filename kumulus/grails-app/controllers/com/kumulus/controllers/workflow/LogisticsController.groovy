
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
    
    def orderMaterials(){
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
    
}       


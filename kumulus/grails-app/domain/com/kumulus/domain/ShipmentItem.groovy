package com.kumulus.domain

class ShipmentItem {
    
    static final byte TYPE_NODE = 1
    static final byte TYPE_PRODUCT = 2
    
    byte type
    long itemId
    Long quantity
    
    static belongsTo = [shipment: Shipment]
    
    static constraints = {

    }
}

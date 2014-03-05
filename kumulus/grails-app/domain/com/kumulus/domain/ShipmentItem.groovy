package com.kumulus.domain

class ShipmentItem {
    
    static final byte TYPE_NODE = 1
    static final byte TYPE_PRODUCT = 2
    
    static final byte DELIVERY_DROP_OFF = 1
    static final byte DELIVERY_PICK_UP = 2
    
    byte type
    long itemId
    Byte delivery
    Long quantity
    
    static belongsTo = [shipment: Shipment]
    
    static constraints = {

    }
}

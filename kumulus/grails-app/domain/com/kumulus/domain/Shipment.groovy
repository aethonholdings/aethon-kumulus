package com.kumulus.domain

class Shipment {

    String toCompany
    String fromCompany    
    Date scheduled
    Date started
    Date finished
    String notes

    static hasMany = [shipmentItems: ShipmentItem]
    
    static constraints = {
        notes maxSize: 500
        started nullable: true
        finished nullable: true
        notes nullable: true
        shipmentItems nullable: true
    }
}

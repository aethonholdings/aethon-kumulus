package com.kumulus.domain

class Shipment {

    Company company
    Date scheduled
    Date started
    Date finished
    String notes

    static hasMany = [nodes: Node]
    
    static constraints = {
        notes maxSize: 500
        started nullable: true
        finished nullable: true
        notes nullable: true
    }
}

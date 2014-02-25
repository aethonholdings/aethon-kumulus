package com.kumulus.domain

class Shipment {

    String toCompany
    String fromCompany    
    Date scheduled
    Date started
    Date finished
    String notes

    static hasMany = [nodes: Node, products: Product]
    
    static constraints = {
        notes maxSize: 500
        started nullable: true
        finished nullable: true
        notes nullable: true
        nodes nullable: true
        products nullable: true
    }
}

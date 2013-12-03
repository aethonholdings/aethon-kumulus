package com.kumulus.domain

class Company {

    String name
    String street
    String buildingNumber
    String unitNumber
    String postcode
    
    static hasMany = [users: User]
    
    static constraints = {
        name blank: false, unique: true
    }
}

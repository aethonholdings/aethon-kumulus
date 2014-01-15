package com.kumulus.domain

class Currency {

    String fullName
    String shortName
    
    static constraints = {
        fullName nullable: false
        shortName nullable: false
    }
}

package com.kumulus.domain

class Task {

    String userId
    Date created
    String type

    static hasMany = [nodes: Nodes]
    
    static constraints = {
        created nullable: false
        userId nullable: false
        type nullable: false
    }
}

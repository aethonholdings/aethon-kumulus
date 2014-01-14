package com.kumulus.domain

class Task {

    String userId
    Date created
    long status
    String batchInstanceID
    String batchInstanceUrlId
    
    static hasMany= [nodes: Nodes]
    
    static constraints = {
        created nullable: false
        userId nullable: false
        status nullable: false
    }
}

package com.kumulus.domain

class Task {

    String type
    String status
    Date created
    Date started
    Date ended
    
    static hasMany = [nodes: Nodes]
    
    static belongsTo = [owner: User]
    
    static constraints = {
        created nullable: false
        started nullable: true
        ended nullable: true
        owner nullable: false
        type nullable: false
        status nullable: false
    }
}

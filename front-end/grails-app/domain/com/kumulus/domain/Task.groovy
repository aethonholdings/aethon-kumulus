package com.kumulus.domain

class Task {

    String type
    String status
    Date start
    Date end
    
    static hasMany = [nodes: Nodes]
    
    static belongsTo = [owner: User]
    
    static constraints = {
        start nullable: false
        end nullable: true
        owner nullable: false
        type nullable: false
        status nullable: false
    }
}

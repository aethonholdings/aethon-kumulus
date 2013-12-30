package com.kumulus.domain

class Task {

    String userId
    String type
    Date created

    static hasMany = [nodes: Nodes]
    
    static constraints = {
        created nullable: false
        started nullable: true
        ended nullable: true
        ownerLDAPUid nullable: false
        type nullable: false
        status nullable: false
    }
}

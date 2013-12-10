package com.kumulus.domain

class Task {

    String ownerLDAPUid
    String type
    String status
    Date created
    Date started
    Date ended

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

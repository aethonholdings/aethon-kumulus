package com.kumulus.domain

class Project {

    String clientLDAPId
    String projectName
    String status

    static hasMany = [nodes: Nodes, lineItems: LineItem]

    static mapping = {
        id column: "project_id"
        version false
    }

    static constraints = {
        projectName nullable: true, maxSize: 50, unique: true
        status nullable: true, maxSize: 10
        clientLDAPId nullable: false, maxSize: 50
    }
}

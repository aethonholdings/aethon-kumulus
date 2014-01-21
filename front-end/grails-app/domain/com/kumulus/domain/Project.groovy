package com.kumulus.domain

class Project {

    String company
    Company client
    String projectName
    String status
    String comment
    String literal

    static hasMany = [nodes: Nodes, lineItems: LineItem]

    static mapping = {
        id column: "project_id"
        version false
    }

    static constraints = {
        projectName nullable: true, maxSize: 50, unique: true
        status nullable: true, maxSize: 10
        company nullable: false, maxSize: 50
        comment nullable: true
        literal nullable: false
    }
        
}

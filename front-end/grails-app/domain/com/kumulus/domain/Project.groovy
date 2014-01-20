package com.kumulus.domain

class Project {

    String company
    String client
    String projectName
    String status
    String comment

    static hasMany = [nodes: Nodes, lineItems: LineItem]

    static mapping = {
        id column: "project_id"
        version false
    }

    static constraints = {
        projectName nullable: true, maxSize: 50, unique: true
        status nullable: true, maxSize: 10
        company nullable: false, maxSize: 50
    }
        
}

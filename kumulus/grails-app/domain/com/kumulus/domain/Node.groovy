package com.kumulus.domain

class Node {

    static searchable = [only: ['name', 'barcode', 'comment', 'createDateTime']]
    
    static final String STATUS_OPEN = "0"
    static final String STATUS_CLOSED = "1"
    
    String name
    String barcode
    String comment
    String internalComment
    String status
    String creatorId
    String lastUpdateId
    Date createDatetime
    Date lastUpdateDatetime
    Project project
    Node parent 
    NodeType type

    static mapping = {
        id column: "node_id"
        parent column: "parent_node_id"
        version false
    }

    static constraints = {
        name nullable: true, maxSize: 45
        barcode nullable: true, maxSize: 45
        comment nullable: true, maxSize: 300
        internalComment nullable: true, maxSize: 300
        status maxSize: 2
        createDatetime nullable: true
        lastUpdateDatetime nullable: true
    }
    
    String owner() {
        return(project.company)
    }
    
}

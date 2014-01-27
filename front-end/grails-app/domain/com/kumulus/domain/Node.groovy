package com.kumulus.domain

class Node {

    String name
    String type
    String barcode
    String comment
    String internalComment
    String status
    String creatorId
    String lastUpdateId
    Date createDatetime
    Date lastUpdateDatetime

    static belongsTo = [project: Project, parent: Node]

    static mapping = {
        id column: "node_id"
        parent column: "parent_node_id"
        version false
    }

    static constraints = {
        name nullable: true, maxSize: 45
        type nullable: true, maxSize: 45
        barcode nullable: true, maxSize: 45
        comment nullable: true, maxSize: 200
        internalComment nullable: true, maxSize: 200
        status nullable: true, maxSize: 10
        createDatetime nullable: true
        lastUpdateDatetime nullable: true
    }
}

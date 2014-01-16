package com.kumulus.domain

class Nodes {

    String name
    String type
    String barcode
    String comment
    String internalComment
    String status
    String hierarchy
    String thumbnailImageName
    String actualImageName
    String creatorLDAPUid
    String creatorId
    String lastUpdateId
    Date createDatetime
    Date lastUpdateDatetime
    Integer documentSequenceNumber
    Boolean uploaded

    static belongsTo = [project: Project, parent: Nodes]
    static hasMany = [documents: Document]

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
        hierarchy nullable: true, maxSize: 200
        thumbnailImageName nullable: true, maxSize: 1000
        actualImageName nullable: true, maxSize: 1000
        creatorLDAPUid nullable: true, maxSize: 45
        createDatetime nullable: true
        lastUpdateDatetime nullable: true
        documentSequenceNumber nullable: true
        uploaded nullable: true
    }
}

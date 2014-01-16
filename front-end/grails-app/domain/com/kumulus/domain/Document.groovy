package com.kumulus.domain

class Document {

    String type
    String identifier
    String company
    Date date
    
    static belongsTo = [node: Nodes]
    static hasMany = [images: Image, thumbnails: Image, lineItems: LineItem]
    
    static mapping = {
        version false
    }
    
    static constraints = {
        type nullable: false, maxSize: 10
        identifier nullable: true, maxSize: 30
        company nullable: true
    }
}

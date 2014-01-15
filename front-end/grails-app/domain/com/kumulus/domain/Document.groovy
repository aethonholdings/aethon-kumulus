package com.kumulus.domain

class Document {

    String type
    String identifier
    String company
    Date date
    Image thumbnail
    
    static belongsTo = [node: Node]
    static hasMany = [images: Image]
    
    static mapping = {
        version false
    }
    
    static constraints = {
        type nullable: false, maxSize: 10
        identifier nullable: true, maxSize: 30
        company nullable: true
    }
}

package com.kumulus.domain

class Document {

    String type
    Company company
    Date date
    File file
    String literal
    String identifier
    
    static belongsTo = [node: Nodes]
    static hasMany = [pages: Page]
    
    static mapping = {
        version false
    }
    
    static constraints = {
        type nullable: false, maxSize: 10
        identifier nullable: true, maxSize: 30
        company nullable: true
        date nullable: true
        literal nullable: false
    }
}

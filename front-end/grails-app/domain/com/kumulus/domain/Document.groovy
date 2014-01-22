package com.kumulus.domain

import com.lucastex.grails.fileuploader.UFile

class Document {

    static final int EDITABLE = 1
    static final int FINAL = 2
    
    String type
    Company company
    Date date
    String literal
    String identifier
    Byte status
    UFile file
    
    static hasMany= [pages: Page]
    
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

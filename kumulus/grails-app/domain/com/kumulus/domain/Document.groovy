package com.kumulus.domain

import com.lucastex.grails.fileuploader.UFile

class Document {

    static searchable = [only: ['date', 'company', 'identifier', 'text']]
    
    static final int EDITABLE = 1
    static final int FINAL = 2
    
    Company company
    Date date
    String literal
    String identifier
    Byte status
    UFile file
    String text
    String ocrTask
    
    static hasMany = [pages: Page, task: Task]
    static belongsTo = [project: Project, type: DocumentType]
    
    static mapping = {
        text sqlType: "longtext"
    }
    
    static constraints = {  
        identifier nullable: true, maxSize: 30
        company nullable: true
        date nullable: true
        literal nullable: false
        file nullable: true
        pages nullable: true
        text nullable: true
        ocrTask nullable: true
    }
}

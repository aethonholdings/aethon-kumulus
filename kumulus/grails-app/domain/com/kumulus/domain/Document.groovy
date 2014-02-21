package com.kumulus.domain

import com.lucastex.grails.fileuploader.UFile

class Document {

    static searchable = [only: ['date', 'company', 'identifier', 'text']]
    
    static final int EDITABLE = 1
    static final int FINAL = 2
    static final int SUBMITTED = 3
    static final int SEARCHABLE = 4
    static final int EXTRACTED = 5
    static final int SUBMISSION_ERROR = -1
    
    Company company
    Date date
    String literal
    String identifier
    Byte status
    UFile file
    String text
    String ocrTask
    Project project
    DocumentType type
    Boolean deleted
    
    static hasMany = [pages: Page]
    
    static mapping = {
        text sqlType: "longtext"
        status index: "idx_document_status"
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
    
    String owner() {
        return(project.company)
    }
    
}

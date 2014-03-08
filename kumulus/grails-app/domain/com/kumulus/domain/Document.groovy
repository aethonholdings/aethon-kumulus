package com.kumulus.domain

import com.lucastex.grails.fileuploader.UFile

class Document {

    static searchable = [only: ['date','literal', 'company', 'identifier', 'text']]
    
    
    static final int STATUS_IMPORTED = 1
    static final int STATUS_BUILT  = 2
    static final int STATUS_SUBMITTED = 3
    static final int STATUS_SEARCHABLE = 4
    static final int STATUS_EXTRACTED = 5
    static final int STATUS_PROCESSED = 6
    static final int STATUS_FINAL = 7
    static final int STATUS_SUBMISSION_ERROR = -1
    
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
        deleted index: "idx_document_status"
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
    
    String status() {
        switch(status){
            case STATUS_IMPORTED:
                return("Scan imported")
            case STATUS_BUILT:
                return("Document built")
            case STATUS_SUBMITTED:
                return("Submitted for processing")
            case STATUS_SEARCHABLE:
                return("Submitted for processing")
            case STATUS_PROCESSED:
                return("Ready for review")   
            case STATUS_FINAL:
                return("Completed")          
        }         
    }
    
}

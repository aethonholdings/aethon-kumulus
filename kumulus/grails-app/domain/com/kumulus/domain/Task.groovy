package com.kumulus.domain

class Task {
    
    // type enum constants
    static final String BUILD_DOCUMENT= "BUILD"
    static final String OCR_DOCUMENT= "OCR"
    static final String PROCESS_DOCUMENT= "PROCESS"
    static final String REVIEW_DOCUMENT= "REVIEW"

    Project project
    Document document    
    String userId
    String createdBy
    String type
    Date created
    Date started
    Date completed
        
    static constraints = {
        document nullable: true
        started nullable: true
        completed nullable: true
        userId nullable: true
        type maxSize: 10
    }
    
}

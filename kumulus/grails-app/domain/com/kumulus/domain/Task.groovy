package com.kumulus.domain

class Task {
    
    // type enum constants
    static final String TYPE_BUILD= "BUILD"
    static final String TYPE_OCR= "OCR"
    static final String TYPE_PROCESS= "PROCESS"
    static final String TYPE_REVIEW= "REVIEW"
    static final String TYPE_VALIDATE= "VALIDATE"

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

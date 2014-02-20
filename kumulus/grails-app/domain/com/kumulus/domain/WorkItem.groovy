package com.kumulus.domain

class WorkItem {
    
    // type enum constants
    static final byte BUILD_DOCUMENT = 1
    static final byte OCR_DOCUMENT = 2
    static final byte REVIEW_DOCUMENT = 3
    
    String userId
    byte type
    Date created
    Date completed
    
    static belongsTo = [project: Project]
    static hasMany = [tasks: Task]
    
    static constraints = {
        completed nullable: true        
    }
    
}

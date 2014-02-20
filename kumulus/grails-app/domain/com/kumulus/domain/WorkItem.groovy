package com.kumulus.domain

class WorkItem {

    // status enum constants
    static final long ERROR = -1
    static final long READY_FOR_UPLOAD = 1
    static final long READY_FOR_BATCH_INSTANCE = 2
    static final long CREATED = 3
    static final long READY_FOR_REVIEW = 4
    static final long READY_FOR_VALIDATION = 5
    static final long FINISHED = 6
    
    // type enum constants
    static final byte BUILD_DOCUMENT = 1
    static final byte OCR_DOCUMENT = 2
    static final byte REVIEW_DOCUMENT = 3
    
    String userId
    Date created
    Date completed
    byte type
    long status
    
    static belongsTo = [project: Project]
    static hasMany = [tasks: Task]
    
    static constraints = {
        completed nullable: true
    }
    
    Boolean isCompleted() {
        if(completed==null) return(false)
        return(true)
    }
}

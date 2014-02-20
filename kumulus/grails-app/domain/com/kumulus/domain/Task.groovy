package com.kumulus.domain

class Task {
    
    // status enum constants
    static final long ERROR = -1
    static final long READY_FOR_UPLOAD = 1
    static final long READY_FOR_BATCH_INSTANCE = 2
    static final long CREATED = 3
    static final long READY_FOR_REVIEW = 4
    static final long READY_FOR_VALIDATION = 5
    static final long FINISHED = 6
    
    Document document
    long status
    Date created
    Date completed
    
    static belongsTo = [workItem: WorkItem]
    
    static constraints = {
        completed nullable: true
    }
    
}

package com.kumulus.domain

class Task {
    
    Date created
    Date completed
    Document document
    
    static belongsTo = [workItem: WorkItem]
    
    static constraints = {
        completed nullable: true
    }
    
    Boolean isCompleted() {
        if(completed==null) return(false)
        return(true)
    }
}

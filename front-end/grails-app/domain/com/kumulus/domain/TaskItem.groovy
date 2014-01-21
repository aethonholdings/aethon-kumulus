package com.kumulus.domain

class TaskItem {

    long sequence
    Page page
    Task task
    
    static belongsTo = [task: Task]

    static constraints = {
        
    }
}

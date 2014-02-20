package com.kumulus.domain

class WorkItem {

    String userId
    Date created
    Date completed
    
    static belongsTo = [project: Project]
    static hasMany = [tasks: Task]
    
    static constraints = {
        completed nullable: true
    }
}

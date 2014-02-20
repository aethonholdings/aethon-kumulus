package com.kumulus.domain

class WorkItem {

    Date created
    String userId
    
    static belongsTo = [project: Project]
    static hasMany = [tasks: Task]
    
    static constraints = {
    }
}

package com.kumulus.domain

class Task {

    User owner
    String type
    Date start
    Date end
    
    static hasMany = [nodes: Nodes]
    
    static belongsTo = [user: User]
    
    static constraints = {
        start blank: false
        owner blank: false
        type blank: false 
    }
}

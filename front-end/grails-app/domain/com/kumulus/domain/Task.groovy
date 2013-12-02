package com.kumulus.domain

class Task {

    User owner
    
    static hasMany = [nodes: Nodes]
    
    static constraints = {
    }
}

package com.kumulus.domain

class File {

    String path
    String name
    String type
    
    static belongsTo = [project: Project]
    
    static constraints = {
        path nullable: false
        name nullable: false
        type nullable: false, maxSize: 3
        project nullable: false
    }
}

package com.kumulus.domain

class File {

    String path
    String name
    String type
    
    static constraints = {
        path nullable: false
        name nullable: false
        type nullable: false, maxSize: 3
    }
}

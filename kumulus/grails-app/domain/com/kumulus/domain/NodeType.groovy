package com.kumulus.domain

class NodeType {

    String name
    String code
    String description
    String imagePath
    Boolean isContainer
    
    static constraints = {
        code maxSize: 2
    }
}

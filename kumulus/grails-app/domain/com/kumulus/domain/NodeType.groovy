package com.kumulus.domain

class NodeType {

    String name
    String code
    String description
    String imagePath
    Boolean isContainer
    Boolean storeable
    
    static constraints = {
        code maxSize: 2
    }
}

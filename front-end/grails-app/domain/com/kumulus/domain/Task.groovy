package com.kumulus.domain

class Task {

    String userId
    Date created
    Document document
    long status
    String batchInstanceID
    String batchInstanceUrlId
    String literal
    
    static constraints = {
        created nullable: false
        userId nullable: false
        status nullable: false
    }
}

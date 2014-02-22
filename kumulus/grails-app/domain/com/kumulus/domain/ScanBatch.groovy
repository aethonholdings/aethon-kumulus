package com.kumulus.domain

class ScanBatch {

    String userId
    Date timestamp
    Project project
    
    static hasMany = [pages: Page]
    
    static constraints = {
        
    }
    
    String owner() {
        return(project.company)
    }
}

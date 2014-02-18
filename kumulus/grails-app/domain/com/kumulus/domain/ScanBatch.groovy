package com.kumulus.domain

class ScanBatch {

    String userId
    Date timestamp
    
    static belongsTo = [project: Project]
    static hasMany = [pages: Page]
    
    static constraints = {
        
    }
    
    String owner() {
        return(project.company)
    }
}

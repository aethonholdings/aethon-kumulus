package com.kumulus.domain

import com.lucastex.grails.fileuploader.UFile

class Image {

    long height
    long width
    UFile file
    boolean thumbnail
    boolean compressed
    
    static belongsTo = [page: Page]
    
    static constraints = {
        
    }
    
    String owner() {
        return(page.owner())
    }
    
}

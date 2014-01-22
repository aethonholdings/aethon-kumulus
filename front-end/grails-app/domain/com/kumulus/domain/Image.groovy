package com.kumulus.domain

import com.lucastex.grails.fileuploader.UFile

class Image {

    static final int STAGED = 1
    static final int IMPORTED = 2
    static final int FINAL = 3
    
    long height
    long width
    UFile file
    boolean thumbnail
    boolean compressed
    
    static belongsTo = [page: Page]
    
    static constraints = {
        
    }
}

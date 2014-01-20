package com.kumulus.domain

class Image {

    long height
    long width
    File file
    boolean thumbnail
    boolean compressed
    
    static belongsTo = [page: Page]
    
    static constraints = {
        
    }
}

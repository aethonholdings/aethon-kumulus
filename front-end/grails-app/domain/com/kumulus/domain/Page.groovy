package com.kumulus.domain

class Page {

    Image scanImage
    Image viewImage
    Image thumbnailImage
    long number
    boolean first
    boolean last
    
    static belongsTo = [document: Document]
    
    static constraints = {
        
    }
}

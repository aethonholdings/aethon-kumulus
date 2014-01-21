package com.kumulus.domain

class Page {

    Image scanImage
    Image viewImage
    Image thumbnailImage
    long number
    boolean first
    boolean last
    Nodes node
    String literal
    
    static belongsTo = [node: Nodes, document: Document]
    static hasMany = [lineItems: LineItem]
    
    static constraints = {
        
    }
}

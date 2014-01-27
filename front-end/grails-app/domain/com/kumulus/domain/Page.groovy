package com.kumulus.domain

class Page {

    long number
    boolean first
    boolean last
    String literal
    Image scanImage
    Image viewImage
    Image thumbnailImage
    
    static belongsTo = [node: Node, document: Document, scanBatch: ScanBatch]
    static hasMany = [lineItems: LineItem]
    
    static constraints = {
        scanImage unique: true
        viewImage unique: true
        thumbnailImage unique: true
    }
}

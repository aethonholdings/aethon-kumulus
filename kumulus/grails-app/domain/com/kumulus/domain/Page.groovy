package com.kumulus.domain

class Page {

    long number
    boolean first
    boolean last
    String literal
    Image scanImage
    Image viewImage
    Image thumbnailImage
    
    static mappedBy = [scanImage: 'page', viewImage: 'page', thumbnailImage: 'page']
    static belongsTo = [node: Node, document: Document, scanBatch: ScanBatch]
    static hasMany = [lineItems: LineItem]
    
    static constraints = {

    }
    
    String owner() {
        return(node.project.company)
    }
}

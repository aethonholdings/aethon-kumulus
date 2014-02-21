package com.kumulus.domain

class Page {

    long number
    boolean first
    boolean last
    String literal
    Image scanImage
    Image viewImage
    Image thumbnailImage
    Node node
    Document document 
    ScanBatch scanBatch
    
    static mappedBy = [scanImage: 'page', viewImage: 'page', thumbnailImage: 'page']
    static hasMany = [lineItems: LineItem]
    
    static constraints = {

    }
    
    String owner() {
        return(node.project.company)
    }
}

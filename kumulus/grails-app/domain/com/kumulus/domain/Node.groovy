package com.kumulus.domain

class Node {
    
    static searchable = [only: ['name', 'barcode', 'comment', 'createDateTime']]
        
    static final int STATE_CLIENT_OPEN = 1
    static final int STATE_FLAGGED_TO_SHIP = 2
    static final int STATE_IN_TRANSIT = 3
    static final int STATE_IN_STORAGE = 4
    static final int STATE_IN_DIGITISATION_LINE = 5
    static final int STATE_FLAGGED_TO_FETCH = 6
    static final int STATE_RETURN_IN_PROGRESS = 7
    static final int STATE_CLIENT_RETURNED = 8
    
    static final String STATUS_OPEN = "0"
    static final String STATUS_CLOSED = "1"
    static final String STATUS_REOPENED = "2"
    
    static final String LOCATION_CLIENT = "My premises"
    static final String LOCATION_STORAGE = "In storage"
    
    int state
    String name
    String comment
    String internalComment
    String status
    String creatorId
    String lastUpdateId
    String location
    Date createDatetime
    Date lastUpdateDatetime
    Barcode barcode
    Project project
    Shipment shipment
    Node parent 
    NodeType type
    Page page
    
    static mapping = {
        id column: "node_id"
        parent column: "parent_node_id"
        version false
    }

    static constraints = {
        name nullable: true, maxSize: 45
        barcode nullable: true, maxSize: 45
        comment nullable: true, maxSize: 300
        internalComment nullable: true, maxSize: 300
        status maxSize: 2
        createDatetime nullable: true
        lastUpdateDatetime nullable: true
        shipment nullable: true
        page nullable: true
        location maxSize: 12
    }
    
    String owner() {
        return(project.company)
    }
    
    String status() {
        switch(status) {
            case STATUS_OPEN:
                return("Open")
            case STATUS_CLOSED:
                return("Closed")
            case STATUS_REOPENED:
                return("Reopened")
        }
    }
    
    String state() {
        switch(state) {
            case STATE_CLIENT_OPEN:
                return("At my premises, work in progress")
            case STATE_FLAGGED_TO_SHIP:
                return("Shipment requested")
            case STATE_IN_TRANSIT:
                return("In transit")
            case STATE_IN_STORAGE:
                return("In storage")
            case STATE_IN_DIGITISATION_LINE:
                return("Digitisation in progress")
            case STATE_FLAGGED_TO_FETCH:
                return("Pending shipment to my premises")
            case STATE_RETURN_IN_PROGRESS:
                return("In transit")
            case STATE_CLIENT_RETURNED:
                return("Returned to client")
        }
    }
    
}

package com.kumulus.domain

class LineItem {

    Date date
    String description
    Currency currency
    Float quantity
    Float price
    Float amount
    
    static belongsTo = [page: Page]
    
    static constraints = {
        date nullable: true
        description nullable: false
        quantity nullable: true
        price nullable: true
        currency nullable: false
        amount nullable: false
    }
    
    String owner() {
        return(page.owner())
    }
}

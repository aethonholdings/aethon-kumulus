package com.kumulus.domain

class Product {
    
    String name
    String description
    float price
    String imagePath
    
    static constraints = {
        description nullable: true
    }
}

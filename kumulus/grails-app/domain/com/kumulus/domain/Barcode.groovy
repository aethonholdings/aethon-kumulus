package com.kumulus.domain

class Barcode {

    String text
    Boolean printed
    Boolean used
    
    static constraints = {
        
    }
    
    String toString() {
        return(text)
    }
}

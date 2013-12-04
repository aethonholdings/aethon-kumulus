package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured


class ExtractionController {

    @Secured(['ROLE_VALIDATE'])
    def validate() { 
        
    }
    
    @Secured(['ROLE_CLASSIFY'])
    def classify() { 
        
    }
}

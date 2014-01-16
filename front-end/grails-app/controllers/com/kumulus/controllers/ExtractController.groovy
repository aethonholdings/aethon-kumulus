package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*

class ExtractController {

    def springSecurityService
    
    @Secured(['ROLE_EXTRACT'])
    def index() { 
        
    }
    
}


package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_VALIDATE'])
class ValidationController {
    
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
        
    def index() { 
        
    }    
}
package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
class ValidationController {
    
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
        
    def index() { 
        render 'success'
    }
}
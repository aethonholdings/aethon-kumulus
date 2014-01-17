package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*

class ReviewController {

    def springSecurityService
    
    @Secured(['ROLE_EXTRACT'])
    def index() { 
        def auth = springSecurityService.getAuthentication()
        String username = auth.getPrincipal().getUsername()
        def tasks = Task.findByUserId(username)
        render view:"index", layout:"home", model:[user: username, tasks: tasks]
    }
    
    @Secured(['ROLE_EXTRACT'])
    def tasks() {
        
    }
}

package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*


class HomeController {

    def springSecurityService
    
    @Secured(['ROLE_CLASSIFY', 'ROLE_ADMIN', 'ROLE_VIEW', 'ROLE_COLLECT', 'ROLE_EXPORT', 'ROLE_VALIDATE', 'ROLE_IMPORT'])
    
    def index() { 

//        def auth = springSecurityService.getAuthentication()
//        
//        String username = auth.getPrincipal().getUsername()
//        def tasks = Task.findByOwnerLDAPUidAndStatus(username, "PENDING")
//
//        render view:"index", layout:"main", model:[user: username, tasks: tasks]
        
    }
    
    
    @Secured(['ROLE_VALIDATE'])
    def validate() {
        
        
        // Credentials defaultcreds = new UsernamePasswordCredentials("username", "password"); 
        // client.getState().setCredentials(new AuthScope("serverName", 8080), defaultcreds); 
        // client.getParams().setAuthenticationPreemptive(true);
        
        // this is where we call the ephesoft API to log in
        
        
    }
    
    
}
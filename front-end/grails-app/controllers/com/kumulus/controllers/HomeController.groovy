package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
    
@Secured(['ROLE_CLASSIFY', 'ROLE_ADMIN', 'ROLE_VIEW', 'ROLE_COLLECT', 'ROLE_EXPORT', 'ROLE_VALIDATE', 'ROLE_IMPORT'])

class HomeController {

    def springSecurityService
    
    def index() { 
        def auth = springSecurityService.getAuthentication()
        String username = auth.getPrincipal().getUsername()
        // def tasks = Task.findByOwnerAndStatus(user, "PENDING")
        render "<br>Username: " + username
        render "<br>Authorities: " + auth.getPrincipal().getAuthorities()

        // render view:"index", layout:"main", model:[user: username, tasks: tasks]

    }
}
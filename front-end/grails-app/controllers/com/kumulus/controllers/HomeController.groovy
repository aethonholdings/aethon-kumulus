package com.kumulus.controllers

class HomeController {

    def springSecurityService
    
    def index() { 
        def auth = springSecurityService.getAuthentication()

        // def user = springSecurityService.currentUser
        // def tasks = Task.findByOwnerAndStatus(user, "PENDING")
        render "Username: " + auth.getPrincipal().getUsername()
        render "<br>Authorities: " + auth.getPrincipal().getAuthorities()

        // render view:"index", layout:"main", model:[user: user, tasks: tasks]

    }
}
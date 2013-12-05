package com.kumulus.controllers

class HomeController {

    def springSecurityService
    
    def index() { 
        def auth = springSecurityService.authentication
        // def user = springSecurityService.currentUser
        // def tasks = Task.findByOwnerAndStatus(user, "PENDING")
        render auth
        // user.authorities.each({
        //         render it
        // })
        // render view:"index", layout:"main", model:[user: user, tasks: tasks]

    }
}
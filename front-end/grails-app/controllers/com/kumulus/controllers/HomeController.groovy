package com.kumulus.controllers

import com.kumulus.domain.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_VALIDATE', 'ROLE_CLASSIFY', 'ROLE_ADMIN', 'ROLE_CAPTURE', 'ROLE_SEPARATE', 'ROLE_IMPORT'])
class HomeController {

    def springSecurityService
    
    def index() { 
        def user = springSecurityService.currentUser
        def tasks = Task.findByOwnerAndStatus(user, "PENDING")
        render view:"index", layout:"main", model:[user: user, tasks: tasks]
    }
}

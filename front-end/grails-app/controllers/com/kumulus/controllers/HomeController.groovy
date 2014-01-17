package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*

class HomeController {
    
    def springSecurityService
    
    @Secured(['ROLE_CLASSIFY', 'ROLE_ADMIN', 'ROLE_VIEW', 'ROLE_COLLECT', 'ROLE_EXPORT', 'ROLE_VALIDATE', 'ROLE_IMPORT'])
    def index() { 
        render view:"index", layout:"home"
    }
    
    @Secured(['ROLE_COLLECT'])
    def collect() { 
        def projectList = Project.findAllByStatus("A")
        render view:"collect", layout:"home", model:[projects: projectList]
    }
    
    @Secured(['ROLE_EXTRACT'])
    def extract() { 
        def projectList = Project.findAllByStatus("A")
        render view:"extract", layout:"home", model:[projects: projectList]
    }
    
    @Secured(['ROLE_REVIEW'])
    def review() { 
        def auth = springSecurityService.getAuthentication()
        String username = auth.getPrincipal().getUsername()
        def tasks = Task.findByUserId(username)
        render view:"review", layout:"home", model:[user: username, tasks: tasks]
    }
    
    @Secured(['ROLE_ACCESS'])
    def access() { 
        render view:"access", layout:"home"
    }

}
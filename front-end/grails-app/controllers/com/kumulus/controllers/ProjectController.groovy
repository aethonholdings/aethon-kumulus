package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*

class ProjectController {

    def springSecurityService

    @Secured(['ROLE_EXTRACT'])    
    def index() { 
        def projectList = Project.findAll()
        render view:"index", layout:"home", model:[projects: projectList]
    }
}

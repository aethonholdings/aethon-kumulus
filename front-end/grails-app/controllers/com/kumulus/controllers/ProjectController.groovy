package com.kumulus.controllers

import com.kumulus.domain.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_MANAGE'])    
class ProjectController {

    def userService
    
    def list() {
        def projectList = userService.getProjects()
        render view:"list", layout: "home", model:[projectList: projectList]
    }
    
    def create() {
        def project = new Project()
        render view:"edit", layout: "home", model:[project: project]
    }

}

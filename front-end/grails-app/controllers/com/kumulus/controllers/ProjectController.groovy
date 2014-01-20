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
    
    def edit() {
        def project = Project.findById(params?.id)
        if(userService.checkPermisions(project)) {
            render view:"edit", layout: "home", model:[project: project]
        }
    }
    
    def update() {
        def project = Project.findById(params?.id)
        if(userService.checkPermisions(project)) {
            project.properties = params
            project.save()
        }        
        redirect action:"list"
    }

}

package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*
import com.kumulus.services.*

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
        if(!project) project = new Project([company: userService.getCompany(), status: "A", lineItems:[], nodes:[]])
        if(userService.checkPermisions(project)) {
            project.properties = params
            project.save()
        }        
        redirect action:"list"
    }
    
    def create() {
        render view:"edit", layout: "home", model:[project: new Project()]
    }
    
    def delete() {
        def project = Project.findById(params?.id)
        if(userService.checkPermisions(project)) project.delete()
        redirect action:"list"
    }
    
    def close() {
        def project = Project.findById(params?.id)
        if(userService.checkPermisions(project)) {
            project.status = "D"
            project.save()
        }
        redirect action:"list"
    }

}

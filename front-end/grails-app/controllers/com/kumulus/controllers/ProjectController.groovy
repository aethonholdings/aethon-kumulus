package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*
import com.kumulus.services.*

    
class ProjectController {

    def userService
    def projectService
    def filesystemService
    def exportService
    
    @Secured(['ROLE_MANAGE'])
    def list() {
        def projectList = userService.getProjects()
        render view:"list", layout: "home", model:[projectList: projectList]
    }
    
    @Secured(['ROLE_MANAGE'])
    def edit() {
        def project = Project.findById(params?.id)
        if(userService.checkPermisions(project)) {
            render view:"edit", layout: "home", model:[project: project]
        }
    }
    
    @Secured(['ROLE_MANAGE'])
    def update() {
        def project = Project.findById(params?.id)
        if(!project) project = filesystemService.newProject(params) 
        else if(userService.checkPermisions(project))  {
            def client = Company.findById(params?.clientId)
            project.client = client
            bindData(project, params, [exclude:['client', 'clientId']])
            project.save()
        }
        redirect action:"list"
    }
    
    @Secured(['ROLE_MANAGE'])
    def create() {
        render view:"edit", layout: "home", model:[project: new Project()]
    }
    
    @Secured(['ROLE_MANAGE'])
    def delete() {
        def project = Project.findById(params?.id)
        if(userService.checkPermisions(project) && project.status == "A") project.delete()
        redirect action:"list"
    }
    
    @Secured(['ROLE_MANAGE'])
    def close() {
        def project = Project.findById(params?.id)
        if(userService.checkPermisions(project)) {
            project.status = "D"
            project.save()
        }
        redirect action:"list"
    }

    @Secured(['ROLE_EXTRACT'])
    def download() {
        def project = Project.findById(params?.id)
        response.contentType = grailsApplication.config.grails.mime.types['csv']
        response.setHeader("Content-disposition", "attachment; filename=extract")
        def export = projectService.getCSV(project)
        if(export) exportService.export('csv', response.outputStream, export.ledger, export.fields, export.labels, [:], [:])
    }
    
}

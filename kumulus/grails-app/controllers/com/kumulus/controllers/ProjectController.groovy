package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*
import com.kumulus.services.*

    
class ProjectController {

    def permissionsService
    def dataExportService
    def filesystemService
    def exportService
    
    @Secured(['ROLE_SUPERVISE'])
    def list() {
        def projectList 
        def actions = []
        switch(params?.type) {
            case "manage":
                projectList = permissionsService.getProjects()
                actions = ["Edit", "Delete", "Close", "Create"]
                break
            case "download":
                projectList = permissionsService.getProjects()
                actions = ["Download"]
                break
            case "access":
                projectList = permissionsService.getProjects()
                actions = ["Access"]
                break
            case "collect":
                projectList = permissionsService.getProjects([status: "A"])
                actions = ["Collect"]
                break
            case "upload":
                projectList = permissionsService.getProjects([status: "A"])
                actions = ["Upload"]                
                break
        }
        render view:"list", model:[projectList:projectList , title: "", actions: actions]
    }
    
    @Secured(['ROLE_SUPERVISE'])
    def edit() {
        def project = Project.findById(params?.id)
        if(permissionsService.checkPermisions(project)) {
            render view:"edit", model:[project: project]
        }
    }
    
    @Secured(['ROLE_SUPERVISE'])
    def update() {
        def project = Project.findById(params?.id)
        if(!project) project = filesystemService.newProject(params) 
        else if(permissionsService.checkPermisions(project))  {
            def client = Company.findById(params?.clientId)
            project.client = client
            bindData(project, params, [exclude:['client', 'clientId']])
            project.save()
        }
        redirect action:"list", params:[type:"manage"]
    }
    
    @Secured(['ROLE_SUPERVISE'])
    def create() {
        render view:"edit", model:[project: new Project()]
    }
    
    @Secured(['ROLE_SUPERVISE'])
    def delete() {
        def project = Project.findById(params?.id)
        if(permissionsService.checkPermisions(project) && project.status == "A") project.delete()
        redirect action:"list", params:[type:"manage"]
    }
    
    @Secured(['ROLE_SUPERVISE'])
    def close() {
        def project = Project.findById(params?.id)
        if(permissionsService.checkPermisions(project)) {
            project.status = "D"
            project.save()
        }
        redirect action:"list", params:[type:"manage"]
    }

    @Secured(['ROLE_VIEW'])
    def download() {
        def project = Project.findById(params?.id)
        response.contentType = grailsApplication.config.grails.mime.types['csv']
        response.setHeader("Content-disposition", "attachment; filename=extract")
        def export = dataExportService.getCSV(project)
        if(export) exportService.export('csv', response.outputStream, export.ledger, export.fields, export.labels, [:], [:])
    }
    
}

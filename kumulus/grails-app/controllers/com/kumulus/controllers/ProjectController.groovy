package com.kumulus.controllers

import com.kumulus.domain.*
import com.kumulus.services.*
import grails.converters.*

class ProjectController {

    def permissionsService
    def filesystemService
    
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
                projectList = permissionsService.getProjects([status: Project.STATUS_ACTIVE])
                actions = ["Collect"]
                break
            case "upload":
                projectList = permissionsService.getProjects([status: Project.STATUS_ACTIVE])
                actions = ["Upload"]                
                break
        }
        render view:"list", model:[projectList:projectList , title: "", actions: actions]
    }
    
    def edit() {
        def project = Project.findById(params?.id)
        if(permissionsService.checkPermissions(project)) {
            render view:"edit", model:[project: project]
        }
    }
    
    def update() {
        def project = Project.findById(params?.id)
        if(!project) project = filesystemService.newProject(params) 
        else if(permissionsService.checkPermissions(project))  {
            def client = Company.findById(params?.clientId)
            project.client = client
            bindData(project, params, [exclude:['client', 'clientId']])
            project.save()
        }
        redirect action:"list", params:[type:"manage"]
    }
    
    def create() {
        render view:"edit", model:[project: new Project()]
    }
    
    def delete() {
        def project = Project.findById(params?.id)
        if(permissionsService.checkPermissions(project) && project.status == "A") project.delete()
        redirect action:"list", params:[type:"manage"]
    }
    
    def close() {
        def project = Project.findById(params?.id)
        if(permissionsService.checkPermissions(project)) {
            project.status = Project.STATUS_CLOSED
            project.save()
        }
        redirect action:"list", params:[type:"manage"]
    }
    
       
}

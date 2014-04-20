package com.kumulus.controllers

import com.kumulus.domain.*
import com.kumulus.services.*
import grails.converters.*

class ProjectController {
     
    def permissionsService
    def filesystemService
    def searchableService 
    def captureService
        
    def create() {
        render view: "create", model:[project: new Project()]
    }
    
    def save() {
        if(params?.projectName && params?.ClientName)  {
            def project = captureService.insertProject(params.projectName, 
                                                            params.ClientName, 
                                                            params?.comment, 
                                                            permissionsService.getCompany().name, 
                                                            permissionsService.getUsername())
        }        
        redirect controller: "home", action: "index"
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

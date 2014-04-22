package com.kumulus.controllers

import com.kumulus.domain.*
import grails.converters.JSON

class ProjectController {

    def accessService
    def permissionsService
    
    def search() {   
        def data = request.JSON
        def responseData = [:]
        if (data.q?.trim() && data?.projectId) {
            def project = Project.findById(data.projectId)
            if(project && permissionsService.checkPermissions(project)) responseData = accessService.search(project, data.q)
        }
        render responseData as JSON
     }  
}

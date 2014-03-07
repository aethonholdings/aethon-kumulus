package com.kumulus.controllers

import com.kumulus.domain.*
import com.kumulus.services.*
import grails.converters.*
import org.compass.core.engine.SearchEngineQueryParseException

class ProjectController {
     
    def permissionsService
    def filesystemService
    def searchableService 
    static String WILDCARD = "*"
    
    def update() {
        def project = Project.get(params?.id)
        if(project && permissionsService.checkPermissions(project))  {
            def client = Company.findById(params?.clientId)
            project.client = client
            bindData(project, params, [exclude:['client', 'clientId']])
            project.save()
        }
        redirect action: "view", id: params?.id
    }
    
    def create() {
        render view: "create", model:[project: new Project()]
    }
    
    def save() {
        def project = filesystemService.newProject(params) 
        def client = Company.findById(params?.clientId)
        project.client = client
        bindData(project, params, [exclude:['client', 'clientId']])
        project.save()
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
    
    def view(){
        
      def project=  Project.findById(Integer.parseInt(params.id))
      render view:"view" , model:[project:project]
    }
    
     def search() 
     { 
         
        def searchResult 
        if (!params.q?.trim()) {
            return [:]
        }
        try {
               String searchTerm = WILDCARD+ params.q + WILDCARD
               searchResult = searchableService.search(searchTerm, params)
               return [searchResult: searchResult]
        } catch (SearchEngineQueryParseException ex) {
               return [parseException: true]
        }
     }  
}

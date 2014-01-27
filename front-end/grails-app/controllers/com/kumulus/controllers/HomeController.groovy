package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*

class HomeController {
    
    def projectService
    def userService
    
    @Secured(['ROLE_ADMIN', 'ROLE_PROCESS', 'ROLE_IMPORT', 'ROLE_REVIEW', 'ROLE_SUPERVISE', 'ROLE_VIEW'])
    def index() { 
        render view:"index", layout:"home", model:[pageTitle: "Home"]
    }

    @Secured(['ROLE_SUPERVISE'])
    def order() { 

    }
    
    @Secured(['ROLE_SUPERVISE'])
    def manage() { 
        def projectList = userService.getProjects([status: "A"])
        redirect controller: "project", action:"list"
    }

    @Secured(['ROLE_VIEW'])
    def download() { 
        
    }
    
    @Secured(['ROLE_VIEW'])
    def access() { 
        render view:"access", layout:"home", model:[pageTitle: "Access archive"]
    }

    @Secured(['ROLE_IMPORT'])
    def collect() {
        def projectList = userService.getProjects([status: "A"])
        render view:"collect", layout:"home", model:[pageTitle: "Collect archive documents", projects: projectList]
    }
    
    @Secured(['ROLE_IMPORT'])
    def upload() {
        def projectList = userService.getProjects([status: "A"])
        render view:"upload", layout:"home", model:[pageTitle: "Import scan images", projects: projectList]
    }
    
    @Secured(['ROLE_IMPORT'])
    def build() {
        redirect controller:"task", action:"listGroupByProject"
    }
    
    @Secured(['ROLE_IMPORT'])
    def pickup() {
        
    }
    
    @Secured(['ROLE_PROCESS'])
    def process() { 
        redirect controller:"task", action:"list", params:[type:"ocr", title:"Process documents"]
    }
    
    @Secured(['ROLE_SUPERVISE'])
    def review() { 
        redirect controller:"task", action:"list", params:[type:"review", title:"Review documents"]
    }
    

    
}
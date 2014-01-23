package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*

class HomeController {
    
    def projectService
    def userService
    
    @Secured(['ROLE_CLASSIFY', 'ROLE_ADMIN', 'ROLE_VIEW', 'ROLE_COLLECT', 'ROLE_EXPORT', 'ROLE_VALIDATE', 'ROLE_IMPORT'])
    def index() { 
        render view:"index", layout:"home", model:[pageTitle: "Home"]
    }

    @Secured(['ROLE_MANAGE'])
    def order() { 

    }
    
    @Secured(['ROLE_MANAGE'])
    def manage() { 
        def projectList = userService.getProjects([status: "A"])
        redirect controller: "project", action:"list"
    }

    @Secured(['ROLE_MANAGE'])
    def download() { 
        
    }
    
    @Secured(['ROLE_ACCESS'])
    def access() { 
        render view:"access", layout:"home", model:[pageTitle: "Access archive"]
    }

    @Secured(['ROLE_COLLECT'])
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
        redirect controller:"document", action:"build"
    }
    
    @Secured(['ROLE_IMPORT'])
    def pickup() {
        
    }
    
    @Secured(['ROLE_EXTRACT'])
    def process() { 
        redirect controller:"task", action:"list", params:[type:"ocr", title:"Process documents"]
    }
    
    @Secured(['ROLE_REVIEW'])
    def review() { 
        redirect controller:"task", action:"list", params:[type:"review", title:"Review documents"]
    }
    

    
}
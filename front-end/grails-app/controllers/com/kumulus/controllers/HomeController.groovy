package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*

class HomeController {
    
    def projectService
    def userService
    
    @Secured(['ROLE_ADMIN', 'ROLE_PROCESS', 'ROLE_IMPORT', 'ROLE_REVIEW', 'ROLE_SUPERVISE', 'ROLE_VIEW'])
    def index() { 
        render(view:"index", layout:"home", model:[pageTitle: "Home"])
    }

    // SUPERVISOR USER CONTROLLER ACTIONS
    @Secured(['ROLE_SUPERVISE'])
    def manage() { 
        def projectList = userService.getProjects([status: "A"])
        redirect(controller: "project", action:"list", params:[type: "manage"])
    }
    
    @Secured(['ROLE_SUPERVISE'])
    def review() { 
        redirect(controller:"task", action:"list", params:[type:"review", title:"Review documents"])
    }
     
    @Secured(['ROLE_SUPERVISE'])
    def order() { 
        
    }
    
    // VIEWER USER CONTROLLER ACTIONS
    @Secured(['ROLE_VIEW'])
    def download() { 
        redirect(controller: "project", action: "list", params:[type: "download"])
    }
    
    @Secured(['ROLE_VIEW'])
    def access() { 
        redirect(controller: "project", action: "list", params:[type: "access"])
    }

    // IMPORT USER CONTROLLER ACTIONS
    @Secured(['ROLE_IMPORT'])
    def collect() {
        redirect(controller: "project", action: "list", params:[type: "collect"])
    }
    
    @Secured(['ROLE_IMPORT'])
    def upload() {
        redirect(controller: "project", action: "list", params:[type: "upload"])    
    }
    
    @Secured(['ROLE_IMPORT'])
    def build() {
        redirect(controller:"task", action:"listGroupByProject")
    }
    
    @Secured(['ROLE_IMPORT'])
    def pickup() {
        
    }
    
    // PROCESS USER CONTROLLER ACTIONS
    @Secured(['ROLE_PROCESS'])
    def process() { 
        redirect(controller:"task", action:"list", params:[type:"ocr", title:"Process documents"])
    }
    
}
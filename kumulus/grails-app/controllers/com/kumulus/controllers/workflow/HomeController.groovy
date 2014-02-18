package com.kumulus.controllers.workflow

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*

@Secured(['ROLE_ADMIN', 'ROLE_PROCESS', 'ROLE_IMPORT', 'ROLE_REVIEW', 'ROLE_SUPERVISE', 'ROLE_VIEW'])
class HomeController {
    
    def permissionsService
    
    def index() { 
        render(view:"index", model:[pageTitle: "Home"])
    }

    // SUPERVISOR USER CONTROLLER ACTIONS
    def manage() { 
        def projectList = permissionsService.getProjects([status: "A"])
        redirect(controller: "project", action:"list", params:[type: "manage"])
    }
    
    def review() { 
        redirect(controller:"task", action:"list", params:[type: Task.REVIEW_DOCUMENT])
    }
     
    def order() { 
        
    }
    
    // VIEWER USER CONTROLLER ACTIONS
    def download() { 
        redirect(controller: "project", action: "list", params:[type: "download"])
    }
    
    def access() { 
        redirect(controller: "access", action: "access")
    }

    // IMPORT USER CONTROLLER ACTIONS
    def collect() {
        redirect(controller: "project", action: "list", params:[type: "collect"])
    }
    
    def upload() {
        redirect(controller: "project", action: "list", params:[type: "upload"])    
    }
    
    def build() {
        redirect(controller:"task", action:"listGroupByProject", params:[type: Task.BUILD_DOCUMENT])
    }
    
    def pickup() {
        redirect(controller:"capture", action:"pickup") 
    }
    
    // PROCESS USER CONTROLLER ACTIONS
    def process() { 
        redirect(controller:"task", action:"list", params:[type: Task.OCR_DOCUMENT])
    }
    
}
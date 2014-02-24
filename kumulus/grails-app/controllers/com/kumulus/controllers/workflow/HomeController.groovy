package com.kumulus.controllers.workflow

import com.kumulus.domain.*

class HomeController {
    
    def permissionsService
    def workflowService
    
    def index() { 
        def projectList = Project.findAll {
            company == permissionsService.getCompany()
            status == Project.STATUS_ACTIVE
        }
        def userTasks = workflowService.openTaskSummary(permissionsService.getUsername())
        def backOfficeTasks = workflowService.openTaskSummary(null)
        render(view:"index", model:[pageTitle: "Home", projectList: projectList, userTasks: userTasks, backOfficeTasks: backOfficeTasks])
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
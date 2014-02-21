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
        def tasks = workflowService.taskSummary(permissionsService.getUsername())
        render(view:"index", model:[pageTitle: "Home", projectList: projectList, tasks: tasks])
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
    
    def completeTasks() {
        def project = Project.findById(params?.id)
        if(permissionsService.checkPermissions(project)) {
            switch(workItem.type) {
                case(workItem.BUILD_DOCUMENT):
                    redirect controller: "capture", action: "build", id: project.id
                    break
                case(workItem.OCR_DOCUMENT):
                    redirect controller: "structure", action: "process", id: project.id
                    break
                case(workItem.VALIDATE):
                    break
            }
        }
    }
    
}
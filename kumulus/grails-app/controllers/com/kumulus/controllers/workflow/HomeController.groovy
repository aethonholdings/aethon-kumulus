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
        def userTasks = workflowService.getTaskQueues(permissionsService.getUsername())
        def backOfficeTasks = workflowService.getTaskQueues(null)
        render(view:"index", model:[pageTitle: "Home", projectList: projectList, userTasks: userTasks, backOfficeTasks: backOfficeTasks, userId: permissionsService.getUsername()])
        
    }
    
}
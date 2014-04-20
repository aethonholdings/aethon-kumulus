package com.kumulus.controllers.workflow

import com.kumulus.domain.*

class HomeController {
    
    def permissionsService
    def workflowService
    
    def index() { 
        
        def projectList = Project.findAll {
            company == permissionsService.getCompany()?.name
            status == Project.STATUS_ACTIVE
        }
        def shipmentList=Shipment.findAll()
        def userTasks = workflowService.getTaskQueues(permissionsService.getUsername())
        def backOfficeTasks = workflowService.getTaskQueues(null)
        render(view:"index", model:[pageTitle: "Home", projectList: projectList,shipmentList:shipmentList, userTasks: userTasks, backOfficeTasks: backOfficeTasks, userId: permissionsService.getUsername()])
        
    }
    
}
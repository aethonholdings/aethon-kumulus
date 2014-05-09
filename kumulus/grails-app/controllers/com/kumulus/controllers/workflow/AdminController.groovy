package com.kumulus.controllers.workflow

import com.kumulus.domain.*

class AdminController {

    def workflowService
    def permissionsService
    
     def home() {
        def projectList = Project.findAll {
            company == permissionsService.getCompany()?.name
            status == Project.STATUS_ACTIVE
        }
        def shipmentList=Shipment.findAll()
        def userTasks = workflowService.getTaskQueues(permissionsService.getUsername())
        def backOfficeTasks = workflowService.getTaskQueues(null)
        render(view:"home", model:[pageTitle: "Home", projectList: projectList,shipmentList:shipmentList, userTasks: userTasks, backOfficeTasks: backOfficeTasks, userId: permissionsService.getUsername()])    
    }
}

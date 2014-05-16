package com.kumulus.controllers.workflow

import com.kumulus.domain.*
import grails.converters.JSON

// NEED TO SECURE THIS BASED ON BACK OFFICE PERMISSIONS
class BackOfficeController {
    
    def permissionsService
    def workflowService
    def captureService
    
    def home() {
        def projectList = Project.findAll {
            company == permissionsService.getCompany()?.name
            status == Project.STATUS_ACTIVE
        }
        def shipmentList=Shipment.findAll()
        def userTasks = Task.findAllByUserIdAndCompleted(permissionsService.getUsername(), null, [sort:"created", order:"asc"])
        def backOfficeTasks = workflowService.getTaskQueues(null)
        render(view:"home", model:[pageTitle: "Home", projectList: projectList,shipmentList:shipmentList, userTasks: userTasks, backOfficeTasks: backOfficeTasks, userId: permissionsService.getUsername()])    
    }
    
    def performTask() {
        def task = Task.get(params?.id)
        if(task && permissionsService.checkPermissions(task) && !task.completed) {
            def currencies = Currency.listOrderByFullName()
            def documentTypes = DocumentType.listOrderByName()
            def document = task.document            
            render view: "process", model:[task: task, document: document, currencies: currencies, documentTypes: documentTypes, size:document?.pages?.size()]
        } else {
            redirect controller: "home", action: "index"
        }
    }
        
}

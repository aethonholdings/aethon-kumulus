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
        def task = Task.get(params?.id?.toLong())
        if(task && permissionsService.checkPermissions(task) && !task.completed) {
            def currencies = Currency.listOrderByFullName()
            def documentTypes = DocumentType.listOrderByName()
            def document = task.document            
            render view: "process", model:[task: task, document: document, currencies: currencies, documentTypes: documentTypes, size:document?.pages?.size()]
        } else {
            redirect controller: "home", action: "index"
        }
    }
    
    def closeTaskAndNext() {
        def task = Task.findById(params?.id)
        if(task && permissionsService.checkPermissions(task) && (task.type == Task.TYPE_PROCESS || task.type == Task.TYPE_VALIDATE)) {
            def document = task.document
            workflowService.completeTask(task)
            if(task.type == Task.TYPE_PROCESS) workflowService.createTask(document, Task.TYPE_VALIDATE, permissionsService.getUsername())
            def nextTask = workflowService.getNextTask(permissionsService.getUsername())
            if(nextTask) { 
                workflowService.assignTask(nextTask, permissionsService.getUsername())
                workflowService.startTask(nextTask)
                redirect action: "performTask", id: nextTask.id
            } else {
                redirect action: "home"
            }
        }
        
    }  
        
}

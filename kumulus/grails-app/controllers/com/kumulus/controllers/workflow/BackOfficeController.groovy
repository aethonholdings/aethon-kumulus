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
        def userTasks = workflowService.getTaskQueues(permissionsService.getUsername())
        def backOfficeTasks = workflowService.getTaskQueues(null)
        render(view:"home", model:[pageTitle: "Home", projectList: projectList,shipmentList:shipmentList, userTasks: userTasks, backOfficeTasks: backOfficeTasks, userId: permissionsService.getUsername()])    
    }
    
    def getNextTask() {
        if(params?.type) {
            def task = workflowService.getNextTask(params.type, permissionsService.getUsername())
            if(task) {
                workflowService.assignTask(task, permissionsService.getUsername())
                workflowService.startTask(task)
                redirect action: "process", params: [taskId: task.id]
            } else {
                redirect controller: "home", action: "index"
            }
        }
    }
    
    def process() {
        def task = Task.get(params?.taskId)
        if(task && !task.completed) {
            def currencies = Currency.listOrderByFullName()
            def documentTypes = DocumentType.listOrderByName()
            def document = task.document            
            render view: "process", model:[task: task, document: document, currencies: currencies, documentTypes: documentTypes, size:document?.pages?.size()]
        } else {
            redirect controller: "home", action: "index"
        }
    }
    
}

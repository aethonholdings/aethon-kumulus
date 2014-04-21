package com.kumulus.controllers.workflow

import com.kumulus.domain.*
import grails.converters.JSON

// NEED TO SECURE THIS BASED ON BACK OFFICE PERMISSIONS
class BackOfficeController {
    
    def structureService
    def permissionsService
    def workflowService
    
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
    
    def save(){
        // NEED TO MOVE ALL THIS TO A SERVICE, CURRENTLY IT IS NOT ATOMIC
        def response = [done: false]
        def data = request.JSON
        def task = Task.findById(data?.form.taskId)
        def currency = Currency.findById(data?.form.currency)
        def documentType = DocumentType.findById(data?.form.documentType)

        if(task && !task.completed && currency && documentType && data?.form.company && data?.form.identifier) {
            
            // update the document
            Date date
            if(data.form.date) date = new Date().parse("dd/MM/yyyy", data.form.date) else date = null
            def document = structureService.updateDocument(task.document, data.form.company, documentType, date, data.form.identifier)
            
            // update the line items submitted
            def updatedLineItems = []
            data.form?.lineItems.each {
                date = null
                if(it?.lineItemDate) date = new Date().parse("dd/MM/yyyy", it.lineItemDate)
                def lineItem = structureService.updateLineItem(it?.lineItemId, it?.pageId, currency, date, it?.description, it?.quantity, it?.price, it?.amount)
                updatedLineItems.add(lineItem)
            }
            
            // remove any deleted line items from the database
            def lineItemsToRemove = document.pages.lineItems.toList().flatten()
            lineItemsToRemove.removeAll(updatedLineItems)
            lineItemsToRemove.each { lineItem ->
                lineItem.page.removeFromLineItems(lineItem)
                lineItem.delete()
            }
            
            // close the task if requested
            if(data?.completeTask) {
                workflowService.completeTask(task)
                if(task.type==Task.TYPE_PROCESS) {
                    def newTask = workflowService.createTask(document, Task.TYPE_VALIDATE, permissionsService.getUsername())
                    workflowService.assignTask(newTask, task.document.project.ownerId)
                }
            }
            response.done = true
        }
        render response as JSON
    }
}

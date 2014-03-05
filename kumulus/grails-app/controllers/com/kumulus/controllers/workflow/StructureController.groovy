package com.kumulus.controllers.workflow

import com.kumulus.domain.*
import grails.converters.JSON

class StructureController {
    
    def structureService
    def permissionsService
    def workflowService
    
    def process() {
        // NEED TO SECURE THIS BASED ON BACK OFFICE PERMISSIONS
        def task = workflowService.getNextTask(Task.TYPE_PROCESS)
        def currencies = Currency.listOrderByFullName()
        def documentTypes = DocumentType.listOrderByName()
        def document = task.document
        if(currencies && documentTypes && document) {
            workflowService.assignTask(task, permissionsService.getUsername())
            workflowService.startTask(task)
          
            render view: "process", model:[task: task, document: document, currencies: currencies, documentTypes: documentTypes, size:document?.pages?.size()]
        }
    }
    
    def save(){
        // must check permissions properly

        def response = [done: false]
        def data = request.JSON
        def task = Task.findById(data?.taskId)
        def currency = Currency.findById(data?.currency)
        def documentType = DocumentType.findById(data?.documentType)
        if(task && currency && documentType) {

            // update the document
            Date date
            if(data?.date) date = new Date().parse("yyyy/MM/dd", data.date) else date = null
            def document = structureService.updateDocument(task.document, documentType, date, data?.identifier)
            
            // update the line items
            data?.lineItems.each {
                
                date = null
                if(it?.date) date = new Date().parse("yyyy/MM/dd", it.date)
                def lineItem = structureService.updateLineItem(it?.lineItemId, it?.pageId, currency, date, it?.description, it?.quantity, it?.price, it?.amount)
            }
            workflowService.completeTask(task)
            response.done = true
        }
        render response as JSON
    }
    
}

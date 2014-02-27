package com.kumulus.controllers.workflow

import com.kumulus.domain.*

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
            render view: "process", model:[document: document, currencies: currencies, documentTypes: documentTypes]
        }
    }
    
    def save(){
        // must check permissions properly
        println(params)
          def data = request.JSON
          
        def currency = Currency.findByShortName(params?.currency)
        if(params?.id && currency) {
            
            // update the document
            Date date
            if(params?.date) date = new Date().parse("yyyy-MM-dd", params.date) else date = null
            def document = structureService.updateDocument(params?.id, params?.documentType, date, params?.identifier)
            
            // update the line items
            for(int i=0; i<params?.amount.size(); i++) {
                println(i)
                // def lineItem = structureService.updateLineItem(params?.lineItemId[i], page, currency, new Date().parse("yyyy-MM-dd", params?.lineItemDate[i]), params.float(quantity[i]), params.float(price[i]), params.float(amount[i]))
            }
            render "OK"   
        }
    }
    
}

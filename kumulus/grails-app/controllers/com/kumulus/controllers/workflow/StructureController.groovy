package com.kumulus.controllers.workflow

import com.kumulus.domain.*

class StructureController {
    
    def structureService
    def permissionsService
    def workflowService
    
    def process() {
        // NEED TO SECURE THIS BASED ON BACK OFFICE PERMISSIONS
        def task = workflowService.getNextTask(Task.PROCESS_DOCUMENT)
        workflowService.assignTask(task, permissionsService.getUsername())
        def currencies = Currency.listOrderByFullName()
        def documentTypes = DocumentType.listOrderByName()
        def document = task.document
        render view: "process", model:[document: document, currencies: currencies, documentTypes: documentTypes]
    }
    
    def save(){
        // must check permissions properly
        if(params?.id) {
            
            // update the document
            Date date
            if(params?.date) date = new Date().parse("yyyy-MM-dd", params.date) else date = null
            def document = structureService.updateDocument(params?.id, params?.documentType, date, params?.identifier)
            
            // update the line items
            println(params)
            
            render "OK"   
        }
    }
    
}

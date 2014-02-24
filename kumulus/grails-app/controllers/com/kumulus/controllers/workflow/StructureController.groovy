package com.kumulus.controllers.workflow

import com.kumulus.domain.*

class StructureController {
    
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
    
    
    def updateData(){
        // not getting the currency
        def document = Document.findById(params?.id)
        if(document && permissionsService.checkPermissions(document)) {
            def currency = Currency.findById(params?.currencyId)
            def documentType = DocumentType.findById(params?.shortName)
            def company
            render "OK"   
        }
    }
    
}

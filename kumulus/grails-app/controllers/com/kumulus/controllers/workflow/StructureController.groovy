package com.kumulus.controllers.workflow

import com.kumulus.domain.*

class StructureController {
    
    def permissionsService
    
    def process() {
        // this should be from the workflow service getting the next task for the user
        def task = Task.findByProjectAndUserIdAndTypeAndCompleted(Project.findById(params?.id), permissionsService.getUsername(), Task.OCR_DOCUMENT, null)
        def currencies = Currency.listOrderByFullName()
        def documentTypes = DocumentType.listOrderByName()
        def document = task.document
        render view: "process", model:[document: document, currencies: currencies, documentTypes: documentTypes]
    }
    
    
    def updateData(){
        
   
    }
}

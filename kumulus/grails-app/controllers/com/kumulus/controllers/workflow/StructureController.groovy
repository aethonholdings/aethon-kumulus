package com.kumulus.controllers.workflow

import com.kumulus.domain.*

class StructureController {

    def process() {
        def task = Task.findById(params?.id)
        def currencies = Currency.listOrderByFullName()
        def documentTypes = DocumentType.listOrderByName()
        def document = task.document
        render view: "process", model:[document: document, currencies: currencies, documentTypes: documentTypes]
    }
    
    
    def update(){
        
   
    }
}

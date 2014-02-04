package com.kumulus.controllers.workflow

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*

@Secured(['ROLE_IMPORT'])
class StructureController {

    def process() {
        def task = Task.findById(params?.id)
        def currencies = Currency.listOrderByFullName()
        def documentTypes = DocumentType.listOrderByName()
        def document = task.document
        render view: "process", model:[document: document, currencies: currencies, documentTypes: documentTypes]
    }
}

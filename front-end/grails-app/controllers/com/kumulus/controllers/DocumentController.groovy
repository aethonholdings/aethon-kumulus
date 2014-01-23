package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*

class DocumentController {

    def userService
    
    @Secured(['ROLE_IMPORT'])
    def build() {
        def documentList = []
        def company = userService.getCompany()
        Project.findAllByCompany(company).each() { project ->
            Document.findAllByProjectAndStatus(project, Document.EDITABLE).each() { document ->
                documentList.add document
            }
        }
        render view: "build", layout: "home", model: [documents: documentList]
    }

}

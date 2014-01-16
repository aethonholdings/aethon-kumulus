package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*

class ExtractController {

    def springSecurityService
    def exportService
    def grailsApplication
    
    @Secured(['ROLE_EXTRACT'])
    def index() { 
        def projectList = Project.findAllByStatus("A")
        render view:"index", layout:"home", model:[projects: projectList]
    }
    
    @Secured(['ROLE_EXTRACT'])
    def download() {
        response.contentType = grailsApplication.config.grails.mime.types['csv']
        response.setHeader("Content-disposition", "attachment; filename=extract")
        def project = Project.findById(params.id)
        def ledger = new ArrayList()
        if (project) {
            def nodes = Nodes?.findAllByProjectAndType(project, "D")
            nodes.each {node ->
                node.documents.each {document ->
                    document.lineItems.each {
                        ledger.add it
                    }
                }
            }
            println(ledger)
            exportService.export('csv', response.outputStream, ledger, [:], [:])
        }
    }
    
}

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
        def ledger = LineItem.findAllByProject
        exportService.export('csv', response.outputStream,"need a list here", [:], [:])
    }
    
}

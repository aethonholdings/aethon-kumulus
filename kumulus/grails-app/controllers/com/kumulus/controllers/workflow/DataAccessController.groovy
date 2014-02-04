package com.kumulus.controllers.workflow

import grails.plugin.springsecurity.annotation.Secured
import org.compass.core.engine.SearchEngineQueryParseException
import com.kumulus.domain.*

@Secured(['ROLE_VIEW'])
class DataAccessController {

    def exportService
    def dataExportService
    def searchableService
    
    def search() { 
        if (!params.q?.trim()) {
            return [:]
        }
        try {
            def searchResult = searchableService.search(params.q, params)
            return [searchResult: searchResult]
        } catch (SearchEngineQueryParseException ex) {
            return [parseException: true]
        }
    }
    
    def download() {
        println(params)
        def project = Project?.findById(params?.id)
        response.contentType = grailsApplication.config.grails.mime.types['csv']
        response.setHeader("Content-disposition", "attachment; filename=extract")
        def export = dataExportService.getCSV(project)
        if(export) exportService.export('csv', response.outputStream, export.ledger, export.fields, export.labels, [:], [:])
    }
    
    def access() {
        render view: "access"
    }
}

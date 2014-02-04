package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import org.compass.core.engine.SearchEngineQueryParseException

@Secured(['ROLE_IMPORT'])
class SearchController {
    
    def searchableService
    
    def index() { 
        if (!params.q?.trim()) {
            return [:]
        }
        try {
            def searchResult = searchableService.search(params.q, params)
            println(searchResult)
            return [searchResult: searchResult]
        } catch (SearchEngineQueryParseException ex) {
            return [parseException: true]
        }
    }
}

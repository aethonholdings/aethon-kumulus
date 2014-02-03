package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*
import grails.converters.*

class CompanyController {
    
    @Secured(['ROLE_PROCESS'])
    def search() { 
        def companies = []
        Company.findAllByNameLike(params?.term+'%', [sort:'name', order:'asc']).each { companies.add(it.name) }
        render companies as JSON
    }
}

package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*
import grails.converters.*

@Secured(['ROLE_ADMIN', 'ROLE_PROCESS', 'ROLE_IMPORT', 'ROLE_REVIEW', 'ROLE_SUPERVISE', 'ROLE_VIEW'])
class CompanyController {
    
    def search() { 
        def companies = []
        Company.findAllByNameLike(params?.term+'%', [sort:'name', order:'asc']).each { companies.add(it.name) }
        render companies as JSON
    }
}

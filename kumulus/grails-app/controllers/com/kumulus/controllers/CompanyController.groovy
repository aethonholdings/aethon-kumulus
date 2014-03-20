package com.kumulus.controllers

import com.kumulus.domain.*
import grails.converters.*

class CompanyController {
    
    def search() { 
        def companies = []
        Company.findAllByNameLike(params?.term+'%', [sort:'name', order:'asc']).each { companies.add(it.name) }
        render companies as JSON
    }
}

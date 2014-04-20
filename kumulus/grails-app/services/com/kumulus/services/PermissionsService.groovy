package com.kumulus.services

import grails.transaction.Transactional
import com.kumulus.domain.*

import grails.plugin.springsecurity.SpringSecurityUtils

@Transactional
class PermissionsService {

    def springSecurityService
    def grailsApplication
    
    def getCompany() {
        def company = springSecurityService.currentUser.company
        return(company)
    }
    
    def getProjects(params) {
        def projectList = Project.findAllByCompany(getCompany().name)
        if(params?.status) projectList = projectList.findAll { it.status == params.status }
        return (projectList)
    }
        
    def getUsername() {
        return(springSecurityService.currentUser.username)
    }
    
    boolean checkPermissions(instance) { 
        boolean permission = false
        String companyName = getCompany().name?.toString()
        String owner = instance.owner()?.toString()
        if(owner && companyName && owner.equalsIgnoreCase(companyName)) permission = true
        return(permission)
    }
    
}

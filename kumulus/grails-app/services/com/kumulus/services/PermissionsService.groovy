package com.kumulus.services

import grails.transaction.Transactional
import com.kumulus.domain.*

@Transactional
class PermissionsService {

    def springSecurityService
    def grailsApplication
    
    def getCompany() {
        def authorities = springSecurityService.authentication.authorities      // get the user authorities
        String company
        authorities.each { authority ->
            if(!grailsApplication.config.kumulus.roles.contains(authority)) {
                company = authority.toString().replaceAll("ROLE_", {""}).replaceAll("_", {" "})
            }
        }
        return(company)
    }
    
    def getProjects(params) {
        def projectList = Project.findAllByCompany(getCompany())
        if(params?.status) projectList = projectList.findAll { it.status == params.status }
        return (projectList)
    }
        
    def getUsername() {
        def auth = springSecurityService.getAuthentication()
        String username = auth.getPrincipal().getUsername()        
    }
    
    boolean checkPermissions(instance) { 
        boolean permission = false
        if(instance?.owner() && getCompany() && instance?.owner() == getCompany()) permission = true
        return(permission)
    }
}

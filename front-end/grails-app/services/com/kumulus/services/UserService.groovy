package com.kumulus.services

import grails.transaction.Transactional
import com.kumulus.domain.*

@Transactional
class UserService {

    def springSecurityService
    
    def getCompany() {
        def authorities = springSecurityService?.authentication?.authorities
        String company
        authorities.each { authority ->
            if(authority!="ROLE_CLASSIFY" && authority!="ROLE_ACCESS" && authority!="ROLE_EXTRACT" && authority!="ROLE_COLLECT" && authority!="ROLE_VALIDATE") {
                company = authority.toString().replaceAll("ROLE_", {""}).replaceAll("_", {" "})
            }
        }
        return(company)
    }
    
    def getProjects(params) {
        def projectList = Project.findAllByClientLDAPId(getCompany())
        if(params?.status) projectList = projectList.findAll { it.status == params.status }
        return (projectList)
    }
    
    def getTasks() {
        return(Task.findByUserId(getUsername()))
    }
    
    def getUsername() {
        def auth = springSecurityService.getAuthentication()
        String username = auth.getPrincipal().getUsername()        
    }
    
}

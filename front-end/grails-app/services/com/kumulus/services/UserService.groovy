package com.kumulus.services

import grails.transaction.Transactional
import com.kumulus.domain.*

@Transactional
class UserService {

    def springSecurityService
    
    def getCompany() {
        String company
        authorities.each { authority ->
            if(authority!="ROLE_CLASSIFY" && authority!="ROLE_ACCESS" && authority!="ROLE_EXTRACT" && authority!="ROLE_COLLECT" && authority!="ROLE_VALIDATE") {
                company = authority.toString().replaceAll("ROLE_", {""}).replaceAll("_", {" "})
            }
        }
        return(company)
    }
    
    def getProjects() {
        
    }
    
    def getAuthorities() {
        def authorities = springSecurityService?.authentication?.authorities
        
    }
    
}

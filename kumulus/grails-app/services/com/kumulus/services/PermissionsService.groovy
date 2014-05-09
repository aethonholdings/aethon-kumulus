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
        
        // check permissions for customers; authenticated internal roles have access to objects
        // in order to work with them
        boolean permission = false
        String userCategory = getUserCategory()
        if(userCategory) {
            if(userCategory=="CUSTOMER") {
                String companyName = getCompany().name?.toString()
                String owner = instance.owner()?.toString()
                if(owner && companyName && owner.equalsIgnoreCase(companyName)) permission = true
            } else {
                permission = true
            }
        }
        return(permission)
    }
    
    String getUserCategory() {
        String category
        // return the key user category based on role
        if(SpringSecurityUtils.ifAllGranted("ROLE_CUSTOMER")) category = "CUSTOMER"
        if(SpringSecurityUtils.ifAllGranted("ROLE_CAPTURE")) category = "CAPTURE"
        if(SpringSecurityUtils.ifAllGranted("ROLE_BACK_OFFICE")) category = "BACK_OFFICE"
        if(SpringSecurityUtils.ifAllGranted("ROLE_ADMIN")) category = "ADMIN"
        return(category) 
    }
    
}

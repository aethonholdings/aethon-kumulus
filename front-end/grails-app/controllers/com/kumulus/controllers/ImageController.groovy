package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*

class ImageController {

    def userService
    
    @Secured(['ROLE_IMPORT'])
    def upload() {
        def project = Project.findById(params?.id)
        if(project.company == userService.getCompany()) render view:"upload", layout:"home", model:[project: project]
    }
    
    @Secured(['ROLE_IMPORT'])
    def process() {
        
    }
}

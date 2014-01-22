package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*
import com.lucastex.grails.fileuploader.UFile



class ImageController {

    def userService
    def filesystemService
    
    @Secured(['ROLE_IMPORT'])
    def upload() {
        def project = Project.findById(params?.id)
        if(project?.company == userService.getCompany()) render view:"upload", layout:"home", model:[project: project]
    }
    
    @Secured(['ROLE_IMPORT'])
    def process() {
        filesystemService.processUploadFile(Nodes.findById(params?.id), UFile.findById(params?.ufileId))
        redirect action:"upload", params:[id: 1]        
    }
}

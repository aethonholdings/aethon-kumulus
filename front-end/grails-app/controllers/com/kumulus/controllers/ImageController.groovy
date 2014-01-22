package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*
import com.lucastex.grails.fileuploader.UFile

class ImageController {

    def userService
    
    @Secured(['ROLE_IMPORT'])
    def upload() {
        def project = Project.findById(params?.id)
        if(project?.company == userService.getCompany()) render view:"upload", layout:"home", model:[project: project]
    }
    
    @Secured(['ROLE_IMPORT'])
    def process() {
        println(params)
        def node = Nodes.findById(params?.id)
        def uFile = UFile.findById(params?.ufileId)
        if(node && uFile) {
            
            def project = Nodes.findById(params?.id)?.project
            redirect action:"upload", params:[id: project.id]
        }
        
    }
}

package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*
import com.lucastex.grails.fileuploader.UFile
import com.lucastex.grails.fileuploader.DownloadController

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
        def node = Nodes.findById(params?.id)
        def scanBatch = new ScanBatch(userId: userService.getUsername(), timestamp: new Date(), project: node.project)
        scanBatch.save()
        filesystemService.processUploadFile(node, UFile.findById(params?.ufileId), scanBatch)
        redirect action: "upload", params:[id: 1]        
    }

    @Secured(['ROLE_CLASSIFY', 'ROLE_ADMIN', 'ROLE_VIEW', 'ROLE_COLLECT', 'ROLE_EXPORT', 'ROLE_VALIDATE', 'ROLE_IMPORT'])    
    def get() {
        def image = Image.findById(params?.id)
        if(image) redirect controller: "download", action: "index", id: image.file.id
    }
}

package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*
import com.lucastex.grails.fileuploader.*

class ImageController {

    def userService
    def filesystemService
    
    @Secured(['ROLE_IMPORT'])
    def upload() {
        def project = Project.findById(params?.id)
        if(project?.company == userService.getCompany()) render view:"upload", model:[project: project]
    }
    
    @Secured(['ROLE_IMPORT'])
    def process() {
        def node = Node.findById(params?.nodeId)
        def scanBatch = new ScanBatch(userId: userService.getUsername(), timestamp: new Date(), project: node.project)
        scanBatch.save()
        filesystemService.processUploadFile(node, UFile.findById(params?.ufileId), scanBatch)
        redirect action: "upload", params:[id: 1]     
    }

    @Secured(['ROLE_ADMIN', 'ROLE_PROCESS', 'ROLE_IMPORT', 'ROLE_REVIEW', 'ROLE_SUPERVISE', 'ROLE_VIEW'])    
    def get() {
        def image = Image.findById(params?.id)
        if(image) redirect controller: "download", action: "index", id: image.file.id
    }
}

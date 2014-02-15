package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*
import com.lucastex.grails.fileuploader.*

@Secured(['ROLE_ADMIN', 'ROLE_PROCESS', 'ROLE_IMPORT', 'ROLE_REVIEW', 'ROLE_SUPERVISE', 'ROLE_VIEW'])
class ImageController {

    def permissionsService
    def filesystemService
        
    def upload() {
        def node = Node.findById(params?.nodeId)
        def scanBatch = new ScanBatch(userId: permissionsService.getUsername(), timestamp: new Date(), project: node.project)
        scanBatch.save()
        filesystemService.indexImage(node, UFile.findById(params?.ufileId), scanBatch)
        redirect controller: "capture", action: "upload", id: params?.projectId
    }

    def get() {
        // check for permissions here
        def image = Image.findById(params?.id)
        if(image) redirect controller: "download", action: "index", id: image.file.id
    }
}

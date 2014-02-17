package com.kumulus.controllers

import com.kumulus.domain.*
import com.lucastex.grails.fileuploader.*

class ImageController {

    def permissionsService
    def filesystemService
        
    def upload() {
        def node = Node.findById(params?.id)
        if(node) {
            def scanBatch = new ScanBatch(userId: permissionsService.getUsername(), timestamp: new Date(), project: node.project)
            scanBatch.save()
            filesystemService.indexImage(node, UFile.findById(params?.ufileId), scanBatch)
            redirect controller: "capture", action: "upload", id: node.project.id
        }
    }

    def get() {
        // check for permissions here
        def image = Image.findById(params?.id)
        if(image) redirect controller: "download", action: "index", id: image.file.id
    }
}

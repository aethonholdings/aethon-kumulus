package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*

@Secured(['ROLE_ADMIN', 'ROLE_PROCESS', 'ROLE_IMPORT', 'ROLE_REVIEW', 'ROLE_SUPERVISE', 'ROLE_VIEW'])
class FileController {

    def get() {
        // check for permissions here
        def image = Image.findById(params?.id)
        if(image) redirect controller: "download", action: "index", id: image.file.id
    }
}

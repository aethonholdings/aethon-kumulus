package com.kumulus.controllers

import com.kumulus.domain.*

class FileController {
    
    def permissionsService
    
    def get() {
        // check for permissions here
        def image = Image.findById(params?.id)
        if(image && permissionsService.checkPermisions(image)) redirect controller: "download", action: "index", id: image.file.id
    }
}

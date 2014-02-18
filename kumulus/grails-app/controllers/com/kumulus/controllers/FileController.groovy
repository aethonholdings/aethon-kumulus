package com.kumulus.controllers

import com.kumulus.domain.*

class FileController {

    
    def get() {
        // check for permissions here
        def image = Image.findById(params?.id)
        if(image) redirect controller: "download", action: "index", id: image.file.id
    }
}

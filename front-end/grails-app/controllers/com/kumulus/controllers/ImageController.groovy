package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*

class ImageController {

    @Secured(['ROLE_REVIEW'])
    def upload() { 
        render view:"upload", layout:"home"
    }
}

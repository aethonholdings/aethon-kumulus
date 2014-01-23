package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*

class DocumentController {

    @Secured(['ROLE_IMPORT'])
    def build() {
        
    }

}

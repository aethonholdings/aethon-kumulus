package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*
import grails.converters.*

class AccessController {

    @Secured(['ROLE_ACCESS'])
    def index() { 
        render view:"index", layout:"home"
    }
}

package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*

@Secured(['ROLE_REVIEW'])
class TaskController {

    // need securing based on user permissions
    def review() {
        def pages = Task.findById(params?.id).pages
        render view:"review", layout:"home", model:[pages: pages]
    }
    
    
}

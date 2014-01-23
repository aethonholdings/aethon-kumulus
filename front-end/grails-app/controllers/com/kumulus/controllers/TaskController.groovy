package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*

@Secured(['ROLE_REVIEW'])
class TaskController {

    // need securing based on user permissions
    def review() {
        def pages = []   
        def task = Task.findById(params?.id)
        render view:"review", layout:"home", model:[pages: pages]
    }
    
    def list() {
        
    }
    
    
}

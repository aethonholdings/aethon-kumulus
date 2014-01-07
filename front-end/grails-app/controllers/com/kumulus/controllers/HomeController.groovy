package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*
import static groovyx.net.http.ContentType.*

class HomeController {

    def springSecurityService
    
    @Secured(['ROLE_CLASSIFY', 'ROLE_ADMIN', 'ROLE_VIEW', 'ROLE_COLLECT', 'ROLE_EXPORT', 'ROLE_VALIDATE', 'ROLE_IMPORT'])
    def index() { 

        def auth = springSecurityService.getAuthentication()
        String username = auth.getPrincipal().getUsername()
        def tasks = Task.findByUserId(username)
        render view:"index", layout:"home", model:[user: username, tasks: tasks]
        
    }
    
    @Secured(['ROLE_VALIDATE'])
    def validate() {
        
        withHttp(uri: "http://test.ephesoft.kumulus.sg:8080") {
            
            def html = post(path : '/dcma/j_security_check',  body : [j_username: 'ephesoft', j_password: 'Ke$haIsGreat666'])
            render html
        
        }
    }
    
}
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
        def tasks = Task.findByOwnerLDAPUidAndStatus(username, "PENDING")

        render view:"index", layout:"main", model:[user: username, tasks: tasks]
        
    }
    
    
    @Secured(['ROLE_VALIDATE'])
    def validate() {
        
        withRest(uri: "http://www.google.com") {
            def html = get(path : '/search',  query : [q:'Groovy'], headers : [Accept : 'application/xml'], contentType : TEXT)
            render html.data.text
        }
        
    }
}
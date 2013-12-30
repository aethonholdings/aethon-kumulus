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
        
        withRest(uri: "http://test.ephesoft.kumulus.sg:8080") {
            def html = get(path : '/dcma/j_security_check',  headers : [Accept : 'application/xml'], contentType : TEXT)
            render html.data.text
        }
        render 'this is where we use REST HttpBuilder to log on and embed ephesoft<br>'
        render "see --> <a href='http://groovy.codehaus.org/modules/http-builder/doc/auth.html'>here</a>"

//        withHttp(uri: "http://test.ephesoft.kumulus.sg:8080") {
//            def html = post(path : '/dcma/j_security_check',  body : [j_username: 'kumulus', password: 'password'])
//            render html
//        }   
    }
}
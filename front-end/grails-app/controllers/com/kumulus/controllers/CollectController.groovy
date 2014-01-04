package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*

class CollectController {

    @Secured(['ROLE_COLLECT'])
    def index() { 

        def projectList = Project.getAll()
        render view:"index", layout:"home", model:[projects: projectList]
    
    }
}

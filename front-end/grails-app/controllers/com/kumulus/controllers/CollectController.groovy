package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*

class CollectController {

    @Secured(['ROLE_COLLECT'])
    def index() { 
        def projectList = Project.findAllByStatus("A")
        render view:"index", layout:"home", model:[projects: projectList]
    }
    
    @Secured(['ROLE_COLLECT'])
    def workflow() {
        // this is not secured at user permission level yet
        def nodeList = Nodes.findAllByProject(Project.findById(params.id))
        render view:"workflow", layout:"home", model:[nodes: nodeList]
    }
    
    @Secured(['ROLE_COLLECT'])
    def refreshTree() {
        
    }
    
}

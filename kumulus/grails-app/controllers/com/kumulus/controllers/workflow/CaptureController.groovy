package com.kumulus.controllers.workflow

import com.kumulus.domain.*

class CaptureController {

    def permissionsService

    def collect() { 
       def project = Project.findById(params?.id)
        if(permissionsService.checkPermissions(project)) {
            def nodeTypes = NodeType.findAll {
                isContainer==true
            }
            render view:"collect", model:[project: project, nodeTypes: nodeTypes]
        }
    }
    
    def upload() {
        def project = Project.findById(params?.id)
        if(permissionsService.checkPermissions(project)) render view:"upload", model:[project: project]
    }
    
    def build() {
        def taskList = []
        def project = Project.findById(params?.id)
        if(permissionsService.checkPermissions(project)) {
            taskList = Task.findAll(sort:"created", order: "asc") { 
                (project == project && type == Task.BUILD_DOCUMENT && userId == permissionsService.getUsername() && completed == null)
            }
        }
        render view: "build", model: [tasks: taskList]
    }
    
    def pickup (){
        
    }
}
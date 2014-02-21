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
        def workItem = WorkItem.findById(params?.id)
        if(workItem?.userId == permissionsService.getUsername()) {
            taskList = workItem.tasks.findAll { it.completed == null }
        }
        render view: "build", model: [tasks: taskList]
    }
    
    def pickup (){
        
    }
}
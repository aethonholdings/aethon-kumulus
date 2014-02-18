package com.kumulus.controllers.workflow

import com.kumulus.domain.*

class CaptureController {

    def permissionsService

    def collect() { 
        def project = Project.findById(params?.id)
        if(project.company == permissionsService.getCompany()) render view:"collect", model:[project: project]
    }
    
    def upload() {
        def project = Project.findById(params?.id)
        if(project?.company == permissionsService.getCompany()) render view:"upload", model:[project: project]
    }
    
    def build() {
    def documentList = []
    def project = Project.findById(params?.id)
    if(project) {
        Task.findAllByUserIdAndType(permissionsService.getUsername(), Task.BUILD_DOCUMENT).each { task ->
            if(task.document.project==project) documentList.add task.document            
        }
    }
    render view: "build", model: [documents: documentList]
    }
    
    def pickup (){
        
    }
}

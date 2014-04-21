package com.kumulus.controllers.workflow

import com.kumulus.domain.*

class CaptureController {

    def permissionsService
    def workflowService

    def home() {
        def projectList = Project.findAll {
            // company == permissionsService.getCompany()?.name                 // temporary centralised implementattion of upload functionality
            status == Project.STATUS_ACTIVE
        }
        def userTasks = workflowService.getTaskQueues(permissionsService.getUsername())
        render(view:"home", model:[pageTitle: "Home", projectList: projectList, userTasks: userTasks, userId: permissionsService.getUsername()])    
    }
    
    def upload() {
        def project = Project.findById(params?.id)
        // no permission checking for the time being here
        if(permissionsService.checkPermissions(project)) {
            def nodeTypes = NodeType.findAll {
                isContainer==true
            }
            render view:"upload", model:[project: project, nodeTypes: nodeTypes]
        }
    }
    
    def build() {
        def taskList = []
        def project = Project.findById(params?.projectId)
        if(permissionsService.checkPermissions(project)) {
            taskList = Task.findAll(sort:"created", order: "asc") { 
                (project == project && type == Task.TYPE_BUILD && userId == permissionsService.getUsername() && completed == null)
            }
        }
        render view: "build", model: [tasks: taskList]
    }
    
}
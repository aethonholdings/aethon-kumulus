package com.kumulus.controllers.workflow

import com.kumulus.domain.*

class CaptureController {

    def permissionsService
    def workflowService

    def home() {
        def projectList = Project.findAllByStatus(Project.STATUS_ACTIVE)
        def taskQueue = workflowService.getTaskQueues(permissionsService.getUsername())
        def userTasks = taskQueue.types.BUILD.groupBy { task -> task.document.project }
        render(view:"home", model:[pageTitle: "Home", scanCount: taskQueue.types.BUILD.size(), projectList: projectList, userTasks: userTasks, userId: permissionsService.getUsername()])    
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
        def project = Project.findById(params?.id)
        if(permissionsService.checkPermissions(project)) {
            taskList = Task.findAll(sort:"created", order: "asc") { 
                (project == project && type == Task.TYPE_BUILD && userId == permissionsService.getUsername() && completed == null)
            }
        }
        render view: "build", model: [tasks: taskList]
    }
    
}
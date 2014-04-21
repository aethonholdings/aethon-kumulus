package com.kumulus.controllers.workflow

import com.kumulus.domain.*

class CaptureController {

    def permissionsService
    def workflowService

    def home() {
        def projectList = Project.findAll {
            company == permissionsService.getCompany()?.name
            status == Project.STATUS_ACTIVE
        }
        def shipmentList=Shipment.findAll()
        def userTasks = workflowService.getTaskQueues(permissionsService.getUsername())
        def backOfficeTasks = workflowService.getTaskQueues(null)
        render(view:"home", model:[pageTitle: "Home", projectList: projectList,shipmentList:shipmentList, userTasks: userTasks, backOfficeTasks: backOfficeTasks, userId: permissionsService.getUsername()])    
    }
    
    def upload() {
        def project = Project.findById(params?.id)
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
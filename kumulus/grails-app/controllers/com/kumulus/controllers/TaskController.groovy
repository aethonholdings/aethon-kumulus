package com.kumulus.controllers

import com.kumulus.domain.*

class TaskController {
    
    def permissionsService
    
    def list() {
        def taskList = Task.findAllByUserIdAndType(permissionsService.getUsername(), params?.type, [sort: "created", order:"asc"])
        render view:"list", model: [tasks: taskList, title: params?.title]
    }
    
    def listGroupByProject() {
        def tasksByProject = [:]
        def projectList = []
        def taskList = Task.findAllByUserIdAndType(permissionsService.getUsername(), params?.type, [sort: "created", order:"asc"])
        taskList.each { task ->
            def project = task.document.project
            if(!tasksByProject.containsKey(project.id)) {
                def taskGroup = [:]
                projectList.add(project)
                taskGroup.put('project', project)
                taskGroup.put('tasks', [])
                tasksByProject.put(project.id, taskGroup)
            }
            tasksByProject[project.id].tasks.add(task)
        }
        
        render view:"listGroupByProject", model: [tasksByProject: tasksByProject, projectList: projectList, title: params?.title]
    }
    
    def getNext() {
        if(params?.taskType) {
            switch(params.taskType) {
                case Task.BUILD_DOCUMENT.toString():
                    def project = Project.findById(params?.projectId)
                    if(permissionsService.checkPermissions(project)) redirect controller: "capture", action: "build", id: project.id
                    break
                case Task.PROCESS_DOCUMENT.toString():
                    redirect controller: "structure", action: "process"
                    break
            }
        }
    }
    
        
}

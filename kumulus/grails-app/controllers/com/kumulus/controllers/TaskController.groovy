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
    
    def complete() {
        def workItem = WorkItem.findById(params?.id)
        if(workItem && workItem.completed==null && permissionsService.getUsername()==workItem.userId) {
            switch(workItem.type) {
                case(workItem.BUILD_DOCUMENT):
                    redirect controller: "capture", action: "build", id: workItem.id
                    break
                case(workItem.OCR_DOCUMENT):
                    redirect controller: "structure", action: "process", id: workItem.id
                    break
                case(workItem.VALIDATE):
                    break
            }
        }
    }
        
}

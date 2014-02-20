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
        def task = Task.findById(params?.id)
        if(task && task.status!=Task.FINISHED && permissionsService.getUsername()==task.userId) {
            switch(task.type) {
                case(Task.BUILD_DOCUMENT):
//                    redirect controller: "capture", action: "build", id: task.document.pages[0].node.project.id
                    break
                case(Task.OCR_DOCUMENT):
                    redirect controller: "structure", action: "process", id: params.id
                    break
                case(Task.VALIDATE):
                    break
            }
        }
    }
        
}

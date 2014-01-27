package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*

@Secured(['ROLE_REVIEW', 'ROLE_PROCESS'])
class TaskController {

    def userService
    def taskService
    
    // need securing based on user permissions
    def review() {
        def pages = []   
        def task = Task.findById(params?.id)
        render view:"review", layout:"home", model:[pages: pages]
    }
    
    def list() {
        def taskList = Task.findAllByUserIdAndType(userService.getUsername(), params?.type, [sort: "created", order:"asc"])
        render view:"list", layout: "home", model: [tasks: taskList, title: params?.title]
    }
    
    def listGroupByProject() {
        def tasksByProject = [:]
        def projectList = []
        def taskList = Task.findAllByUserIdAndType(userService.getUsername(), params?.type, [sort: "created", order:"asc"])
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
        
        render view:"listGroupByProject", layout: "home", model: [tasksByProject: tasksByProject, projectList: projectList, title: params?.title]
    }
    
    def perform() {
        if(params?.id) {
            def task = Task.findById(params.id)
            if(task?.userId==userService.getUsername()) {
                switch(task.type) {
                }
            }
        }
    }
        
}

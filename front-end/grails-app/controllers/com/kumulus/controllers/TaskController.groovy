package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*

@Secured(['ROLE_REVIEW'])
class TaskController {

    def userService
    
    // need securing based on user permissions
    def review() {
        def pages = []   
        def task = Task.findById(params?.id)
        render view:"review", layout:"home", model:[pages: pages]
    }
    
    def list() {
        def taskList
        if(params?.type) {
            switch(params.type.toUpperCase()) {
                case("BUILD"):
                    taskList = Task.findAllByUserIdAndType(userService.getUsername(), Task.BUILD_DOCUMENT, [sort: "created", order:"asc"])
                    break
            }
            render view:"list", layout: "home", model: [tasks: taskList, title: params?.title]
        }
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

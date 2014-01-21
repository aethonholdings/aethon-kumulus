package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*

@Secured(['ROLE_REVIEW'])
class TaskController {

    // need securing based on user permissions
    def review() {
        def pages = []   
        def task = Task.findById(params?.id)
        TaskItem.findAllByTask(task, [sort: "sequence", order: "asc"]).each { pages.add it.page }
        render view:"review", layout:"home", model:[pages: pages]
    }
    
    
}

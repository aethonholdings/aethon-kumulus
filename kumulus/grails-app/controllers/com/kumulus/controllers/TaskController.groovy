package com.kumulus.controllers

import com.kumulus.domain.*
import grails.converters.*

class TaskController {
    
    def accessService
    def permissionsService
    def workflowService

    def getNext() {
        def data = request.JSON
        def response = [
            success: false,
            data: [:]
        ]
        def task = workflowService.getNextTask(permissionsService.getUsername())
        if(task && permissionsService.checkPermissions(task)) {
            workflowService.assignTask(task, permissionsService.getUsername())
            workflowService.startTask(task)
            response.data = accessService.renderTask(task)
            response.success = true
        }
        render response as JSON
    }
    
}

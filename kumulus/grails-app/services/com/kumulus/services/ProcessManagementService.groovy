package com.kumulus.services

import com.kumulus.domain.*
import java.text.SimpleDateFormat
import grails.transaction.Transactional

@Transactional
class ProcessManagementService {

    def permissionsService

    def createTask(document, taskType, status) {
        def task = new Task(
            document: document,
            created: new Date(),
            userId: permissionsService.getUsername(),
            type: taskType, 
            status: status
        )
        task.save()
        return(task)
    }
}

package com.kumulus.services

import com.kumulus.domain.*
import java.text.SimpleDateFormat
import grails.transaction.Transactional

@Transactional
class TaskService {

    def createTask(document, taskType, userId) {
        println('here')
        def task = new Task(
            document: document,
            created: new Date(),
            userId: userId,
            type: taskType, 
            status: Task.CREATED
        )
        task.save()
        return(task)
    }
    
    def getTasks() {
        return(Task.findByUserId(getUsername()))
    }
    
    def closeTask() {
        
    }
}

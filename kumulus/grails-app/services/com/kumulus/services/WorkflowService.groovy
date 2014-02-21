package com.kumulus.services

import com.kumulus.domain.*
import java.text.SimpleDateFormat
import grails.transaction.Transactional

@Transactional
class WorkflowService {

    def createTask(document, taskType, userId) {
        
        def task = new Task(
            userId: userId,
            project: document.project,
            created: new Date(),
            document: document,
            type: taskType,
            status: Task.CREATED
        )
        task.save()
        return(task)
    }
    
    def getTasks() {
        return(Task.findByUserId(getUsername()))
    }
    
    def completeTask(task) {
        task?.completed = new Date()
        task.save()
        def workItem = task.workItem
        if(!Task.findAllByWorkItemAndCompleted(workItem, null).count) {
            workItem.completed = new Date()
            workItem.save()
        }
        return(task)
    }
}

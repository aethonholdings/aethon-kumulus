package com.kumulus.services

import com.kumulus.domain.*
import java.text.SimpleDateFormat
import grails.transaction.Transactional

@Transactional
class WorkflowService {

    def createTask(document, taskType, userId) {
        WorkItem workItem
        
        if(taskType==Task.BUILD_DOCUMENT){
            workItem = WorkItem.findByUserIdAndProject(userId, document?.project)
        }
        if(!workItem){            
            workItem = new WorkItem(
                userId: userId, 
                project: document.project,
                created: new Date()
            )
        }
        def task = new Task(
            document: document,
            created: new Date(),
            userId: userId,
            type: taskType, 
            status: Task.CREATED,
        )
        workItem.addToTasks(task)
        workItem.save()
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
    }
}

package com.kumulus.services

import com.kumulus.domain.*
import java.text.SimpleDateFormat
import grails.transaction.Transactional

@Transactional
class WorkflowService {

    def createTask(document, taskType, userId) {
        WorkItem workItem
        
        if(taskType==WorkItem.BUILD_DOCUMENT){
            workItem = WorkItem.findByUserIdAndProjectAndTypeAndCompleted(userId, document?.project, taskType, null)
        }
        
        if(!workItem){
            workItem = new WorkItem(
                userId: userId, 
                project: document.project,
                type: taskType,
                created: new Date()
            )
        }       
        def task = new Task(
            created: new Date(),
            document: document,
            status: Task.CREATED
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
        return(task)
    }
}

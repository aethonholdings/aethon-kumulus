package com.kumulus.services

import com.kumulus.domain.*
import java.text.SimpleDateFormat
import grails.transaction.Transactional

class WorkflowService {
    
    private def stateMap = {
        def map = [
            (Task.TYPE_BUILD): [create: Document.STATUS_IMPORTED, complete: Document.STATUS_BUILT, error: null],
            (Task.TYPE_OCR): [create: Document.STATUS_BUILT, complete: Document.STATUS_SUBMITTED, error: null],
            (Task.TYPE_OCR_RETRIEVE): [create: Document.STATUS_SUBMITTED, complete: Document.STATUS_SEARCHABLE, error: Document.STATUS_SUBMISSION_ERROR],
            (Task.TYPE_PROCESS): [create: Document.STATUS_SEARCHABLE, complete: Document.STATUS_PROCESSED, error: null],
            (Task.TYPE_VALIDATE): [create: Document.STATUS_PROCESSED, complete: Document.STATUS_FINAL, error: null]
        ]
    }
    
    def getTaskQueues(String userId) {
        def queues = [
            count: 0,
            types: [:]
        ]
        stateMap().each {
            def tasks = Task.findAllByUserIdAndTypeAndCompleted(userId, it.key, null, [sort:"created", order:"asc"])
            queues.types.put(it.key, [count: tasks.size(), tasks: tasks])
            queues.count += tasks.size().toLong()
        }
        return(queues)
    }
        
    class InconsistentStateException extends RuntimeException {
    }
    
    def createTask(Document document, String taskType, String createdByUserId) {
        if(stateMap().get(taskType)?.create==document.status) { 
            def task = new Task(
                project: document.project,
                created: new Date(),
                started: null,
                completed: null,
                createdBy: createdByUserId,
                userId: null,
                document: document,
                type: taskType,
                status: null
            )
            task.save()
            return(task)
        }
        else {
            throw new InconsistentStateException()
        }
    }
    
    def getNextTask(String taskType, String userId) {
        
        // check the user queue first 
        def task = Task.findByTypeAndCompletedAndUserId(taskType, null, userId, [order: "created", type: "asc"])
        
        // if there are no tasks in the user queue, check the back office queue 
        if(!task) task = Task.findByTypeAndCompletedAndUserId(taskType, null, null, [order: "created", type: "asc"])
                
        return(task)
    }
    
    def assignTask(Task task, String userId) {
        task.userId = userId
        task.save()
        return(task)
    }
    
    def startTask(Task task) {
        task.started = new Date()
        task.save()
        return(task)
    }
    
    def completeTask(Task task) {
        task.document.status = stateMap().get(task.type).complete
        task.document.save()
        task.completed = new Date()
        task.save()
        return(task)
    }
        
}

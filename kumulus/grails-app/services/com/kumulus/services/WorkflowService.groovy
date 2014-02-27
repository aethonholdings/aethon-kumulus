package com.kumulus.services

import com.kumulus.domain.*
import java.text.SimpleDateFormat
import grails.transaction.Transactional

@Transactional
class WorkflowService {
    
    private def stateMap = {
        def map = [
            (Task.TYPE_BUILD): [create: Document.STATUS_IMPORTED, complete: Document.STATUS_BUILT, error: null],
            (Task.TYPE_OCR): [create: Document.STATUS_BUILT, complete: Document.STATUS_SEARCHABLE, error: Document.STATUS_SUBMISSION_ERROR],
            (Task.TYPE_PROCESS): [create: Document.STATUS_SEARCHABLE, complete: Document.STATUS_PROCESSED, error: null],
            (Task.TYPE_VALIDATE): [create: Document.STATUS_PROCESSED, complete: Document.STATUS_FINAL, error: null]
        ]
    }

    def createTask(Document document, String taskType, String createdByUserId) {
        Task task
        
        if(stateMap().get(taskType)?.create==document.status) { 
            task = new Task(
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
        }
        return(task)
    }
    
    def openTaskSummary(String userId) {
        def taskSummary
        // query to get the set of tasks summarised by the database
        def criteria = Task.createCriteria()
        if(userId)
            taskSummary = criteria.list {
                eq("userId", userId)
                isNull("completed")
                projections {
                    sqlGroupProjection "project_id, type, count(id) as taskCount", "project_id, type", ["project_id", "type", "taskCount"], [LONG, STRING, LONG]
                }
                order("project", "asc")
                order("created", "asc")
            }
        else {
            taskSummary = criteria.list {
                isNull("completed")
                projections {
                    sqlGroupProjection "project_id, type, count(id) as taskCount", "project_id, type", ["project_id", "type", "taskCount"], [LONG, STRING, LONG]
                }
                order("project", "asc")
                order("created", "asc")
            }            
        }
        
        // package the tasks for consumption
        def tasks = [
            total: Task.findAllByUserIdAndCompleted(userId, null).size, 
            type: [
                (Task.TYPE_BUILD): [
                    total: 0,
                    list: []
                ], 
                (Task.TYPE_OCR): [
                    total: 0,
                    list: []                    
                ], 
                (Task.TYPE_PROCESS): [
                    total: 0,
                    list: []                    
                ],
                (Task.TYPE_REVIEW): [
                    total: 0,
                    list: []                    
                ], 
                (Task.TYPE_VALIDATE): [
                    total: 0,
                    list: []                    
                ]
            ]
        ]
        
        taskSummary.each { 
            def project = Project.findById(it[0])
            def row = [project: project, taskCount: it[2]]
            tasks.type.(it[1].toString()).total += row.taskCount
            tasks.type.(it[1].toString()).list.add(row)
        }
        return(tasks)
    }
    
    def getNextTask(String taskType) {
        def task
        switch(taskType) {
            case Task.TYPE_PROCESS.toString():
                task = Task.findByTypeAndCompleted(taskType, null, [order: "created", type: "asc"])
                break
        }
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

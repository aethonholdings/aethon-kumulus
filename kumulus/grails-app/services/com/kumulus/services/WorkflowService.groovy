package com.kumulus.services

import com.kumulus.domain.*
import java.text.SimpleDateFormat
import grails.transaction.Transactional

@Transactional
class WorkflowService {

    def createTask(Document document, String taskType, String createdByUserId) {
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
                isNull("userId")
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
                (Task.BUILD_DOCUMENT): [
                    total: 0,
                    list: []
                ], 
                (Task.OCR_DOCUMENT): [
                    total: 0,
                    list: []                    
                ], 
                (Task.PROCESS_DOCUMENT): [
                    total: 0,
                    list: []                    
                ],
                (Task.REVIEW_DOCUMENT): [
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
    
    def completeTask(Task task) {
        task.completed = new Date()
        task.save()
        return(task)
    }
    
    def assignTask(Task task, String userId) {
        task.userId = userId
        task.save()
    }
    
    def getNextTask(String taskType) {
        return(Task.findByTypeAndCompleted(taskType, null))
    }
    
}

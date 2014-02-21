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
    
    def taskSummary(userId) {
        def criteria = Task.createCriteria()
        
        // query to get the set of tasks summarised by the database
        def taskIds = criteria.list {
            eq("userId", userId)
            isNull("completed")
            projections {
                sqlGroupProjection "project_id, type, count(id) as taskCount", "project_id, type", ["project_id", "type", "taskCount"], [LONG, LONG, LONG]
            }
            order("project", "asc")
            order("created", "asc")
        }
        
        // package the tasks for consumption
        def tasks = []
        taskIds.each { 
            def project = Project.findById(it[0])
            def row = [project: project, taskType: it[1], taskCount: it[2]]
            tasks.add(row)
        }
        return(tasks)
    }
    
    def completeTask(task) {
        task.completed = new Date()
        task.save()
        return(task)
    }
    
    
}

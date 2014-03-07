/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kumulus.jobs

import com.kumulus.domain.Document
import com.kumulus.domain.Task

/**
 *
 * @author theo
 */
class ExtractTextJob {
    
    def filesystemService
    def workflowService

    static triggers = {
        simple name: 'Extract Job', startDelay: 0, repeatInterval: 10000  
    }

    def group = "Jobs"

    def execute() {
        for (doc in Document.findAll {status == Document.STATUS_SEARCHABLE && deleted == false}) {
            Document.withTransaction { trans ->
                def wtask = Task.find {document == doc && completed == null && type == Task.TYPE_PROCESS}
                workflowService.startTask(wtask)
                filesystemService.indexDocument(doc)
                workflowService.completeTask(wtask)
                workflowService.createTask(doc, Task.TYPE_VALIDATE, 'kumulus')
                doc.save()
            }
        }
    }
	
}

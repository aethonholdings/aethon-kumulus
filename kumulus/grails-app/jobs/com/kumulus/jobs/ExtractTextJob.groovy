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
    def concurrent = false

    static triggers = {
        simple name: 'Extract Job', startDelay: 0, repeatInterval: 10000
    }

    def group = "Jobs"

    def execute() {
        for (wtask in Task.findAllByTypeAndCompleted(Task.TYPE_OCR_EXTRACT, null)) {
            workflowService.startTask(wtask)
            def doc = wtask.document
            assert doc.status == Document.STATUS_SEARCHABLE
            def text = filesystemService.indexDocument(doc)
            Document.withTransaction { trans ->
                doc.text = text
                workflowService.completeTask(wtask)
                workflowService.createTask(doc, Task.TYPE_PROCESS, 'kumulus')
                doc.save()
            }
        }
    }
	
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author theo
 */
package com.kumulus.jobs

import com.abbyy.ocrsdk.Client
import com.kumulus.domain.Document

import com.kumulus.aws.SimpleNotificationService

class RetrieveDocumentJob {
    
    static triggers = {
        simple name: 'Retrieve Job', startDelay: 0, repeatInterval: 120000  
    }

    def group = "Jobs"

    def execute() {
        def client = new Client()
        // TODO: get parameters
        client.applicationId = ""
        client.password = ""

        // TODO: get parameters for constructor
        sns = SimpleNotificationService()

        // Retrieve searchable documents from ABBYY
        for (doc in Document.list(status: Document.SUBMITTED)) {
            def task = client.getTaskStatus(doc.ocrTask)
            if (task.isTaskActive()) {
                if (task.status == Task.TaskStatus.Completed) {
                    // TODO: construct output path
                    outputPath = ''
                    client.downloadResult(task, outputPath)
                    doc.file = outputPath
                    doc.status = Document.SEARCHABLE
                }
                else if (task.status == Task.TaskStatus.NotEnoughCredits) {
                    sns.sendEmail('ABBYY is not processing documents due to lack of funds!',
                                  'Buy additional pages and change the status of affected documents in database.')
                    doc.status = Document.SUBMISSION_ERROR
                }
                else {
                    sns.sendEmail('ABBYY reported an error while processing document ' + doc.identifier,
                                  'Resolve and change status of affected document in database.')
                    doc.status = Document.SUBMISSION_ERROR
                }
                doc.save(flush: true)
                // TODO: handle text extraction
            }
        }
    }
	
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kumulus.jobs

import com.abbyy.ocrsdk.Client
import com.abbyy.ocrsdk.ProcessingSettings
import com.kumulus.domain.Document
import com.kumulus.domain.Page
import com.kumulus.domain.Task

/**
 *
 * @author theo
 */
class SubmitDocumentJob {

    def grailsApplication
    def workflowService

    static triggers = {
        simple name: 'Submit Job', startDelay: 0, repeatInterval: 10000  
    }

    def group = "Jobs"

    def execute() {
        def client = new Client()
        client.applicationId = grailsApplication.config.abbyy.applicationId
        client.password = grailsApplication.config.abbyy.password

        def settings = new ProcessingSettings()
        settings.setLanguage('English')
        settings.setOutputFormat(ProcessingSettings.OutputFormat.pdfSearchable)

        // Submit documents with status STATUS_BUILT to ABBYY
        for (doc in Document.findAll {status == Document.STATUS_BUILT && deleted == false}) {
            def wtask = Task.find {document == doc && completed == null && type == Task.TYPE_OCR}
            workflowService.startTask(wtask)
            def task = null
            for (page in Page.findAll {document == doc}) {
                def filename = page.scanImage.file.path
                def id = (task == null) ? null : task.Id
                def result = client.submitImage(filename, id)
                if (result != null) { task = result }
            }
            task = client.processDocument(task.Id, settings)
            doc.ocrTask = task.Id
            workflowService.completeTask(wtask)
            workflowService.createTask(doc, Task.TYPE_OCR_RETRIEVE, 'kumulus')
            doc.save(flush: true)
        }
    }
}

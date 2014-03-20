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
    def concurrent = false

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
        for (wtask in Task.findAllByTypeAndCompleted(Task.TYPE_OCR, null)) {
            workflowService.startTask(wtask)
            def doc = wtask.document
            assert doc.status == Document.STATUS_BUILT
            def task = null
            for (page in Page.findAllByDocument(doc, [sort: "number", order: "asc"])) {
                def filename = page.scanImage.file.path
                def id = (task == null) ? null : task.Id
                def result = client.submitImage(filename, id)
                if (result != null) { task = result }
            }
            task = client.processDocument(task.Id, settings)
            Document.withTransaction { trans ->
                doc.ocrTask = task.Id
                workflowService.completeTask(wtask)
                workflowService.createTask(doc, Task.TYPE_OCR_RETRIEVE, 'kumulus')
                doc.save()
            }
        }
    }
}

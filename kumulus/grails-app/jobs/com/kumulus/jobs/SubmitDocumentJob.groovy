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

/**
 *
 * @author theo
 */
class SubmitDocumentJob {

    static triggers = {
        simple name: 'Submit Job', startDelay: 0, repeatInterval: 120000  
    }

    def group = "Jobs"

    def execute() {
        def client = new Client()
        // TODO: get parameters
        client.applicationId = ""
        client.password = ""

        def settings = new ProcessingSettings()
        settings.setLanguage('English')
        settings.setOutputFormat(ProcessingSettings.OutputFormat.pdfSearchable)

        // Submit documents with status FINAL to ABBYY
        for (doc in Document.list(status: Document.FINAL)) {
            def task = null
            for (page in Page.list(document: doc)) {
                def filename = page.scanImage.file.path
                def id = (task == null) ? null : task.Id
                def result = client.submitImage(filename, id)
                if (result != null) { task = result }
            }
            task = client.processDocument(task.Id, settings)
            doc.ocrTask = task.Id
            doc.status = Document.SUBMITTED
            doc.save(flush: true)
        }
    }
}

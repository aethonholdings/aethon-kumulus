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
class ManagerJob {

    static triggers = {
        simple name: 'Manager Job', startDelay: 0, repeatInterval: 120000  
    }

    def group = "Manager Jobs"

    def execute() {
        def client = new Client()
        client.applicationId = ""
        client.password = ""
        
        def settings = new ProcessingSettings()
        settings.setLanguage('English')
        settings.setOutputFormat(ProcessingSettings.OutputFormat.pdfSearchable)

        for (doc in Document.list(status: Document.FINAL)) {
            def task = null
            for (page in Page.list(document: doc)) {
                def file = page.scanImage.file
                def filename = file.path + '/' + file.name + '.' + file.extension
                def id = (task == null) ? null : task.Id
                def result = client.submitImage(filename, id)
                if (result != null) { task = result }
            }
            task = client.processDocument(task.Id, settings)
            document.ocr_task = task.Id
            document.save(flush: true)
        }
    }
}

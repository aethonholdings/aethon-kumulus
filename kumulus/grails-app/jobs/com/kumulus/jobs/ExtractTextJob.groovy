/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kumulus.jobs

import com.kumulus.domain.Document

/**
 *
 * @author theo
 */
class ExtractTextJob {
    
    def filesystemService

    static triggers = {
        simple name: 'Extract Job', startDelay: 0, repeatInterval: 10000  
    }

    def group = "Jobs"

    def execute() {
        for (doc in Document.findAll {status == Document.STATUS_SEARCHABLE && deleted == false}) {
            filesystemService.indexDocument(doc)
            doc.status = Document.STATUS_PROCESSED
            doc.save(flush: true)
        }
    }
	
}

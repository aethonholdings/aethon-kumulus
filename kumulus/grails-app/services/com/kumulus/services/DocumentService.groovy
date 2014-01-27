package com.kumulus.services

import grails.transaction.Transactional
import com.kumulus.domain.*

@Transactional
class DocumentService {

    def filesystemService
    def userService
    
    def merge(documents) {
        
        Document newDocument
        
        // first check the document input dataset to ensure it is processable
        boolean validData = true
        Project checkProject = null

        documents?.each { 
            if(validData) {
                if(it.status!=Document.EDITABLE) validData = false  // check if all documents are editable
                if(it.project != checkProject && checkProject != null) validData = false else checkProject = it.project // check if all documents belong to the same project
            }
            // should have a break here?
        }
        if(validData && checkProject) {
            if(documents?.size > 1) {
                // create a new document
                newDocument = new Document(
                    literal: filesystemService.generateLiteral(),
                    status: Document.EDITABLE,
                    project: checkProject
                )
                newDocument.save()

                // cycle through the pages to build the document
                def pages = []
                long pageCount = 0
                documents.each { document ->
                    document.pages.each { page ->
                        pages.add(page)
                        pageCount++
                    }
                }

                pages.eachWithIndex { page, i ->
                    // for each page, update the page domain class
                    page.document = newDocument
                    if(i==0) page.first = true else page.first = false
                    if(i==pageCount-1) page.last = true else page.last = false
                    page.number = i+1
                    page.save()
                }

                // delete the old documents and tasks
                documents.each { document ->
                    Task.findByDocumentAndType(document, Task.BUILD_DOCUMENT)?.delete()
                    document.pages = []
                    document.delete()
                }
            } else if (documents?.size == 1) {
                // no processing of the document needed
                newDocument = documents[0]
                Task.findByDocumentAndType(newDocument, Task.BUILD_DOCUMENT)?.delete()
            }
        } 
        return(newDocument)
    }
    
    def createTask(document, taskType, status) {
        def task = new Task(
            document: document,
            created: new Date(),
            userId: userService.getUsername(),
            type: taskType, 
            status: status
        )
        task.save()
        return(task)
    }
    
}



package com.kumulus.taglibs

import com.kumulus.domain.*

class KumulusTagLib {
    
    def permissionsService
    def workflowService
    
    static defaultEncodeAs = 'text'
    //static encodeAsForTags = [tagName: 'raw']
        
    def userCompany = { attrs, body ->
        String company = permissionsService.company
        if(company) {
            out << company
        }
    }
    
    def pageTitle = { attrs, body ->
        if(attrs.text) {
            out << attrs.text
        }
    }
    
    def kumulusImg = { attrs ->
        
        if(attrs.image) {
            
            def imgHeight = new java.math.BigDecimal(attrs.image.height)
            def imgWidth = new java.math.BigDecimal(attrs.image.width)
            def imgRatio = imgHeight/imgWidth

            def height = new java.math.BigDecimal(attrs?.height)
            def width = new java.math.BigDecimal(attrs?.width)

            def outputHeight
            def outputWidth

            if(height/width>imgRatio) {
                // height is the constraint
                outputHeight = height
                outputWidth = Math.round(height/imgRatio)
            } else {
                // width is the constraint
                outputHeight = Math.round(width*imgRatio)
                outputWidth = width
            }

            out << "<img "
            attrs.each { key, value ->
                if(key!="image") out << key << "='${value}' "
            }
            out << "src='${request.contextPath}/image/get/${attrs.image.id}' "
            out << "/>"
        }
    }
    
    def taskDescription = { attrs, body ->
        switch(attrs?.task.type) {
            case(Task.TYPE_BUILD):
                out << "Build document"
                break
            case(Task.TYPE_OCR):
                out << "OCR document"
                break
            case(Task.TYPE_REVIEW):
                out << "Review document"
                break
        }
    }
    
    def taskQueue = { attrs ->
        
        def userTasks = workflowService.getTaskQueues(attrs?.userId)            
        out << "<ul class='kumulus-task-queue'>"
        out << "    <li class='kumulus-task-queue'>${userTasks.types.BUILD.count} scans to be assembled into documents</li>"
        if(userTasks.types.BUILD.count > 0) {
            out << "        <ul>"
            userTasks.types.BUILD.tasks.groupBy({ task -> task.project }).each {
                if(it.value.size()>0) {
                    out << "<li  class='kumulus-task-queue-project'>"
                    out << "<div class='kumulus-task-queue-project-name'>${it.key.projectName}</div>"
                    out << "<div class='kumulus-task-queue-project-count'>" << g.link(controller: "capture", action: "build", params: [projectId: it.key.id], "${it.value.size()}") << "</div>"
                    out << "</li>"
                }
            }
        }
        if(userTasks.types.BUILD.count > 0) out << "        </ul>"
        out << "    <li  class='kumulus-task-queue'>${userTasks.types.PROCESS.count} documents to be processed"
        if(userTasks.types.PROCESS.count > 0) {
            out << " - "
            out << g.link(controller: "structure", action: "process", "Perform next")
        }
        out << "</li>"
        out << "    <li class='kumulus-task-queue'>${userTasks.types.VALIDATE.count} documents to be reviewed"
        out << "</ul>"          
    }
    
}

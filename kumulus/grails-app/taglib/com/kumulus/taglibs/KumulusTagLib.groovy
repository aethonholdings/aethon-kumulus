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
        out << "<ul class='kumulus-task-queue'>\n"
        out << "    <li class='kumulus-task-queue'>${userTasks.types.BUILD.count} scans to be assembled into documents</li>\n"
        if(userTasks.types.BUILD.count > 0) {
            out << "        <ul>\n"
            userTasks.types.BUILD.tasks.groupBy({ task -> task.project }).each {
                if(it.value.size()>0) {
                    out << "<li class='kumulus-task-queue-project kumulus-task-queue-action-item'>\n"
                    out << "<span class='kumulus-task-queue-project-name'>${it.key.projectName}</span>\n"
                    out << "<span class='kumulus-task-queue-project-count'>" << it.value.size() << "</span>\n"
                    out << "<span class='kumulus-task-queue-action'>" << g.link(controller: "capture", action: "build", params: [projectId: it.key.id], "Action") << "</span>\n"
                    out << "</li>\n"
                }
            }
            out << "        </ul>"
        }
        if(userTasks.types.PROCESS.count > 0) {
            out << "    <li class='kumulus-task-queue kumulus-task-queue-action-item'>${userTasks.types.PROCESS.count} documents to be processed"
            out << "<span class='kumulus-task-queue-action'>" << g.link(controller: "structure", action: "getNextTask", "Action") << "</span>"
        } else {
            out << "    <li class='kumulus-task-queue'>${userTasks.types.PROCESS.count} documents to be processed"
        }
        out << "</li>\n"
        out << "    <li class='kumulus-task-queue'>${userTasks.types.VALIDATE.count} documents to be reviewed"
        out << "</ul>\n"          
    }
    
    def searchResult = { attrs ->
        def nodeResult=null
        def docResult=null
        out<<"<div class='kumulus-search-result'>"
        switch(attrs?.result.class){
            case com.kumulus.domain.Node:
                def node = Node.findById(attrs?.result.id)
                while(node!=null){
                   if(nodeResult==null){
                      nodeResult = node.name
                   }
                   else{
                        nodeResult = node.name+" >> "+nodeResult
                   }
                   node=node.parent
                }
                out<<"<p><h4>"+Node.findById(attrs?.result.id).project.projectName+" >> "+nodeResult+"</h4></p>"
                out<<"<p>"+Node.findById(attrs?.result.id).type.name+"</p>"
                break;
            case com.kumulus.domain.Document:
                def doc = Document.findById(attrs?.result.id)
                def node=doc.pages[0].node.parent
                while(node!=null)
                {
                   if(docResult==null){
                       docResult = node.name
                   }
                   else{
                        docResult = node.name+" >> "+docResult
                   }
                   node=node.parent
                 }
                 out<<"<p><h4>"+doc.pages[0].node.parent.project.projectName +" >> "+docResult+" >> <a href='#'>"+ doc.literal+"</a> </h4></p>"
                 out<<"<p>"+doc.type.name+"</p>"
                 out<<"<p>"+doc.text+"</p>"
                 break;
        }
        out<<"</div>"        
    }
}

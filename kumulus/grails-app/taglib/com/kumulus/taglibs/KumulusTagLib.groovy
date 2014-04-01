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
        
        // build queue
        out << "\n<table class='kumulus-task-queue'>\n"
        out << "\t<tr>\n"
        out << "\t\t<td>Scans to be assembled into documents</td>\n"
        out << "\t\t<td class='kumulus-task-count'>${userTasks.types.BUILD.count}</td>\n"
        out << "\t\t<td></td>\n"
        out << "\t</tr>\n"
        if(userTasks.types.BUILD.count > 0) {
            userTasks.types.BUILD.tasks.groupBy({ task -> task.project }).each {
                if(it.value.size()>0) {
                    out << "\t<tr class='kumulus-task-queue-action-item'>\n"
                    out << "\t\t<td><span class='kumulus-task-queue-project-name'>${it.key.projectName}</span></td>\n"
                    out << "\t\t<td class='kumulus-task-count'>${it.value.size()}</td>\n"
                    out << "\t\t<td class='kumulus-task-queue-action'>" << g.link(controller: "capture", action: "build", params: [projectId: it.key.id], "Action") << "</td>\n"
                    out << "\t</tr>\n"
                }
            }
        }
        
        // OCR queue
        def taskCount = userTasks.types.OCR.count + userTasks.types.RETRIEVE.count + userTasks.types.EXTRACT.count
        out << "\t<tr>\n"         
        out << "\t\t<td>Documents in OCR queue</td>\n"
        out << "\t\t<td class='kumulus-task-count'>${taskCount}</td>\n"
        out << "\t\t<td class='kumulus-task-queue-action'></td>"
        out << "\t</tr>\n"
        
        // process queue
        if(userTasks.types.PROCESS.count>0) out << "\t<tr class='kumulus-task-queue-action-item'>\n" else out << "\t<tr>\n"         
        out << "\t\t<td>Documents to be processed</td>\n"
        out << "\t\t<td class='kumulus-task-count'>${userTasks.types.PROCESS.count}</td>\n"
        if(userTasks.types.PROCESS.count > 0) {
            out << "\t\t<td class='kumulus-task-queue-action'>" << g.link(controller: "structure", action: "getNextTask", params:[type: "${Task.TYPE_PROCESS}"], "Action") << "</td>\n"
        } else {
            out << "\t\t<td class='kumulus-task-queue-action'></td>"
        }
        out << "\t</tr>\n"
        
        // validation queue
        if(userTasks.types.VALIDATE.count>0) out << "\t<tr class='kumulus-task-queue-action-item'>\n" else out << "\t<tr>\n" 
        out << "\t\t<td>Documents to be validated</td>\n"
        out << "\t\t<td class='kumulus-task-count'>${userTasks.types.VALIDATE.count}</td>\n"
        if(userTasks.types.VALIDATE.count > 0) {
            out << "\t\t<td class='kumulus-task-queue-action'>" << g.link(controller: "structure", action: "getNextTask", params:[type: "${Task.TYPE_VALIDATE}"], "Action") << "</td>\n"
        } else {
            out << "\t\t<td class='kumulus-task-queue-action'></td>"
        }
        out << "</table>\n"       
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

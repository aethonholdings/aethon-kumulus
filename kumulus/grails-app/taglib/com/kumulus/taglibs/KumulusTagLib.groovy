package com.kumulus.taglibs

import com.kumulus.domain.*

class KumulusTagLib {
    
    def permissionsService
    def workflowService
    
    static defaultEncodeAs = 'text'
    //static encodeAsForTags = [tagName: 'raw']
        
    def userCompany = { attrs, body ->
        def company = permissionsService.getCompany()
        if(company) {
            out << company.name
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
    
    def viewImage = {attrs ->
        if(attrs?.image) {
            out << "<html>"
            out << "\t<head>"
            out << "\t</head>"
            out << "\t<body>"
            out << "\t\t<img  src='${request.contextPath}/image/get/${attrs.image.id}'>"
            out << "\t</body>"
            out << "</html>"
        }
    }
    
    def viewDocument = {attrs ->
        if(attrs?.document) {
            out << "<html>"
            out << "\t<head>"
            out << "\t</head>"
            out << "\t<body>"
            out << "<object width='100%' height='100%' type='application/pdf' src='${request.contextPath}/document/get/${attrs.document.id}'>"
            out << "\t</body>"
            out << "</html>"
        }
    }
}

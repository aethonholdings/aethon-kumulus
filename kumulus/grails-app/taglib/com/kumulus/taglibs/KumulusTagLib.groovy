package com.kumulus.taglibs

import com.kumulus.domain.*

class KumulusTagLib {
    static defaultEncodeAs = 'text'
    //static encodeAsForTags = [tagName: 'raw']
    
    def permissionsService
        
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
            case(Task.BUILD_DOCUMENT):
                out << "Build document"
                break
            case(Task.OCR_DOCUMENT):
                out << "OCR document"
                break
            case(Task.REVIEW_DOCUMENT):
                out << "Review document"
                break
        }
    }
    
}

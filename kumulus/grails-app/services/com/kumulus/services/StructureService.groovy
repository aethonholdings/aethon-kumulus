package com.kumulus.services

import com.kumulus.domain.*
import java.text.SimpleDateFormat
import grails.transaction.Transactional

@Transactional
class StructureService {
    
    def filesystemService
    def permissionsService
    def grailsApplication
    
    def update(document, data) {
        def documentType = DocumentType.findByName(data?.documentType)        
        Date date
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(grailsApplication.config.kumulus.dateFormat)
        if(data?.date && simpleDateFormat.parse(data?.date)) date = new Date(data?.date)
        if(document && documentType) {
            // bind the document data
            bindData(document, [
                company: data?.company,
                date: date
            ])

            // bind the line item data
            data?.lineItems.each { it ->
                def lineItem = LineItem.findById(it?.id)
                if(lineItem) {
                    
                } else {
                    
                }
            }
        }
    }

}

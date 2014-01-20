package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*

@Secured(['ROLE_REVIEW', 'ROLE_ACCESS'])
class DocumentController {

    // need securing based on user permissions
    
    def review() {
        def documents = []
        Task?.findById(params?.id).nodes.each { node ->
            Document.findAllByNode(node).each { document ->
                documents.add document
            }
        }
        render view:"review", layout:"home", model:[documents: documents]
    }
        
}

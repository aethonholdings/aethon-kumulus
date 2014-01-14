package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*
import grails.converters.*

class TreeRenderController {
    
    def treeRenderService
    
    @Secured(['ROLE_COLLECT', 'ROLE_ACCESS'])
    def getRoot() {
        def rootNode = treeRenderService.renderRoot(Project?.findById(params?.id))    
        render rootNode as JSON  
    }    
    
    @Secured(['ROLE_COLLECT', 'ROLE_ACCESS'])
    def getChildren() {
        def treeNodes = []
        def node = Nodes.findById(params?.id)         // should check permissions first
        if (node) {
            def children = Nodes.findAllByParent(node)
            children.each { if(it?.type!='D') treeNodes.add(treeRenderService.renderNode(it)) }
        }
        render treeNodes as JSON  
    }
}

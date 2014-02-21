package com.kumulus.controllers.workflow


import grails.converters.*
import com.kumulus.domain.*
class ScanDoController {
    
    def authenticate() {
     println("Calling from scanDo"+params)
     def results = "true"
     def responseData = [
    'results': results,
     ]
       render responseData as JSON  
    }
    
    def fetchProjectList(){     
        def projectlist =Project.list()        
        println("*******************project List"+projectlist)
         def responseData=[:]
         int i=1
         projectlist.each{
         responseData = [
          i: it.projectName           
        ] 
        i++
         }        
       render responseData as JSON  
    }
    
    
    def updateNodeProperties() { }
    
    def fetchChildNodeList() {
    
    }
    
    def updateNodePropertiesList() { }
    
    def getHierarchyFromSearchBarcode() { }
    
    def saveScannedImages() { }
    
    def checkIfNodeIsUpdatedByOtherUser() { }
    
    def getChildNodeCount() { }
    
    def getEncodedActualImageString() { }
    
}

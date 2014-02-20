package com.kumulus.controllers.workflow

import grails.converters.*

class ScanDoController {
    
    def authenticate() {
        def response = "true"
        render response as JSON
    }
    
    def updateNodeProperties() { }
    
    def fetchChildNodeList() { }
    
    def updateNodePropertiesList() { }
    
    def getHierarchyFromSearchBarcode() { }
    
    def saveScannedImages() { }
    
    def checkIfNodeIsUpdatedByOtherUser() { }
    
    def getChildNodeCount() { }
    
    def getEncodedActualImageString() { }
    
}

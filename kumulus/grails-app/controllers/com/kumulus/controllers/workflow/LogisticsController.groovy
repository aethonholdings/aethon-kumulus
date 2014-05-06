package com.kumulus.controllers.workflow

import com.kumulus.domain.*

class LogisticsController {

    def logisticsService
    def permissionsService
    
    def index() {
        redirect(controller: "home")
    }
    
    def home() {
        render(view:"home", model:[userId: permissionsService.getUsername()])    
    }
    
    def schedule() {
        def flaggedNodes = logisticsService.getFlaggedNodes()
        def shipments = logisticsService.getSchedule()
        def today = (new Date()).clearTime()
        def calendar = []
        (today..(today+6)).each { date ->
            if(date.format("EEE")!="Sat" && date.format("EEE")!="Sun") calendar.add(date)
        }
        render(view:"schedule", model:[flaggedNodes: flaggedNodes, shipments: shipments, userId: permissionsService.getUsername(), calendar: calendar])
    }
    
    def pickup() {
        
    }
    
    def store() {
        render(view:"store", model:[userId: permissionsService.getUsername()])    
    }
    
    def retrieve() {
        render(view:"retrieve", model:[userId: permissionsService.getUsername()])    
    }
    
    def generateBarcodeSheet() {     
        def barcodes = []
        for(i in 0..29) { barcodes.add(logisticsService.generateBarcode()) }
        render view: "generateBarcodeSheet", model: [barcodes: barcodes]
    }
    
    def barcode() {
        renderBarcodePng("code39Generator", Barcode.findById(params?.id).text)
    }
}       


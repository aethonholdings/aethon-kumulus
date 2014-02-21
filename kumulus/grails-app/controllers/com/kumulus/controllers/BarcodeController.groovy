package com.kumulus.controllers

import com.kumulus.domain.*
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class BarcodeController {

    def permissionsService
    
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    def logisticsService

    @Transactional
    def generate() {
        def company = permissionsService.getCompany()
        
    }
}

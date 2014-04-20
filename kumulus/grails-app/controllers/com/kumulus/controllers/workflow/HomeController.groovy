package com.kumulus.controllers.workflow

import com.kumulus.domain.*

class HomeController {
    
    def permissionsService
    def workflowService
    
    def index() { 
        // permissionsService.getUserCategory()
        redirect (controller: "customer", action: "home")
    }
}
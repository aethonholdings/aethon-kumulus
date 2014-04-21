package com.kumulus.controllers.workflow

import com.kumulus.domain.*

class HomeController {
    
    def permissionsService
    def workflowService
    
    def index() { 
        String category = permissionsService.getUserCategory()
        switch(category) {
            case "CUSTOMER":
                redirect (controller: "customer", action: "home")
                break;
            case "CAPTURE":
                redirect (controller: "capture", action: "home")    
                break;
            case "BACK_OFFICE":
                redirect (controller: "backOffice", action: "home")
                break;
            case "ADMIN":
                redirect (controller: "customer", action: "home")
                break;
        }
        
        
    }
}
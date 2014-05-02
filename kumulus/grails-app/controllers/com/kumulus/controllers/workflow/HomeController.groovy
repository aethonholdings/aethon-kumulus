package com.kumulus.controllers.workflow

import com.kumulus.domain.*

class HomeController {
    
    def permissionsService
    def workflowService
    
    def index() { 
        // redirect to the appropriate homepage for the user category
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
                redirect (controller: "admin", action: "home")
                break;
            case "LOGISTICS":
                redirect (controller: "logistics", action: "home")
                break;
        }
        
        
    }
}
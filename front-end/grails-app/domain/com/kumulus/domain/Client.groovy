package com.kumulus.domain

class Client {

    Company company
    User accountManager
    
    static hasMany = [projects: Project, 
                        users: User]
    
    static constraints = {
    }
}

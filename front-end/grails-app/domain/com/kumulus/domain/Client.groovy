package com.kumulus.domain

class Client {

    String companyName
    String address
    User accountManager
    
    static hasMany = [projects: Project, 
                        users: User]
    
    static constraints = {
    }
}

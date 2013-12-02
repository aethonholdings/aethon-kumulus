package com.kumulus.domain

class Project {

	String projectName
	String status
        Client client

	static hasMany = [applicationParameters: ApplicationParameter,
	                  attendances: Attendance,
	                  nodes: Nodes]
                      
        static belongsTo = [Client]

	static mapping = {
		id column: "project_id"
		version false
	}

	static constraints = {
		projectName nullable: true, maxSize: 50, unique: true
		status nullable: true, maxSize: 10
	}
}

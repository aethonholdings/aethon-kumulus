package com.kumulus.domain

class User {

	String userId
	String useridPassword
	String status
	String collectionRight
	String importRight
	String separationRight
	Integer importKpiTarget
	Integer separationKpiTarget

	static hasMany = [attendances: Attendance,
	                  nodeses: Nodes]

	static mapping = {
		id name: "userId", generator: "assigned"
		version false
	}

	static constraints = {
		userId maxSize: 45
		useridPassword nullable: true, maxSize: 100
		status nullable: true, maxSize: 10
		collectionRight nullable: true, maxSize: 1
		importRight nullable: true, maxSize: 1
		separationRight nullable: true, maxSize: 1
		importKpiTarget nullable: true
		separationKpiTarget nullable: true
	}
}

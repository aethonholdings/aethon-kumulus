package com.kumulus.domain

class Attendance {

	Date loginFromTime
	Date loginToTime
	User user
	Project project

	static belongsTo = [Project, User]

	static mapping = {
		id column: "attendance_id"
		version false
	}

	static constraints = {
		loginFromTime nullable: true
		loginToTime nullable: true
	}
}

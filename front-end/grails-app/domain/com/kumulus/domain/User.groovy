package com.kumulus.domain

class User {

        // Spring definitions
	transient springSecurityService

	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static transients = ['springSecurityService']

        // scando definitions
        String userId
	String useridPassword
	String status
        String name
        String telephone
        String email

	String collectionRight
	String importRight
	String separationRight
	Integer importKpiTarget
	Integer separationKpiTarget

        static hasMany = [attendances: Attendance, tasks: Task]
                      
        static belongsTo = [company: Company]
        
        // mappings and constraints
	static constraints = {
                // Spring constraints
		username blank: false, unique: true
		password blank: false
                
                // ScanDo constraints
                userId maxSize: 45
		useridPassword nullable: true, maxSize: 100
		status nullable: true, maxSize: 10
		collectionRight nullable: true, maxSize: 1
		importRight nullable: true, maxSize: 1
		separationRight nullable: true, maxSize: 1
		importKpiTarget nullable: true
		separationKpiTarget nullable: true
	}

	static mapping = {
                // Spring mappings
		password column: '`password`'
                
                // Scando mappings
                id name: "userId", generator: "assigned"
		version false
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}

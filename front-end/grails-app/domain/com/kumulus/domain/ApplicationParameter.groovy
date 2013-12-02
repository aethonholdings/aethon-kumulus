package com.kumulus.domain

class ApplicationParameter {

	String paramName
	String paramVal
    
	static belongsTo = [project: Project]

	static mapping = {
		id column: "param_id"
                paramName column: "param_name"
                paramVale column: "param_val"
		version false
	}
        
	static constraints = {
		paramName nullable: true, maxSize: 50
		paramVal nullable: true, maxSize: 150
	}
}

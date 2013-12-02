package com.kumulus.domain

class ParamNames {

	String param

	static mapping = {
		id name: "param", generator: "assigned"
		version false
	}

	static constraints = {
		param maxSize: 20
	}
}

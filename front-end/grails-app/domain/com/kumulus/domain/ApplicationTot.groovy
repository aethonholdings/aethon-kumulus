package com.kumulus.domain

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class ApplicationTot implements Serializable {

	String objectType
	String objectName
	String objectValue

	int hashCode() {
		def builder = new HashCodeBuilder()
		builder.append objectType
		builder.append objectName
		builder.append objectValue
		builder.toHashCode()
	}

	boolean equals(other) {
		if (other == null) return false
		def builder = new EqualsBuilder()
		builder.append objectType, other.objectType
		builder.append objectName, other.objectName
		builder.append objectValue, other.objectValue
		builder.isEquals()
	}

	static mapping = {
		id composite: ["objectType", "objectName", "objectValue"]
		version false
	}

	static constraints = {
		objectType nullable: true, maxSize: 45
		objectName nullable: true, maxSize: 45
		objectValue nullable: true, maxSize: 45
	}
}

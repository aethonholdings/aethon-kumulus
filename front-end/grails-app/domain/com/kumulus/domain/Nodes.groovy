package com.kumulus.domain

class Nodes {

	String name
	String type
	String barcode
	String comment
	String internalComment
	String status
	String hierarchy
	String thumbnailImageName
	String actualImageName
	String creatorId
	Date createDatetime
	Date lastUpdateDatetime
	Integer documentSequenceNumber
	Boolean uploaded
        
        // relationships
	Nodes parent
	User user
	Project project

	static belongsTo = [Project, User, Nodes]

	static mapping = {
		id column: "node_id"
                parent column: "parent_node_id"
		version false
	}

	static constraints = {
		name nullable: true, maxSize: 45
		type nullable: true, maxSize: 45
		barcode nullable: true, maxSize: 45
		comment nullable: true, maxSize: 200
		internalComment nullable: true, maxSize: 200
		status nullable: true, maxSize: 10
		hierarchy nullable: true, maxSize: 200
		thumbnailImageName nullable: true, maxSize: 1000
		actualImageName nullable: true, maxSize: 1000
		creatorId nullable: true, maxSize: 45
		createDatetime nullable: true
		lastUpdateDatetime nullable: true
		documentSequenceNumber nullable: true
		uploaded nullable: true
	}
}

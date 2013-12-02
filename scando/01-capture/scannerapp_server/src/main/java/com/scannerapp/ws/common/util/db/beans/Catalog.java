package com.scannerapp.ws.common.util.db.beans;

import java.util.HashMap;

import com.scannerapp.ws.common.util.db.DbConstant;


public abstract class Catalog {

	private String operation = DbConstant.AND_OPRN;

	/**
	 * Returns the operation of the Catalog class
	 * 
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * Sets the operation of the Catalog class
	 * 
	 * @param operation
	 *            the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	public abstract HashMap<String, String> getFieldMappings();

}

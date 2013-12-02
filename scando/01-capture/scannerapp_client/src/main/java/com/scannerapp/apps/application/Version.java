package com.scannerapp.apps.application;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2002
 * </p>
 * <p>
 * Company: System Plus P. Ltd
 * </p>
 * 
 * @author Kanhaiya Samariya
 * @version 1.0
 */
public class Version {
	protected static Version version = null;

	protected Version() {
		super();
	}

	public static Version getInstance() {
		if (version == null) {
			version = new Version();
		}
		return version;
	}

	public String toString() {
		return "v1.0"; // Updated for QA ISSUE#3850
	}
}

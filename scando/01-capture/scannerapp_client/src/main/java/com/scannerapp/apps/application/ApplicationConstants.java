package com.scannerapp.apps.application;

import java.io.File;

public interface ApplicationConstants {

	String RESOURCE_DIR = "resources";
	String PROPERTIES_DIR = "resources" + File.separator + "properties";
	String IMAGE_DIR = "resources" + File.separator + "images";

	String APP_PROPERTY_FILE = PROPERTIES_DIR + File.separator
			+ "application.properties";
	String ERR_PROPERTY_FILE = PROPERTIES_DIR + File.separator + "errorMessage.properties";
	String HELP_PROPERTY_FILE = PROPERTIES_DIR + File.separator + "tabHelp.properties";

	// ********************** UNUSED **********************
	public String DEFAULT_LANGUAGE = "e030";
	public String ROMANIAN_LANGUAGE = "r070";
	public String GREEK_LANGUAGE = "g060";
	public String DEFAULT_LANGUAGE_CODE = "030";
	public String ROMANIAN_LANGUAGE_CODE = "070";
	public String GREEK_LANGUAGE_CODE = "060";
	public String DEFAULT_USER = "IM";
	// ********************** UNUSED **********************
}

package com.scannerapp.ws.common.util.configuration;

import java.io.File;

/**
 * This class contains various constants required across the application
 * 
 * @author Amit Patel <amit.patel@spec-india.com>
 * @since Jan 23, 2012
 */

public interface ApplicationConstants {

	public static final String APP_RESOURCE_PATH = "resources";
	public static final String MESSAGES_DIR_PATH = "message";

	public static final String DB_MESSAGES_PATH = MESSAGES_DIR_PATH
			+ File.separator + "db";
	public static final String DB_MESSAGES_FILE = DB_MESSAGES_PATH
			+ File.separator + "error.properties";

	public static final String DB_CONN_CONST_FILE = APP_RESOURCE_PATH
			+ File.separator + "dbconnection.properties";
	public static final String CONF_CONST_FILE = APP_RESOURCE_PATH
			+ File.separator + "configuration.properties";

	public static final String LOG4_FIL_NAME_WITH_PATH = "log4j.properties";

	public static final String START_PARANTHESES = "[";
	public static final String END_PARANTHESES = "]";
	public static final String COLON = ":";
	public static final String COMMA = ",";
	public static final String TILDE = "~";
	public static final String THREE_TILDE = "~~~";
	public static final String AND_SIGN = "&";
	public static final String AND_STRING = "a_n_d";

	public static final String JSON_RESULT_SEPARATOR = "^";

	public static final String TRUE = "T";
	public static final String FALSE = "F";

	public static final String STRING_DOT = ".";

	public static final String FORM_EMP_CODE_PARAM = "empcode";
	public static final String FORM_ENTITY_PARAM = "entity";
	public static final String FORM_DATA_PARAM = "dataToUpload";
	public static final String FORM_DATA_SESSION_ID = "sessionID";
}

package com.scannerapp.ws.common.util.db;

import java.io.File;

/**
 * This class contains various Data Base constants required across the
 * application
 * 
 * @author Amit Patel <amit.patel@spec-india.com>
 * @since Apr 27, 2011
 */

public interface DbConstant {
	/** The Constant BEAN_TABLENAME. */
	public static final String BEAN_TABLENAME = "tableName";
	/** The Constant SEQUENCE. */
	public static final String SEQUENCE = "sequence";
	/** The Constant SEQUENCE_FIELD. */
	public static final String SEQUENCE_FIELD = "sequenceField";
	/** The Constant AND_OPRN. */
	public static final String AND_OPRN = " and ";
	/** The Constant OR_OPRN. */
	public static final String OR_OPRN = " or ";
	/** The Constant NULL_OPRN. */
	public static final String NULL_OPRN = " ";
	/** The Constant MAX_LENGTH_ARRAY_VALUE. */
	public static final int MAX_LENGTH_ARRAY_VALUE = 100;
	/** The Constant Data Source Constant. */

	// FOR JBOSS APPLICATION SERVER
	public static final String DATA_SOURCE = "java:jdbc/jlloracle_ds";

	// FOR TOMCAT WEB SERVER
	// public static final String DATA_SOURCE =
	// "java:/comp/env/jdbc/jlloracle_ds";

	/** Login Data Query */
	public static final String LOGIN_SQL_FILE = "query" + File.separator
			+ "login_sql.properties";
	/** Node Data Query */
	public static final String NODE_SQL_FILE = "query" + File.separator
			+ "node_sql.properties";
}

package com.scannerapp.apps.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.scannerapp.apps.application.ApplicationConstants;

/**
 * A Utility class to get the constant values defined in different properties
 * file.
 * 
 * @author Shalin Shah<shalin.shah@spec-india.com>
 * @since 09/Jul/2013
 */
public class ConstantUtil {

	private static final Logger LOGGER = Logger.getLogger(ConstantUtil.class);

	private static Properties appConstProperties = null;
	private static Properties errorConstProperties = null;

	/**
	 * Method to get the value from the "appconstant.properties" file for the
	 * provided key.
	 * 
	 * @param propertyKey
	 * @return
	 */
	public static String getApplicationConstant(String propertyKey) {

		if (appConstProperties == null) {

			appConstProperties = new Properties();
			initProperties(ApplicationConstants.APP_PROPERTY_FILE,
					appConstProperties);
		}

		return appConstProperties.getProperty(propertyKey);
	}

	/**
	 * Method to get the value from the "error.properties" file for the
	 * provided key.
	 * 
	 * @param propertyKey
	 * @return
	 */
	public static String getErrorConstant(String propertyKey) {

		if (errorConstProperties == null) {

			errorConstProperties = new Properties();
			initProperties(ApplicationConstants.ERR_PROPERTY_FILE,
					errorConstProperties);
		}

		return errorConstProperties.getProperty(propertyKey);
	}
	
	/**
	 * Method to initialize the provided properties object by loading all
	 * properties from the provided property file name.
	 * 
	 * @param propertyFileName
	 * @param properties
	 */
	private static void initProperties(String propertyFileName,
			Properties properties) {

		FileInputStream propertyFileInputStream = null;
		File propertyFile = new File(propertyFileName);

		try {

			propertyFileInputStream = new FileInputStream(propertyFile);
			properties.load(propertyFileInputStream);

		} catch (Exception exception) {

			LOGGER.error("Exception while loading properties file : "
					+ propertyFileName);
			LOGGER.error("Exception : " + exception);
		}

		finally {

			try {

				if (propertyFileInputStream != null)
					propertyFileInputStream.close();

			} catch (Exception exception) {

				LOGGER.error("Exception while closing input stream to load properties file : "
						+ propertyFileName);
				LOGGER.error("Exception : " + exception);
			}
		}
	}
}

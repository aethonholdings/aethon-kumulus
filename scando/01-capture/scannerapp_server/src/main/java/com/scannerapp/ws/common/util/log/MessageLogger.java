package com.scannerapp.ws.common.util.log;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.scannerapp.ws.common.util.configuration.ApplicationConstants;

/**
 * This class is a Wrapper to Logger class of apache log4j framework. This class
 * has got methods to return the logger for the class/package specified.
 * 
 * @author Niraj Salot <niraj.salot@spec-india.com>
 * @since Apr 27, 2011
 */
public class MessageLogger {

	/**
	 * Message Logger instance used for obtaining the logger instance
	 */
	private static MessageLogger msLogger = null;

	/**
	 * Logger instance that will be returned to all classes
	 */
	private static Logger myLogger;

	/**
	 * This is a private constructor of MessageLogger. This will use the
	 * DOMConfigurator to configure the log4j framework
	 */
	private MessageLogger() {
		try {
			// createDirectory();
			PropertyConfigurator.configureAndWatch(
					ApplicationConstants.LOG4_FIL_NAME_WITH_PATH, 4000);
		} catch (Exception ex) {
			System.out
					.println("System could not load logger configuration file:"
							+ ex);
			ex.printStackTrace();
		}
	}

	/**
	 * Method to create the directory for log if not exist.
	 */
	private void createDirectory() {

		Properties log4jProperties = new Properties();

		try {
			log4jProperties.load(new FileInputStream(
					ApplicationConstants.LOG4_FIL_NAME_WITH_PATH));

			// GETTING LOG FILE NAME..
			String logFile = log4jProperties
					.getProperty("log4j.appender.LOGFILE.File");
			logFile = logFile.replaceAll("\\", File.separator);
			logFile = logFile.replaceAll("/", File.separator);

			// GETTING LOG DIRECTORY AND CREATING IF NOT EXISTS..
			String logDirectoryStr = logFile.substring(0,
					logFile.lastIndexOf(File.separator));

			File logDirectory = new File(logDirectoryStr);

			if (!logDirectory.exists()) {
				logDirectory.mkdirs();
			}

		} catch (Exception e) {
			System.out.println("ERROR While Creating Log Directory.");
			e.printStackTrace();
		}
	}

	/**
	 * This method returns an instance of MessageLogger. This method will take a
	 * String (fully qualified Class name ) as a parameter.
	 * 
	 * @param logger
	 *            String - This argument needs to have the name of the class
	 *            from where this method is called.
	 * @return Logger
	 * @exception
	 */
	public static Logger getLogger(String logger) {
		if (msLogger == null) {
			msLogger = new MessageLogger();
		}
		myLogger = Logger.getLogger(logger);
		return myLogger;
	}

	/**
	 * This method returns an instance of MessageLogger. It will automatically
	 * find the name of the calling class.
	 * 
	 * @return Logger
	 * @exception
	 */
	public static Logger getLogger() {
		StackTraceElement myCaller = Thread.currentThread().getStackTrace()[2];
		String logger = myCaller.getClassName();
		if (msLogger == null) {
			msLogger = new MessageLogger();
		}
		myLogger = Logger.getLogger(logger);
		return myLogger;
	}

}
package com.scannerapp.apps.application;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

public class InitLog4j {
	
	static final String LOG_PROPERTIES_FILE = "resources\\properties\\log4j.properties";
	
	public static void initializeLogger() {
	    Properties logProperties = new Properties();
	    try {
	    	createLogFolder();
	    	logProperties.load(new FileInputStream(LOG_PROPERTIES_FILE));
	      	PropertyConfigurator.configure(logProperties);
	    }
	    catch(IOException e) {
	    	throw new RuntimeException("Unable to losad logging property " + LOG_PROPERTIES_FILE);
	    }
	  }
	
	public static void createLogFolder(){
    	File logDir = new File("log");
    	if (!logDir.exists())
    		logDir.mkdir();
	}
}

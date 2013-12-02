package com.scannerapp.apps.framework.utils;
import org.apache.log4j.Logger;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: System Plus P. Ltd</p>
 * @author Kanhaiya Samariya
 * @version 1.0
 */
public abstract class ClientHelper {
	private static Logger log = Logger.getLogger(ClientHelper.class);//For Log4j
    
    private final static String SERVER_PROPERTIES_FILE = "IMSessionManager.properties";
    public ClientHelper() {
        super();
    }
    public static void getManager() {
        try {
        		
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}

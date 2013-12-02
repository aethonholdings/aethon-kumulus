package com.scannerapp.common.utils;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Kanhaiya Samariya
 * @version 1.0
 */
public class Debug {
    private static boolean iDebug = true;
    private static Debug instance = null;
    private Debug() {
        super();
    }
    public static Debug getInstance() {
        if(instance == null) {
            instance = new Debug();
        }
        return instance;
    }
    public static boolean isDebug() {
        return iDebug;
    }
    public static void setDebug(boolean newValue) {
        iDebug = newValue;
    }
    public static void debugLog(String aMessage) {
        if(isDebug()) {
            System.out.println();
            System.out.println(aMessage);
        }
    }
    public static void errorLog(String aMessage, Throwable t) {
        System.out.println("ERROR: " + aMessage);
        if(t != null) {
            t.printStackTrace();
        }
    }
    public static void errorLog(String aMessage) {
        errorLog(aMessage, null);
    }
}

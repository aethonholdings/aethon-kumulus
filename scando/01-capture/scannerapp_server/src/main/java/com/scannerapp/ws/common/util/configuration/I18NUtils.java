package com.scannerapp.ws.common.util.configuration;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URLClassLoader;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * This class offers methods for getting messages from bundle files . 
 * This Helper class is the support for the I18N. 
 *
 * @author Amit Patel <amit.patel@spec-india.com>
 * @since Jan 23, 2012
 */
public class I18NUtils {

	/**
	 * Obtains a message from a bundle.
	 * @param bundleName name of the bundle package and file
	 * @param bundleMsg key message
	 * @return the message description of the provided key
	 */
	public static String getMessageFromBundle (String bundleName, String bundleMsg , Locale locale) {
		String msg = null;
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);
			msg = bundle.getString(bundleMsg);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * Obtains a message from a bundle.
	 * @param bundleName name of the bundle package and file
	 * @param bundleMsg key message
	 * @return the message description of the provided key
	 */
	public static String getMessageFromBundle (String bundleName, String bundleMsg ) {
		
		String msg = null;
		Locale locale = null;
		if(locale==null)
			locale = Locale.getDefault();
				
		java.io.File fileObj = new java.io.File(bundleName); 
		java.net.URL resourceURL = null;
	
		try { 
		resourceURL = fileObj.getParentFile().toURI().toURL();
		} catch (MalformedURLException e) { 
			e.printStackTrace(); 
		} 
		
		URLClassLoader urlLoader = new URLClassLoader(new java.net.URL[]{resourceURL}); 
		java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle(bundleName.substring(bundleName.lastIndexOf(File.separator)+1,bundleName.indexOf(".")), 
				locale , urlLoader );		
		try {			
			msg = bundle.getString(bundleMsg);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * Obtains a message from a bundle with arguments. This is a dynamic message due to 
	 * that the arguments are replaced in some points of the message ({0},{1},...)
	 * @param bundleName name of the bundle package and file
	 * @param bundleMsg key message
	 * @param args arguments to be included in the message
	 * @return the message description of the provided key with the arguments included
	 */
	public static String getMessageFromBundle (String bundleName, String bundleMsg, Object[] args,Locale locale) {
		String msg = getMessageFromBundle(bundleName, bundleMsg,locale);
		String result = MessageFormat.format(msg, args);
		return result;
	}	
}

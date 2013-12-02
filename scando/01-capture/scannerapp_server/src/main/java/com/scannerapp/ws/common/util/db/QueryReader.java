package com.scannerapp.ws.common.util.db;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * This class is used to read the SQL query from the property file.
 * 
 * @author Niraj Salot <niraj.salot@spec-india.com>
 * @since Apr 27, 2011
 */
public class QueryReader {

	/** The IN clause data separator. */
	private static String separator = ",";

	/**
	 * Obtains a SQL from a property file.
	 * 
	 * @param bundleName
	 *            name of the file
	 * @param bundleMsg
	 *            key message
	 * @return the message description of the provided key
	 */
	public static String getQueryFromPropertyFile(String bundleName,
			String bundleMsg) {

		String msg = null;

		java.util.ResourceBundle bundle = java.util.ResourceBundle
				.getBundle(bundleName.substring(0, bundleName.indexOf(".")));

		try {
			msg = bundle.getString(bundleMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * Obtains a SQL from a property file.
	 * 
	 * @param bundleName
	 *            name of the file
	 * @param bundleMsg
	 *            key message
	 * @param inClauseData
	 *            data which will go in SQL IN clause
	 * @return the message description of the provided key
	 */
	public static String getQueryFromPropertyFile(String bundleName,
			String bundleMsg, Map<String, List<?>> inClauseData) {
		System.out.println("second one called");
		String msg = null;
		Locale locale = Locale.getDefault();
		java.io.File fileObj = new java.io.File(bundleName);
		java.net.URL resourceURL = null;

		try {
			resourceURL = fileObj.getParentFile().toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		URLClassLoader urlLoader = new URLClassLoader(
				new java.net.URL[] { resourceURL });
		java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle(
				bundleName.substring(
						bundleName.lastIndexOf(File.separator) + 1,
						bundleName.indexOf(".")), locale, urlLoader);
		try {
			msg = bundle.getString(bundleMsg);
			for (Object object : inClauseData.keySet()) {
				List<?> data = inClauseData.get(object.toString());
				msg = msg.replace(object.toString(), listToString(data));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * List to string.
	 * 
	 * @param listObj
	 *            the list obj
	 * @return the string
	 */
	private static String listToString(List<?> listObj) {
		StringBuilder sb = new StringBuilder("");
		String sep = "";
		for (Object object : listObj) {
			sb.append(sep).append(object.toString());
			sep = separator;
		}
		return sb.toString();
	}

	/**
	 * Sets the to string.
	 * 
	 * @param setObj
	 *            the set obj
	 * @return the string
	 */
	private static String setToString(Set<?> setObj) {
		StringBuilder sb = new StringBuilder("");
		String sep = "";
		for (Object object : setObj) {
			sb.append(sep).append(object.toString());
			sep = separator;
		}
		return sb.toString();
	}
}

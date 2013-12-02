package com.scannerapp.ws.common.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;

/**
 * This class is a Wrapper to Gson class of google JSON framework. This class
 * has got methods to return the logger for the class/package specified.
 * 
 * @author Vipul
 * @since Apr 27, 2011
 */

public class GsonUtility {
	/**
	 * This is a private constructor of MessageLogger.
	 */
	private GsonUtility() {
	}

	/**
	 * Gson Utility instance used for obtaining the Gson instance
	 */
	private static GsonUtility gsonUtility = null;
	/**
	 * Gson object that will be returned to all classes
	 */
	private static Gson gson;

	/**
	 * This method returns an instance of GsonUtility. This method will take a
	 * 
	 * @return GsonUtility
	 * @exception
	 */
	public static GsonUtility getGsonUtility() {
		if (gsonUtility == null) {
			gsonUtility = new GsonUtility();
			gson = new Gson();
		}
		return gsonUtility;
	}

	/**
	 * This method serializes the specified object into its equivalent Json
	 * representation
	 * 
	 * @param src
	 *            - the object for which Json representation is to be created
	 *            setting for Gson
	 * @return Json representation of src
	 * 
	 */
	public String toJson(Object src) {
		return gson.toJson(src);
	}

	/**
	 * This method serializes the specified object, including those of generic
	 * types, into its equivalent Json representation
	 * 
	 * @param src
	 *            - the object for which JSON representation is to be created
	 * @param typeOfSrc
	 *            - The specific genericized type of src. You can obtain this
	 *            type by using the TypeToken class. For example, to get the
	 *            type for Collection<Foo>, you should use:
	 * @return Json representation of src
	 * 
	 */
	public String toJson(Object src, Type typeOfSrc) {
		return gson.toJson(src, typeOfSrc);
	}

	/**
	 * This method deserializes the specified Json into an object of the
	 * specified class
	 * 
	 * @param json
	 *            - the string from which the object is to be deserialized
	 * @param classOfT
	 *            - the class of T = T - the type of the desired object
	 * @return an object of type T from the string
	 * 
	 */

	// public <T> T fromJson(String json,Class<T> classOfT) {
	// return gson.fromJson(json, classOfT);
	// }

	/**
	 * This method deserializes the specified Json into an object of the
	 * specified type. This method is useful if the specified object is a
	 * generic type.
	 * 
	 * @param the
	 *            string from which the object is to be deserialized
	 * @param The
	 *            specific genericized type of src. You can obtain this type by
	 *            using the TypeToken class. For example, to get the type for
	 *            Collection<Foo>, you should use:
	 * @return an object of type T from the string
	 * 
	 */
	// public <T> T fromJson(String json, Type typeOfT) {
	// return gson.fromJson(json, typeOfT);
	// }

}

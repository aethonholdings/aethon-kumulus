/**
 * 
 */
package com.scannerapp.apps.utils;

import com.scannerapp.shared.SessionData;

/**
 * A utility class to create and store the session data for the logged in user.
 * 
 * @author Shalin Shah<shalin.shah@spec-india.com>
 * @since 15/July/2013
 */
public class SessionUtil {

	private static SessionData sessionData = null;

	/**
	 * To set instance of {@link SessionData}
	 * 
	 * @param sessionDataFromDB
	 *            the instance of {@link SessionData} received from DB.
	 */
	public static void setSessionData(SessionData sessionDataFromDB) {
		sessionData = sessionDataFromDB;
	}

	/**
	 * To get instance of {@link SessionData}
	 * 
	 * @return
	 */
	public static SessionData getSessionData() {
		return sessionData;
	}
}

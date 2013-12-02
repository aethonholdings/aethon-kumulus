/**
 * 
 */
package com.scannerapp.util;

import java.util.HashMap;

import com.scannerapp.shared.SessionData;

/**
 * A utility class to maintain the session data for the user at the server side.
 * 
 * @author Shalin Shah<shalin.shah@spec-india.com>
 * @since 15/July/2013
 */
public class SessionUtil {

	private static HashMap<String, SessionData> sessionDataMap = new HashMap<String, SessionData>();

	/**
	 * Method to get the session data for the provided user.
	 * 
	 * @param userId
	 * @return
	 */
	public static SessionData getSessionData(String userId) {

		SessionData sessionData = null;

		if (sessionDataMap.get(userId) == null) {

			sessionData = new SessionData();
			sessionDataMap.put(userId, sessionData);

		} else {

			sessionData = sessionDataMap.get(userId);
		}

		return sessionData;
	}
}

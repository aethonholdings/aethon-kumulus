package com.scannerapp.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.scannerapp.dao.BaseDAO;
import com.scannerapp.dao.DAOFactory;
import com.scannerapp.dao.LoginDAO;
import com.scannerapp.shared.SessionData;
import com.scannerapp.util.SessionUtil;
import com.scannerapp.ws.common.util.configuration.ApplicationConstants;
import com.scannerapp.ws.common.util.db.QueryReader;
import com.scannerapp.ws.common.util.log.MessageLogger;

/**
 * Implementation class which actually interacts with the DAO class to operate
 * with DB.
 * 
 * @author Shalin Shah<shalin.shah@spec-india.com>
 * @since 10/Jul/2013
 */
public class LoginServiceImpl extends BaseDAO {

	private Logger logger = MessageLogger.getLogger("LoginServiceImpl");

	private LoginDAO loginDAO = DAOFactory.getLoginDAO();

	/**
	 * Method to get the list of the project when the application starts. These
	 * projects will be in a map having project id as key and project name as
	 * value.
	 * 
	 * @return
	 */
	public HashMap<String, String> getProjectList() {

		try {

			String serverVersion = QueryReader.getQueryFromPropertyFile(
					ApplicationConstants.CONF_CONST_FILE, "serverVersion");

			logger.info("Start Executing --- ScanDo Application --- Server Version : "
					+ serverVersion);

			return loginDAO.getProjectList();

		} catch (Exception exception) {

			logger.error("Exception while fetching the project list.");
			logger.error("Exception : " + exception);

			return null;
		}
	}

	/**
	 * Method to check if the login is authorized and if it is authorized, fetch
	 * other parameters.
	 * 
	 * @param loginCredentials
	 *            - List containing userId and password and project id.
	 * @return
	 */
	public Boolean authorizeLogin(ArrayList<String> loginCredentials) {

		Connection connection = null;
		Boolean isProcessCompleted = false;

		String userId = loginCredentials.get(0);
		String userNameAndPassword = loginCredentials.get(1);
		String projectId = loginCredentials.get(2);
		String projectName = loginCredentials.get(3);

		try {

			connection = getSqlUtil().getConnectionFactory().getDBConnection();

			// Authorizing the login
			if (loginDAO.authorizeLogin(userNameAndPassword, connection)) {

				// Making the attendance.
				if (loginDAO.noteAttendance(userId, projectId, connection)) {

					logger.info("User : "
							+ userId
							+ "  ---> Logged In Successfully & Attendance Noted");

					isProcessCompleted = true;
				}
			}

			if (isProcessCompleted)
				connection.commit();
			else
				connection.rollback();

		}

		// IF ANY EXCEPTION IS CAUGHT WHILE AUTHORIZING THE LOGIN AND FETCHING
		// THE PARAMETERS.
		catch (Exception exception) {

			logger.error("Exception while login authorization for User ID : "
					+ userId + " and Project Name : " + projectName);
			logger.error("Exception : " + exception);

			if (connection != null) {

				try {
					connection.rollback();
				} catch (Exception e) {
					logger.error("Exception while rolling back the connection for login authorization for User ID : "
							+ userId + " and Project Name : " + projectName);
					logger.error("Exception : " + e);
				}
			}
		}

		// FINALLY BLOCK TO EXECUTE TO CLOSE CONNECTION.
		finally {
			if (connection != null) {

				try {
					connection.close();
				} catch (Exception e) {
					logger.error("Exception while closing the connection for login authorization for User ID : "
							+ userId + " and Project Name : " + projectName);
					logger.error("Exception : " + e);
				}
			}
		}

		return isProcessCompleted;
	}

	/**
	 * Method to fetch the session data from the DB for the logged in user.
	 * 
	 * @param loginCredentials
	 * @return
	 */
	public SessionData fetchSessionData(ArrayList<String> loginCredentials) {

		String userId = loginCredentials.get(0);
		String projectId = loginCredentials.get(2);
		String projectName = loginCredentials.get(3);

		try {

			HashMap<String, String> applicationParamMap = loginDAO
					.getApplicationParameters(projectId);

			HashMap<String, HashMap<String, String>> applicationTotMap = loginDAO
					.getApplicationTot();

			HashMap<String, String> userParameters = loginDAO
					.getUserParameters(userId);

			// Getting SessionData for user.
			SessionData sessionData = SessionUtil.getSessionData(userId);

			sessionData.setUserId(userId);
			sessionData.setProjectId(projectId);
			sessionData.setProjectName(projectName);

			setAppParamInSessionData(applicationParamMap, sessionData);
			setAppTotInSessionData(applicationTotMap, sessionData);
			setUserParamsInSessionData(userParameters, sessionData);

			return sessionData;
		}

		catch (Exception e) {
			logger.error("Exception while fetching the session data for User ID : "
					+ userId + " and Project Name : " + projectName);
			logger.error("Exception : " + e);

			return new SessionData();
		}
	}

	/***
	 * Method to update the attendance of the user at every specific interval.
	 * 
	 * @param userProjectList
	 *            - list containing user id as first element and project id as
	 *            second.
	 * @return
	 */
	public Boolean updateAttendance(ArrayList<String> userProjectList) {

		String userId = userProjectList.get(0);
		String projectId = userProjectList.get(1);
		String breathInterval = userProjectList.get(2);

		try {

			return loginDAO.updateAttendance(userId, projectId, breathInterval);
		}

		catch (Exception e) {
			logger.error("Exception updating the attendance of the User ID : "
					+ userId + " and Project ID : " + projectId);
			logger.error("Exception : " + e);

			return false;
		}
	}

	// ************** UTILITY METHOD TO SET SESSION DATA **************

	/**
	 * Method to set the application parameters in the session data.
	 * 
	 * @param applicationParamMap
	 * @param sessionData
	 */
	private void setAppParamInSessionData(
			HashMap<String, String> applicationParamMap, SessionData sessionData) {

		// VERSION
		if (applicationParamMap.get("version") != null) {
			sessionData.setVersion(applicationParamMap.get("version"));
		}

		// OVER ALL TARGET KPI
		if (applicationParamMap.get("targetKPI") != null) {
			sessionData.setOverallTarget(applicationParamMap.get("targetKPI"));
		}

		// REFRESH INTERVAL
		if (applicationParamMap.get("refresh_interval") != null) {

			Integer refresh_interval = Integer.parseInt(applicationParamMap
					.get("refresh_interval")) * 60000;

			sessionData.setRefreshInterval(refresh_interval.toString());
		}

		// BREATH INTERVAL
		if (applicationParamMap.get("breath_interval") != null
				&& applicationParamMap.get("breath_interval").length() > 0) {

			Integer breath_interval = Integer.parseInt(applicationParamMap
					.get("breath_interval"));

			sessionData.setBreathInterval(breath_interval.toString());
		}

		// LOCAL STORAGE PATH
		/*
		 * if (applicationParamMap.get("local_storage_path") != null) {
		 * 
		 * String localStoragePath = applicationParamMap
		 * .get("local_storage_path");
		 * 
		 * localStoragePath = localStoragePath .replace('/',
		 * File.separatorChar).replace('\\', File.separatorChar);
		 * 
		 * sessionData.setLocalStoragePath(localStoragePath); }
		 */

		// TOTAL IMAGE TO UPLOAD AT ONCE
		if (applicationParamMap.get("total_upload_images_at_once") != null) {
			sessionData.setTotalImagesToUploadAtOnce(applicationParamMap
					.get("total_upload_images_at_once"));
		}

		// LOCAL THUMBNAIL DIRECTORY PATH
		/*
		 * if (applicationParamMap.get("local_thumbnail_dir_path") != null) {
		 * 
		 * String localThumbnailDirPath = applicationParamMap
		 * .get("local_thumbnail_dir_path");
		 * 
		 * localThumbnailDirPath = localThumbnailDirPath.replace('/',
		 * File.separatorChar).replace('\\', File.separatorChar);
		 * 
		 * sessionData.setLocalThumbnailDirPath(localThumbnailDirPath); }
		 */
	}

	/**
	 * Method to set the TOT data fetched from DB in the session data.
	 * 
	 * @param applicationTotMap
	 * @param sessionData
	 */
	private void setAppTotInSessionData(
			HashMap<String, HashMap<String, String>> applicationTotMap,
			SessionData sessionData) {

		sessionData.setStatusMap(applicationTotMap.get("STATUS"));
		sessionData.setNodeTypeMap(applicationTotMap.get("NODE_TYPE"));
	}

	/**
	 * Method to set the user parameters like rights, KPIs etc. in the session
	 * data.
	 * 
	 * @param userParameters
	 * @param sessionData
	 */
	private void setUserParamsInSessionData(
			HashMap<String, String> userParameters, SessionData sessionData) {

		sessionData.setCollectionRight(userParameters.get("collectionRight"));
		sessionData.setImportRight(userParameters.get("importRight"));
		sessionData.setSeparationRight(userParameters.get("separationRight"));
		sessionData.setImportTarget(userParameters.get("importKPI"));
		sessionData.setSeparationTarget(userParameters.get("separationKPI"));
	}

	/**
	 * Method to authorize the application param.
	 * 
	 * @return
	 */
	public Boolean authorizeAppParam(String projectId) {
		try {
			return loginDAO.authorizeAppParam(projectId);
		}

		catch (Exception e) {
			logger.error("Exception authorize the application_param of the Project ID : "
					+ projectId);
			logger.error("Exception : " + e);

			return false;
		}
	}

	/**
	 * Method to fetch the overall and import/separation KPI performance for
	 * provided user id at every interval.
	 * 
	 * @param userId
	 * @return
	 */
	public HashMap<String, String> fetchKPIPerformance(String userId) {

		HashMap<String, String> performanceMap = new HashMap<String, String>();

		try {
			performanceMap.put("overallKPIPerformance",
					loginDAO.fetchOverallKPIPerformance());
			performanceMap.put("importKPIPerformance",
					loginDAO.fetchImportKPIPerformance(userId));
			performanceMap.put("separationKPIPerformance",
					loginDAO.fetchSeparationKPIPerformance(userId));
		}

		catch (Exception e) {

			logger.error("Exception while fetching the KPI performance for user : "
					+ userId);
			logger.error("Exception : " + e);
		}

		return performanceMap;
	}
}

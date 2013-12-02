package com.scannerapp.dao;

import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.scannerapp.ws.common.exception.BusinessException;
import com.scannerapp.ws.common.exception.FatalException;
import com.scannerapp.ws.common.util.db.DbConstant;
import com.scannerapp.ws.common.util.db.QueryReader;
import com.scannerapp.ws.common.util.log.MessageLogger;

/**
 * DAO class to interact with the DB for Login related operations.
 * 
 * @author Shalin Shah<shalin.shah@spec-india.com>
 * @since 12/July/2013
 */
public class LoginDAO extends BaseDAO {

	private Logger logger = MessageLogger.getLogger("LoginDAO");

	/**
	 * Method to get the list of the project when the application starts. These
	 * projects will be in a map having project id as key and project name as
	 * value.
	 * 
	 * @return
	 * @throws BusinessException
	 * @throws FatalException
	 */
	public HashMap<String, String> getProjectList() throws BusinessException,
			Exception {

		HashMap<String, String> projectMap = new HashMap<String, String>();

		String query = QueryReader.getQueryFromPropertyFile(
				DbConstant.LOGIN_SQL_FILE, "getProjectList.sql");

		logger.info("Query To Get The Project List From DB : " + query);

		List<Map<String, Object>> resultList = getSqlUtil().getQueryMaps(query);

		if (resultList != null && resultList.size() > 0) {

			for (int index = 0; index < resultList.size(); index++) {

				Map<String, Object> result = resultList.get(index);

				projectMap.put(getStringValue(result.get("project_Id")),
						getStringValue(result.get("project_name")));
			}
		}

		return projectMap;
	}

	/**
	 * Method to check if the logged in user is authorized or not.
	 * 
	 * @param userNameAndPassword
	 * @return
	 * @throws BusinessException
	 * @throws FatalException
	 */
	public Boolean authorizeLogin(String userNameAndPassword,
			Connection connection) throws BusinessException, Exception {

		Object[] params = new Object[1];
		params[0] = userNameAndPassword;

		String query = QueryReader.getQueryFromPropertyFile(
				DbConstant.LOGIN_SQL_FILE, "authorizeLogin.sql");

		logger.info("Query Authorize The Login : " + query
				+ "      PARAMETERS : " + Arrays.toString(params));

		Map<String, Object> result = getSqlUtil().getQueryMap(connection,
				query, params);

		if (result != null
				&& Integer.parseInt(getStringValue(result.get("totalCount"))) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to note the attendance for the logged in user.
	 * 
	 * @param userId
	 * @param projectId
	 * @param connection
	 * @return
	 * @throws BusinessException
	 * @throws FatalException
	 */
	public boolean noteAttendance(String userId, String projectId,
			Connection connection) throws BusinessException, Exception {

		Object[] params = new Object[1];
		params[0] = projectId;

		String query = QueryReader.getQueryFromPropertyFile(
				DbConstant.LOGIN_SQL_FILE, "fetchBreathIntervalAtLogin.sql");

		logger.info("Query To Fetch The Breath Interval To Note Attendace : "
				+ query + "      PARAMETERS : " + Arrays.toString(params));

		Map<String, Object> result = getSqlUtil().getQueryMap(connection,
				query, params);

		if (result == null || result.size() == 0)
			return false;

		params = new Object[3];
		params[0] = userId;
		params[1] = projectId;
		params[2] = getStringValue(result.get("param_val"));

		query = QueryReader.getQueryFromPropertyFile(DbConstant.LOGIN_SQL_FILE,
				"noteAttendance.sql");

		logger.info("Query To Note Attendance : " + query
				+ "      PARAMETERS : " + Arrays.toString(params));

		long insertedRow = getSqlUtil().dmlQueryExecutor(connection, query,
				params);

		if (insertedRow > 0)
			return true;
		else
			return false;
	}

	/**
	 * Method to get the application parameters stored in APPLICATION_PARAMETER
	 * table.
	 * 
	 * @param projectId
	 * 
	 * @return
	 * @throws BusinessException
	 * @throws FatalException
	 */
	public HashMap<String, String> getApplicationParameters(String projectId)
			throws BusinessException, Exception {

		HashMap<String, String> applicationParametersMap = new HashMap<String, String>();

		String query = QueryReader.getQueryFromPropertyFile(
				DbConstant.LOGIN_SQL_FILE, "getApplicationParameters.sql");

		logger.info("Query To Get The Application Parameters : " + query
				+ "      PARAMETERS : " + projectId);

		List<Map<String, Object>> resultList = getSqlUtil().getQueryMaps(query,
				projectId);

		if (resultList != null && resultList.size() > 0) {

			for (int index = 0; index < resultList.size(); index++) {

				Map<String, Object> result = resultList.get(index);

				applicationParametersMap.put(
						getStringValue(result.get("param_name")),
						getStringValue(result.get("param_val")));
			}
		}

		return applicationParametersMap;
	}

	/**
	 * Method to get the application TOT values stored in APPLICATION_TOT table.
	 * 
	 * @return
	 * @throws BusinessException
	 * @throws FatalException
	 */
	public HashMap<String, HashMap<String, String>> getApplicationTot()
			throws BusinessException, Exception {

		HashMap<String, HashMap<String, String>> applicationTotMap = new HashMap<String, HashMap<String, String>>();

		String query = QueryReader.getQueryFromPropertyFile(
				DbConstant.LOGIN_SQL_FILE, "getApplicationTot.sql");

		logger.info("Query To Get The Application TOT : " + query
				+ "      PARAMETERS : " + null);

		List<Map<String, Object>> resultList = getSqlUtil().getQueryMaps(query);

		if (resultList != null && resultList.size() > 0) {

			for (int index = 0; index < resultList.size(); index++) {

				Map<String, Object> result = resultList.get(index);

				HashMap<String, String> totMap = null;

				if (applicationTotMap.get(getStringValue(result
						.get("object_type"))) == null) {

					totMap = new HashMap<String, String>();

					applicationTotMap.put(
							getStringValue(result.get("object_type")), totMap);

				} else {
					totMap = applicationTotMap.get(getStringValue(result
							.get("object_type")));
				}

				totMap.put(getStringValue(result.get("object_name")),
						getStringValue(result.get("object_value")));
			}
		}

		return applicationTotMap;
	}

	/**
	 * Method to fetch the user parameters like, rights provided to him, KPI etc
	 * from USER table.
	 * 
	 * @param userId
	 * @return
	 * @throws BusinessException
	 * @throws FatalException
	 */
	public HashMap<String, String> getUserParameters(String userId)
			throws BusinessException, Exception {

		HashMap<String, String> userParamMap = new HashMap<String, String>();

		Object[] params = new Object[1];
		params[0] = userId;

		String query = QueryReader.getQueryFromPropertyFile(
				DbConstant.LOGIN_SQL_FILE, "getUserParam.sql");

		logger.info("Query To Get User Parameters : " + query
				+ "      PARAMETERS : " + params);

		Map<String, Object> result = getSqlUtil().getQueryMap(query, params);

		if (result != null && result.size() > 0) {

			userParamMap.put("collectionRight",
					getStringValue(result.get("collection_right")));
			userParamMap.put("importRight",
					getStringValue(result.get("import_right")));
			userParamMap.put("separationRight",
					getStringValue(result.get("separation_right")));
			userParamMap.put("importKPI",
					getStringValue(result.get("import_kpi_target")));
			userParamMap.put("separationKPI",
					getStringValue(result.get("separation_kpi_target")));
		}

		return userParamMap;
	}

	/***
	 * Method to update the attendance of the user at every specific interval.
	 * 
	 * @param userId
	 * @param projectId
	 * @param breathInterval
	 * @return
	 */
	public boolean updateAttendance(String userId, String projectId,
			String breathInterval) throws BusinessException, Exception {

		long updatedRow = 0;

		Object[] params = new Object[2];
		params[0] = userId;
		params[1] = projectId;

		String query = QueryReader.getQueryFromPropertyFile(
				DbConstant.LOGIN_SQL_FILE, "getAttendanceId.sql");

		logger.info("Query To Get Inserted Attendance Record Id To Update Attendance: "
				+ query + "      PARAMETERS : " + params);

		Map<String, Object> result = getSqlUtil().getQueryMap(query, params);

		if (result != null && result.size() > 0) {

			int attendanceId = (Integer) result.get("attendance_id");

			params = new Object[2];
			params[0] = breathInterval;
			params[1] = attendanceId;

			query = QueryReader.getQueryFromPropertyFile(
					DbConstant.LOGIN_SQL_FILE, "updateAttendance.sql");

			logger.info("Query To Update Attendance : " + query
					+ "      PARAMETERS : " + params);

			updatedRow = getSqlUtil().dmlExecutor(query, params);
		}

		if (updatedRow > 0)
			return true;
		else
			return false;
	}

	public Boolean authorizeAppParam(String projectId)
			throws BusinessException, Exception {
		Object[] params = new Object[1];

		params[0] = projectId;

		Map<String, Object> result;

		String query = QueryReader.getQueryFromPropertyFile(
				DbConstant.LOGIN_SQL_FILE, "authorizeAppParam.sql");

		logger.info("Query To Authorize Application Param : " + query
				+ "      PARAMETERS : " + params);

		result = getSqlUtil().getQueryMap(query, params);

		if (result != null
				&& Integer.parseInt(getStringValue(result.get("totalVal"))) > 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Method to fetch the overall KPI performance at every interval.
	 * 
	 * @param userId
	 * @return
	 */
	public String fetchOverallKPIPerformance() throws BusinessException,
			Exception {

		String overallKPIPerformance = "";

		String query = QueryReader.getQueryFromPropertyFile(
				DbConstant.NODE_SQL_FILE, "fetchOverallKPIPerformance.sql");

		logger.info("Query To Fetch Overall KPI Performance : " + query);

		Map<String, Object> result = getSqlUtil().getQueryMap(query);

		if (result != null && result.size() > 0) {

			overallKPIPerformance = getStringValue(result
					.get("overallPerformance"));
		}

		return overallKPIPerformance;
	}

	/**
	 * Method to fetch the import KPI performance for provided user id at every
	 * interval.
	 * 
	 * @param userId
	 * @return
	 */
	public String fetchImportKPIPerformance(String userId)
			throws BusinessException, Exception {

		String importKPIPerformance = "";

		Object[] params = new Object[1];
		params[0] = userId;

		String query = QueryReader.getQueryFromPropertyFile(
				DbConstant.NODE_SQL_FILE, "fetchImportKPIPerformance.sql");

		logger.info("Query To Fetch Import KPI Performance : " + query
				+ "     PARAMETERS : " + Arrays.toString(params));

		Map<String, Object> result = getSqlUtil().getQueryMap(query, params);

		if (result != null && result.size() > 0) {

			importKPIPerformance = getStringValue(result
					.get("importPerformance"));
		}

		return importKPIPerformance;
	}

	/**
	 * Method to fetch the separation KPI performance for provided user id at
	 * every interval.
	 * 
	 * @param userId
	 * @return
	 */
	public String fetchSeparationKPIPerformance(String userId)
			throws BusinessException, Exception {

		String separationKPIPerformance = "";

		Object[] params = new Object[1];
		params[0] = userId;

		String query = QueryReader.getQueryFromPropertyFile(
				DbConstant.NODE_SQL_FILE, "fetchSeparationKPIPerformance.sql");

		logger.info("Query To Fetch Separation KPI Performance : " + query
				+ "     PARAMETERS : " + Arrays.toString(params));

		Map<String, Object> result = getSqlUtil().getQueryMap(query, params);

		if (result != null && result.size() > 0) {

			separationKPIPerformance = getStringValue(result
					.get("separationPerformance"));
		}

		return separationKPIPerformance;
	}

	// ***************** UTILITY METHOD *****************

	/**
	 * Method to get the String representation of the provided object.
	 */
	private String getStringValue(Object object) {

		if (object == null) {
			return null;
		}

		return String.valueOf(object);
	}
}

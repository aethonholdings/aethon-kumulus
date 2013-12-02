package com.scannerapp.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.scannerapp.service.impl.LoginServiceImpl;
import com.scannerapp.shared.SessionData;

/**
 * Service class to execute services at the server side and thereby to cater the
 * request from client regarding login functionality.
 * 
 * @author Shalin Shah<shalin.shah@spec-india.com>
 * @since 10/Jul/2013
 */
@Path("loginServices")
public class LoginService {

	private LoginServiceImpl loginServiceImpl = new LoginServiceImpl();

	/**
	 * Method to get the list of the project when the application starts. These
	 * projects will be in a map having project id as key and project name as
	 * value.
	 * 
	 * @return
	 */
	@GET
	@Path("fetchProject")
	public HashMap<String, String> getProjectList() {

		return loginServiceImpl.getProjectList();
	}

	/**
	 * Method to verify if the logged in user is authorized or not.
	 * 
	 * @return
	 */
	@POST
	@Path("authorizeLogin")
	public Boolean authorizeLogin(ArrayList<String> loginCredentials) {

		return loginServiceImpl.authorizeLogin(loginCredentials);
	}

	/**
	 * Method to fetch the session data from the DB for logged in user.
	 * 
	 * @return
	 */
	@POST
	@Path("fetchSessionData")
	public SessionData fetchSessionData(ArrayList<String> loginCredentials) {

		return loginServiceImpl.fetchSessionData(loginCredentials);
	}

	/**
	 * Method to update the attendance time for user at every interval.
	 * 
	 * @return
	 */
	@POST
	@Path("updateAttendance")
	public Boolean updateAttendance(ArrayList<String> userProjectList) {

		return loginServiceImpl.updateAttendance(userProjectList);
	}

	/**
	 * Method to update the attendance time for user at every interval.
	 * 
	 * @return
	 */
	@POST
	@Path("authorizeAppParam")
	public Boolean isAuthorizeAppParam(String projectId) {

		return loginServiceImpl.authorizeAppParam(projectId);
	}

	/**
	 * Method to fetch the overall and import/separation KPI performance for
	 * provided user id at every interval.
	 * 
	 * @param userId
	 * @return
	 */
	@POST
	@Path("fetchKPIPerformance")
	public HashMap<String, String> fetchKPIPerformance(String userId) {

		return loginServiceImpl.fetchKPIPerformance(userId);
	}
}

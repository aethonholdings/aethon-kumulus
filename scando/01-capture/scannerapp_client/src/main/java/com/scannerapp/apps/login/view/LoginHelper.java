package com.scannerapp.apps.login.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.scannerapp.apps.framework.utils.ClientHelper;
import com.scannerapp.apps.framework.view.ErrorMessage;
import com.scannerapp.apps.utils.ConnectionUtil;
import com.scannerapp.shared.SessionData;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class LoginHelper extends ClientHelper {

	private static Logger log = Logger.getLogger(LoginHelper.class);

	private ObjectMapper mapper = new ObjectMapper();
        
        String json= null;

	/**
	 * Method to fetch the project list when the application starts.
	 * 
	 * @return
	 */
	public HashMap<String, String> getProjectList() {

		HashMap<String, String> projectList = new HashMap<String, String>();
                String projectUrl="http://localhost:8080/kumulus/scanDo/fetchProjectList";

		try {
//			String projectListJson = ConnectionUtil.getWebService()
//					.path("loginServices").path("fetchProject")
//					.accept(MediaType.APPLICATION_JSON_TYPE).get(String.class);
                        
                        
                      String projectListJson = this.getAuthenticatedfromServer(projectUrl);
                        System.out.println("Project json is : "+projectListJson);
                        
                        System.out.println("****************************************************");
                        
                       

			projectList = mapper.readValue(projectListJson,
					new TypeReference<HashMap<String, String>>() {
					});
                        
                         System.out.println("Project keys"+ projectList.keySet());

		} 
		catch (JsonParseException e) 
		{
			log.error("Error while reading the json string for project list");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorInParsingResponseStringAS");
		}
		catch (JsonMappingException e) 
		{
			log.error("Error while reading the json string for project list");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorInParsingResponseStringAS");
		}
		catch (IOException e) 
		{
			log.error("Error while reading the json string for project list");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorInParsingResponseStringAS");
		}
		catch (UniformInterfaceException e) {

			log.error("Error while reading the json string for project list");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
		}
		catch (ClientHandlerException e) {

			log.error("Error while reading the json string for project list");
			log.error("Exception : " + e);
			if(e.toString().contains("java.net.ConnectException: Connection refused"))
			{
				ErrorMessage.displayMessage('E', "errorInASConnection");
			}
			else
			{
				ErrorMessage.displayMessage('E', "errorGeneratedOnAS");	
			}			
		}
		catch (Exception e) {

			log.error("Error while reading the json string for project list");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
		}

		return projectList;
	}

	/**
	 * Method to verify the logged in user and perform authorization.
	 * 
	 * @param loginCredentials
	 * @return
	 */
	public boolean authorizeLogin(ArrayList<String> loginCredentials) {

		Boolean isAuthorizedLogin = false;

		try {
			ClientResponse response = ConnectionUtil.getWebService()
					.path("loginServices").path("authorizeLogin")
					.type(MediaType.APPLICATION_JSON_TYPE)
					.accept(MediaType.APPLICATION_JSON_TYPE)
					.post(ClientResponse.class, loginCredentials);

			isAuthorizedLogin = (Boolean) response.getEntity(Boolean.class);

		} 		
		catch (UniformInterfaceException e) {

			log.error("Error while checking for authorized login.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
		}
		catch (ClientHandlerException e) {

			log.error("Error while checking for authorized login.");
			log.error("Exception : " + e);
			if(e.toString().contains("java.net.ConnectException: Connection refused"))
			{
				ErrorMessage.displayMessage('E', "errorInASConnection");
			}
			else
			{
				ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
			}
		}
		catch (Exception e) {

			log.error("Error while checking for authorized login.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
		}

		return isAuthorizedLogin;
	}

	/**
	 * Method to fetch the session data from the DB when the user successfully
	 * logs into the system.
	 * 
	 * @param loginCredentials
	 * @return
	 */
	public SessionData fetchSessionData(ArrayList<String> loginCredentials) {

		SessionData sessionData = null;

		try {
			ClientResponse response = ConnectionUtil.getWebService()
					.path("loginServices").path("fetchSessionData")
					.type(MediaType.APPLICATION_JSON_TYPE)
					.accept(MediaType.APPLICATION_JSON_TYPE)
					.post(ClientResponse.class, loginCredentials);

			sessionData = (SessionData) response.getEntity(SessionData.class);

		} 
		catch (UniformInterfaceException e) {

			log.error("Error while fetching session data.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
		}
		catch (ClientHandlerException e) {

			log.error("Error while fetching session data.");
			log.error("Exception : " + e);
			if(e.toString().contains("java.net.ConnectException: Connection refused"))
			{
				ErrorMessage.displayMessage('E', "errorInASConnection");
			}
			else
			{
				ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
			}
		}
		catch (Exception e) {

			log.error("Error while fetching session data.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
		}

		return sessionData;
	}

	/**
	 * Method to update the attendance for the user id passed in arraylist (1st
	 * element). 2nd element is project id.
	 * 
	 * @param loginCredentials
	 * @return
	 */
	public boolean updateAttendance(ArrayList<String> loginCredentials) {

		try {
			ClientResponse response = ConnectionUtil.getWebService()
					.path("loginServices").path("updateAttendance")
					.type(MediaType.APPLICATION_JSON_TYPE)
					.accept(MediaType.APPLICATION_JSON_TYPE)
					.post(ClientResponse.class, loginCredentials);

			return (Boolean) response.getEntity(Boolean.class);

		} 
		catch (UniformInterfaceException e) {

			log.error("Error while updating attendance.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
			
			return false;
		}
		catch (ClientHandlerException e) {

			log.error("Error while updating attendance.");
			log.error("Exception : " + e);
			if(e.toString().contains("java.net.ConnectException: Connection refused"))
			{
				ErrorMessage.displayMessage('E', "errorInASConnection");
			}
			else
			{
				ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
			}
			
			return false;
		}
	catch (Exception e) {

			log.error("Error while updating attendance.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");

			return false;
		}
	}

	/**
	 * Method to fetch the KPI performance for the provided user.
	 * 
	 * @param userId
	 * @return
	 */
	public HashMap<String, String> fetchKPIPerformance(String userId) {

		HashMap<String, String> performanceMap = null;

		try {
			ClientResponse response = ConnectionUtil.getWebService()
					.path("loginServices").path("fetchKPIPerformance")
					.type(MediaType.APPLICATION_JSON_TYPE)
					.accept(MediaType.APPLICATION_JSON_TYPE)
					.post(ClientResponse.class, userId);

			String jsonString = (String) response.getEntity(String.class);

			performanceMap = mapper.readValue(jsonString,
					new TypeReference<HashMap<String, String>>() {
					});

		}
		catch (JsonParseException e) 
		{
			log.error("Error while fetching KPI performance.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorInParsingResponseStringAS");
		}
		catch (JsonMappingException e) 
		{
			log.error("Error while fetching KPI performance.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorInParsingResponseStringAS");
		}
		catch (IOException e) 
		{
			log.error("Error while fetching KPI performance.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorInParsingResponseStringAS");
		}
		catch (UniformInterfaceException e) {

			log.error("Error while fetching KPI performance.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
		}
		catch (ClientHandlerException e) {

			log.error("Error while fetching KPI performance.");
			log.error("Exception : " + e);
			if(e.toString().contains("java.net.ConnectException: Connection refused"))
			{
				ErrorMessage.displayMessage('E', "errorInASConnection");
			}
			else
			{
				ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
			}
		}
		
		
		catch (Exception e) {

			log.error("Error while fetching KPI performance.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
		}

		return performanceMap;
	}

	public boolean isAuthorizeApplicationParam(String projecId) {

		try {
			ClientResponse response = ConnectionUtil.getWebService()
					.path("loginServices").path("authorizeAppParam")
					.type(MediaType.APPLICATION_JSON_TYPE)
					.accept(MediaType.APPLICATION_JSON_TYPE)
					.post(ClientResponse.class, projecId);

			return (Boolean) response.getEntity(Boolean.class);

		} 
		catch (UniformInterfaceException e) {

			log.error("Error while checking for authorized application parameter.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
			
			return false;
		}
		catch (ClientHandlerException e) {

			log.error("Error while checking for authorized application parameter.");
			log.error("Exception : " + e);
			if(e.toString().contains("java.net.ConnectException: Connection refused"))
			{
				ErrorMessage.displayMessage('E', "errorInASConnection");
			}
			else
			{
				ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
			}
			
			return false;
		}
		catch (Exception e) {

			log.error("Error while checking for authorized application parameter.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");

			return false;
		}

	}
        
        
        /**
         * @auther Raj
         * Method to make http request
         * @param loginCredentials
         * @return
         * @throws IOException 
         */
        private String getAuthenticatedfromServer(String serverUrl) throws IOException{
           
               
           try {
 
		URL url = new URL(serverUrl);
                //URL url = new URL("http://192.168.1.7:8080/kumulus/scanDo/authenticate?j_username=kumulus&j_password=password");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(60000);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Accept", "application/json");
 
                
                
                System.out.println("Input stream from server"+conn.getInputStream());
//		if (conn.getResponseCode() != 200) {
//			throw new RuntimeException("Failed : HTTP error code : "
//					+ conn.getResponseCode());
//		}
 
		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
 
		String output;
                JSONObject jObj =null;
                StringBuilder sb = new StringBuilder();
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
                    sb.append(output);
			System.out.println(output);
		}
                 json= sb.toString();
//                try {
//                   jObj = new JSONObject(json);
//	           json= jObj.getString("results");            
//                System.out.println("Result is "+jObj.getString("results"));
//                } catch (JSONException e) {
//			}
		conn.disconnect();
 
	  } catch (MalformedURLException e) {
 
		e.printStackTrace();
 
	  } catch (IOException e) {
 
		e.printStackTrace();
 
	  }
                    
            return json;
       }
}

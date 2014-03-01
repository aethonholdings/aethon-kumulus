package com.scannerapp.apps.desktopleftpanel.view;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.scannerapp.apps.framework.utils.ClientHelper;
import com.scannerapp.apps.framework.view.ErrorMessage;
import com.scannerapp.apps.utils.ConnectionUtil;
import com.scannerapp.shared.NodeProperties;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.core.MultivaluedMap;
import org.codehaus.jettison.json.JSONObject;

public class DesktopLeftHelper extends ClientHelper {

	private static Logger log = Logger.getLogger(DesktopLeftHelper.class);
	private final ObjectMapper mapper = new ObjectMapper();

	public ArrayList initializeScreen() {
		ArrayList initData = new ArrayList();

		try {
			log.debug("DesktopLeftHelper :: initializeScreen() :: Calling.................");

			log.debug("LoginHelper :: initializeScreen() :: Called.............");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

		return initData;
	}

	public ArrayList<NodeProperties> getChildNodePropertiesList(
			ArrayList<String> idList) {

                String projectId =idList.get(0) ;
                String parentnodeId=idList.get(1) ;
		ArrayList<NodeProperties> childNodePropertiesList = null;
                
		try {
                    
                    
			ClientResponse response = ConnectionUtil.getWebService()
					.path("scanDo").path("fetchChildNodeList")
					.type(MediaType.APPLICATION_JSON_TYPE)
					.accept(MediaType.APPLICATION_JSON_TYPE)
					.post(ClientResponse.class, idList);

			String jsonString = (String) response.getEntity(String.class);
                     //  String jsonobj = GetJsonUtil.getJsonfromServertree(projectUrl);
                        System.out.println("Json is "+jsonString);
                       //childNodePropertiesList = new ArrayList<NodeProperties>();
			childNodePropertiesList = mapper.readValue(jsonString,
					new TypeReference<ArrayList<NodeProperties>>() {
					});
                        
                        //System.out.println("mmm"+childNodePropertiesList+"mmmmmmmmmmmmmm"+jsonobj.toString());

		}
	
		catch (JsonParseException e) 
		{
			log.error("Error while getting list of properties for child nodes.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorInParsingResponseStringAS");
		}
		catch (JsonMappingException e) 
		{
			log.error("Error while getting list of properties for child nodes.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorInParsingResponseStringAS");
		}
		catch (IOException e) 
		{
			log.error("Error while getting list of properties for child nodes.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorInParsingResponseStringAS");
		}
		catch (UniformInterfaceException e) {

			log.error("Error while getting list of properties for child nodes.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
		}
		catch (ClientHandlerException e) {

			log.error("Error while getting list of properties for child nodes.");
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
		catch (Exception e) 
		{
			log.error("Error while getting list of properties for child nodes.");
			log.error("Exception : " + e);
			
			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");	
		}

		return childNodePropertiesList;
	}
        
        
}

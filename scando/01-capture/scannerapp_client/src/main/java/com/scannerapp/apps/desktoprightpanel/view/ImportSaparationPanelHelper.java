package com.scannerapp.apps.desktoprightpanel.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.scannerapp.apps.framework.utils.ClientHelper;
import com.scannerapp.apps.framework.view.ErrorMessage;
import com.scannerapp.apps.utils.ConnectionUtil;
import com.scannerapp.apps.utils.SessionUtil;
import com.scannerapp.shared.NodeProperties;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.core.MultivaluedMap;
import org.codehaus.jettison.json.JSONObject;

public class ImportSaparationPanelHelper extends ClientHelper {

	private static Logger log = Logger
			.getLogger(ImportSaparationPanelHelper.class);

	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * Method to fetch hierarchy from DB for the provided barcode.
	 * 
	 * @param searchBarcode
	 * @return
	 */
	public String getHierarchyFromSearchBarcode(String searchBarcode) {

		String hierarchy = null;
		//ArrayList<String> param = new ArrayList<String>();
                MultivaluedMap requestData = new MultivaluedMapImpl();
                requestData.add("searchBarcode", searchBarcode);
		requestData.add("projectId",SessionUtil.getSessionData().getProjectId());
		
		try {
			ClientResponse response = ConnectionUtil.getWebService()
					.path("scanDo").path("getHierarchyFromSearchBarcode")
					.type(MediaType.APPLICATION_JSON_TYPE)
					.accept(MediaType.APPLICATION_JSON_TYPE)
					.post(ClientResponse.class, requestData);

			hierarchy = (String) response.getEntity(String.class);

		}

		catch (UniformInterfaceException e) {

			log.error("Error while fetching hierarchy from DB for provided search barcode.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
		}

		catch (ClientHandlerException e) {

			log.error("Error while fetching hierarchy from DB for provided search barcode.");
			log.error("Exception : " + e);

			if (e.toString().contains(
					"java.net.ConnectException: Connection refused")) {
				ErrorMessage.displayMessage('E', "errorInASConnection");
			} else {
				ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
			}
		}

		catch (Exception e) {

			log.error("Error while fetching hierarchy from DB for provided search barcode.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
		}

		return hierarchy;
	}

	public List<NodeProperties> fetchChildNodeList(ArrayList<String> idList) {
		ArrayList<NodeProperties> childNodePropertiesList = null;
                MultivaluedMap reuestData = new MultivaluedMapImpl();
                 reuestData.add("projectId", idList.get(0));
                 reuestData.add("parentnodeId", idList.get(1));

		try {
			ClientResponse response = ConnectionUtil.getWebService()
					.path("scanDo").path("fetchChildNodeList")
			 		.type(MediaType.APPLICATION_JSON_TYPE)
					.accept(MediaType.APPLICATION_JSON_TYPE)
					.post(ClientResponse.class, reuestData);

			String jsonString = (String) response.getEntity(String.class);

			childNodePropertiesList = mapper.readValue(jsonString,
					new TypeReference<ArrayList<NodeProperties>>() {
					});

		}

		catch (JsonParseException e) {
			log.error("Error while getting list of properties for child nodes.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorInParsingResponseStringAS");
		}

		catch (JsonMappingException e) {
			log.error("Error while getting list of properties for child nodes.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorInParsingResponseStringAS");
		}

		catch (IOException e) {
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

			if (e.toString().contains(
					"java.net.ConnectException: Connection refused")) {
				ErrorMessage.displayMessage('E', "errorInASConnection");
			} else {
				ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
			}
		}

		catch (Exception e) {

			log.error("Error while getting list of properties for child nodes.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
		}

		return childNodePropertiesList;
	}

	/**
	 * Method to upload scanned images.
	 * 
	 * @param imageNodePropertiesList
	 * @return
	 */
	public HashMap<String, Boolean> uploadScannedImages(
			ArrayList<NodeProperties> imageNodePropertiesList) {

		HashMap<String, Boolean> imageUploadResultMap = null;

		try {

    			ClientResponse response = ConnectionUtil.getWebService()
					.path("scanDo").path("saveScannedImages")
					.type(MediaType.APPLICATION_JSON_TYPE)
					.accept(MediaType.APPLICATION_JSON_TYPE)
					.post(ClientResponse.class, imageNodePropertiesList);

			String jsonString = (String) response.getEntity(String.class);

			imageUploadResultMap = mapper.readValue(jsonString,
					new TypeReference<HashMap<String, Boolean>>() {
					});

		}

		catch (JsonParseException e) {
			log.error("Error to establish connection while uploading images.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorInParsingResponseStringAS");
		}

		catch (JsonMappingException e) {
			log.error("Error to establish connection while uploading images.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorInParsingResponseStringAS");
		}

		catch (IOException e) {
			log.error("Error to establish connection while uploading images.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorInParsingResponseStringAS");
		}

		catch (UniformInterfaceException e) {

			log.error("Error to establish connection while uploading images.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
		}

		catch (ClientHandlerException e) {

			log.error("Error to establish connection while uploading images.");
			log.error("Exception : " + e);
			if (e.toString().contains(
					"java.net.ConnectException: Connection refused")) {
				ErrorMessage.displayMessage('E', "errorInASConnection");
			} else {
				ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
			}
		}

		catch (Exception e) {

			log.error("Error to establish connection while uploading images.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
		}

		return imageUploadResultMap;
	}

	/**
	 * Method to check if the node IDs contained by provided map is updated by
	 * any other user. Here provided map is an association of node id (key) and
	 * its last update time (value on client side).
	 * 
	 * @param nodeUpdateTimeMap
	 *            - {@link HashMap} of node id (key) and its last update time
	 *            (value on client side)
	 * @return TRUE if any of the node id in provided map is updated by other
	 *         user, i.e., its last update time in map and in DB are different.
	 *         FALSE otherwise.
	 */
	public boolean checkIfNodeIsUpdatedByOtherUser(
			HashMap<String, String> nodeUpdateTimeMap) {

		boolean isNodeUpdated = true;

		try {

// ------ KONS COMMENT OUT
//			ClientResponse response = ConnectionUtil.getWebService()
//					.path("scanDo")
//					.path("checkIfNodeIsUpdatedByOtherUser")
//					.type(MediaType.APPLICATION_JSON_TYPE)
//					.accept(MediaType.APPLICATION_JSON_TYPE)
//					.post(ClientResponse.class, nodeUpdateTimeMap);
//
//			isNodeUpdated = (Boolean) response.getEntity(Boolean.class);
// ------ END KONS COMMENT OUT
                    
                        isNodeUpdated = false;
                        
		}

		catch (UniformInterfaceException e) {

			log.error("Error to establish connection while check node is updated by other user.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
		}

		catch (ClientHandlerException e) {

			log.error("Error to establish connection while check node is updated by other user.");
			log.error("Exception : " + e);

			if (e.toString().contains(
					"java.net.ConnectException: Connection refused")) {
				ErrorMessage.displayMessage('E', "errorInASConnection");
			} else {
				ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
			}
		}

		catch (Exception e) {

			log.error("Error to establish connection while checking if node is updated by other user.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
		}

		return isNodeUpdated;
	}

	public int getChildNodeCount(String nodeId) 
	{
		int childNodeCount=-1;
		try {

			ClientResponse response = ConnectionUtil.getWebService()
					.path("nodeServices")
					.path("getChildNodeCount")
					.type(MediaType.APPLICATION_JSON_TYPE)
					.accept(MediaType.APPLICATION_JSON_TYPE)
					.post(ClientResponse.class, nodeId);

			childNodeCount = (Integer) response.getEntity(Integer.class);
		}

		catch (UniformInterfaceException e) {

			log.error("Error to establish connection while getting child node count for node: " + nodeId);
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
		}

		catch (ClientHandlerException e) {

			log.error("Error to establish connection while getting child node count for node: " + nodeId);
			log.error("Exception : " + e);

			if (e.toString().contains(
					"java.net.ConnectException: Connection refused")) {
				ErrorMessage.displayMessage('E', "errorInASConnection");
			} else {
				ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
			}
		}

		catch (Exception e) {

			log.error("Error to establish connection while getting child node count for node: " + nodeId);
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
		}
		return childNodeCount;

	}
	

	public String getEncodedImageStringForActualImage (String nodeId) 
	{
		String encodedImageStringForActualImage = "";
		try {

			ClientResponse response = ConnectionUtil.getWebService()
					.path("nodeServices")
					.path("getEncodedActualImageString")
					.type(MediaType.APPLICATION_JSON_TYPE)
					.accept(MediaType.APPLICATION_JSON_TYPE)
					.post(ClientResponse.class, nodeId);

			encodedImageStringForActualImage = (String) response.getEntity(String.class);
		}

		catch (UniformInterfaceException e) {

			log.error("Error to establish connection while getting child node count for node: " + nodeId);
			log.error("Exception : " + e);
			
			encodedImageStringForActualImage = "";

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
		}

		catch (ClientHandlerException e) {

			log.error("Error to establish connection while getting child node count for node: " + nodeId);
			log.error("Exception : " + e);

			encodedImageStringForActualImage = "";
			
			if (e.toString().contains(
					"java.net.ConnectException: Connection refused")) {
				ErrorMessage.displayMessage('E', "errorInASConnection");
			} else {
				ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
			}
		}

		catch (Exception e) {

			log.error("Error to establish connection while getting child node count for node: " + nodeId);
			log.error("Exception : " + e);

			encodedImageStringForActualImage = "";
			
			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
		}
		
		return encodedImageStringForActualImage;
	}
        
        /**
         * Method to get the Project corresponding to bar code entered by user
         * @param barcode
	 * @return project  if any project exists in DB having the bar code provided.
         */
        public boolean getProjectByBarcode(String barcode) {

            MultivaluedMap requestData = new MultivaluedMapImpl();
            requestData.add("barcode", barcode);
		try {
			ClientResponse response = ConnectionUtil.getWebService()
					.path("scanDo").path("getProjectBybarcode")
					.type(MediaType.APPLICATION_JSON_TYPE)
					.accept(MediaType.APPLICATION_JSON_TYPE)
					.post(ClientResponse.class, requestData);

                        String s = response.getEntity(String.class);
                        JSONObject jsonObj = new JSONObject(s);
                        SessionUtil.getSessionData().setProjectId(jsonObj.getString("projectId"));
                        SessionUtil.getSessionData().setProjectName(jsonObj.getString("projectName"));

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
            return true;

	}
        
}

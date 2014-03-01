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

public class CollectionPanelHelper extends ClientHelper {
	private static Logger log = Logger.getLogger(CollectionPanelHelper.class);

	private ObjectMapper mapper = new ObjectMapper();

	public ArrayList initializeScreen() {
		ArrayList initData = new ArrayList();

		try {

		} catch (Exception e) {

		}

		return initData;
	}

	public NodeProperties saveNode(NodeProperties nodeToSave) {

		NodeProperties nodeProperties = null;

		try {
			ClientResponse response = ConnectionUtil.getWebService()
					.path("nodeServices").path("updateNodeProperties")
					.type(MediaType.APPLICATION_JSON_TYPE)
					.accept(MediaType.APPLICATION_JSON_TYPE)
					.post(ClientResponse.class, nodeToSave);

			nodeProperties = (NodeProperties) response
					.getEntity(NodeProperties.class);

		}

		catch (UniformInterfaceException e) {

			log.error("Error while saving node properties.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
		}

		catch (ClientHandlerException e) {

			log.error("Error while saving node properties.");
			log.error("Exception : " + e);

			if (e.toString().contains(
					"java.net.ConnectException: Connection refused")) {
				ErrorMessage.displayMessage('E', "errorInASConnection");
			} else {
				ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
			}
		}

		catch (Exception e) {

			log.error("Error while saving node properties.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
		}

		return nodeProperties;
	}

	/**
	 * Method to save the list of the node properties in the DB.
	 * 
	 * @param nodePropertiesList
	 * @return
	 */
	public ArrayList<NodeProperties> saveNodePropertiesList(
			ArrayList<NodeProperties> nodePropertiesList) {

		ArrayList<NodeProperties> listOfNodeProperties = null;

		try {
			ClientResponse response = ConnectionUtil.getWebService()
					.path("nodeServices").path("updateNodePropertiesList")
					.type(MediaType.APPLICATION_JSON_TYPE)
					.accept(MediaType.APPLICATION_JSON_TYPE)
					.post(ClientResponse.class, nodePropertiesList);

			String jsonString = response.getEntity(String.class);

			listOfNodeProperties = mapper.readValue(jsonString,
					new TypeReference<ArrayList<NodeProperties>>() {
					});

		}

		catch (JsonParseException e) {
			log.error("Error while saving list of node properties.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorInParsingResponseStringAS");
		}

		catch (JsonMappingException e) {
			log.error("Error while saving list of node properties.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorInParsingResponseStringAS");
		}

		catch (IOException e) {
			log.error("Error while saving list of node properties.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorInParsingResponseStringAS");
		}

		catch (UniformInterfaceException e) {

			log.error("Error while saving list of node properties.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
		}

		catch (ClientHandlerException e) {

			log.error("Error while saving list of node properties.");
			log.error("Exception : " + e);

			if (e.toString().contains(
					"java.net.ConnectException: Connection refused")) {
				ErrorMessage.displayMessage('E', "errorInASConnection");
			} else {
				ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
			}
		}

		catch (Exception e) {

			log.error("Error while saving list of node properties.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
		}

		return listOfNodeProperties;
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

			ClientResponse response = ConnectionUtil.getWebService()
					.path("scanDo")
					.path("checkIfNodeIsUpdatedByOtherUser")
					.type(MediaType.APPLICATION_JSON_TYPE)
					.accept(MediaType.APPLICATION_JSON_TYPE)
					.post(ClientResponse.class, nodeUpdateTimeMap);

			isNodeUpdated = (Boolean) response.getEntity(Boolean.class);

		}

		catch (UniformInterfaceException e) {

			log.error("Error to establish connection while checking node is updated by other user.");
			log.error("Exception : " + e);

			ErrorMessage.displayMessage('E', "errorGeneratedOnAS");
		}

		catch (ClientHandlerException e) {

			log.error("Error to establish connection while checking node is updated by other user.");
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

	public List<NodeProperties> fetchChildNodeList(ArrayList<String> idList) {
		ArrayList<NodeProperties> childNodePropertiesList = null;

		try {
			ClientResponse response = ConnectionUtil.getWebService()
					.path("nodeServices").path("fetchChildNodeList")
					.type(MediaType.APPLICATION_JSON_TYPE)
					.accept(MediaType.APPLICATION_JSON_TYPE)
					.post(ClientResponse.class, idList);

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
	 * Method to fetch hierarchy from DB for the provided barcode.
	 * 
	 * @param searchBarcode
	 * @return
	 */
	public String getHierarchyFromSearchBarcode(String searchBarcode) {

		String hierarchy = null;
		ArrayList<String> param = new ArrayList<String>();
		param.add(SessionUtil.getSessionData().getProjectId());
		param.add(searchBarcode);

		try {
			ClientResponse response = ConnectionUtil.getWebService()
					.path("nodeServices").path("getHierarchyFromSearchBarcode")
					.type(MediaType.APPLICATION_JSON_TYPE)
					.accept(MediaType.APPLICATION_JSON_TYPE)
					.post(ClientResponse.class, param);

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
}

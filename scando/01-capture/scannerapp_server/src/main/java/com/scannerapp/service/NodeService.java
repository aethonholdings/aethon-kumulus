package com.scannerapp.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.scannerapp.service.impl.NodeServiceImpl;
import com.scannerapp.shared.NodeProperties;

/**
 * Service class to execute services at the server side and thereby to cater the
 * request from client regarding operations to be performed for the node.
 * 
 * @author Shalin Shah<shalin.shah@spec-india.com>
 * @since 16/Jul/2013
 */
@Path("nodeServices")
public class NodeService {

	private NodeServiceImpl nodeServiceImpl = new NodeServiceImpl();

	/**
	 * Method to add/update/delete provided node properties from the NODES table
	 * in DB.
	 * 
	 * @param nodeProperties
	 * @return
	 */
	@POST
	@Path("updateNodeProperties")
	public NodeProperties updateNodeProperties(NodeProperties nodeProperties) {

		return nodeServiceImpl.updateNodeProperties(nodeProperties);
	}

	/**
	 * Method to fetch the list of node properties of child nodes for provided
	 * id list which contains project id and parent node id. The list also
	 * contains the range (From and To value) of document sequence number in
	 * order to fetch child nodes(images) in batch.
	 * 
	 * @param parentId
	 * @return
	 */
	@POST
	@Path("fetchChildNodeList")
	public ArrayList<NodeProperties> fetchChildNodeList(ArrayList<String> idList) {

		String fromDocumentSequenceNumberStr = "-1";
		String toDocumentSequenceNumberStr = "-1";

		String projectId = idList.get(0);
		String parentNodeId = idList.get(1);

		if (idList.size() == 4) {
			fromDocumentSequenceNumberStr = idList.get(2);
			toDocumentSequenceNumberStr = idList.get(3);
		}

		return nodeServiceImpl.fetchChildNodeList(projectId, parentNodeId,
				fromDocumentSequenceNumberStr, toDocumentSequenceNumberStr);
	}

	/**
	 * Method to save the list properties provided in array list.
	 * 
	 * @param nodePropertiesList
	 * @return
	 */
	@POST
	@Path("updateNodePropertiesList")
	public ArrayList<NodeProperties> updateNodePropertiesList(
			ArrayList<NodeProperties> nodePropertiesList) {

		return nodeServiceImpl.updateNodePropertiesList(nodePropertiesList);
	}

	/**
	 * Method to fetch the hierarchy from DB for the provided list of project id
	 * and search barcode.
	 * 
	 * @param searchParameters
	 * @return
	 */
	@POST
	@Path("getHierarchyFromSearchBarcode")
	public String getHierarchyFromSearchBarcode(
			ArrayList<String> searchParameters) {

		return nodeServiceImpl.getHierarchyFromSearchBarcode(searchParameters);
	}

	/**
	 * Method to store the scanned images and insert node properties in the DB.
	 * 
	 * @param scannedImageNodePropertiesList
	 * @return
	 */
	@POST
	@Path("saveScannedImages")
	public HashMap<String, Boolean> saveScannedImages(
			ArrayList<NodeProperties> scannedImageNodePropertiesList) {

		return nodeServiceImpl
				.saveScannedImages(scannedImageNodePropertiesList);
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
	@POST
	@Path("checkIfNodeIsUpdatedByOtherUser")
	public Boolean checkIfNodeIsUpdatedByOtherUser(
			HashMap<String, String> nodeUpdateTimeMap) {

		return nodeServiceImpl
				.checkIfNodeIsUpdatedByOtherUser(nodeUpdateTimeMap);
	}

	/**
	 * Method to get the count of child nodes for provided parent node id.
	 * 
	 * @param parentNodeId
	 * @return
	 */
	@POST
	@Path("getChildNodeCount")
	public Integer getChildNodeCount(String parentNodeId) {

		return nodeServiceImpl.getChildNodeCount(parentNodeId);
	}

	/**
	 * Method to fetch the encoded string of actual image for provided node id.
	 * 
	 * @param nodeId
	 * @return
	 */
	@POST
	@Path("getEncodedActualImageString")
	public String getEncodedActualImageString(String nodeId) {

		return nodeServiceImpl.getEncodedActualImageString(nodeId);
	}
}

package com.scannerapp.dao;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.log4j.Logger;

import com.scannerapp.shared.NodeProperties;
import com.scannerapp.shared.TransactionConstant;
import com.scannerapp.ws.common.exception.BusinessException;
import com.scannerapp.ws.common.exception.FatalException;
import com.scannerapp.ws.common.util.configuration.ApplicationConstants;
import com.scannerapp.ws.common.util.db.DbConstant;
import com.scannerapp.ws.common.util.db.QueryReader;
import com.scannerapp.ws.common.util.log.MessageLogger;
import com.sun.jersey.core.util.Base64;

/**
 * DAO class to interact with the DB for Node Properties related operations.
 * 
 * @author Shalin Shah<shalin.shah@spec-india.com>
 * @since 16/July/2013
 */
public class NodeDAO extends BaseDAO {

	private Logger logger = MessageLogger.getLogger("NodeDAO");

	/**
	 * Method to insert the node properties in the database.
	 * 
	 * @param nodeProperties
	 * @param connection
	 * @return
	 * @throws BusinessException
	 * @throws FatalException
	 */
	public synchronized boolean insertNodeProperties(
			NodeProperties nodeProperties, Connection connection)
			throws BusinessException, FatalException {

		boolean isSuccess = false;

		String queryKeyNameInPropertiesFile = "insertNodeProperties.sql";
		String query = null;
		String valueForNewDocumentSequenceNumber = null;

		// SETTING VALUE FOR GETTING NEXT DOCUMENT SEQUENCE FOR IMAGE NODE...
		if (nodeProperties.getActualImageName() != null
				&& nodeProperties.getActualImageName().length() > 0
				&& nodeProperties.getThumbnailImageName() != null
				&& nodeProperties.getThumbnailImageName().length() > 0) {

			queryKeyNameInPropertiesFile = "insertImageProperties.sql";

			// Setting parent id so insert new document sequence number. Check
			// query in file.
			valueForNewDocumentSequenceNumber = nodeProperties
					.getParentNodeId();
		}

		// INSERTING NODE PROPERTIES...
		Object[] params = new Object[14];
		int index = 0;

		params[index++] = nodeProperties.getProjectId(); // PROJECT_ID
		params[index++] = nodeProperties.getName(); // NAME
		params[index++] = nodeProperties.getType(); // TYPE
		params[index++] = nodeProperties.getBarcode(); // BARCODE
		params[index++] = nodeProperties.getComment(); // COMMENT
		params[index++] = nodeProperties.getInternalComment(); // INTERNAL_COMMENT
		params[index++] = nodeProperties.getStatus(); // STATUS
		params[index++] = nodeProperties.getParentNodeId(); // PARENT_NODE_ID
		params[index++] = nodeProperties.getHierarchy(); // HIERARCHY
		params[index++] = nodeProperties.getThumbnailImageName(); // THUMBNAIL_IMAGE_NAME
		params[index++] = nodeProperties.getActualImageName(); // ACTUAL_IMAGE_NAME
		params[index++] = nodeProperties.getUserId(); // CREATOR_ID
		params[index++] = nodeProperties.getUserId(); // LAST_UPDATE_ID
		params[index++] = valueForNewDocumentSequenceNumber; // document_sequence_number

		query = QueryReader.getQueryFromPropertyFile(DbConstant.NODE_SQL_FILE,
				queryKeyNameInPropertiesFile);

		logger.info("Query To Insert Node Properties : " + query
				+ "     PARAMETERS : " + Arrays.toString(params));

		long insertedRow = getSqlUtil().dmlExecutor(connection, query, params);

		if (insertedRow > 0) {
			isSuccess = true;
		} else {
			isSuccess = false;
		}

		return isSuccess;
	}

	/**
	 * Method to fetch node properties like node id, document sequence number,
	 * last update date and time from DB for the lasted inserted node with
	 * provided node properties POJO.
	 * 
	 * @param nodeProperties
	 * @throws BusinessException
	 * @throws Exception
	 */
	public void getNodePropertiesForInsertedNode(NodeProperties nodeProperties)
			throws BusinessException, Exception {

		String query = null;

		Object[] params = new Object[4];
		params[0] = nodeProperties.getName(); // NAME
		params[1] = nodeProperties.getProjectId(); // PROJECT_ID
		params[2] = nodeProperties.getParentNodeId(); // PARENT_NODE_ID
		params[3] = nodeProperties.getUserId(); // CREATOR_ID

		query = QueryReader.getQueryFromPropertyFile(DbConstant.NODE_SQL_FILE,
				"getNodeIdAndUpdateTime.sql");

		logger.info("Query To Get Node Id, Update Date Time And Document Sequence Number Of Inserted Row : "
				+ query + "     PARAMETERS : " + Arrays.toString(params));

		Map<String, Object> result = getSqlUtil().getQueryMap(query, params);

		nodeProperties.setNodeId(getStringValue(result.get("node_id")));
		nodeProperties.setLastUpdateDateTime(getStringValue(result
				.get("lastUpdateDateTime")));
		nodeProperties.setDocumentSequenceNumber(getStringValue(result
				.get("document_sequence_number")));

		// SETTING UPDATE FLAG BECAUSE POJO WILL BE TRAVELLED & PERSISTED AT
		// CLIENT SIDE TO REPRESENT INSERTED ROW IN DB.
		nodeProperties.setTransactionStatus(TransactionConstant.UPDATE);
	}

	/**
	 * Method to update the node properties in the database.
	 * 
	 * @param nodeProperties
	 * @param connection
	 * @return
	 * @throws BusinessException
	 * @throws FatalException
	 */
	public boolean updateNodeProperties(NodeProperties nodeProperties,
			Connection connection) throws BusinessException, FatalException {

		String query = null;

		boolean isSuccess = false;
		String nextDocumentSequenceNumber = null;

		Object[] params = null;

		// FETCHING NEXT DOCUMENT SEQUENCE IN DB FOR IMAGE NODE WHEN IMAGE IS
		// CUT FROM ONE NODE AND PASTED IN ANOTHER...
		if (nodeProperties.getActualImageName() != null
				&& nodeProperties.getActualImageName().length() > 0
				&& nodeProperties.getOldActualImageName() != null
				&& nodeProperties.getOldActualImageName().length() > 0
				&& !nodeProperties.getOldActualImageName().equals(
						nodeProperties.getActualImageName())) {

			params = new Object[1];
			params[0] = nodeProperties.getParentNodeId();

			query = QueryReader.getQueryFromPropertyFile(
					DbConstant.NODE_SQL_FILE,
					"getNextDocumentSequenceNumber.sql");

			logger.info("Query To Get Next Document Sequence Number : " + query
					+ "     PARAMETERS : " + Arrays.toString(params));

			Map<String, Object> result = getSqlUtil().getQueryMap(connection,
					query, params);

			if (result != null && result.size() > 0) {

				nextDocumentSequenceNumber = getStringValue(result
						.get("nextDocumentSequenceNumber"));
			}
		}

		// UPDATING NODE PROPERTIES...
		params = new Object[14];
		int index = 0;

		params[index++] = nodeProperties.getProjectId(); // PROJECT_ID
		params[index++] = nodeProperties.getName(); // NAME
		params[index++] = nodeProperties.getType(); // TYPE
		params[index++] = nodeProperties.getBarcode(); // BARCODE
		params[index++] = nodeProperties.getComment(); // COMMENT
		params[index++] = nodeProperties.getInternalComment(); // INTERNAL_COMMENT
		params[index++] = nodeProperties.getStatus(); // STATUS
		params[index++] = nodeProperties.getParentNodeId(); // PARENT_NODE_ID
		params[index++] = nodeProperties.getHierarchy(); // HIERARCHY
		params[index++] = nodeProperties.getThumbnailImageName(); // THUMBNAIL_IMAGE_NAME
		params[index++] = nodeProperties.getActualImageName(); // ACTUAL_IMAGE_NAME
		params[index++] = nodeProperties.getUserId(); // LAST_UPDATE_ID
		params[index++] = (nextDocumentSequenceNumber == null) ? nodeProperties
				.getDocumentSequenceNumber() : nextDocumentSequenceNumber; // DOCUMENT_SEQUENCE_NUMBER
		params[index++] = nodeProperties.getNodeId(); // NODE_ID

		query = QueryReader.getQueryFromPropertyFile(DbConstant.NODE_SQL_FILE,
				"updateNodeProperties.sql");

		logger.info("Query To Update Node Properties : " + query
				+ "     PARAMETERS : " + Arrays.toString(params));

		long updatedRow = getSqlUtil().dmlExecutor(connection, query, params);

		if (updatedRow > 0) {

			isSuccess = true;

			params = new Object[1];
			params[0] = nodeProperties.getNodeId(); // NODE_ID

			query = QueryReader
					.getQueryFromPropertyFile(DbConstant.NODE_SQL_FILE,
							"getUpdateTimeOnUpdatingNode.sql");

			logger.info("Query To Get Update Date Time While Updating Node : "
					+ query + "     PARAMETERS : " + Arrays.toString(params));

			Map<String, Object> result = getSqlUtil().getQueryMap(connection,
					query, params);

			nodeProperties.setLastUpdateDateTime(getStringValue(result
					.get("lastUpdateDateTime")));

			nodeProperties.setTransactionStatus(TransactionConstant.UPDATE);

		} else {
			isSuccess = false;
		}

		return isSuccess;
	}

	/**
	 * Method to delete the node properties in the database.
	 * 
	 * @param nodeProperties
	 * @param connection
	 * @return
	 * @throws BusinessException
	 * @throws FatalException
	 */
	public boolean deleteNodeProperties(NodeProperties nodeProperties,
			Connection connection) throws BusinessException, FatalException {

		String query = null;

		boolean isSuccess = false;

		Object[] params = new Object[1];
		int index = 0;

		params[index++] = nodeProperties.getNodeId(); // NODE_ID

		query = QueryReader.getQueryFromPropertyFile(DbConstant.NODE_SQL_FILE,
				"deleteNodeProperties.sql");

		logger.info("Query To Delete Node Properties : " + query
				+ "     PARAMETERS : " + Arrays.toString(params));

		long deletedRow = getSqlUtil().dmlExecutor(connection, query, params);

		if (deletedRow > 0)
			isSuccess = true;
		else
			isSuccess = false;

		return isSuccess;

	}

	/**
	 * Method to fetch the list of node properties of child nodes for provided
	 * project id and parent node id and (in case of fetching images) whose
	 * document sequence number falls between provided from and to document
	 * sequence number. If the node properties POJO are to be fetched for any
	 * node different than image, the value of from and to document sequence
	 * number will be -1 and hence will not be effective.
	 * 
	 * @param projectId
	 * @param parentNodeId
	 * @param fromDocumentSequenceNumber
	 * @param toDocumentSequenceNumber
	 * @return
	 */
	public ArrayList<NodeProperties> fetchChildNodeList(String projectId,
			String parentNodeId, Integer fromDocumentSequenceNumber,
			Integer toDocumentSequenceNumber) throws BusinessException,
			FatalException, Exception {

		ArrayList<NodeProperties> nodePropertiesList = new ArrayList<NodeProperties>();
		Object[] params = null;

		String query = QueryReader.getQueryFromPropertyFile(
				DbConstant.NODE_SQL_FILE, "fetchChildNodeList.sql");

		if (parentNodeId != null) {

			query = query + " AND parent_node_id = ?";

			if (fromDocumentSequenceNumber > 0 && toDocumentSequenceNumber > 0) {

				query = query + " AND document_sequence_number >= ?"
						+ " AND document_sequence_number <= ?";

				params = new Object[4];

				params[0] = projectId; // PROJECT_ID
				params[1] = parentNodeId; // PARENT_NODE_ID
				params[2] = fromDocumentSequenceNumber; // DOCUMENT_SEQUENCE_NUMBER
				params[3] = toDocumentSequenceNumber; // DOCUMENT_SEQUENCE_NUMBER

			} else {

				params = new Object[2];
				params[0] = projectId; // PROJECT_ID
				params[1] = parentNodeId; // PARENT_NODE_ID
			}

		} else {

			query = query + " AND parent_node_id IS NULL";

			params = new Object[1];
			params[0] = projectId; // PROJECT_ID
		}

		// ADDING ORDER TO THE QUERY...
		query = query + "  ORDER BY  document_sequence_number ASC, node_id ASC";

		logger.info("Query To Fetch Child Node Properties : " + query
				+ "     PARAMETERS : " + Arrays.toString(params));

		// EXECUTING QUERY TO GET RESULT...
		logger.info("Beginning To Execute Query.");
		List<Map<String, Object>> resultList = getSqlUtil().getQueryMaps(query,
				params);
		logger.info("Query Execution Finished.");

		if (resultList != null && resultList.size() > 0) {

			logger.info("Beginning To Fill Node Properties & Fetch Image From FTP If Required.");

			FTPClient ftpClient = null;

			try {
				// INITIALIZING FTP CONNECTION...
				ftpClient = initFTPClient();

				// ITERATING THROUGH LOOP...
				for (int index = 0; index < resultList.size(); index++) {

					Map<String, Object> result = resultList.get(index);

					NodeProperties nodeProperties = new NodeProperties();

					nodeProperties.setNodeId(getStringValue(result
							.get("node_id")));
					nodeProperties.setProjectId(getStringValue(result
							.get("project_id")));
					nodeProperties.setName(getStringValue(result.get("name")));
					nodeProperties.setType(getStringValue(result.get("type")));
					nodeProperties.setBarcode(getStringValue(result
							.get("barcode")));
					nodeProperties.setComment(getStringValue(result
							.get("comment")));
					nodeProperties.setInternalComment(getStringValue(result
							.get("internal_comment")));
					nodeProperties.setStatus(getStringValue(result
							.get("status")));
					nodeProperties.setParentNodeId(getStringValue(result
							.get("parent_node_id")));
					nodeProperties.setHierarchy(getStringValue(result
							.get("hierarchy")));

					nodeProperties
							.setOldThumbnailImageName(getStringValue(result
									.get("thumbnail_image_name")));
					nodeProperties.setThumbnailImageName(getStringValue(result
							.get("thumbnail_image_name")));

					nodeProperties.setOldActualImageName(getStringValue(result
							.get("actual_image_name")));
					nodeProperties.setActualImageName(getStringValue(result
							.get("actual_image_name")));

					nodeProperties
							.setDocumentSequenceNumber(getStringValue(result
									.get("document_sequence_number")));

					nodeProperties.setLastUpdateDateTime(getStringValue(result
							.get("lastUpdateDateTime")));

					// FETCHING IMAGE/THUMBNAIL FROM FTP & SETTING ENCODING IT
					// IN STRING...
					if (nodeProperties.getActualImageName() != null
							&& nodeProperties.getActualImageName().length() > 0
							&& nodeProperties.getThumbnailImageName() != null
							&& nodeProperties.getThumbnailImageName().length() > 0) {

						// FETCHING ACTUAL IMAGE
						// String imagePath =
						// nodeProperties.getActualImageName();
						//
						// imagePath = imagePath.replace('\\',
						// File.separatorChar);
						// imagePath = imagePath.replace('/',
						// File.separatorChar);
						//
						// nodeProperties.setEncodeStringForImage(getEncodeImageStringFromFTP(imagePath,
						// ftpClient));

						// FETCHING THUMBNAIL IMAGE
						String imagePath = nodeProperties
								.getThumbnailImageName();

						imagePath = imagePath.replace('\\', File.separatorChar);
						imagePath = imagePath.replace('/', File.separatorChar);

						nodeProperties
								.setEncodeStringForThumbnail(getEncodeImageStringFromFTP(
										imagePath, ftpClient));
					}

					nodeProperties
							.setTransactionStatus(TransactionConstant.UPDATE);

					nodePropertiesList.add(nodeProperties);
				}

			} catch (Exception e) {

				throw e;

			} finally {

				// DISCONNECTING FTP...
				if (ftpClient != null)
					disconnectFTPClient(ftpClient);
			}

			logger.info("Finished Filling Node Properties.");
		}

		return nodePropertiesList;
	}

	/**
	 * Method to fetch the hierarchy from DB for the provided project id and
	 * search barcode.
	 * 
	 * @param projectId
	 * @param searchBarcode
	 * @return
	 * @throws BusinessException
	 * @throws FatalException
	 */
	public String getHierarchyFromSearchBarcode(String projectId,
			String searchBarcode) throws BusinessException, Exception {

		String query = null;

		String hierarchy = "";

		Object[] params = new Object[2];
		params[0] = projectId;
		params[1] = searchBarcode;

		query = QueryReader.getQueryFromPropertyFile(DbConstant.NODE_SQL_FILE,
				"getHierarchyFromSearchBarcode.sql");

		logger.info("Query To Fetch Hierarchy From Search Barcode : " + query
				+ "     PARAMETERS : " + Arrays.toString(params));

		Map<String, Object> result = getSqlUtil().getQueryMap(query, params);

		if (result != null && result.size() > 0) {

			hierarchy = getStringValue(result.get("hierarchy"));
		}

		return hierarchy;
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
	public Boolean checkIfNodeIsUpdatedByOtherUser(
			HashMap<String, String> nodeUpdateTimeMap)
			throws BusinessException, Exception {

		boolean isNodeUpdated = false;

		if (nodeUpdateTimeMap == null || nodeUpdateTimeMap.size() == 0)
			return isNodeUpdated;

		ArrayList<String> nodeIdList = new ArrayList<String>(
				nodeUpdateTimeMap.keySet());

		String query = QueryReader
				.getQueryFromPropertyFile(DbConstant.NODE_SQL_FILE,
						"checkIfNodeIsUpdatedByOtherUser.sql");

		query = query + getQueryInClause("WHERE", "node_id", nodeIdList);

		logger.info("Query To Check If Node Is Updated By Other User : "
				+ query);

		List<Map<String, Object>> resultList = getSqlUtil().getQueryMaps(query,
				(Object[]) null);

		if (resultList != null && resultList.size() > 0) {

			for (int index = 0; index < resultList.size(); index++) {

				Map<String, Object> result = resultList.get(index);

				String nodeId = getStringValue(result.get("node_id"));
				String lastUpdateTime = getStringValue(result
						.get("lastUpdateDateTime"));

				if (!nodeUpdateTimeMap.get(nodeId).equals(lastUpdateTime)) {
					isNodeUpdated = true;
					break;
				}
			}
		}

		return isNodeUpdated;
	}

	/**
	 * Method to get the count of child nodes for provided parent node id.
	 * 
	 * @param parentNodeId
	 * @return
	 * @throws BusinessException
	 * @throws Exception
	 */
	public Integer getChildNodeCount(String parentNodeId)
			throws BusinessException, Exception {

		int childNodeCount = -1;

		if (parentNodeId == null || parentNodeId.length() == 0)
			return childNodeCount;

		String query = null;

		Object[] params = new Object[1];
		params[0] = parentNodeId;

		query = QueryReader.getQueryFromPropertyFile(DbConstant.NODE_SQL_FILE,
				"getChildNodeCount.sql");

		logger.info("Query To Get Child Node Count : " + query
				+ "     PARAMETERS : " + Arrays.toString(params));

		Map<String, Object> result = getSqlUtil().getQueryMap(query, params);

		if (result != null && result.size() > 0) {

			childNodeCount = Integer.parseInt(getStringValue(result
					.get("childNodeCount")));
		}

		return childNodeCount;
	}

	/**
	 * Method to fetch the encoded string of actual image for provided node id.
	 * 
	 * @param nodeId
	 * @return
	 */
	public String getEncodedActualImageString(String nodeId)
			throws BusinessException, Exception {

		String encodedActualImageString = "";

		if (nodeId == null || nodeId.length() == 0)
			return encodedActualImageString;

		String query = null;

		Object[] params = new Object[1];
		params[0] = nodeId;

		query = QueryReader.getQueryFromPropertyFile(DbConstant.NODE_SQL_FILE,
				"getActualImageName.sql");

		logger.info("Query To Get Actual Image Name : " + query
				+ "     PARAMETERS : " + Arrays.toString(params));

		Map<String, Object> result = getSqlUtil().getQueryMap(query, params);

		if (result != null && result.size() > 0) {

			FTPClient ftpClient = null;

			try {
				// INITIALIZING FTP CONNECTION...
				ftpClient = initFTPClient();

				String imagePath = getStringValue(result
						.get("actual_image_name"));

				imagePath = imagePath.replace('\\', File.separatorChar);
				imagePath = imagePath.replace('/', File.separatorChar);

				encodedActualImageString = getEncodeImageStringFromFTP(
						imagePath, ftpClient);

			} catch (Exception e) {

				throw e;

			} finally {

				// DISCONNECTING FTP...
				if (ftpClient != null)
					disconnectFTPClient(ftpClient);
			}
		}

		return encodedActualImageString;
	}

	// ***************** UTILITY METHOD *****************

	/**
	 * Method to fetch the image via FTP from specified path and encode it in
	 * String.
	 * 
	 * @param imagePath
	 * @return Encoded image string for image.
	 */
	public String getEncodeImageStringFromFTP(String imagePath,
			FTPClient ftpClient) throws Exception {

		InputStream in = null;
		BufferedInputStream inbf = null;
		ByteArrayOutputStream os = null;

		try {
			// FTPClient ftpClient = initFTPClient();

			byte buffer[] = new byte[1024];

			try {
				// logger.info("Beginning to fetch image stream from FTP Client. Image Path : "
				// + imagePath);

				in = ftpClient.retrieveFileStream(imagePath);

				// logger.info("Image stream fetched from FTP Client. Image Path : "
				// + imagePath);
			} catch (FTPConnectionClosedException e) {
				logger.error("FTP Connection Closed. Error while retrieving image file stream from FTP.");
				throw e;
			} catch (IOException ioException) {
				logger.error("Error while retrieving image file stream from FTP.");
				throw ioException;
			}

			inbf = new BufferedInputStream(in);
			os = new ByteArrayOutputStream();

			int readCount;
			byte result[] = null;

			try {
				// logger.info("Writing image stream from FTP Client to encode into String.");

				while ((readCount = inbf.read(buffer)) > 0) {
					os.write(buffer, 0, readCount);
				}
			} catch (IOException ioException) {
				logger.error("Error while reading the byte stream of image file stream from buffered input stream.");
				throw ioException;
			}

			result = os.toByteArray();

			byte[] encode = Base64.encode(result);

			String encodedImageString = new String(encode);

			// disconnectFTPClient(ftpClient);

			return encodedImageString;

		} catch (IOException ioException) {
			logger.error("Error while retrieving image from FTP.");
			logger.error("Exception : " + ioException);

			throw ioException;
		}

		catch (Exception exception) {
			logger.error("Error while retrieving image from FTP.");
			logger.error("Exception : " + exception);

			throw exception;
		}

		finally {

			if (os != null) {
				try {
					os.close();
				} catch (IOException ioException) {
					logger.error("Error while closing output stream in finally block.");
					throw ioException;
				}
			}

			if (inbf != null) {
				try {
					inbf.close();
				} catch (IOException ioException) {
					logger.error("Error while closing input buffer stream in finally block.");
					throw ioException;
				}
			}

			if (in != null) {
				try {
					in.close();
					ftpClient.completePendingCommand();
				} catch (IOException ioException) {
					logger.error("Error while closing input stream in finally block.");
					throw ioException;
				}
			}
		}
	}

	/**
	 * Method to initialize the FTP connection.
	 * 
	 * @return
	 */
	private FTPClient initFTPClient() throws Exception {

		String ftpURL = QueryReader.getQueryFromPropertyFile(
				ApplicationConstants.DB_CONN_CONST_FILE, "ftpUrl");
		String ftpUserName = QueryReader.getQueryFromPropertyFile(
				ApplicationConstants.DB_CONN_CONST_FILE, "ftpUser");
		String ftpPassword = QueryReader.getQueryFromPropertyFile(
				ApplicationConstants.DB_CONN_CONST_FILE, "ftpPassword");
		String ftpPort = QueryReader.getQueryFromPropertyFile(
				ApplicationConstants.DB_CONN_CONST_FILE, "ftpPort");

		FTPClient ftpClient = new FTPClient();

		try {
			logger.info("Initializing FTP Client...");

			ftpClient.connect(ftpURL, Integer.parseInt(ftpPort));

			if (!ftpClient.login(ftpUserName, ftpPassword))
				throw new Exception("Error while logging in for FTP.");

			logger.info("FTP Client Initialized...");

			// CONFIGURING FTP CLIENT...
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.setRemoteVerificationEnabled(false);

			logger.info("FTP Client Configured...");

		} catch (Exception e) {

			logger.error("Error while creating FTP connection.");
			logger.error("Exception : " + e);

			throw e;
		}

		return ftpClient;
	}

	/**
	 * Method to disconnect FTP.
	 * 
	 * @param ftpClient
	 */
	private void disconnectFTPClient(FTPClient ftpClient) {

		try {

			if (ftpClient != null && ftpClient.isConnected()) {
				ftpClient.logout();
				ftpClient.disconnect();
			}

		} catch (Exception e) {

			logger.error("Error while disconnecting FTP connection.");
			logger.error("Exception : " + e);
		}
	}

	/**
	 * Method to generate the condition part of query with IN clause.
	 * 
	 * @param conditionConnector
	 *            - Condition connector of query like "WHERE", "AND", "OR" etc.
	 * @param columnName
	 *            - Name of column for which condition with IN clause is to be
	 *            generated.
	 * @param inClauseParamList
	 *            - List of parameters for IN clause.
	 * @return - String with below format<br>
	 *         conditionConnector + columnName + IN ( + IN clause parameters
	 *         separated by comma + )<br>
	 *         <b>For example,</b> WHERE node_id IN ('10100', '10111')
	 */
	private String getQueryInClause(String conditionConnector,
			String columnName, ArrayList<String> inClauseParamList) {

		if (inClauseParamList == null || inClauseParamList.size() == 0)
			return "";

		String query = "  " + conditionConnector + " " + columnName + " IN (";

		for (int index = 0; index < inClauseParamList.size(); index++) {

			if (index < inClauseParamList.size() - 1)
				query = query + "'" + inClauseParamList.get(index) + "', ";
			else
				query = query + "'" + inClauseParamList.get(index) + "')";
		}

		return query;
	}

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

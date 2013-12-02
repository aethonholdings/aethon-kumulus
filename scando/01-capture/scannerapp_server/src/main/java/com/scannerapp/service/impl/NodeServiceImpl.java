package com.scannerapp.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.scannerapp.dao.BaseDAO;
import com.scannerapp.dao.DAOFactory;
import com.scannerapp.dao.NodeDAO;
import com.scannerapp.shared.NodeProperties;
import com.scannerapp.shared.TransactionConstant;
import com.scannerapp.ws.common.util.log.MessageLogger;

/**
 * Service implementation class for Node Service.
 * 
 * @author Shalin Shah<shalin.shah@spec-india.com>
 * @since 16/July/2013
 */
public class NodeServiceImpl extends BaseDAO {

	private Logger logger = MessageLogger.getLogger("NodeServiceImpl");

	private NodeDAO nodeDAO = DAOFactory.getNodeDAO();
	private ImageProcessServiceImpl imageProcessServiceImpl = new ImageProcessServiceImpl();

	/**
	 * Method to add/update/delete node properties in/from the NODES table in
	 * DB.
	 * 
	 * @param nodeProperties
	 * @return
	 */
	public NodeProperties updateNodeProperties(NodeProperties nodeProperties) {

		boolean isSuccess = false;

		Connection connection = null;

		try {

			connection = getSqlUtil().getConnectionFactory().getDBConnection();

			if (nodeProperties.getTransactionStatus() == TransactionConstant.INSERT) {

				isSuccess = nodeDAO.insertNodeProperties(nodeProperties,
						connection);

				if (isSuccess)
					connection.commit();
				else
					connection.rollback();

				nodeDAO.getNodePropertiesForInsertedNode(nodeProperties);

			} else if (nodeProperties.getTransactionStatus() == TransactionConstant.UPDATE) {

				isSuccess = nodeDAO.updateNodeProperties(nodeProperties,
						connection);

				if (isSuccess)
					connection.commit();
				else
					connection.rollback();

			} else if (nodeProperties.getTransactionStatus() == TransactionConstant.DELETE) {

				isSuccess = nodeDAO.deleteNodeProperties(nodeProperties,
						connection);

				if (isSuccess)
					connection.commit();
				else
					connection.rollback();
			}
		}

		// Exception while performing transaction...
		catch (Exception ex) {

			logger.error("Error while performing \""
					+ nodeProperties.getTransactionStatus()
					+ "\" operation for Node ID : "
					+ nodeProperties.getNodeId() + "   Node Name : "
					+ nodeProperties.getName() + "   Parent Node ID : "
					+ nodeProperties.getParentNodeId() + "   Project ID : "
					+ nodeProperties.getProjectId());

			logger.error("Exception : " + ex);

			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e) {
					logger.error("Error while rolling back the connection...");
					logger.error("Exception : " + e);
				}
			}
		}

		// Finally block to close the connection...
		finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
					logger.error("Error while closing the connection...");
					logger.error("Exception : " + e);
				}
			}
		}

		return nodeProperties;
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
	 * @param fromDocumentSequenceNumberStr
	 * @param toDocumentSequenceNumberStr
	 * @return
	 */
	public ArrayList<NodeProperties> fetchChildNodeList(String projectId,
			String parentNodeId, String fromDocumentSequenceNumberStr,
			String toDocumentSequenceNumberStr) {

		ArrayList<NodeProperties> nodePropertiesList = null;

		try {

			Integer fromDocumentSequenceNumber;
			Integer toDocumentSequenceNumber;

			try {
				fromDocumentSequenceNumber = Integer
						.valueOf(fromDocumentSequenceNumberStr);
				toDocumentSequenceNumber = Integer
						.valueOf(toDocumentSequenceNumberStr);
			} catch (NumberFormatException exception) {
				logger.error("NumberFormatException While Parsing   FromDocumentSequenceNumber : "
						+ fromDocumentSequenceNumberStr
						+ "   ToDocumentSequenceNumber : "
						+ toDocumentSequenceNumberStr);
				throw exception;
			}

			nodePropertiesList = nodeDAO.fetchChildNodeList(projectId,
					parentNodeId, fromDocumentSequenceNumber,
					toDocumentSequenceNumber);

		} catch (Exception exception) {

			logger.error("Error while fetching the list of child nodes properties for Project ID : "
					+ projectId + " and Parent Node ID : " + parentNodeId);
			logger.error("Exception : " + exception);
		}

		return nodePropertiesList;
	}

	/**
	 * Method to save the list properties provided in array list.
	 * 
	 * @param nodePropertiesList
	 * @return
	 */
	public ArrayList<NodeProperties> updateNodePropertiesList(
			ArrayList<NodeProperties> nodePropertiesList) {

		Connection connection = null;

		try {

			connection = getSqlUtil().getConnectionFactory().getDBConnection();
			boolean isSuccess = true;

			for (int index = 0; index < nodePropertiesList.size(); index++) {
				isSuccess = isSuccess
						& nodeDAO.updateNodeProperties(
								nodePropertiesList.get(index), connection);

				if (isSuccess == false)
					break;
			}

			if (isSuccess)
				connection.commit();
			else
				connection.rollback();

		} catch (Exception exception) {

			logger.error("Error while updating the list of nodes properties.");
			logger.error("Exception : " + exception);

			try {
				connection.rollback();
			} catch (Exception e) {
				logger.error("Error in rolling the connection back while updating the list of nodes properties.");
				logger.error("Exception : " + e);
			}
		}

		finally {
			try {
				connection.close();
			} catch (Exception e) {
				logger.error("Error in closing the connection while updating the list of nodes properties.");
				logger.error("Exception : " + e);
			}
		}

		return nodePropertiesList;
	}

	/**
	 * Method to fetch the hierarchy from DB for the provided list of project id
	 * and search barcode.
	 * 
	 * @param searchParameters
	 * @return
	 */
	public String getHierarchyFromSearchBarcode(
			ArrayList<String> searchParameters) {

		String hierarchy = "";

		String projectId = searchParameters.get(0);
		String searchBarcode = searchParameters.get(1);

		try {

			hierarchy = nodeDAO.getHierarchyFromSearchBarcode(projectId,
					searchBarcode);

		} catch (Exception exception) {

			logger.error("Error while fetching the hierarchy for provided SEARCH BARCODE : "
					+ searchBarcode + "  and PROJECT ID : " + projectId);
			logger.error("Exception : " + exception);
		}

		return hierarchy;
	}

	/**
	 * Method to store the scanned images and insert node properties in the DB.
	 * 
	 * @param scannedImageNodePropertiesList
	 * @return
	 */
	public HashMap<String, Boolean> saveScannedImages(
			ArrayList<NodeProperties> scannedImageNodePropertiesList) {

		HashMap<String, Boolean> imageUploadResultMap = new HashMap<String, Boolean>();
		Connection connection = null;

		// Initializing connection and if any exception occurs, method will
		// return.
		try {
			connection = getSqlUtil().getConnectionFactory().getDBConnection();

		} catch (Exception e) {

			logger.error("Error getting connection to save scanned images.");
			logger.error("Exception : " + e);

			for (int index = 0; index < scannedImageNodePropertiesList.size(); index++) {

				imageUploadResultMap.put(
						scannedImageNodePropertiesList.get(index)
								.getActualImageName(), false);
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (Exception exceptionWhileCloseConnection) {
					logger.error("Error in closing connection while saving scanned images.");
					logger.error("Exception : " + exceptionWhileCloseConnection);
				}
			}

			return imageUploadResultMap;
		}

		// Trying to upload image and insert entries in DB...
		try {

			for (int index = 0; index < scannedImageNodePropertiesList.size(); index++) {

				NodeProperties scannedImageNodeProperty = scannedImageNodePropertiesList
						.get(index);
                                
                                if (scannedImageNodeProperty.getParentNodeId() == null || "".equals(scannedImageNodeProperty.getParentNodeId()))
                                {
                                    logger.error("Trying to upload image without parent node!");
                                    continue;
                                }

				char transactionStatus = scannedImageNodeProperty
						.getTransactionStatus();

				try {

					boolean status = false;

					// INSERTING / UPLOADING IMAGES...
					if (transactionStatus == TransactionConstant.INSERT) {

						status = imageProcessServiceImpl
								.uploadScannedImage(scannedImageNodeProperty)
								& nodeDAO.insertNodeProperties(
										scannedImageNodeProperty, connection);

						if (status) {
							connection.commit();
							nodeDAO.getNodePropertiesForInsertedNode(scannedImageNodeProperty);

						} else {
							connection.rollback();

							imageProcessServiceImpl
									.deleteImageFromFTP(scannedImageNodeProperty
											.getActualImageName());
							imageProcessServiceImpl
									.deleteImageFromFTP(scannedImageNodeProperty
											.getThumbnailImageName());
						}

						// status = nodeDAO.insertNodeProperties(
						// scannedImageNodeProperty, connection);
						//
						// // Here first inserting row in DB, then uploading
						// file
						// // and if that fails deleting row from DB... This is
						// // done because if client system is fast, multiple
						// // threads to upload file may start with very little
						// // time difference (in microseconds) and this may
						// result
						// // in having DB row with same document sequence
						// number..
						// if (status) {
						//
						// connection.commit();
						//
						// nodeDAO.getNodePropertiesForInsertedNode(scannedImageNodeProperty);
						//
						// status = imageProcessServiceImpl
						// .uploadScannedImage(scannedImageNodeProperty);
						//
						// if (!status) {
						//
						// if (nodeDAO.deleteNodeProperties(
						// scannedImageNodeProperty, connection)) {
						// connection.commit();
						// } else {
						// connection.rollback();
						// }
						//
						// imageProcessServiceImpl
						// .deleteImageFromFTP(scannedImageNodeProperty
						// .getActualImageName());
						// imageProcessServiceImpl
						// .deleteImageFromFTP(scannedImageNodeProperty
						// .getThumbnailImageName());
						// }
						//
						// } else {
						// connection.rollback();
						// }

						imageUploadResultMap.put(
								scannedImageNodeProperty.getActualImageName(),
								status);
					}

					// UPDATING IMAGE PROPERTIES...
					else if (transactionStatus == TransactionConstant.UPDATE) {

						// PARENT NODE IS SAME & SEQUENCE NUMBER IS CHANGED...
						if (scannedImageNodeProperty.getActualImageName()
								.equals(scannedImageNodeProperty
										.getOldActualImageName())) {

							status = nodeDAO.updateNodeProperties(
									scannedImageNodeProperty, connection);
						}

						// IMAGE CUT/PASTE & PARENT NODE IS CHANGED...
						else {

							status = nodeDAO.updateNodeProperties(
									scannedImageNodeProperty, connection)
									& imageProcessServiceImpl.moveImageOnFTP(
											scannedImageNodeProperty
													.getOldActualImageName(),
											scannedImageNodeProperty
													.getActualImageName())
									& imageProcessServiceImpl
											.moveImageOnFTP(
													scannedImageNodeProperty
															.getOldThumbnailImageName(),
													scannedImageNodeProperty
															.getThumbnailImageName());
						}

						if (status)
							connection.commit();
						else
							connection.rollback();

						imageUploadResultMap.put(
								scannedImageNodeProperty.getActualImageName(),
								status);
					}

					// DELETING IMAGE...
					else if (transactionStatus == TransactionConstant.DELETE) {

						status = nodeDAO.deleteNodeProperties(
								scannedImageNodeProperty, connection)
								& imageProcessServiceImpl
										.deleteImageFromFTP(scannedImageNodeProperty
												.getActualImageName())
								& imageProcessServiceImpl
										.deleteImageFromFTP(scannedImageNodeProperty
												.getThumbnailImageName());

						if (status)
							connection.commit();
						else
							connection.rollback();

						imageUploadResultMap.put(
								scannedImageNodeProperty.getActualImageName(),
								status);
					}

					logger.info("Image Operation : " + transactionStatus
							+ "       Status : " + status);
					logger.info("Image Info :       Old Actual Name : "
							+ scannedImageNodeProperty.getOldActualImageName()
							+ "       New Actual Name : "
							+ scannedImageNodeProperty.getActualImageName());
				}

				// Catching exception while uploading the image.
				catch (Exception e) {

					try {
						logger.error("Error while performing operation on scanned image.");
						logger.error("Transaction Type : " + transactionStatus);
						logger.error("Old Actual Image Name : "
								+ scannedImageNodeProperty
										.getOldActualImageName()
								+ "        New Actual Image Name : "
								+ scannedImageNodeProperty.getActualImageName());

						logger.error("Exception : " + e);

						imageUploadResultMap.put(
								scannedImageNodeProperty.getActualImageName(),
								false);

						connection.rollback();

					} catch (Exception f) {

						logger.error("Error while rolling back connection when performing operation to save scanned image.");
						logger.error("Exception : " + f);
					}
				}
			}
		}

		// Finally block to close connection...
		finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
					logger.error("Error in closing connection while saving scanned images.");
					logger.error("Exception : " + e);
				}
			}
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
	public Boolean checkIfNodeIsUpdatedByOtherUser(
			HashMap<String, String> nodeUpdateTimeMap) {

		try {
			return nodeDAO.checkIfNodeIsUpdatedByOtherUser(nodeUpdateTimeMap);

		} catch (Exception exception) {

			ArrayList<String> nodeIdList = new ArrayList<String>(
					nodeUpdateTimeMap.keySet());

			logger.error("Error checking if node ids are updated by other user.");
			logger.error("Provided Node IDs To Check For Updation Are : "
					+ nodeIdList.toString());
			logger.error("Exception : " + exception);

			return true;
		}
	}

	/**
	 * Method to get the count of child nodes for provided parent node id.
	 * 
	 * @param parentNodeId
	 * @return
	 */
	public Integer getChildNodeCount(String parentNodeId) {

		try {
			return nodeDAO.getChildNodeCount(parentNodeId);

		} catch (Exception exception) {

			logger.error("Error while getting the count of child nodes for parent node id : "
					+ parentNodeId);
			logger.error("Exception : " + exception);

			return -1;
		}
	}

	/**
	 * Method to fetch the encoded string of actual image for provided node id.
	 * 
	 * @param nodeId
	 * @return
	 */
	public String getEncodedActualImageString(String nodeId) {

		try {
			return nodeDAO.getEncodedActualImageString(nodeId);

		} catch (Exception exception) {

			logger.error("Error while getting encoded string of actual image for node id : "
					+ nodeId);
			logger.error("Exception : " + exception);

			return "";
		}
	}
}

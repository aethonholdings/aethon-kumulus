/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.aethon.kumulus.stress;

/**
 * A POJO class to contain all properties that are specific to particular node.
 * 
 * @author Shalin Shah<shalin.shah@spec-india.com>
 * @since 12/July/2013
 */
public class NodeProperties {

	String nodeId;
	String projectId;
	String name;
	String type;
	String barcode;
	String comment;
	String internalComment;
	String status;
	String parentNodeId;
	String hierarchy;
	String thumbnailImageName;
	String actualImageName;
	String lastUpdateDateTime;
	String documentSequenceNumber;

	String userId;
	String encodeStringForImage;
	String encodeStringForThumbnail;
	String oldActualImageName;
	String oldThumbnailImageName;

	char transactionStatus;

	/**
	 * @return the nodeId
	 */
	public String getNodeId() {
		return nodeId;
	}

	/**
	 * @param nodeId
	 *            the nodeId to set
	 */
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	/**
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId
	 *            the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the barcode
	 */
	public String getBarcode() {
		return barcode;
	}

	/**
	 * @param barcode
	 *            the barcode to set
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the internalComment
	 */
	public String getInternalComment() {
		return internalComment;
	}

	/**
	 * @param internalComment
	 *            the internalComment to set
	 */
	public void setInternalComment(String internalComment) {
		this.internalComment = internalComment;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the parentNodeId
	 */
	public String getParentNodeId() {
		return parentNodeId;
	}

	/**
	 * @param parentNodeId
	 *            the parentNodeId to set
	 */
	public void setParentNodeId(String parentNodeId) {
		this.parentNodeId = parentNodeId;
	}

	/**
	 * @return the hierarchy
	 */
	public String getHierarchy() {
		return hierarchy;
	}

	/**
	 * @param hierarchy
	 *            the hierarchy to set
	 */
	public void setHierarchy(String hierarchy) {
		this.hierarchy = hierarchy;
	}

	/**
	 * @return the thumbnailImageName
	 */
	public String getThumbnailImageName() {
		return thumbnailImageName;
	}

	/**
	 * @param thumbnailImageName
	 *            the thumbnailImageName to set
	 */
	public void setThumbnailImageName(String thumbnailImageName) {
		this.thumbnailImageName = thumbnailImageName;
	}

	/**
	 * @return the actualImageName
	 */
	public String getActualImageName() {
		return actualImageName;
	}

	/**
	 * @param actualImageName
	 *            the actualImageName to set
	 */
	public void setActualImageName(String actualImageName) {
		this.actualImageName = actualImageName;
	}

	/**
	 * @return the lastUpdateDateTime
	 */
	public String getLastUpdateDateTime() {
		return lastUpdateDateTime;
	}

	/**
	 * @param lastUpdateDateTime
	 *            the lastUpdateDateTime to set
	 */
	public void setLastUpdateDateTime(String lastUpdateDateTime) {
		this.lastUpdateDateTime = lastUpdateDateTime;
	}

	/**
	 * @return the documentSequenceNumber
	 */
	public String getDocumentSequenceNumber() {
		return documentSequenceNumber;
	}

	/**
	 * @param documentSequenceNumber
	 *            the documentSequenceNumber to set
	 */
	public void setDocumentSequenceNumber(String documentSequenceNumber) {
		this.documentSequenceNumber = documentSequenceNumber;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the encodeStringForImage
	 */
	public String getEncodeStringForImage() {
		return encodeStringForImage;
	}

	/**
	 * @param encodeStringForImage
	 *            the encodeStringForImage to set
	 */
	public void setEncodeStringForImage(String encodeStringForImage) {
		this.encodeStringForImage = encodeStringForImage;
	}

	/**
	 * @return the encodeStringForThumbnail
	 */
	public String getEncodeStringForThumbnail() {
		return encodeStringForThumbnail;
	}

	/**
	 * @param encodeStringForThumbnail
	 *            the encodeStringForThumbnail to set
	 */
	public void setEncodeStringForThumbnail(String encodeStringForThumbnail) {
		this.encodeStringForThumbnail = encodeStringForThumbnail;
	}

	/**
	 * @return the oldActualImageName
	 */
	public String getOldActualImageName() {
		return oldActualImageName;
	}

	/**
	 * @param oldActualImageName
	 *            the oldActualImageName to set
	 */
	public void setOldActualImageName(String oldActualImageName) {
		this.oldActualImageName = oldActualImageName;
	}

	/**
	 * @return the oldThumbnailImageName
	 */
	public String getOldThumbnailImageName() {
		return oldThumbnailImageName;
	}

	/**
	 * @param oldThumbnailImageName
	 *            the oldThumbnailImageName to set
	 */
	public void setOldThumbnailImageName(String oldThumbnailImageName) {
		this.oldThumbnailImageName = oldThumbnailImageName;
	}

	/**
	 * @return the transactionStatus
	 */
	public char getTransactionStatus() {
		return transactionStatus;
	}

	/**
	 * @param transactionStatus
	 *            the transactionStatus to set
	 */
	public void setTransactionStatus(char transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
}

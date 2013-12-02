package com.scannerapp.shared;

import java.util.HashMap;

/**
 * POJO class to contain the parameters for the application that are fetched
 * when user logs into the system.
 * 
 * @author Shalin Shah<shalin.shah@spec-india.com>
 * @since 12/July/2013
 */
public class SessionData {

	String userId;
	String projectId;
	String projectName;
	String collectionRight;
	String separationRight;
	String importRight;
	String separationTarget;
	String importTarget;
	String overallTarget;

	String version;
	String refreshInterval;
	String localStoragePath;
	String localThumbnailDirPath;
	String breathInterval;

	String totalImagesToUploadAtOnce;

	HashMap<String, String> statusMap;
	HashMap<String, String> nodeTypeMap;

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
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName
	 *            the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the collectionRight
	 */
	public String getCollectionRight() {
		return collectionRight;
	}

	/**
	 * @param collectionRight
	 *            the collectionRight to set
	 */
	public void setCollectionRight(String collectionRight) {
		this.collectionRight = collectionRight;
	}

	/**
	 * @return the separationRight
	 */
	public String getSeparationRight() {
		return separationRight;
	}

	/**
	 * @param separationRight
	 *            the separationRight to set
	 */
	public void setSeparationRight(String separationRight) {
		this.separationRight = separationRight;
	}

	/**
	 * @return the importRight
	 */
	public String getImportRight() {
		return importRight;
	}

	/**
	 * @param importRight
	 *            the importRight to set
	 */
	public void setImportRight(String importRight) {
		this.importRight = importRight;
	}

	/**
	 * @return the separationTarget
	 */
	public String getSeparationTarget() {
		return separationTarget;
	}

	/**
	 * @param separationTarget
	 *            the separationTarget to set
	 */
	public void setSeparationTarget(String separationTarget) {
		this.separationTarget = separationTarget;
	}

	/**
	 * @return the importTarget
	 */
	public String getImportTarget() {
		return importTarget;
	}

	/**
	 * @param importTarget
	 *            the importTarget to set
	 */
	public void setImportTarget(String importTarget) {
		this.importTarget = importTarget;
	}

	/**
	 * @return the overallTarget
	 */
	public String getOverallTarget() {
		return overallTarget;
	}

	/**
	 * @param overallTarget
	 *            the overallTarget to set
	 */
	public void setOverallTarget(String overallTarget) {
		this.overallTarget = overallTarget;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the refreshInterval
	 */
	public String getRefreshInterval() {
		return refreshInterval;
	}

	/**
	 * @param refreshInterval
	 *            the refreshInterval to set
	 */
	public void setRefreshInterval(String refreshInterval) {
		this.refreshInterval = refreshInterval;
	}

	/**
	 * @return the localStoragePath
	 */
	public String getLocalStoragePath() {
		return localStoragePath;
	}

	/**
	 * @param localStoragePath
	 *            the localStoragePath to set
	 */
	public void setLocalStoragePath(String localStoragePath) {
		this.localStoragePath = localStoragePath;
	}

	/**
	 * @return the localThumbnailDirPath
	 */
	public String getLocalThumbnailDirPath() {
		return localThumbnailDirPath;
	}

	/**
	 * @param localThumbnailDirPath
	 *            the localThumbnailDirPath to set
	 */
	public void setLocalThumbnailDirPath(String localThumbnailDirPath) {
		this.localThumbnailDirPath = localThumbnailDirPath;
	}

	/**
	 * @return the breathInterval
	 */
	public String getBreathInterval() {
		return breathInterval;
	}

	/**
	 * @param breathInterval
	 *            the breathInterval to set
	 */
	public void setBreathInterval(String breathInterval) {
		this.breathInterval = breathInterval;
	}

	/**
	 * @return the totalImagesToUploadAtOnce
	 */
	public String getTotalImagesToUploadAtOnce() {
		return totalImagesToUploadAtOnce;
	}

	/**
	 * @param totalImagesToUploadAtOnce
	 *            the totalImagesToUploadAtOnce to set
	 */
	public void setTotalImagesToUploadAtOnce(String totalImagesToUploadAtOnce) {
		this.totalImagesToUploadAtOnce = totalImagesToUploadAtOnce;
	}

	/**
	 * @return the statusMap
	 */
	public HashMap<String, String> getStatusMap() {
		return statusMap;
	}

	/**
	 * @param statusMap
	 *            the statusMap to set
	 */
	public void setStatusMap(HashMap<String, String> statusMap) {
		this.statusMap = statusMap;
	}

	/**
	 * @return the nodeTypeMap
	 */
	public HashMap<String, String> getNodeTypeMap() {
		return nodeTypeMap;
	}

	/**
	 * @param nodeTypeMap
	 *            the nodeTypeMap to set
	 */
	public void setNodeTypeMap(HashMap<String, String> nodeTypeMap) {
		this.nodeTypeMap = nodeTypeMap;
	}
}

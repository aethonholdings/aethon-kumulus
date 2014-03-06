/**
 * 
 */
package com.scannerapp.apps.desktoprightpanel.view;

import com.scannerapp.apps.desktopleftpanel.view.CustomMutableTreeNode;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.scannerapp.apps.framework.view.ErrorMessage;
import com.scannerapp.apps.utils.SessionUtil;
import com.scannerapp.common.NodePropertyConstants;
import com.scannerapp.shared.NodeProperties;
import com.scannerapp.shared.TransactionConstant;
import com.sun.jersey.core.util.Base64;
import javax.swing.tree.TreePath;

/**
 * Class to upload the images.
 * 
 * @author Shalin Shah<shalin.shah@spec-india.com>
 * @since 07/August/2013
 */
public class ImageUploader {

	private static Logger logger = Logger.getLogger(ImageUploader.class);

	private ImportSaparationPanel importSaparationPanel;
	private ArrayList<NodeProperties> imageNodePropertiesList = new ArrayList<NodeProperties>();

	private int totalScannedImages = 0;
	private int totalUploadedImages = 0;
	private int totalImagesWithUploadError = 0;

	/**
	 * Count to maintain number of pages that are in process of resume import.
	 * Once the page is imported and result is received, count will be
	 * decreased.
	 */
	private int pageCountInResumeImportProcess = 0;

	/**
	 * Constructor to initialize the object.
	 * 
	 * @param importSaparationPanel
	 */
	public ImageUploader(ImportSaparationPanel importSaparationPanel) {
                this.importSaparationPanel = importSaparationPanel;
	}

	/**
	 * Method to update the counter when image is successfully scanned and
	 * generated on local machine.
	 * 
	 * @param isScannedImageGenerated
	 *            - Flag to indicate if image is scanned & generated.
	 */
	public void isScannedImageGenerated(boolean isScannedImageGenerated) {

		if (isScannedImageGenerated) {

			totalScannedImages++;

			importSaparationPanel.setImportPagesLabel(totalScannedImages,
					totalUploadedImages, totalImagesWithUploadError);
		}
	}

	/**
	 * Method to proceed for image upload.
	 * 
	 * @param imageDirectoryPath
	 * @param imageName
	 * @param parentDocumentNodeProperties
	 * @param isResumeImport
	 */
	public void proceedToUpload(String imageDirectoryPath, String imageName,
			NodeProperties parentDocumentNodeProperties, boolean isResumeImport,
                        CustomMutableTreeNode selectedNode
                       ) {

		// Displaying label to indicate that upload image is in process...
		importSaparationPanel.getUploadProgressPanel().setVisible(true);

		// Generating node properties for scanned image...
		NodeProperties imageNodeProperties = getImageNodeProperties(
				imageDirectoryPath, imageName, parentDocumentNodeProperties);

		// Incrementing count of pages in resume import process.
		if (isResumeImport) {
			pageCountInResumeImportProcess++;
		}

		// Inserting node properties for scanned image in the list for
		// uploading...
		imageNodePropertiesList.add(imageNodeProperties);

		uploadImagesInBatch(isResumeImport, false, selectedNode);
	}

	/**
	 * Method to upload the images (during both, import and resume import) in
	 * batch when the number of created images reaches the batch size defined in
	 * DB. This method is also responsible for uploading the remaining images
	 * (during both, import and resume import) whose count is less than batch
	 * size.
	 * 
	 * @param isResumeImport
	 *            - Flag to indicate if to perform resume import
	 * @param uploadRemainingImages
	 *            - Flag to indicate if remaining images in batch are to upload.
	 */
	public void uploadImagesInBatch(boolean isResumeImport,
			boolean uploadRemainingImages, CustomMutableTreeNode selectedNode) {

		if ((uploadRemainingImages && imageNodePropertiesList.size() > 0)
				|| imageNodePropertiesList.size() == Integer
						.parseInt(SessionUtil.getSessionData()
								.getTotalImagesToUploadAtOnce())) {

			new Thread(new UploadImage(imageNodePropertiesList, isResumeImport, selectedNode))
					.start();
                        
			imageNodePropertiesList.clear();
		}
	}

	/**
	 * Method to generate the node properties for scanned image from provided
	 * parent node properties, image name and directory path.
	 * 
	 * @param imageDirectoryPath
	 * @param imageName
	 * @param parentDocumentNodeProperties
	 * @return
	 */
	private NodeProperties getImageNodeProperties(String imageDirectoryPath,
			String imageName, NodeProperties parentDocumentNodeProperties) {

		NodeProperties imageNodeProperties = new NodeProperties();

		imageNodeProperties.setProjectId(SessionUtil.getSessionData()
				.getProjectId());
		imageNodeProperties.setName(imageName);
		imageNodeProperties.setParentNodeId(parentDocumentNodeProperties
				.getNodeId());
		imageNodeProperties.setActualImageName(imageDirectoryPath + imageName);
		imageNodeProperties.setThumbnailImageName(imageDirectoryPath
				+ NodePropertyConstants.THUMBNAIL_DIR + imageName);
		imageNodeProperties.setUserId(SessionUtil.getSessionData().getUserId());

		imageNodeProperties.setEncodeStringForImage(getEncodeStringForImage(
				imageDirectoryPath, imageName));

		imageNodeProperties.setTransactionStatus(TransactionConstant.INSERT);

		return imageNodeProperties;
	}

	/**
	 * Method to process the result of the image upload.
	 * 
	 * @param imageUploadResultMap
	 * @param isResumeImport
	 */
	private synchronized void processImageUploadResult(
			HashMap<String, Boolean> imageUploadResultMap,
			boolean isResumeImport) {

		if (imageUploadResultMap == null || imageUploadResultMap.size() == 0) {

			ErrorMessage.displayMessage('I', "errorDuringUploadingImage");
			return;
		}

		Iterator<Entry<String, Boolean>> iterator = imageUploadResultMap
				.entrySet().iterator();

		// Iterating through upload result map...
		while (iterator.hasNext()) {

			Entry<String, Boolean> mapEntry = iterator.next();

			String actualImageName = mapEntry.getKey();

			String imageDirectoryPath = actualImageName.substring(0,
					actualImageName.lastIndexOf(File.separator) + 1);
			String imageName = actualImageName.substring(actualImageName
					.lastIndexOf(File.separator) + 1);

			// If image is not uploaded - ERROR...
			if (!mapEntry.getValue()) {

				logger.error("ERROR In Uploading Image.");
				logger.error("Image Name : " + imageName + "        PATH : "
						+ imageDirectoryPath);

				// In case of resume import failure, counter already contains
				// error count...
				if (!isResumeImport) {

					totalImagesWithUploadError++;

					HashMap<String, ArrayList<String>> uploadErrorImageNameMap = importSaparationPanel
							.getUploadErrorImageNameMap();

					if (uploadErrorImageNameMap.get(imageDirectoryPath) == null)
						uploadErrorImageNameMap.put(imageDirectoryPath,
								new ArrayList<String>());

					uploadErrorImageNameMap.get(imageDirectoryPath).add(
							imageName);
				}
			}

			// If image is successfully uploaded...
			else {

				logger.info("Image Uploaded Successfully.");
				logger.info("Image Name : " + imageName + "        PATH : "
						+ imageDirectoryPath);

				String imageFilePath = SessionUtil.getSessionData()
						.getLocalStoragePath()
						+ File.separator
						+ imageDirectoryPath + imageName;

				File imageFile = new File(imageFilePath);
				imageFile.delete();

				logger.info("Image deleted from local machine...");

				// If pages are "resume import" success count will be increased
				// and failure count will be decreased.
				if (!isResumeImport) {
					totalUploadedImages++;
				} else {
					totalUploadedImages++;
					totalImagesWithUploadError--;

					importSaparationPanel.getUploadErrorImageNameMap()
							.get(imageDirectoryPath).remove(imageName);
				}
			}

			// Result received for image sent in resume import. Hence,
			// decrementing count of pages in resume import process.
			if (isResumeImport) {
				pageCountInResumeImportProcess--;
			}
		}

		importSaparationPanel.setImportPagesLabel(totalScannedImages,
				totalUploadedImages, totalImagesWithUploadError);

		// Removing label to indicate that upload image is in process when it
		// gets over...
		if (!isImportInProcess() && !isResumeImportInProcess()) {
			importSaparationPanel.getUploadProgressPanel().setVisible(false);
		}
	}

	/**
	 * Method to get the image with provided image name and located at provided
	 * path, in encoded string format.
	 * 
	 * @param imageDirectoryPath
	 * @param imageName
	 * @return
	 */
	private String getEncodeStringForImage(String imageDirectoryPath,
			String imageName) {

		String imageFilePath = SessionUtil.getSessionData()
				.getLocalStoragePath()
				+ File.separator
				+ imageDirectoryPath
				+ imageName;

		InputStream inputStream = null;
		BufferedInputStream bufferedInputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = null;

		byte result[] = null;

		try {
			inputStream = new FileInputStream(new File(imageFilePath));
			bufferedInputStream = new BufferedInputStream(inputStream);

			byteArrayOutputStream = new ByteArrayOutputStream();

			byte buffer[] = new byte[1024];
			int readCount;

			while ((readCount = bufferedInputStream.read(buffer)) > 0) {
				byteArrayOutputStream.write(buffer, 0, readCount);
			}

			result = byteArrayOutputStream.toByteArray();
		}

		// Catch block to catch exception while reading/writing stream...
		catch (Exception e) {
			logger.error("Error while getting encoded string for image.");
			logger.error("Exception : " + e);
		}

		// Finally block...
		finally {

			try {
				byteArrayOutputStream.close();
				bufferedInputStream.close();
				inputStream.close();

			} catch (Exception e) {

				logger.error("Error on closing i/o streams while getting encoded string for image.");
				logger.error("Exception : " + e);
			}
		}

		if (result == null)
			return "";

		byte[] encode = Base64.encode(result);
		String encodedImageString = new String(encode);

		return encodedImageString;
	}

	/**
	 * Runnable class to upload the images to the server.
	 * 
	 * @author Shalin Shah<shalin.shah@spec-india.com>
	 * @since 08/August/2013
	 */
	private class UploadImage implements Runnable {

		private ArrayList<NodeProperties> imageNodePropertiesList;
		private boolean isResumeImport;
                private CustomMutableTreeNode selectedNode;

		private UploadImage(ArrayList<NodeProperties> imageNodePropertiesList,
				boolean isResumeImport, CustomMutableTreeNode selectedNode) {

			this.imageNodePropertiesList = new ArrayList<NodeProperties>();
			this.imageNodePropertiesList.addAll(imageNodePropertiesList);
                        this.selectedNode = selectedNode;
			this.isResumeImport = isResumeImport;
		}

		@Override
		public void run() {
			beginToUpload();
                        
                        // -- KONS CODE - refresh the tree after the upload is completed
                        importSaparationPanel.getDesktopMainPanel().getjLeftPanel().refreshTreeNode(selectedNode);
                        // -- KONS CODE
		}

		private void beginToUpload() {

			HashMap<String, Boolean> imageUploadResultMap = importSaparationPanel
					.controller().uploadScannedImages(
							this.imageNodePropertiesList);

			processImageUploadResult(imageUploadResultMap, this.isResumeImport);
		}
	}

	/**
	 * @return flag to indicate if import is in process.
	 */
	public boolean isImportInProcess() {

		if (totalScannedImages > (totalUploadedImages + totalImagesWithUploadError))
			return true;
		else
			return false;
	}

	/**
	 * @return flag to indicate if resume import is in process.
	 */
	public boolean isResumeImportInProcess() {

		if (pageCountInResumeImportProcess > 0)
			return true;
		else
			return false;
	}
}

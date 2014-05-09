/**
 * 
 */
package com.scannerapp.apps.desktoprightpanel.view;

import com.scannerapp.apps.desktopleftpanel.view.CustomMutableTreeNode;
import java.io.File;
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
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.apache.commons.io.FileUtils;

/**
 * Class to upload the images.
 * 
 * @author Shalin Shah<shalin.shah@spec-india.com>
 * @since 07/August/2013
 */
public class ImageUploader {

	private static final Logger logger = Logger.getLogger(ImageUploader.class);

	private final ImportSaparationPanel importSaparationPanel;
	private final ArrayList<NodeProperties> imageNodePropertiesList = new ArrayList<NodeProperties>();
	private int totalScannedImages = 0;
	private int totalUploadedImages = 0;
	private int totalImagesWithUploadError = 0;
        
        
        public class UploadTask
        {
            private final ArrayList<NodeProperties> imageNodePropertiesList;
            private final boolean isResumeImport;
            private final CustomMutableTreeNode selectedNode;
            
            public UploadTask(ArrayList<NodeProperties> imageNodePropertiesList,
                              boolean isResumeImport,
                              CustomMutableTreeNode selectedNode) {
                this.imageNodePropertiesList = new ArrayList<NodeProperties>();
                this.imageNodePropertiesList.addAll(imageNodePropertiesList);
                this.isResumeImport = isResumeImport;
                this.selectedNode = selectedNode;
            }

        }
        
        private BlockingQueue<UploadTask> queue = new ArrayBlockingQueue(1024);

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
	private ImageUploader(ImportSaparationPanel importSaparationPanel) {
                this.importSaparationPanel = importSaparationPanel;
	}

        private void startUploadThread() {
            new Thread(new UploadThread(queue)).start();
        }
        
        public static ImageUploader createInstance(ImportSaparationPanel importSaparationPanel) {
            ImageUploader instance = new ImageUploader(importSaparationPanel);
            instance.startUploadThread();
            return instance;
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
         * @param selectedNode
         * @throws java.lang.Exception
	 */
	public void proceedToUpload(String imageDirectoryPath, String imageName,
			NodeProperties parentDocumentNodeProperties, boolean isResumeImport,
                        CustomMutableTreeNode selectedNode)
            throws Exception {

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
         * @param selectedNode
	 */
	public void uploadImagesInBatch(boolean isResumeImport,
			boolean uploadRemainingImages, CustomMutableTreeNode selectedNode)
            throws Exception{

		if ((uploadRemainingImages && imageNodePropertiesList.size() > 0)
				|| imageNodePropertiesList.size() == Integer
						.parseInt(SessionUtil.getSessionData()
								.getTotalImagesToUploadAtOnce())) {

			queue.put(new UploadTask(imageNodePropertiesList, isResumeImport, selectedNode));
                        
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
			String imageName, NodeProperties parentDocumentNodeProperties)
            throws Exception {

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
                                               String imageName)
            throws Exception {

		String imageFilePath = SessionUtil.getSessionData()
				.getLocalStoragePath()
				+ File.separator
				+ imageDirectoryPath
				+ imageName;

                return new String(Base64.encode(FileUtils.readFileToByteArray(new File(imageFilePath))));
                
	}

	/**
	 * Runnable class to upload the images to the server.
	 * 
	 * @author Shalin Shah<shalin.shah@spec-india.com>
	 * @since 08/August/2013
	 */
	private class UploadThread implements Runnable {

                private final BlockingQueue<UploadTask> queue;
            
		private UploadThread(BlockingQueue<UploadTask> queue) {
                    this.queue = queue;
		}

		@Override
		public void run() {
                    while (true) {
                        try
                        {
                            UploadTask task = queue.take();

                            HashMap<String, Boolean> imageUploadResultMap = importSaparationPanel
                                            .controller().uploadScannedImages(
                                                            task.imageNodePropertiesList);

                            processImageUploadResult(imageUploadResultMap, task.isResumeImport);

                            // -- KONS CODE - refresh the tree after the upload is completed
                            importSaparationPanel.getDesktopMainPanel().getjLeftPanel().refreshTreeNode(task.selectedNode);
                            // -- KONS CODE
                        }
                        catch (Exception e)
                        {
                            // TODO: error handling
                            e.printStackTrace();
                        }
                    }
                
                }
	}

	/**
	 * @return flag to indicate if import is in process.
	 */
	public boolean isImportInProcess() {

		return (totalScannedImages > (totalUploadedImages + totalImagesWithUploadError));
	}

	/**
	 * @return flag to indicate if resume import is in process.
	 */
	public boolean isResumeImportInProcess() {

		return (pageCountInResumeImportProcess > 0);
	}
        
}

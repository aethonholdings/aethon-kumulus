package com.scannerapp.apps.desktoprightpanel.scanner7;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.scannerapp.apps.desktopleftpanel.view.CustomMutableTreeNode;
import com.scannerapp.apps.desktoprightpanel.view.ImportSaparationPanel;
import com.scannerapp.apps.framework.view.ErrorMessage;
import com.scannerapp.apps.utils.SessionUtil;
import com.scannerapp.shared.NodeProperties;

/**
 * @author rahul
 * 
 */
public class ImageGenerator implements Runnable {

	private static final Logger logger = Logger.getLogger(ImageGenerator.class);

	private static final String IMAGE_BASE_NAME = "scanned_doc ";
	private static final String IMAGE_FILE_EXTENSION = ".jpg";

	private ImportSaparationPanel importSeparationPanel;
	private NodeProperties parentDocumentNodeProperties;
	private BufferedImage bufferedScannedImage;

	private String imageDirectoryPath = File.separator;
	private String imageName = "";

	/**
	 * Constructor to initialize the object.
	 * 
	 * @param bufferedScannedImage
	 * @param importSeparationPanel
	 * @param parentDocumentNodeProperties
	 */
	public ImageGenerator(BufferedImage bufferedScannedImage,
			ImportSaparationPanel importSeparationPanel,
			NodeProperties parentDocumentNodeProperties) {

		this.bufferedScannedImage = bufferedScannedImage;
		this.importSeparationPanel = importSeparationPanel;
		this.parentDocumentNodeProperties = parentDocumentNodeProperties;
	}

	@Override
	public void run() {

		logger.debug("Image generation from buffered image is starting.");

		// Getting image directory path by Node IDs of nodes in hierarchy of
		// selected node...
		imageDirectoryPath = getImageDirectoryPath();

		// Getting image name with time stamp...
		imageName = getImageName();

		generateImage();
	}

	/**
	 * Method to generate the image.
	 */
	private int generateImage() {


		// output image type.
		String imageFormat = "jpg";

		try {


			// CREATING DIRECTORY TO STORE IMAGE...
			String imageDir = SessionUtil.getSessionData()
					.getLocalStoragePath()
					+ File.separator
					+ imageDirectoryPath;

			File dir = new File(imageDir);

			if (!dir.exists())
				dir.mkdirs();

			// WRITING IMAGE FILE...
			String imageFilePath = imageDir + imageName;

			ImageIO.write(bufferedScannedImage, imageFormat, new File(
					imageFilePath));

			// Method to update the counter on generating scanned image on local
			// machine...
			importSeparationPanel.getImageUploader().isScannedImageGenerated(
					true);

			// Method to proceed for uploading generated image.
			importSeparationPanel.getImageUploader().proceedToUpload(
					imageDirectoryPath, imageName,
					parentDocumentNodeProperties, false);

		} catch (IOException ioExcep) {

			logger.error("Error while generating the image.");
			logger.error("Exception : " + ioExcep);

			ErrorMessage.displayMessage('E',
					"errorWhileGeneratingImageForScannedDocument");

		} catch (Exception excep) {

			logger.error("Error while generating the image.");
			logger.error("Exception : " + excep);

			ErrorMessage.displayMessage('E',
					"errorWhileGeneratingImageForScannedDocument");

		} finally {

			imageFormat = null;
		}

		return 1;
	}

	/**
	 * Method to get the directory path for the scanned image. This path is
	 * relative which begins with project name and name subsequent folders are
	 * node id of respective tree nodes in hierarchy of selected node.
	 * 
	 * @param hierarchy
	 * @return
	 */
	private String getImageDirectoryPath() {

		String path = "";

		CustomMutableTreeNode presentNode = importSeparationPanel
				.getDesktopMainPanel().getjRightPanel().getCollectionPanel()
				.getSelectedTreeNode();

		while (presentNode.getNodeId() != SessionUtil.getSessionData()
				.getProjectId()) {

			path = presentNode.getNodeId() + File.separator + path;

			presentNode = (CustomMutableTreeNode) presentNode.getParent();
		}

		path = SessionUtil.getSessionData().getProjectName() + File.separator
				+ path;

		return path;
	}

	/**
	 * Method to generate and get the name of the image.
	 * 
	 * @return
	 */
	private String getImageName() {

		// Getting Current Time To Append To File Name...
		Date currentTime = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy HH_mm_ss");

		String imageName = IMAGE_BASE_NAME + dateFormat.format(currentTime)
				+ IMAGE_FILE_EXTENSION;

		return imageName;
	}
}

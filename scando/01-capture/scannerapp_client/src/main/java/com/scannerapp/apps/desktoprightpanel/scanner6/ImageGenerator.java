package com.scannerapp.apps.desktoprightpanel.scanner6;

import java.awt.Image;
import java.awt.image.BufferedImage;
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
public class ImageGenerator {

	private static final Logger logger = Logger.getLogger(ImageGenerator.class);
        private static final String IMAGE_FORMAT = "png";
	private static final String IMAGE_BASE_NAME = "IMG_";

	private ImportSaparationPanel importSeparationPanel;
	private NodeProperties parentDocumentNodeProperties;
	private BufferedImage bufferedScannedImage;
	private Image scannedImage;

	private String imageDirectoryPath = File.separator;
	private String imageName;

	/**
	 * Constructor to initialize the object.
	 * 
	 * @param scannedImage
	 * @param bufferedScannedImage
	 * @param importSeparationPanel
	 * @param parentDocumentNodeProperties
	 */
	public ImageGenerator(Image scannedImage,
			BufferedImage bufferedScannedImage,
			ImportSaparationPanel importSeparationPanel,
			NodeProperties parentDocumentNodeProperties) {

                // initialise the image file literal
                Date currentTime = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                this.imageName = IMAGE_BASE_NAME + dateFormat.format(currentTime);
		
                // initialise the image directory
                /**
                * Method to get the directory path for the scanned image. This path is
                * relative which begins with project name and name subsequent folders are
                * node id of respective tree nodes in hierarchy of selected node.
                * 
                * @return
                */
                this.imageDirectoryPath = "";
		CustomMutableTreeNode presentNode = importSeparationPanel
				.getDesktopMainPanel().getjRightPanel().getCollectionPanel()
				.getSelectedTreeNode();
		while (!presentNode.getNodeId().equals("#")) {
                        if(presentNode.getParent()==null) { 
                            this.imageDirectoryPath = SessionUtil.getSessionData().getProjectId() + File.separator + this.imageDirectoryPath;
                        } else {
                            this.imageDirectoryPath = presentNode.getNodeId() + File.separator + this.imageDirectoryPath;
                        }
			presentNode = (CustomMutableTreeNode) presentNode.getParent();
		}
		this.imageDirectoryPath = SessionUtil.getSessionData().getProjectName() + File.separator
				+ this.imageDirectoryPath 
                                + File.separator;
                this.scannedImage = scannedImage;
		this.bufferedScannedImage = bufferedScannedImage;
		this.importSeparationPanel = importSeparationPanel;
		this.parentDocumentNodeProperties = parentDocumentNodeProperties;
	}

	/**
	 * Method to start the generation of image and upload in batch.
	 */
	public void beginToGenerateImage(CustomMutableTreeNode selectedNode) {
                
		logger.debug("Image generation from buffered image is starting.");

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
			String imageFilePath = imageDir + imageName + "." + IMAGE_FORMAT;

			bufferedScannedImage.createGraphics().drawImage(scannedImage, 0, 0,
					null);
			ImageIO.write(bufferedScannedImage, IMAGE_FORMAT, new File(
					imageFilePath));
                        
			// Method to update the counter on generating scanned image on local
			// machine...
                        importSeparationPanel.getImageUploader().isScannedImageGenerated(
                                        true);

			// Method to proceed for uploading generated image.
                        importSeparationPanel.getImageUploader().proceedToUpload(
                                        imageDirectoryPath, imageName  + "." + IMAGE_FORMAT,
                                        parentDocumentNodeProperties, false, selectedNode);

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

			
		}

	}

	

}

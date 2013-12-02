/**
 * 
 */
package com.scannerapp.apps.desktoprightpanel.scanner6;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import org.apache.log4j.Logger;

import SK.gnome.morena.MorenaImage;
import SK.gnome.twain.TwainManager;
import SK.gnome.twain.TwainSource;

import com.scannerapp.apps.desktoprightpanel.view.ImportSaparationPanel;
import com.scannerapp.apps.framework.view.ErrorMessage;
import com.scannerapp.shared.NodeProperties;

/**
 * @author rahul
 * 
 */
public class DocumentScanner {

	private static final Logger logger = Logger
			.getLogger(DocumentScanner.class);

	private final static boolean IS_AUTOFEED_SCANNER = true;

	private ImportSaparationPanel importSeparationPanel;
	private NodeProperties parentDocumentNodeProperties;

	private boolean isImageScanningInProcess = false;

	/**
	 * Constructor to instantiate the object.
	 * 
	 * @param importSeparationPanel
	 *            - {@link ImportSaparationPanel} object.
	 * @param parentDocumentNodeProperties
	 *            - {@link NodeProperties} object.
	 */
	public DocumentScanner(ImportSaparationPanel importSeparationPanel,
			NodeProperties parentDocumentNodeProperties) {

		this.importSeparationPanel = importSeparationPanel;
		this.parentDocumentNodeProperties = parentDocumentNodeProperties;
	}

	/**
	 * Method to scan single document.
	 * 
	 * @param source
	 * @throws Exception
	 */
	public void scanDocument(TwainSource source) {

		try {
			scanDocument(source, 1);

		} catch (Exception exception) {

			logger.error("Error while scanning single document.");
			logger.error("Exception : " + exception);

			ErrorMessage.displayMessage('E', "errorWhileScanningDocument");
		}
	}

	/**
	 * Method to scan provided number of documents.
	 * 
	 * @param scannerSource
	 *            - {@link TwainSource} object through which document is to be
	 *            scanner.
	 * @param documentCount
	 *            - Number of documents to scan.
	 */
	public void scanDocument(TwainSource scannerSource, int documentCount) {

		try {

			// If scanner source is found...
			if (scannerSource != null) {

				scannerSource.setFeederEnabled(IS_AUTOFEED_SCANNER);
				scannerSource.setAutoFeed(IS_AUTOFEED_SCANNER);
				scannerSource.setTransferCount(-1);

				do {
					isImageScanningInProcess = true;

					MorenaImage morenaImage = new MorenaImage(scannerSource);

					Image scannedImage = Toolkit.getDefaultToolkit()
							.createImage(morenaImage);

					BufferedImage bufferedScannedImage = new BufferedImage(
							scannedImage.getWidth(null),
							scannedImage.getHeight(null),
							BufferedImage.TYPE_INT_RGB);

					ImageGenerator imageGenerator = new ImageGenerator(
							scannedImage, bufferedScannedImage,
							importSeparationPanel, parentDocumentNodeProperties);

					imageGenerator.beginToGenerateImage();

				} while (scannerSource.hasMoreImages());

				// Uploading remaining images that did not reach batch size...
				importSeparationPanel.getImageUploader().uploadImagesInBatch(
						false, true);

				// All images are scanned and generated in local machine...
				isImageScanningInProcess = false;
			}

			// When scanning source is not found...
			else {

				logger.error("No scanning device found. System can not scan.");

				ErrorMessage.displayMessage('E', "noScanningDeviceFound");

				return;
			}

			TwainManager.close();

		} catch (Exception exception) {

			logger.error("Error while scanning multiple (" + documentCount
					+ ") documents.");
			logger.error("Exception : " + exception);

			ErrorMessage.displayMessage('E', "errorWhileScanningDocument");

			isImageScanningInProcess = false;
		}
	}

	/**
	 * @return the isImageScanningInProcess
	 */
	public boolean isImageScanningInProcess() {
		return isImageScanningInProcess;
	}
}

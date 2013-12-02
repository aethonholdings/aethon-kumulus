package com.scannerapp.apps.desktoprightpanel.scanner7;

/**
 * @author rahul
 * 
 */
public class TransferDoneHandler { // implements TransferDoneListener {

	// private static final Logger logger = Logger
	// .getLogger(TransferDoneHandler.class);
	//
	// private DocumentScanner documentScanner;
	// private ImportSaparationPanel importSeparationPanel;
	// private NodeProperties parentDocumentNodeProperties;
	//
	// /**
	// * Constructor to instantiate the object.
	// *
	// * @param documentScanner
	// * - {@link DocumentScanner} object.
	// * @param importSeparationPanel
	// * - {@link ImportSaparationPanel} object.
	// * @param parentDocumentNodeProperties
	// * - {@link NodeProperties} object.
	// */
	// public TransferDoneHandler(DocumentScanner documentScanner,
	// ImportSaparationPanel importSeparationPanel,
	// NodeProperties parentDocumentNodeProperties) {
	//
	// this.documentScanner = documentScanner;
	// this.importSeparationPanel = importSeparationPanel;
	// this.parentDocumentNodeProperties = parentDocumentNodeProperties;
	// }
	//
	// @Override
	// public void transferDone(File file) {
	//
	// try {
	//
	// logger.debug("Transfer Done - Image scanned successfully.");
	//
	// BufferedImage bufferedScannedImage = ImageIO.read(file);
	//
	// ImageGenerator imageGenerator = new ImageGenerator(
	// bufferedScannedImage, importSeparationPanel,
	// parentDocumentNodeProperties);
	//
	// new Thread(imageGenerator).start();
	//
	// } catch (Exception e) {
	//
	// logger.error("Error while reading scanned document or generating image for it.");
	// logger.error("Exception : " + e);
	//
	// ErrorMessage.displayMessage('E', "errorWhileScanningDocument");
	// }
	//
	// }
	//
	// @Override
	// public void transferFailed(int errorCode, String message) {
	//
	// // ERROR CODE WHEN DEVICE IS NOT ATTACHED OR TURNED OFF SUDDENLY...
	// if (errorCode == -2145320959 || errorCode == -2145320955) {
	//
	// logger.error("Scanning of document FAILED.");
	// logger.error("ERROR Code : " + errorCode);
	// logger.error("ERROR Message : " + message);
	//
	// documentScanner.setScanningError(true);
	//
	// // ErrorMessage.displayMessage('E', "documentScanningFailed");
	// }
	// }
}

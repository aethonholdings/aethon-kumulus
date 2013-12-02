package com.scannerapp.apps.desktoprightpanel.scanner7;

/**
 * @author rahul
 * 
 */
public class DocumentScanner {

	// public static final int WIA_ERROR_PAPER_EMPTY = 417;
	// public static final int FLAT_BED_SCANNER = 0;
	// public static final int AUTO_FEED_SCANNER = 1;
	//
	// private static final Logger logger = Logger
	// .getLogger(DocumentScanner.class);
	//
	// private ImportSaparationPanel importSeparationPanel;
	// private NodeProperties parentDocumentNodeProperties;
	//
	// private boolean isScanningError = false;
	//
	// /**
	// * Constructor to instantiate the object.
	// *
	// * @param importSeparationPanel
	// * - {@link ImportSaparationPanel} object.
	// */
	// public DocumentScanner(ImportSaparationPanel importSeparationPanel,
	// NodeProperties parentDocumentNodeProperties) {
	//
	// this.importSeparationPanel = importSeparationPanel;
	// this.parentDocumentNodeProperties = parentDocumentNodeProperties;
	// }
	//
	// /**
	// * Method to scan single document.
	// *
	// * @param device
	// * - {@link Device} object through which document is to be
	// * scanner.
	// * @param showImageSettingUI
	// * - Flag to indicate if image setting UI is to be displayed.
	// */
	// public void scanDocument(Device scannerDevice, boolean
	// showImageSettingUI) {
	//
	// try {
	//
	// startScan(scannerDevice, showImageSettingUI, AUTO_FEED_SCANNER);
	//
	// } catch (Exception e) {
	//
	// logger.error("Error while scanning single document.");
	//
	// if (e.getMessage().indexOf(
	// "[" + DocumentScanner.WIA_ERROR_PAPER_EMPTY + "]") >= 0) {
	// logger.error("No more sheets in the document scanner");
	// }
	//
	// logger.error("Exception : " + e);
	//
	// ErrorMessage.displayMessage('E', "errorWhileScanningDocument");
	// }
	// }
	//
	// /**
	// * Method to scan provided number of documents.
	// *
	// * @param scannerDevice
	// * - {@link Device} object through which document is to be
	// * scanner.
	// * @param showImageSettingUI
	// * - Flag to indicate if image setting UI is to be displayed.
	// * @param documentCount
	// * - Number of documents to scan.
	// */
	// public void scanDocument(Device scannerDevice, boolean
	// showImageSettingUI,
	// int documentCount) {
	//
	// try {
	//
	// for (int i = 0; i < documentCount; i++) {
	//
	// if (isScanningError())
	// break;
	//
	// boolean isScanSuccess = startScan(scannerDevice,
	// showImageSettingUI, AUTO_FEED_SCANNER);
	//
	// if (!isScanSuccess)
	// break;
	// }
	//
	// } catch (Exception e) {
	//
	// logger.error("Error while scanning multiple (" + documentCount
	// + ") documents.");
	//
	// if (e.getMessage().indexOf(
	// "[" + DocumentScanner.WIA_ERROR_PAPER_EMPTY + "]") >= 0) {
	// logger.error("No more sheets in the document scanner");
	// }
	//
	// logger.error("Exception : " + e);
	//
	// ErrorMessage.displayMessage('E', "errorWhileScanningDocument");
	// }
	// }
	//
	// /**
	// * Method to begin scanning of document with provided scanner device.
	// *
	// * @param scannerDevice
	// * - {@link Device} object through which document is to be
	// * scanner.
	// * @param showImageSettingUI
	// * - Flag to indicate if image setting UI is to be displayed.
	// * @param scannerType
	// * - Type of scanner (Flat bed scanner, auto feed scanner etc.)
	// *
	// * @throws Exception
	// */
	// private boolean startScan(Device scannerDevice, boolean
	// showImageSettingUI,
	// int scannerType) throws Exception {
	//
	// TransferDoneHandler handler = new TransferDoneHandler(this,
	// importSeparationPanel, parentDocumentNodeProperties);
	//
	// if (scannerDevice != null) {
	//
	// // If scanner device is scanner...
	// if (scannerDevice instanceof Scanner) {
	//
	// logger.info("Scanner device is of Scanner type.");
	//
	// Scanner scanner = (Scanner) scannerDevice;
	// scanner.setMode(Scanner.RGB_8);
	// scanner.setResolution(75);
	// // scanner.setFrame(100, 100, 500, 500);
	//
	// // document will be scanned and one of the method of handler
	// // will be fired upon success or failed scanning.
	// synchronized (handler) {
	//
	// logger.info("Document scanning is going to start.");
	//
	// scanner.startTransfer(showImageSettingUI, handler,
	// scannerType);
	// }
	//
	// return true;
	// }
	//
	// // If scanner device is of camera type...
	// else if (scannerDevice instanceof Camera) {
	//
	// logger.info("Scanner device is of Camera type.");
	//
	// // document will be scanned and one of the method of handler
	// // will be fired upon success or failed scanning.
	// synchronized (handler) {
	//
	// logger.info("Document scanning is going to start.");
	//
	// ((Camera) scannerDevice).startTransfer(showImageSettingUI,
	// handler); // SPEC
	// // SCANNER
	// }
	//
	// return true;
	// }
	//
	// // If no scanner device found.
	// else {
	// logger.error("Unknown Scanning Device. System can not scan.");
	// ErrorMessage.displayMessage('E', "unknownScannerDevice");
	//
	// return false;
	// }
	//
	// }
	//
	// else {
	// logger.error("No scanning device found. System can not scan.");
	// ErrorMessage.displayMessage('E', "noScanningDeviceFound");
	//
	// return false;
	// }
	// }
	//
	// /**
	// * @return the isScanningError
	// */
	// public boolean isScanningError() {
	// return isScanningError;
	// }
	//
	// /**
	// * @param isScanningError
	// * the isScanningError to set
	// */
	// public void setScanningError(boolean isScanningError) {
	// this.isScanningError = isScanningError;
	// }
}

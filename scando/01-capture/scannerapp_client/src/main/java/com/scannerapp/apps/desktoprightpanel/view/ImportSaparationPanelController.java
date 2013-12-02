package com.scannerapp.apps.desktoprightpanel.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.scannerapp.apps.framework.view.BaseController;
import com.scannerapp.common.SessionInfo;
import com.scannerapp.shared.NodeProperties;

public class ImportSaparationPanelController extends BaseController {

	private static Logger log = Logger
			.getLogger(ImportSaparationPanelController.class);// For Log4j

	private ImportSaparationPanelHelper importSaparationPanelHelper;
	protected SessionInfo sessionInfo;

	public ImportSaparationPanelController() {
		//super(jPanel);
		importSaparationPanelHelper = new ImportSaparationPanelHelper();
	}

	public void initialize() {
		super.initialize();
	}

	public ImportSaparationPanel view() {
		return (ImportSaparationPanel) getView();
	}

	/**
	 * Method to fetch hierarchy from DB for the provided barcode.
	 * 
	 * @param searchBarcode
	 * @return
	 */
	public String getHierarchyFromSearchBarcode(String searchBarcode) {
		return importSaparationPanelHelper
				.getHierarchyFromSearchBarcode(searchBarcode);
	}

	public List<NodeProperties> fetchChildNodeList(ArrayList<String> idList) {
		return importSaparationPanelHelper.fetchChildNodeList(idList);
	}

	/**
	 * Method to upload scanned images.
	 * 
	 * @param imageNodePropertiesList
	 * @return
	 */
	public HashMap<String, Boolean> uploadScannedImages(
			ArrayList<NodeProperties> imageNodePropertiesList) {

		return importSaparationPanelHelper
				.uploadScannedImages(imageNodePropertiesList);
	}

	public boolean checkIfNodeIsUpdatedByOtherUser(
			Map<String, String> nodeUpdateTimeMap) 
	{
		return importSaparationPanelHelper
		.checkIfNodeIsUpdatedByOtherUser((HashMap<String, String>)nodeUpdateTimeMap);
	}

	public int getChildNodeCount(String nodeId) {
		return importSaparationPanelHelper.getChildNodeCount(nodeId);
	}

	/**
	 * @return the importSaparationPanelHelper
	 */
	public ImportSaparationPanelHelper getImportSaparationPanelHelper() {
		return importSaparationPanelHelper;
	}

	/**
	 * @param importSaparationPanelHelper the importSaparationPanelHelper to set
	 */
	public void setImportSaparationPanelHelper(
			ImportSaparationPanelHelper importSaparationPanelHelper) {
		this.importSaparationPanelHelper = importSaparationPanelHelper;
	}
	
	

} // End of Class
package com.scannerapp.apps.desktoprightpanel.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.apache.log4j.Logger;

import com.scannerapp.apps.desktopleftpanel.view.CustomMutableTreeNode;
import com.scannerapp.apps.framework.view.BaseController;
import com.scannerapp.apps.utils.GeneralUtils;
import com.scannerapp.common.SessionInfo;
import com.scannerapp.shared.NodeProperties;

public class CollectionPanelController extends BaseController {
	private static Logger log = Logger
			.getLogger(CollectionPanelController.class);// For Log4j
	private CollectionPanelHelper collectionPanelHelper;
	protected SessionInfo sessionInfo;

	public CollectionPanelController(CollectionPanel jPanel) {
		super(jPanel);
		collectionPanelHelper = new CollectionPanelHelper();
	}

	public void initialize() {
		super.initialize();
	}

	public CollectionPanel view() {
		return (CollectionPanel) getView();
	}

	public void initializeScreen() {
		ArrayList initData = new ArrayList();
		try {
			initData = collectionPanelHelper.initializeScreen();
			if (initData != null) {
				sessionInfo = SessionInfo.getInstance();
				initData = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Hashtable getScreenUOAHashtable(Hashtable rights) {
		return GeneralUtils.getScreenUOAHashtable(rights);
	}

	public void addNodeButton_actionPerformed(String nodeId, String nodeName) {

		JTree nodeTree = view().getDesktopMainPanel().getjLeftPanel()
				.getNodeTree();
		CustomMutableTreeNode selectedElement = (CustomMutableTreeNode) nodeTree
				.getSelectionPath().getLastPathComponent();

		log.info("Selected Node: " + selectedElement.getUserObject());
		log.info("Sel Path: " + nodeTree.getSelectionPath());

		DefaultTreeModel model = (DefaultTreeModel) nodeTree.getModel();

		CustomMutableTreeNode newNode = new CustomMutableTreeNode(nodeName);

		model.insertNodeInto(newNode, selectedElement,
				selectedElement.getChildCount());

		if (nodeId == null)
			nodeTree.setSelectionPath(new TreePath(newNode.getPath()));

		if (nodeId != null && nodeId.trim().length() > 0)
			newNode.setNodeId(nodeId);
	}

	public NodeProperties saveNodeButton_actionPerformed(
			NodeProperties newNodePOJO) {

		NodeProperties saveNode = collectionPanelHelper.saveNode(newNodePOJO);

		return saveNode;

	}

	/**
	 * Method to save the list of node properties in the DB.
	 * 
	 * @param nodePropertiesList
	 * @return
	 */
	public ArrayList<NodeProperties> saveNodePropertiesList(
			ArrayList<NodeProperties> nodePropertiesList) {

		ArrayList<NodeProperties> listOfNodeProperties = collectionPanelHelper
				.saveNodePropertiesList(nodePropertiesList);

		return listOfNodeProperties;
	}

	public boolean checkIfNodeIsUpdatedByOtherUser(
			Map<String, String> nodeUpdateTimeMap) {
		return collectionPanelHelper
		.checkIfNodeIsUpdatedByOtherUser((HashMap<String, String>)nodeUpdateTimeMap);
	}

	public List<NodeProperties> fetchChildNodeList(ArrayList<String> idList) 
	{
		return collectionPanelHelper.fetchChildNodeList(idList);
	}

	public String getHierarchyFromSearchBarcode(String barcode) {
		return collectionPanelHelper.getHierarchyFromSearchBarcode(barcode);
	}
} // End of Class
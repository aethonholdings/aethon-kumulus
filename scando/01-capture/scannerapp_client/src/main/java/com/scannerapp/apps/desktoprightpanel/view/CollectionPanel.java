package com.scannerapp.apps.desktoprightpanel.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.apache.log4j.Logger;

import com.scannerapp.apps.component.EdittedTextField;
import com.scannerapp.apps.desktopleftpanel.view.CustomMutableTreeNode;
import com.scannerapp.apps.desktopmainpanel.view.DesktopMainJPanel;
import com.scannerapp.apps.framework.view.BaseJPanel;
import com.scannerapp.apps.framework.view.ErrorMessage;
import com.scannerapp.apps.utils.ConstantUtil;
import com.scannerapp.apps.utils.SessionUtil;
import com.scannerapp.common.HelpPopup;
import com.scannerapp.common.NodePropertyConstants;
import com.scannerapp.resources.IconRepository;
import com.scannerapp.shared.NodeProperties;
import com.scannerapp.shared.TransactionConstant;

public class CollectionPanel extends BaseJPanel implements ActionListener,
		FocusListener, ListSelectionListener {

	private static Logger log = Logger.getLogger(CollectionPanel.class);// For

	private JPanel mainPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JPanel dataPanel = new JPanel();
	private JPanel buttonBorderPanel = new JPanel();
	private GridBagLayout buttonPanelLayout = new GridBagLayout();
	private GridBagLayout dataPanelLayout = new GridBagLayout();
	private GridBagLayout mainPanelLayout = new GridBagLayout();
	private JButton addNodeButton = new JButton("");
	private JButton saveButton = new JButton("");
	private JButton deleteButton = new JButton("");
	private JButton helpButton = new JButton("");

	private JButton cutButton = new JButton("");
	private JButton pasteButton = new JButton("");
	private JButton cancelButton = new JButton("");

	private JLabel barCodeLabel = new JLabel();
	private EdittedTextField barCodeFeild = new EdittedTextField(20);
	private JLabel commentLabel = new JLabel();
	private JTextArea commentFeild = new JTextArea();
	private JScrollPane commentScroll = new JScrollPane(commentFeild);
	private JLabel intCommentLabel = new JLabel();
	private JTextArea intCommentFeild = new JTextArea();
	private JScrollPane intCommentScroll = new JScrollPane(intCommentFeild);
	private JLabel statusLabel = new JLabel();

	private JLabel nodeTypeLabel = new JLabel();

	DefaultListModel listModel;
	private JList nodeTypeList;

	DefaultListModel statusListModel;
	private JList statusList;

	private DesktopMainJPanel desktopMainPanel;

	private JScrollPane nodeTypeScroll;
	private JScrollPane statusScroll;

	private CustomMutableTreeNode newAddedNode = null;
	private ArrayList<CustomMutableTreeNode> nodesToMarkDone = new ArrayList<CustomMutableTreeNode>();
	private ArrayList<CustomMutableTreeNode> nodesToMarkInProgress = new ArrayList<CustomMutableTreeNode>();

	// added for Cut/Paste functionality
	private CustomMutableTreeNode[] nodesToCut = null;

	public CollectionPanel() {
		initCollectionPanel();
		initHandlers();
	}

	public CollectionPanel(DesktopMainJPanel desktopMainPanel) {
		this.desktopMainPanel = desktopMainPanel;
		this.setLayout(new GridBagLayout());
		setController(new CollectionPanelController(this));

		// Fetch and Set Data from Session (Set Data to Combobox and ListBox)
		initDataFromSession();

		boolean rootNode = isRootNode(desktopMainPanel.getjLeftPanel()
				.getProjectNode().toString());
		log.info("is Root Node: Disable Property: " + rootNode);
		if (rootNode) {
			nodePropertyEnableDisable(false); // Disable Node Property if
												// Selected Node is Root Node
			nodeTypeList.setEnabled(false);
			setButtonEnableDisable(false);
		}

		//initCollectionPanel();
		initHandlers();

	}

	public void setButtonEnableDisable(boolean enabled) {
		saveButton.setEnabled(enabled);
		deleteButton.setEnabled(enabled);
	}

	private void initDataFromSession() {

		statusListModel = new DefaultListModel();
		Map<String, String> statusMap = SessionUtil.getSessionData()
				.getStatusMap();
		List<String> statusListFromMap = new ArrayList<String>(
				statusMap.keySet());
		Iterator<String> statusIterator = statusListFromMap.iterator();

		while (statusIterator.hasNext()) {
			String status = statusIterator.next();
			statusListModel.addElement(status);
		}

		statusList = new JList(statusListModel);
		statusScroll = new JScrollPane(statusList);

		listModel = new DefaultListModel();
		Map<String, String> nodeTypeMap = SessionUtil.getSessionData()
				.getNodeTypeMap();
		List<String> nodeTypeKeyList = new ArrayList<String>(
				nodeTypeMap.keySet());
		Iterator<String> nodeTypeIterator = nodeTypeKeyList.iterator();

		while (nodeTypeIterator.hasNext()) {
			String nodeType = nodeTypeIterator.next();
			listModel.addElement(nodeType);
		}

		nodeTypeList = new JList(listModel);
		nodeTypeList.setSelectedIndex(0);
		nodeTypeScroll = new JScrollPane(nodeTypeList);

	}

	// Method Also Used In DesktopLeftJPanel->valueChanged()
	public void nodePropertyEnableDisable(boolean enable) {
		barCodeFeild.setEnabled(enable);
		commentFeild.setEnabled(enable);
		intCommentFeild.setEnabled(enable);
		statusList.setEnabled(enable);
		// nodeTypeList.setEnabled(enable);
	}

	private void initHandlers() {
		addNodeButton.addActionListener((ActionListener) this);
		saveButton.addActionListener((ActionListener) this);
		deleteButton.addActionListener((ActionListener) this);
		helpButton.addActionListener((ActionListener) this);

		cutButton.addActionListener((ActionListener) this);
		pasteButton.addActionListener((ActionListener) this);
		cancelButton.addActionListener((ActionListener) this);

		barCodeFeild.addFocusListener((FocusListener) this);
		commentFeild.addFocusListener((FocusListener) this);
		intCommentFeild.addFocusListener((FocusListener) this);

		statusList.addListSelectionListener((ListSelectionListener) this);
		nodeTypeList.addListSelectionListener((ListSelectionListener) this);

		/* desktopMainPanel.getjLeftPanel().getNodeTree().add */
	}

	public void initCollectionPanel() {
		initButtonPanel();
		setButtonPanelCaptions();
		setLabelCaption();
		initDataPanel();

		mainPanel.setLayout(mainPanelLayout);

		mainPanel.add(buttonPanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));

		mainPanel.add(dataPanel, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));

		this.add(mainPanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));

	}

	private void setLabelCaption() {
		barCodeLabel.setText(ConstantUtil.getApplicationConstant("barcode"));
		commentLabel.setText(ConstantUtil.getApplicationConstant("comment"));
		intCommentLabel.setText(ConstantUtil
				.getApplicationConstant("internalComment"));
		statusLabel.setText(ConstantUtil.getApplicationConstant("status"));
		nodeTypeLabel
				.setText(ConstantUtil.getApplicationConstant("type") + "*");
	}

	private void setButtonPanelCaptions() {
		addNodeButton.setText(ConstantUtil.getApplicationConstant("addNode"));
		saveButton.setText(ConstantUtil.getApplicationConstant("save"));
		deleteButton.setText(ConstantUtil.getApplicationConstant("delete"));
		helpButton.setText(ConstantUtil.getApplicationConstant("help"));

		cutButton.setText(ConstantUtil.getApplicationConstant("cut"));
		pasteButton.setText(ConstantUtil.getApplicationConstant("paste"));
		cancelButton.setText(ConstantUtil.getApplicationConstant("cancel"));

	}

	private void initButtonPanel() {

		buttonPanel.setLayout(buttonPanelLayout);
		// buttonPanel.setPreferredSize(new Dimension(900, 80));
		// buttonPanel.setMinimumSize(new Dimension(700, 80));

		buttonPanel.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));

		addNodeButton.setPreferredSize(new Dimension(110, 30));
		addNodeButton.setMinimumSize(new Dimension(110, 30));
		addNodeButton.setIcon(IconRepository.ADD_ICON);

		saveButton.setPreferredSize(new Dimension(110, 30));
		saveButton.setMinimumSize(new Dimension(110, 30));
		saveButton.setIcon(IconRepository.SAVE_ICON);

		deleteButton.setPreferredSize(new Dimension(110, 30));
		deleteButton.setMinimumSize(new Dimension(110, 30));
		deleteButton.setIcon(IconRepository.DELETE_ICON);

		helpButton.setPreferredSize(new Dimension(110, 30));
		helpButton.setMinimumSize(new Dimension(110, 30));
		helpButton.setIcon(IconRepository.HELP_ICON);

		cutButton.setPreferredSize(new Dimension(110, 30));
		cutButton.setMinimumSize(new Dimension(110, 30));
		cutButton.setIcon(IconRepository.ICON_CUT);

		pasteButton.setPreferredSize(new Dimension(110, 30));
		pasteButton.setMinimumSize(new Dimension(110, 30));
		pasteButton.setIcon(IconRepository.ICON_PASTE);
		pasteButton.setEnabled(false);

		cancelButton.setPreferredSize(new Dimension(110, 30));
		cancelButton.setMinimumSize(new Dimension(110, 30));
		cancelButton.setIcon(IconRepository.CANCEL_ICON);
		cancelButton.setEnabled(false);

		buttonPanel.add(addNodeButton, new GridBagConstraints(0, 0, 1, 1, 1.0,
				1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(10, 5, 5, 0), 0, 0));// east none
		buttonPanel.add(saveButton, new GridBagConstraints(1, 0, 1, 1, 1.0,
				1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(10, 5, 5, 0), 0, 0));// east none
		buttonPanel.add(deleteButton, new GridBagConstraints(2, 0, 1, 1, 1.0,
				1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(10, 5, 5, 5), 0, 0));// east none
		buttonPanel.add(helpButton, new GridBagConstraints(3, 0, 1, 1, 1.0,
				1.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(10, 5, 5, 5), 0, 0));// east none
		buttonPanel.add(cutButton, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
				GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
						10, 5, 5, 5), 0, 0));// east none
		buttonPanel.add(pasteButton, new GridBagConstraints(1, 1, 1, 1, 1.0,
				1.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(10, 5, 5, 5), 0, 0));// east none
		buttonPanel.add(cancelButton, new GridBagConstraints(2, 1, 1, 1, 1.0,
				1.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(10, 5, 5, 5), 0, 0));// east none

	}

	private void initDataPanel() {

		dataPanel.setLayout(dataPanelLayout);
		// dataPanel.setPreferredSize(new Dimension(900, 545));
		// dataPanel.setMinimumSize(new Dimension(700, 545));

		dataPanel.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));

		commentScroll.setPreferredSize(new Dimension(500, 90));
		commentScroll.setMinimumSize(new Dimension(500, 90));

		intCommentScroll.setPreferredSize(new Dimension(500, 90));
		intCommentScroll.setMinimumSize(new Dimension(500, 90));

		nodeTypeScroll.setPreferredSize(new Dimension(180, 62));
		nodeTypeScroll.setMinimumSize(new Dimension(180, 62));

		statusScroll.setPreferredSize(new Dimension(180, 62));
		statusScroll.setMinimumSize(new Dimension(180, 62));

		nodeTypeList.setBorder(BorderFactory.createLoweredBevelBorder());
		nodeTypeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		nodeTypeList.setLayoutOrientation(JList.VERTICAL);

		statusList.setBorder(BorderFactory.createLoweredBevelBorder());
		statusList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		statusList.setLayoutOrientation(JList.VERTICAL);

		// Code to set The Value of Left Panel Node Property lable for Selected
		// NodeStatus
		boolean rootNode = isRootNode(desktopMainPanel.getjLeftPanel()
				.getProjectNode().toString());
		log.info("isRootNode : " + rootNode);
		if (rootNode)
			desktopMainPanel.getjLeftPanel().getStatusLabelValue().setText("");
		else
			desktopMainPanel.getjLeftPanel().getStatusLabelValue()
					.setText(statusList.getSelectedValue().toString());

		dataPanel.add(barCodeLabel, new GridBagConstraints(0, 0, 1, 1, 1.0,
				1.0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE,
				new Insets(80, 20, 20, 10), 0, 0));
		dataPanel.add(barCodeFeild, new GridBagConstraints(1, 0, 1, 1, 1.0,
				1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
				new Insets(80, 20, 20, 10), 0, 0));
		dataPanel.add(commentLabel, new GridBagConstraints(0, 1, 1, 1, 1.0,
				1.0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE,
				new Insets(10, 20, 20, 20), 0, 0));// east none
		dataPanel.add(commentScroll, new GridBagConstraints(1, 1, 1, 2, 1.0,
				1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
				new Insets(10, 20, 20, 20), 0, 0));// east none
		dataPanel.add(intCommentLabel, new GridBagConstraints(0, 2, 1, 1, 1.0,
				1.0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE,
				new Insets(70, 20, 20, 20), 0, 0));// east none
		dataPanel.add(intCommentScroll, new GridBagConstraints(1, 2, 1, 2, 1.0,
				1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
				new Insets(70, 20, 20, 20), 0, 0));// east none
		dataPanel.add(statusLabel, new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0,
				GridBagConstraints.NORTHEAST, GridBagConstraints.NONE,
				new Insets(70, 20, 10, 20), 0, 0));// east none
		dataPanel.add(statusScroll, new GridBagConstraints(1, 3, 1, 1, 1.0,
				1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
				new Insets(70, 20, 10, 20), 0, 0));// east none
		dataPanel.add(nodeTypeLabel, new GridBagConstraints(0, 4, 1, 1, 1.0,
				1.0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE,
				new Insets(10, 20, 20, 20), 0, 0));// east none
		dataPanel.add(nodeTypeScroll, new GridBagConstraints(1, 4, 1, 2, 1.0,
				1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
				new Insets(10, 20, 20, 20), 0, 0));// east none
	}

	private boolean isRootNode(String nodeName) {
		JTree nodeTree = desktopMainPanel.getjLeftPanel().getNodeTree();
		CustomMutableTreeNode selectedElement = (CustomMutableTreeNode) nodeTree
				.getSelectionPath().getLastPathComponent();
		log.info("Selected Tree Node: " + selectedElement);
		log.info("Selected Tree Node Object: "
				+ selectedElement.getUserObject());
		log.info("Project Node: "
				+ desktopMainPanel.getjLeftPanel().getProjectNode());
		if (selectedElement.getUserObject().toString().equals(nodeName))
			return true;
		else
			return false;
	}

	// Method Also Used in DesktopLeftJPanel and EditNodePropertyPopup
	public CustomMutableTreeNode getSelectedTreeNode() {

		JTree nodeTree = desktopMainPanel.getjLeftPanel().getNodeTree();

		// In case the node is deselected (by Ctrl + Click), selected node will
		// not be found and hence return from method.
		if (nodeTree.getSelectionPath() == null
				|| nodeTree.getSelectionPath().getLastPathComponent() == null) {

			ErrorMessage.displayMessage('I', "pleaseSelectNode");

			return null;
		}

		CustomMutableTreeNode selectedElement = (CustomMutableTreeNode) nodeTree
				.getSelectionPath().getLastPathComponent();

		log.info("Selected Node Path: " + nodeTree.getSelectionPath());
		log.info("Selected Tree Node: " + selectedElement);
		log.info("Parent node path: "
				+ nodeTree.getSelectionPath().getParentPath());

		if (!isRootNode(selectedElement.toString()))
			log.info("Parent Node Id: "
					+ ((CustomMutableTreeNode) selectedElement.getParent())
							.getNodeId());

		log.info("Selected Node Id: " + selectedElement.getNodeId());

		return selectedElement;
	}

	public void actionPerformed(ActionEvent e) {

		// Before starting any action, checking if mutliple nodes are selected.
		if (e.getSource() != helpButton) {

			// desktopMainPanel.getjLeftPanel().setNodesToCut(null);

			if (isMultipleNodesSelected())
				return;
		}

		if (e.getSource() == addNodeButton) {
			log.info("Click Add Node");

			CustomMutableTreeNode newNode = getSelectedTreeNode();

			if (newNode == null)
				return;

			// Condition to check if node is saved or newly added node.
			if (newNode.getNodeId().equals(
					SessionUtil.getSessionData().getProjectId())
					|| (newNode.getNodeId() != null && newNode.getNodeId()
							.trim().length() > 0)) {

				if (isSelectedNodeIsInDoneStatus())
					ErrorMessage.displayMessage('I',
							"notAllowedToAddNodeInDoneState");

				else if (isDocumentNode())
					ErrorMessage.displayMessage('I',
							"notAllowedToAddInDocumentNode");

				else if (isNodePropertyModified())
					ErrorMessage.displayMessage('I', "saveOrDeleteUpdatedNode");

				else {
					controller().addNodeButton_actionPerformed(null,
							ConstantUtil.getApplicationConstant("newNode"));
					nodePropertyEnableDisable(true);
					nodeTypeList.setEnabled(true);
					resetNodeProperty();
					resetLeftPanelNodePropertyLabel();

					newAddedNode = getSelectedTreeNode();

					// Below check is not effective and confusing. It is just
					// kept to perform check, nothing else.
					if (newAddedNode == null)
						return;

					desktopMainPanel.getjLeftPanel().getNodeTree()
							.expandPath(new TreePath(newAddedNode.getPath()));
					desktopMainPanel.getjLeftPanel().getNodeTree().updateUI();
				}
			} else {
				ErrorMessage.displayMessage('I', "saveOrDeleteAddedNode");
			}
		}

		if (e.getSource() == saveButton) {

			CustomMutableTreeNode newNode = getSelectedTreeNode();

			if (newNode == null)
				return;

			if (isValidProperties()
					&& newNode.getNodeId() != SessionUtil.getSessionData()
							.getProjectId()) {

				nodesToMarkDone.clear();
				nodesToMarkInProgress.clear();

				String selectedStatusInListBox = statusList.getSelectedValue()
						.toString();

				// To perform the cascade update to done status.
				if (hasStatusChangeToDone(newNode, selectedStatusInListBox)) {

					boolean allowedToProceed = canBeMarkedDone(newNode,
							selectedStatusInListBox, true);

					if (allowedToProceed) {
						markParentNodesDone(newNode, selectedStatusInListBox);
					} else {
						ErrorMessage.displayMessage('I',
								"nodeCanNotBeMarkedDone");
						return;
					}
				}

				// To perform the cascade update to in progress status.
				if (hasStatusChangeToInProgress(newNode,
						selectedStatusInListBox)) {

					markParentNodesInProgress(newNode, selectedStatusInListBox);
				}

				NodeProperties newNodePOJO = initPOJOToSaveNodeProperty(newNode);
				if (newNodePOJO == null) {
					return;
				}

				NodeProperties savedNewNode = controller()
						.saveNodeButton_actionPerformed(newNodePOJO);
				newNode.setNodeId(savedNewNode.getNodeId());

				nodeTypeList.setEnabled(false);
				desktopMainPanel.getjLeftPanel().getNameLabelValue()
						.setText(savedNewNode.getName());

				desktopMainPanel.getjLeftPanel().getNodePropertiesMap()
						.put(savedNewNode.getNodeId(), savedNewNode);

				newNode.setUserObject(savedNewNode.getName());
				desktopMainPanel.getjLeftPanel().getNodeTree().updateUI();

				// update nodes to done state.
				updateNodesToDoneState();

				// update nodes to in progress state.
				updateNodesToInProgressState();

				// Marking All Property disable for Done Status
				if (savedNewNode.getStatus().equals(NodePropertyConstants.DONE)) {
					desktopMainPanel.getjRightPanel().getCollectionPanel()
							.setAllButtonEnableDisable(false);
					desktopMainPanel.getjRightPanel().getCollectionPanel()
							.setDoneStatusNodePropertyEnableDisable(false);
					// Save Button Enabled from
					// setDoneStatusNodePropertyEnableDisable() method

					/*
					 * // If Node Is In Done Status And Node Is Not Document
					 * Node - Import And Separation Panel
					 * if(!savedNewNode.getType().equalsIgnoreCase("D")) {
					 * desktopMainPanel.getjRightPanel().getImportAndSepPanel().
					 * setButtonDisableWhileNodeInDoneStatus(false); } // If
					 * Node Is In Done Status And Node Is Document Node - Import
					 * And Separation Panel else
					 * if(savedNewNode.getType().equalsIgnoreCase("D")) {
					 * desktopMainPanel.getjRightPanel().getImportAndSepPanel().
					 * setButtonDisableWhileNodeInDoneStatus(false);
					 * desktopMainPanel.getjRightPanel().getImportAndSepPanel().
					 * getEditNodePropertyButton().setEnabled(true); }
					 */

					desktopMainPanel.getjLeftPanel()
							.enableDisableImportSeparationButtons(savedNewNode);

				} else {
					// Check Condition If Node Is Updated To Done
					if (savedNewNode.getStatus().equals(
							NodePropertyConstants.IN_PROGRESS)) {
						/*
						 * // If Node Is Document Node Then Enable Disable
						 * Import And Separation Buttons - Import And Separation
						 * Panel if(savedNewNode.getType().equals("D")) {
						 * desktopMainPanel
						 * .getjRightPanel().getImportAndSepPanel
						 * ().setButtonDisableWhileNodeInDoneStatus(true);
						 * desktopMainPanel
						 * .getjRightPanel().getImportAndSepPanel
						 * ().getDeleteDocumentButton().setEnabled(true);
						 * desktopMainPanel
						 * .getjRightPanel().getImportAndSepPanel
						 * ().getImportButton().setEnabled(true);
						 * desktopMainPanel
						 * .getjRightPanel().getImportAndSepPanel
						 * ().getResumeImportButton().setEnabled(true); } //
						 * Import And Separation PanelButton Enable Disable else
						 * {
						 * desktopMainPanel.getjRightPanel().getImportAndSepPanel
						 * ().setAllButtonEnableDisable(true); }
						 */
						desktopMainPanel.getjLeftPanel()
								.enableDisableImportSeparationButtons(
										savedNewNode);
					}

					desktopMainPanel.getjRightPanel().getCollectionPanel()
							.getAddNodeButton().setEnabled(true);
					desktopMainPanel.getjRightPanel().getCollectionPanel()
							.getCutButton().setEnabled(true);
					desktopMainPanel.getjRightPanel().getCollectionPanel()
							.setButtonEnableDisable(true);
					desktopMainPanel.getjRightPanel().getCollectionPanel()
							.nodePropertyEnableDisable(true);
					desktopMainPanel.getjRightPanel().getCollectionPanel()
							.getNodeTypeList().setEnabled(false);
				}

				// Marking node to null because when node is saved, it is not
				// newly added.
				newAddedNode = null;

			} else
				log.info("Selected Node is Root node");
		}

		if (e.getSource() == deleteButton) {

			nodesToCut = null;
			deleteNode();
		}

		if (e.getSource() == helpButton) {
			log.info("Click Help");

			HelpPopup help = new HelpPopup();
			int selectedTab = desktopMainPanel.getjRightPanel().getTabbedPane()
					.getSelectedIndex();
			help.setHelpText(selectedTab);
		}

		if (e.getSource() == cutButton) {

			log.info("Cut Button");

			cutNodes();

		}

		if (e.getSource() == pasteButton) {

			log.info("Paste Button");

			pasteNodes();
		}

		if (e.getSource() == cancelButton) {

			log.info("Cancel Button");

			nodesToCut = null;

			/*
			 * pasteButton.setEnabled(false); cancelButton.setEnabled(false);
			 * addNodeButton.setEnabled(true); saveButton.setEnabled(true);
			 * deleteButton.setEnabled(true);
			 */

			// Make Button Enable/Disable After Cut / Cancel
			getAddNodeButton().setEnabled(true);
			setButtonEnableDisable(true);
			getCutButton().setEnabled(true);
			getPasteButton().setEnabled(false);
			getCancelButton().setEnabled(false);
			desktopMainPanel.getjLeftPanel().getJbtnRefresh().setEnabled(true);

			// helpButton.setEnabled(true);

		}
	}

	private void cutNodes() {

		// desktopMainPanel.getjLeftPanel().performCheckBeforeSelectingNode();

		if (newAddedNode != null) {
			ErrorMessage.displayMessage('I',
					"saveOrDeleteAddedNodeBeforeCutNode");
			return;
		}
		if (isNodePropertyModified()) {
			ErrorMessage
					.displayMessage('I', "saveOrDeleteUpdatedNodeBeforeCut");
			return;
		}
		// Getting selected nodes to cut.
		TreePath[] selectedTreePaths = desktopMainPanel.getjLeftPanel()
				.getNodeTree().getSelectionPaths();

		if (selectedTreePaths == null || selectedTreePaths.length <= 0) {
			ErrorMessage.displayMessage('I', "selectAtleastOneNodeToCut");
			return;
		}

		nodesToCut = new CustomMutableTreeNode[selectedTreePaths.length];

		for (int index = 0; index < selectedTreePaths.length; index++) {

			// If Project Node is selected to cut...
			if (((CustomMutableTreeNode) selectedTreePaths[index]
					.getLastPathComponent()).getNodeId().equals(
					SessionUtil.getSessionData().getProjectId())) {

				nodesToCut = null;
				ErrorMessage.displayMessage('I', "rootNodeNotAllowToCut");
				return;
			}

			cutButton.setEnabled(false);
			pasteButton.setEnabled(true);
			cancelButton.setEnabled(true);
			addNodeButton.setEnabled(false);
			saveButton.setEnabled(false);
			deleteButton.setEnabled(false);
			// helpButton.setEnabled(false);
			nodesToCut[index] = (CustomMutableTreeNode) selectedTreePaths[index]
					.getLastPathComponent();
		}

		desktopMainPanel.getjLeftPanel().getJbtnRefresh().setEnabled(false);
	}

	private void pasteNodes() {
		TreePath[] selectedTreePaths = desktopMainPanel.getjLeftPanel()
				.getNodeTree().getSelectionPaths();

		if (nodesToCut == null || nodesToCut.length == 0) {
			ErrorMessage.displayMessage('I', "noNodesToCutPaste");
			return;
		}

		if (selectedTreePaths == null || selectedTreePaths.length <= 0) {
			ErrorMessage.displayMessage('I', "noDestinationNodeSelected");
			return;
		}

		if (selectedTreePaths != null && selectedTreePaths.length > 1) {
			ErrorMessage.displayMessage('I', "moreThanOneDestinationNode");
			return;
		}

		CustomMutableTreeNode destinationNode = (CustomMutableTreeNode) selectedTreePaths[0]
				.getLastPathComponent();

		// Checking if one of nodes that are cut and destination node is
		// same...
		for (int index = 0; index < nodesToCut.length; index++) {

			if (destinationNode == nodesToCut[index]) {

				ErrorMessage.displayMessage('I',
						"sameCutNodeAndDestinationNodeNotAllowed");
				nodesToCut = null;
				return;
			}
		}

		// To check if destination node is Project Root Node...
		boolean isProjectNodeAsDestinationNode = destinationNode.getNodeId()
				.equals(SessionUtil.getSessionData().getProjectId());

		if (!isProjectNodeAsDestinationNode) {

			NodeProperties destinationNodeProperty = desktopMainPanel
					.getjLeftPanel().getNodePropertiesMap()
					.get((destinationNode).getNodeId());

			// If Project Node is not destination node, destination node
			// should not be Document node or with Done status.
			if (destinationNodeProperty.getStatus().equals(
					NodePropertyConstants.DONE)) {
				ErrorMessage.displayMessage('I',
						"notAllowedToAddNodeInDoneState");
				return;
			}

			if (destinationNodeProperty.getType().equals(
					NodePropertyConstants.DOCUMENT)) {
				ErrorMessage.displayMessage('I',
						"notAllowedToAddInDocumentNode");
				return;
			}
		}

		ArrayList<NodeProperties> nodePropertiesList = new ArrayList<NodeProperties>();
		Map<String, String> nodeUpdateTimeMap = new HashMap<String, String>();
		NodeProperties destinationNodeProperty = desktopMainPanel
				.getjLeftPanel().getNodePropertiesMap()
				.get((destinationNode).getNodeId());

		for (int index = 0; index < nodesToCut.length; index++) {

			// Below line will add node to new destination node...
			destinationNode.add(nodesToCut[index]);

			// Updating node properties...
			NodeProperties nodeProperties = desktopMainPanel.getjLeftPanel()
					.getNodePropertiesMap().get(nodesToCut[index].getNodeId());

			// Condition to check that if Destination node is root node then set
			// parent node id to null
			if (isProjectNodeAsDestinationNode) {
				nodeProperties.setParentNodeId(null);
			} else {
				nodeProperties.setParentNodeId(destinationNode.getNodeId());
			}
			nodeProperties.setHierarchy((new TreePath(nodesToCut[index]
					.getPath())).toString());
			nodeProperties.setUserId(SessionUtil.getSessionData().getUserId());
			nodePropertiesList.add(nodeProperties);
			nodeUpdateTimeMap.put(nodeProperties.getNodeId(),
					nodeProperties.getLastUpdateDateTime());
		}

		if (!isProjectNodeAsDestinationNode) {
			nodeUpdateTimeMap.put(destinationNodeProperty.getNodeId(),
					destinationNodeProperty.getLastUpdateDateTime());
		}

		// Condition to check That Cut/Paste Node Is Updated by Other User Or
		// Not
		boolean isUpdated = controller().checkIfNodeIsUpdatedByOtherUser(
				nodeUpdateTimeMap);
		if (isUpdated) {
			ErrorMessage.displayMessage('I',
					"updatedByOtherUserPleaseRefreshTree");
			nodesToCut = null;
			return;
		} else {
			// Expanding Destination node.
			((DefaultTreeModel) desktopMainPanel.getjLeftPanel().getNodeTree()
					.getModel()).reload();
			desktopMainPanel.getjLeftPanel().getNodeTree()
					.setSelectionPath(new TreePath(destinationNode.getPath()));
			desktopMainPanel.getjLeftPanel().getNodeTree()
					.expandPath(new TreePath(destinationNode.getPath()));

			// Making the call to server to update in DB and get new node
			// properties list.
			nodePropertiesList = desktopMainPanel.getjRightPanel()
					.getCollectionPanel().controller()
					.saveNodePropertiesList(nodePropertiesList);

			if (nodePropertiesList != null && nodePropertiesList.size() > 0) {

				for (int index = 0; index < nodePropertiesList.size(); index++) {

					NodeProperties nodeProperties = nodePropertiesList
							.get(index);

					desktopMainPanel.getjLeftPanel().getNodePropertiesMap()
							.put(nodeProperties.getNodeId(), nodeProperties);
				}
			}

			// Make Button Enable/Disable After Cut / Cancel
			saveButton.setEnabled(true);
			deleteButton.setEnabled(true);
			addNodeButton.setEnabled(true);
			setButtonEnableDisable(true);
			cutButton.setEnabled(true);
			pasteButton.setEnabled(false);
			cancelButton.setEnabled(false);
			desktopMainPanel.getjLeftPanel().getJbtnRefresh().setEnabled(true);

			nodesToCut = null;
		}

		/*
		 * cutButton.setEnabled(true); pasteButton.setEnabled(false);
		 * cancelButton.setEnabled(false); addNodeButton.setEnabled(true);
		 */
		/*
		 * saveButton.setEnabled(true); deleteButton.setEnabled(true);
		 */
		// helpButton.setEnabled(true);
	}

	public void deleteNode() {
		CustomMutableTreeNode newNode = getSelectedTreeNode();

		if (newNode == null)
			return;

		if (newNode.getNodeId() != SessionUtil.getSessionData().getProjectId()
				&& newNode.getChildCount() == 0) {

			boolean allowToDeleteNode = true;

			// If node in DB is being deleted...
			if (newNode.getNodeId() != null && newNode.getNodeId().length() > 0) {

				if (isSelectedNodeIsInDoneStatus()) {

					allowToDeleteNode = false;
					ErrorMessage.displayMessage('I',
							"notAllowedToDeleteNodeInDoneState");
				} else {
					// delete confirmation
					int selectedValue = ErrorMessage.displayMessage('Q',
							"deleteNodeConfirmation");

					if (selectedValue == JOptionPane.NO_OPTION)
						return;

					NodeProperties nodeProperties = desktopMainPanel
							.getjLeftPanel().getNodePropertiesMap()
							.get(newNode.getNodeId());

					// Condition to check That Deleted Node Is Updated by Other
					// User Or Not
					Map<String, String> nodeUpdateTimeMap = new HashMap<String, String>();
					nodeUpdateTimeMap.put(nodeProperties.getNodeId(),
							nodeProperties.getLastUpdateDateTime());
					boolean isUpdated = controller()
							.checkIfNodeIsUpdatedByOtherUser(nodeUpdateTimeMap);
					if (isUpdated) {
						ErrorMessage.displayMessage('I',
								"updatedByOtherUserPleaseRefreshTree");
						
						// Added Code To Reset Field Value Of Selected Node
						// When User Change Some Property Of Node And Node is Updated by Other Users Then Selected Node Actual(old not Updated by Other User) Value Became Selected.
						desktopMainPanel.getjLeftPanel().controller().getAndSetNodePropertyValue();						
						setTreeNodeUserObject(nodeProperties);
						
						return;
					}

					// Condition To Check Node Having Child Node Then Not Allow
					// To Delete
					// If Document Node Then Contains Pages So Not Allow To
					// Delete Document
					List<NodeProperties> childNodePropertyList = fetchChildNodes(newNode);
					if (childNodePropertyList != null) {
						if (!childNodePropertyList.isEmpty()) {
							ErrorMessage.displayMessage('I',
									"deleteNodeWithChild");
							return;
						} else {
							nodeProperties
									.setTransactionStatus(TransactionConstant.DELETE);
							controller().saveNodeButton_actionPerformed(
									nodeProperties);
							desktopMainPanel.getjLeftPanel()
									.getNodePropertiesMap()
									.remove(newNode.getNodeId());
						}
					}
				}
			}

			if (allowToDeleteNode) {

				TreeNode[] selectedTreeNodes = newNode.getPath();
				TreeNode[] parentTreeNodes = new TreeNode[selectedTreeNodes.length - 1];

				for (int index = 0; index < selectedTreeNodes.length - 1; index++) {
					parentTreeNodes[index] = selectedTreeNodes[index];
				}

				TreePath parentNodeTreePath = new TreePath(parentTreeNodes);

				newNode.removeFromParent();

				// Marking node to null because when node is saved, it is
				// not newly added.
				newAddedNode = null;

				desktopMainPanel.getjLeftPanel().getNodeTree()
						.setSelectionPath(parentNodeTreePath);
				desktopMainPanel.getjLeftPanel().getNodeTree().updateUI();
				desktopMainPanel.getjLeftPanel().getNodeTree().repaint();
				nodeTypeList.setEnabled(false);
			}

		} else {
			ErrorMessage.displayMessage('I', "deleteNodeWithChild");
		}
	}

	private boolean isMultipleNodesSelected() {

		TreePath[] selectedTreePaths = desktopMainPanel.getjLeftPanel()
				.getNodeTree().getSelectionPaths();

		if (selectedTreePaths != null && selectedTreePaths.length > 1) {
			ErrorMessage.displayMessage('I',
					"pleaseSelectOnlyOneNodeToAddSaveDelete");

			return true;
		}

		return false;
	}

	// Used In ImportSaparationPanel
	public boolean isSelectedNodeIsInDoneStatus() {
		boolean isNodeInDoneStatus = false;

		CustomMutableTreeNode selectedElement = getSelectedTreeNode();

		if (selectedElement != null && selectedElement.getNodeId() != null
				&& selectedElement.getNodeId().trim().length() > 0) {

			if (!selectedElement.getNodeId().equals(
					SessionUtil.getSessionData().getProjectId())) {

				NodeProperties selectedNodeProperty = desktopMainPanel
						.getjLeftPanel().getNodePropertiesMap()
						.get(selectedElement.getNodeId());

				if (selectedNodeProperty.getStatus().equals(
						NodePropertyConstants.DONE))
					isNodeInDoneStatus = true;
			}
		}

		return isNodeInDoneStatus;
	}

	// Used In ImportSaparationPanel
	public boolean isDocumentNode() {

		boolean isDocumentNode = false;

		CustomMutableTreeNode selectedElement = getSelectedTreeNode();

		if (selectedElement != null && selectedElement.getNodeId() != null
				&& selectedElement.getNodeId().trim().length() > 0) {

			if (!selectedElement.getNodeId().equals(
					SessionUtil.getSessionData().getProjectId())) {

				NodeProperties selectedNodeProperty = desktopMainPanel
						.getjLeftPanel().getNodePropertiesMap()
						.get(selectedElement.getNodeId());

				if (selectedNodeProperty.getType().equals(
						NodePropertyConstants.DOCUMENT))
					isDocumentNode = true;
			}
		}

		return isDocumentNode;
	}

	public boolean isNodePropertyModified() {

		boolean isNodePropertyModified = false;

		CustomMutableTreeNode selectedElement = getSelectedTreeNode();

		if (selectedElement != null
				&& selectedElement.getNodeId() != null
				&& selectedElement.getNodeId().trim().length() > 0
				&& desktopMainPanel.getjLeftPanel().getNodePropertiesMap()
						.get(selectedElement.getNodeId()) != null) {

			NodeProperties nodeProperties = desktopMainPanel.getjLeftPanel()
					.getNodePropertiesMap().get(selectedElement.getNodeId());

			String barcode = barCodeFeild.getText().trim();
			String comment = commentFeild.getText().trim();
			String internalComment = intCommentFeild.getText().trim();
			String status = getValueFromMap(SessionUtil.getSessionData()
					.getStatusMap(), statusList.getSelectedValue().toString());

			if (!barcode.equals(nodeProperties.getBarcode())
					|| !comment.equals(nodeProperties.getComment())
					|| !internalComment.equals(nodeProperties
							.getInternalComment())
					|| !status.equals(nodeProperties.getStatus())) {

				isNodePropertyModified = true;
			}
		}

		return isNodePropertyModified;
	}

	private NodeProperties initPOJOToSaveNodeProperty(
			CustomMutableTreeNode newNode) {

		NodeProperties newNodePOJO = null;
		char transactionStatus = TransactionConstant.INSERT;

		HashMap<String, NodeProperties> nodePropertiesMap = desktopMainPanel
				.getjLeftPanel().getNodePropertiesMap();

		String barcode = barCodeFeild.getText().trim();

		if (newNode.getNodeId() != null
				&& newNode.getNodeId().trim().length() > 0
				&& nodePropertiesMap.get(newNode.getNodeId()) != null) {

			if (nodePropertiesMap.get(newNode.getNodeId())
					.getTransactionStatus() != TransactionConstant.INSERT) {
				// Condition to check That Saved Node Is Updated by Other User
				// Or Not
				Map<String, String> nodeUpdateTimeMap = new HashMap<String, String>();
				nodeUpdateTimeMap.put(nodePropertiesMap
						.get(newNode.getNodeId()).getNodeId(),
						nodePropertiesMap.get(newNode.getNodeId())
								.getLastUpdateDateTime());
				boolean isUpdated = controller()
						.checkIfNodeIsUpdatedByOtherUser(nodeUpdateTimeMap);
				if (isUpdated) {
					ErrorMessage.displayMessage('I',
							"updatedByOtherUserPleaseRefreshTree");

					// Added Code To Reset Field Value Of Selected Node
					// When User Change Some Property Of Node And Node is Updated by Other Users Then Selected Node Actual(old not Updated by Other User) Value Became Selected.
					desktopMainPanel.getjLeftPanel().controller().getAndSetNodePropertyValue();
					setTreeNodeUserObject(nodePropertiesMap.get(newNode.getNodeId()));
					
					return null;
				}

			}

			newNodePOJO = nodePropertiesMap.get(newNode.getNodeId());
			transactionStatus = TransactionConstant.UPDATE;

			if (newNodePOJO.getTransactionStatus() == TransactionConstant.UPDATE
					&& !barcode.equalsIgnoreCase(newNodePOJO.getBarcode())) {
				// Condition To Check If Node Is In Update Status And Barcode Is
				// Not Same As Current Node Barcode And Entered Barcode Already
				// Exists In DB
				String barcodeHierarchy = controller()
						.getHierarchyFromSearchBarcode(barcode);
				// String barcodeHierarchyForUpdatedNode =
				// desktopMainPanel.getjLeftPanel().getNodeTree().getSelectionPath().toString();
				log.info("Search Node Hierarchy : " + barcodeHierarchy);
				// barcodeHierarchyForUpdatedNode.equalsIgnoreCase(newNodePOJO.getHierarchy()
				if (!barcode.isEmpty()
						&& (barcodeHierarchy != null && barcodeHierarchy
								.length() > 0))
				// &&
				// !newNodePOJO.getType().equals(NodePropertyConstants.DOCUMENT)
				{
					log.error("Barcode Already Exists.");
					ErrorMessage.displayMessage('I', "barcodeAlreadyExists");
					barCodeFeild.requestFocus();
					return null;
				}
			}

		} else {
			newNodePOJO = new NodeProperties();
			// Condition To Check If Node Is In Insert Status (Or New Node) And
			// Barcode Is Already Exists In DB
			if (barcode.trim().length() > 0) {
				String hierarchy = controller().getHierarchyFromSearchBarcode(
						barcode);
				log.info("Search Node Hierarchy : " + hierarchy);
				if (hierarchy != null && hierarchy.length() > 0) {
					log.error("Barcode Already Exists.");
					ErrorMessage.displayMessage('I', "barcodeAlreadyExists");
					barCodeFeild.requestFocus();
					return null;
				}

			}
		}

		String projectId = SessionUtil.getSessionData().getProjectId();
		// String barcode = barCodeFeild.getText().trim();
		String comment = commentFeild.getText().trim();
		String internalComment = intCommentFeild.getText().trim();

		String selectedType = nodeTypeList.getSelectedValue().toString();
		Map<String, String> nodeTypeMap = SessionUtil.getSessionData()
				.getNodeTypeMap();
		String type = getValueFromMap(nodeTypeMap, selectedType);

		String name = barCodeFeild.getText().trim();

		if (type.equals(NodePropertyConstants.DOCUMENT)
				&& barCodeFeild.getText().trim() != null
				&& barCodeFeild.getText().trim().length() <= 0) {
			// Condition To Check Node Is not in Update Status
			// If New Document Node is insert Without Barcode Then Add New Node
			// With named Document + ChildCount
			if (transactionStatus != TransactionConstant.UPDATE) {
				name = ConstantUtil.getApplicationConstant("document") + " "
						+ newNode.getParent().getChildCount();
			}
			// Condition TO Check Node Is In Update Mode
			// If Document Node Is Updated and Has No Barcode Then Add Document
			// + childAtIndex
			// Child At Index Set As UserObject from FocusLost Method of
			// BarcodeField To Update Hierarchy
			else if (transactionStatus == TransactionConstant.UPDATE) {
				name = getSelectedTreeNode().getUserObject().toString();
			}
		}

		// Below Condition Is only for validation if node is box/container type
		// This Condition Never execute because we can not allow box/container
		// type without barcode
		if (name.trim().equals("") || name.trim().length() <= 0) {
			name = newNodePOJO.getName();
		}

		String selectedStatus = statusList.getSelectedValue().toString();
		Map<String, String> statusMap = SessionUtil.getSessionData()
				.getStatusMap();
		String status = getValueFromMap(statusMap, selectedStatus);

		String parentNodeId = ((CustomMutableTreeNode) newNode.getParent())
				.getNodeId();

		// Condition To Check If Barcode Is Not Entered For Document node Then
		// Make Hierarchy With Document + Child Count
		if ((barcode.trim().length() <= 0 && newNodePOJO.getNodeId() == null)) {
			newNode.setUserObject(ConstantUtil
					.getApplicationConstant("document")
					+ " "
					+ ((CustomMutableTreeNode) newNode.getParent())
							.getChildCount());
		}

		String hierarchy = desktopMainPanel.getjLeftPanel().getNodeTree()
				.getSelectionPath().toString();
		newNodePOJO.setProjectId(projectId);
		newNodePOJO.setName(name);
		newNodePOJO.setBarcode(barcode);
		newNodePOJO.setComment(comment);
		newNodePOJO.setInternalComment(internalComment);
		newNodePOJO.setType(type);
		newNodePOJO.setStatus(status);

		if (parentNodeId.equals(SessionUtil.getSessionData().getProjectId()))
			newNodePOJO.setParentNodeId(null);
		else
			newNodePOJO.setParentNodeId(parentNodeId);

		newNodePOJO.setHierarchy(hierarchy);
		// newNodePOJO.setThumbnailImageName(thumbnailImageName);
		// newNodePOJO.setActualImageName(actualImageName);
		newNodePOJO.setTransactionStatus(transactionStatus);
		newNodePOJO.setUserId(SessionUtil.getSessionData().getUserId());

		return newNodePOJO;
	}

	// Methods Which Returns the Key of Passed Value
	private String getValueFromMap(Map<String, String> map, String passedKey) {
		for (String key : map.keySet()) {
			if (key.equals(passedKey))
				return map.get(key);
		}
		return "";
	}

	private boolean isValidProperties() {

		boolean isValid = true;

		String barcode = barCodeFeild.getText().trim();
		Object selectedNodeType = nodeTypeList.getSelectedValue();
		Object selectedStatus = statusList.getSelectedValue();
		String comment = commentFeild.getText().trim();
		String internalComment = intCommentFeild.getText().trim();

		String selectedNodeId = getSelectedTreeNode().getNodeId();

		if (selectedNodeType == null
				|| selectedNodeType.toString().length() == 0) {

			ErrorMessage.displayMessage('I', "emptyNodeType");
			nodeTypeList.requestFocus();

			isValid = false;
		}

		else if (getValueFromMap(SessionUtil.getSessionData().getNodeTypeMap(),
				selectedNodeType.toString()).equals(
				NodePropertyConstants.DOCUMENT)
				&& (selectedNodeId == null || selectedNodeId.length() == 0)
				&& getSelectedTreeNode().getParent() == desktopMainPanel
						.getjLeftPanel().getProjectNode()) {

			ErrorMessage.displayMessage('I',
					"documentNotAllowedToBeChildOfRootNode");
			nodeTypeList.requestFocus();

			isValid = false;
		}

		else if (selectedStatus == null
				|| selectedStatus.toString().length() == 0) {

			ErrorMessage.displayMessage('I', "emptyStatus");
			statusList.requestFocus();

			isValid = false;
		}

		else if (selectedStatus.equals("Sealed")) {
			ErrorMessage.displayMessage('I',
					"notAllowedToAddNodeInSealedStatus");
			isValid = false;
		}

		else if (barcode == null || barcode.length() == 0) {

			String selectedNodeTypeValue = getValueFromMap(SessionUtil
					.getSessionData().getNodeTypeMap(),
					selectedNodeType.toString());

			if (!selectedNodeTypeValue.equals(NodePropertyConstants.DOCUMENT)) {
				ErrorMessage.displayMessage('I', "emptyBarcode");
				barCodeFeild.requestFocus();

				isValid = false;
			}
		}

		else if (comment.length() > 150) {

			ErrorMessage.displayMessage('I', "provide150Characters");
			commentFeild.requestFocus();

			isValid = false;
		}

		else if (internalComment.length() > 150) {

			ErrorMessage.displayMessage('I', "provide150Characters");
			intCommentFeild.requestFocus();

			isValid = false;
		}

		return isValid;
	}

	// This Method Also used In DesktopLeftJPanel (call when user selected Node
	// is Root Node)
	public void resetLeftPanelNodePropertyLabel() {
		desktopMainPanel.getjLeftPanel().getNameLabelValue().setText("");
		desktopMainPanel.getjLeftPanel().getBarCodeLabelValue().setText("");
		desktopMainPanel.getjLeftPanel().getCommnetLabelValue().setText("");
		desktopMainPanel.getjLeftPanel().getIntCommentLabelValue().setText("");
		desktopMainPanel.getjLeftPanel().getStatusLabelValue().setText("");
		desktopMainPanel.getjLeftPanel().getNodeTypeLabelValue().setText("");
	}

	// This Method Also used In DesktopLeftJPanel (call when user selected Node
	// is Root Node)
	public void resetNodeProperty() {
		barCodeFeild.setText("");
		commentFeild.setText("");
		intCommentFeild.setText("");
		statusList.setSelectedIndex(0);
		nodeTypeList.clearSelection();
	}

	@Override
	public void focusLost(FocusEvent e) {

		if (e.getSource() == barCodeFeild) {

			NodeProperties selectedNodeProperty = desktopMainPanel
					.getjLeftPanel().getNodePropertiesMap()
					.get(getSelectedTreeNode().getNodeId());

			desktopMainPanel.getjLeftPanel().getBarCodeLabelValue()
					.setText(barCodeFeild.getText().trim());
			desktopMainPanel.getjLeftPanel().getBarCodeLabelValue()
					.setToolTipText(barCodeFeild.getText().trim());

			setTreeNodeUserObject(selectedNodeProperty);
			
			if (barCodeFeild.getText().trim().length() > 0) {
				String hierarchy = desktopMainPanel.getjLeftPanel()
						.getNodeTree().getSelectionPath().toString();
				getSelectedTreeNode().setUserObject(barCodeFeild.getText());
				desktopMainPanel.getjLeftPanel().getNodeTree().updateUI();
			} else if (selectedNodeProperty != null
					&& selectedNodeProperty.getType().equalsIgnoreCase(
							NodePropertyConstants.DOCUMENT)
					&& barCodeFeild.getText().trim().length() <= 0) {
				int childIndex = desktopMainPanel
						.getjLeftPanel()
						.getNodeTree()
						.getModel()
						.getIndexOfChild(getSelectedTreeNode().getParent(),
								getSelectedTreeNode());
				String hierarchy = desktopMainPanel.getjLeftPanel()
						.getNodeTree().getSelectionPath().toString();
				getSelectedTreeNode().setUserObject(
						ConstantUtil.getApplicationConstant("dumyDocumentNode")
								+ " " + (childIndex + 1));
				desktopMainPanel.getjLeftPanel().getNodeTree().updateUI();
			}

		}

		if (e.getSource() == commentFeild) {

			String comment = commentFeild.getText().trim();

			desktopMainPanel.getjLeftPanel().getCommnetLabelValue()
					.setText(comment);
			desktopMainPanel.getjLeftPanel().getCommnetLabelValue()
					.setToolTipText(comment);
		}

		if (e.getSource() == intCommentFeild) {

			String internalComment = intCommentFeild.getText().trim();

			desktopMainPanel.getjLeftPanel().getIntCommentLabelValue()
					.setText(internalComment);
			desktopMainPanel.getjLeftPanel().getIntCommentLabelValue()
					.setToolTipText(internalComment);
		}
	}

	/*
	 * @Override public void itemStateChanged(ItemEvent e) { if (e.getSource()
	 * == statusList) { log.info("Change Property" +
	 * statusList.getSelectedValue()); log.info("Index" +
	 * statusList.getSelectedIndex()); String selectedStatus =
	 * statusList.getSelectedValue().toString(); int selectedStatusIndex =
	 * statusList.getSelectedIndex();
	 * desktopMainPanel.getjLeftPanel().getStatusLabelValue()
	 * .setText(selectedStatus); } }
	 */

	
	private void setTreeNodeUserObject(NodeProperties selectedNodeProperty)  
	{
		if (barCodeFeild.getText().trim().length() > 0) 
		{
			String hierarchy = desktopMainPanel.getjLeftPanel().getNodeTree().getSelectionPath().toString();
			getSelectedTreeNode().setUserObject(barCodeFeild.getText());
			desktopMainPanel.getjLeftPanel().getNodeTree().updateUI();
		} 
		else if (selectedNodeProperty != null && selectedNodeProperty.getType().equalsIgnoreCase(NodePropertyConstants.DOCUMENT) && barCodeFeild.getText().trim().length() <= 0) 
		{
			int childIndex = desktopMainPanel
					.getjLeftPanel()
					.getNodeTree()
					.getModel()
					.getIndexOfChild(getSelectedTreeNode().getParent(),
							getSelectedTreeNode());
			
			getSelectedTreeNode().setUserObject(ConstantUtil.getApplicationConstant("dumyDocumentNode") + " " + (childIndex + 1));
			desktopMainPanel.getjLeftPanel().getNodeTree().updateUI();
		}	
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == nodeTypeList) {
			log.info("Change Type Value : " + nodeTypeList.getSelectedValue());
			log.info("Type Index : " + nodeTypeList.getSelectedIndex());

			if (nodeTypeList.getSelectedValue() != null) {

				String selectedType = nodeTypeList.getSelectedValue()
						.toString();
				if (!getValueFromMap(
						SessionUtil.getSessionData().getNodeTypeMap(),
						selectedType).equals(NodePropertyConstants.DOCUMENT))
					barCodeLabel.setText(ConstantUtil
							.getApplicationConstant("barcode") + "*");
				else
					barCodeLabel.setText(ConstantUtil
							.getApplicationConstant("barcode"));
				int selectedTypeIndex = nodeTypeList.getSelectedIndex();
				desktopMainPanel.getjLeftPanel().getNodeTypeLabelValue()
						.setText(selectedType);
			}
		}
		if (e.getSource() == statusList) {
			log.info("Change Status Value : " + statusList.getSelectedValue());
			log.info("Status Index : " + statusList.getSelectedIndex());

			if (statusList.getSelectedValue() != null) {

				String selectedStatus = statusList.getSelectedValue()
						.toString();
				int selectedStatusIndex = statusList.getSelectedIndex();
				desktopMainPanel.getjLeftPanel().getStatusLabelValue()
						.setText(selectedStatus);
			}
		}
	}

	/**
	 * Method to check if the status of existing node is changed to In Progress.
	 * 
	 * @param selectedNode
	 * @param selectedStatusInListBox
	 * @return
	 */
	public boolean hasStatusChangeToInProgress(
			CustomMutableTreeNode selectedNode, String selectedStatusInListBox) {

		// If updated node is new node and status is set to in progress, it is
		// default status and hence not changed...
		if (selectedNode.getNodeId() == null
				|| selectedNode.getNodeId().length() == 0) {

			return false;
		}

		String selectedStatus = getValueFromMap(SessionUtil.getSessionData()
				.getStatusMap(), selectedStatusInListBox);

		// If any existing node has status change...
		NodeProperties nodeProperties = desktopMainPanel.getjLeftPanel()
				.getNodePropertiesMap().get(selectedNode.getNodeId());

		if (nodeProperties != null
				&& !nodeProperties.getStatus().equals(selectedStatus)
				&& selectedStatus.equals(NodePropertyConstants.IN_PROGRESS))
			return true;
		else
			return false;
	}

	/**
	 * Method to check make the list of parent nodes to mark it to In Progress
	 * status when any child node is set to In Progress.
	 * 
	 * @param updatedNode
	 * @param selectedStatusInListBox
	 */
	public void markParentNodesInProgress(CustomMutableTreeNode updatedNode,
			String selectedStatusInListBox) {

		CustomMutableTreeNode presentNode = updatedNode;

		while ((CustomMutableTreeNode) presentNode.getParent() != null
				&& (CustomMutableTreeNode) presentNode.getParent() != desktopMainPanel
						.getjLeftPanel().getProjectNode()) {

			presentNode = (CustomMutableTreeNode) presentNode.getParent();

			nodesToMarkInProgress.add(presentNode);
		}
	}

	/**
	 * Method to update the nodes to In Progress status.
	 */
	public void updateNodesToInProgressState() {

		if (nodesToMarkInProgress.size() > 0) {

			ArrayList<NodeProperties> nodePropertiesList = new ArrayList<NodeProperties>();

			for (int index = 0; index < nodesToMarkInProgress.size(); index++) {

				NodeProperties nodeProperties = desktopMainPanel
						.getjLeftPanel().getNodePropertiesMap()
						.get(nodesToMarkInProgress.get(index).getNodeId());

				nodeProperties.setStatus(NodePropertyConstants.IN_PROGRESS);
				nodeProperties.setUserId(SessionUtil.getSessionData()
						.getUserId());
				nodeProperties.setTransactionStatus(TransactionConstant.UPDATE);

				nodePropertiesList.add(nodeProperties);
			}

			nodePropertiesList = controller().saveNodePropertiesList(
					nodePropertiesList);

			if (nodePropertiesList != null && nodePropertiesList.size() > 0) {

				for (int index = 0; index < nodePropertiesList.size(); index++) {

					NodeProperties nodeProperties = nodePropertiesList
							.get(index);

					desktopMainPanel.getjLeftPanel().getNodePropertiesMap()
							.put(nodeProperties.getNodeId(), nodeProperties);
				}
			}
		}
	}

	/**
	 * Method to check if the status is changed to Done.
	 * 
	 * @param selectedNode
	 * @param selectedStatusInListBox
	 * @return
	 */
	public boolean hasStatusChangeToDone(CustomMutableTreeNode selectedNode,
			String selectedStatusInListBox) {

		String selectedStatus = getValueFromMap(SessionUtil.getSessionData()
				.getStatusMap(), selectedStatusInListBox);

		// If updated node is new node and status is set to done...
		if (selectedNode.getNodeId() == null
				|| selectedNode.getNodeId().length() == 0) {

			if (selectedStatus.equals(NodePropertyConstants.DONE))
				return true;
			else
				return false;
		}

		// If any existing node has status change...
		NodeProperties nodeProperties = desktopMainPanel.getjLeftPanel()
				.getNodePropertiesMap().get(selectedNode.getNodeId());

		if (nodeProperties != null
				&& !nodeProperties.getStatus().equals(selectedStatus)
				&& selectedStatus.equals(NodePropertyConstants.DONE))
			return true;
		else
			return false;
	}

	/**
	 * Method to check if the node can be marked to Done status.
	 * 
	 * @param updatedNode
	 * @param selectedStatusInListBox
	 * @param isForwarTraversalAllowed
	 * @return
	 */
	public boolean canBeMarkedDone(CustomMutableTreeNode updatedNode,
			String selectedStatusInListBox, boolean isForwarTraversalAllowed) {

		// When "updatedNode" == New Node (selected) and status is selected to
		// Done, check if node type is document...
		if (updatedNode.getNodeId() == null
				|| updatedNode.getNodeId().length() == 0) {

			if (getValueFromMap(SessionUtil.getSessionData().getNodeTypeMap(),
					nodeTypeList.getSelectedValue().toString()).equals(
					NodePropertyConstants.DOCUMENT)) {

				log.info("Can Be Done Node : "
						+ new TreePath(updatedNode.getPath()));
				return true;

			} else
				return false;
		}

		// When "updatedNode" == Selected Node (but not a New Node) and status
		// is selected to Done, check if node type is document...
		if (updatedNode.getChildCount() == 0
				&& updatedNode == getSelectedTreeNode()) {

			if (getValueFromMap(SessionUtil.getSessionData().getNodeTypeMap(),
					nodeTypeList.getSelectedValue().toString()).equals(
					NodePropertyConstants.DOCUMENT)) {

				log.info("Can Be Done Node : "
						+ new TreePath(updatedNode.getPath()));
				return true;

			} else
				return false;
		}

		// If the provided "updatedNode" has child nodes....
		if (updatedNode.getChildCount() > 0) {

			boolean status = true;

			// When Child nodes of provided "updatedNode" is not required to be
			// scanned for status change. Here status of only 1st level child
			// nodes of "updatedNode" will be checked...
			if (!isForwarTraversalAllowed) {

				for (int index = 0; index < updatedNode.getChildCount(); index++) {

					// If child node is selected node, check updated status from
					// screen.
					if ((CustomMutableTreeNode) updatedNode.getChildAt(index) == getSelectedTreeNode()) {

						String selectedStatus = getValueFromMap(SessionUtil
								.getSessionData().getStatusMap(),
								selectedStatusInListBox);

						status = status
								& selectedStatus
										.equals(NodePropertyConstants.DONE);

					} else {
						// No need to check property if child node is going to
						// be marked as Done.
						if (nodesToMarkDone
								.contains((CustomMutableTreeNode) updatedNode
										.getChildAt(index))) {
							status = status & true;

						} else {
							NodeProperties childNodeProperty = desktopMainPanel
									.getjLeftPanel()
									.getNodePropertiesMap()
									.get(((CustomMutableTreeNode) updatedNode
											.getChildAt(index)).getNodeId());

							status = status
									& childNodeProperty.getStatus().equals(
											NodePropertyConstants.DONE);
						}
					}

					if (status == false)
						break;
				}
			}

			// When Child nodes of provided "updatedNode" is also to be scanned
			// for status change.
			else {
				status = canBeMarkedDone(
						(CustomMutableTreeNode) updatedNode.getChildAt(0),
						selectedStatusInListBox, true);

				for (int index = 1; index < updatedNode.getChildCount(); index++) {

					status = status
							& canBeMarkedDone(
									(CustomMutableTreeNode) updatedNode
											.getChildAt(index),
									selectedStatusInListBox, true);

					if (status == false)
						break;
				}
			}

			if (status == true) {

				if (updatedNode != getSelectedTreeNode())
					nodesToMarkDone.add(updatedNode);

				log.info("Can Be Done Node : "
						+ new TreePath(updatedNode.getPath()));
			}

			return status;
		}

		// If provided "updatedNode" has no child nodes...
		else {

			NodeProperties nodeProperties = desktopMainPanel.getjLeftPanel()
					.getNodePropertiesMap().get(updatedNode.getNodeId());

			if (nodeProperties.getType().equals(NodePropertyConstants.DOCUMENT)) {

				if (updatedNode != getSelectedTreeNode())
					nodesToMarkDone.add(updatedNode);

				log.info("Can Be Done Node : "
						+ new TreePath(updatedNode.getPath()));

				return true;

			} else {
				return false;
			}
		}
	}

	/**
	 * Method to check if the parent nodes of provided updated nodes can be
	 * marked to done.
	 * 
	 * @param updatedNode
	 * @param selectedStatusInListBox
	 */
	public void markParentNodesDone(CustomMutableTreeNode updatedNode,
			String selectedStatusInListBox) {

		CustomMutableTreeNode presentNode = updatedNode;

		while ((CustomMutableTreeNode) presentNode.getParent().getParent() != null
				&& (CustomMutableTreeNode) presentNode.getParent().getParent() != desktopMainPanel
						.getjLeftPanel().getProjectNode()) {

			presentNode = (CustomMutableTreeNode) presentNode.getParent();

			boolean canBeMarkedDone = canBeMarkedDone(presentNode,
					selectedStatusInListBox, false);

			if (canBeMarkedDone == false)
				break;
		}
	}

	/**
	 * Method to update the nodes in Done status.
	 */
	public void updateNodesToDoneState() {

		if (nodesToMarkDone.size() > 0) {

			ArrayList<NodeProperties> nodePropertiesList = new ArrayList<NodeProperties>();

			for (int index = 0; index < nodesToMarkDone.size(); index++) {

				NodeProperties nodeProperties = desktopMainPanel
						.getjLeftPanel().getNodePropertiesMap()
						.get(nodesToMarkDone.get(index).getNodeId());

				nodeProperties.setStatus(NodePropertyConstants.DONE);
				nodeProperties.setUserId(SessionUtil.getSessionData()
						.getUserId());
				nodeProperties.setTransactionStatus(TransactionConstant.UPDATE);
				nodePropertiesList.add(nodeProperties);
			}

			nodePropertiesList = controller().saveNodePropertiesList(
					nodePropertiesList);

			if (nodePropertiesList != null && nodePropertiesList.size() > 0) {

				for (int index = 0; index < nodePropertiesList.size(); index++) {

					NodeProperties nodeProperties = nodePropertiesList
							.get(index);

					desktopMainPanel.getjLeftPanel().getNodePropertiesMap()
							.put(nodeProperties.getNodeId(), nodeProperties);
				}
			}
		}
	}

	public CollectionPanelController controller() {
		return (CollectionPanelController) getController();
	}

	public void setAllButtonEnableDisable(boolean enable) {
		addNodeButton.setEnabled(enable);
		saveButton.setEnabled(enable);
		deleteButton.setEnabled(enable);
		cutButton.setEnabled(enable);
		pasteButton.setEnabled(enable);
		cancelButton.setEnabled(enable);
	}

	public void nodeAllPropertyEnableDisable(boolean enabled) {
		barCodeFeild.setEnabled(enabled);
		commentFeild.setEnabled(enabled);
		intCommentFeild.setEnabled(enabled);
		statusList.setEnabled(enabled);
		nodeTypeList.setEnabled(enabled);
	}

	public void setDoneStatusNodePropertyEnableDisable(boolean enabled) {
		barCodeFeild.setEnabled(enabled);
		commentFeild.setEnabled(enabled);
		intCommentFeild.setEnabled(enabled);
		statusList.setEnabled(true);
		saveButton.setEnabled(true);
	}

	private List<NodeProperties> fetchChildNodes(
			CustomMutableTreeNode selectedNode) {

		ArrayList<String> idList = new ArrayList<String>();
		String projectId = SessionUtil.getSessionData().getProjectId();
		String parentNodeId = selectedNode.getNodeId();

		idList.add(projectId);
		idList.add(parentNodeId);

		List<NodeProperties> childNodePropertyList = controller()
				.fetchChildNodeList(idList);

		return childNodePropertyList;

	}

	@Override
	public void focusGained(FocusEvent e) {

	}

	public DesktopMainJPanel getDesktopMainPanel() {
		return desktopMainPanel;
	}

	public void setDesktopMainPanel(DesktopMainJPanel desktopMainPanel) {
		this.desktopMainPanel = desktopMainPanel;
	}

	public EdittedTextField getBarCodeFeild() {
		return barCodeFeild;
	}

	public void setBarCodeFeild(EdittedTextField barCodeFeild) {
		this.barCodeFeild = barCodeFeild;
	}

	public JTextArea getCommentFeild() {
		return commentFeild;
	}

	public void setCommentFeild(JTextArea commentFeild) {
		this.commentFeild = commentFeild;
	}

	public JTextArea getIntCommentFeild() {
		return intCommentFeild;
	}

	public void setIntCommentFeild(JTextArea intCommentFeild) {
		this.intCommentFeild = intCommentFeild;
	}

	/**
	 * @return the statusList
	 */
	public JList getStatusList() {
		return statusList;
	}

	/**
	 * @param statusList
	 *            the statusList to set
	 */
	public void setStatusList(JList statusList) {
		this.statusList = statusList;
	}

	public JList getNodeTypeList() {
		return nodeTypeList;
	}

	public void setNodeTypeList(JList nodeTypeList) {
		this.nodeTypeList = nodeTypeList;
	}

	public CustomMutableTreeNode getNewAddedNode() {
		return newAddedNode;
	}

	public void setNewAddedNode(CustomMutableTreeNode newAddedNode) {
		this.newAddedNode = newAddedNode;
	}

	public ArrayList<CustomMutableTreeNode> getNodesToMarkDone() {
		return nodesToMarkDone;
	}

	public void setNodesToMarkDone(
			ArrayList<CustomMutableTreeNode> nodesToMarkDone) {
		this.nodesToMarkDone = nodesToMarkDone;
	}

	public CustomMutableTreeNode[] getNodesToCut() {
		return nodesToCut;
	}

	public void setNodesToCut(CustomMutableTreeNode[] nodesToCut) {
		this.nodesToCut = nodesToCut;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(JButton deleteButton) {
		this.deleteButton = deleteButton;
	}

	/**
	 * @return the addNodeButton
	 */
	public JButton getAddNodeButton() {
		return addNodeButton;
	}

	/**
	 * @param addNodeButton
	 *            the addNodeButton to set
	 */
	public void setAddNodeButton(JButton addNodeButton) {
		this.addNodeButton = addNodeButton;
	}

	/**
	 * @return the cutButton
	 */
	public JButton getCutButton() {
		return cutButton;
	}

	/**
	 * @param cutButton
	 *            the cutButton to set
	 */
	public void setCutButton(JButton cutButton) {
		this.cutButton = cutButton;
	}

	/**
	 * @return the pasteButton
	 */
	public JButton getPasteButton() {
		return pasteButton;
	}

	/**
	 * @param pasteButton
	 *            the pasteButton to set
	 */
	public void setPasteButton(JButton pasteButton) {
		this.pasteButton = pasteButton;
	}

	/**
	 * @return the cancelButton
	 */
	public JButton getCancelButton() {
		return cancelButton;
	}

	/**
	 * @param cancelButton
	 *            the cancelButton to set
	 */
	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	/**
	 * @return the nodesToMarkInProgress
	 */
	public ArrayList<CustomMutableTreeNode> getNodesToMarkInProgress() {
		return nodesToMarkInProgress;
	}

	/**
	 * @param nodesToMarkInProgress
	 *            the nodesToMarkInProgress to set
	 */
	public void setNodesToMarkInProgress(
			ArrayList<CustomMutableTreeNode> nodesToMarkInProgress) {
		this.nodesToMarkInProgress = nodesToMarkInProgress;
	}
}

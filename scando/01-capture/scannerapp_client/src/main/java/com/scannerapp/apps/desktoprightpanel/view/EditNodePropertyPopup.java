package com.scannerapp.apps.desktoprightpanel.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;

import com.scannerapp.apps.desktopleftpanel.view.CustomMutableTreeNode;
import com.scannerapp.apps.desktopmainpanel.view.DesktopMainJPanel;
import com.scannerapp.apps.framework.view.ErrorMessage;
import com.scannerapp.apps.utils.ConstantUtil;
import com.scannerapp.apps.utils.SessionUtil;
import com.scannerapp.common.NodePropertyConstants;
import com.scannerapp.shared.NodeProperties;
import com.scannerapp.shared.TransactionConstant;

@SuppressWarnings("serial")
public class EditNodePropertyPopup extends JDialog implements ActionListener {
	private JPanel internalCommentRowPannel;

	private JLabel heading;

	private JLabel intCommentLabel;
	private JTextArea intCommentFeild;
	private JScrollPane intCommentScroll;

	private JLabel statusLabel;
	DefaultComboBoxModel model;
	private JComboBox statusCombo;

	private JButton updateNodePropertyButton;
	private DesktopMainJPanel desktopMainPanel;
	Map<String, String> statusMap;

	private static Logger log = Logger.getLogger(CollectionPanel.class);// For
																		// Log4j

	public EditNodePropertyPopup(DesktopMainJPanel desktopMainPanel) {
		this.desktopMainPanel = desktopMainPanel;
		initEditPopup();
	}

	private void initEditPopup() {

		internalCommentRowPannel = new JPanel();
		internalCommentRowPannel.setLayout(new GridBagLayout());

		heading = new JLabel();
		heading.setText(ConstantUtil
				.getApplicationConstant("editNodePropertyHeading"));
		heading.setFont(new Font(internalCommentRowPannel.getFont().getName(),
				Font.BOLD, 20));

		intCommentLabel = new JLabel();
		intCommentLabel.setText(ConstantUtil
				.getApplicationConstant("internalComment"));

		intCommentFeild = new JTextArea();
		intCommentScroll = new JScrollPane(intCommentFeild);
		intCommentScroll.setPreferredSize(new Dimension(500, 90));
		intCommentScroll.setMinimumSize(new Dimension(500, 90));

		statusLabel = new JLabel();
		statusLabel.setText(ConstantUtil.getApplicationConstant("status"));
		statusMap = SessionUtil.getSessionData().getStatusMap();
		List<String> statusList = new ArrayList<String>(statusMap.keySet());
		model = new DefaultComboBoxModel(statusList.toArray());
		statusCombo = new JComboBox(model);

		statusCombo.setPreferredSize(new Dimension(130, 25));
		statusCombo.setMinimumSize(new Dimension(130, 25));

		updateNodePropertyButton = new JButton();
		updateNodePropertyButton.setText(ConstantUtil
				.getApplicationConstant("update"));
		updateNodePropertyButton.addActionListener((ActionListener) this);

		CustomMutableTreeNode selectedNode = desktopMainPanel.getjRightPanel()
				.getCollectionPanel().getSelectedTreeNode();
		NodeProperties selectedNodeProperties = desktopMainPanel
				.getjLeftPanel().getNodePropertiesMap()
				.get(selectedNode.getNodeId());
		// Condition TO Check Node Is InProgress Status
		// Only Allow To Edit Node Comment If It Is In InProgress Status
		if (selectedNodeProperties.getStatus().equals(
				NodePropertyConstants.IN_PROGRESS)) {
			internalCommentRowPannel.add(intCommentLabel,
					new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 0, 0), 0, 0));
			internalCommentRowPannel.add(intCommentLabel,
					new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 0, 0), 0, 0));
			internalCommentRowPannel.add(intCommentScroll,
					new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 10, 0, 0), 0, 0));
		}

		CustomMutableTreeNode newNode = desktopMainPanel.getjRightPanel()
				.getCollectionPanel().getSelectedTreeNode();

		if (newNode == null)
			return;

		NodeProperties nodeProperty = desktopMainPanel.getjLeftPanel()
				.getNodePropertiesMap().get(newNode.getNodeId());

		log.info("Node Property Id: " + newNode.getNodeId());

		if (!newNode.getNodeId().equals(
				SessionUtil.getSessionData().getProjectId())) {

			//String internalComment = nodeProperty.getInternalComment();
			//String nodeStatus = nodeProperty.getStatus();

			//log.info("Int Comment: " + internalComment);
			//log.info("Status: " + nodeStatus);

			setnodePropertyValue(nodeProperty);
			
			/*String status = getKeyFromMap(statusMap, nodeProperty.getStatus());
			intCommentFeild.setText(internalComment);
			statusCombo.setSelectedItem(status);*/

			this.setTitle(ConstantUtil
					.getApplicationConstant("editNodePropertyHeading"));
			this.setSize(700, 300);

			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			// Determine the new location of the window
			int w = this.getSize().width;
			int h = this.getSize().height;
			int x = (dim.width - w) / 2;
			int y = (dim.height - h) / 2;
			// Move the window
			this.setLocation(x, y);
			this.setVisible(true);

			this.show();
		}

		this.setLayout(new GridBagLayout());

		// this.setTitle(ConstantUtil.getApplicationConstant("editNodeProperty"));

		String hasImportRights = SessionUtil.getSessionData().getImportRight();
		String hasSeparationRights = SessionUtil.getSessionData()
				.getSeparationRight();

		/*
		 * this.add(intCommentLabel, new GridBagConstraints(0, 0, 1, 1, 0.0,0.0,
		 * GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0,
		 * 0, 0), 0, 0));// east none
		 */
		/*
		 * this.add(intCommentScroll, new GridBagConstraints(1, 0, 1, 1,
		 * 0.0,0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new
		 * Insets(0, 0, 0, 0), 0, 0));// east none
		 */

		// Condition TO Check User Has Separation Rights
		if (("Y").equalsIgnoreCase(hasSeparationRights)) {
			// Condition To Check The Node Is Document Node Then Only Allow To
			// Edit Status
			if (nodeProperty.getType().equalsIgnoreCase(
					NodePropertyConstants.DOCUMENT)) {
				internalCommentRowPannel.add(statusLabel,
						new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
								GridBagConstraints.CENTER,
								GridBagConstraints.BOTH,
								new Insets(10, 0, 0, 0), 0, 0));
				internalCommentRowPannel.add(statusCombo,
						new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
								GridBagConstraints.WEST,
								GridBagConstraints.NONE, new Insets(10, 10, 0,
										0), 0, 0));
			}
			/*
			 * this.add(statusLabel, new GridBagConstraints(0, 1, 1, 1, 0.0,
			 * 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new
			 * Insets(0, 0, 0, 0), 0, 0));// east none this.add(statusCombo, new
			 * GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
			 * GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,
			 * 0, 0, 0), 0, 0));// east none
			 */
		}

		this.add(heading, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));

		this.add(new JSeparator(), new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						5, 0, 0, 0), 0, 0));

		this.add(internalCommentRowPannel, new GridBagConstraints(0, 2, 1, 1,
				1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));

		this.add(updateNodePropertyButton, new GridBagConstraints(0, 3, 1, 1,
				0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(0, 0, 10, 0), 0, 0));// east none
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		log.info("Update Property Click");
		if (e.getSource() == updateNodePropertyButton) {

			CustomMutableTreeNode newNode = desktopMainPanel.getjRightPanel()
					.getCollectionPanel().getSelectedTreeNode();
			if (newNode == null)
				return;

			if (isValidProperties()) {

				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.getNodesToMarkDone().clear();
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.getNodesToMarkInProgress().clear();

				String selectedStatusInListBox = statusCombo.getSelectedItem()
						.toString();

				if (selectedStatusInListBox.equalsIgnoreCase("DONE")) {
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.setCutNodePropertyList(null);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.setCutNodeIndex(null);
				}

				// Code to Check Concurrency
				// Condition To Check before Update Document Property,
				// Is The Node is going to updated is Previouly
				// Updated By Other Users Or Not.

				NodeProperties selectedNodeProperty = desktopMainPanel
						.getjLeftPanel().getNodePropertiesMap()
						.get(newNode.getNodeId());

				Map<String, String> nodeUpdateTimeMap = new HashMap<String, String>();
				nodeUpdateTimeMap.put(selectedNodeProperty.getNodeId(),
						selectedNodeProperty.getLastUpdateDateTime());

				if (isNodeUpdatedByOtherUser(nodeUpdateTimeMap)) {
					
					setnodePropertyValue(selectedNodeProperty);
					
					this.hide();
					
					ErrorMessage.displayMessage('I',
							"updatedByOtherUserPleaseRefreshTree");

					return;
				}

				// To perform the cascade update to done status.
				if (desktopMainPanel
						.getjRightPanel()
						.getCollectionPanel()
						.hasStatusChangeToDone(newNode, selectedStatusInListBox)) {
					boolean allowedToProceed = desktopMainPanel
							.getjRightPanel()
							.getCollectionPanel()
							.canBeMarkedDone(newNode, selectedStatusInListBox,
									true);

					if (allowedToProceed) {
						desktopMainPanel
								.getjRightPanel()
								.getCollectionPanel()
								.markParentNodesDone(newNode,
										selectedStatusInListBox);
					} else {
						ErrorMessage.displayMessage('I',
								"nodeCanNotBeMarkedDone");
						return;
					}
				}

				// To perform the cascade update to in progress status.
				if (desktopMainPanel
						.getjRightPanel()
						.getCollectionPanel()
						.hasStatusChangeToInProgress(newNode,
								selectedStatusInListBox)) {

					desktopMainPanel
							.getjRightPanel()
							.getCollectionPanel()
							.markParentNodesInProgress(newNode,
									selectedStatusInListBox);
				}

				NodeProperties updateNodePOJO = initPOJOToUpdateNodeProperty(selectedNodeProperty);
				NodeProperties savedNewNode = desktopMainPanel.getjRightPanel()
						.getCollectionPanel().controller()
						.saveNodeButton_actionPerformed(updateNodePOJO);

				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.getNodeTypeList().setEnabled(false);

				// Below Code is to Set Left Panel Labels
				desktopMainPanel.getjLeftPanel().getIntCommentLabelValue()
						.setText(savedNewNode.getInternalComment());
				desktopMainPanel
						.getjLeftPanel()
						.getStatusLabelValue()
						.setText(
								getValueFromMap(statusMap,
										savedNewNode.getStatus()));

				// Below Code to Set the Right Panel Field
				// Because on JTree Value Change Event we check that the right
				// Side Panel Fields Value is Changed or Not
				// If Not Set Value To Right Side It Will Throw Error Message
				// DesktopLeftJPanel -> valueChanged ->
				// performCheckBeforeSelectingNode -> "saveOrDeleteUpdatedNode"

				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.getIntCommentFeild()
						.setText(savedNewNode.getInternalComment());
				Map<String, String> statusMap = SessionUtil.getSessionData()
						.getStatusMap();
				String status = getKeyFromMap(statusMap,
						savedNewNode.getStatus());
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.getStatusList().setSelectedValue(status, true);

				desktopMainPanel.getjLeftPanel().getNodePropertiesMap()
						.put(savedNewNode.getNodeId(), savedNewNode);

				// update nodes to done state.
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.updateNodesToDoneState();

				// update nodes to in progress state.
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.updateNodesToInProgressState();

				desktopMainPanel.getjLeftPanel()
						.enableDisableImportSeparationButtons(savedNewNode);

				// Marking node to null because when node is saved, it is not
				// newly added.
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.setNewAddedNode(null);
				this.hide();
			}
		}
	}

	private void setnodePropertyValue(NodeProperties selectedNodeProperty) 
	{
		this.intCommentFeild.setText(selectedNodeProperty.getInternalComment());

		String status = getKeyFromMap(statusMap, selectedNodeProperty.getStatus());
		this.statusCombo.setSelectedItem(status);
		
	}

	private boolean isNodeUpdatedByOtherUser(
			Map<String, String> nodeUpdateTimeMap) {
		return desktopMainPanel.getjRightPanel().getImportAndSepPanel()
				.controller()
				.checkIfNodeIsUpdatedByOtherUser(nodeUpdateTimeMap);
	}

	private NodeProperties initPOJOToUpdateNodeProperty(
			NodeProperties selectedNodeProperty) {

		NodeProperties newNodePOJO = selectedNodeProperty;
		;

		/*
		 * HashMap<String, NodeProperties> nodePropertiesMap = desktopMainPanel
		 * .getjLeftPanel().getNodePropertiesMap(); newNodePOJO =
		 * nodePropertiesMap.get(newNode.getNodeId());
		 */

		String internalComment = intCommentFeild.getText().trim();
		String selectedStatus = statusCombo.getSelectedItem().toString();
		Map<String, String> statusMap = SessionUtil.getSessionData()
				.getStatusMap();
		String status = getValueFromMap(statusMap, selectedStatus);

		newNodePOJO.setUserId(SessionUtil.getSessionData().getUserId());
		newNodePOJO.setInternalComment(internalComment);
		newNodePOJO.setStatus(status);
		newNodePOJO.setTransactionStatus(TransactionConstant.UPDATE);

		return newNodePOJO;
	}

	private boolean isValidProperties() {
		boolean isValid = true;
		String internalComment = intCommentFeild.getText().trim();
		String selectedStatus = statusCombo.getSelectedItem().toString();

		if (internalComment.length() > 150) {

			ErrorMessage.displayMessage('I', "provide150Characters");
			intCommentFeild.requestFocus();

			isValid = false;
		}
		if (selectedStatus.equalsIgnoreCase("sealed")) {
			ErrorMessage.displayMessage('I',
					"notAllowedToUpdateNodeInSealedStatus");
			isValid = false;
		}

		return isValid;
	}

	// Methods Which Returns the Key of Passed Value
	private String getValueFromMap(Map<String, String> map, String passedKey) {
		for (String key : map.keySet()) {
			if (key.equals(passedKey))
				return map.get(key);
		}
		return "";
	}

	// Methods Which Returns the Value of Passed Key
	private String getKeyFromMap(Map<String, String> map, String passedKey) {
		for (String key : map.keySet()) {
			if (map.get(key).equals(passedKey))
				return key;
		}
		return "";
	}

}
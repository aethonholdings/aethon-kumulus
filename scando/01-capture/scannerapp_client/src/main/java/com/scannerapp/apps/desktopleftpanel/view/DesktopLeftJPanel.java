package com.scannerapp.apps.desktopleftpanel.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.apache.log4j.Logger;

import com.scannerapp.apps.desktopmainpanel.view.DesktopMainJPanel;
import com.scannerapp.apps.framework.view.BaseJPanel;
import com.scannerapp.apps.framework.view.ErrorMessage;
import com.scannerapp.apps.utils.ConstantUtil;
import com.scannerapp.apps.utils.SessionUtil;
import com.scannerapp.common.NodePropertyConstants;
import com.scannerapp.resources.IconRepository;
import com.scannerapp.shared.NodeProperties;

public class DesktopLeftJPanel extends BaseJPanel implements IconRepository,
		FocusListener, java.awt.event.ActionListener, TreeSelectionListener {// ,TreeCellRenderer

	private static Logger log = Logger.getLogger(DesktopLeftHelper.class);// For
																			// Log4j

	protected boolean logout = false;
	protected boolean flag = true;

	// protected Hashtable hBundle = null;
	private JPanel mainPanel = new JPanel();
	private JPanel jLeftPanel = new JPanel();
	private JPanel jButtonPanel = new JPanel();
	// private JPanel jLabelPanel = new JPanel();
	// private JPanel jTreeScrollPanel = new JPanel();
	private JPanel jTreePanel = new JPanel();
	private JPanel jLabelDetailPanel = new JPanel();
	private JButton jbtnRefresh = new JButton();

	// private JButton jbtnCut = new JButton();
	// private JButton jbtnPaste = new JButton();

	private JLabel kpiLabel = new JLabel();
	private JLabel actualLabel = new JLabel();
	private JLabel fiveTLabel = new JLabel();
	private JLabel targetLabel = new JLabel();
	private JLabel tenTLabel = new JLabel();
	private JLabel nameLabel = new JLabel();
	private JLabel barCodeLabel = new JLabel();
	private JLabel commnetLabel = new JLabel();
	private JLabel intCommentLabel = new JLabel();
	private JLabel statusLabel = new JLabel();
	private JLabel nodeTypeLabel = new JLabel();

	private JLabel nameLabelValue = new JLabel("");
	private JLabel barCodeLabelValue = new JLabel("");
	private JLabel statusLabelValue = new JLabel("");
	private JLabel nodeTypeLabelValue = new JLabel("");

	// private JScrollPane jTreeScroll = new JScrollPane();
	private JScrollPane jTreeScroll;

	private GridBagLayout thisPanelLayout = new GridBagLayout();
	private GridBagLayout mainPanelLayout = new GridBagLayout();
	private GridBagLayout buttonPanelLayout = new GridBagLayout();
	private GridBagLayout labelPanelLayout = new GridBagLayout();

	private JTextArea commnetLabelValue = new JTextArea();
	private JTextArea intCommentLabelValue = new JTextArea();

	private DesktopMainJPanel desktopMainPanel;
	private JTree nodeTree;
	DefaultTreeCellRenderer renderer;
	private CustomMutableTreeNode projectNode;

	private HashMap<String, NodeProperties> nodePropertiesMap = new HashMap<String, NodeProperties>();
	private CustomMutableTreeNode lastSelectedNode = null;
	
	// private CustomMutableTreeNode[] nodesToCut = null;

	public DesktopLeftJPanel() {

	}

	public DesktopLeftJPanelController controller() {
		return (DesktopLeftJPanelController) getController();
	}

	public DesktopLeftJPanel(DesktopMainJPanel desktopMainJPanel) {
		this.desktopMainPanel = desktopMainJPanel;
		try {
			setController(new DesktopLeftJPanelController(this));
			jbInit();
			initHandler();
			setLabelCaption();
			setButtonCaption();
			setToolTips();
			setShortcuts();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void initHandler() {
		nodeTree.addTreeSelectionListener((TreeSelectionListener) this);
		nodeTree.setCellRenderer(new NodeRender(getNodePropertiesMap()));
		jbtnRefresh.addActionListener((ActionListener) this);
		// jbtnCut.addActionListener((ActionListener) this);
		// jbtnPaste.addActionListener((ActionListener) this);
		// nodeTree.addTreeExpansionListener((TreeExpansionListener)this)
	}

	void jbInit() throws Exception {
		initButtonPanel();
		initTreePanel();
		initNodeDetailPanel();
		initLeftPanel();
	}

	private void initLeftPanel() {
		mainPanel.setLayout(mainPanelLayout);

		mainPanel.add(jButtonPanel, new GridBagConstraints(0, 0, 1, 1, 1.0,
				0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
				new Insets(5, 5, 0, 5), 0, 0));
		mainPanel.add(kpiLabel, new GridBagConstraints(0, 1, 0, 1, 0.0, 0.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0));
		mainPanel.add(jTreePanel, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(0, 5, 0, 5), 0, 0));
		mainPanel.add(jLabelDetailPanel, new GridBagConstraints(0, 3, 1, 1,
				1.0, 0.0, GridBagConstraints.SOUTHWEST,
				GridBagConstraints.BOTH, new Insets(5, 5, 0, 5), 0, 0));

		this.setLayout(thisPanelLayout);
		this.add(mainPanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));
	}

	private void initButtonPanel() {

		jButtonPanel.setLayout(buttonPanelLayout);

		jbtnRefresh.setPreferredSize(new Dimension(100, 30));
		jbtnRefresh.setMinimumSize(new Dimension(100, 30));
		jbtnRefresh.setMaximumSize(new Dimension(100, 30));
		jbtnRefresh.setIcon(IconRepository.REFRESH_ICON);

		/*
		 * jbtnCut.setPreferredSize(new Dimension(90, 30));
		 * jbtnCut.setMinimumSize(new Dimension(90, 30));
		 * jbtnCut.setMaximumSize(new Dimension(90, 30));
		 * jbtnCut.setIcon(IconRepository.ICON_CUT);
		 * 
		 * jbtnPaste.setPreferredSize(new Dimension(90, 30));
		 * jbtnPaste.setMinimumSize(new Dimension(90, 30));
		 * jbtnPaste.setMaximumSize(new Dimension(90, 30));
		 * jbtnPaste.setIcon(IconRepository.ICON_PASTE);
		 */

		kpiLabel.setPreferredSize(new Dimension(250, 30));
		kpiLabel.setMinimumSize(new Dimension(250, 30));
		kpiLabel.setMaximumSize(new Dimension(250, 30));

		actualLabel.setPreferredSize(new Dimension(50, 30));
		actualLabel.setMinimumSize(new Dimension(50, 30));
		actualLabel.setMaximumSize(new Dimension(50, 30));

		fiveTLabel.setPreferredSize(new Dimension(50, 30));
		fiveTLabel.setMinimumSize(new Dimension(50, 30));
		fiveTLabel.setMaximumSize(new Dimension(50, 30));

		targetLabel.setPreferredSize(new Dimension(50, 30));
		targetLabel.setMinimumSize(new Dimension(50, 30));
		targetLabel.setMaximumSize(new Dimension(50, 30));

		tenTLabel.setPreferredSize(new Dimension(50, 30));
		tenTLabel.setMinimumSize(new Dimension(50, 30));
		tenTLabel.setMaximumSize(new Dimension(50, 30));

		jButtonPanel.add(jbtnRefresh, new GridBagConstraints(0, 0, 1, 1, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 10), 0, 0));

		/*
		 * jButtonPanel.add(jbtnCut, new GridBagConstraints(1, 0, 1, 1, 0.0,
		 * 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
		 * 0, 35, 0, 10), 0, 0)); jButtonPanel.add(jbtnPaste, new
		 * GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
		 * GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		 */

	}

        // KONS CODE
        public void refreshTreePanel() {
                jTreeScroll.getViewport().remove(nodeTree);
                createProjectRootNode();
                jTreeScroll.getViewport().add(nodeTree);
                fetchChildNodes(SessionUtil.getSessionData().getProjectId(),	null);
                ((DefaultTreeModel) getNodeTree().getModel()).reload();
                getNodeTree().updateUI();
		getNodeTree().repaint();
        }
        // KONS CODE ENDS
        
	public void initTreePanel() {

		jTreePanel.setLayout(new GridBagLayout());

		createProjectRootNode();

		nodeTree.setPreferredSize(null);
		nodeTree.setScrollsOnExpand(true);
		jTreeScroll = new JScrollPane();
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		
		// Code To Scale Tree Scroll Panel As Per Resolution
		// If resolution is greater then 1440 then increment tree panel Height
		if(xSize>1440)
		{
			jTreeScroll.setPreferredSize(new Dimension(320, 830));
			jTreeScroll.setMinimumSize(new Dimension(320, 830));
		}
		else
		{
			jTreeScroll.setPreferredSize(new Dimension(320, 700));
			jTreeScroll.setMinimumSize(new Dimension(320, 700));	
		}
		
		jTreeScroll.setBackground(Color.WHITE);

		jTreeScroll.getViewport().add(nodeTree);
		this.validate();
		// jTreePanel.add(jTreeScroll, BorderLayout.CENTER);

		jTreePanel.add(jTreeScroll, new GridBagConstraints(0, 0, 1, 1, 1.0,
				1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
	}

	
            public void fetchChildNodes(String projectId, String parentNodeId) {

		ArrayList<String> idList = new ArrayList<String>();

		idList.add(projectId);
		idList.add(parentNodeId);

		ArrayList<NodeProperties> childNodePropertiesList = controller()
				.getChildNodePropertiesList(idList);

		if (childNodePropertiesList != null
				&& childNodePropertiesList.size() > 0) {

			for (int index = 0; index < childNodePropertiesList.size(); index++) {

				NodeProperties childNodeProperties = childNodePropertiesList
						.get(index);

				String nodeId = childNodeProperties.getNodeId();
				String nodeName = childNodeProperties.getName();

				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.controller()
						.addNodeButton_actionPerformed(nodeId, nodeName);

				nodePropertiesMap.put(nodeId, childNodeProperties);
			}

			TreePath path = new TreePath(projectNode.getPath());
			getNodeTree().expandPath(path);
		}
	}
            
            
          

	private void initNodeDetailPanel() {
		nameLabelValue.setPreferredSize(new Dimension(180, 13));
		barCodeLabelValue.setPreferredSize(new Dimension(180, 13));
		// commnetLabelValue.setPreferredSize(new Dimension(180, 13));

		commnetLabelValue.setLineWrap(true);
		commnetLabelValue.setEditable(false);
		commnetLabelValue.setBackground(this.getBackground());
		commnetLabelValue.setMinimumSize(new Dimension(180, 30));
		commnetLabelValue.setMaximumSize(new Dimension(280, 30));
		commnetLabelValue.setPreferredSize(new Dimension(200, 30));
		commnetLabelValue.setRows(2);

		// intCommentLabelValue.setPreferredSize(new Dimension(200, 13));

		intCommentLabelValue.setLineWrap(true);
		intCommentLabelValue.setEditable(false);
		intCommentLabelValue.setBackground(this.getBackground());
		intCommentLabelValue.setMinimumSize(new Dimension(180, 30));
		intCommentLabelValue.setMaximumSize(new Dimension(280, 30));
		intCommentLabelValue.setPreferredSize(new Dimension(200, 30));
		intCommentLabelValue.setRows(2);
		// commnetLabelValue.setPreferredSize(new Dimension(180, 30));
		// commnetLabelValue.setMinimumSize(new Dimension(180, 30));
		statusLabelValue.setPreferredSize(new Dimension(180, 13));
		nodeTypeLabelValue.setPreferredSize(new Dimension(180, 13));
		nameLabelValue.setMinimumSize(new Dimension(180, 13));
		barCodeLabelValue.setMinimumSize(new Dimension(180, 13));

		statusLabelValue.setMinimumSize(new Dimension(180, 13));
		nodeTypeLabelValue.setMinimumSize(new Dimension(180, 13));

		jLabelDetailPanel.setLayout(labelPanelLayout);
		jLabelDetailPanel.add(nameLabel, new GridBagConstraints(0, 0, 1, 1,
				0.0, 0.0, GridBagConstraints.SOUTHWEST,
				GridBagConstraints.NONE, new Insets(0, 15, 0, 0), 0, 0));
		jLabelDetailPanel.add(barCodeLabel, new GridBagConstraints(0, 1, 1, 1,
				0.0, 0.0, GridBagConstraints.SOUTHWEST,
				GridBagConstraints.NONE, new Insets(3, 15, 0, 0), 0, 0));
		jLabelDetailPanel.add(commnetLabel, new GridBagConstraints(0, 2, 1, 1,
				0.0, 0.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.NONE, new Insets(0, 15, 0, 0), 0, 0));
		jLabelDetailPanel.add(intCommentLabel, new GridBagConstraints(0, 3, 1,
				1, 0.0, 0.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.NONE, new Insets(0, 15, 0, 0), 0, 0));
		jLabelDetailPanel.add(statusLabel, new GridBagConstraints(0, 6, 1, 1,
				0.0, 0.0, GridBagConstraints.SOUTHWEST,
				GridBagConstraints.NONE, new Insets(3, 15, 0, 0), 0, 0));
		jLabelDetailPanel.add(nodeTypeLabel, new GridBagConstraints(0, 7, 1, 1,
				0.0, 0.0, GridBagConstraints.SOUTHWEST,
				GridBagConstraints.NONE, new Insets(3, 15, 0, 0), 0, 0));

		jLabelDetailPanel.setLayout(labelPanelLayout);
		jLabelDetailPanel.add(nameLabelValue, new GridBagConstraints(1, 0, 1,
				1, 0.0, 0.0, GridBagConstraints.SOUTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 15, 0, 0), 0, 0));
		jLabelDetailPanel.add(barCodeLabelValue, new GridBagConstraints(1, 1,
				1, 1, 0.0, 0.0, GridBagConstraints.SOUTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(3, 15, 0, 0), 0, 0));
		jLabelDetailPanel.add(commnetLabelValue, new GridBagConstraints(1, 2,
				1, 1, 1.0, 0.0, GridBagConstraints.SOUTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 15, 0, 0), 0, 0));
		jLabelDetailPanel.add(intCommentLabelValue, new GridBagConstraints(1,
				3, 1, 1, 1.0, 0.0, GridBagConstraints.SOUTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 15, 0, 0), 0, 0));
		jLabelDetailPanel.add(statusLabelValue, new GridBagConstraints(1, 6, 1,
				1, 0.0, 0.0, GridBagConstraints.SOUTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(3, 15, 0, 0), 0, 0));
		jLabelDetailPanel.add(nodeTypeLabelValue, new GridBagConstraints(1, 7,
				1, 1, 0.0, 0.0, GridBagConstraints.SOUTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(3, 15, 0, 0), 0, 0));
	}

	// Return the frame's preferred size.
	public Dimension getPreferredSize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return new Dimension(screenSize.width, screenSize.height - 20); // 40
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jbtnRefresh) {

			// nodesToCut = null;

			refreshTree();
		}

		/*
		 * if (e.getSource() == jbtnCut) {
		 * 
		 * // Getting selected nodes to cut. TreePath[] selectedTreePaths =
		 * getNodeTree().getSelectionPaths();
		 * 
		 * if (selectedTreePaths == null || selectedTreePaths.length <= 0) {
		 * ErrorMessage.displayMessage('I', "selectAtleastOneNodeToCut");
		 * return; }
		 * 
		 * nodesToCut = new CustomMutableTreeNode[selectedTreePaths.length];
		 * 
		 * for (int index = 0; index < selectedTreePaths.length; index++) {
		 * 
		 * // If Project Node is selected to cut... if (((CustomMutableTreeNode)
		 * selectedTreePaths[index] .getLastPathComponent()).getNodeId().equals(
		 * SessionUtil.getSessionData().getProjectId())) {
		 * 
		 * nodesToCut = null; ErrorMessage.displayMessage('I',
		 * "rootNodeNotAllowToCut"); return; }
		 * 
		 * nodesToCut[index] = (CustomMutableTreeNode) selectedTreePaths[index]
		 * .getLastPathComponent(); } }
		 * 
		 * if (e.getSource() == jbtnPaste) {
		 * 
		 * TreePath[] selectedTreePaths = getNodeTree().getSelectionPaths();
		 * 
		 * if (nodesToCut == null || nodesToCut.length == 0) {
		 * ErrorMessage.displayMessage('I', "noNodesToCutPaste"); return; }
		 * 
		 * if (selectedTreePaths == null || selectedTreePaths.length <= 0) {
		 * ErrorMessage.displayMessage('I', "noDestinationNodeSelected");
		 * return; }
		 * 
		 * if (selectedTreePaths != null && selectedTreePaths.length > 1) {
		 * ErrorMessage.displayMessage('I', "moreThanOneDestinationNode");
		 * return; }
		 * 
		 * CustomMutableTreeNode destinationNode = (CustomMutableTreeNode)
		 * selectedTreePaths[0] .getLastPathComponent();
		 * 
		 * // Checking if one of nodes that are cut and destination node is //
		 * same... for (int index = 0; index < nodesToCut.length; index++) {
		 * 
		 * if (destinationNode == nodesToCut[index]) {
		 * 
		 * ErrorMessage.displayMessage('I',
		 * "sameCutNodeAndDestinationNodeNotAllowed"); nodesToCut = null;
		 * return; } }
		 * 
		 * // To check if destination node is Project Root Node... boolean
		 * isProjectNodeAsDestinationNode = destinationNode .getNodeId().equals(
		 * SessionUtil.getSessionData().getProjectId());
		 * 
		 * if (!isProjectNodeAsDestinationNode) {
		 * 
		 * NodeProperties destinationNodeProperty = getNodePropertiesMap()
		 * .get((destinationNode).getNodeId());
		 * 
		 * // If Project Node is not destination node, destination node //
		 * should not be Document node or with Done status. if
		 * (destinationNodeProperty.getStatus().equals("1")) {
		 * ErrorMessage.displayMessage('I', "notAllowedToAddNodeInDoneState");
		 * return; }
		 * 
		 * if (destinationNodeProperty.getType().equals("D")) {
		 * ErrorMessage.displayMessage('I', "notAllowedToAddInDocumentNode");
		 * return; } }
		 * 
		 * ArrayList<NodeProperties> nodePropertiesList = new
		 * ArrayList<NodeProperties>();
		 * 
		 * for (int index = 0; index < nodesToCut.length; index++) {
		 * 
		 * // Below line will add node to new destination node...
		 * destinationNode.add(nodesToCut[index]);
		 * 
		 * // Updating node properties... NodeProperties nodeProperties =
		 * getNodePropertiesMap().get( nodesToCut[index].getNodeId());
		 * nodeProperties.setParentNodeId(destinationNode.getNodeId());
		 * nodeProperties.setHierarchy((new TreePath(nodesToCut[index]
		 * .getPath())).toString());
		 * nodeProperties.setUserId(SessionUtil.getSessionData() .getUserId());
		 * 
		 * nodePropertiesList.add(nodeProperties); }
		 * 
		 * // Expanding Destination node. ((DefaultTreeModel)
		 * getNodeTree().getModel()).reload(); getNodeTree().setSelectionPath(
		 * new TreePath(destinationNode.getPath()));
		 * getNodeTree().expandPath(new TreePath(destinationNode.getPath()));
		 * 
		 * // Making the call to server to update in DB and get new node //
		 * properties list. nodePropertiesList =
		 * desktopMainPanel.getjRightPanel() .getCollectionPanel().controller()
		 * .saveNodePropertiesList(nodePropertiesList);
		 * 
		 * if (nodePropertiesList != null && nodePropertiesList.size() > 0) {
		 * 
		 * for (int index = 0; index < nodePropertiesList.size(); index++) {
		 * 
		 * NodeProperties nodeProperties = nodePropertiesList .get(index);
		 * 
		 * getNodePropertiesMap().put(nodeProperties.getNodeId(),
		 * nodeProperties); } }
		 * 
		 * nodesToCut = null; }
		 */
	}

	private void refreshTree() {
		String hierarchy = "";

		CustomMutableTreeNode newNode = desktopMainPanel.getjRightPanel()
				.getCollectionPanel().getSelectedTreeNode();

		if (newNode == null)
			return;

		NodeProperties selectedNodeProperty = getNodePropertiesMap().get(
				newNode.getNodeId());
		if (selectedNodeProperty != null) {
			hierarchy = selectedNodeProperty.getHierarchy();
		}

		// Condition to check if node is saved or newly added node.
		if (newNode.getNodeId().equals(
				SessionUtil.getSessionData().getProjectId())
				|| (newNode.getNodeId() != null && newNode.getNodeId().trim()
						.length() > 0)) {

			if (desktopMainPanel.getjRightPanel().getCollectionPanel()
					.isNodePropertyModified())
				ErrorMessage.displayMessage('I',
						"saveOrDeleteUpdatedNodeBeforeRefresh");

			else {

				projectNode.removeAllChildren();
				nodePropertiesMap.clear();
				((DefaultTreeModel) getNodeTree().getModel()).reload();
				getNodeTree().setSelectionPath(
						new TreePath(projectNode.getPath()));
				fetchChildNodes(SessionUtil.getSessionData().getProjectId(),
						null);
				statusLabelValue.setText("");

				desktopMainPanel.getjRightPanel().getImportAndSepPanel()
						.searchNodeFromhierarchy(getProjectNode(), hierarchy);
			}
		} else {
			ErrorMessage.displayMessage('I',
					"saveOrDeleteAddedNodeBeforeRefresh");
		}
	}

	public void createProjectRootNode() {

		String project = SessionUtil.getSessionData().getProjectName();

		projectNode = new CustomMutableTreeNode(project);
		projectNode.setNodeId(SessionUtil.getSessionData().getProjectId());
                
                // INITALISE THE NDOE TRE
    		nodeTree = new JTree(projectNode);
		nodeTree.setExpandsSelectedPaths(true);
                nodeTree.setSelectionPath(new TreePath(projectNode.getPath()));
                initHandler();                                                  // KONS EDIT, CALLING THIS TO INITIALISE THE EVENT LISTENERS PER TB CODE
                renderer = new DefaultTreeCellRenderer();                       // no idea what this does and where renderer is needed
                
	}

	public void setLabelCaption() {

		setOverallKPILabel("0");

		nameLabel.setText(ConstantUtil.getApplicationConstant("name"));
		barCodeLabel.setText(ConstantUtil.getApplicationConstant("barcode"));
		commnetLabel.setText(ConstantUtil.getApplicationConstant("comment"));
		intCommentLabel.setText(ConstantUtil
				.getApplicationConstant("internalComment"));
		statusLabel.setText(ConstantUtil.getApplicationConstant("status"));
		nodeTypeLabel.setText(ConstantUtil.getApplicationConstant("type"));

		actualLabel.setText(ConstantUtil.getApplicationConstant("actual"));
		targetLabel.setText(ConstantUtil.getApplicationConstant("target"));

	}

	/**
	 * Method to set the overall KPI performance.
	 * 
	 * @param overallKPIPerformance
	 */
	public void setOverallKPILabel(String overallKPIPerformance) {

		kpiLabel.setText(ConstantUtil
				.getApplicationConstant("overallProductivity")
				+ " : "
				+ " "
				+ overallKPIPerformance
				+ " / "
				+ SessionUtil.getSessionData().getOverallTarget());
	}

	public void setButtonCaption() {
		jbtnRefresh.setText(ConstantUtil.getApplicationConstant("refresh"));
		/*
		 * jbtnCut.setText(ConstantUtil.getApplicationConstant("cut"));
		 * jbtnPaste.setText(ConstantUtil.getApplicationConstant("paste"));
		 */
	}

	public void setToolTips() {
		jbtnRefresh.setToolTipText("Refresh Tree");
	}

	public void setShortcuts() {
	}

	public void cleanup() {

		jLeftPanel = null;
	}

	public void setForLogin() {
		// jbtnLogon.requestFocus();
	}

	public void focusGained(FocusEvent arg0) {

	}

	public void focusLost(FocusEvent arg0) {

	}

	public DesktopMainJPanel getDesktopMainPanel() {
		return desktopMainPanel;
	}

	public void setDesktopMainPanel(DesktopMainJPanel desktopMainPanel) {
		this.desktopMainPanel = desktopMainPanel;
	}

	public JTree getNodeTree() {
		return nodeTree;
	}

	public void setNodeTree(JTree nodeTree) {
		this.nodeTree = nodeTree;
	}

	public JLabel getNameLabelValue() {
		return nameLabelValue;
	}

	public void setNameLabelValue(JLabel nameLabelValue) {
		this.nameLabelValue = nameLabelValue;
	}

	public JLabel getBarCodeLabelValue() {
		return barCodeLabelValue;
	}

	public void setBarCodeLabelValue(JLabel barCodeLabelValue) {
		this.barCodeLabelValue = barCodeLabelValue;
	}

	public JLabel getStatusLabelValue() {
		return statusLabelValue;
	}

	public void setStatusLabelValue(JLabel statusLabelValue) {
		this.statusLabelValue = statusLabelValue;
	}

	public JLabel getNodeTypeLabelValue() {
		return nodeTypeLabelValue;
	}

	public void setNodeTypeLabelValue(JLabel nodeTypeLabelValue) {
		this.nodeTypeLabelValue = nodeTypeLabelValue;
	}

	public CustomMutableTreeNode getProjectNode() {
		return projectNode;
	}

	public void setProjectNode(CustomMutableTreeNode projectNode) {
		this.projectNode = projectNode;
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {

		log.info("Value Change Call");

		if (e.getSource() == nodeTree) {

			JTree nodeTree = desktopMainPanel.getjLeftPanel().getNodeTree();

			// In case the node is deselected (by Ctrl + Click), selected node
			// will not be found and hence return from method.
			if (nodeTree.getSelectionPath() == null
					|| nodeTree.getSelectionPath().getLastPathComponent() == null)
				return;

			restrictNodeSelection();
			performCheckBeforeSelectingNode();

			CustomMutableTreeNode selectedElement = (CustomMutableTreeNode) nodeTree
					.getSelectionPath().getLastPathComponent();
			NodeProperties nodeProperties = getNodePropertiesMap().get(
					selectedElement.getNodeId());

			// If Selected Node Is Project Node
			if (selectedElement.getNodeId().equals(
					SessionUtil.getSessionData().getProjectId())) {
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.nodePropertyEnableDisable(false);
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.getNodeTypeList().setEnabled(false);
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.setButtonEnableDisable(false);
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.getAddNodeButton().setEnabled(true);
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.resetLeftPanelNodePropertyLabel();
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.resetNodeProperty();
			}

			// If New Node
			else if (selectedElement.getNodeId() == null
					|| selectedElement.getNodeId().length() == 0) {
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.nodePropertyEnableDisable(true);
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.getNodeTypeList().setEnabled(true);
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.setButtonEnableDisable(true);
			}

			// If Node Is In Sealed Status
			else if (nodeProperties.getStatus().equals(NodePropertyConstants.SEALED)) {
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.setAllButtonEnableDisable(false);
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.nodeAllPropertyEnableDisable(false);
			}
			// If Node Is In Done Status
			else if (nodeProperties.getStatus().equals(NodePropertyConstants.DONE)) {
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.setAllButtonEnableDisable(false);
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.setDoneStatusNodePropertyEnableDisable(false);

				// Save Button Enabled from
				// setDoneStatusNodePropertyEnableDisable() method
			}
			// If Node Is In InProgress Status
			else if (nodeProperties.getStatus().equals(NodePropertyConstants.IN_PROGRESS)) {

				// If Cut Nodes Are Available
				if (desktopMainPanel.getjRightPanel().getCollectionPanel()
						.getNodesToCut() != null
						&& desktopMainPanel.getjRightPanel()
								.getCollectionPanel().getNodesToCut().length > 0) {
					desktopMainPanel.getjRightPanel().getCollectionPanel()
							.getAddNodeButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getCollectionPanel()
							.setButtonEnableDisable(false);
					desktopMainPanel.getjLeftPanel().getJbtnRefresh()
							.setEnabled(false);
				}
				// If Cut Nodes Are Not Available
				else {
					desktopMainPanel.getjRightPanel().getCollectionPanel()
							.getAddNodeButton().setEnabled(true);
					desktopMainPanel.getjRightPanel().getCollectionPanel()
							.setButtonEnableDisable(true);
					desktopMainPanel.getjRightPanel().getCollectionPanel()
							.getPasteButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getCollectionPanel()
							.getCancelButton().setEnabled(false);
					desktopMainPanel.getjLeftPanel().getJbtnRefresh()
							.setEnabled(true);
				}

				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.getCutButton().setEnabled(true);
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.nodePropertyEnableDisable(true);
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.getNodeTypeList().setEnabled(false);
			}

			// Set Button Enable Disable If Node Is Cut In Collection Panel
			log.info("Node To Cut : "
					+ desktopMainPanel.getjRightPanel().getCollectionPanel()
							.getNodesToCut());
			if (desktopMainPanel.getjRightPanel().getCollectionPanel()
					.getNodesToCut() != null
					&& desktopMainPanel.getjRightPanel().getCollectionPanel()
							.getNodesToCut().length > 0) {
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.getCutButton().setEnabled(false);
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.getPasteButton().setEnabled(true);
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.getCancelButton().setEnabled(true);

				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.getAddNodeButton().setEnabled(false);
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.getSaveButton().setEnabled(false);
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.getDeleteButton().setEnabled(false);

				desktopMainPanel.getjLeftPanel().getJbtnRefresh()
						.setEnabled(false);
			}

			if (!selectedElement.getNodeId().equals(
					SessionUtil.getSessionData().getProjectId())
					&& selectedElement.getNodeId().length() > 0) {
				if (lastSelectedNode == null
						|| !selectedElement.getNodeId().equals(
								lastSelectedNode.getNodeId())) {
					controller().getAndSetNodePropertyValue();
				}

			} else {

				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.resetLeftPanelNodePropertyLabel();
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.resetNodeProperty();
			}

			if (nodeProperties != null && selectedElement.getNodeId() != null
					&& selectedElement.getNodeId().length() > 0
					&& selectedElement.getChildCount() <= 0) {

				if (nodeProperties.getType().equalsIgnoreCase(NodePropertyConstants.CONTAINER)
						|| nodeProperties.getType().equalsIgnoreCase(NodePropertyConstants.BOX)) {
					fetchChildNodes(
							SessionUtil.getSessionData().getProjectId(),
							selectedElement.getNodeId());
					TreePath path = new TreePath(selectedElement.getPath());
					getNodeTree().expandPath(path);
				}
			}

			lastSelectedNode = selectedElement;

			// Import Separation Panel Thumbnail Panel Visible/Invisible
			visibleInvisibleThumbnailPanel(selectedElement);

			// Import And Separation Panel Buttons Enable Diable
			enableDisableImportSeparationButtons(nodeProperties);

		}
	}

	private void visibleInvisibleThumbnailPanel(
			CustomMutableTreeNode selectedElement) {
		// Condition TO Check Image Viewer/ Thumbnail Panel is visible and
		// change nodes to container/box then hide
		if (desktopMainPanel.getjRightPanel().getImportAndSepPanel()
				.getImageViewer() != null) {
			if (desktopMainPanel.getjRightPanel().getImportAndSepPanel()
					.getLastViewedThumbnailNodeProperty() != null) {
				if (selectedElement.getNodeId().equals(
						desktopMainPanel.getjRightPanel()
								.getImportAndSepPanel()
								.getLastViewedThumbnailNodeProperty()
								.getNodeId())) {
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getImageViewer().setVisible(true);
				} else {
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getImageViewer().setVisible(false);
				}
			}
		}
	}

	public void enableDisableImportSeparationButtons(
			NodeProperties nodeProperties) {
		// Check If Node Is Project Node
		if (nodeProperties == null
				|| SessionUtil.getSessionData().getProjectId()
						.equals(nodeProperties.getNodeId())) {
			desktopMainPanel.getjRightPanel().getImportAndSepPanel()
					.setAllButtonEnableDisable(false);
		} else {
			desktopMainPanel.getjRightPanel().getImportAndSepPanel()
					.getSearchButton().setEnabled(true);
			desktopMainPanel.getjRightPanel().getImportAndSepPanel()
					.getHelpButton().setEnabled(true);

			if (nodeProperties.getType().equalsIgnoreCase(NodePropertyConstants.DOCUMENT)) {
				if (nodeProperties.getStatus().equals(NodePropertyConstants.IN_PROGRESS)) {
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getViewThumbnailsButton().setEnabled(true);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getCreateDocumentButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getDeleteDocumentButton().setEnabled(true);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getEditNodePropertyButton().setEnabled(true);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getImportButton().setEnabled(true);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getResumeImportButton().setEnabled(true);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getSplitButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getReverseButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getDeletePageButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getCutButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getPasteButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getCancelButton().setEnabled(false);

					checkConditionToEnableDisableButtonForPageNode(nodeProperties);
				}
				if (nodeProperties.getStatus().equals(NodePropertyConstants.DONE)) {
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getViewThumbnailsButton().setEnabled(true);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getCreateDocumentButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getDeleteDocumentButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getEditNodePropertyButton().setEnabled(true);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getImportButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getResumeImportButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getSplitButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getReverseButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getDeletePageButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getCutButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getPasteButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getCancelButton().setEnabled(false);
				}
				if (nodeProperties.getStatus().equals(NodePropertyConstants.SEALED)) {
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getViewThumbnailsButton().setEnabled(true);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getCreateDocumentButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getDeleteDocumentButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getEditNodePropertyButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getImportButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getResumeImportButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getSplitButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getReverseButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getDeletePageButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getCutButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getPasteButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getCancelButton().setEnabled(false);
				}
			} else if (nodeProperties.getType().equalsIgnoreCase(NodePropertyConstants.BOX)
					|| nodeProperties.getType().equalsIgnoreCase(NodePropertyConstants.CONTAINER)) {

				if (nodeProperties.getStatus().equals(NodePropertyConstants.IN_PROGRESS)) {
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getViewThumbnailsButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getCreateDocumentButton().setEnabled(true);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getDeleteDocumentButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getEditNodePropertyButton().setEnabled(true);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getImportButton().setEnabled(true);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getResumeImportButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getSplitButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getReverseButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getDeletePageButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getCutButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getPasteButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getCancelButton().setEnabled(false);
				}
				if (nodeProperties.getStatus().equals(NodePropertyConstants.DONE)) {
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getViewThumbnailsButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getCreateDocumentButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getDeleteDocumentButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getEditNodePropertyButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getImportButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getResumeImportButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getSplitButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getReverseButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getDeletePageButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getCutButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getPasteButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getCancelButton().setEnabled(false);
				}
				if (nodeProperties.getStatus().equals(NodePropertyConstants.SEALED)) {
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getViewThumbnailsButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getCreateDocumentButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getDeleteDocumentButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getEditNodePropertyButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getImportButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getResumeImportButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getSplitButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getReverseButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getDeletePageButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getCutButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getPasteButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getCancelButton().setEnabled(false);
				}
			}

		}
	}

	private void checkConditionToEnableDisableButtonForPageNode(
			NodeProperties nodeProperties) {

		// Condition To Enable Button
		// While Image Viewer Is Not Null
		if (desktopMainPanel.getjRightPanel().getImportAndSepPanel()
				.getImageViewer() != null) {
			JList selectedPagesList = desktopMainPanel.getjRightPanel()
					.getImportAndSepPanel().getImageViewer().getThumbnailList();
			Object[] selectedValues = selectedPagesList.getSelectedValues();

			// Enable Button If At Least One Page Is Slected
			if (selectedValues.length > 0) {
				// Enable Button If Cut Nodes Are Available
				if (desktopMainPanel.getjRightPanel().getImportAndSepPanel()
						.getCutNodePropertyList() != null
						&& !desktopMainPanel.getjRightPanel()
								.getImportAndSepPanel()
								.getCutNodePropertyList().isEmpty()) {
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getSplitButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getReverseButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getDeletePageButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getCutButton().setEnabled(false);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getPasteButton().setEnabled(true);
					desktopMainPanel.getjRightPanel().getImportAndSepPanel()
							.getCancelButton().setEnabled(true);
				}
				// Disable Button If Cut Nodes Are Not Available
				else {
					// Condition To Check Same Node Is Selected Then Only Enable
					if (desktopMainPanel.getjRightPanel()
							.getImportAndSepPanel()
							.getLastViewedThumbnailNodeProperty().getNodeId()
							.equals(nodeProperties.getNodeId())) {
						desktopMainPanel.getjRightPanel()
								.getImportAndSepPanel().getSplitButton()
								.setEnabled(true);
						desktopMainPanel.getjRightPanel()
								.getImportAndSepPanel().getReverseButton()
								.setEnabled(true);
						desktopMainPanel.getjRightPanel()
								.getImportAndSepPanel().getDeletePageButton()
								.setEnabled(true);
						desktopMainPanel.getjRightPanel()
								.getImportAndSepPanel().getCutButton()
								.setEnabled(true);
						desktopMainPanel.getjRightPanel()
								.getImportAndSepPanel().getPasteButton()
								.setEnabled(false);
						desktopMainPanel.getjRightPanel()
								.getImportAndSepPanel().getCancelButton()
								.setEnabled(false);
					}

				}
			}
			// Enable Button If Cut Nodes Are Available (And Paste It To Different Node After Press button View Thumbnails)
			else if (desktopMainPanel.getjRightPanel().getImportAndSepPanel()
					.getCutNodePropertyList() != null
					&& !desktopMainPanel.getjRightPanel()
							.getImportAndSepPanel()
							.getCutNodePropertyList().isEmpty()) {
				desktopMainPanel.getjRightPanel().getImportAndSepPanel()
						.getSplitButton().setEnabled(false);
				desktopMainPanel.getjRightPanel().getImportAndSepPanel()
						.getReverseButton().setEnabled(false);
				desktopMainPanel.getjRightPanel().getImportAndSepPanel()
						.getDeletePageButton().setEnabled(false);
				desktopMainPanel.getjRightPanel().getImportAndSepPanel()
						.getCutButton().setEnabled(false);
				desktopMainPanel.getjRightPanel().getImportAndSepPanel()
						.getPasteButton().setEnabled(true);
				desktopMainPanel.getjRightPanel().getImportAndSepPanel()
						.getCancelButton().setEnabled(true);
			}
			

		}
	}

	public void restrictNodeSelection() {

		TreePath[] selectedTreePaths = nodeTree.getSelectionPaths();
		ArrayList<TreePath> newSelectedTreePathList = new ArrayList<TreePath>();

		if (selectedTreePaths == null || selectedTreePaths.length <= 0)
			return;

		TreeNode firstNodeParent = ((CustomMutableTreeNode) selectedTreePaths[0]
				.getLastPathComponent()).getParent();

		// Checking if all selected nodes belong to same Parent...
		for (int index = 0; index < selectedTreePaths.length; index++) {

			TreeNode nodeParent = ((CustomMutableTreeNode) selectedTreePaths[index]
					.getLastPathComponent()).getParent();

			if (nodeParent != firstNodeParent) {

				nodeTree.removeSelectionPath(selectedTreePaths[index]);

			} else {

				newSelectedTreePathList.add(selectedTreePaths[index]);
			}
		}

		TreePath[] newTreePaths = new TreePath[newSelectedTreePathList.size()];

		for (int index = 0; index < newSelectedTreePathList.size(); index++) {
			newTreePaths[index] = newSelectedTreePathList.get(index);
		}

		nodeTree.setSelectionPaths(newTreePaths);
	}

	/**
	 * Method to perform check before selecting any node. also used in
	 * collection Panel
	 */
	public void performCheckBeforeSelectingNode() {

		CustomMutableTreeNode selectedElement = (CustomMutableTreeNode) nodeTree
				.getSelectionPath().getLastPathComponent();

		// Condition to check when New Node is Added...
		if (desktopMainPanel.getjRightPanel().getCollectionPanel()
				.getNewAddedNode() != null) {

			CustomMutableTreeNode newAddedNode = desktopMainPanel
					.getjRightPanel().getCollectionPanel().getNewAddedNode();

			if (selectedElement.getNodeId() != null
					&& newAddedNode.getNodeId() != null
					&& !selectedElement.getNodeId().equals(
							newAddedNode.getNodeId())) {

				ErrorMessage.displayMessage('I',
						"saveOrDeleteAddedNodeBeforeSelectingOther");

				TreePath newNodeTreePath = new TreePath(newAddedNode.getPath());
				getNodeTree().setSelectionPath(newNodeTreePath);
				getNodeTree().expandPath(newNodeTreePath);
			}
		}

		// Condition to check when Node property is Modified...
		else if (lastSelectedNode != null
				&& !lastSelectedNode.getNodeId().equals(
						selectedElement.getNodeId())
				&& nodePropertiesMap.get(lastSelectedNode.getNodeId()) != null) {

			NodeProperties nodeProperties = nodePropertiesMap
					.get(lastSelectedNode.getNodeId());

			String barcode = desktopMainPanel.getjRightPanel()
					.getCollectionPanel().getBarCodeFeild().getText().trim();
			String comment = desktopMainPanel.getjRightPanel()
					.getCollectionPanel().getCommentFeild().getText().trim();
			String internalComment = desktopMainPanel.getjRightPanel()
					.getCollectionPanel().getIntCommentFeild().getText().trim();
			String status = getValueFromMap(SessionUtil.getSessionData()
					.getStatusMap(), desktopMainPanel.getjRightPanel()
					.getCollectionPanel().getStatusList().getSelectedValue()
					.toString());

			if (!barcode.equals(nodeProperties.getBarcode())
					|| !comment.equals(nodeProperties.getComment())
					|| !internalComment.equals(nodeProperties
							.getInternalComment())
					|| !status.equals(nodeProperties.getStatus())) {

				ErrorMessage.displayMessage('I', "saveOrDeleteUpdatedNode");

				TreePath lastSelectedNodeTreePath = new TreePath(
						lastSelectedNode.getPath());
				getNodeTree().setSelectionPath(lastSelectedNodeTreePath);
				getNodeTree().expandPath(lastSelectedNodeTreePath);
			}
		}
	}

	// Methods Which Returns the Key of Passed Value
	private String getValueFromMap(Map<String, String> map, String passedKey) {
		for (String key : map.keySet()) {
			if (key.equals(passedKey))
				return map.get(key);
		}
		return "";
	}

	public HashMap<String, NodeProperties> getNodePropertiesMap() {
		return nodePropertiesMap;
	}

	public void setNodePropertiesMap(
			HashMap<String, NodeProperties> nodePropertiesMap) {
		this.nodePropertiesMap = nodePropertiesMap;
	}

	public DefaultTreeCellRenderer getRenderer() {
		return renderer;
	}

	public void setRenderer(DefaultTreeCellRenderer renderer) {
		this.renderer = renderer;
	}

	public JLabel getCommnetLabel() {
		return commnetLabel;
	}

	public void setCommnetLabel(JLabel commnetLabel) {
		this.commnetLabel = commnetLabel;
	}

	public JLabel getIntCommentLabel() {
		return intCommentLabel;
	}

	public void setIntCommentLabel(JLabel intCommentLabel) {
		this.intCommentLabel = intCommentLabel;
	}

	public JTextArea getCommnetLabelValue() {
		return commnetLabelValue;
	}

	public void setCommnetLabelValue(JTextArea commnetLabelValue) {
		this.commnetLabelValue = commnetLabelValue;
	}

	public JTextArea getIntCommentLabelValue() {
		return intCommentLabelValue;
	}

	public void setIntCommentLabelValue(JTextArea intCommentLabelValue) {
		this.intCommentLabelValue = intCommentLabelValue;
	}

	public JButton getJbtnRefresh() {
		return jbtnRefresh;
	}

	public void setJbtnRefresh(JButton jbtnRefresh) {
		this.jbtnRefresh = jbtnRefresh;
	}

	/*
	 * public void setNodesToCut(CustomMutableTreeNode[] nodesToCut) {
	 * this.nodesToCut = nodesToCut; }
	 * 
	 * public CustomMutableTreeNode[] getNodesToCut() { return nodesToCut; }
	 */
}

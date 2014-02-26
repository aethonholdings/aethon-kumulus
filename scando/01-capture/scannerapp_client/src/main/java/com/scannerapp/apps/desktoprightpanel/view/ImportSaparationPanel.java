package com.scannerapp.apps.desktoprightpanel.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.border.EtchedBorder;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import SK.gnome.twain.TwainManager;
import SK.gnome.twain.TwainSource;

import com.scannerapp.apps.component.EdittedTextField;
import com.scannerapp.apps.desktopleftpanel.view.CustomMutableTreeNode;
import com.scannerapp.apps.desktopmainpanel.view.DesktopMainJPanel;
import com.scannerapp.apps.desktoprightpanel.scanner6.DocumentScanner;
import com.scannerapp.apps.framework.view.BaseJPanel;
import com.scannerapp.apps.framework.view.ErrorMessage;
import com.scannerapp.apps.utils.AsyncImageViewer;
import com.scannerapp.apps.utils.ConstantUtil;
import com.scannerapp.apps.utils.SessionUtil;
import com.scannerapp.common.HelpPopup;
import com.scannerapp.common.NodePropertyConstants;
import com.scannerapp.resources.IconRepository;
import com.scannerapp.shared.NodeProperties;
import com.scannerapp.shared.TransactionConstant;

@SuppressWarnings("serial")
public class ImportSaparationPanel extends BaseJPanel implements
		ActionListener, KeyListener {

	private static Logger log = Logger.getLogger(ImportSaparationPanel.class);// For
																				// Log4j
	private DesktopMainJPanel desktopMainPanel;

	// Main Panel
	private JPanel mainPanel;
	private GridBagLayout mainPanelLayout;
	private GridBagLayout basePanelLayout;

	// Upper Panel
	private JPanel upperPanel;

	// Search Panel
	private JPanel searchPanel;
	private GridBagLayout searchPanelLayout;
	private JPanel labelPanel;
	private GridBagLayout labelPanelLayout;
	private JLabel barcode;
	private EdittedTextField barcodeField;
	private JButton searchButton;
	private JButton helpButton;
	private JLabel importKPI;
	private JLabel separationKPI;
	private JLabel scannedPage;

	// Button Panel
	private JPanel buttonPanel;
	private JPanel buttonPanelGroup1;
	private JPanel buttonPanelGroup2;
	private JPanel buttonPanelGroup3;
	private GridBagLayout buttonPanelLayout;
	private JButton importButton;
	private JButton createDocumentButton;
	private JButton deleteDocumentButton;
	private JButton viewThumbnailsButton;
	private JButton editNodePropertyButton;
	private JButton splitButton;
	private JButton cutButton;
	private JButton pasteButton;
	private JButton cancelButton;
	private JButton reverseButton;
	private JButton resumeImportButton;
	private JButton deletePageButton;

	// Extra
	public static NodeProperties lastViewedThumbnailNodeProperty;
	private int[] lastSelectedPageIndexeList = null;
	private List<Integer> deletePageIndex;
	private Integer destinationNodeIndexToSelectPage = -1;
	private NodeProperties destinationNodePropertyToSelectPage = null;
	private List<NodeProperties> updatedNodePropertyListToSelectPage = null;
	private boolean isNodeDeleted = false;
	private CustomMutableTreeNode cutImageParentNode = null;

	// To upload images...
	private ImageUploader imageUploader = new ImageUploader(this);

	/**
	 * Map containing name of images with upload error in value (list). Key of
	 * map denotes image directory path of images.
	 */
	private HashMap<String, ArrayList<String>> uploadErrorImageNameMap = new HashMap<String, ArrayList<String>>();

	// Thumbnail Panel
	private ImageViewer imageViewer;
	private List<NodeProperties> cutNodePropertyList = null;
	private List<Integer> cutNodeIndex = null;

	DocumentScanner documentScanner = null;
	AsyncImageViewer asyncImageViewer = null;

	private JPanel uploadProgressPanel;
	private JLabel uploadProgressLabel;
	private JLabel uploadProgressBar;
	private boolean isCutNodesArePasted = false;

	public ImportSaparationPanel() {

	}

	public ImportSaparationPanel(DesktopMainJPanel desktopMainPanel) {

		this.desktopMainPanel = desktopMainPanel;
		this.setLayout(new GridBagLayout());

		setController(new ImportSaparationPanelController());

		initImportSaparationPanel();
		initHandlers();

	}

	private void initImportSaparationPanel() {
		initSearchPanel();
		initButtonPanel();
		setButtonCaptions();
		setButtonTooltip();
		setLabelCaption();

		initMainPanel();
	}

	private void initSearchPanel() {
		searchPanel = new JPanel();
		searchPanelLayout = new GridBagLayout();

		labelPanel = new JPanel();
		labelPanelLayout = new GridBagLayout();

		JLabel tempLabel = new JLabel();
		tempLabel.setText("	   ");

		barcode = new JLabel();

		barcodeField = new EdittedTextField(20);
		barcodeField.setPreferredSize(new Dimension(150, 20));
		barcodeField.setMinimumSize(new Dimension(150, 20));
		barcodeField.setMaximumSize(new Dimension(150, 20));

		searchButton = new JButton();
		searchButton.setIcon(IconRepository.SEARCH_ICON);

		helpButton = new JButton();
		helpButton.setIcon(IconRepository.HELP_ICON);

		importKPI = new JLabel();
		separationKPI = new JLabel();
		scannedPage = new JLabel();

		searchPanel.setLayout(searchPanelLayout);
		labelPanel.setLayout(labelPanelLayout);

		searchPanel.add(barcode, new GridBagConstraints(0, 0, 1, 0, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,
						0, 0, 5), 0, 0));

		searchPanel.add(barcodeField, new GridBagConstraints(1, 0, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 5), 0, 0));

		searchPanel.add(searchButton, new GridBagConstraints(2, 0, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));

		searchPanel.add(importKPI, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,
						45, 0, 0), 0, 0));

		searchPanel.add(helpButton, new GridBagConstraints(4, 0, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(0, 5, 0, 0), 0, 0));

		labelPanel.add(scannedPage, new GridBagConstraints(0, 0, 1, 1, 0.0,
				0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0));

		labelPanel.add(tempLabel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 20), 0, 0));

		labelPanel.add(separationKPI, new GridBagConstraints(2, 0, 1, 1, 0.0,
				0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(0, 15, 0, 0), 0, 0));

	}

	private void initButtonPanel() {

		String hasImportRights = SessionUtil.getSessionData().getImportRight();
		String hasSeparationRights = SessionUtil.getSessionData()
				.getSeparationRight();

		buttonPanel = new JPanel();

		buttonPanelGroup1 = new JPanel();
		buttonPanelGroup2 = new JPanel();
		buttonPanelGroup3 = new JPanel();

		buttonPanelLayout = new GridBagLayout();

		// Button Group 1
		createDocumentButton = new JButton();
		createDocumentButton.setIcon(IconRepository.ADD_ICON);

		deleteDocumentButton = new JButton();
		deleteDocumentButton.setIcon(IconRepository.ICON_DELETE);

		viewThumbnailsButton = new JButton();
		viewThumbnailsButton.setIcon(IconRepository.ICON_VIEW_THUMBNAIL);

		editNodePropertyButton = new JButton();
		editNodePropertyButton.setIcon(IconRepository.ICON_CHANGE);

		// Button Group 2
		importButton = new JButton();
		importButton.setIcon(IconRepository.ICON_IMPORT);

		splitButton = new JButton();
		splitButton.setIcon(IconRepository.ICON_SPLIT);

		cutButton = new JButton();
		cutButton.setIcon(IconRepository.ICON_CUT);

		pasteButton = new JButton();
		pasteButton.setIcon(IconRepository.ICON_PASTE);

		cancelButton = new JButton();
		cancelButton.setIcon(IconRepository.CANCEL_ICON);

		reverseButton = new JButton();
		reverseButton.setIcon(IconRepository.ICON_REVERSE);

		resumeImportButton = new JButton();
		resumeImportButton.setIcon(IconRepository.ICON_IMPORT);

		deletePageButton = new JButton();
		deletePageButton.setIcon(IconRepository.ICON_DELETE);

		uploadProgressPanel = new JPanel();

		uploadProgressLabel = new JLabel("");
		uploadProgressLabel.setText(ConstantUtil
				.getApplicationConstant("uploadImageInProgress"));

		uploadProgressBar = new JLabel("");
		uploadProgressBar.setIcon(IconRepository.UPLOAD_PROGRESS);

		buttonPanel.setLayout(buttonPanelLayout);

		buttonPanelGroup1.setLayout(buttonPanelLayout);
		buttonPanelGroup1.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));

		buttonPanelGroup2.setLayout(buttonPanelLayout);
		buttonPanelGroup2.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));

		buttonPanelGroup3.setLayout(buttonPanelLayout);
		buttonPanelGroup3.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));

		// Condition To Check If User Has Separation Rights
		if (("Y").equalsIgnoreCase(hasSeparationRights)) {
			buttonPanelGroup1.add(createDocumentButton,
					new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
							GridBagConstraints.NORTH,
							GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2,
									2), 0, 0));
			buttonPanelGroup1.add(deleteDocumentButton,
					new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
							GridBagConstraints.NORTH,
							GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2,
									2), 0, 0));
		}

		buttonPanelGroup1.add(viewThumbnailsButton, new GridBagConstraints(0,
				1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
		buttonPanelGroup1.add(editNodePropertyButton, new GridBagConstraints(1,
				1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

		buttonPanelGroup2.add(importButton, new GridBagConstraints(0, 0, 1, 1,
				0.0, 0.0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

		buttonPanelGroup2.add(resumeImportButton, new GridBagConstraints(1, 0,
				1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

		uploadProgressPanel.add(uploadProgressLabel);
		uploadProgressPanel.add(uploadProgressBar);

		buttonPanelGroup2.add(uploadProgressPanel, new GridBagConstraints(0, 1,
				2, 1, 0.0, 0.0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

		uploadProgressPanel.setVisible(false);

		// Condition To Check If User Has Separation Rights
		if (("Y").equalsIgnoreCase(hasSeparationRights)) {
			buttonPanelGroup3.add(splitButton,
					new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
							GridBagConstraints.NORTH,
							GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2,
									2), 0, 0));
		}

		// Condition To Check If User Has Separation Rights
		if (("Y").equalsIgnoreCase(hasImportRights)) {
			buttonPanelGroup3.add(reverseButton,
					new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
							GridBagConstraints.NORTH,
							GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2,
									2), 0, 0));
			buttonPanelGroup3.add(deletePageButton,
					new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
							GridBagConstraints.NORTH,
							GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2,
									2), 0, 0));
		}

		buttonPanelGroup3.add(cutButton, new GridBagConstraints(0, 1, 1, 1,
				0.0, 0.0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
		buttonPanelGroup3.add(pasteButton, new GridBagConstraints(1, 1, 1, 1,
				0.0, 0.0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
		buttonPanelGroup3.add(cancelButton, new GridBagConstraints(2, 1, 1, 1,
				0.0, 0.0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

		buttonPanel.add(buttonPanelGroup1, new GridBagConstraints(0, 0, 1, 1,
				1.0, 1.0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

		// Condition To Check If User Has Import Rights
		if (("Y").equalsIgnoreCase(hasImportRights)) {
			buttonPanel.add(buttonPanelGroup2, new GridBagConstraints(1, 0, 1,
					1, 1.0, 1.0, GridBagConstraints.NORTH,
					GridBagConstraints.HORIZONTAL, new Insets(0, 20, 0, 0), 0,
					0));
		}

		buttonPanel.add(buttonPanelGroup3, new GridBagConstraints(2, 0, 1, 1,
				1.0, 1.0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(0, 20, 0, 0), 0, 0));

	}

	private void initThumbnailPanel(final CustomMutableTreeNode selectedNode)
			throws Exception {

		if (imageViewer != null && imageViewer.getFullScreenDialog() != null) {
			imageViewer.getFullScreenDialog().hide();
			imageViewer.getFullScreenDialog().dispose();
		}

		// Remove Image From Local Directory
		log.info("Inititalize Thumbnai Panel -> Local Directory Path To Store Full Screen Images : "
				+ SessionUtil.getSessionData().getLocalThumbnailDirPath());
		File f = new File(SessionUtil.getSessionData()
				.getLocalThumbnailDirPath());
		if (f.exists()) {
			FileUtils.cleanDirectory(f);
		}

		log.info("Inititalize Thumbnai Panel -> Selected Node Id : "
				+ selectedNode.getNodeId());

		if (selectedNode == null)
			return;

		// Condition To Check That Node Has Child Node Or Not...
		int childNodeCount = controller().getChildNodeCount(
				selectedNode.getNodeId());

		// Condition To Check If Any Error Generated On Server Side Then Return
		if (childNodeCount < 0)
			return;

		if (childNodeCount == 0) {
			// Condition To Check If Delete Operation is Performed And Node Has
			// no Pages Then Remove ImageViewer View/ Thumbnail View
			if (isNodeDeleted() == true) {
				setNodeDeleted(false);
				setLastSelectedPageIndexeList(null);
				imageViewer.getThumbnailList().removeAll();
				getMainPanel().remove(imageViewer);
				imageViewer = null;
				getMainPanel().revalidate();
				getMainPanel().repaint();
			}
			// Flag Became False
			// If User Cut Page And Paste On Destination Node (After Clicking On
			// ViewThumbnail And Document Has No Pages)
			// Then Source Node imageViewer is not removed
			setNodeDeleted(false);

			ErrorMessage.displayMessage('I', "documentHasNoPages");
			return;
		}

		// Create Local Directory To Store Image/Thumbnail At Local System
		NodeProperties selectedNodeProperty = desktopMainPanel.getjLeftPanel()
				.getNodePropertiesMap().get(selectedNode.getNodeId());

		initImageViewer();

		imageViewer.initHandler();

		// Create Thumbnail Directory
		String localDirectoryPath = SessionUtil.getSessionData()
				.getLocalThumbnailDirPath()
				+ File.separator
				+ NodePropertyConstants.THUMBNAIL_DIR;

		log.info("Inititalize Thumbnai Panel -> Local Directory Path To Store Thumbnails Images : "
				+ localDirectoryPath);

		createLocalDirectory(localDirectoryPath);

		asyncImageViewer = new AsyncImageViewer(localDirectoryPath,
				imageViewer, selectedNode, desktopMainPanel, desktopMainPanel
						.getjRightPanel().getImportAndSepPanel().controller()
						.getImportSaparationPanelHelper());
		asyncImageViewer.execute();
		lastViewedThumbnailNodeProperty = selectedNodeProperty;
	}

	private void initImageViewer() {

		if (imageViewer != null) {
			mainPanel.remove(imageViewer);
		}

		imageViewer = new ImageViewer(desktopMainPanel);

		DefaultListCellRenderer renderer;
		DefaultListModel listModel = new DefaultListModel();

		imageViewer.thumbnailList = new JList(listModel);
		imageViewer.getThumbnailList().setLayoutOrientation(
				JList.HORIZONTAL_WRAP);
		imageViewer.getThumbnailList().setVisibleRowCount(-1);
		imageViewer.getThumbnailList().setFixedCellHeight(110);
		imageViewer.getThumbnailList().setFixedCellWidth(110);
		imageViewer.getThumbnailList().setBackground(Color.WHITE);
		imageViewer.getThumbnailList().setSelectionBackground(
				new Color(165, 210, 216));
		renderer = (DefaultListCellRenderer) imageViewer.getThumbnailList()
				.getCellRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);
		imageViewer.getThumbnailScrollPanel().getViewport()
				.add(imageViewer.getThumbnailList());

		mainPanel.revalidate();

		mainPanel.add(imageViewer, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));

		mainPanel.revalidate();

	}

	private void createLocalDirectory(String localDirectoryPath) {
		File dir = new File(localDirectoryPath);
		dir.mkdirs();
	}

	public List<NodeProperties> fetchChildNodes(
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

	private void initMainPanel() {
		mainPanel = new JPanel();
		mainPanelLayout = new GridBagLayout();
		basePanelLayout = new GridBagLayout();

		mainPanel.setLayout(mainPanelLayout);

		upperPanel = new JPanel();
		upperPanel.setLayout(new GridBagLayout());

		upperPanel.add(searchPanel, new GridBagConstraints(0, 0, 1, 1, 1.0,
				0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(5, 0, 5, 0), 0, 0));

		upperPanel.add(labelPanel, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						0, 0, 10, 0), 0, 0));

		upperPanel.add(new JSeparator(), new GridBagConstraints(0, 2, 1, 1,
				0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));

		upperPanel.add(buttonPanel, new GridBagConstraints(0, 3, 1, 1, 1.0,
				0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(10, 0, 10, 0), 0, 0));

		upperPanel.add(new JSeparator(), new GridBagConstraints(0, 4, 1, 1,
				0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));

		upperPanel.setMaximumSize(new Dimension(0, 150));
		upperPanel.setMinimumSize(new Dimension(0, 150));

		mainPanel.add(upperPanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));

		this.setLayout(basePanelLayout);
		this.add(mainPanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));

		setButtonDisableWhileNodeIsProjectNode();

	}

	private void setButtonTooltip() {
		searchButton.setToolTipText(ConstantUtil
				.getApplicationConstant("searchTT"));
		helpButton.setToolTipText(ConstantUtil
				.getApplicationConstant("helpImportSeparationTT"));

		createDocumentButton.setToolTipText(ConstantUtil
				.getApplicationConstant("createDocumentTT"));
		deleteDocumentButton.setToolTipText(ConstantUtil
				.getApplicationConstant("deleteDocumentTT"));
		viewThumbnailsButton.setToolTipText(ConstantUtil
				.getApplicationConstant("viewThumbnailsTT"));
		editNodePropertyButton.setToolTipText(ConstantUtil
				.getApplicationConstant("editNodePropertyTT"));

		importButton.setToolTipText(ConstantUtil
				.getApplicationConstant("importTT"));
		splitButton.setToolTipText(ConstantUtil
				.getApplicationConstant("splitTT"));
		cutButton.setToolTipText(ConstantUtil
				.getApplicationConstant("cutPageTT"));
		pasteButton.setToolTipText(ConstantUtil
				.getApplicationConstant("pastePageTT"));
		cancelButton.setToolTipText(ConstantUtil
				.getApplicationConstant("cancelTT"));
		reverseButton.setToolTipText(ConstantUtil
				.getApplicationConstant("reverseTT"));
		resumeImportButton.setToolTipText(ConstantUtil
				.getApplicationConstant("resumeImportTT"));
		deletePageButton.setToolTipText(ConstantUtil
				.getApplicationConstant("deletePageTT"));
	}

	private void setLabelCaption() {

		barcode.setText(ConstantUtil.getApplicationConstant("barcode"));

		setImportKPILabel("0");
		setSeparationKPILabel("0");
		setImportPagesLabel(0, 0, 0);
	}

	/**
	 * Method to set provided actual import KPI in label for import KPI.
	 * 
	 * @param actualImportKPI
	 */
	public void setImportKPILabel(String actualImportKPI) {

		String importKPIText = ConstantUtil.getApplicationConstant("importKPI")
				+ "    :  " + actualImportKPI + " / "
				+ SessionUtil.getSessionData().getImportTarget();

		importKPI.setText(importKPIText);
	}

	/**
	 * Method to set provided actual separation KPI in label for separation KPI.
	 * 
	 * @param actualSeparationKPI
	 */
	public void setSeparationKPILabel(String actualSeparationKPI) {

		String separationKPIText = ConstantUtil
				.getApplicationConstant("separationKPI")
				+ "  :  "
				+ actualSeparationKPI
				+ " / "
				+ SessionUtil.getSessionData().getSeparationTarget();

		separationKPI.setText(separationKPIText);
	}

	/**
	 * Method to set provided total scanned pages, total uploaded pages and
	 * total pages with upload error in respective labels.
	 * 
	 * @param totalImportedPages
	 * @param totalUploadedPages
	 * @param totalPagesWithUploadError
	 */
	public void setImportPagesLabel(int totalImportedPages,
			int totalUploadedPages, int totalPagesWithUploadError) {

		String scannedPageText = ConstantUtil
				.getApplicationConstant("totalPages")
				+ " "
				+ totalImportedPages
				+ " |  "
				+ ConstantUtil.getApplicationConstant("savedPages")
				+ " "
				+ totalUploadedPages
				+ " |  "
				+ ConstantUtil.getApplicationConstant("pagesWithError")
				+ " "
				+ totalPagesWithUploadError;

		scannedPage.setText(scannedPageText);
	}

	private void setButtonCaptions() {
		searchButton.setText(ConstantUtil.getApplicationConstant("search"));
		helpButton.setText(ConstantUtil.getApplicationConstant("help"));

		createDocumentButton.setText(ConstantUtil
				.getApplicationConstant("createDocument"));
		deleteDocumentButton.setText(ConstantUtil
				.getApplicationConstant("deleteDocument"));
		viewThumbnailsButton.setText(ConstantUtil
				.getApplicationConstant("viewThumbnails"));
		editNodePropertyButton.setText(ConstantUtil
				.getApplicationConstant("editNodeProperty"));

		importButton.setText(ConstantUtil.getApplicationConstant("import"));
		splitButton.setText(ConstantUtil.getApplicationConstant("split"));
		cutButton.setText(ConstantUtil.getApplicationConstant("cut"));
		pasteButton.setText(ConstantUtil.getApplicationConstant("paste"));
		cancelButton.setText(ConstantUtil.getApplicationConstant("cancel"));
		reverseButton.setText(ConstantUtil.getApplicationConstant("reverse"));
		resumeImportButton.setText(ConstantUtil
				.getApplicationConstant("resumeImport"));
		deletePageButton.setText(ConstantUtil
				.getApplicationConstant("deletePage"));

	}

	private void initHandlers() {
		searchButton.addActionListener((ActionListener) this);
		helpButton.addActionListener((ActionListener) this);
		createDocumentButton.addActionListener((ActionListener) this);
		deleteDocumentButton.addActionListener((ActionListener) this);
		viewThumbnailsButton.addActionListener((ActionListener) this);
		editNodePropertyButton.addActionListener((ActionListener) this);
		importButton.addActionListener((ActionListener) this);
		resumeImportButton.addActionListener((ActionListener) this);
		splitButton.addActionListener((ActionListener) this);
		cutButton.addActionListener((ActionListener) this);
		pasteButton.addActionListener((ActionListener) this);
		cancelButton.addActionListener((ActionListener) this);
		reverseButton.addActionListener((ActionListener) this);
		deletePageButton.addActionListener((ActionListener) this);
		barcodeField.addKeyListener((KeyListener) this);

	}

	public ImportSaparationPanelController controller() {
		return (ImportSaparationPanelController) getController();
	}

	public DesktopMainJPanel getDesktopMainPanel() {
		return desktopMainPanel;
	}

	public void setDesktopMainPanel(DesktopMainJPanel desktopMainPanel) {
		this.desktopMainPanel = desktopMainPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == searchButton) {
			searchNodeFromBarcode();
		}
		if (e.getSource() == helpButton) {
			HelpPopup help = new HelpPopup();
			int selectedTab = desktopMainPanel.getjRightPanel().getTabbedPane()
					.getSelectedIndex();
			help.setHelpText(selectedTab);
		}

		// In case the node is deselected (by Ctrl + Click), selected node will
		// not be found and hence return from method.
		JTree nodeTree = desktopMainPanel.getjLeftPanel().getNodeTree();
		if (nodeTree.getSelectionPath() == null
				|| nodeTree.getSelectionPath().getLastPathComponent() == null) {
			ErrorMessage.displayMessage('I', "pleaseSelectNode");
			return;
		}

		// Condition To Check That If View Thumbnail Is In Progress Or Not
		boolean isViewThumbnailIsInProgress = isViewThumbnailInProgress(e
				.getSource());
		if (isViewThumbnailIsInProgress) {
			return;
		}
		boolean isImportOrResumeImportInProgress = isImportOrResumeImportInProgress(e
				.getSource());
		if (isImportOrResumeImportInProgress) {
			return;
		}

		if (e.getSource() == createDocumentButton) {

			NodeProperties newSavedNodeProperty = createDocument();

			if (newSavedNodeProperty != null)
				log.info("Create Document -> Newly Added Node Id: "
						+ newSavedNodeProperty.getNodeId());
			else
				log.info("Create Document -> Newly Added Dummy Node Id Return Null. Node Is Not Added To Database. Trying To Add Node In Done/Sealed Status or Added To Document Node");
		}

		CustomMutableTreeNode selectedNode = desktopMainPanel.getjRightPanel()
				.getCollectionPanel().getSelectedTreeNode();

		if (e.getSource() == importButton) {
			importImageFromScanner(selectedNode);
		}
		if (e.getSource() == resumeImportButton) {
			resumeImport(selectedNode);
		}
		if (e.getSource() == deleteDocumentButton) {
			if (selectedNode.getNodeId().equalsIgnoreCase(
					SessionUtil.getSessionData().getProjectId())) {
				ErrorMessage.displayMessage('I', "rootNodeNotAllowToDelete");
				return;
			}

			// Check That Node Is Not Container Or Box
			NodeProperties selectedNodeProperty = desktopMainPanel
					.getjLeftPanel().getNodePropertiesMap()
					.get(selectedNode.getNodeId());

			if (selectedNodeProperty.getType().equals(
					NodePropertyConstants.CONTAINER)
					|| selectedNodeProperty.getType().equals(
							NodePropertyConstants.BOX)) {
				ErrorMessage.displayMessage('I',
						"notAllowToDeleteContainerOrBoxNode");
				return;
			}

			// Condition TO Check Only One Document is Selected For Delete
			int totalDocumentSelectedToDelete = desktopMainPanel
					.getjLeftPanel().getNodeTree().getSelectionRows().length;
			log.info("Delete Documents -> Total Selected Node To Delete: "
					+ totalDocumentSelectedToDelete);
			if (totalDocumentSelectedToDelete > 1) {
				ErrorMessage.displayMessage('I',
						"pleaseSelectOnlyOneNodeToDelete");
				return;
			}

			// Condition To Check before Delete Document, Is The Node is
			// going to delete is Previouly Updated By Other Users Or Not.
			Map<String, String> nodeUpdateTimeMap = new HashMap<String, String>();
			nodeUpdateTimeMap.put(selectedNodeProperty.getNodeId(),
					selectedNodeProperty.getLastUpdateDateTime());

			if (isNodeUpdatedByOtherUser(nodeUpdateTimeMap)) {
				ErrorMessage.displayMessage('I',
						"updatedByOtherUserPleaseRefreshTree");
				return;
			}

			// Check That Node Is Not In In Progress Status
			if ((selectedNodeProperty.getStatus()
					.equals(NodePropertyConstants.IN_PROGRESS))) {
				// Condition To Check If Document has Pages Then Not Allow
				// To Delete
				List<NodeProperties> childNodePropertyList = fetchChildNodes(selectedNode);
				if (childNodePropertyList != null) {
					if (!childNodePropertyList.isEmpty()) {
						ErrorMessage.displayMessage('I', "deleteNodeWithChild");
						return;
					} else if (childNodePropertyList.size() == 0) {
						desktopMainPanel.getjRightPanel().getCollectionPanel()
								.deleteNode();
					}
				}

			} else {
				ErrorMessage.displayMessage('I',
						"nodeIsInDoneOrSealedStatusNotAllowToDelete");
				return;
			}

		}

		if (e.getSource() == viewThumbnailsButton) {

			// Check If Selected Node Is Project Node/ Root Node
			if (selectedNode.getNodeId().equalsIgnoreCase(
					SessionUtil.getSessionData().getProjectId())) {
				ErrorMessage.displayMessage('I', "selectDocumentNode");
				return;
			}

			try {
				initThumbnailPanel(selectedNode);
			} catch (Exception exception) {
				log.debug("Exception Occurs While Trying To Initialize Thumbnail Panel "
						+ exception);
				exception.printStackTrace();
			}
		}
		if (e.getSource() == editNodePropertyButton) {
			// Check If Selected Node Is Project Node/ Root Node
			if (selectedNode.getNodeId().equalsIgnoreCase(
					SessionUtil.getSessionData().getProjectId())) {
				ErrorMessage.displayMessage('I', "rootNodeNotAllowToEdit");
				return;
			} else {
				// Check If Selected Node Is In InProgress Status or In Done
				// Status
				NodeProperties selectedNodeProperty = desktopMainPanel
						.getjLeftPanel().getNodePropertiesMap()
						.get(selectedNode.getNodeId());

				if (selectedNodeProperty.getStatus().equals(
						NodePropertyConstants.IN_PROGRESS)
						|| selectedNodeProperty.getStatus().equals(
								NodePropertyConstants.DONE)) {
					// Condition To Check Thet Selected Node Is Box/Container
					// And Is In Done Status
					// Then Not Allowed To Edit Property
					if (!selectedNodeProperty.getType().equalsIgnoreCase(
							NodePropertyConstants.DOCUMENT)
							&& selectedNodeProperty.getStatus().equals(
									NodePropertyConstants.DONE)) {
						ErrorMessage.displayMessage('I',
								"onlyDocumentNodeAllowedToEditInDoneStatus");
						return;
					} else {

						String hasImportRights = SessionUtil.getSessionData()
								.getImportRight();
						String hasSeparationRights = SessionUtil
								.getSessionData().getSeparationRight();

						if (("Y").equalsIgnoreCase(hasImportRights)
								&& !("Y").equalsIgnoreCase(hasSeparationRights)
								&& selectedNodeProperty.getStatus().equals(
										NodePropertyConstants.DONE)) {
							ErrorMessage.displayMessage('I',
									"notAllowToEditNodeInDoneStatus");
							return;
						}
						// Condition To Check before Delete Document,
						// Is The Node is going to delete is Previouly
						// Updated By Other Users Or Not.
						Map<String, String> nodeUpdateTimeMap = new HashMap<String, String>();
						nodeUpdateTimeMap.put(selectedNodeProperty.getNodeId(),
								selectedNodeProperty.getLastUpdateDateTime());

						if (isNodeUpdatedByOtherUser(nodeUpdateTimeMap)) {
							ErrorMessage.displayMessage('I',
									"updatedByOtherUserPleaseRefreshTree");
							return;
						}

						EditNodePropertyPopup editPopup = new EditNodePropertyPopup(
								desktopMainPanel);
					}
				} else {
					ErrorMessage.displayMessage('I',
							"nodeIsInSealedStatusNotAllowToEdit");
					return;
				}
			}
		}

		if (e.getSource() == cancelButton) {
			cancelCutNodes(selectedNode);
		}

		if (e.getSource() == splitButton) {
			try {
				splitNodes(selectedNode);
			} catch (Exception err) {
				log.error("Error while Split Nodes");
				log.error("Exception : " + err);
			}
		}
		if (e.getSource() == cutButton) {
			cutNodes(selectedNode);
		}

		if (e.getSource() == pasteButton) {
			try {
				pasteNodes(selectedNode);
			} catch (Exception err) {
				log.error("Error while Paste Nodes");
				log.error("Exception : " + err);
			}
		}

		if (e.getSource() == reverseButton) {
			if (isContinuousListSelection()) {
				try {
					reverseNodes(selectedNode);
				} catch (Exception err) {
					log.error("Error While Reverse Page");
					log.error("Exception : " + err);
				}

			} else {
				ErrorMessage.displayMessage('I',
						"selectPagesInSequenceToReverse");
				return;
			}
		}
		if (e.getSource() == deletePageButton) {
			try {
				deletePage(selectedNode);
			} catch (Exception err) {
				log.error("Error while delete Page");
				log.error("Exception : " + err);
			}
		}
	}

	private boolean isImportOrResumeImportInProgress(Object source) {
		boolean isImportOrResumeImportInProgress = false;

		if ((documentScanner != null && documentScanner
				.isImageScanningInProcess())
				|| (imageUploader != null && (imageUploader.isImportInProcess() || imageUploader
						.isResumeImportInProcess()))) {

			if (source == importButton) {

				// Clicking On Import Will Not Be Allowed If Document Scanning
				// Or Resume Import Is In Process. But If Doc. Scanning Is Over
				// And Import Is In Process, Clicking On Import Is Allowed.
				if ((documentScanner != null && documentScanner
						.isImageScanningInProcess())
						|| (imageUploader != null && imageUploader
								.isResumeImportInProcess())) {
					ErrorMessage
							.displayMessage('I',
									"cannotImportImageStillImageScanningOrResumeImportInProgress");
					isImportOrResumeImportInProgress = true;
				}
			}

			else if (source == cutButton) {
				ErrorMessage.displayMessage('I',
						"cannotCutImageStillUploadingInProgress");
				isImportOrResumeImportInProgress = true;
			}

			else if (source == pasteButton) {
				ErrorMessage.displayMessage('I',
						"cannotPasteImageStillUploadingInProgress");
				isImportOrResumeImportInProgress = true;
			}

			else if (source == splitButton) {
				ErrorMessage.displayMessage('I',
						"cannotSplitImageStillUploadingInProgress");
				isImportOrResumeImportInProgress = true;
			}

			else if (source == reverseButton) {
				ErrorMessage.displayMessage('I',
						"cannotReverseImageStillUploadingInProgress");
				isImportOrResumeImportInProgress = true;
			}

			else if (source == deletePageButton) {
				ErrorMessage.displayMessage('I',
						"cannotDeletePageImageStillUploadingInProgress");
				isImportOrResumeImportInProgress = true;
			}

			else if (source == resumeImportButton) {
				ErrorMessage.displayMessage('I',
						"cannotResumeImportImageStillUploading");
				isImportOrResumeImportInProgress = true;
			}

			else if (source == viewThumbnailsButton) {
				ErrorMessage.displayMessage('I',
						"cannotShowThumbnailImageStillUploading");
				isImportOrResumeImportInProgress = true;
			}

			else if (source == editNodePropertyButton) {
				ErrorMessage.displayMessage('I',
						"cannotEditNodePropertyImageStillUploading");
				isImportOrResumeImportInProgress = true;
			}

			else if (source == deleteDocumentButton) {
				ErrorMessage.displayMessage('I',
						"cannotDeleteDocumentNodeImageStillUploading");
				isImportOrResumeImportInProgress = true;
			}

		}

		return isImportOrResumeImportInProgress;
	}

	private boolean isViewThumbnailInProgress(Object source) {
		boolean isViewThumbnailInProgress = false;

		// Condition To Check That View Thumbnail Is In Progress Or Not.
		// isAllowedToViewThumbnail() Method Check that displayed thumbnail is
		// same as Total thumbnail or Not
		if (asyncImageViewer != null
				&& !asyncImageViewer.isAllowedToViewThumbnail()) {
			if (source == cutButton) {
				ErrorMessage.displayMessage('I',
						"cannotCutViewThumbnailInProgress");
				isViewThumbnailInProgress = true;
			}
			if (source == pasteButton) {
				ErrorMessage.displayMessage('I',
						"cannotPasteViewThumbnailInProgress");
				isViewThumbnailInProgress = true;
			}
			if (source == splitButton) {
				ErrorMessage.displayMessage('I',
						"cannotSplitViewThumbnailInProgress");
				isViewThumbnailInProgress = true;
			}
			if (source == reverseButton) {
				ErrorMessage.displayMessage('I',
						"cannotReverseViewThumbnailInProgress");
				isViewThumbnailInProgress = true;
			}
			if (source == deletePageButton) {
				ErrorMessage.displayMessage('I',
						"cannotDeletePageViewThumbnailInProgress");
				isViewThumbnailInProgress = true;
			}
			if (source == editNodePropertyButton) {
				ErrorMessage.displayMessage('I',
						"cannotEditNodePropertyViewThumbnailInProgress");
				isViewThumbnailInProgress = true;
			}
			if (source == deleteDocumentButton) {
				ErrorMessage.displayMessage('I',
						"cannotDeleteDocumentNodeViewThumbnailInProgress");
				isViewThumbnailInProgress = true;
			}
		}
		return isViewThumbnailInProgress;
	}

	private boolean isNodeUpdatedByOtherUser(
			Map<String, String> nodeUpdateTimeMap) {
		return controller().checkIfNodeIsUpdatedByOtherUser(nodeUpdateTimeMap);
	}

	/**
	 * Method To Check ListBox Selected Pages/ Thumbnails Are In Continuous
	 * Range or Random
	 */
	private boolean isContinuousListSelection() {

		int[] indicesList = imageViewer.getThumbnailList().getSelectedIndices();
		int firstIndex = indicesList[0];
		int lastIndex = indicesList[indicesList.length - 1];
		int size = indicesList.length;

		// Condition To Check Continuous Selection Or Random Selection.
		if (((lastIndex - firstIndex) + 1) == size) {
			// Continuous Selection
			return true;
		} else {
			// Random Selection
			return false;
		}

	}

	private void splitNodes(CustomMutableTreeNode selectedNode)
			throws Exception {

		CustomMutableTreeNode parentNode = (CustomMutableTreeNode) selectedNode
				.getParent();
		log.info("Split Node -> Parent Node Id: " + parentNode.getNodeId());

		List<NodeProperties> selectedNodePropertyList = getSelectedPagesOrNodes();

		if (selectedNodePropertyList != null
				&& !selectedNodePropertyList.isEmpty()) {
			if (selectedNodePropertyList.size() > 1) {
				ErrorMessage.displayMessage('I', "selectOnlyOnePageToSplit");
				return;
			} else {
				// Condition To Check that If Selected Nodes Are updated by
				// Other Users Then Not Allow To Split Nodes
				boolean isSelectedNodesUpdatedByOtherUser = isNodeUpdatedByOtherUser(checkNodeIsUpdatedByOtherUserOrNot(selectedNodePropertyList));
				if (isSelectedNodesUpdatedByOtherUser) {
					ErrorMessage.displayMessage('I',
							"updatedByOtherUserPleaseRefreshTree");
					return;
				}

				JTree nodeTree = desktopMainPanel.getjLeftPanel().getNodeTree();
				nodeTree.setSelectionPath(new TreePath(parentNode.getPath()));

				NodeProperties newSavedNodeProperty = createDocument();
				if (newSavedNodeProperty != null) {
					log.info("Split Node -> Newly Added Node Id: "
							+ newSavedNodeProperty.getNodeId());

					NodeProperties selectedNodeFromWhereToSplit = selectedNodePropertyList
							.get(0);
					List<NodeProperties> nodePropertyList = getChildNodeListIncludingSelectedNodes(selectedNodeFromWhereToSplit);

					// Method To Update Cut Nodes Parent ID To Newly Added Node
					List<NodeProperties> updatedNodePropertyList = updateNodePropertyListFromNodeProperty(
							nodePropertyList, newSavedNodeProperty);

					updateNodePropertyOperationToDatabase(updatedNodePropertyList);
					refreshThumbnailPanelWithCurrentSelectedNode();

				} else {
					log.info("Split Node -> Newly Added Dummy Node Id Return Null. Node Is Not Added To Database. Trying To Add Node In Done/Sealed Status or Added To Document Node");
				}
			}
		}
	}

	private Map<String, String> checkNodeIsUpdatedByOtherUserOrNot(
			List<NodeProperties> selectedNodePropertyList) {
		// Condition To Check before Delete Document,
		// Is The Node is going to delete is Previouly Updated By Other Users Or
		// Not.
		Map<String, String> nodeUpdateTimeMap = new HashMap<String, String>();
		for (int index = 0; index < selectedNodePropertyList.size(); index++) {
			if (selectedNodePropertyList.get(index).getTransactionStatus() != TransactionConstant.INSERT) {
				nodeUpdateTimeMap.put(selectedNodePropertyList.get(index)
						.getNodeId(), selectedNodePropertyList.get(index)
						.getLastUpdateDateTime());
			}
		}
		return nodeUpdateTimeMap;
	}

	private ArrayList<NodeProperties> updateDocumentSequenceNumberForDeletePages(
			CustomMutableTreeNode selectedNode,
			List<NodeProperties> selectedNodePropertyListToDelete,
			List<Integer> deletePageIndex) {
		// Get Total Pages For Selected Source Nodes
		ArrayList<String> idList = new ArrayList<String>();
		String projectId = SessionUtil.getSessionData().getProjectId();
		idList.add(projectId);
		idList.add(selectedNode.getNodeId());
		List<NodeProperties> totalPagesForSelectedSourceDocument = controller()
				.fetchChildNodeList(idList);

		ArrayList<NodeProperties> pageNodePropertiesToDelete = updateDocumentSequenceNumber(
				(ArrayList<NodeProperties>) totalPagesForSelectedSourceDocument,
				(ArrayList<NodeProperties>) selectedNodePropertyListToDelete,
				(ArrayList<Integer>) deletePageIndex, -1);

		return pageNodePropertiesToDelete;
	}

	private void updateNodePropertyOperationToDatabase(
			List<NodeProperties> updatedNodePropertyList) {

		for (int i = 0; i < updatedNodePropertyList.size(); i++) {

			NodeProperties nodeProperties = updatedNodePropertyList.get(i);

			nodeProperties.setEncodeStringForThumbnail(null);
		}

		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		controller().uploadScannedImages(
				(ArrayList<NodeProperties>) updatedNodePropertyList);

		this.setCursor(Cursor.getDefaultCursor());
	}

	private void deletePage(CustomMutableTreeNode selectedNode)
			throws Exception {
		// log.info("Delete Page -> Delete Page for Project Id : " +
		// SessionUtil.getSessionData().getProjectId());

		int selectedValue = ErrorMessage.displayMessage('Q',
				"deletePageNodeConfirmation");

		if (selectedValue == JOptionPane.NO_OPTION)
			return;

		// Added Flag For Refreshing The View After Bulk Delete And Document Has
		// no Pages
		isNodeDeleted = true;

		List<NodeProperties> selectedNodePropertyList = getSelectedPagesOrNodes();
		log.info("Delete Page -> Total Pages : "
				+ selectedNodePropertyList.size());
		if (selectedNodePropertyList != null
				&& !selectedNodePropertyList.isEmpty()) {

			// Condition To Check that If Selected Nodes Are updated by Other
			// Users Then Not Allow To Delete Nodes
			boolean isSelectedNodesUpdatedByOtherUser = isNodeUpdatedByOtherUser(checkNodeIsUpdatedByOtherUserOrNot(selectedNodePropertyList));
			if (isSelectedNodesUpdatedByOtherUser) {
				ErrorMessage.displayMessage('I',
						"updatedByOtherUserPleaseRefreshTree");
				isNodeDeleted = true;
				return;
			}

			deletePageIndex = new ArrayList<Integer>(
					selectedNodePropertyList.size());
			JList selectedPagesList = imageViewer.getThumbnailList();

			for (int index = 0; index < selectedNodePropertyList.size(); index++) {
				selectedNodePropertyList.get(index).setTransactionStatus(
						TransactionConstant.DELETE);
				deletePageIndex
						.add(selectedPagesList.getSelectedIndices()[index]);
			}

			List<NodeProperties> nodePropertyListToUpdateAfterDelete = updateDocumentSequenceNumberForDeletePages(
					selectedNode, selectedNodePropertyList, deletePageIndex);

			// updateNodePropertyOperationToDatabase(selectedNodePropertyList);
			updateNodePropertyOperationToDatabase(nodePropertyListToUpdateAfterDelete);

			// get the current divider location (pixels from left edge)
			int dividerLocation = imageViewer.getSplitPanel()
					.getDividerLocation();

			refreshThumbnailPanelWithCurrentSelectedNode();

			// Method Called From AsyncImageViewer.java
			// setSelectedPageAfterDelete(deletePageIndex, selectedNode);

			// set new divider location
			if (imageViewer != null) {
				imageViewer.getSplitPanel().setDividerLocation(dividerLocation);
			}
		}
	}

	File file;
	Image localImage;

	// Method Called From AsyncImageViewer.java
	public void setSelectedPageAfterDelete(List<Integer> deletePageIndex,
			CustomMutableTreeNode selectedNode) {

		if (imageViewer == null) {
			log.info("Delete Page -> Image Viewer Is Null, no Index Is Going To Select");
			isNodeDeleted = false;
			return;
		}

		// Condition TO Check That If All The Pages Are Selected At A Time And
		// Remove Then Remove The Thumbnail Panel
		if (imageViewer.getThumbnailList().getModel().getSize() == 1) {
			int nodePropertyListCount = controller().getChildNodeCount(
					selectedNode.getNodeId());
			// Condition TO Check Node Has A Child / Pages TO Display
			if (nodePropertyListCount <= 0) {
				setNodeDeleted(false);
				setLastSelectedPageIndexeList(null);
				imageViewer.getThumbnailList().removeAll();
				mainPanel.remove(imageViewer);
				imageViewer = null;
				mainPanel.revalidate();
				mainPanel.repaint();
			}
		}

		// Condition To Check That Only One Page Is Going To Deleted
		if (deletePageIndex.size() == 1) {
			int setSelectedIndex = -1;
			int deletedPageIndex = deletePageIndex.get(0);
			int totalItemsInList = imageViewer.getThumbnailList().getModel()
					.getSize();

			// Condition To Check that If Selected Index is 0 And List Item has
			// Only Two Pages Then Select Page at Position 0 Mean Select First
			// Page Which Is At Index 0
			if ((deletedPageIndex == 0 && totalItemsInList > 0)
					|| deletedPageIndex < (totalItemsInList - 1)
					|| deletedPageIndex == (totalItemsInList - 1)) {
				log.info("Delete Page -> First Page Selected After Delete");
				log.info("Index Selected: " + deletePageIndex.get(0));
				setSelectedIndex = deletePageIndex.get(0);

			}
			// Condition to check that If Selected Only One Page
			// And Selected page Is Last Page Then Select Previous Page
			else if (deletedPageIndex > totalItemsInList - 1) {
				log.info("Delete Page -> Previous Page Selected After Delete");
				log.info("Index Selected: " + (deletePageIndex.get(0) - 1));
				setSelectedIndex = (deletePageIndex.get(0) - 1);
			} else {
				log.info("Empty");
			}

			showSelectedThumbnailView(setSelectedIndex, selectedNode);

		}
		// Condition To Check That If Multiple Pages Are Selected
		else if (deletePageIndex.size() > 1) {
			int setSelectedIndex = -1;
			int totalItemsInList = imageViewer.getThumbnailList().getModel()
					.getSize();

			// Condition To Check That If User Delete Pages At The End Then
			// Select Previous Page
			if (deletePageIndex.get(0) > (totalItemsInList - 1)) {
				log.info("Select Previous Page");
				setSelectedIndex = deletePageIndex.get(0) - 1;
				showSelectedThumbnailView(setSelectedIndex, selectedNode);
			} else {
				log.info("Select Next Page");
				setSelectedIndex = deletePageIndex.get(0);
				showSelectedThumbnailView(setSelectedIndex, selectedNode);
			}
			// Condition To Check That If User Delete Pages At The End Then
			// Select Previous Page

		}

	}

	private void showSelectedThumbnailView(int setSelectedIndex,
			CustomMutableTreeNode selectedNode) {
		imageViewer.getThumbnailList().setSelectedIndex(setSelectedIndex);
		imageViewer.getThumbnailList().ensureIndexIsVisible(setSelectedIndex);

		Object value = imageViewer
				.getThumbnailList()
				.getModel()
				.getElementAt(imageViewer.getThumbnailList().getSelectedIndex());
		log.info("Delet Page -> Image Path To Set Right Side After Delete Page : "
				+ ((CustomImageIcon) value).getActualImagePath());

		try {
			imageViewer.viewLargeThumbnailImage(value, file, localImage);
		} catch (IOException e) {
			e.printStackTrace();
		}

		NodeProperties selectedNodeProperty = desktopMainPanel.getjLeftPanel()
				.getNodePropertiesMap().get(selectedNode.getNodeId());
		desktopMainPanel.getjLeftPanel().enableDisableImportSeparationButtons(
				selectedNodeProperty);
	}

	private void refreshThumbnailPanelWithCurrentSelectedNode()
			throws Exception {
		CustomMutableTreeNode selectedNode = desktopMainPanel.getjRightPanel()
				.getCollectionPanel().getSelectedTreeNode();
		initThumbnailPanel(selectedNode);
		NodeProperties selectedNodeProperty = desktopMainPanel.getjLeftPanel()
				.getNodePropertiesMap().get(selectedNode.getNodeId());
		desktopMainPanel.getjLeftPanel().enableDisableImportSeparationButtons(
				selectedNodeProperty);
	}

	/**
	 * Method to update Node Property List From Node Property
	 * 
	 * @param childNodeListToUpdate
	 *            - List Of Child Node To Update
	 * @param parentNode
	 *            - Node Property From Which Child Node Is Going To Be Updat
	 */
	private List<NodeProperties> updateNodePropertyListFromNodeProperty(
			List<NodeProperties> childNodeListToUpdate,
			NodeProperties parentNode) {
		List<NodeProperties> updatedNodePropertyList = new ArrayList<NodeProperties>(
				childNodeListToUpdate.size());

		for (int index = 0; index < childNodeListToUpdate.size(); index++) {
			NodeProperties childNode = childNodeListToUpdate.get(index);

			String imageDirectoryPath = getImageDirectoryPath();

			childNode.setParentNodeId(parentNode.getNodeId());
			childNode.setActualImageName(imageDirectoryPath
					+ childNode.getName());
			childNode
					.setThumbnailImageName(imageDirectoryPath
							+ NodePropertyConstants.THUMBNAIL_DIR
							+ childNode.getName());
			childNode.setUserId(SessionUtil.getSessionData().getUserId());
			childNode.setTransactionStatus(TransactionConstant.UPDATE);

			updatedNodePropertyList.add(childNode);
		}

		return updatedNodePropertyList;
	}

	private void reverseNodes(CustomMutableTreeNode selectedNode)
			throws Exception {
		// log.info("Cut Node");

		NodeProperties selectedNodeProperty = desktopMainPanel.getjLeftPanel()
				.getNodePropertiesMap().get(selectedNode.getNodeId());
		log.info("Reverse Node -> Selected Node Id: "
				+ selectedNodeProperty.getNodeId());

		/*
		 * UnCommented Code To Reverse Selected Document (All) Pages
		 */

		// List<NodeProperties> nodePropertyList =
		// fetchChildNodes(selectedNode);

		/*
		 * Code To Reverse Only Selected Nodes/Pages Return Null If No Pages
		 * Selected
		 */

		List<NodeProperties> nodePropertyList = getSelectedPagesOrNodes();
		if (nodePropertyList != null && !nodePropertyList.isEmpty()) {
			// Condition To Check Selcted Atlease To Pages To Reverse
			if (nodePropertyList.size() < 2) {
				ErrorMessage.displayMessage('I',
						"selectAtLeastTwoPageToReverse");
				return;
			}

			// Condition To Check that If Selected Nodes Are updated by Other
			// Users Then Not Allow To Split Nodes
			boolean isSelectedNodesUpdatedByOtherUser = isNodeUpdatedByOtherUser(checkNodeIsUpdatedByOtherUserOrNot(nodePropertyList));
			if (isSelectedNodesUpdatedByOtherUser) {
				ErrorMessage.displayMessage('I',
						"updatedByOtherUserPleaseRefreshTree");
				return;
			}

			// Define Temporary Variable To reverse Sequence
			List<String> list = new ArrayList<String>();

			// Loop To Add Sequence Number To Temporary List
			for (NodeProperties nodeProperties : nodePropertyList) {
				list.add(nodeProperties.getDocumentSequenceNumber());
			}

			// Loop To generate nodePropertPojo with New Sequence Number
			for (int index = 0, reverse = nodePropertyList.size() - 1; index < nodePropertyList
					.size() && reverse >= 0; index++, reverse--) {
				nodePropertyList.get(index).setDocumentSequenceNumber(
						list.get(reverse));
				nodePropertyList.get(index).setUserId(
						SessionUtil.getSessionData().getUserId());
				nodePropertyList.get(index).setTransactionStatus(
						TransactionConstant.UPDATE);
			}

			updateNodePropertyOperationToDatabase(nodePropertyList);

			// get the current divider location (pixels from left edge)
			int dividerLocation = imageViewer.getSplitPanel()
					.getDividerLocation();

			refreshThumbnailPanelWithCurrentSelectedNode();

			// set new divider location
			imageViewer.getSplitPanel().setDividerLocation(dividerLocation);

		}

	}

	public List<NodeProperties> getSelectedPagesOrNodes() {

		JList selectedPagesList = imageViewer.getThumbnailList();
		Object[] selectedValues = selectedPagesList.getSelectedValues();
		List<NodeProperties> selectedNodePropertyList;

		log.info("Total Selected Nodes: " + selectedValues.length);

		if (selectedValues.length <= 0) {
			ErrorMessage.displayMessage('I', "selectAtLeastOnePage");
			return null;
		} else {
			selectedNodePropertyList = new ArrayList<NodeProperties>(
					selectedValues.length);
			lastSelectedPageIndexeList = new int[selectedValues.length];
			for (int index = 0; index < selectedValues.length; index++) {
				Object object = selectedValues[index];
				NodeProperties selectedPageNodeProperty = ((CustomImageIcon) object)
						.getNodeProperty();
				selectedNodePropertyList.add(selectedPageNodeProperty);
				lastSelectedPageIndexeList[index] = selectedPagesList
						.getSelectedIndices()[index];

			}
			return selectedNodePropertyList;
		}
	}

	public List<NodeProperties> getChildNodeListIncludingSelectedNodes(
			NodeProperties selectedPageNode) {
		JList selectedPagesList = imageViewer.getThumbnailList();
		int selectedIndex = selectedPagesList.getSelectedIndex();

		List<NodeProperties> nodePropertyListIncluddingSelectedIndex = new ArrayList<NodeProperties>(
				selectedPagesList.getModel().getSize() - selectedIndex);

		for (int index = selectedIndex; index < selectedPagesList.getModel()
				.getSize(); index++) {
			NodeProperties nodeProperty = ((CustomImageIcon) selectedPagesList
					.getModel().getElementAt(index)).getNodeProperty();
			nodePropertyListIncluddingSelectedIndex.add(nodeProperty);

		}
		return nodePropertyListIncluddingSelectedIndex;

	}

	private void cutNodes(CustomMutableTreeNode selectedNode) {

		log.info("Cut Node -> Selected Document Node Id: "
				+ selectedNode.getNodeId());

		cutImageParentNode = selectedNode;

		List<NodeProperties> cutNodeList = getCutNodes();

		if (cutNodeList != null && !cutNodeList.isEmpty()) {
			// Condition To Check that If Selected Nodes Are updated by Other
			// Users Then Not Allow To Cut Nodes
			boolean isSelectedNodesUpdatedByOtherUser = isNodeUpdatedByOtherUser(checkNodeIsUpdatedByOtherUserOrNot(cutNodeList));
			if (isSelectedNodesUpdatedByOtherUser) {
				ErrorMessage.displayMessage('I',
						"updatedByOtherUserPleaseRefreshTree");
				cutNodePropertyList = null;
				cutNodeIndex = null;
				NodeProperties selectedNodeProperty = desktopMainPanel
						.getjLeftPanel().getNodePropertiesMap()
						.get(selectedNode.getNodeId());
				desktopMainPanel.getjLeftPanel()
						.enableDisableImportSeparationButtons(
								selectedNodeProperty);
				return;
			}

			for (int index = 0; index < cutNodeList.size(); index++) {
				NodeProperties selectedPageNodeProperty = cutNodeList
						.get(index);
				log.info("Cut Node -> Cut Node Id : "
						+ selectedPageNodeProperty.getNodeId());
			}
		}
	}

	// Return Cut Nodes If Pages Are Selected And Cut
	// Return NULL, If no Pages/Nodes are selected
	// Return NULL, If View Thumbnail Is Not Clicked

	private List<NodeProperties> getCutNodes() {
		if (imageViewer != null && imageViewer.getThumbnailList() != null
				&& imageViewer.getThumbnailList().getComponentCount() > 0) {

			JList selectedPagesList = imageViewer.getThumbnailList();
			Object[] selectedValues = selectedPagesList.getSelectedValues();

			if (selectedValues.length <= 0) {
				ErrorMessage.displayMessage('I', "selectPageToCut");
				return null;

			} else {
				// Set Button Enable Disable
				splitButton.setEnabled(false);
				reverseButton.setEnabled(false);
				deletePageButton.setEnabled(false);
				cutButton.setEnabled(false);
				pasteButton.setEnabled(true);
				cancelButton.setEnabled(true);

				cutNodePropertyList = new ArrayList<NodeProperties>(
						selectedValues.length);
				cutNodeIndex = new ArrayList<Integer>(selectedValues.length);

				for (int index = 0; index < selectedValues.length; index++) {
					Object object = selectedValues[index];
					NodeProperties selectedPageNodeProperty = ((CustomImageIcon) object)
							.getNodeProperty();
					cutNodePropertyList.add(selectedPageNodeProperty);
					cutNodeIndex
							.add(selectedPagesList.getSelectedIndices()[index]);
				}
				return cutNodePropertyList;
			}
		} else {
			// Return Null If Thumbnail View Is Not Visible
			return null;
		}
	}

	private void pasteNodes(CustomMutableTreeNode selectedNode)
			throws Exception {

		// If user has only import right & no separation right, he should not be
		// allowed to paste images in different node.
		if (SessionUtil.getSessionData().getImportRight().equals("Y")
				&& SessionUtil.getSessionData().getSeparationRight()
						.equals("N")
				&& !cutImageParentNode.getNodeId().equals(
						selectedNode.getNodeId())) {
			ErrorMessage.displayMessage('I',
					"importUserNotAllowedToPasteImageInOtherNode");
			desktopMainPanel
					.getjLeftPanel()
					.getNodeTree()
					.setSelectionPath(
							new TreePath(cutImageParentNode.getPath()));
			return;
		}

		// Condition To Check That Is Cut Nodes Are Available To paste
		if (cutNodePropertyList == null || cutNodePropertyList.isEmpty()) {
			ErrorMessage.displayMessage('I', "noPagesToCutPaste");
			return;
		}

		else {
			// Condition To Check That If Selected Node Is Multiple Then Not
			// Allow To Paste
			JList selectedPagesList = imageViewer.getThumbnailList();
			Object[] selectedValues = selectedPagesList.getSelectedValues();

			// Get Source Document Node Id
			String sourceNodeId = cutNodePropertyList.get(0).getParentNodeId();

			// Get Destination Document Node
			NodeProperties destinationDocumentNode = desktopMainPanel
					.getjLeftPanel().getNodePropertiesMap()
					.get(selectedNode.getNodeId());

			// Get Destination Node Index
			Integer destinationNodeIndex = -1;

			// If Source And Destination Nodes Are Same
			if (sourceNodeId.equals(destinationDocumentNode.getNodeId())) {
				// Condition To Check At Least one Node Is Selected
				if (selectedValues != null && selectedValues.length > 0) {
					// Condition To Check Only One Node Is Selected
					if (selectedValues.length > 1) {
						ErrorMessage.displayMessage('I',
								"selectOnlyOnePageToPaste");
						return;
					}

					else {
						// Get Destination Node Index
						destinationNodeIndex = selectedPagesList
								.getSelectedIndices()[0];
						log.info("Paste Node -> Destination node Index: "
								+ destinationNodeIndex);

						// Destination Node Property List
						NodeProperties selectedDestinationPageNodeProperty = ((CustomImageIcon) selectedValues[0])
								.getNodeProperty();
						log.info("Paste Node -> Destination node Id: "
								+ selectedDestinationPageNodeProperty
										.getNodeId());
					}
				}
			}

			// Added condition to not allow if paste image index is same as one
			// of the cut images' index.
			if (cutNodeIndex.contains(destinationNodeIndex)) {
				ErrorMessage.displayMessage('I',
						"pasteImageInSameNodeCanNotBeSameAsAnyOfCutImage");
				return;
			}

			// Get Total Pages For Selected Source Nodes
			ArrayList<String> idList = new ArrayList<String>();
			String projectId = SessionUtil.getSessionData().getProjectId();
			idList.add(projectId);
			idList.add(sourceNodeId);
			List<NodeProperties> totalPageListForSelectedSourceDocument = controller()
					.fetchChildNodeList(idList);

			// Get Total Pages For Selected Destination Nodes
			NodeProperties selectedNodeProperty = desktopMainPanel
					.getjLeftPanel().getNodePropertiesMap()
					.get(selectedNode.getNodeId());

			// Get Updated Cut Nodes Property List
			List<NodeProperties> updatedNodeListToPaste = updateCutNodePropertyToPaste(
					cutNodePropertyList, destinationDocumentNode);

			// Condition To Check that If Selected Nodes Are updated by
			// Other Users Then Not Allow To Cut Nodes
			Map<String, String> nodeUpdateTimeMap = new HashMap<String, String>();

			nodeUpdateTimeMap.put(destinationDocumentNode.getNodeId(),
					destinationDocumentNode.getLastUpdateDateTime());

			nodeUpdateTimeMap
					.putAll(checkNodeIsUpdatedByOtherUserOrNot(totalPageListForSelectedSourceDocument));
			nodeUpdateTimeMap
					.putAll(checkNodeIsUpdatedByOtherUserOrNot(updatedNodeListToPaste));

			if (isNodeUpdatedByOtherUser(nodeUpdateTimeMap)) {
				ErrorMessage.displayMessage('I',
						"updatedByOtherUserPleaseRefreshTree");
				cutNodePropertyList = null;
				cutNodeIndex = null;
				return;
			}

			ArrayList<NodeProperties> imageNodePropertiesToUpdate = updateDocumentSequenceNumber(
					(ArrayList<NodeProperties>) totalPageListForSelectedSourceDocument,
					(ArrayList<NodeProperties>) updatedNodeListToPaste,
					(ArrayList<Integer>) cutNodeIndex, destinationNodeIndex);

			// Set Value To Make Page Selected After Refresh Thumbnails
			setDestinationNodeIndexToSelectPage(destinationNodeIndex);
			setDestinationNodePropertyToSelectPage(selectedNodeProperty);
			setUpdatedNodePropertyListToSelectPage(updatedNodeListToPaste);

			updateNodePropertyOperationToDatabase(imageNodePropertiesToUpdate);

			// get the current divider location (pixels from left edge)
			int dividerLocation = imageViewer.getSplitPanel()
					.getDividerLocation();

			// Added Flag To Clear/Null Cut Nodes After Refresh Thumbnail (
			// Async call process) completed
			isCutNodesArePasted = true;

			refreshThumbnailPanelWithCurrentSelectedNode();

			// set new divider location
			imageViewer.getSplitPanel().setDividerLocation(dividerLocation);

			// cutNodePropertyList = null;
			// cutNodeIndex = null;
		}
	}

	public void setSelectedPageAfterPaste(Integer destinationNodeIndex,
			NodeProperties selectedNodeProperty,
			List<NodeProperties> updatedNodeListToPaste) {
		// Code To Set Cut Node Selected After Refresh Thumbnail
		// Condition To Check If Cut Node Paste To Greater Index then
		// Increase Index by Total Count
		int tempCount = 0;
		for (Integer tempCutNodeIndex : cutNodeIndex) {

			if (destinationNodeIndex > tempCutNodeIndex) {
				tempCount++;
			} else if (destinationNodeIndex == tempCutNodeIndex) {
				tempCount++;
			}
		}

		// Condition To Check Source and Destination Nodes Are Same
		if (destinationNodeIndex != -1) {
			// Code To Set Cut Node Became Selected After Paste
			int[] lastSelectedCutNodeIndex = new int[updatedNodeListToPaste
					.size()];

			for (int i = 0; i < updatedNodeListToPaste.size(); i++) {
				lastSelectedCutNodeIndex[i] = destinationNodeIndex + (i + 1)
						- tempCount;
			}

			imageViewer.getThumbnailList().setSelectedIndices(
					lastSelectedCutNodeIndex);
			imageViewer.getThumbnailList().ensureIndexIsVisible(
					destinationNodeIndex);
		} else {
			imageViewer.getThumbnailList().setSelectedIndex(
					destinationNodeIndex);
			imageViewer.getThumbnailList().ensureIndexIsVisible(
					destinationNodeIndex);

		}
	}

	private List<NodeProperties> updateCutNodePropertyToPaste(
			List<NodeProperties> cutNodePropertyList,
			NodeProperties selectedDestinationPageNodeProperty) {

		for (int index = 0; index < cutNodePropertyList.size(); index++) {

			NodeProperties nodeProperty = cutNodePropertyList.get(index);

			String imageDirectoryPath = getImageDirectoryPath();

			nodeProperty.setParentNodeId(selectedDestinationPageNodeProperty
					.getNodeId());
			nodeProperty.setActualImageName(imageDirectoryPath
					+ nodeProperty.getName());
			nodeProperty.setThumbnailImageName(imageDirectoryPath
					+ NodePropertyConstants.THUMBNAIL_DIR
					+ nodeProperty.getName());
			nodeProperty.setUserId(SessionUtil.getSessionData().getUserId());
			nodeProperty.setTransactionStatus(TransactionConstant.UPDATE);
		}
		return cutNodePropertyList;
	}

	private void cancelCutNodes(CustomMutableTreeNode selectedNode) {

		cutNodePropertyList = null;
		cutNodeIndex = null;

		NodeProperties selectedNodeProperty = desktopMainPanel.getjLeftPanel()
				.getNodePropertiesMap().get(selectedNode.getNodeId());

		desktopMainPanel.getjLeftPanel().enableDisableImportSeparationButtons(
				selectedNodeProperty);

	}

	/**
	 * Method to perform resume import operation.
	 * 
	 * @param selectedNode
	 */
	private void resumeImport(CustomMutableTreeNode selectedNode) {

		// Condition To check Selected Node Is Not Root Node.
		if (selectedNode.getNodeId() == SessionUtil.getSessionData()
				.getProjectId())
			return;

		NodeProperties nodeProperties = desktopMainPanel.getjLeftPanel()
				.getNodePropertiesMap().get(selectedNode.getNodeId());

		if (nodeProperties == null)
			return;

		// Condition To Check before Delete Document, Is The Node is
		// going to delete is Previouly Updated By Other Users Or Not.
		Map<String, String> nodeUpdateTimeMap = new HashMap<String, String>();
		nodeUpdateTimeMap.put(nodeProperties.getNodeId(),
				nodeProperties.getLastUpdateDateTime());

		if (isNodeUpdatedByOtherUser(nodeUpdateTimeMap)) {
			ErrorMessage.displayMessage('I',
					"updatedByOtherUserPleaseRefreshTree");
			return;
		}

		// Getting directory path of image from node id of nodes in hierarchy of
		// selected node..
		String imageDirectoryPath = getImageDirectoryPath();

		ArrayList<String> uploadErrorImageNameList = uploadErrorImageNameMap
				.get(imageDirectoryPath);

		// If erroneous images are not existing...
		if (uploadErrorImageNameList == null
				|| uploadErrorImageNameList.size() == 0) {

			ErrorMessage.displayMessage('I', "noImagesWithUploadError");
			return;
		}

		// Uploading image in batch...
		for (int index = 0; index < uploadErrorImageNameList.size(); index++) {

			String imageName = uploadErrorImageNameList.get(index);

			imageUploader.proceedToUpload(imageDirectoryPath, imageName,
					nodeProperties, true);
		}

		// Uploading remaining images that did not reach batch size...
		imageUploader.uploadImagesInBatch(true, true);
	}

	/**
	 * Method to get the directory path for the scanned image. This path is
	 * relative which begins with project name and name subsequent folders are
	 * node id of respective tree nodes in hierarchy of selected node.
	 * 
	 * @return
	 */
	private String getImageDirectoryPath() {

		String path = "";

		CustomMutableTreeNode presentNode = desktopMainPanel.getjRightPanel()
				.getCollectionPanel().getSelectedTreeNode();

		while (presentNode.getNodeId() != SessionUtil.getSessionData()
				.getProjectId()) {

			path = presentNode.getNodeId() + File.separator + path;

			presentNode = (CustomMutableTreeNode) presentNode.getParent();
		}

		path = SessionUtil.getSessionData().getProjectName() + File.separator
				+ path;

		return path;
	}

	private void importImageFromScanner(CustomMutableTreeNode selectedNode) {

		NodeProperties selectedNodeProperty = desktopMainPanel.getjLeftPanel()
				.getNodePropertiesMap().get(selectedNode.getNodeId());

		// Condition To Check before Delete Document, Is The Node is
		// going to delete is Previouly Updated By Other Users Or Not.
		Map<String, String> nodeUpdateTimeMap = new HashMap<String, String>();
		nodeUpdateTimeMap.put(selectedNodeProperty.getNodeId(),
				selectedNodeProperty.getLastUpdateDateTime());

		if (isNodeUpdatedByOtherUser(nodeUpdateTimeMap)) {
			ErrorMessage.displayMessage('I',
					"updatedByOtherUserPleaseRefreshTree");
			return;
		}

		// Getting scanning device & if no device is attached, display error and
		// return...
		TwainSource scanningDevice = getScanningDevice();

		if (scanningDevice == null) {
			log.info("Import -> No scanning devide found...");
			ErrorMessage.displayMessage('I', "noScanningDeviceFound");
			return;
		}

		NodeProperties parentDocumentNodeProperties = createDummyDocument(selectedNode);
		log.info("Import -> Import Document To Node: "
				+ parentDocumentNodeProperties.getNodeId());

		startDocumentScanning(parentDocumentNodeProperties, scanningDevice);
	}

	/**
	 * Method to get the scanning device attached with system.
	 * 
	 * @return
	 */
	private TwainSource getScanningDevice() {

		// MORENA 7 CODE STARTS...

		// Manager manager = Manager.getInstance();
		// Device scanningDevice = null;
		//
		// // Getting device list and finding right device...
		// java.util.List<Device> deviceList = manager.listDevices();
		//
		// for (Device tmpDevice : deviceList) {
		//
		// if (tmpDevice instanceof Scanner || tmpDevice instanceof Camera) {
		//
		// scanningDevice = tmpDevice;
		// break;
		// }
		// }

		// MORENA 7 CODE ENDS...

		// MORENA 6 CODE STARTS...
		TwainSource scanningDevice = null;
		scanningDevice = TwainManager.selectSource(null, null);
		// try {
		//
		// } catch (TwainException e) {
		// log.error("Error In Detecting Scanner Device...");
		// ErrorMessage.displayMessage('E', "errorInDetectingScanningDevice");
		// }
		// MORENA 6 CODE ENDS...

		return scanningDevice;
	}

	/**
	 * Method to create dummy child document node if selected node is Container
	 * or Box type. Method will return the node properties of either created
	 * dummy document node or selected document node.
	 * 
	 * @param selectedNode
	 * @return
	 */
	private NodeProperties createDummyDocument(
			CustomMutableTreeNode selectedNode) {
		log.info("Create Dummy Document -> Selected Document Node Id : "
				+ selectedNode.getNodeId());

		// Condition To check Selected Node Is Not Root Node.
		if (selectedNode.getNodeId() == SessionUtil.getSessionData()
				.getProjectId())
			return null;

		NodeProperties nodeProperties = desktopMainPanel.getjLeftPanel()
				.getNodePropertiesMap().get(selectedNode.getNodeId());

		// Condition TO Check Node Is Box/Container
		// If Node Is Box/Container Add Dummy Document Node To It And Start
		// Pages Import From That Dummy Node
		if (nodeProperties != null
				&& (nodeProperties.getType().equals(
						NodePropertyConstants.CONTAINER) || nodeProperties
						.getType().equals(NodePropertyConstants.BOX))) {

			NodeProperties newlyAddedDummyNodeProperty = createDocument();

			if (newlyAddedDummyNodeProperty != null) {

				nodeProperties = newlyAddedDummyNodeProperty;

			} else
				log.info("Create Dummy Document -> Newly Added Dummy Node Id Return Null. Node Is Not Added To Database. Trying To Add Node In Done/Sealed Status or Added To Document Node");
		}

		return nodeProperties;
	}

	/**
	 * Method to begin scanning of the document.
	 * 
	 * @param parentDocumentNodeProperties
	 */
	private void startDocumentScanning(
			NodeProperties parentDocumentNodeProperties,
			TwainSource scanningDevice) {

		// MORENA 7 CODE STARTS...

		// DocumentScanner documentScanner = new DocumentScanner(this,
		// parentDocumentNodeProperties);
		//
		// boolean showImageSettingUI = false;
		//
		// String numberOfPagesToScan = ConstantUtil
		// .getApplicationConstant("numberOfPagesToScan");
		//
		// // Beginning scanning of document...
		// synchronized (scanningDevice) {
		// documentScanner.scanDocument(scanningDevice, showImageSettingUI,
		// Integer.parseInt(numberOfPagesToScan));
		// }

		// MORENA 7 CODE ENDS...

		// MORENA 6 CODE STARTS...
		documentScanner = new DocumentScanner(this,
				parentDocumentNodeProperties);

		String numberOfPagesToScan = ConstantUtil
				.getApplicationConstant("numberOfPagesToScan");

		documentScanner.scanDocument(scanningDevice,
				Integer.parseInt(numberOfPagesToScan));
		// MORENA 6 CODE ENDS...
	}

	private void searchNodeFromBarcode() {
            
            DesktopMainJPanel desktopMainPanel= new DesktopMainJPanel();
            desktopMainPanel.updatejleftPanel();

		String searchBarcode = barcodeField.getText().trim();

                
		CustomMutableTreeNode projectNode = desktopMainPanel.getjLeftPanel()
				.getProjectNode();

		if (searchBarcode == null || searchBarcode.length() == 0) {
			ErrorMessage.displayMessage('I', "provideSearchBarcode");
			return;
		}

		String hierarchy = controller().getHierarchyFromSearchBarcode(
				searchBarcode);
		log.info("Search Barcode -> Search Node Hierarchy : " + hierarchy);
		if (hierarchy == null || hierarchy.length() == 0) {
			log.error("Search Barcode -> EMPTY Hierarchy. Error while searching node.");
			ErrorMessage.displayMessage('I',
					"errorWhileSearchingNodeFromBarcode");
			return;
		}

		// To remove "[" and "]" from the hierarchy coming from DB and split
		// hierarchy in tree node names.
		String[] nodeNames = hierarchy.substring(1, hierarchy.length() - 1)
				.split(", ");

		if (!nodeNames[0].equals(String.valueOf(projectNode.getUserObject()))) {
			ErrorMessage.displayMessage('I',
					"providedSearchBarcodeBelongsToOtherProject");
			return;
		}

		searchNodeFromhierarchy(projectNode, hierarchy);

		barcodeField.setText("");

	}

	public void searchNodeFromhierarchy(CustomMutableTreeNode projectNode,
			String hierarchy) {

		String[] nodeNames;

		CustomMutableTreeNode currentNode = projectNode;

		if (hierarchy.trim().isEmpty()) {
			nodeNames = new String[0];
		} else {
			nodeNames = hierarchy.substring(1, hierarchy.length() - 1).split(
					", ");
		}

		for (int index = 1; index < nodeNames.length; index++) {

			boolean nodeFound = false;

			if (currentNode.getChildCount() == 0) {
				desktopMainPanel.getjLeftPanel().fetchChildNodes(
						SessionUtil.getSessionData().getProjectId(),
						currentNode.getNodeId());
			}

			for (int childIndex = 0; childIndex < currentNode.getChildCount(); childIndex++) {

				CustomMutableTreeNode childNode = (CustomMutableTreeNode) currentNode
						.getChildAt(childIndex);

				if (nodeNames[index].equals(String.valueOf(childNode
						.getUserObject()))) {
					currentNode = childNode;

					TreePath searchNodePath = new TreePath(
							currentNode.getPath());
					desktopMainPanel.getjLeftPanel().getNodeTree()
							.setSelectionPath(searchNodePath);
					desktopMainPanel.getjLeftPanel().getNodeTree()
							.expandPath(searchNodePath);
					desktopMainPanel.getjLeftPanel().getNodeTree().updateUI();
					desktopMainPanel.getjLeftPanel().getNodeTree().repaint();

					nodeFound = true;
					break;
				}
			}

			if (currentNode.getChildCount() > 0 && !nodeFound) {
				ErrorMessage.displayMessage('I',
						"nodeInHierarchyNotFoundInTree");
				return;
			}
		}
		if (currentNode != null) {
			TreePath path = new TreePath(currentNode.getPath());

			desktopMainPanel.getjLeftPanel().getNodeTree()
					.setSelectionPath(path);
			desktopMainPanel.getjLeftPanel().getNodeTree().expandPath(path);
			desktopMainPanel.getjLeftPanel().getNodeTree().updateUI();
			desktopMainPanel.getjLeftPanel().getNodeTree().repaint();
		}
	}

	public CustomMutableTreeNode searchNode(String nodeStr) {
		JTree nodeTree = desktopMainPanel.getjLeftPanel().getNodeTree();
		CustomMutableTreeNode node = null;
		Enumeration e = ((CustomMutableTreeNode) nodeTree.getModel().getRoot())
				.breadthFirstEnumeration();
		while (e.hasMoreElements()) {
			node = (CustomMutableTreeNode) e.nextElement();
			if (nodeStr.equals(node.getUserObject().toString())) {
				return node;
			}
		}
		return null;
	}

	private NodeProperties createDocument() {

		CustomMutableTreeNode selectedParentNode = desktopMainPanel
				.getjRightPanel().getCollectionPanel().getSelectedTreeNode();

		if (selectedParentNode == null)
			return null;

		// Condition to check if node is saved or newly added node.
		if (selectedParentNode.getNodeId().equals(
				SessionUtil.getSessionData().getProjectId())
				|| (selectedParentNode.getNodeId() != null && selectedParentNode
						.getNodeId().trim().length() > 0)) {

			if (desktopMainPanel.getjRightPanel().getCollectionPanel()
					.isSelectedNodeIsInDoneStatus())
				ErrorMessage.displayMessage('I',
						"notAllowedToAddNodeInDoneState");

			else if (desktopMainPanel.getjRightPanel().getCollectionPanel()
					.isDocumentNode())
				ErrorMessage.displayMessage('I',
						"notAllowedToAddInDocumentNode");

			else {

				JTree nodeTree = desktopMainPanel.getjLeftPanel().getNodeTree();
				CustomMutableTreeNode selectedElement = (CustomMutableTreeNode) nodeTree
						.getSelectionPath().getLastPathComponent();

				log.info("Create Document -> Selected Document Node: "
						+ selectedElement.getUserObject());
				log.info("Create Document -> Sel Path: "
						+ nodeTree.getSelectionPath());

				DefaultTreeModel model = (DefaultTreeModel) nodeTree.getModel();

				String dumyDocumentNode = ConstantUtil
						.getApplicationConstant("dumyDocumentNode")
						+ " "
						+ (selectedParentNode.getChildCount() + 1);
				CustomMutableTreeNode newDocumentNode = new CustomMutableTreeNode(
						dumyDocumentNode);
				model.insertNodeInto(newDocumentNode, selectedElement,
						selectedElement.getChildCount());

				nodeTree.setSelectionPath(new TreePath(newDocumentNode
						.getPath()));

				// Passed New Document Node to Set Parent Node And Hierarchy
				NodeProperties newNodeDocumentNodePOJO = initNewDocumentNodePOJO(newDocumentNode);
				NodeProperties savedNewNode = desktopMainPanel
						.getjRightPanel()
						.getCollectionPanel()
						.controller()
						.saveNodeButton_actionPerformed(newNodeDocumentNodePOJO);
				newDocumentNode.setNodeId(savedNewNode.getNodeId());
				newDocumentNode.setUserObject(savedNewNode.getName());

				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.getNodeTypeList().setEnabled(false);
				desktopMainPanel.getjLeftPanel().getNameLabelValue()
						.setText(savedNewNode.getName());

				desktopMainPanel.getjLeftPanel().getNodePropertiesMap()
						.put(savedNewNode.getNodeId(), savedNewNode);

				desktopMainPanel.getjLeftPanel().getNodeTree().updateUI();

				// Marking node to null because when node is saved, it is not
				// newly added.
				desktopMainPanel.getjRightPanel().getCollectionPanel()
						.setNewAddedNode(null);

				desktopMainPanel.getjLeftPanel().controller()
						.getAndSetNodePropertyValue();

				desktopMainPanel.getjLeftPanel()
						.enableDisableImportSeparationButtons(savedNewNode);

				return savedNewNode;
			}
		} else {
			ErrorMessage.displayMessage('I', "saveOrDeleteAddedNode");
			return null;
		}
		return null;

	}

	private NodeProperties initNewDocumentNodePOJO(
			CustomMutableTreeNode newDocumentNode) {
		NodeProperties newDocumentNodeProperty = new NodeProperties();

		String parentNodeId = ((CustomMutableTreeNode) newDocumentNode
				.getParent()).getNodeId();
		String hierarchy = desktopMainPanel.getjLeftPanel().getNodeTree()
				.getSelectionPath().toString();

		newDocumentNodeProperty.setHierarchy(hierarchy);

		if (parentNodeId.equals(SessionUtil.getSessionData().getProjectId()))
			newDocumentNodeProperty.setParentNodeId(null);
		else
			newDocumentNodeProperty.setParentNodeId(parentNodeId);

		newDocumentNodeProperty.setBarcode("");
		newDocumentNodeProperty.setComment("");
		newDocumentNodeProperty.setInternalComment("");

		newDocumentNodeProperty.setStatus(NodePropertyConstants.IN_PROGRESS);
		newDocumentNodeProperty.setName(newDocumentNode.getUserObject()
				.toString());
		newDocumentNodeProperty.setProjectId(SessionUtil.getSessionData()
				.getProjectId());
		newDocumentNodeProperty.setType(NodePropertyConstants.DOCUMENT);
		newDocumentNodeProperty
				.setTransactionStatus(TransactionConstant.INSERT);
		newDocumentNodeProperty.setUserId(SessionUtil.getSessionData()
				.getUserId());

		return newDocumentNodeProperty;
	}

	/**
	 * Method to update the document sequence number of all affected images when
	 * images are cut and pasted in same node or different node or when images
	 * are deleted.
	 * 
	 * @param fetchedImageNodePropertiesList
	 *            - List of image node properties when Thumbnail button is
	 *            clicked.
	 * @param cutImageNodePropertiesList
	 *            - List of image node properties that are cut or deleted.
	 * @param cutImageIndexList
	 *            - List of index of images (on screen starting from 0) that are
	 *            cut or deleted.
	 * @param pasteImageIndex
	 *            - Index of image (in same node) below which cut images are
	 *            pasted. In case of images are pasted in different node or
	 *            images are deleted, the index will be -1.
	 * @return
	 */
	public ArrayList<NodeProperties> updateDocumentSequenceNumber(
			ArrayList<NodeProperties> fetchedImageNodePropertiesList,
			ArrayList<NodeProperties> cutImageNodePropertiesList,
			ArrayList<Integer> cutImageIndexList, int pasteImageIndex) {

		ArrayList<NodeProperties> updateImageNodePropertiesList = new ArrayList<NodeProperties>();

		Collections.sort(cutImageIndexList);

		// Index from which "fetchedImageNodePropertiesList" will be iterated to
		// get the Node Properties to update document sequence number.
		int start = pasteImageIndex == -1 ? cutImageIndexList.get(0) : Math
				.min(cutImageIndexList.get(0), pasteImageIndex);

		// Index to which "fetchedImageNodePropertiesList" will be iterated to
		// get the Node Properties to update document sequence number.
		int end = pasteImageIndex == -1 ? fetchedImageNodePropertiesList.size() - 1
				: Math.max(cutImageIndexList.get(cutImageIndexList.size() - 1),
						pasteImageIndex);

		int cutImageCount = 0;
		int lastDocumentSequenceNumber = 0;

		// Updating the image node properties that are falling within "start"
		// and "end" range. Here node properties of images that are cut or
		// deleted will not be updated.
		for (int index = start; index <= end; index++) {

			// Skipping node properties of images that are cut or deleted.
			if (cutImageIndexList.contains(index)) {

				cutImageCount++;
				continue;
			}

			// Updating affected node properties within range...
			else {

				NodeProperties imageNodeProperties = fetchedImageNodePropertiesList
						.get(index);

				int documentSequenceNumber = Integer
						.parseInt(imageNodeProperties
								.getDocumentSequenceNumber());

				// Logically, all images that are not cut till paste image
				// (inclusive) will be moved before the cut images. Hence by
				// below code, decresing the document sequence number of image
				// at specific index by the number images (before present image
				// at specific index) that are cut.
				if (pasteImageIndex == -1 || index <= pasteImageIndex) {
					documentSequenceNumber = documentSequenceNumber
							- cutImageCount;
					lastDocumentSequenceNumber = documentSequenceNumber;
				}

				// Logically, all images thare not cut after paste image will be
				// moved after the cut images. Hence by below code, increasing
				// the document sequence number of image at specific index by
				// the number images (after present image at specific index)
				// that are cut.
				else if (index > pasteImageIndex) {
					documentSequenceNumber = documentSequenceNumber
							+ cutImageIndexList.size() - cutImageCount;
				}

				imageNodeProperties.setDocumentSequenceNumber(String
						.valueOf(documentSequenceNumber));
				imageNodeProperties.setUserId(SessionUtil.getSessionData()
						.getUserId());
				imageNodeProperties
						.setTransactionStatus(TransactionConstant.UPDATE);

				updateImageNodePropertiesList.add(imageNodeProperties);
			}
		}

		// Updating node properties of cut or deleted images... If the paste
		// index is -1, add cut/delete image node property list to
		// "updateImageNodePropertiesList"
		if (pasteImageIndex != -1) {

			for (int index = 0; index < cutImageNodePropertiesList.size(); index++) {

				NodeProperties imageNodeProperties = cutImageNodePropertiesList
						.get(index);

				++lastDocumentSequenceNumber;

				imageNodeProperties.setDocumentSequenceNumber(String
						.valueOf(lastDocumentSequenceNumber));

				updateImageNodePropertiesList.add(imageNodeProperties);
			}

		} else {

			updateImageNodePropertiesList.addAll(cutImageNodePropertiesList);
		}

		return updateImageNodePropertiesList;
	}

	public void setEnableDisableImportPanelButtonWhileCut(boolean enabled) {

		searchButton.setEnabled(enabled);
		createDocumentButton.setEnabled(enabled);
		deleteDocumentButton.setEnabled(enabled);
		viewThumbnailsButton.setEnabled(enabled);
		editNodePropertyButton.setEnabled(enabled);
		importButton.setEnabled(enabled);
		resumeImportButton.setEnabled(enabled);
		splitButton.setEnabled(enabled);
		reverseButton.setEnabled(enabled);
		deletePageButton.setEnabled(enabled);
		reverseButton.setEnabled(enabled);
	}

	public void setCutPasteCancelButtonEnableDisable(boolean enabled) {
		cutButton.setEnabled(enabled);
		pasteButton.setEnabled(enabled);
		cancelButton.setEnabled(enabled);
	}

	public void setButtonDisableWhileNodeInSealedStatus() {
		searchButton.setEnabled(true);
		createDocumentButton.setEnabled(false);
		deleteDocumentButton.setEnabled(false);
		viewThumbnailsButton.setEnabled(true);
		editNodePropertyButton.setEnabled(false);
		importButton.setEnabled(false);
		resumeImportButton.setEnabled(false);
		splitButton.setEnabled(false);
		reverseButton.setEnabled(false);
		deletePageButton.setEnabled(false);
		reverseButton.setEnabled(false);
		cutButton.setEnabled(false);
		pasteButton.setEnabled(false);
		cancelButton.setEnabled(false);

	}

	public void setButtonDisableWhileNodeInDoneStatus(boolean enabled) {
		searchButton.setEnabled(true);
		viewThumbnailsButton.setEnabled(true);
		createDocumentButton.setEnabled(false);
		deleteDocumentButton.setEnabled(false);
		editNodePropertyButton.setEnabled(enabled);
		importButton.setEnabled(false);
		resumeImportButton.setEnabled(false);
		splitButton.setEnabled(false);
		reverseButton.setEnabled(false);
		deletePageButton.setEnabled(false);
		reverseButton.setEnabled(false);
		cutButton.setEnabled(false);
		pasteButton.setEnabled(false);
		cancelButton.setEnabled(false);

	}

	public void setButtonDisableWhileNodeIsProjectNode() {
		searchButton.setEnabled(true);
		viewThumbnailsButton.setEnabled(false);
		createDocumentButton.setEnabled(false);
		deleteDocumentButton.setEnabled(false);
		editNodePropertyButton.setEnabled(false);
		importButton.setEnabled(false);
		resumeImportButton.setEnabled(false);
		splitButton.setEnabled(false);
		reverseButton.setEnabled(false);
		deletePageButton.setEnabled(false);
		reverseButton.setEnabled(false);
		cutButton.setEnabled(false);
		pasteButton.setEnabled(false);
		cancelButton.setEnabled(false);
	}

	public void setAllButtonEnableDisable(boolean enabled) {
		// searchButton.setEnabled(enabled);
		viewThumbnailsButton.setEnabled(enabled);
		createDocumentButton.setEnabled(enabled);
		deleteDocumentButton.setEnabled(enabled);
		editNodePropertyButton.setEnabled(enabled);
		importButton.setEnabled(enabled);
		resumeImportButton.setEnabled(enabled);
		splitButton.setEnabled(enabled);
		reverseButton.setEnabled(enabled);
		deletePageButton.setEnabled(enabled);
	}

	public void setButtonsEnableDisbleForPages(boolean enabled) {
		splitButton.setEnabled(enabled);
		reverseButton.setEnabled(enabled);
		deletePageButton.setEnabled(enabled);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == barcodeField) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				searchNodeFromBarcode();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
	}

	public JButton getHelpButton() {
		return helpButton;
	}

	public void setHelpButton(JButton helpButton) {
		this.helpButton = helpButton;
	}

	public JButton getImportButton() {
		return importButton;
	}

	public void setImportButton(JButton importButton) {
		this.importButton = importButton;
	}

	public JButton getCreateDocumentButton() {
		return createDocumentButton;
	}

	public void setCreateDocumentButton(JButton createDocumentButton) {
		this.createDocumentButton = createDocumentButton;
	}

	public JButton getDeleteDocumentButton() {
		return deleteDocumentButton;
	}

	public void setDeleteDocumentButton(JButton deleteDocumentButton) {
		this.deleteDocumentButton = deleteDocumentButton;
	}

	public JButton getViewThumbnailsButton() {
		return viewThumbnailsButton;
	}

	public void setViewThumbnailsButton(JButton viewThumbnailsButton) {
		this.viewThumbnailsButton = viewThumbnailsButton;
	}

	public JButton getEditNodePropertyButton() {
		return editNodePropertyButton;
	}

	public void setEditNodePropertyButton(JButton editNodePropertyButton) {
		this.editNodePropertyButton = editNodePropertyButton;
	}

	public JButton getSplitButton() {
		return splitButton;
	}

	public void setSplitButton(JButton splitButton) {
		this.splitButton = splitButton;
	}

	public JButton getCutButton() {
		return cutButton;
	}

	public void setCutButton(JButton cutButton) {
		this.cutButton = cutButton;
	}

	public JButton getPasteButton() {
		return pasteButton;
	}

	public void setPasteButton(JButton pasteButton) {
		this.pasteButton = pasteButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public JButton getReverseButton() {
		return reverseButton;
	}

	public void setReverseButton(JButton reverseButton) {
		this.reverseButton = reverseButton;
	}

	public JButton getResumeImportButton() {
		return resumeImportButton;
	}

	public void setResumeImportButton(JButton resumeImportButton) {
		this.resumeImportButton = resumeImportButton;
	}

	public JButton getDeletePageButton() {
		return deletePageButton;
	}

	public void setDeletePageButton(JButton deletePageButton) {
		this.deletePageButton = deletePageButton;
	}

	/**
	 * @return the imageUploader
	 */
	public ImageUploader getImageUploader() {
		return imageUploader;
	}

	/**
	 * @return the uploadErrorImageNameMap
	 */
	public HashMap<String, ArrayList<String>> getUploadErrorImageNameMap() {
		return uploadErrorImageNameMap;
	}

	public List<NodeProperties> getCutNodePropertyList() {
		return cutNodePropertyList;
	}

	public void setCutNodePropertyList(List<NodeProperties> cutNodePropertyList) {
		this.cutNodePropertyList = cutNodePropertyList;
	}

	public ImageViewer getImageViewer() {
		return imageViewer;
	}

	public void setImageViewer(ImageViewer imageViewer) {
		this.imageViewer = imageViewer;
	}

	public static NodeProperties getLastViewedThumbnailNodeProperty() {
		return lastViewedThumbnailNodeProperty;
	}

	public static void setLastViewedThumbnailNodeProperty(
			NodeProperties lastViewedThumbnailNodeProperty) {
		ImportSaparationPanel.lastViewedThumbnailNodeProperty = lastViewedThumbnailNodeProperty;
	}

	/**
	 * @return the cutNodeIndex
	 */
	public List<Integer> getCutNodeIndex() {
		return cutNodeIndex;
	}

	/**
	 * @param cutNodeIndex
	 *            the cutNodeIndex to set
	 */
	public void setCutNodeIndex(List<Integer> cutNodeIndex) {
		this.cutNodeIndex = cutNodeIndex;
	}

	/**
	 * @return the isNodeDeleted
	 */
	public boolean isNodeDeleted() {
		return isNodeDeleted;
	}

	/**
	 * @param isNodeDeleted
	 *            the isNodeDeleted to set
	 */
	public void setNodeDeleted(boolean isNodeDeleted) {
		this.isNodeDeleted = isNodeDeleted;
	}

	/**
	 * @return the mainPanel
	 */
	public JPanel getMainPanel() {
		return mainPanel;
	}

	/**
	 * @param mainPanel
	 *            the mainPanel to set
	 */
	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	/**
	 * @return the lastSelectedPageIndexeList
	 */
	public int[] getLastSelectedPageIndexeList() {
		return lastSelectedPageIndexeList;
	}

	/**
	 * @param lastSelectedPageIndexeList
	 *            the lastSelectedPageIndexeList to set
	 */
	public void setLastSelectedPageIndexeList(int[] lastSelectedPageIndexeList) {
		this.lastSelectedPageIndexeList = lastSelectedPageIndexeList;
	}

	/**
	 * @return the deletePageIndex
	 */
	public List<Integer> getDeletePageIndex() {
		return deletePageIndex;
	}

	/**
	 * @param deletePageIndex
	 *            the deletePageIndex to set
	 */
	public void setDeletePageIndex(List<Integer> deletePageIndex) {
		this.deletePageIndex = deletePageIndex;
	}

	/**
	 * @return the destinationNodeIndexToSelectPage
	 */
	public Integer getDestinationNodeIndexToSelectPage() {
		return destinationNodeIndexToSelectPage;
	}

	/**
	 * @param destinationNodeIndexToSelectPage
	 *            the destinationNodeIndexToSelectPage to set
	 */
	public void setDestinationNodeIndexToSelectPage(
			Integer destinationNodeIndexToSelectPage) {
		this.destinationNodeIndexToSelectPage = destinationNodeIndexToSelectPage;
	}

	/**
	 * @return the destinationNodePropertyToSelectPage
	 */
	public NodeProperties getDestinationNodePropertyToSelectPage() {
		return destinationNodePropertyToSelectPage;
	}

	/**
	 * @param destinationNodePropertyToSelectPage
	 *            the destinationNodePropertyToSelectPage to set
	 */
	public void setDestinationNodePropertyToSelectPage(
			NodeProperties destinationNodePropertyToSelectPage) {
		this.destinationNodePropertyToSelectPage = destinationNodePropertyToSelectPage;
	}

	/**
	 * @return the updatedNodePropertyToSelectPage
	 */
	public List<NodeProperties> getUpdatedNodePropertyListToSelectPage() {
		return updatedNodePropertyListToSelectPage;
	}

	/**
	 * @param updatedNodePropertyListToSelectPage
	 *            the updatedNodePropertyToSelectPage to set
	 */
	public void setUpdatedNodePropertyListToSelectPage(
			List<NodeProperties> updatedNodePropertyListToSelectPage) {
		this.updatedNodePropertyListToSelectPage = updatedNodePropertyListToSelectPage;
	}

	/**
	 * @return the upperPanel
	 */
	public JPanel getUpperPanel() {
		return upperPanel;
	}

	/**
	 * @return the asyncImageViewer
	 */
	public AsyncImageViewer getAsyncImageViewer() {
		return asyncImageViewer;
	}

	/**
	 * @return the uploadProgressPanel
	 */
	public JPanel getUploadProgressPanel() {
		return uploadProgressPanel;
	}

	/**
	 * @return the isCutNodesArePasted
	 */
	public boolean isCutNodesArePasted() {
		return isCutNodesArePasted;
	}

	/**
	 * @param isCutNodesArePasted
	 *            the isCutNodesArePasted to set
	 */
	public void setCutNodesArePasted(boolean isCutNodesArePasted) {
		this.isCutNodesArePasted = isCutNodesArePasted;
	}

}

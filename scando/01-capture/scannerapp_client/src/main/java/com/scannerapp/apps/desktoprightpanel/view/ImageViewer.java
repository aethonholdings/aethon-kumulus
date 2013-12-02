package com.scannerapp.apps.desktoprightpanel.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.log4j.Logger;
import org.imgscalr.Scalr;

import com.scannerapp.apps.desktopleftpanel.view.CustomMutableTreeNode;
import com.scannerapp.apps.desktopmainpanel.view.DesktopMainJPanel;
import com.scannerapp.apps.framework.view.BaseJPanel;
import com.scannerapp.apps.framework.view.ErrorMessage;
import com.scannerapp.apps.utils.ConstantUtil;
import com.scannerapp.apps.utils.SessionUtil;
import com.scannerapp.common.NodePropertyConstants;
import com.scannerapp.resources.IconRepository;
import com.scannerapp.shared.NodeProperties;
import com.sun.jersey.core.util.Base64;

/**
 * @author darshanm
 *
 */
@SuppressWarnings("serial")
public class ImageViewer extends BaseJPanel implements ListSelectionListener  {

	private static Logger log = Logger.getLogger(ImageViewer.class);// For Log4j
	private GridBagLayout mainPanelLayout;
	private JScrollPane thumbnailScrollPanel;

	private JPanel thumbnailActualImageViewPanel;
	private JScrollPane thumbnailActualImageViewScrollPanel;

	JSplitPane splitPanel;
	private CustomActualImageViewJLabel thumbnailActualImageViewLabel;
	private List<File> fileList;

	JList thumbnailList;
	DefaultListCellRenderer renderer;
	private DesktopMainJPanel desktopMainPanel;
	private List<NodeProperties> nodePropertyListForPages;
	private JLabel updatedViewThumbnailLabel;

	int currentImageResizeHeight;
	int currentImageResizeWidth;
	
	private ArrayList<NodeProperties> thumbnailPageNodePropertiesList = new ArrayList<NodeProperties>();
	private int lastFullScreenImageIndex = -1;
	private JLabel fullScreenImageLabel;
	JDialog fullScreenDialog = null;

	public ImageViewer() {
		this.setLayout(new GridBagLayout());
		initThumbnailPanel();
		initThumbnailViewPanel();
		initMainPanel();

	}

	public ImageViewer(List<File> listOfFiles,DesktopMainJPanel desktopMainPanel,List<NodeProperties> nodePropertyList) 
	{
		this.setLayout(new GridBagLayout());
		initThumbnailPanel();
		initThumbnailViewPanel();
		initMainPanel();

		fileList = listOfFiles;
		this.desktopMainPanel = desktopMainPanel;
		nodePropertyListForPages = nodePropertyList;
	}

	public ImageViewer(DesktopMainJPanel desktopMainPanel) 
	{
		log.info("Call 1 Arg Constructor.");
		this.desktopMainPanel = desktopMainPanel;
		initThumbnailPanel();
		initThumbnailViewPanel();
		initMainPanel();

		
	}

	public void initHandler() 
	{
		thumbnailList.addMouseListener(new ThumbnailClickActionListener());
		thumbnailList.addListSelectionListener((ListSelectionListener)this);
	}

	private void initThumbnailPanel() // Left Side Thumbnail View 
	{ 
		thumbnailScrollPanel = new JScrollPane();
		thumbnailScrollPanel.getVerticalScrollBar().setUnitIncrement(16);
		thumbnailScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);	
	}

	private void initThumbnailViewPanel() // Right Side View
	{
		thumbnailActualImageViewPanel = new JPanel();
		thumbnailActualImageViewPanel.setLayout(new GridBagLayout());

		thumbnailActualImageViewLabel = new CustomActualImageViewJLabel();
		thumbnailActualImageViewScrollPanel = new JScrollPane();
		/*thumbnailActualImageViewScrollPanel.setSize(new Dimension(300, 300));*/

		JButton fullScreen = new JButton();
		updatedViewThumbnailLabel = new JLabel("");

		fullScreen.setPreferredSize(new Dimension(120, 25));

		fullScreen.setText(ConstantUtil.getApplicationConstant("fullScreen"));
		fullScreen.setIcon(IconRepository.ICON_FULL_SCREEN);
		fullScreen.addActionListener(new FullScreenActionListener());

		thumbnailActualImageViewPanel.add(thumbnailActualImageViewLabel,
				new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.NONE,
						new Insets(0, 0, 0, 0), 0, 0));

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(updatedViewThumbnailLabel);
		buttonPanel.add(fullScreen);

		JPanel mainThumbnailImageViewPanel = new JPanel();
		mainThumbnailImageViewPanel.setLayout(new BorderLayout());
		mainThumbnailImageViewPanel.add(thumbnailActualImageViewPanel,
				BorderLayout.CENTER);
		mainThumbnailImageViewPanel.add(buttonPanel, BorderLayout.PAGE_END);	

		thumbnailActualImageViewScrollPanel.getViewport().add(
				mainThumbnailImageViewPanel,
				new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 0), 0, 0));

	}

	private void initMainPanel() 
	{
		mainPanelLayout = new GridBagLayout();

		this.setLayout(mainPanelLayout);

		splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true,
				thumbnailScrollPanel, thumbnailActualImageViewScrollPanel);
		splitPanel.setDividerLocation(140);

		splitPanel.setOneTouchExpandable(true);

		Dimension screenSize =  Toolkit.getDefaultToolkit().getScreenSize();
		int xSize = ((int) screenSize.getWidth());
		int ySize = ((int) screenSize.getHeight());

		/*// Code To Scale Split layout Panel As Per Resolution
		if (xSize < 1680 && xSize > 1440) {
			splitPanel.setPreferredSize(new Dimension(0, 630));
			splitPanel.setMinimumSize(new Dimension(0, 630));
		} else if (xSize > 1440) {
			splitPanel.setPreferredSize(new Dimension(0, 800));
			splitPanel.setMinimumSize(new Dimension(0, 800));
		} else if (xSize > 1280) {
			splitPanel.setPreferredSize(new Dimension(0, 650));
			splitPanel.setMinimumSize(new Dimension(0, 650));
		} else if (xSize <= 1280) {
			splitPanel.setPreferredSize(new Dimension(0, 470));
			splitPanel.setMinimumSize(new Dimension(0, 470));
		} else {
			splitPanel.setPreferredSize(new Dimension(0, 500));
			splitPanel.setMinimumSize(new Dimension(0, 500));
		}	*/
		
		
		splitPanel.setPreferredSize(new Dimension(0, (ySize-220)));
		splitPanel.setMaximumSize(new Dimension(0, (ySize-220)));
		splitPanel.setMinimumSize(new Dimension(0, (ySize-220)));
		this.add(splitPanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));
	}

	public void setImageThumbnails(List<File> listOfFiles) throws Exception {
		log.info("Set Image thumbnails");

		if (listOfFiles != null && listOfFiles.size() > 0) {

			DefaultListModel listModel = generateThumbnails(listOfFiles);
			
			thumbnailList = new JList(listModel);
			thumbnailList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			thumbnailList.setVisibleRowCount(-1);
			thumbnailList.setFixedCellHeight(110);
			thumbnailList.setFixedCellWidth(110);
			thumbnailList.setBackground(Color.WHITE);
			thumbnailList.setSelectionBackground(new Color(165, 210, 216));
			renderer = (DefaultListCellRenderer) thumbnailList
					.getCellRenderer();
			renderer.setHorizontalAlignment(JLabel.CENTER);

			initHandler();

			thumbnailScrollPanel.getViewport().add(thumbnailList);
		}

	}

	private DefaultListModel generateThumbnails(List<File> listOfFiles)
			throws Exception {
		CustomImageIcon icon = null;
		DefaultListModel listModel = new DefaultListModel();

		for (int index = 0; index < listOfFiles.size(); index++) {
			BufferedImage originalImage = ImageIO.read(new File(listOfFiles
					.get(index).getPath()));

			BufferedImage thumbnail = Scalr.resize(originalImage,
					Scalr.Method.SPEED, Scalr.Mode.FIT_EXACT, 100, 100,
					Scalr.OP_ANTIALIAS);
			icon = new CustomImageIcon(thumbnail);
			icon.setActualImagePath(listOfFiles.get(index).getPath());
			icon.setFullScreenImagePath(SessionUtil.getSessionData()
					.getLocalThumbnailDirPath()
					+ File.separator
					+ listOfFiles.get(index).getName());
			icon.setNodeProperty(nodePropertyListForPages.get(index));
			listModel.add(index, icon);
		}

		return listModel;

	}

	class ThumbnailClickActionListener extends MouseAdapter {
		private Image localImage;
		private File file;

		public ThumbnailClickActionListener(String thumbnailImagePath,
				String actualImagePath) {			
			file = new File(thumbnailImagePath);
			try {
				localImage = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public ThumbnailClickActionListener() {
			log.info("Selected: " + thumbnailList.getSelectedIndex());
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			viewThumbnailOnMouseClick(file,localImage);		
		}
	}

	public void viewLargeThumbnailImage(Object value, File file,
			Image localImage) throws IOException {
		Image thumb;
		ImageIcon icon = null;
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());

		int percentageX = (int) ((xSize * 72.39) / 100);
		int percentageY = (int) ((ySize * 64.81) / 100);
		
		file = new File(((CustomImageIcon) value).getActualImagePath());
		localImage = ImageIO.read(file);
		
		/*thumb = localImage.getScaledInstance(percentageX - 300,
				percentageY - 50, Image.SCALE_SMOOTH);*/
		
		thumb= Scalr.resize((BufferedImage)localImage,
				Scalr.Method.SPEED, Scalr.Mode.FIT_EXACT,percentageX-300,percentageY-50,
				Scalr.OP_ANTIALIAS);
		
		icon = new ImageIcon(thumb);
		thumbnailActualImageViewLabel.setIcon(icon);
		thumbnailActualImageViewLabel.setNodeProperty(((CustomImageIcon) value)
				.getNodeProperty());
		thumbnailActualImageViewLabel
				.setActualImagePath(((CustomImageIcon) value)
						.getFullScreenImagePath());
	}
	

	public void viewThumbnailOnMouseClick(File file, Image localImage) 
	{
		log.info("Selected: " + thumbnailList.getSelectedIndex());
		if (thumbnailList.getSelectedIndex() < 0) {
			desktopMainPanel.getjRightPanel().getImportAndSepPanel()
					.getCutButton().setEnabled(false);
			desktopMainPanel.getjRightPanel().getImportAndSepPanel()
					.getDeletePageButton().setEnabled(false);
			desktopMainPanel.getjRightPanel().getImportAndSepPanel()
					.getSplitButton().setEnabled(false);
			desktopMainPanel.getjRightPanel().getImportAndSepPanel()
					.getReverseButton().setEnabled(false);
			desktopMainPanel.getjRightPanel().getImportAndSepPanel()
					.getPasteButton().setEnabled(false);
			desktopMainPanel.getjRightPanel().getImportAndSepPanel()
					.getCancelButton().setEnabled(false);
			return;
		}

		desktopMainPanel.getjRightPanel().getImportAndSepPanel()
				.getCutButton().setEnabled(true);
		desktopMainPanel.getjRightPanel().getImportAndSepPanel()
				.getDeletePageButton().setEnabled(true);
		desktopMainPanel.getjRightPanel().getImportAndSepPanel()
				.getSplitButton().setEnabled(true);
		desktopMainPanel.getjRightPanel().getImportAndSepPanel()
				.getReverseButton().setEnabled(true);
		desktopMainPanel.getjRightPanel().getImportAndSepPanel()
				.getPasteButton().setEnabled(false);
		desktopMainPanel.getjRightPanel().getImportAndSepPanel()
				.getCancelButton().setEnabled(false);

		if (desktopMainPanel.getjRightPanel().getImportAndSepPanel()
				.getCutNodePropertyList() != null
				&& !desktopMainPanel.getjRightPanel()
						.getImportAndSepPanel().getCutNodePropertyList()
						.isEmpty()) {
			desktopMainPanel.getjRightPanel().getImportAndSepPanel()
					.getCutButton().setEnabled(false);
			desktopMainPanel.getjRightPanel().getImportAndSepPanel()
					.getPasteButton().setEnabled(true);
			desktopMainPanel.getjRightPanel().getImportAndSepPanel()
					.getCancelButton().setEnabled(true);
		}

		// Condition To Disable Button While Status Is Done or Sealed
		CustomMutableTreeNode selectedNode = desktopMainPanel
				.getjRightPanel().getCollectionPanel()
				.getSelectedTreeNode();
		if (selectedNode != null
				&& selectedNode.getNodeId() != SessionUtil.getSessionData()
						.getProjectId()) {
			NodeProperties selectedNodeproperty = desktopMainPanel
					.getjLeftPanel().getNodePropertiesMap()
					.get(selectedNode.getNodeId());
			if (selectedNodeproperty.getStatus().equals(
					NodePropertyConstants.DONE)
					|| selectedNodeproperty.getStatus().equals(
							NodePropertyConstants.SEALED)) {
				desktopMainPanel.getjRightPanel().getImportAndSepPanel()
						.getCutButton().setEnabled(false);
				desktopMainPanel.getjRightPanel().getImportAndSepPanel()
						.getDeletePageButton().setEnabled(false);
				desktopMainPanel.getjRightPanel().getImportAndSepPanel()
						.getSplitButton().setEnabled(false);
				desktopMainPanel.getjRightPanel().getImportAndSepPanel()
						.getReverseButton().setEnabled(false);
				desktopMainPanel.getjRightPanel().getImportAndSepPanel()
						.getPasteButton().setEnabled(false);
				desktopMainPanel.getjRightPanel().getImportAndSepPanel()
						.getCancelButton().setEnabled(false);
			}
		}

		Object value = thumbnailList.getModel().getElementAt(
				thumbnailList.getSelectedIndex());

		log.info(((CustomImageIcon) value).getActualImagePath());
		log.info(((CustomImageIcon) value).getNodeProperty().getNodeId());

		try {
			viewLargeThumbnailImage(value, file, localImage);
		} catch (Exception e1) {
			e1.printStackTrace();
		}	
	}

	private void generateImage(String fullLocalImagePath,
			String nodeId) {

		File imageFile = new File(fullLocalImagePath);
		if (imageFile.exists()) {
			return;
		} else {
			String encodedImageString = fetchEncodedString(nodeId);
			byte[] decodedImageString = Base64.decode(encodedImageString);

			try {
			imageFile.createNewFile();

			FileOutputStream scannedImageFileOutputStream = new FileOutputStream(
					imageFile);
			scannedImageFileOutputStream.write(decodedImageString);
			scannedImageFileOutputStream.close();
			}
			catch (Exception e) {
				log.error("Error while generating image for  Node ID : " + nodeId + "   Path : " + fullLocalImagePath);
				e.printStackTrace();
			}
		}

	}

	private String fetchEncodedString(String nodeId) {
		log.info(nodeId);
		return desktopMainPanel.getjRightPanel().getImportAndSepPanel().controller().getImportSaparationPanelHelper().getEncodedImageStringForActualImage(nodeId);
	}
	

	private void displayImage(String fullImagePath,
			JLabel fullScreenImageLabel, JButton zoomIn, JButton zoomOut)
			throws IOException {
		Image thumb;
		ImageIcon icon = null;
		BufferedImage image;

		File file = new File(fullImagePath);
		currentFile = file;

		// If File Not Exists
		if (!file.isFile()) {
			fullScreenImageLabel.add(
					new JLabel(ConstantUtil
							.getApplicationConstant("noImage")),
					new GridBagConstraints(0, 0, 0, 1, 0.0, 0.0,
							GridBagConstraints.CENTER,
							GridBagConstraints.NONE,
							new Insets(0, 0, 0, 0), 0, 0));
		} else {
			image = ImageIO.read(file);

			int imageHeight = image.getHeight(null);
			int imageWidth = image.getWidth(null);

			log.info("Image Height: " + imageHeight);
			log.info("Image Width: " + imageWidth);

			int totalPercentageToResize = Integer
					.parseInt(ConstantUtil
							.getApplicationConstant("initialFullScreenZoomingPercentage"));

			currentImageResizeHeight = imageHeight;
			currentImageResizeWidth = imageWidth;

			currentImageResizeHeight = currentImageResizeHeight
					+ ((currentImageResizeHeight * totalPercentageToResize) / 100);
			currentImageResizeWidth = currentImageResizeWidth
					+ ((currentImageResizeWidth * totalPercentageToResize) / 100);

			thumb = image.getScaledInstance(currentImageResizeWidth,
					currentImageResizeHeight, Image.SCALE_SMOOTH);
			icon = new ImageIcon(thumb);

			fullScreenImageLabel.setIcon(icon);

			zoomIn.addActionListener(new ZoomInButtonActionListemer(image,
					currentFile, fullScreenImageLabel));
			zoomOut.addActionListener(new ZoomOutButtonActionListemer(
					image, currentFile, fullScreenImageLabel));

		}
	}

	class FullScreenActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// Get Selected Node
			CustomMutableTreeNode selectedNode = desktopMainPanel
					.getjRightPanel().getCollectionPanel()
					.getSelectedTreeNode();

			NodeProperties selectedThumbnailNodeProperty = thumbnailActualImageViewLabel
					.getNodeProperty();
			
			if(selectedThumbnailNodeProperty==null)
			{
				ErrorMessage.displayMessage('I', "selectImageToShowInFullScreen");
				return;
			}

			String fullImagePath = SessionUtil.getSessionData()
					.getLocalThumbnailDirPath()
					+ File.separator
					+ thumbnailActualImageViewLabel.getNodeProperty().getName();

			// adding logic to get the POJO index.
			for(int index = 0; index < thumbnailPageNodePropertiesList.size(); index++) {
				
				if(selectedThumbnailNodeProperty.getNodeId().equals(thumbnailPageNodePropertiesList.get(index).getNodeId())) {
					lastFullScreenImageIndex = index;
					break;
				}
			}
			
			
			try {
				generateImage(fullImagePath, selectedThumbnailNodeProperty.getNodeId());
			} catch (Exception e1) {
				log.info("Exception generated While Generate image for Node: "
						+ selectedThumbnailNodeProperty.getNodeId());
				e1.printStackTrace();
			}

			fullScreenDialog=initFullScreenDialog(fullImagePath, nodePropertyListForPages,selectedNode.getNodeId());

		}



		public JDialog initFullScreenDialog(String fullImagePath,
				List<NodeProperties> nodePropertyListForPages, String nodeId) {
			JDialog fullScreenView = new JDialog();
			JScrollPane scrollPaneFullScreenView = new JScrollPane();
			JPanel fullScreenPanel = new JPanel();
			JLabel fullScreenImageLabelInner = new JLabel();

			fullScreenImageLabel = fullScreenImageLabelInner;
			
			scrollPaneFullScreenView
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPaneFullScreenView
					.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

			fullScreenView.setLayout(new GridBagLayout());
			fullScreenView.setTitle("Image Viewer");

			JPanel buttonPanel = new JPanel();
			JButton next = new JButton();
			JButton previous = new JButton();
			JButton zoomIn = new JButton();
			JButton zoomOut = new JButton();

			next.setText(ConstantUtil.getApplicationConstant("next"));
			next.setIcon(IconRepository.ICON_NEXT);
			next.setPreferredSize(new Dimension(170, 25));

			previous.setPreferredSize(new Dimension(170, 25));
			previous.setText(ConstantUtil.getApplicationConstant("previous"));
			previous.setIcon(IconRepository.ICON_PREVIOUS);

			zoomIn.setText(ConstantUtil.getApplicationConstant("zoomIn"));
			zoomIn.setIcon(IconRepository.ICON_ZOOMIN);
			zoomIn.setPreferredSize(new Dimension(170, 25));

			zoomOut.setText(ConstantUtil.getApplicationConstant("zoomOut"));
			zoomOut.setIcon(IconRepository.ICON_ZOOMOUT);
			zoomOut.setPreferredSize(new Dimension(170, 25));

			buttonPanel.add(previous);
			buttonPanel.add(next);
			buttonPanel.add(zoomIn);
			buttonPanel.add(zoomOut);

			fullScreenPanel.setLayout(new GridBagLayout());
			fullScreenPanel.add(fullScreenImageLabelInner, new GridBagConstraints(0,
					0, 1, 0, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			scrollPaneFullScreenView.getViewport().add(fullScreenPanel);

			fullScreenView.add(scrollPaneFullScreenView,
					new GridBagConstraints(0, 0, 0, 1, 1.0, 1.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 0, 0), 0, 0));
			fullScreenView.add(buttonPanel, new GridBagConstraints(0, 1, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

			
			previous.addActionListener(new PreviousButtonActionListemer(
			fullScreenImageLabelInner, previous, next));
			
			next.addActionListener(new NextButtonActionListemer(
					fullScreenImageLabelInner, previous, next));
			
			
			if(thumbnailPageNodePropertiesList.size() == 1) {
				previous.setEnabled(false);
				next.setEnabled(false);
			} else if(lastFullScreenImageIndex == thumbnailPageNodePropertiesList.size() - 1) {
				previous.setEnabled(true);
				next.setEnabled(false);
			} else if(lastFullScreenImageIndex == 0) {
				previous.setEnabled(false);
				next.setEnabled(true);
			}
			
			try {
				displayImage(fullImagePath, fullScreenImageLabelInner, zoomIn,
						zoomOut);
			} catch (IOException e) {
				log.info("Exception generated While Displaying image from path: "
						+ fullImagePath);
				e.printStackTrace();
			}

			Toolkit tk = Toolkit.getDefaultToolkit();
			int xSize = ((int) tk.getScreenSize().getWidth());
			int ySize = ((int) tk.getScreenSize().getHeight());
			fullScreenView.setSize(xSize, ySize - 50);
			fullScreenView.show();

			return fullScreenView;
		}

	}

	File currentFile;

	class PreviousButtonActionListemer implements ActionListener {
		
		private JButton previousButton;
		private JButton nextButton;

		public PreviousButtonActionListemer(JLabel currentFullScreenImageLabel, JButton previousButton, JButton nextButton) {
			this.previousButton = previousButton;
			this.nextButton = nextButton;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			lastFullScreenImageIndex = lastFullScreenImageIndex - 1;
			
			if(lastFullScreenImageIndex == 0) {
				previousButton.setEnabled(false);
				nextButton.setEnabled(true);
			} else if (lastFullScreenImageIndex < thumbnailPageNodePropertiesList.size() - 1) {
				previousButton.setEnabled(true);
				nextButton.setEnabled(true);
			}
			
			
			NodeProperties previousImageNodeProperties = thumbnailPageNodePropertiesList.get(lastFullScreenImageIndex);
			
			String fullLocalImagePath = SessionUtil.getSessionData()
											.getLocalThumbnailDirPath()
											+ File.separator
											+ previousImageNodeProperties.getName();
			
			generateImage(fullLocalImagePath, previousImageNodeProperties.getNodeId());
			

			try {
				displayImage(fullLocalImagePath, fullScreenImageLabel, new JButton(),
						new JButton());
			} catch (IOException exception) {
				log.info("Exception generated While Displaying image from path: "
						+ fullLocalImagePath);
				exception.printStackTrace();
			}
		}

	}

	class NextButtonActionListemer implements ActionListener {
		private JButton previousButton;
		private JButton nextButton;

		public NextButtonActionListemer(JLabel currentFullScreenImageLabel, JButton previousButton, JButton nextButton) {
			this.previousButton = previousButton;
			this.nextButton = nextButton;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			

			lastFullScreenImageIndex = lastFullScreenImageIndex + 1;
			
			if(lastFullScreenImageIndex == thumbnailPageNodePropertiesList.size() - 1) {
				previousButton.setEnabled(true);
				nextButton.setEnabled(false);
			} else if (lastFullScreenImageIndex < thumbnailPageNodePropertiesList.size() - 1) {
				previousButton.setEnabled(true);
				nextButton.setEnabled(true);
			}
			
			
			NodeProperties previousImageNodeProperties = thumbnailPageNodePropertiesList.get(lastFullScreenImageIndex);
			
			String fullLocalImagePath = SessionUtil.getSessionData()
											.getLocalThumbnailDirPath()
											+ File.separator
											+ previousImageNodeProperties.getName();
			
			generateImage(fullLocalImagePath, previousImageNodeProperties.getNodeId());
			

			try {
				displayImage(fullLocalImagePath, fullScreenImageLabel, new JButton(),
						new JButton());
			} catch (IOException exception) {
				log.info("Exception generated While Displaying image from path: "
						+ fullLocalImagePath);
				exception.printStackTrace();
			}

		}
	}

	class ZoomInButtonActionListemer implements ActionListener {
		JLabel zoomInFullScreenImageLabel;
		BufferedImage fullScreenImage;

		public ZoomInButtonActionListemer(BufferedImage image, File file,
				JLabel fullScreenImageLabel) {
			zoomInFullScreenImageLabel = fullScreenImageLabel;
			fullScreenImage = image;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			//log.info("Zoom In");
			//log.info("File Path: " + currentFile.getPath());

			//log.info("Image height: " + fullScreenImage.getHeight());
			//log.info("Image width: " + fullScreenImage.getWidth());

			currentImageResizeHeight = currentImageResizeHeight
					+ ((currentImageResizeHeight * 20) / 100);
			currentImageResizeWidth = currentImageResizeWidth
					+ ((currentImageResizeWidth * 20) / 100);

			zoomInFullScreenImageLabel.removeAll();
			setFullScreenImageAfterResize(zoomInFullScreenImageLabel,
					currentFile, currentImageResizeHeight,
					currentImageResizeWidth);

		}

	}

	class ZoomOutButtonActionListemer implements ActionListener {
		JLabel zoomOutFullScreenImageLabel;
		BufferedImage fullScreenImage;

		public ZoomOutButtonActionListemer(BufferedImage image, File file,
				JLabel fullScreenImageLabel) {
			zoomOutFullScreenImageLabel = fullScreenImageLabel;
			fullScreenImage = image;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			//log.info("Zoom Out");
			//log.info("File Path: " + currentFile.getPath());

			//log.info("Image height: " + fullScreenImage.getHeight());
			//log.info("Image width: " + fullScreenImage.getWidth());

			currentImageResizeHeight = currentImageResizeHeight
					- ((currentImageResizeHeight * 20) / 100);
			currentImageResizeWidth = currentImageResizeWidth
					- ((currentImageResizeWidth * 20) / 100);

			zoomOutFullScreenImageLabel.removeAll();
			setFullScreenImageAfterResize(zoomOutFullScreenImageLabel,
					currentFile, currentImageResizeHeight,
					currentImageResizeWidth);

		}

	}

	public void setFullScreenImageAfterResize(JLabel fullScreenImageLabel,
			File currentFile, int currentImageResizeHeight,
			int currentImageResizeWidth) {

		Image thumb;
		ImageIcon icon = null;
		BufferedImage image;

		try {
			image = ImageIO.read(currentFile);
			/*thumb = image.getScaledInstance(currentImageResizeWidth,
					currentImageResizeHeight, Image.SCALE_SMOOTH);*/
			thumb= Scalr.resize(image,
					Scalr.Method.SPEED, Scalr.Mode.FIT_EXACT, currentImageResizeWidth, currentImageResizeHeight,
					Scalr.OP_ANTIALIAS);
			icon = new ImageIcon(thumb);
			
			fullScreenImageLabel.setIcon(icon);
		} catch (IOException e) {
			log.error("Error Generated While Set Image After Resize");
			e.printStackTrace();
		}
	}

	public JList getThumbnailList() {
		return thumbnailList;
	}

	public void setThumbnailList(JList thumbnailList) {
		this.thumbnailList = thumbnailList;
	}

	/**
	 * @return the splitPanel
	 */
	public JSplitPane getSplitPanel() {
		return splitPanel;
	}

	/**
	 * @param splitPanel
	 *            the splitPanel to set
	 */
	public void setSplitPanel(JSplitPane splitPanel) {
		this.splitPanel = splitPanel;
	}

	/**
	 * @return the thumbnailScrollPanel
	 */
	public JScrollPane getThumbnailScrollPanel() {
		return thumbnailScrollPanel;
	}

	/**
	 * @param thumbnailScrollPanel
	 *            the thumbnailScrollPanel to set
	 */
	public void setThumbnailScrollPanel(JScrollPane thumbnailScrollPanel) {
		this.thumbnailScrollPanel = thumbnailScrollPanel;
	}

	/**
	 * @return the nodePropertyListForPages
	 */
	public List<NodeProperties> getNodePropertyListForPages() {
		return nodePropertyListForPages;
	}

	/**
	 * @param nodePropertyListForPages
	 *            the nodePropertyListForPages to set
	 */
	public void setNodePropertyListForPages(
			List<NodeProperties> nodePropertyListForPages) {
		this.nodePropertyListForPages = nodePropertyListForPages;
	}

	/**
	 * @return the updatedViewThumbnailLabel
	 */
	public JLabel getUpdatedViewThumbnailLabel() {
		return updatedViewThumbnailLabel;
	}

	/**
	 * @param updatedViewThumbnailLabel
	 *            the updatedViewThumbnailLabel to set
	 */
	public void setUpdatedViewThumbnailLabel(JLabel updatedViewThumbnailLabel) {
		this.updatedViewThumbnailLabel = updatedViewThumbnailLabel;
	}

	/**
	 * @return the thumbnailPageNodePropertiesList
	 */
	public ArrayList<NodeProperties> getThumbnailPageNodePropertiesList() {
		return thumbnailPageNodePropertiesList;
	}

	/**
	 * @param thumbnailPageNodePropertiesList the thumbnailPageNodePropertiesList to set
	 */
	public void setThumbnailPageNodePropertiesList(
			ArrayList<NodeProperties> thumbnailPageNodePropertiesList) {
		this.thumbnailPageNodePropertiesList = thumbnailPageNodePropertiesList;
	}

	/**
	 * @return the fullScreenDialog
	 */
	public JDialog getFullScreenDialog() {
		return fullScreenDialog;
	}

	/**
	 * @param fullScreenDialog the fullScreenDialog to set
	 */
	public void setFullScreenDialog(JDialog fullScreenDialog) {
		this.fullScreenDialog = fullScreenDialog;
	}

	
	@Override
	public void valueChanged(ListSelectionEvent e) 
	{
		
		 int selectedIndex=thumbnailList.getSelectedIndex();			 
		 
		 Object value = thumbnailList.getModel().getElementAt(selectedIndex);
		 //log.info(((CustomImageIcon) value).getActualImagePath());
		 String thumbnailImagePath = ((CustomImageIcon) value).getActualImagePath();
		 Image localImageToViewLargeThumbnail = null;
		 File thumbnailFile;
		
		 thumbnailFile = new File(thumbnailImagePath);
			try 
			{
				localImageToViewLargeThumbnail = ImageIO.read(thumbnailFile);
				viewLargeThumbnailImage(value, thumbnailFile, localImageToViewLargeThumbnail);
			}
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}				
	}
		
}

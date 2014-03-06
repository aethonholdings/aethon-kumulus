package com.scannerapp.apps.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.imgscalr.Scalr;

import com.scannerapp.apps.application.ApplicationConstants;
import com.scannerapp.apps.desktopleftpanel.view.CustomMutableTreeNode;
import com.scannerapp.apps.desktopmainpanel.view.DesktopMainJPanel;
import com.scannerapp.apps.desktoprightpanel.view.CustomImageIcon;
import com.scannerapp.apps.desktoprightpanel.view.ImageViewer;
import com.scannerapp.apps.desktoprightpanel.view.ImportSaparationPanel;
import com.scannerapp.apps.desktoprightpanel.view.ImportSaparationPanelHelper;
import com.scannerapp.apps.framework.view.ErrorMessage;
import com.scannerapp.common.NodePropertyConstants;
import com.scannerapp.shared.NodeProperties;
import com.sun.jersey.core.util.Base64;

public class AsyncImageViewer extends SwingWorker<Void, NodeProperties> {


	private ImageViewer imageViewer;
	private final CustomMutableTreeNode selectedNode;
	private final DesktopMainJPanel desktopMainPanel;
	private final String localDirectoryPath;
	private final ImportSaparationPanel importSaparationPanel;
	private final ImportSaparationPanelHelper importSaparationPanelHelper;
	
	int displayedThumbnails=0;
	int totalThumbnails=0;
	
	private static Logger log = Logger.getLogger(AsyncImageViewer.class);
	
	public AsyncImageViewer(String localDirectoryPath, ImageViewer imageViewer, CustomMutableTreeNode selectedNode, DesktopMainJPanel desktopMainPanel, ImportSaparationPanelHelper importSaparationPanelHelper) {
		this.localDirectoryPath = localDirectoryPath;
		this.imageViewer = imageViewer;
		this.selectedNode = selectedNode;
		this.desktopMainPanel = desktopMainPanel;		
		this.importSaparationPanel = desktopMainPanel.getjRightPanel().getImportAndSepPanel();
		this.importSaparationPanelHelper =importSaparationPanelHelper;
		displayedThumbnails=0;
		totalThumbnails=0;
	}
	
	@Override
	protected Void doInBackground() throws Exception 
	{
                // -- KONS CODE - FOLLOWING SPEC CODE IS DEPRECATED, PAGE NODES NOW ONLY HAVE ONE PAGE BY DEFAULT
		// int totalChildNodeCount = importSaparationPanel.controller().getChildNodeCount(selectedNode.getNodeId());
                int totalChildNodeCount = 1;                
                // -- END KONS CODE
                
		int batchSize = Integer.parseInt(ConstantUtil.getApplicationConstant("viewThumbnailBatchSize"));

		totalThumbnails=totalChildNodeCount;
		
		imageViewer.getUpdatedViewThumbnailLabel().setText(ConstantUtil.getApplicationConstant("thumbnailsRetrived") + " : " + displayedThumbnails + " / " + totalChildNodeCount);		
		
		//log.info("Async Image Viewer -> Total Child Node For Selected Node: " + totalChildNodeCount);
		//log.info("Async Image Viewer -> Batch Size: " + batchSize);
		 
		if(totalChildNodeCount>0)
		{
			imageViewer.getThumbnailPageNodePropertiesList().clear();
			
			int rangeStart=1;
			int rangeEnd = batchSize;			
			int totalCall = (totalChildNodeCount / batchSize);
		
			if(rangeEnd>totalChildNodeCount)
			{
				totalCall = 1;
				rangeEnd = totalChildNodeCount;
			}
			/*log.info("Async Image Viewer -> Total Call To DB : " + totalCall);
			log.info("Async Image Viewer -> Initial Range Start : " + rangeStart);
			log.info("Async Image Viewer -> Initial Range End : " + rangeEnd);*/
			log.info("====== START VIEW THUMBNAILS ======");
			for(int i=0;i<totalCall;i++)
			{		
				//log.info("Call Loop : " + i);
				//log.info("Async Image Viewer -> "+" Call : "+ i +" Fetch Range Start : " + rangeStart);
				//log.info("Async Image Viewer -> "+" Call : "+ i +" Fetch Range End : " + rangeEnd);				
				
				ArrayList<String> idList = new ArrayList<String>();
				idList.add(SessionUtil.getSessionData().getProjectId());
				idList.add(selectedNode.getNodeId());
				idList.add(String.valueOf(rangeStart));
				idList.add(String.valueOf(rangeEnd));
				
				//log.info("=> START Fetch " + rangeStart + " To " + rangeEnd + " Records");
				List<NodeProperties> nodePropertyList = importSaparationPanelHelper.fetchChildNodeList(idList);
				//log.info("=> END Fetch " + rangeStart + " To " + rangeEnd + " Records");
				
				if (nodePropertyList == null)
					return null;				
				// displayedThumbnails = rangeEnd;
				displayedThumbnails = displayedThumbnails + nodePropertyList.size();
				
				publilshNodePropertyList(nodePropertyList);				
				Thread.sleep(500);
				//log.info("End publish records");
					//log.info("Async Image Viewer -> Fetch Call : " + rangeStart);
					//log.info("Async Image Viewer -> Fetch Range End : " + rangeEnd);
					
					rangeStart=rangeEnd+1;
					rangeEnd=rangeEnd+batchSize;
					
					if(rangeEnd>totalChildNodeCount)
					{																		
						rangeEnd = totalChildNodeCount;
						
						idList.set(2, String.valueOf(rangeStart));
						idList.set(3, String.valueOf(rangeEnd));
						// displayedThumbnails = rangeEnd;
						List<NodeProperties> nodePropertyListLastCall = importSaparationPanelHelper.fetchChildNodeList(idList);
						
						displayedThumbnails = displayedThumbnails + nodePropertyListLastCall.size();
						
						if (nodePropertyListLastCall == null)
							return null;				
						
						publilshNodePropertyList(nodePropertyListLastCall);
						Thread.sleep(500);
					}
					
			}	
			log.info("====== END VIEW THUMBNAILS ======");
			
			// Code To Make Image Selected After Any Operation Performed i.e reverse
			if(importSaparationPanel.getLastSelectedPageIndexeList()!=null)
			{			
				imageViewer.getThumbnailList().setSelectedIndices(importSaparationPanel.getLastSelectedPageIndexeList());
				imageViewer.getThumbnailList().ensureIndexIsVisible(importSaparationPanel.getLastSelectedPageIndexeList()[0]);
				importSaparationPanel.setLastSelectedPageIndexeList(null);
			}
			
			// Code To Make Image Selected After Any Operation Performed i.e delete
			if(importSaparationPanel.isNodeDeleted()==true && importSaparationPanel.getDeletePageIndex()!=null)
			{			
				importSaparationPanel.setSelectedPageAfterDelete(importSaparationPanel.getDeletePageIndex(),selectedNode);
				importSaparationPanel.setNodeDeleted(false);
			}
			
			// Code To Make Image Selected After Any Operation Performed i.e cut/paste
			if(importSaparationPanel.getCutNodeIndex()!=null && importSaparationPanel.getDestinationNodePropertyToSelectPage()!=null && importSaparationPanel.getUpdatedNodePropertyListToSelectPage()!=null)
			{			
				importSaparationPanel.setSelectedPageAfterPaste(importSaparationPanel.getDestinationNodeIndexToSelectPage(),importSaparationPanel.getDestinationNodePropertyToSelectPage(),importSaparationPanel.getUpdatedNodePropertyListToSelectPage());
				//importSaparationPanel.setCutNodePropertyList(null);
				//importSaparationPanel.setCutNodeIndex(null);
			}
			if(importSaparationPanel.isCutNodesArePasted())
			{
				importSaparationPanel.setCutNodePropertyList(null);
				importSaparationPanel.setCutNodeIndex(null);
				importSaparationPanel.setCutNodesArePasted(false);
			}
			
			desktopMainPanel.getjLeftPanel().enableDisableImportSeparationButtons(desktopMainPanel.getjLeftPanel().getNodePropertiesMap().get(selectedNode.getNodeId()));
		}

		/*for(int i=0;i<500;i++)
		{
			List<NodeProperties> nodePropertyList = importSaparationPanel.fetchChildNodes(selectedNode);
			Thread.sleep(50);
			publilshNodePropertyList(nodePropertyList);
			
		}*/
			
		return null;
	}
	private void publilshNodePropertyList(List<NodeProperties> nodePropertyList) {

		// Condition TO Check Node Has A Child / Pages TO Display
		if (nodePropertyList != null && nodePropertyList.size() > 0) 
		{		
			imageViewer.getThumbnailPageNodePropertiesList().addAll(nodePropertyList);
			publish(nodePropertyList.toArray(new NodeProperties[nodePropertyList.size()]));					
		}		
	}

	
	@Override
	protected void process(List<NodeProperties> nodePropertyList) 
	{
		try {
			imageViewer.setNodePropertyListForPages(nodePropertyList);
			updateImageThumbnails(localDirectoryPath, nodePropertyList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void updateImageThumbnails(String localThumbnailDirectoryPath,List<NodeProperties> nodePropertyList) throws Exception 
	{
		//log.info("######### Before List Box Count: " + imageViewer.getThumbnailList().getModel().getSize());
		
		// Generate Thumbnails
		for (int index = 0; index < nodePropertyList.size(); index++) 
		{

			//log.info("=> START Generating Thumbnails on Local Machine");
			NodeProperties nodeProperties = nodePropertyList.get(index);

			File imageFile = new File(localThumbnailDirectoryPath + nodeProperties.getName());
			//log.info("File Path : " + imageFile.getPath());
			String encodedImageString = nodeProperties.getEncodeStringForThumbnail();
			byte[] decodedImageString = Base64.decode(encodedImageString);

			// Change the concept to write image file from ImageIO to
			// FileOutputStream
			FileUtils.touch(imageFile);

			FileOutputStream scannedImageFileOutputStream = new FileOutputStream(imageFile);
			scannedImageFileOutputStream.write(decodedImageString);
			scannedImageFileOutputStream.close();								
			//log.info("=> END Generate Thumbnails on Local Machine");
			
			//log.info("=> START Displaing Thumbnails ");
		
			// Set List Items
			CustomImageIcon icon = null;
			
			BufferedImage originalImage = ImageIO.read(new File(imageFile.getPath()));
			BufferedImage thumbnail = Scalr.resize(originalImage,Scalr.Method.SPEED, Scalr.Mode.FIT_EXACT, 100, 100,Scalr.OP_ANTIALIAS);
			icon = new CustomImageIcon(thumbnail);
			icon.setActualImagePath(imageFile.getPath());
			icon.setFullScreenImagePath(SessionUtil.getSessionData().getLocalThumbnailDirPath()+ File.separator+ imageFile.getName());
			icon.setNodeProperty(nodeProperties);
			
			((DefaultListModel)imageViewer.getThumbnailList().getModel()).addElement(icon);
			imageViewer.getThumbnailList().validate();
			imageViewer.getThumbnailList().repaint();	

			imageViewer.getUpdatedViewThumbnailLabel().setText(ConstantUtil.getApplicationConstant("thumbnailsRetrived") + " : " + displayedThumbnails + " / " + totalThumbnails);
			//log.info("=> END Display Thumbnails ");
					
		}		
		
		//NodeProperties selectedNodeProperty = desktopMainPanel.getjLeftPanel().getNodePropertiesMap().get(selectedNode.getNodeId());
		//desktopMainPanel.getjLeftPanel().enableDisableImportSeparationButtons(selectedNodeProperty);
		
		//log.info("######### After List Box Count: " + imageViewer.getThumbnailList().getModel().getSize());
	}
	
/**
	 * Method To Check That If The Total Thumbnail And Displayed Thumbnail Counter Is Same Or Not
	 * @return
	 */
	public boolean isAllowedToViewThumbnail() 
	{
		boolean isAllowToViewThumbnail = false;
		if(displayedThumbnails==totalThumbnails)
		{
			isAllowToViewThumbnail=true; 
		}
		return isAllowToViewThumbnail;
	}
	
	/**
	 * @return the displayedThumbnails
	 */
	public int getDisplayedThumbnails() {
		return displayedThumbnails;
	}

	/**
	 * @return the totalThumbnails
	 */
	public int getTotalThumbnails() {
		return totalThumbnails;
	}

}

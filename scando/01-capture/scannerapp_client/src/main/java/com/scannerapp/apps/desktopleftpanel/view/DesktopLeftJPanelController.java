package com.scannerapp.apps.desktopleftpanel.view;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTree;

import org.apache.log4j.Logger;

import com.scannerapp.apps.desktoprightpanel.view.CollectionPanel;
import com.scannerapp.apps.framework.view.BaseController;
import com.scannerapp.apps.utils.GeneralUtils;
import com.scannerapp.apps.utils.SessionUtil;
import com.scannerapp.common.SessionInfo;
import com.scannerapp.shared.NodeProperties;


public class DesktopLeftJPanelController extends BaseController 
{
 private static Logger log = Logger.getLogger(DesktopLeftJPanelController.class);//For Log4j	
  private DesktopLeftHelper desktopLeftHelper;
  protected SessionInfo sessionInfo;
    
  public DesktopLeftJPanelController(DesktopLeftJPanel jPanel) {
    super(jPanel);
    desktopLeftHelper = new DesktopLeftHelper();
  }

  public void initialize() {
    super.initialize();
  }

  public DesktopLeftJPanel view() {
    return (DesktopLeftJPanel) getView();
  }

  public void initializeScreen() 
  {
    ArrayList initData = new ArrayList();
    try 
    {
      initData = desktopLeftHelper.initializeScreen();
      if (initData != null) 
      {                        
        sessionInfo = SessionInfo.getInstance();        
        initData = null;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private Hashtable getScreenUOAHashtable(Hashtable rights) {
    return GeneralUtils.getScreenUOAHashtable(rights);
  }

public void getNodeProperty() 
{
	
}

public void getAndSetNodePropertyValue() 
{
	
	JTree nodeTree=view().getNodeTree();		
	CustomMutableTreeNode selectedElement =(CustomMutableTreeNode)nodeTree.getSelectionPath().getLastPathComponent();
	
	log.info("Selected Tree Node: " + selectedElement);
	log.info("Selected Tree Node Object: " + selectedElement.getUserObject());
	log.info("Project Node: " + view().getProjectNode());
	
	System.out.println("********** " + selectedElement.getNodeId());
	
	NodeProperties nodeProperty = view().getNodePropertiesMap().get(selectedElement.getNodeId());
	
	//NodeProperties nodeProperty=desktopLeftHelper.getNodePropertyValue(selectedElement.getNodeId());
	
	setNodePropertyToLeftPanel(nodeProperty);
	setNodePropertyToRightFields(nodeProperty);
	
}

private void setNodePropertyToRightFields(NodeProperties nodeProperty) 
{
	CollectionPanel collectionPanel=view().getDesktopMainPanel().getjRightPanel().getCollectionPanel();
	collectionPanel.getBarCodeFeild().setText(nodeProperty.getBarcode());
	collectionPanel.getCommentFeild().setText(nodeProperty.getComment());
	collectionPanel.getIntCommentFeild().setText(nodeProperty.getInternalComment());
		
	Map<String, String> nodeTypeMap = SessionUtil.getSessionData().getNodeTypeMap();
	String type = getKeyFromMap(nodeTypeMap, nodeProperty.getType());	
	collectionPanel.getNodeTypeList().setSelectedValue(type,true);
	
	Map<String, String> statusMap = SessionUtil.getSessionData().getStatusMap();
	String status = getKeyFromMap(statusMap, nodeProperty.getStatus());
	collectionPanel.getStatusList().setSelectedValue(status,true);
	
}

private void setNodePropertyToLeftPanel(NodeProperties nodeProperty)
{
	log.info("Set Node Property left");
	view().getNameLabelValue().setText(nodeProperty.getName());
	view().getBarCodeLabelValue().setText(nodeProperty.getBarcode());
	// Set Comment
	setCommentAndInternalCommentValue(nodeProperty.getComment(),view().getCommnetLabelValue());
	// Set internal Comment
	setCommentAndInternalCommentValue(nodeProperty.getInternalComment(),view().getIntCommentLabelValue());
	
	Map<String, String> statusMap = SessionUtil.getSessionData().getStatusMap();
	String status = getKeyFromMap(statusMap, nodeProperty.getStatus());		
	view().getStatusLabelValue().setText(status);
	
	Map<String, String> nodeTypeMap = SessionUtil.getSessionData().getNodeTypeMap();
	String type = getKeyFromMap(nodeTypeMap, nodeProperty.getType());
	view().getNodeTypeLabelValue().setText(type);	
}

//Methods Which Returns the Value of Passed Key
private String getKeyFromMap(Map<String, String> map, String passedKey) {
	for (String key : map.keySet()) {
		if (map.get(key).equals(passedKey))
			return key;
	}
	return "";
}


private void setCommentAndInternalCommentValue(String comment, JTextArea commentLabel) 
{
	
/*	if(comment.length()>30)
	{
		String commentPart1=comment.substring(0,30);
		String commentPart2=comment.substring(30,comment.length());
	
		commentLabel.setText(commentPart1);				
		if(comment.length()>60)
			commentPart2+="...";
		wrappedCommentLabel.setText(commentPart2);
			
	}			
	else
		commentLabel.setText(comment);*/
	
	commentLabel.setText(comment);
	commentLabel.setToolTipText(comment);	
}

public ArrayList<NodeProperties> getChildNodePropertiesList(ArrayList<String> idList) {
	
	return desktopLeftHelper.getChildNodePropertiesList(idList);
}

} //End of Class
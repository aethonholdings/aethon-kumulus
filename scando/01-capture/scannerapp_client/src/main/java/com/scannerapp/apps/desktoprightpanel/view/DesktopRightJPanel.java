package com.scannerapp.apps.desktoprightpanel.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultEditorKit.CutAction;
import javax.swing.tree.TreePath;

import org.apache.log4j.Logger;

import com.scannerapp.apps.desktopleftpanel.view.CustomMutableTreeNode;
import com.scannerapp.apps.desktopmainpanel.view.DesktopMainJPanel;
import com.scannerapp.apps.framework.view.BaseJPanel;
import com.scannerapp.apps.framework.view.ErrorMessage;
import com.scannerapp.apps.utils.SessionUtil;
import com.scannerapp.apps.utils.XMLBundleReader;
import com.scannerapp.common.IMModule;
import com.scannerapp.common.IMScreen;
import com.scannerapp.common.SessionInfo;
import com.scannerapp.common.XMLBundle;


/**
 * @author darshanm
 *
 */
public class DesktopRightJPanel extends BaseJPanel implements ChangeListener{

	private static Logger log = Logger.getLogger(DesktopRightJPanel.class);// For Log4j
	
  protected boolean logout = false;
  protected boolean flag = true;
  
  
  protected Hashtable hBundle = null; 
  private GridBagLayout gridBagLayout=new GridBagLayout();
  private BorderLayout borderLayout=new BorderLayout();
  private JTabbedPane tabbedPane = new JTabbedPane();
  private CollectionPanel collectionPanel;
  private ImportSaparationPanel importAndSepPanel;
  private JButton jbtnAddNode = new JButton();
  private JPanel jButtonPanel=new JPanel();
  
  private DesktopMainJPanel desktopMainPanel; 
  
  public DesktopRightJPanel() 
  {
    try {    
      jbInit();    
      //setController(new DesktopRightJPanelController(this));
      //controller().initializeScreen();
      
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public DesktopRightJPanel(DesktopMainJPanel desktopMainJPanel) 
  {
	  this.desktopMainPanel= desktopMainJPanel;
	  collectionPanel=new CollectionPanel(desktopMainPanel);
	  importAndSepPanel=new ImportSaparationPanel(desktopMainPanel);	  
           //importAndSepPanel=new ImportSaparationPanel(this);	  
	  
	  try 
	  {    
	      jbInit();
	      initHandler();
	      
	    }
	    catch (Exception ex) {
	      ex.printStackTrace();
	    }
}

private void initHandler() 
{
	tabbedPane.addChangeListener((ChangeListener)this);	
}

public DesktopRightJPanelController controller() {
    return (DesktopRightJPanelController) getController();
  }

  void jbInit() throws Exception 
  {   
	  this.setLayout(borderLayout);
	  
	  String hasCollectionRights = SessionUtil.getSessionData().getCollectionRight();
	  String hasImportRights = SessionUtil.getSessionData().getImportRight();
	  String hasSeparationRights = SessionUtil.getSessionData().getSeparationRight();
	  
	  log.info("Collection Panel Right" + hasCollectionRights);
	  log.info("Import Panel Right" + hasImportRights);
	  log.info("Separation Panel Right" + hasSeparationRights);
	  
	  if(("Y").equals(hasCollectionRights))
	  {
		  tabbedPane.add(collectionPanel,"Collection");
	  }		  
	  if(("Y").equalsIgnoreCase(hasImportRights) || ("Y").equalsIgnoreCase(hasSeparationRights))
	  {
		  tabbedPane.add(importAndSepPanel, "Import and Separation");
	  }
	  this.add(tabbedPane,BorderLayout.CENTER);
  }
  
  
private void initButtonPanel() {
	jButtonPanel.setLayout(borderLayout);
	jButtonPanel.add(jbtnAddNode, BorderLayout.WEST);
}
//Return the frame's preferred size.
  public Dimension getPreferredSize() {
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      return new Dimension(screenSize.width, screenSize.height - 20); //40
  }

  public void actionPerformed(ActionEvent e) 
  {
  }
 
  public void setLabelCaption(Hashtable hBundle) {
    
  }

  public void setButtonCaption(Hashtable hBundle) {
	  jbtnAddNode.setText("Add Node");
  }

  public void setToolTips(Hashtable hBundle) {
	  jbtnAddNode.setToolTipText("Add Node");
  }

  public void setShortcuts(Hashtable hBundle) {
    //jbtnLogon.setMnemonic(hBundle.get("jbtnLogonSC").toString().charAt(0));
  }

  public void cleanup() {
    //jbtnLogon.removeActionListener( (java.awt.event.ActionListener)this);
      hBundle = null;
   // setController(null);
    //super.cleanup();
  }

  public void setForLogin() {
    //jbtnLogon.requestFocus(); 
  }

public DesktopMainJPanel getDesktopMainPanel() {
	return desktopMainPanel;
}

public void setDesktopMainPanel(DesktopMainJPanel desktopMainPanel) {
	this.desktopMainPanel = desktopMainPanel;
}

public JTabbedPane getTabbedPane() {
	return tabbedPane;
}

public void setTabbedPane(JTabbedPane tabbedPane) {
	this.tabbedPane = tabbedPane;
}

public CollectionPanel getCollectionPanel() {
	return collectionPanel;
}

public void setCollectionPanel(CollectionPanel collectionPanel) {
	this.collectionPanel = collectionPanel;
}



	public ImportSaparationPanel getImportAndSepPanel() {
	return importAndSepPanel;
}

public void setImportAndSepPanel(ImportSaparationPanel importAndSepPanel) {
	this.importAndSepPanel = importAndSepPanel;
}

	@Override
	public void stateChanged(ChangeEvent e) 
	{
		if(e.getSource() == tabbedPane)
		{
			log.info("Tab Change Index: " + tabbedPane.getSelectedIndex());
									
			if(SessionUtil.getSessionData().getCollectionRight().equals("Y"))
			{				
				if(tabbedPane.getSelectedIndex() == 1)
				{
					JTree nodeTree = desktopMainPanel.getjLeftPanel().getNodeTree();
					if (nodeTree.getSelectionPath() == null || nodeTree.getSelectionPath().getLastPathComponent() == null) 
					{
						tabbedPane.setSelectedIndex(0);
						ErrorMessage.displayMessage('I', "pleaseSelectNode");
						return;
					}
					
					CustomMutableTreeNode selectedNode = desktopMainPanel.getjRightPanel().getCollectionPanel().getSelectedTreeNode();
					CustomMutableTreeNode newAddedNode = desktopMainPanel.getjRightPanel().getCollectionPanel().getNewAddedNode();
					
					// Multiple Node Selection Restriction
					desktopMainPanel.getjLeftPanel().restrictNodeSelection();
					
					if(newAddedNode != null)
					{					
						log.info("Component : " + tabbedPane.getComponentAt(1));
						tabbedPane.setSelectedIndex(0);
						ErrorMessage.displayMessage('I',"saveOrDeleteAddedNodeBeforeChangeTab");					
						return;
					}
					else if (desktopMainPanel.getjRightPanel().getCollectionPanel().isNodePropertyModified())
					{
						tabbedPane.setSelectedIndex(0);					
						ErrorMessage.displayMessage('I', "saveOrDeleteUpdatedNodeBeforeChangeTab");						
						return;
					}			
				}
			}
		}
	}
	

}

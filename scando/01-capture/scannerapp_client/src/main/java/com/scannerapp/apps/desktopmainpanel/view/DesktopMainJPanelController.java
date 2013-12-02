package com.scannerapp.apps.desktopmainpanel.view;

import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.scannerapp.apps.desktopleftpanel.view.DesktopLeftJPanel;
import com.scannerapp.apps.framework.view.BaseController;
import com.scannerapp.apps.utils.GeneralUtils;
import com.scannerapp.common.SessionInfo;


public class DesktopMainJPanelController extends BaseController 
{
 private static Logger log = Logger.getLogger(DesktopMainJPanelController.class);//For Log4j	
  private DesktopMainPanelHelper desktopMainPanelHelper;
  protected SessionInfo sessionInfo;
    
  public DesktopMainJPanelController(DesktopLeftJPanel jPanel) {
    super(jPanel);
    desktopMainPanelHelper = new DesktopMainPanelHelper();
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
      initData = desktopMainPanelHelper.initializeScreen();
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


  public void jbtnLogon_actionPerformed() 
  {
    
  }
  
  private Hashtable getScreenUOAHashtable(Hashtable rights) {
    return GeneralUtils.getScreenUOAHashtable(rights);
  }

} //End of Class
package com.scannerapp.apps.desktoprightpanel.view;

import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.scannerapp.apps.framework.view.BaseController;
import com.scannerapp.apps.utils.GeneralUtils;
import com.scannerapp.common.SessionInfo;


public class DesktopRightJPanelController extends BaseController 
{
 private static Logger log = Logger.getLogger(DesktopRightJPanelController.class);//For Log4j	
  private DesktopRightHelper desktopRightHelper;
  protected SessionInfo sessionInfo;
    
  public DesktopRightJPanelController(DesktopRightJPanel jPanel) {
    super(jPanel);
    desktopRightHelper = new DesktopRightHelper();
  }

  public void initialize() {
    super.initialize();
  }

  public DesktopRightJPanel view() {
    return (DesktopRightJPanel) getView();
  }

  public void initializeScreen() 
  {
    ArrayList initData = new ArrayList();
    try 
    {
      initData = desktopRightHelper.initializeScreen();
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

} //End of Class
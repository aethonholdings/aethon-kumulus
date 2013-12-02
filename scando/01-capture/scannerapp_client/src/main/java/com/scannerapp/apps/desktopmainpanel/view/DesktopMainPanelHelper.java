package com.scannerapp.apps.desktopmainpanel.view;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.scannerapp.apps.framework.utils.ClientHelper;

public class DesktopMainPanelHelper extends ClientHelper 
{
  private static Logger log = Logger.getLogger(DesktopMainPanelHelper.class);
  public ArrayList initializeScreen() 
  {
    ArrayList initData = new ArrayList();
    
    try {
      log.debug("DesktopLeftHelper :: initializeScreen() :: Calling.................");
      //LoginFacadeSFRemote loginFacadeSFRemote = (LoginFacadeSFRemote)getEJBReference(com.fritolay.im.components.login.facade.LoginFacadeSFHome.class);
      //initData = loginFacadeSFRemote.initializeScreen();
      log.debug("LoginHelper :: initializeScreen() :: Called.............");
    }
    catch (Exception e) {
    	System.out.println("Error: "+ e.getMessage());
	}
    //catch (RemoteException e) {
      //return null;
    //}
    return initData;
  }  
  
}

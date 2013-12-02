package com.scannerapp.apps.desktoprightpanel.view;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.scannerapp.apps.framework.utils.ClientHelper;

public class DesktopRightHelper extends ClientHelper 
{
  private static Logger log = Logger.getLogger(DesktopRightHelper.class);
  public ArrayList initializeScreen() 
  {
    ArrayList initData = new ArrayList();
    
    try {
      log.debug("DesktopRightHelper :: initializeScreen() :: Calling.................");
      //LoginFacadeSFRemote loginFacadeSFRemote = (LoginFacadeSFRemote)getEJBReference(com.fritolay.im.components.login.facade.LoginFacadeSFHome.class);
      //initData = loginFacadeSFRemote.initializeScreen();
      log.debug("DesktopRightHelper :: initializeScreen() :: Called.............");
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

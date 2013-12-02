package com.scannerapp.apps.framework.view ;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.scannerapp.apps.login.view.LoginHelper;
import com.scannerapp.apps.utils.GeneralUtils;
import com.scannerapp.apps.utils.SessionUtil;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Kanhaiya Samariya
 * @version 1.0
 */
public abstract class BaseController
    extends Observable
    implements Observer //, NotificationListener
{
  private static Logger log = Logger.getLogger(BaseController.class);//For Log4j
  protected Component view = null ;
  private String classNameWithoutPackage = "" ;
  private LoginHelper loginHelper;
  
  public BaseController ()
  {
    super () ;
    initHelper();
  }


public BaseController ( Component view )
  {
    this () ;
    setView ( view ) ;
    initHelper();
  }

private void initHelper() {
	  loginHelper = new LoginHelper();	
}

  // Return controller's view
  public Component getView ()
  {
    return view ;
  }

  // Set controller's view
  public void setView ( Component view )
  {
    this.view = view ;
  }

  // Initialize
  public void initialize ()
  {
    addListeners () ;
  }

  // Register Listeners
  protected void addListeners ()
  {

  }

  // De-Register Listeners
  protected void removeListeners ()
  {

  }

  // Set Changed flag automatically when notifying observers!
  public void notifyObservers ( Object object )
  {
    setChanged () ;
    super.notifyObservers ( object ) ;
  }

  // E V E N T S

  // EVENTS FROM WINDOW LISTENERS
  public void windowOpened ( Object window )
  {

  }

  public void windowClosing ( Object window )
  {

  }

  public void windowClosed ( Object window )
  {

  }

  public void windowActivated ( Object window )
  {

  }

  public void windowDeactivated ( Object window )
  {

  }

  public void windowIconified ( Object window )
  {

  }

  public void windowDeiconified ( Object window )
  {

  }

  // EVENTS FROM WINDOW CLOSE LISTENERS

  // Override - Windox close behavior
  public void cancelWindowClose ()
  {

  }

  // Override - Save Changes
  public void saveChanges ()
  {

  }

  // Override - Discard Changes
  public void discardChanges ()
  {

  }

  public void dispose ()
  {
    setView ( null ) ;
  }

  public boolean confirmWindowClose ()
  {
    boolean returnValue = false ;
      //int answer = JOptionPane.showConfirmDialog(getView(), GeneralUtils.getDesktopBundle().get("jmsgExitInternalFrame").toString(), GeneralUtils.getDesktopBundle().get("jmsgExitInternalFrameHeader").toString(), JOptionPane.YES_NO_OPTION);
      String arStr[] = new String[ 2 ] ;
      arStr[ 0 ] = GeneralUtils.getDesktopBundle ().get ( "jlblYES" ).toString () ;
      arStr[ 1 ] = GeneralUtils.getDesktopBundle ().get ( "jlblNO" ).toString () ;
      int answer = JOptionPane.showOptionDialog ( getView () ,
                                                  GeneralUtils.getDesktopBundle ().
                                                  get ( "jmsgExitInternalFrame" ).
                                                  toString () ,
                                                  GeneralUtils.getDesktopBundle ().
                                                  get (
          "jmsgExitInternalFrameHeader" ).toString () ,
                                                  JOptionPane.YES_NO_OPTION ,
                                                  JOptionPane.QUESTION_MESSAGE , null ,
                                                  arStr , arStr ) ;
      switch ( answer )
      {
        case JOptionPane.YES_OPTION:
          saveChanges () ;
          updateLogoutTime();
          returnValue = true ;
          break ;

        case JOptionPane.NO_OPTION:
          discardChanges () ;
          returnValue = false ;
          break ;

        case JOptionPane.CANCEL_OPTION:
          cancelWindowClose () ;
          return false ;
        default:
      }
    return returnValue ;
  }

private void updateLogoutTime() 
{
	long interval = Long.parseLong(SessionUtil.getSessionData()
			.getRefreshInterval());

	Timer updateAttendance = new Timer();	
	UpdateAttendance timerTask = new UpdateAttendance();
	updateAttendance.schedule(timerTask, 0,0);	
}

/* Code Clean-up : Dhwanil */
//  public void processNotificationEvent ( NotificationEvent event )
//  {
//
//  }
/**/

  // EVENTS FROM OBSERVABLES
  public void update ( Observable observable , Object event )
  {
//    if ( event instanceof NotificationEvent )
//    {
//      notifyObservers ( event ) ;
//    }
  }

  protected void debug ( String aMsg )
  {
    log.debug(getClassNameWithoutPackage () + " :: " +
                                    aMsg ) ;
  }

  protected void errorLog ( String message , Throwable th )
  {
	log.debug ( getClassNameWithoutPackage () + " :: " +
                                    message , th ) ;
  }

  protected String getClassNameWithoutPackage ()
  {
    String fullName = getClass ().getName () ;
    int lastIndex = fullName.lastIndexOf ( '.' ) ;
    if ( lastIndex > 0 )
    {
      classNameWithoutPackage = fullName.substring ( lastIndex + 1 ) ;
    }
    else
    {
      classNameWithoutPackage = fullName ;
    }
    return classNameWithoutPackage ;
  }
  
//Inner Class For Timer...
	private class UpdateAttendance extends TimerTask {

		@Override
		public void run() {

			log.info("LogOut Time: " + new Date());
			
			ArrayList<String> loginCredentials = new ArrayList<String>();

			loginCredentials.add(SessionUtil.getSessionData().getUserId());
			loginCredentials.add(SessionUtil.getSessionData().getProjectId());
			
			if (!loginHelper.updateAttendance(loginCredentials)) {
				ErrorMessage
						.displayMessage('E', "errorWhileUpdatingAttendance");
			}
		}
	}
}

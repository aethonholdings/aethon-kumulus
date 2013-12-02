package com.scannerapp.apps.framework.view ;

import java.io.Serializable;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.scannerapp.common.IMScreen;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Kanhaiya Samariya
 * @version 1.0
 */

/*
 *======================Modification History======================
 *
 * Date      Author           		 Code #        			Description of Modifications
 * --------  ----------------  --------  			--------------------------------------
 * 2-3-2005  Hardik Shah  			ClientSession#1    	New Screen Right added as FAST_ENTRY_ON_LU Ref. Issue#35,44
 **/

public class ClientSessionInfo
    extends java.lang.Object
    implements Serializable
{
	private static Logger log = Logger.getLogger(ClientSessionInfo.class);//For Log4j
  private static ClientSessionInfo clientSessionInfo ;
  private static Hashtable hRights = new Hashtable ( 30 ) ;
  private static Hashtable hjif = new Hashtable () ;
  private boolean isFastEntryUser = false ;
  public ClientSessionInfo ()
  {
    super () ;
  }

  public static ClientSessionInfo getInstance ()
  {
    if ( clientSessionInfo == null )
    {
      clientSessionInfo = new ClientSessionInfo () ;
    }
    return clientSessionInfo ;
  }

  public static Hashtable getScreenRights ()
  {
    return hRights ;
  }

  public static void setScreenRights ( Hashtable screenRights )
  {
    hRights = screenRights ;
  }

  public static Hashtable getJIF ()
  {
    return hjif ;
  }

  public static void setJIF ( Hashtable jif )
  {
    hjif = jif ;
  }

//Start ClientSession#1
  public void setFastEntryUser ()
  {
    log.debug( "hRights.get(IMScreen.FAST_ENTRY_ON_LU).toString()  " +
                         hRights.get ( IMScreen.FAST_ENTRY_ON_LU ).toString () ) ;
    if ( hRights.get ( IMScreen.FAST_ENTRY_ON_LU ).toString ().equals ( "N" ) )
    {
      isFastEntryUser = false ;
    }
    else
    {
      isFastEntryUser = true ;
    }
  }

  public boolean getFastEntryUser ()
  {
    return isFastEntryUser ;
  }
//End ClientSession#1
}

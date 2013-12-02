package com.scannerapp.apps.framework.view ;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Kanhaiya Samariya
 * @version 1.0
 */
public class BaseJInternalFrameController
    extends BaseController
{
  public BaseJInternalFrameController ()
  {
    super () ;
  }

  public BaseJInternalFrameController ( BaseJInternalFrame bij )
  {
    super ( bij ) ;
  }

  public BaseJInternalFrame getFrameView ()
  {
    return ( BaseJInternalFrame ) getView () ;
  }
}

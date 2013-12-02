/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    StyledScrollPane.java
 * Author:  Anna Swinney
 * Purpose: StyledScrollPane extends JScrollPane initialize method to set the default
 *          style.
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 10/01/98 aswinn   Initial release
 **/
package com.scannerapp.apps.component ;

import java.awt.Component;

import javax.swing.JScrollPane;

import org.apache.log4j.Logger;

import com.scannerapp.apps.framework.StyleGuide;
import com.scannerapp.apps.framework.common.interfaces.Disposable;
public class StyledScrollPane
    extends JScrollPane
    implements Disposable
{

  /**
   * Constructor
   **/
  private static Logger log = Logger.getLogger(StyledScrollPane.class);//For Log4j	
  public StyledScrollPane ()
  {
    super () ;
//  commented by Manish Vithlani for changing Look
    //setAttributes () ;
  } //end constructor

  public StyledScrollPane ( Component component )
  {
    super ( component ) ;
//  commented by Manish Vithlani for changing Look
    //setAttributes () ;
  } //end constructor

  public StyledScrollPane ( Component component , int vsbPolicy , int hsbPolicy )
  {
    super ( component , vsbPolicy , hsbPolicy ) ;
//  commented by Manish Vithlani for changing Look
    //setAttributes () ;
  }

  public StyledScrollPane ( int vsbPolicy , int hsbPolicy )
  {
    super ( vsbPolicy , hsbPolicy ) ;
//  commented by Manish Vithlani for changing Look
    //setAttributes () ;
  }

  private void setAttributes ()
  {
    setBackground ( StyleGuide.PANEL_BACKGROUND_COLOR ) ;
  }

  /**
   * Removes all components from the component
   **/
  public void dispose ()
  {

    //Component component = getViewport().getView();

    //log.debug("StyledScrollPane.dispose.." + component);

    //if (component instanceof Disposable) {

    //  ((Disposable)component).dispose();

    //}
    removeAll () ;
  } // end
} // StylePanel

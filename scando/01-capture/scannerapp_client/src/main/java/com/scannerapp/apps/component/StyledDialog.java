/**
 * © Copyright 2000 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    StyledDialog.java
 * Author:  Paul McCluskey
     * Purpose: StyledDialog extends JDialog to maintain consistency in dialog boxes
 *					in the Masterfiles Application.
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 08/09/00 pmcclu   Initial release
 **/
package com.scannerapp.apps.component ;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.scannerapp.apps.framework.StyleGuide;
import com.scannerapp.apps.framework.common.interfaces.Disposable;

public class StyledDialog
    extends JDialog
    implements Disposable
{

  /**
   * Constructor
   **/
  public StyledDialog ()
  {
    super () ;
//  commented by Manish Vithlani for changing Look
    //setAttributes () ;
  } //end constructor

  public StyledDialog ( JFrame frame , String title , boolean modal )
  {
    super ( frame , title , modal ) ;
//  commented by Manish Vithlani for changing Look
    //setAttributes () ;
  } //end constructor

  private void setAttributes ()
  {
    this.setBackground ( StyleGuide.PANEL_BACKGROUND_COLOR ) ;
  }

  /**
   * Removes all components from the class
   **/
  public void dispose ()
  {
    setLayout ( null ) ;
    removeAll () ;
  } // end
} // StyledDialog

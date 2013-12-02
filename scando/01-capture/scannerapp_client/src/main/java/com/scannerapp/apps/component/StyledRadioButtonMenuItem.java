/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    StyledMenu.java
 * Author:  Chris Kohle
 * Purpose: StyledMenuItem extend and styleises a JMenuItem.
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 07/23/98 ckohle   Initial release
 **/
package com.scannerapp.apps.component ;

import javax.swing.Icon;
import javax.swing.JRadioButtonMenuItem;

import com.scannerapp.apps.framework.StyleGuide;

public class StyledRadioButtonMenuItem
    extends JRadioButtonMenuItem
{

  /**
   * constructor
   **/
  public StyledRadioButtonMenuItem ()
  {
    super () ;
//  commented by Manish Vithlani for changing Look
    //setAttributes () ;
  } // end constructor

  public StyledRadioButtonMenuItem ( String text , Icon icon )
  {
    super ( text , icon ) ;
//  commented by Manish Vithlani for changing Look
    //setAttributes () ;
  }

  private void setAttributes ()
  {
    this.setBackground ( StyleGuide.MENU_ITEM_BACKGROUND_COLOR ) ;
    this.setForeground ( StyleGuide.MENU_ITEM_FOREGROUND_COLOR ) ;
    this.setFont ( StyleGuide.MENU_ITEM_FONT ) ;
    this.setOpaque ( true ) ;
  }
} // end class

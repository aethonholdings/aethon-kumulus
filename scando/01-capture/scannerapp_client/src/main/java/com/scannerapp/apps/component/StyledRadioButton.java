/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    StyledRadioButton.java
 * Author:  Arief Setiawan
     * Purpose: StyledRadioButton extend JButton initialize method to set the default
 *          style for the radio button.
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 08/05/98 asetia   Initial release
 **/
package com.scannerapp.apps.component ;

import javax.swing.JRadioButton;

import com.scannerapp.apps.framework.common.interfaces.Disposable;

public class StyledRadioButton
    extends JRadioButton
    implements Disposable
{

  /**
   * Constructor
   **/
  public StyledRadioButton ()
  {
    super () ;
//  commented by Manish Vithlani for changing Look
    //this.setFont ( StyleGuide.RADIO_BUTTON_FONT ) ;
    //this.setForeground ( StyleGuide.RADIO_BUTTON_FOREGROUND_COLOR ) ;
    //this.setBackground ( StyleGuide.BUTTON_BACKGROUND_COLOR ) ;
  } //end constructor

  /**
   **/
  public void setText ( String text )
  {
    super.setText ( " " + text ) ;
  }

  /**
   * Removes all components from the panel
   **/
  public void dispose ()
  {

    /*
         try {
         super.finalize();
         }
         catch (Throwable e) {
         // What the heck are you supposed to do if you can't finalize something?
         }
     */
  } // end
} // StyledRadioButton

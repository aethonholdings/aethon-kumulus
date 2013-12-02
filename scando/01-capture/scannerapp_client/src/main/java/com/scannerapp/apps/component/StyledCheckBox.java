/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    StyledCheckBox.java
 * Author:  Arief Setiawan
     * Purpose: StyledCheckBox extend JCheckBox initialize method to set the default
 *          style for the check box.
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 08/05/98 asetia   Initial release
 **/
package com.scannerapp.apps.component ;

import javax.swing.JCheckBox;

import com.scannerapp.apps.framework.StyleGuide;
import com.scannerapp.apps.framework.common.interfaces.Disposable;

public class StyledCheckBox
    extends JCheckBox
    implements Disposable  //, ErrorComponentInterface
{

  /**
   * Constructor
   **/
  public StyledCheckBox ()
  {
    super () ;
    //commented by Manish Vithlani for changing Look
    //setDefaultAttributes () ;
  } //end constructor

  /**
   **/
  public void setDefaultAttributes ()
  {
    this.setFont ( StyleGuide.CHECKBOX_FONT ) ;
    this.setBackground ( StyleGuide.CHECKBOX_BACKGROUND_COLOR ) ;
    this.setForeground ( StyleGuide.CHECKBOX_FOREGROUND_COLOR ) ;
  }

  /**
   **/
  public void setText ( String text )
  {
    super.setText ( " " + text ) ;
  }

  /**
   * Set background and font attributes based on whether the field is in error or not
   * @param <em>error</em> boolean indicating whether or not this field
   * is currently in error
   **/
  public void setErrorDisplayAttributes ( boolean error )
  {
    if ( error )
    {

      //setBackground(StyleGuide.CHECKBOX_ERROR_BACKGROUND_COLOR);
      setForeground ( StyleGuide.CHECKBOX_ERROR_FOREGROUND_COLOR ) ;
    }
    else
    {

      //setBackground(StyleGuide.CHECKBOX_BACKGROUND_COLOR);
      setForeground ( StyleGuide.CHECKBOX_FOREGROUND_COLOR ) ;
    }
    this.repaint () ;
  }

  /**
   * Disposes of the component
   **/
  public void dispose ()
  {

  } // end
}

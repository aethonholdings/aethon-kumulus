/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    StyledLabel.java
 * Author:  Arief Setiawan
 * Purpose: StyledLabel extend JLabel initialize method to set the default
 *          style for the label.
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 08/13/98 asetia   Initial release
 **/
package com.scannerapp.apps.component ;

import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;

import com.scannerapp.apps.framework.StyleGuide;

public class StyledLabel
    extends JLabel
{
  private boolean _paddedWithSpace = false ;

  /**
   * Constructor
   **/
  public StyledLabel ()
  {
    super () ;
//  commented by Manish Vithlani for changing Look
    //setAttributes () ;
  }

  public StyledLabel ( String text )
  {
    super ( text ) ;
//  commented by Manish Vithlani for changing Look
    //setAttributes () ;
  }

  public StyledLabel ( Icon image )
  {
    super ( image ) ;
//  commented by Manish Vithlani for changing Look
    //setAttributes () ;
  }

  private void setAttributes ()
  {
    this.setForeground ( StyleGuide.LABEL_FOREGROUND_COLOR ) ;
    this.setFont ( StyleGuide.LABEL_FONT ) ;
  }

  //---------- "font fixer" methods

  /**
   **/
  public void setText ( String text )
  {
    Font thisFont = this.getFont () ;
    if ( thisFont == null || thisFont.getFamily () == null || text == null )
    {
      super.setText ( text ) ;
      return ;
    }
    _paddedWithSpace = false ;
    String realText = text ;
    if ( thisFont.getFamily ().equals ( "Dialog" ) && thisFont.getSize () == 11 )
    {
      if ( text.indexOf ( "W" ) == 0 )
      {
        realText = " " + text ;
        _paddedWithSpace = true ;
      }
    }
    super.setText ( realText ) ;
  }

  /**
   **/

  /*
   public String getText() {
   String text = super.getText();
   if (_paddedWithSpace) {
   if (text.length() > 1) {
   return text.substring(1);
   }
   }
   return text;
   }
   */
} //SytledLabel

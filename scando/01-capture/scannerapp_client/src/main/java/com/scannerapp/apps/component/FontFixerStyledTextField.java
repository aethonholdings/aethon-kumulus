/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    FontFixerStyledTextField
 * Author:  Anna Swinney
     * Purpose:  The purpose of the FontFixerStyledTextField class is fix an anomaly
 * in text fields beginning with W in the Font Dialog, size 11.
 *
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 09/09/99 aswinn   Initial release
 *
 **/
package com.scannerapp.apps.component ;

import java.awt.Font;

import com.scannerapp.apps.framework.StyleGuide;

/**
 * StyledTextField class is an extension
 * of EdittedTextField that applies Masterfiles specific formatting.
 * @version     1.00 - July 29, 1998
 * @author      Anna Swinney
 **/
public class FontFixerStyledTextField
    extends StyledTextField
{
  private boolean _paddedWithSpace = false ;
  public FontFixerStyledTextField ()
  {
    super () ;
  }

  /**
   * @param <em>columns</em> the number of columns
   **/
  public FontFixerStyledTextField ( int columns )
  {
    super ( columns ) ;
  }

  /**
   **/
  public void setText ( String text )
  {
    Font thisFont = this.getFont () ;
    _paddedWithSpace = false ;
    String realText = text ;
    if ( thisFont.getFamily ().equals ( "Dialog" ) && thisFont.getSize () == 11 )
    {
      if ( text.indexOf ( "W" ) == 0 && getMaximumLength () > text.length () )
      {
        realText = " " + text ;
        _paddedWithSpace = true ;
      }
    }
    super.setText ( realText ) ;
  }

  /**
   **/
  public String getText ()
  {
    String text = super.getText () ;
    if ( _paddedWithSpace )
    {
      if ( text.length () > 1 )
      {
        return text.substring ( 1 ) ;
      }
    }
    return text ;
  }

  public static void main ( String[] args )
  {
    StyleGuide.initialize () ;
    FontFixerStyledTextField field = new FontFixerStyledTextField ( 4 ) ;
    field.setText ( "ABC" ) ;

    //com.fritolay.im.apps.framework.MasterfilesEnvironment.log("com.fritolay.im.apps.component", "FontFixerStyledTextField", "main","text returned " + ">"+field.getText()+"<");
    field.setText ( " DE" ) ;

    //com.fritolay.im.apps.framework.MasterfilesEnvironment.log("com.fritolay.im.apps.component", "FontFixerStyledTextField", "main","text returned " + ">"+field.getText()+"<");
    field.setText ( "WXY" ) ;

    //com.fritolay.im.apps.framework.MasterfilesEnvironment.log("com.fritolay.im.apps.component", "FontFixerStyledTextField", "main","text returned " + ">"+field.getText()+"<");
    field.setText ( " WXY" ) ;

    //com.fritolay.im.apps.framework.MasterfilesEnvironment.log("com.fritolay.im.apps.component", "FontFixerStyledTextField", "main","text returned " + ">"+field.getText()+"<");
    field.setText ( "WXYZ" ) ;

    //com.fritolay.im.apps.framework.MasterfilesEnvironment.log("com.fritolay.im.apps.component", "FontFixerStyledTextField", "main","text returned " + ">"+field.getText()+"<");
    System.exit ( 0 ) ;
  }
} // end class

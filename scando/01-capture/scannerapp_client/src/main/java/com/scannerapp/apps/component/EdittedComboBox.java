/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    EdittedComboBox
 * Author:  Anna Swinney
     * Purpose:  The purpose of the EdittedComboBox class is to provide an extension
 * of JComboBox that exhibits Masterfiles-specific Behaviors.
 *
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 07/28/98 aswinn   Initial release
 *
 **/
package com.scannerapp.apps.component ;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import com.scannerapp.apps.framework.StyleGuide;

/**
 * EdittedComboBox class is an extension
 * of JComboBox that exhibits Masterfiles-specific behaviors.
 * @version     1.00 - July 28, 1998
 * @author      Anna Swinney
 **/
public class EdittedComboBox
    extends JComboBox
{
  public EdittedComboBox ()
  {
    super () ;
    setErrorDisplayAttributes ( false ) ;
  }

  public EdittedComboBox ( ComboBoxModel model )
  {
    super ( model ) ;
    setErrorDisplayAttributes ( false ) ;
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
      setBackground ( StyleGuide.LISTBOX_ERROR_BACKGROUND_COLOR ) ;
      setFont ( StyleGuide.LISTBOX_ERROR_FONT ) ;
    }
    else
    {
      setBackground ( StyleGuide.LISTBOX_BACKGROUND_COLOR ) ;
      setForeground ( StyleGuide.LISTBOX_FOREGROUND_COLOR ) ;
      setFont ( StyleGuide.LISTBOX_FONT ) ;
    }
  }
}

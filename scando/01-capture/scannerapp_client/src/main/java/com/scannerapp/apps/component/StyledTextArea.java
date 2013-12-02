/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    StyledTextArea
 * Author:  Anna Swinney
 * Purpose:  The purpose of the StyledTextArea class is to provide an extension
 * of JTextArea that automatically exhibits the default Masterfiles behavior of
 * font size, color, etc.
 *
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 12/14/98 aswinn   Initial release
 * 11/01/99 mtarro   Extended EdittedTextArea instead of JTextArea.  Added ability
 *                   to manage the tab key differently.
 * 04/02/01 fadnan 3630-CW memory leak
 *
 **/
package com.scannerapp.apps.component ;

import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;
import javax.swing.text.Keymap;

import com.scannerapp.apps.framework.StyleGuide;

/**
 * StyledTextArea class is an extension
 * of EdittedTextArea that applies Masterfiles specific formatting.
 * @version
 * @author      mtarro
 **/
public class StyledTextArea
    extends EdittedTextArea
{

  // attribute
  private boolean _focusTraversalFlag = true ;

  /**
   * default constructor
   **/
  public StyledTextArea ()
  {
    super () ;
    setErrorDisplayAttributes ( false ) ;
  }

  /**
   * @param <em>rows</em> the number of rows
   *  @param <em>columns</em> the number of columns
   * @param <em>length</em> the maximum length of characters the text
   *  area can hold
   **/
  public StyledTextArea ( int rows , int columns , int length )
  {
    super ( rows , columns , length ) ;
    setErrorDisplayAttributes ( false ) ;
  }

  /**
   * @param <em>rows</em> the number of rows
   *  @param <em>columns</em> the number of columns
   *
   **/
  public StyledTextArea ( int rows , int columns )
  {
    super ( rows , columns ) ;
    setErrorDisplayAttributes ( false ) ;
  }

  /**
   * Set background and font attributes based on whether the field is in error or not
   * @param <em>error</em> boolean indicating whether or not this field
   * is currently in error
   **/
  public void setErrorDisplayAttributes ( boolean error )
  {
    super.setDisabledTextColor ( StyleGuide.LABEL_FOREGROUND_COLOR ) ;
    if ( error )
    {
      setBackground ( StyleGuide.TEXTFIELD_ERROR_BACKGROUND_COLOR ) ;
      setFont ( StyleGuide.TEXTFIELD_ERROR_FONT ) ;
    }
    else
    {
      setBackground ( StyleGuide.TEXTFIELD_BACKGROUND_COLOR ) ;
      setFont ( StyleGuide.TEXTFIELD_FONT ) ;
    }
    this.repaint () ;
  }

  /**
   * Enables or disables this text Area, depending on the value of the
   * parameter <code>enabled</code>. An enabled component can respond to user
   * input and generate events.
   *
   * @param     <em>enabled</em>   If <code>true</code>, this component is
   *            enabled; otherwise this component is disabled.
   */
  public void setEnabled ( boolean enabled )
  {
    super.setEnabled ( enabled ) ;
    if ( enabled )
    {
      setBackground ( StyleGuide.TEXTFIELD_BACKGROUND_COLOR ) ;
    }
    else
    {
      setBackground ( StyleGuide.TEXTFIELD_DISABLED_BACKGROUND_COLOR ) ;
    }
  }

  /**
   * this method remove the enter key listener
   * that the user can use inside the document to start a new line
   **/
  public void removeEnterKeyListener ()
  {
    KeyStroke enter = KeyStroke.getKeyStroke ( KeyEvent.VK_ENTER , 0 ) ;
    Keymap map = this.getKeymap () ;
    map.removeKeyStrokeBinding ( enter ) ;
  }

  /**
   * this method remove the tab key listener
   * that the user can use inside the document to tab through the document
   **/
  public void removeTabKeyListener ()
  {
    KeyStroke tab = KeyStroke.getKeyStroke ( KeyEvent.VK_TAB , 0 ) ;
    Keymap map = this.getKeymap () ;
    map.removeKeyStrokeBinding ( tab ) ;
  }

  /**
   * if return value is true this method turns off tab traversal once
   * the focus gained else the tab traversal is on .
   * @return boolean
   **/
  public boolean isManagingFocus ()
  {
    return getFocusTraversalFlag () ;
  }

  /***
   *  @return the flag that control the focus traversal
   **/
  public boolean getFocusTraversalFlag ()
  {
    return _focusTraversalFlag ;
  }

  /**
   * @param set the flag that control the focus traversal
   **/
  public void setFocusTraversalFlag ( boolean flag )
  {
    _focusTraversalFlag = flag ;
  }

  public void dispose ()
  {

    //This help initialize the textArea and release memory
    this.createDefaultModel () ;
  }
} // end class

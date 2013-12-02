/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    StyledComboBoxWithProcessOnEnter
 * Author:  Doris Kruse
 * Purpose:  The purpose of the StyledComboBox class is to provide an extension
 * of StyledComboBox that surfaces the Enter for processing.
 *
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 09/11/00 D.Kruse  Initial release
 *
 **/
package com.scannerapp.apps.component ;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ComboBoxModel;

/**
 * StyledComboBoxWithProcessOnEnter class is an extension
 * of StyledComboBox that surfaces the Enter for processing.
 * @version     1.00 - Sept. 11, 2000
 * @author      D.Kruse
 **/
public class StyledComboBoxWithProcessOnEnter
    extends StyledComboBox
{
  private KeyListener _keyListener = null ;
  public StyledComboBoxWithProcessOnEnter ()
  {
    super () ;
  } // end constructor

  /**
       * @param <em>useDefaultKeySelection</em> A Boolean indicating whether to use the
   * default key selection manager provided by Swing (matches a single character) (use true) or
       * the StyledKeySelectionManager which matches on multiple characters (use false)
   **/
  public StyledComboBoxWithProcessOnEnter ( boolean useDefaultKeySelection )
  {
    super ( useDefaultKeySelection ) ;
  } // end constructor

  /**
   * @param <em>model</em> A combo box data model
   **/
  public StyledComboBoxWithProcessOnEnter ( ComboBoxModel model )
  {
    super ( model ) ;
  } // end constructor

  /**
   * @param <em>model</em> A combo box data model
       * @param <em>useDefaultKeySelection</em> A Boolean indicating whether to use the
   * default key selection manager provided by Swing (matches a single character) (use true) or
       * the StyledKeySelectionManager which matches on multiple characters (use false)
   **/
  public StyledComboBoxWithProcessOnEnter ( ComboBoxModel model ,
                                            boolean useDefaultKeySelection )
  {
    super ( model , useDefaultKeySelection ) ;
  } // end constructor

  public void addKeyListener ( KeyListener listener )
  {
    _keyListener = listener ;
    super.addKeyListener ( _keyListener ) ;
  }

  public void removeKeyListener ( KeyListener listener )
  {
    super.removeKeyListener ( listener ) ;
    _keyListener = null ;
  }

  public void processEnter ()
  {
    if ( _keyListener != null )
    {
      _keyListener.keyReleased ( new KeyEvent ( this , KeyEvent.KEY_RELEASED ,
                                                0 , 0 , KeyEvent.VK_ENTER ,
                                                '\r' ) ) ;
    }
  }

  /**
   * Facilitates garbage collection
   **/
  public void dispose ()
  {
    if ( _keyListener != null )
    {
      removeKeyListener ( _keyListener ) ;
    }
    super.dispose () ;
  }
} // end class

/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    GlassPane.java
 * Author:  Anna Swinney
 * Purpose: GlassPane is an arbitrary component for use with JFrames and JInternalFrames
 * that can manage focus.
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 07/23/98 ckohle   Initial release
 **/
package com.scannerapp.apps.component ;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

import com.scannerapp.apps.framework.common.interfaces.Disposable;

public class GlassPane
    extends JComponent
    implements Disposable
{
  private MouseAdapter _mouseAdapter ;
  private KeyAdapter _keyAdapter ;

  /**
   * constructor
   **/
  public GlassPane ()
  {
    super () ;
    setOpaque ( true ) ;
    _mouseAdapter = new MouseAdapter ()
    {
      public void mousePressed ( MouseEvent e )
      {

      }
    } ;
    addMouseListener ( _mouseAdapter ) ;
    _keyAdapter = new KeyAdapter ()
    {
      public void keyPressed ( KeyEvent e )
      {
        e.consume () ;
      }
    } ;
    addKeyListener ( _keyAdapter ) ;
  } // end constructor

  /**
   **/
  public boolean isManagingFocus ()
  {
    return true ;
  }

  /**
   * Removes all components from the panel
   **/
  public void dispose ()
  {
    removeAll () ;
    removeMouseListener ( _mouseAdapter ) ;
    removeKeyListener ( _keyAdapter ) ;
    _mouseAdapter = null ;
    _keyAdapter = null ;
    try
    {
      super.finalize () ;
    }
    catch ( Throwable e )
    {

      // What the heck are you supposed to do if you can't finalize something?
    }
  } // end
} // end class

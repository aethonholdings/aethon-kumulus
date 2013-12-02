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

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JCheckBoxMenuItem;

import com.scannerapp.apps.framework.StyleGuide;
import com.scannerapp.apps.framework.common.interfaces.Disposable;

public class StyledCheckBoxMenuItem
    extends JCheckBoxMenuItem
    implements Disposable
{
  private Action _action ;

  /**
   * constructor
   **/
  public StyledCheckBoxMenuItem ()
  {
    super () ;
//  commented by Manish Vithlani for changing Look
    //setAttributes () ;
  } // end constructor

  public StyledCheckBoxMenuItem ( String text , Icon icon )
  {
    super ( text , icon ) ;
//  commented by Manish Vithlani for changing Look
    //setAttributes () ;
  }

  /**
   **/
  public StyledCheckBoxMenuItem ( Action action )
  {
    this ( ( String ) action.getValue ( Action.NAME ) ,
           ( Icon ) action.getValue ( Action.SMALL_ICON ) ) ;
    _action = action ;
  }

  /**
   **/
  private void setAttributes ()
  {
    this.setBackground ( StyleGuide.MENU_ITEM_BACKGROUND_COLOR ) ;
    this.setForeground ( StyleGuide.MENU_ITEM_FOREGROUND_COLOR ) ;
    this.setFont ( StyleGuide.MENU_ITEM_FONT ) ;
    this.setOpaque ( true ) ;
  }

  /**
   * Removes all components from the component
   **/
  public void dispose ()
  {
    if ( _action != null )
    {
      removeActionListener ( _action ) ;
    }
    if ( _action instanceof Disposable )
    {
      ( ( Disposable ) _action ).dispose () ;
    }
    _action = null ;
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

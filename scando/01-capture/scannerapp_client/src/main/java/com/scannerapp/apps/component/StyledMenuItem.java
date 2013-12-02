package com.scannerapp.apps.component ;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenuItem;

import com.scannerapp.apps.framework.StyleGuide;
import com.scannerapp.apps.framework.common.interfaces.Disposable;

public class StyledMenuItem
    extends JMenuItem
    implements Disposable
{
  private Action _action ;
  public StyledMenuItem ()
  {
    super () ;
//  commented by Manish Vithlani for changing Look
    //setAttributes () ;
  }

  public StyledMenuItem ( String text , Icon icon )
  {
    super ( text , icon ) ;
//  commented by Manish Vithlani for changing Look
    //setAttributes () ;
  }

  public StyledMenuItem ( Action action )
  {
    this ( ( String ) action.getValue ( Action.NAME ) ,
           ( Icon ) action.getValue ( Action.SMALL_ICON ) ) ;
    _action = action ;
  }

  public Action getAction ()
  {
    return _action ;
  }

  private void setAttributes ()
  {
    this.setBackground ( StyleGuide.MENU_ITEM_BACKGROUND_COLOR ) ;
    this.setForeground ( StyleGuide.MENU_ITEM_FOREGROUND_COLOR ) ;
    this.setFont ( StyleGuide.MENU_ITEM_FONT ) ;
    this.setOpaque ( true ) ;
    this.setRequestFocusEnabled ( false ) ;
  }

  public void setStyledMenuItemEnabled ( boolean enabled , String tabName )
  {
    super.setEnabled ( enabled ) ;
  }

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

    }
  }
}

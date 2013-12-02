/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    StyledPopupMenu.java
 * Author:  Chris Kohle
 * Purpose: StyledPopupMenu extend JPopupMenu and provides a method for adding
 *          and styling an action.  It also provides and initialize method to
 *          set the default style for the popup menu.
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 07/23/98 ckohle   Initial release
     * 01/05/01 D.Kruse  added setLocation() to validate the location before setting
     *                   it.  This will cause the popup to shift if it would run off
 *                   the screens edge on the right or bottom.  Note, I added a
 *                   fudge factor to handle the taskbar, if this is a problem, please
 *                   feel free to remove.
 **/
package com.scannerapp.apps.component ;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.scannerapp.apps.framework.common.interfaces.Disposable;

public class StyledPopupMenu
    extends JPopupMenu
    implements Disposable
{

  //  protected Hashtable _actions;

  /**
   * constructor
   **/
  public StyledPopupMenu ()
  {
    super () ;
//  commented by Manish Vithlani for changing Look
    //this.setBackground ( StyleGuide.MENU_BACKGROUND_COLOR ) ;
    //this.setForeground ( StyleGuide.MENU_FOREGROUND_COLOR ) ;
    //this.setRequestFocusEnabled ( false ) ;
  } // end constructor

  /**
   * adds and styles an action to the menu
   * @param action an action
   * @return JMenuItem the styled item created by adding the action
   * @deprecated. This method is deprecated. Call add(Action action) instead.
   **/
  public JMenuItem addAction ( Action action )
  {
    return super.add ( action ) ;
  }

  /**
   * adds and styles an action to the menu
   * @param action an action
   * @return JMenuItem the styled item created by adding the action
   **/

  /*
       public JMenuItem addAction(Action action) {
       JMenuItem item = super.add(action);
       item.setBackground(StyleGuide.MENU_ITEM_BACKGROUND_COLOR);
       item.setForeground(StyleGuide.MENU_ITEM_FOREGROUND_COLOR);
       item.setOpaque(true);
       item.setFont(StyleGuide.MENU_ITEM_FONT);
       item.setRequestFocusEnabled(false);
       item.revalidate();
       rememberTheAction(item, action);
       return item;
       } // end method
   */

  /**
   **/

  /*
       protected void rememberTheAction(JMenuItem menuItem, Action action) {
       if (_actions == null) {
       _actions = new Hashtable(20);
       }
       _actions.put(menuItem, action);
       }
   */

  /**
   * Removes all components from the component
   **/

  /*
       public void dispose() {
       if (_actions != null) {
       Enumeration enumeration = _actions.keys();
       while (enumeration.hasMoreElements()) {
       JMenuItem menuItem = (JMenuItem)enumeration.nextElement();
       Action action = (Action)_actions.remove(menuItem);
       menuItem.removeActionListener(action);
       if (action instanceof ApplicationAction) {
       PropertyChangeListener listener = ((ApplicationAction)action).getPropertyChangeListener();
       if (listener != null) {
       menuItem.removePropertyChangeListener(listener);
       }
       }
       }
       }
       _actions = null;
       Component[] components = getComponents();
       for (int i=0; i<components.length; i++) {
       //logP("dispose","StyledPopupMenu.dispose.." + components[i].getClass().getName());
       //if (components[i] instanceof JMenuItem) {
       //  remove(components[i]);
       //  aes 7/2/99  Although we would like to do the remove(component) in order
       //  to completely remove the listeners, we can't.
       //  There looks like there is a bug in this method in JPopupMenu that causes
       //  a ClassCastException when you do it.  Substituting removeAll() below,
       //  which is not complete.
       //}
       if (components[i] instanceof Disposable) {
       ((Disposable)components[i]).dispose();
       }
       }
       removeAll();
       }
   */
  public void dispose ()
  {
    Object[] menuItems = getSubElements () ;
//    Object menuItem = null ;
    for ( int index = 0 ; index < menuItems.length ; index++ )
    {
      if ( menuItems[ index ] instanceof JMenuItem )
      {
        remove ( ( JMenuItem ) menuItems[ index ] ) ;
      }
    }
  }

  private static int WidthFudgeFactor = 20 ;
  private static int HeightFudgeFactor = 30 ;
  public void setLocation ( int xIn , int yIn )
  {
    int x = xIn ;
    int y = yIn ;

    // Get the Screen size
    Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize () ;

    //logP("dispose","Screen Size = " + screenSize);

    // Ajust the screen size down due to the possible presents of the taskbar.
    screenSize.width = screenSize.width - WidthFudgeFactor ;
    screenSize.height = screenSize.height - HeightFudgeFactor ;

    // logP("dispose","PopupMenu Location is = " + x + ", " + y);

    // logP("dispose","PopupMenu size is = " + getSize());
    Dimension popupSize = getSize () ;

    // Calculate new position on screen
    int xCorner = x + popupSize.width ;
    int yCorner = y + popupSize.height ;
    if ( xCorner > screenSize.width )
    {
      x = screenSize.width - popupSize.width ;
    }
    if ( yCorner > screenSize.height )
    {
      y = screenSize.height - popupSize.height ;
    }
    super.setLocation ( x , y ) ;
  }
} // end class

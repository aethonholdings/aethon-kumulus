/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    StyledBasicListUI.java
 * Author:  Zul Khoja
 * Purpose: StyledBasicListUI is a special UI that allows a single click style
 MultiSelect ListBox
 *
 *======================Modification History======================
 *
 * Date    		ID      	 	Description of Modifications
 * -------- 	--------   	----------------------------
 * 04/27/99 	zkhoja 		 	Initial release
 * 07/02/99 	zkhoja 		 	Converted to Swing1.1
 **/
package com.scannerapp.apps.component ;

import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicListUI;

public class StyledBasicListUI
    extends BasicListUI
{
  StyledBasicListUI () {}

  /**
   * Returns a new instance of BasicListUI.  BasicListUI delegates are
   * allocated one per JList.
   *
   * @return A new ListUI implementation for the Windows look and feel.
   */
  public static ComponentUI createUI ( JComponent list )
  {
    return new StyledBasicListUI () ;
  }

  // Allow access to the Proteced JList from the inner class
  private JList getJList ()
  {
    return list ;
  }

  /** Override the method to create a customer MouseInputListener
   *@see javax.swing.plaf.basic.BasicListUI#createMouseInputListener
   */
  protected MouseInputListener createMouseInputListener ()
  {
    return new MyInputListener () ;
  }

  // Allow access to this proteced method from from the inner class
  public int convertYToRow ( int row )
  {
    return super.convertYToRow ( row ) ;
  }

  /**
   * Create a Custom MouseInputHander to orverride the mousePressed
   * behavior.
   */
  public class MyInputListener
      extends MouseInputHandler
  {
//    private JList list ;

    /**
     * Override the mousePressed method to allow multiple Selection
     * without having to hold down the Control or the Shift key.
     */
    public void mousePressed ( MouseEvent e )
    {
      if ( !SwingUtilities.isLeftMouseButton ( e ) )
      {
        return ;
      }
      JList list = getJList () ;
      if ( !list.isEnabled () )
      {
        return ;
      }
      int row = convertYToRow ( e.getY () ) ;
      if ( row != -1 )
      {
        list.setValueIsAdjusting ( true ) ;
        if ( list.isSelectedIndex ( row ) )
        {
          list.removeSelectionInterval ( row , row ) ;
        }
        else
        {
          list.addSelectionInterval ( row , row ) ;
        } // End else
      } // End if
    } // End mousePressed(MouseEvent e)

    /**
     * Override the mouseDragged method to do nothing
     */
    public void mouseDragged ( MouseEvent e )
    {
    }
  } // End Class MyInputListener
} // End class StyledBasicListUI

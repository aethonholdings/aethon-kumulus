/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    StyledDefaultTreeCellRenderer.java
 * Author:  Anna Swinney
 * Purpose: StyledDefaultTreeCellRenderer is is used by the table to draw each cell
 * 					in the tree.  (NOT including the row header).
 *					This object extends JLabel and implements TreeCellRenderer.
 * 					The required method is getTreeCellRendererComponent.
 *
 *======================Modification History======================
 *
 * Date    		ID      	 	Description of Modifications
 * -------- 	--------   	----------------------------
 * 10/16/98 	aswinn 		 	Initial release
 **/
package com.scannerapp.apps.component ;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;

/**
 *
 * @version     1.00 - September 28, 1998
 * @author      Anna Swinney
 **/
public class StyledDefaultTreeCellRenderer
    extends StyledLabel
    implements TreeCellRenderer
{
  protected int _visibleRow ;

  /**
   * Constructor
   *
   **/
  public StyledDefaultTreeCellRenderer ()
  {
    super () ;
    this.setOpaque ( true ) ;
  } // end constructor

  /**
   * Sets the value of the current tree cell to <code>value</code>.
   * If <code>selected</code> is true, the cell will be drawn as if
   * selected. If <code>expanded</code> is true the node is currently
   * expanded and if <code>leaf</code> is true the node represets a
   * leaf anf if <code>hasFocus</code> is true the node currently has
   * focus. <code>tree</code> is the JTree the receiver is being
   * configured for.
   * Returns the Component that the renderer uses to draw the value.
   *
   * @return	Component that the renderer uses to draw the value.
   */
  public Component getTreeCellRendererComponent ( JTree tree , Object value ,
                                                  boolean isSelected ,
                                                  boolean expanded ,
                                                  boolean leaf , int row ,
                                                  boolean hasFocus )
  {
    if ( isSelected )
    {
//    	commented by Manish Vithlani for changing Look
      //super.setBackground ( StyleGuide.TREE_SELECTED_BACKGROUND_COLOR ) ;
      //super.setForeground ( StyleGuide.TREE_SELECTED_FOREGROUND_COLOR ) ;
    }
    else
    {
//    	commented by Manish Vithlani for changing Look
      //super.setBackground ( StyleGuide.TREE_OUTPUT_BACKGROUND_COLOR ) ;
      //super.setForeground ( StyleGuide.TREE_OUTPUT_FOREGROUND_COLOR ) ;
    } // end if
    if ( value == null )
    {
      return this ;
    } // end if
    super.setText ( value.toString () ) ;
    _visibleRow = row ;
    return this ;
  } // end getTableCellRendererComponent
} // end class

/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    StyledDefaultTableCellRenderer.java
 * Author:  Anna Swinney
 * Purpose: StyledDefaultTableCellRenderer is is used by the table to draw each cell
 * 					in the table.  (NOT including the row header).
 *					This object extends JLabel and implements TableCellRenderer.
 * 					The required method is getTableCellRendererComponent.
 *
 *======================Modification History======================
 *
 * Date    		ID      	 	Description of Modifications
 * -------- 	--------   	----------------------------
 * 09/24/98 	aswinn 		 	Initial release
 **/
package com.scannerapp.apps.component ;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @version     1.00 - September 28, 1998
 * @author      Anna Swinney
 **/
public class StyledDefaultTableCellRenderer
    extends StyledLabel
    implements TableCellRenderer
{
  protected int _visibleRow ;

  /**
   * Constructor
   *
   **/
  public StyledDefaultTableCellRenderer ()
  {
    super () ;
    this.setOpaque ( true ) ;
  } // end constructor

  /**
       * getTableCellRendererComponent - draws each item based on what its value is.
   * @param table - JTable the table
   * @param value - Object the object
   * @param isSelected - boolean if the object is selected
   * @param hasFocus - boolean if the object has focus
   * @param row - int
   * @param column - int
   * @return Component - This component.  (JLabel)
   *
   **/
  public Component getTableCellRendererComponent ( JTable table , Object value ,
      boolean isSelected , boolean hasFocus , int row , int column )
  {
    if ( isSelected )
    {
//    	commented by Manish Vithlani for changing Look
      //super.setBackground ( StyleGuide.TABLE_SELECTED_BACKGROUND_COLOR ) ;
      //super.setForeground ( StyleGuide.TABLE_SELECTED_FOREGROUND_COLOR ) ;
    }
    else
    {
//    	commented by Manish Vithlani for changing Look
      //super.setBackground ( StyleGuide.TABLE_LABEL_BACKGROUND_COLOR ) ;
      //super.setForeground ( StyleGuide.TABLE_LABEL_FOREGROUND_COLOR ) ;
    } // end if
    if ( value == null )
    {
      return this ;
    } // end if
    if ( value instanceof ImageIcon )
    {
      setIcon ( ( ImageIcon ) value ) ;
    }
    else
    {
      setIcon ( null ) ;
      super.setText ( value.toString () ) ;
    }
    _visibleRow = row ;
    return this ;
  } // end getTableCellRendererComponent
} // end class

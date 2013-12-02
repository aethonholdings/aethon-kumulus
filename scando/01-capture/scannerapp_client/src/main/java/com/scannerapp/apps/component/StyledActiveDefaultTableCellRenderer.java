/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    StyledDefaultTableCellRenderer.java
 * Author:  Anna Swinney
 * Purpose: StyledActiveDefaultTableCellRenderer is is used by the table to draw each cell
 * 					in the table.  (NOT including the row header).
 *					This object extends JLabel and implements TableCellRenderer.
 * 					The required method is getTableCellRendererComponent.
 *
 *======================Modification History======================
 *
 * Date    		ID      	 	Description of Modifications
 * -------- 	--------   	----------------------------
 * 01/05/99 	callmo 		 	Initial release
 **/
package com.scannerapp.apps.component ;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

import com.scannerapp.apps.framework.StyleGuide;

/**
 *
 * @version     1.00 - January 5, 1999
 * @author      Christy Allmon
 **/

/**
 * The standard class for rendering (displaying) individual cells
 * in a JTable.
 * <p>
 * Warning: serialized objects of this class will not be compatible with
 * future swing releases.  The current serialization support is appropriate
 * for short term storage or RMI between Swing1.0 applications.  It will
 * not be possible to load serialized Swing1.0 objects with future releases
 * of Swing.  The JDK1.2 release of Swing will be the compatibility
 * baseline for the serialized form of Swing objects.
 *
 * @version 1.9 02/02/98
 * @author Philip Milne
 * @see JTable
 */
public class StyledActiveDefaultTableCellRenderer
    extends StyledLabel
    implements TableCellRenderer
{
  protected static Border noFocusBorder ;

  // We need a place to store the color the JLabel should be returned

  // to after its foreground and background colors have been set

  // to the selection background color.

  // These ivars will be made protected when their names are finalized.
  private Color unselectedForeground ;
  private Color unselectedBackground ;
  public StyledActiveDefaultTableCellRenderer ()
  {
    super () ;
    noFocusBorder = new EmptyBorder ( 1 , 2 , 1 , 2 ) ;
    setOpaque ( true ) ;
    setBorder ( noFocusBorder ) ;
  }

  public void setForeground ( Color c )
  {
    super.setForeground ( c ) ;
    unselectedForeground = c ;
  }

  public void setBackground ( Color c )
  {
    super.setBackground ( c ) ;
    unselectedBackground = c ;
  }

  public Component getTableCellRendererComponent ( JTable table , Object value ,
      boolean isSelected , boolean hasFocus , int row , int column )
  {
    if ( isSelected )
    {
      super.setBackground ( StyleGuide.TABLE_SELECTED_BACKGROUND_COLOR ) ;
      super.setForeground ( StyleGuide.TABLE_SELECTED_FOREGROUND_COLOR ) ;
    }
    else
    {
      super.setBackground ( StyleGuide.TABLE_INPUT_BACKGROUND_COLOR ) ;
      super.setForeground ( StyleGuide.TABLE_INPUT_FOREGROUND_COLOR ) ;
    } // end if
    setFont ( table.getFont () ) ;
    if ( hasFocus )
    {
      setBorder ( UIManager.getBorder ( "Table.focusCellHighlightBorder" ) ) ;
      if ( table.isCellEditable ( row , column ) )
      {
        super.setForeground ( UIManager.getColor ( "Table.focusCellForeground" ) ) ;
        super.setBackground ( UIManager.getColor ( "Table.focusCellBackground" ) ) ;
      }
    }
    else
    {
      setBorder ( noFocusBorder ) ;
    }
    if ( value instanceof ImageIcon )
    {
      setIcon ( ( ImageIcon ) value ) ;
    }
    else
    {
      setIcon ( null ) ;
      setValue ( value ) ;
    }
    return this ;
  }

  protected void setValue ( Object value )
  {
    setText ( ( value == null ) ? "" : value.toString () ) ;
  }

  /**
   * A subclass of BasicTableCellRenderer that implements UIResource.
   * BasicTableCellRenderer doesn't implement UIResource
   * directly so that applications can safely override the
   * cellRenderer property with BasicTableCellRenderer subclasses.
   * <p>
   * Warning: serialized objects of this class will not be compatible with
   * future swing releases.  The current serialization support is appropriate
   * for short term storage or RMI between Swing1.0 applications.  It will
   * not be possible to load serialized Swing1.0 objects with future releases
   * of Swing.  The JDK1.2 release of Swing will be the compatibility
   * baseline for the serialized form of Swing objects.
   */
  public static class UIResource
      extends StyledActiveDefaultTableCellRenderer
      implements javax.swing.plaf.UIResource
  {

  }
}

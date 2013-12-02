/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    StyledCheckBoxListEntryRenderer.java
 * Author:  Zul Khoja
 * Purpose: StyledCheckBoxListEntryRenderer is a special ComboBox that allows a
 CheckBox as the List's Cell Renderer
 *
 *======================Modification History======================
 *
 * Date    		ID      	 	Description of Modifications
 * -------- 	--------   	----------------------------
 * 04/27/99 	zkhoja 		 	Initial release
 **/
package com.scannerapp.apps.component ;

import java.awt.Component;
import java.awt.Insets;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class StyledCheckBoxListEntryRenderer
    extends StyledCheckBox
    implements ListCellRenderer
{
  protected Insets insets = new Insets ( 0 , 0 , 0 , 0 ) ;

  /**
   *  Get them closer togather.
   * @reutrn Insets - A zero inset
   **/
  public Insets getInsets ()
  {
    return insets ;
  }

  /** Copied from com.sun.java.swing.ListCellRenderer.java
   * Return a component that has been configured to display the specified
   * value. That component's <code>paint</code> method is then called to
   * "render" the cell.  If it is necessary to compute the dimensions
   * of a list because the list cells do not have a fixed size, this method
   * is called to generate a component on which <code>getPreferredSize</code>
   * can be invoked.
   *
   * @param list The JList we're painting.
   * @param value The value returned by list.getModel().getElementAt(index).
   * @param index The cells index.
   * @param isSelected True if the specified cell was selected.
   * @param cellHasFocus True if the specified cell has the focus.
   * @return A component whose paint() method will render the specified value.
   *
   * @see com.sun.java.swing.JList
   * @see com.sun.java.swing.ListSelectionModel
   * @see com.sun.java.swing.ListModel
   **/
  public Component getListCellRendererComponent ( JList listbox , Object value ,
                                                  int index ,
                                                  boolean isSelected ,
                                                  boolean cellHasFocus )
  {
    if ( value == null )
    {
      return this ;
    }
    setSelected ( false ) ;
    int[] selectedIndices = listbox.getSelectedIndices () ;
    for ( int selectedIndex = 0 ; selectedIndex < selectedIndices.length ;
          selectedIndex++ )
    {
      if ( selectedIndices[ selectedIndex ] == index )
      {
        setSelected ( true ) ;
        break ;
      }
    }
    
      setText ( value.toString () ) ;
    return this ;
  } // End getListCellRendererComponent()
} // End Class StyledCheckBoxListEntryRenderer

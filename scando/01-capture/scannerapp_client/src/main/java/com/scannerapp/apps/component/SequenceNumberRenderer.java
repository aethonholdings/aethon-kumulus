/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    StyledScrollPane.java
 * Author:  Zul Khoja
 * Purpose: This Renderer used as a Row counter on any JTable
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 11/18/99 zkhoja   Initial release
 **/
package com.scannerapp.apps.component ;

import java.awt.Component;

import javax.swing.JTable;

/**
 * @see StyledDefaultTableCellRenderer
 */
public class SequenceNumberRenderer
    extends StyledDefaultTableCellRenderer
{

  /**
   * @see StyledDefaultTableCellRenderer#getTableCellRendererComponent
   */
  public Component getTableCellRendererComponent ( JTable table , Object value ,
      boolean isSelected , boolean hasFocus , int row , int column )
  {
    setText ( new Integer ( row + 1 ).toString () ) ;
    return super.getTableCellRendererComponent ( table , value , isSelected ,
                                                 hasFocus , row , column ) ;
  }
}

/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    StyledTable.java
 * Author:  Anna Swinney
 * Purpose: StyledTable extends JTable initialize method to set the default
 *          style.
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 10/01/98 aswinn   Initial release
 **/
package com.scannerapp.apps.component ;

import java.awt.Rectangle;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.scannerapp.apps.framework.StyleGuide;
import com.scannerapp.apps.framework.common.interfaces.Disposable;

public class StyledTable
    extends JTable
    implements Disposable
{

  /**
   *
   **/
  public StyledTable ( boolean editable )
  {
    super () ;
//  commented by Manish Vithlani for changing Look
    //setAttributes ( editable ) ;
  }

  /**
   * Constructor
   **/
  public StyledTable ( TableModel model , boolean editable )
  {
    super ( model ) ;
//  commented by Manish Vithlani for changing Look
    //setAttributes ( editable ) ;
  } //end constructor

  /**
   * Constructor
   **/
  public StyledTable ()
  {
    super () ;
//  commented by Manish Vithlani for changing Look
    //setAttributes ( false ) ;
  } //end constructor

  /**
   * Constructor
   **/
  public StyledTable ( TableModel model )
  {
    super ( model ) ;
//  commented by Manish Vithlani for changing Look
    //setAttributes ( false ) ;
  } //end constructor

  private void setAttributes ( boolean editable )
  {
    if ( !editable )
    {
      this.setBackground ( StyleGuide.TABLE_OUTPUT_BACKGROUND_COLOR ) ;
      this.setForeground ( StyleGuide.TABLE_OUTPUT_FOREGROUND_COLOR ) ;
    }
    setFont ( StyleGuide.TABLE_OUTPUT_FONT ) ;
    setDefaultRenderer ( Object.class , new StyledDefaultTableCellRenderer () ) ;
    getTableHeader ().setFont ( new java.awt.Font ( "Dialog" ,
        java.awt.Font.BOLD , 12 ) ) ;
    getTableHeader ().setFont ( new java.awt.Font ( "Dialog" ,
        java.awt.Font.BOLD , 12 ) ) ;
  }

  /**
   * Removes all components from the component
   **/
  public void dispose ()
  {
    setTableHeader ( null ) ;
    removeAll () ;
    setDefaultRenderer ( Object.class , null ) ;

    /*
         try {
         super.finalize();
         }
         catch (Throwable e) {
         // What the heck are you supposed to do if you can't finalize something?
         }
     */
  }

  /**
   * Make the cell visible on screen
   * @param int row - cell's row index
   * @param int col - cell's column index
   **/
  public void makeCellVisible ( int row , int col )
  {
    Rectangle cellRect = null ;
    cellRect = getCellRect ( row , col , true ) ;
    if ( cellRect != null )
    {
      scrollRectToVisible ( cellRect ) ;
    }
  }

  /**
   * Make the row visible on screen
   * @param int row - row index
   **/
  public void makeRowVisible ( int row )
  {
    makeCellVisible ( row , 0 ) ;
  }

  /**
   * Make the column visible on screen
   * @param int col - column index
   **/
  public void makeColumnVisible ( int col )
  {
    makeCellVisible ( 0 , col ) ;
  }
} // StylePanel

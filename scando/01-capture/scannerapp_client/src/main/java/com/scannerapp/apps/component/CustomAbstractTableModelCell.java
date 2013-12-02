package com.scannerapp.apps.component ;

import java.awt.event.ComponentListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Vector;



public class CustomAbstractTableModelCell
    extends CustomAbstractTableModel
    implements ComponentListener , KeyListener
{
  //manish
	//protected CellAttribute cellAtt;
  //finish	
  private int ipassedCol ;
  private int ipassedRow = -1 ;
  private int ipassedRow2 ;
  private ArrayList arlPassedRows = new ArrayList () ;
  private int toColumn = 0 ;
  public CustomAbstractTableModelCell ()
  {
    super () ;

    //      indexes = new int[0]; // For consistency.
  }

  public CustomAbstractTableModelCell ( Vector aColumnNames , Vector aData )
  {
    super () ;
    setColumnName ( aColumnNames ) ;
    if ( aData != null )
    {
      setData ( aData ) ;
    }
  }

  public CustomAbstractTableModelCell ( String aColumnNames[] , Object aData[][] )
  {
    super () ;
    setColumnName ( aColumnNames ) ;
    if ( aData != null )
    {
      setData ( aData ) ;
    }
  }

  public boolean isCellEditable ( int aRow , int aColumn )
  {
    //Debug.debugLog( "CustomAbstractTableModel: isCellEditable( " + aRow + ", " + aColumn + " )" );
    if ( toColumn != 0)
    {
      for ( int i = 0 ; i < arlPassedRows.size () ; i++ )
      {
        if ( aRow == ( ( Integer ) arlPassedRows.get ( i ) ).intValue () &&
             ( aColumn == ipassedCol || aColumn == toColumn ) )
        {
          return true ;
        }
// commented by Imran on 26th June as we need to edit two columns which are apart from each other
// and we dont want to edit columns between them.
// and also added above 2 lines and commneted below 4 lines

//                if(aRow == ((Integer)arlPassedRows.get(i)).intValue() && aColumn >= ipassedCol && aColumn <= toColumn) {
//                    //Debug.debugLog( "1111This is editable :: CustomAbstractTableModel: isCellEditable( " + aRow + ", " + aColumn + " )" );
//                    return true;
//                }
      }
      //else
      //return false;
      //if(aRow == ipassedRow && aColumn >= ipassedCol && aColumn <= toColumn) {   temporary commented since it creates a lot of effects by kunal
      if ( aRow == ipassedRow && aColumn >= ipassedCol && aColumn <= toColumn )
      {
        //Debug.debugLog( "2222This is editable :: CustomAbstractTableModel: isCellEditable( " + aRow + ", " + aColumn + " )" );
        return true ;
      }
      else
      {
        //Debug.debugLog( "33333This is not editable :: CustomAbstractTableModel: isCellEditable( " + aRow + ", " + aColumn + " )" );
        return false ;
      }
    }
    if ( ( aRow == ipassedRow && aColumn > ipassedCol ) ||
         ( aRow == ipassedRow2 && aColumn > ipassedCol ) )
    {
      return true ;
    }
    else
    {
      return false ;
    }
  }

  public void setRowColumn ( int iEnableRow , int iEnableColumn )
  {
    //Debug.debugLog("in setRowCol method..."+iEnableRow + "...iEnableColumn..."+ iEnableColumn) ;
    ipassedCol = iEnableColumn ;
    arlPassedRows.add ( new Integer ( iEnableRow ) ) ;
  }

  public void setTwoRowsColumn ( int iEnableRow1 , int iEnableRow2 ,
                                 int iEnableColumn )
  {
    ipassedCol = iEnableColumn ;
    ipassedRow = iEnableRow1 ;
    ipassedRow2 = iEnableRow2 ;
  }

  public void setToColumn ( int toColumn )
  {
    //Debug.debugLog("in setTOColumn; method"+toColumn) ;
    this.toColumn = toColumn ;
  }

  public void cleanArrayList ()
  {
    arlPassedRows = new ArrayList () ;
  }
  //manish
  /*public CellAttribute getCellAttribute() {
	  cellAtt = new DefaultCellAttribute(10,8);
	    return cellAtt;
	  }*/
  //manish
}


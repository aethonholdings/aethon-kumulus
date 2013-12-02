package com.scannerapp.apps.component ;

import java.awt.Component;
import java.awt.Container;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.log4j.Logger;

/*
 *======================Modification History======================
 *
 * Date      Author           		 Code #        				Description of Modifications
 * --------  ----------------  ----------------  		--------------------------------------
  30/04/2005 Hardik Shah	   CustomTableModel#1       For proper sorting, change some logic for sorting.
  12/05/2010 Manish Vithlani   CustomTableModel#2       QA Issue#10288(Sorting is not working correctly on Physical column of Physical Inventory Screen)
  18/08/2010 Jiten Patel       CustomTableModel#3       Client Issue #1435 
  06/01/2011 Shraddha Panchamiya	CustomTableModel#4	Unit Test Issue#6329
  15/03/2011 Srhaddha Panchamiya	CustomTableModel#5	Client Issue#94
 */

public class CustomAbstractTableModel
    extends AbstractTableModel
	implements  KeyListener
{
	private static Logger log = Logger.getLogger(CustomAbstractTableModel.class);//For Log4j
    private int LastSortColumnIndex = -1 ;
  //ADDED BY PRANAV..WILL BE USED TO STORE LIST OF COLUMNS ON WHICH WE DON'T WANT SORTING 6/27/2004 5:24PM
  private Hashtable hNosort = null ;
  //FINISH 6/27/2004 5:24PM
  public final int getLastSortColumnIndex ()
  {
    return LastSortColumnIndex ;
  }

  public final void setLastSortColumnIndex ( int newValue )
  {
    LastSortColumnIndex = newValue ;
  }

  private boolean AscendingOrder = false ;
  public final boolean isAscendingOrder ()
  {
    return AscendingOrder ;
  }

  public final void setAscendingOrder ( boolean newValue )
  {
    AscendingOrder = newValue ;
  }

  /**
   * getData Method
   *
   * This method will return all data in Vector format.
   * This Vector contains all its elements as Vectors ( rows ) and
   * this row Vector contains all its elements as Objects( column data for that row ).
   *
   */
  public Vector getData ()
  {
    if ( rows != null )
    {
      Vector data = new Vector ( rows.size () ) ;
      for ( int i = 0 ; i < rows.size () ; i++ )
      {
        data.add ( i , ( Vector ) rows.elementAt ( indexes[ i ] ) ) ;
      }
      return data ;
    }
    else
    {
      return null ;
    }
  }

  /**
   * getDataArray Method
   *
   * This method will return all data in form of two dimensional Object array ( Object[][] ).
   *
   */
  public Object[][] getDataArray ()
  {
    if ( rows != null )
    {
      Vector row ;

      //Debug.debugLog( "CustomAbstractTableModel:getDataArray: Rows = " + numRows + ", Columns = " + numColumns );
      Object[][] data = new Object[ numRows ][ numColumns ] ;
      for ( int i = 0 ; i < numRows ; i++ )
      {
        row = ( Vector ) rows.elementAt ( indexes[ i ] ) ;
        for ( int j = 0 ; j < numColumns ; j++ )
        {
          data[ i ][ j ] = row.elementAt ( j ) ;
        }
      }
      row = null ;
      return data ;
    }
    return null ;
  }

  /**
   * compareRowsByColumn Method
   */
  private int compareRowsByColumn ( int row1 , int row2 , int column )
  {
    Class type = getColumnClass ( column ) ;

    //log.debug("compareRowsByColumn() " + type + " " + type.toString());
    return compareRowsByType ( row1 , row2 , column , type ) ;
  }

  /**
   * compare Method
   */
  private int compare ( int row1 , int row2 )
  {
    compares++ ;
    int size = sortingColumns.size () ;
    for ( int level = 0 ; level < size ; level++ ) /*sortingColumns contains index of column in tableModel(?) which has to be sorted*/
    {
      Integer column = ( Integer ) sortingColumns.elementAt ( level ) ;
      int result = -1 ;
      if ( areColumnClassesSpecified )
      {
        result = compareRowsByType ( row1 , row2 , column.intValue () ,
                                     columnTypes[ column.intValue () ] ) ;
      }
      else
      {
        result = compareRowsByColumn ( row1 , row2 , column.intValue () ) ;
      }
      if ( result != 0 )
      {
        return ( AscendingOrder ? -result : result ) ;
      }
    }
    return 0 ;
  }

  /**
   * reallocateIndexes Method
   */
  public void reallocateIndexes ()
  {
    int rowCount = getRowCount () ;

    // Set up a new array of indexes with the right number of elements

    // for the new data model.
    indexes = new int[ rowCount ] ;

    // Initialise with the identity mapping.
    for ( int row = 0 ; row < rowCount ; row++ )
    {
      indexes[ row ] = row ;
    }
  }

  /**
   * checkModel Method
   */
  private void checkModel ()
  {
    if ( indexes != null )
    {
      if ( indexes.length != getRowCount () )
      {
        reallocateIndexes () ;
        log.debug("CustomAbstractTableModel: checkModel: Sorter not informed of a change in model." ) ;
      }
    }
  }

  /**
   * sort Method
   */
  public void sort ()
  {
    checkModel () ;
    compares = 0 ;
    n2sort () ;
    
  }

  /**
   * n2sort Method
   */
  private void n2sort ()
  {
    for ( int i = 0 ; i < getRowCount () ; i++ )
    {
      for ( int j = i + 1 ; j < getRowCount () ; j++ )
      {
        if ( compare ( indexes[ i ] , indexes[ j ] ) == -1 )
        {
          swap ( i , j ) ;
        }
      }
    }
  }

  /**
   * swap Method
   */
  private void swap ( int i , int j )
  {
    if ( indexes != null )
    {
      if ( i < indexes.length && j < indexes.length )
      {
        int tmp = indexes[ i ] ;
        indexes[ i ] = indexes[ j ] ;
        indexes[ j ] = tmp ;
      }
    }
  }

  /**
   * sortByColumn Method
   */
  private void sortByColumn ( int column )
  {
    try
    {
      sortingColumns.removeAllElements () ;

      //Debug.debugLog("Sorting on column " + column + " in table model");
      sortingColumns.addElement ( new Integer ( column ) ) ;
      dateFound = true ;
      sort () ;
      int length = indexes.length ;
      for ( int i = 0 ; i < length ; i++ )
      {

        //Debug.debugLog("indexes[i] = " + indexes[i]);
        if ( indexes[ i ] == selindex )
        {

          //Debug.debugLog("Setting row selection on row = " + i);
          tableView.getSelectionModel ().setSelectionInterval ( i , i ) ;
          Rectangle cellRect = tableView.getCellRect ( i , column , false ) ;
          tableView.scrollRectToVisible ( cellRect ) ;
          break ;
        }
      }
      tableView.revalidate () ;
      //code modification start by manish for QA issue#5630
      if(length==1)
      {
    	  tableView.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
          tableView.repaint();
          tableView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
          tableView.requestFocus();
          
          
      }
      else
      {
    	tableView.repaint () ;
      }
      //code modification Finish by manish for QA issue#5630
      
      
    }
    catch ( Exception e )
    {
    	log.debug( "CustomAbstractTableModel : sortByColumn() " + e ) ;
    }

    //Debug.debugLog("End of for loop selected row in table = " + tableView.getSelectedRow());
  }

  /**
   * addMouseListenerToHeaderInJTable Method
   */
  public void addMouseListenerToHeaderInTable ( JTable jt )
  {
    if ( !isMouseListenerAdded )
    {
      setTable ( jt ) ;
      isSortingEnabled = true ;
      listMouseListener = new EMouseAdapter ( tableView.getTableHeader () ,
                                              tableView.getColumnModel () ,
                                              getSortTableHeaderRenderer () ) ;
      tableView.getTableHeader ().addMouseListener ( listMouseListener ) ;
      isMouseListenerAdded = true ;

      //Debug.debugLog("Leaving addMouseListenerToHeaderInTable");
    }
  }

  /**
   * resetSortOrder Method
   */
  public void resetSortOrder ()
  {
    try
    {
      int col = tableView.convertColumnIndexToView ( getLastSortColumnIndex () ) ;

      //Debug.debugLog("LastSortColumnIndexForView() = "+col);
      if ( col != -1 )
      {
        JTableHeader header = tableView.getTableHeader () ;
        TableColumnModel model = tableView.getColumnModel () ;
        if ( header.getResizingColumn () == null )
        {
          if ( AscendingOrder )
          {
            listMouseListener.getRenderer ().setState ( new Integer (
                SortTableHeaderRenderer.UP ) , col ) ;
          }
          else
          {
            listMouseListener.getRenderer ().setState ( new Integer (
                SortTableHeaderRenderer.DOWN ) , col ) ;
          }
          listMouseListener.getRenderer ().setPressedColumn ( col ) ;
          listMouseListener.getRenderer ().setSelectedColumn ( col ) ;
          header.repaint () ;
          model.getColumn ( col ).setHeaderRenderer ( listMouseListener.
              getRenderer () ) ;
          sortByColumn ( getLastSortColumnIndex () ) ;
        }
      }
    }
    catch ( NullPointerException npe )
    {
    	log.debug ( "CustomAbstractTableModel: Inside resetSortOrder() " +
                           npe ) ;
    }
  }

  public CustomAbstractTableModel ()
  {
    super () ;
    indexes = new int[ 0 ] ; // For consistency.
  }

  public CustomAbstractTableModel ( Vector aColumnNames , Vector aData )
  {
    super () ;
    setColumnName ( aColumnNames ) ;
    if ( aData != null )
    {
      setData ( aData ) ;
    }
  }

  public CustomAbstractTableModel ( String aColumnNames[] , Object aData[][] )
  {
    super () ;
    setColumnName ( aColumnNames ) ;
    if ( aData != null )
    {
      setData ( aData ) ;
    }
  }

  public Object getCellValue ( int aRow , int aColumn )
  {
    return getValueAt ( aRow , aColumn ) ;
  }

  public void setConnection ( Connection aCon )
  {
    try
    {
      this.connection = aCon ;
      statement = connection.createStatement () ;
    }
    catch ( Exception ex )
    {
    	log.debug ( "CustomAbstractTableModel: setConnection: " + ex , ex ) ;
    }
  }

  public void setResultSet ( ResultSet aResultSet )
  {
    try
    {
      QUERY = true ;
      this.resultSet = aResultSet ;
      metaData = resultSet.getMetaData () ;
      columnNames = null ;
      String cNames[] ;

      //get column names
      if ( columnNames == null )
      {
        int numberOfColumns = metaData.getColumnCount () ;
        if ( numberOfColumns <= 0 )
        {
          return ;
        }
        numColumns = numberOfColumns ;
        cNames = new String[ numberOfColumns ] ;
        for ( int column = 0 ; column < numberOfColumns ; column++ )
        {
          cNames[ column ] = metaData.getColumnName ( column + 1 ) ;

          //Debug.debugLog("metaData.getColumnName( column+1 ) = " +  cNames[column]);
        }
        setColumnName ( cNames ) ;
      }

      // Get all rows.
      rows = new Vector ( 1 ) ;
      while ( resultSet.next () )
      {
        Vector newRow = new Vector ( 1 ) ;
        for ( int i = 1 ; i <= getColumnCount () ; i++ )
        {
          Object obj = resultSet.getObject ( i ) ;
          if ( obj != null && obj instanceof String )
          {
            obj = ( ( String ) obj ).trim () ;
          }
          newRow.add ( i - 1 , obj ) ;
        }
        rows.addElement ( newRow ) ; //remove sync error
      }
      numRows = rows.size () ;
      reallocateIndexes () ;

      // Tell the listeners a new table has arrived.
      if ( tableView != null )
      {
        tableView.getColumnModel ().removeColumnModelListener ( tcml ) ;
      }
      fireTableChanged ( new TableModelEvent ( this , -1 , -1 ) ) ;
      if ( tableView != null )
      {
        tableView.getColumnModel ().addColumnModelListener ( tcml ) ;
      }
    }
    catch ( SQLException sqlex )
    {
      System.err.println ( sqlex ) ;
    }

    //setLastSelectedRowIndexByData();

    /*
         if(getLastSortColumnIndex() != -1)
         resetSortOrder();
         if( rows.size() > 0  && calculateColWidth)
         adjustColumnWidths();
     */
  }

  public void close () throws SQLException
  {
    if ( resultSet != null )
    {
      resultSet.close () ;
    }
    if ( statement != null )
    {
      statement.close () ;
    }
    if ( connection != null )
    {
      connection.close () ;
    }
  }

  public void setColumnEditable ( int aColumn , boolean anEditable )
  {
    try
    {
      if ( columnsEditable != null )
      {
        if ( aColumn < columnsEditable.length )
        {
          columnsEditable[ aColumn ] = anEditable ;
        }
      }
    }
    catch ( ArrayIndexOutOfBoundsException aiobe )
    {
    	log.debug ( "CustomAbstractTableModel : setColumnEditable() " +
                           aiobe ) ;
    }
  }

  public void setColumnName ( String aColumnNames[] )
  {
    this.columnNames = aColumnNames ;
    columnsEditable = new boolean[ columnNames.length ] ;
    for ( int i = 0 ; i < columnsEditable.length ; i++ )
    {
      columnsEditable[ i ] = false ;
    }
    numColumns = columnNames.length ;
  }

  public void setColumnName ( Vector aColumnNames )
  {
    this.columnNames = new String[ aColumnNames.size () ] ;
    for ( int i = 0 ; i < aColumnNames.size () ; i++ )
    {
      this.columnNames[ i ] = ( String ) aColumnNames.elementAt ( i ) ;
    }
    columnsEditable = new boolean[ aColumnNames.size () ] ;
    for ( int i = 0 ; i < columnsEditable.length ; i++ )
    {
      columnsEditable[ i ] = false ;
    }
    numColumns = columnNames.length ;
  }

  public void setColumnName ( ArrayList aColumnNames )
  {
    this.columnNames = new String[ aColumnNames.size () ] ;
    for ( int i = 0 ; i < aColumnNames.size () ; i++ )
    {
      this.columnNames[ i ] = ( String ) aColumnNames.get ( i ) ;
    }
    columnsEditable = new boolean[ aColumnNames.size () ] ;
    for ( int i = 0 ; i < columnsEditable.length ; i++ )
    {
      columnsEditable[ i ] = false ;
    }
    numColumns = columnNames.length ;
  }

  public void setData ( Object aData[][] )
  {
    try
    {

      //setLastSelectedRowData();
      rows = new Vector ( aData.length ) ;
      for ( int i = 0 ; i < aData.length ; i++ )
      {
        Vector newRow = new Vector ( getColumnCount () ) ;
        for ( int j = 0 ; j < getColumnCount () ; j++ )
        {
          newRow.add ( j , aData[ i ][ j ] ) ;
        }
        rows.add ( i , newRow ) ;
      }
      if ( rows.size () > 0 )
      {
        numColumns = ( ( Vector ) rows.elementAt ( 0 ) ).size () ;
      }
      numRows = rows.size () ;
      reallocateIndexes () ;
      if ( tableView != null )
      {
        tableView.getColumnModel ().removeColumnModelListener ( tcml ) ;
      }
      fireTableChanged ( new TableModelEvent ( this , -1 , -1 ) ) ;
      if ( tableView != null )
      {
        tableView.getColumnModel ().addColumnModelListener ( tcml ) ;
      }
    }
    catch ( Exception ex )
    {
    	log.debug( "CustomAbstractTableModel: setData(Object[][]): " +
                       ex.getMessage () , ex ) ;
    }

    //setLastSelectedRowIndexByData();

    /*
         if(getLastSortColumnIndex() != -1)
         resetSortOrder();
         if(numRows > 0 && calculateColWidth)
         adjustColumnWidths();
     */
  }

  public void setData ( Vector aData )
  {

    //setLastSelectedRowData();
    rows = new Vector ( aData.size () ) ;
    this.rows = aData ;
    setUnFilteredRow ( ( Vector ) rows.clone () ) ;
    numRows = rows.size () ;

    //Debug.debugLog("Num rows = " + numRows);
    if ( numRows > 0 )
    {
      numColumns = ( ( Vector ) rows.elementAt ( 0 ) ).size () ;
    }
    if ( aData != null )
    {
      try
      {
        reallocateIndexes () ;
        if ( tableView != null )
        {
          tableView.getColumnModel ().removeColumnModelListener ( tcml ) ;
        }
        fireTableChanged ( new TableModelEvent ( this , -1 , -1 ) ) ;
        if ( tableView != null )
        {
          tableView.getColumnModel ().addColumnModelListener ( tcml ) ;
        }
      }
      catch ( Exception ex )
      {
    	  log.debug ( "CustomAbstractTableModel: setData(Vector): " +
                         ex.getMessage () , ex ) ;
      }
    }

    //setLastSelectedRowIndexByData();

    /*
         if(getLastSortColumnIndex() != -1)
         resetSortOrder();
         if(numRows > 0 && calculateColWidth)
         adjustColumnWidths();
     */
  }

  public void setData ( ArrayList aData )
  {
    try
    {

      //setLastSelectedRowData();
      rows = new Vector ( aData.size () ) ;
      for ( int i = 0 , k = aData.size () ; i < k ; i++ )
      {
        Vector newRow = new Vector ( getColumnCount () ) ;
        for ( int j = 0 ; j < getColumnCount () ; j++ )
        {
          newRow.add ( j , ( ( ArrayList ) aData.get ( i ) ).get ( j ) ) ;
        }
        rows.add ( i , newRow ) ;
      }
      if ( rows.size () > 0 )
      {
        numColumns = ( ( Vector ) rows.elementAt ( 0 ) ).size () ;
      }
      numRows = rows.size () ;
      reallocateIndexes () ;
      if ( tableView != null )
      {
        tableView.getColumnModel ().removeColumnModelListener ( tcml ) ;
      }
      fireTableChanged ( new TableModelEvent ( this , -1 , -1 ) ) ;
      if ( tableView != null )
      {
        tableView.getColumnModel ().addColumnModelListener ( tcml ) ;
      }
    }
    catch ( Exception ex )
    {
    	log.debug ( "CustomAbstractTableModel: setData(Object[][]): " +
                       ex.getMessage () , ex ) ;
    }

    //setLastSelectedRowIndexByData();

    /*
         if(getLastSortColumnIndex() != -1)
         resetSortOrder();
         if(numRows > 0 && calculateColWidth)
         adjustColumnWidths();
     */
  }

  public final void setUnFilteredRow ( Vector anUnFilteredRow )
  {
    if ( unFilteredRow != null )
    {
      unFilteredRow.clear () ;
    }
    this.unFilteredRow = anUnFilteredRow ;
  }

  public void setFilter ( Object aData )
  {
    Vector filteredRow = new Vector ( numRows ) ;
    for ( int i = 0 ; i <= numRows - 1 ; i++ )
    {
      Vector row = ( Vector ) rows.elementAt ( i ) ;
      if ( row.contains ( aData ) )
      {
        filteredRow.add ( i , row ) ;
      }
    }
    this.rows = filteredRow ;
    numRows = rows.size () ;
    reallocateIndexes () ;
    try
    {
      fireTableChanged ( new TableModelEvent ( this , -1 , -1 ) ) ;
    }
    catch ( Exception ex )
    {
    	log.debug ( "CustomAbstractTableModel: setFilter: " + ex.getMessage () ,
                       ex ) ;
    }
  }

  public void removeFilter ()
  {
    setData ( unFilteredRow ) ;
  }

  //////////////////////////////////////////////////////////////////////////

  //

  //             Implementation of the TableModel Interface

  //

  //////////////////////////////////////////////////////////////////////////

  // MetaData
  public String getColumnName ( int aColumn )
  {
    if ( columnNames == null || columnNames.length <= aColumn || aColumn < 0 )
    {
      return super.getColumnName ( aColumn ) ;
    }
    String id = columnNames[ aColumn ] ;
    if ( id == null )
    {
      return super.getColumnName ( aColumn ) ;
    }
    else
    {
      return id ;
    }
  }

  public Class getColumnClass ( int aColumn )
  {
    if ( QUERY )
    {
      int type ;
      try
      {
        type = metaData.getColumnType ( aColumn + 1 ) ;
      }
      catch ( SQLException e )
      {
        return super.getColumnClass ( aColumn ) ;
      }
      switch ( type )
      {
        case Types.CHAR:
        case Types.VARCHAR:
        case Types.LONGVARCHAR:
          return String.class ;

        case Types.BIT:
          return Boolean.class ;

        case Types.TINYINT:
        case Types.SMALLINT:
        case Types.INTEGER:
          return Integer.class ;

        case Types.BIGINT:
        case Types.NUMERIC:
          return Long.class ;

        case Types.FLOAT:
        case Types.DOUBLE:
        case Types.DECIMAL:
          return Double.class ;

        case Types.DATE:
          return java.sql.Date.class ;

        default:
          return Object.class ;
      }
    }
    else
    {
      try
      {
        return ( ( Vector ) rows.elementAt ( 0 ) ).elementAt ( aColumn ).
            getClass () ;
      }
      catch ( Exception e )
      {
        return Object.class ;
      }
    }
  }

  public boolean isCellEditable ( int aRow , int aColumn )
  {

    //Debug.debugLog( "CustomAbstractTableModel: isCellEditable( " + aRow + ", " + aColumn + " )" );
    if ( columnsEditable != null && aColumn < columnsEditable.length &&
         aColumn >= 0 )
    {
      return columnsEditable[ aColumn ] ;
    }
    else
    {
      return false ;
    }
  }

  public int getColumnCount ()
  {
    return columnNames.length ;
  }

  // Data methods
  public int getRowCount ()
  {
    if ( rows != null )
    {
      return rows.size () ;
    }
    else
    {
      return -1 ;
    }
  }

  public Object getValueAt ( int aRow , int aColumn )
  {
    if ( rows != null && indexes != null )
    {
      checkModel () ;
      if ( aRow < rows.size () && aRow < indexes.length )
      {
        Vector row = ( Vector ) rows.elementAt ( indexes[ aRow ] ) ;
        if ( row != null )
        {
          return row.elementAt ( aColumn ) ;
        }
      }
    }
    return null ;
  }

  private void newRowsAdded ( TableModelEvent anEvent )
  {
    try
    {
      numRows = rows.size () ;
      int start = anEvent.getFirstRow () ;
      int end = anEvent.getLastRow () ;
      if ( start < 0 )
      {
        start = 0 ;
      }
      if ( end < 0 )
      {
        end = numRows - 1 ;
      }

      // Have to make sure all the new columns have the correct

      // number of columns
      for ( int i = start ; i <= end ; i++ )
      {
        ( ( Vector ) rows.elementAt ( i ) ).setSize ( numColumns ) ;
      }

      // Now we send the notification
      fireTableChanged ( anEvent ) ;
    }
    catch ( ArrayIndexOutOfBoundsException aiobe )
    {
    	log.debug( "CustomAbstractTableModel : newRowsAdded() " + aiobe ) ;
    }
  }

  public final void setNumColumns ( int aNumColumns )
  {
    this.numColumns = aNumColumns ;
  }

  public final void setNumRows ( int aNumRows )
  {
    this.numRows = aNumRows ;
  }

  public void setRows ( Vector aRows )
  {
    this.rows = aRows ;
    reallocateIndexes () ;
    newRowsAdded ( new TableModelEvent ( this ) ) ;
  }

  public void addRow ( Vector aRowData )
  {
    Vector newRow = aRowData ;
    if ( newRow == null )
    {
      newRow = new Vector ( numColumns ) ;
    }
    rows.addElement ( newRow ) ;
    numRows++ ;
    reallocateIndexes () ;
    fireTableChanged ( new TableModelEvent ( this , numRows - 1 , numRows - 1 ,
                                             TableModelEvent.ALL_COLUMNS ,
                                             TableModelEvent.INSERT ) ) ;

    /*
         if(rows.size() == 1)
         adjustColumnWidths();
     */
  }

  public boolean isDuplicate ( Object aKey )
  {
    for ( int i = 0 ; i <= numRows - 1 ; i++ )
    {
      Vector row = ( Vector ) rows.elementAt ( i ) ;
      if ( row.elementAt ( keyColumn ).getClass ().getName ().equals ( "String" ) )
      {
        String s = ( String ) row.elementAt ( keyColumn ) ;
      }
      if ( aKey.equals ( row.elementAt ( keyColumn ) ) )
      {
        return ( true ) ;
      }
    }
    return ( false ) ;
  }

  public final void setKeyColumn ( int aKeyColumn )
  {
    this.keyColumn = aKeyColumn ;
  }

  public final int getKeyColumn ()
  {
    return ( keyColumn ) ;
  }

  public void search ( Vector aRowData )
  {
    Vector newRow = aRowData ;
    if ( newRow == null )
    {
      newRow = new Vector ( numColumns ) ;
    }
    rows.addElement ( newRow ) ; //aRowData );
    numRows++ ;
    reallocateIndexes () ;

    // Generate notification
    newRowsAdded ( new TableModelEvent ( this , numRows - 1 , numRows - 1 ,
                                         TableModelEvent.ALL_COLUMNS ,
                                         TableModelEvent.INSERT ) ) ;
  }

  public void rowsRemoved ( TableModelEvent anEvent )
  {
    if ( anEvent == null )
    {
      throw new IllegalArgumentException ( "rowsRemoved() - null parameter" ) ;
    }
    numRows = rows.size () ;

    // Now we send the notification
    fireTableChanged ( anEvent ) ;

    //reallocateIndexes();
  }

  public void removeRow ( int aRow )
  {
    if ( rows != null && indexes != null && aRow < rows.size () &&
         aRow < indexes.length && aRow >= 0 )
    {
      rows.removeElementAt ( indexes[ aRow ] ) ;
      numRows -= 1 ;
      reallocateIndexes () ;
      fireTableChanged ( new TableModelEvent ( this , aRow , aRow ,
                                               TableModelEvent.ALL_COLUMNS ,
                                               TableModelEvent.DELETE ) ) ;
    }
  }

  public void removeRow ( int[] aDeletedRows )
  {
    try
    {
      int totalrows = aDeletedRows.length ;
      for ( int row = totalrows - 1 ; row >= 0 ; row-- )
      {
        rows.removeElementAt ( indexes[ aDeletedRows[ row ] ] ) ;
      }
      numRows -= aDeletedRows.length ;
      reallocateIndexes () ;

      // Generate notification
      fireTableChanged ( new TableModelEvent ( this ) ) ;
    }
    catch ( ArrayIndexOutOfBoundsException aiobe )
    {
    	log.debug( "CustomAbstractTableModel: Inside removeRow(int[]) " +
                           aiobe ) ;
    }
    catch ( NullPointerException npe )
    {
    	log.debug( "CustomAbstractTableModel: Inside removeRow(int[]) " +
                           npe ) ;
    }
  }

  public void setValueAt ( Object aValue , int aRow , int aColumn )
  {
    try
    {
      checkModel () ;
      Vector dataRow = ( Vector ) rows.elementAt ( indexes[ aRow ] ) ;
      dataRow.setElementAt ( aValue , aColumn ) ;
      fireTableCellUpdated ( aRow , aColumn ) ;
    }
    catch ( ArrayIndexOutOfBoundsException aiobe )
    {
    	log.debug( "CustomAbstractTableModel: setValueAt " + aiobe ) ;
    }
    catch ( NullPointerException npe )
    {
    	log.debug ( "CustomAbstractTableModel: setValueAt " + npe ) ;
    }
  }

  public void insertRow ( int row , Vector rowData )
  {
    try
    {
      if ( rowData == null )
      {
        rowData = new Vector ( getColumnCount () ) ;
      }
      else
      {
        rowData.setSize ( getColumnCount () ) ;
      }
      rows.insertElementAt ( rowData , row ) ;
      reallocateIndexes () ;

      // Generate notification
      newRowsAdded ( new TableModelEvent ( this , row , row ,
                                           TableModelEvent.ALL_COLUMNS ,
                                           TableModelEvent.INSERT ) ) ;
    }
    catch ( ArrayIndexOutOfBoundsException aiobe )
    {
    	log.debug ( "CustomAbstractTableModel: Inside insertRow() " +
                           aiobe ) ;
    }
  }

  protected SortTableHeaderRenderer getSortTableHeaderRenderer ()
  {
    return new SortTableHeaderRenderer () ;
  }

  private int compareRowsByType ( int row1 , int row2 , int column , Class type )
  {

    //Debug.debugLog("Class type= " + type);
    Vector row = ( Vector ) rows.elementAt ( row1 ) ;
    Object o1 = row.elementAt ( column ) ;
    row = null ;
    row = ( Vector ) rows.elementAt ( row2 ) ;
    Object o2 = row.elementAt ( column ) ; //data.getValueAt(row2, column);

    // If both values are null return 0
    if ( o1 == null && o2 == null )
    {
      return 0 ;
    }
    else
    {
      if ( o1 == null )
      {

        // Define null less than everything.
        return -1 ;
      }
      else
      {
        if ( o2 == null )
        {
          return 1 ;
        }
      }
    }
    //start CustomTableModel#2
    //if ( type.getSuperclass () == java.lang.Number.class )
    if ( (type.getSuperclass () == java.lang.Number.class) ||  (type == java.lang.Number.class) )
    //end CustomTableModel#2	
    {

      //Debug.debugLog("Number class else");
      double d1 = 0.0d , d2 = 0.0d ;
      try
      {
//Start CustomTableModel#1 //uncommented for CustomTableModel#3
                        row = (Vector)rows.elementAt(row1);
                        String s1 = row.elementAt(column).toString().trim();
                        if(s1.equals("")) {
                        	//start for CustomTableModel#5
//                            d1 = -1;
                            d1 = 0;
                          //end for CustomTableModel#5
                        }
                        else {
                        	//start forCustomTableModel#5
//                            d1 = Double.parseDouble(s1);
                            d1 = Double.parseDouble(s1.replace(',', '.'));
                            //end for CustomTableModel#5
                        }
                        row = (Vector)rows.elementAt(row2);
                        String s2 = row.elementAt(column).toString().trim();
                        if(s2.equals("")) {
                        	//start for CustomTableModel#5
//                            d2 = -1;
                            d2 = 0;
                          //end for CustomTableModel#5
                        }
                        else {
                        	//start for CustomTableModel#5
//                            d2 = Double.parseDouble(s2);
                            d2 = Double.parseDouble(s2.replace(',', '.'));
                            //end for CustomTableModel#5
                        }
      //finished uncommented for CustomTableModel#3
      //  d1 = new Double ( o1.toString () ).doubleValue () ;////commented for CustomTableModel#3
      //  d2 = new Double ( o2.toString () ).doubleValue () ;////commented for CustomTableModel#3

      }
      catch ( NumberFormatException e )
      {
    	  log.debug (
            "ERROR : Sorted column contains data which cannot be formatted as a number " +
            e ) ;
      }
      if ( d1 < d2 )
      {
        return -1 ;
      }
      else
      {
        if ( d1 > d2 )
        {
          return 1 ;
        }
        else
        {
          return 0 ;
        }
      }
//End CustomTableModel#1
    }
    else
    {
      if ( type == java.util.Date.class )
      {
//Start CustomTableModel#1
        long n1 = 0 , n2 = 0 ;
        try
        {
          SimpleDateFormat sdf = new SimpleDateFormat ( "dd/MM/yyyy" ) ;
          Date d1 = sdf.parse ( o1.toString () ) ;
          Date d2 = sdf.parse ( o2.toString () ) ;

          n1 = d1.getTime () ;
          n2 = d2.getTime () ;
//					    n1 = ((Date)o1).getTime();
//					    n2 = ((Date)o2).getTime();
        }
        catch ( Exception ex )
        {
          ex.printStackTrace () ;
        }
        if ( n1 < n2 )
        {
          return -1 ;
        }
        else if ( n1 > n2 )
        {
          return 1 ;
        }
        else
        {
          return 0 ;
        }

        /*                //the following may not work if default date format of MMM dd, yyyy is changed
                        long n1 = 0, n2 = 0;
             SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            row = (Vector)rows.elementAt(row1);
                            //Date d1 = (Date)row.elementAt( column );
             String s1 = row.elementAt(column).toString().trim();
                            if(s1.equals("") || s1.equals("N/A")) {
                                n1 = -1;
                            }
                            else {
                                Date d1 = sdf.parse(s1);
                                //Date d1 = new Date(s1);
                                n1 = d1.getTime();
                            }
                            row = (Vector)rows.elementAt(row2);
                            //Date d2 = (Date)row.elementAt( column );
             String s2 = row.elementAt(column).toString().trim();
                            if(s2.equals("") || s2.equals("N/A")) {
                                n2 = -1;
                            }
                            else {
                                //Date d2 = new Date(s2);
                                Date d2 = sdf.parse(s2);
                                //Debug.debugLog("Value1 = " + s2);
                                //Date d2 = sdf.parse(s2);
                                n2 = d2.getTime();
                            }
                        }
                        catch(Exception e) {
                            try {
                                n1 = 0;
                                n2 = 0;
             String s1 = row.elementAt(column).toString().trim();
                                if(s1.equals("")) {
                                    n1 = -1;
                                }
                                else {
                                    s1 = "Jan 1, 2001 " + s1;
                                    Date d1 = new Date(s1);
                                    n1 = d1.getTime();
                                }
                                row = (Vector)rows.elementAt(row2);
             String s2 = row.elementAt(column).toString().trim();
                                if(s2.equals("")) {
                                    n2 = -1;
                                }
                                else {
                                    s2 = "Jan 1, 2001 " + s2;
                                    //Debug.debugLog("Value2 = " + s2);
                                    //Date d2 = sdf2.parse(s2);
                                    Date d2 = new Date(s2);
                                    //Debug.debugLog("Date2 = " + d2.toString());
                                    n2 = d2.getTime();
                                //Debug.debugLog("n2 = " + n2);
                                }
                            }
                            catch(Exception e1) {
                                log.debug("ERROR : Sorted column contains data which cannot be formatted as a date " + e1);
                            }
                        }
                        if(n1 < n2) {
                            return -1;
                        }
                        else {
                            if(n1 > n2) {
                                return 1;
                            }
                            else {
                                return 0;
                            }
                        }
         */
//End CustomTableModel#1
      }
      else
      {
        if ( type == String.class )
        {
          if ( dateFound ) /*for first comparison check if it is a date or a string and accordingly this check can be avioded in other comparisons*/
          {
            try
            {
              //start CustomTableModel#2
              long n1 = 0 , n2 = 0 ;//uncommented for CustomTableModel#3
              //Double n1=0.0,n2=0.0; //commented for CustomTableModel#3
              //end CustomTableModel#2
              //start CustomTableModel#4
//              SimpleDateFormat sdf = new SimpleDateFormat ( "dd/MM/yyyy hh:mm:ss" ) ;
              SimpleDateFormat sdf = new SimpleDateFormat ( "dd/MM/yyyy HH:mm:ss" ) ; // "H" for time format of 0-23 hour
              //end CustomTableModel#4
              Date date1= sdf.parse((o1.toString ()).replace("-", " "));
              Date date2= sdf.parse((o2.toString ()).replace("-", " "));
              
              Calendar c1= Calendar.getInstance();
              c1.setTime(date1);
              
              Calendar c2= Calendar.getInstance();
              c2.setTime(date2);
              
             /* commented for CustomTableModel#3
              * row = ( Vector ) rows.elementAt ( row1 ) ;
              String s1 = row.elementAt ( column ).toString ().trim () ;
              
              //start CustomTableModel#2
              //if ( s1.equals ( "" ) )
              if ( s1.equals ( "" ) ||  s1 == null)
              //end CustomTableModel#2
            	  
              {
            	  //start CustomTableModel#2
            	  n1 = -1 ;//uncommented for CustomTableModel#3
            	  //  n1 = -1.0 ;//commented for CustomTableModel#3
            	  //end CustomTableModel#2
              }
              else
              {
                //changed by manish for deprication removel
            	//Date d1 = new java.util.Date ( s1 ) ;
            	  
            	//start CustomTableModel#2  
            	Long l=new Long(s1);//uncommented for CustomTableModel#3
            	//  n1=new Double(s1);//commented for CustomTableModel#3
            	Date d1 = new java.util.Date ( l ) ;//uncommented for CustomTableModel#3
                n1 = d1.getTime () ;//uncommented for CustomTableModel#3
                //end CustomTableModel#2
              }
              row = ( Vector ) rows.elementAt ( row2 ) ;
              String s2 = row.elementAt ( column ).toString ().trim () ;
              
              //start CustomTableModel#2
              //if ( s2.equals ( "" ) )
              if ( s2.equals ( "" ) || s2 == null )
              //end CustomTableModel#2	  
              {
            	   //start CustomTableModel#2
            	  	n2 = -1 ;//uncommented for CustomTableModel#3
            	    //  n2 = -1.0 ;//commented for CustomTableModel#3
            	  //end CustomTableModel#2
              }
              else
              {
            	//changed by manish for deprication removel
              	//Date d2 = new Date ( s2 ) ;
            	  
            	//start CustomTableModel#2    
              	Long l=new Long(s2);//uncommented for CustomTableModel#3
            	  //n2 =new Double(s2);//commented for CustomTableModel#3
              	Date d2 = new java.util.Date ( l ) ;//uncommented for CustomTableModel#3
            	n2 = d2.getTime () ;//uncommented for CustomTableModel#3
            	//end CustomTableModel#2
              }*/
              if (c1.before(c2) )
              {
                return -1 ;
              }
              else
              {
                if ( c1.after(c2) )
                {
                  return 1 ;
                }
                else
                {
                  return 0 ;
                }
              }
            }
            catch ( Exception e )
            {
              dateFound = false ;
              row = ( Vector ) rows.elementAt ( row1 ) ;
              String s1 = row.elementAt ( column ).toString ().trim ().
                  toUpperCase () ;
              row = ( Vector ) rows.elementAt ( row2 ) ;
              String s2 = row.elementAt ( column ).toString ().trim ().
                  toUpperCase () ;
              int result = s1.compareTo ( s2 ) ;
              if ( result < 0 )
              {
                return -1 ;
              }
              else
              {
                if ( result > 0 )
                {
                  return 1 ;
                }
                else
                {
                  return 0 ;
                }
              }
            }
          }
          else
          {
            row = ( Vector ) rows.elementAt ( row1 ) ;
            String s1 = row.elementAt ( column ).toString ().trim ().
                toUpperCase () ;
            row = ( Vector ) rows.elementAt ( row2 ) ;
            String s2 = row.elementAt ( column ).toString ().trim ().
                toUpperCase () ;
            int result = s1.compareTo ( s2 ) ;
            if ( result < 0 )
            {
              return -1 ;
            }
            else
            {
              if ( result > 0 )
              {
                return 1 ;
              }
              else
              {
                return 0 ;
              }
            }
          }
        }
        else
        {
          if ( type == Boolean.class )
          {
            row = ( Vector ) rows.elementAt ( row1 ) ;
            Boolean bool1 = ( Boolean ) row.elementAt ( column ) ;
            boolean b1 = bool1.booleanValue () ;
            row = ( Vector ) rows.elementAt ( row2 ) ;
            Boolean bool2 = ( Boolean ) row.elementAt ( column ) ;
            boolean b2 = bool2.booleanValue () ;
            if ( b1 == b2 )
            {
              return 0 ;
            }
            else
            {
              if ( b1 ) /* Define false < true*/
              {
                return 1 ;
              }
              else
              {
                return -1 ;
              }
            }
          }
          else
          {
            row = ( Vector ) rows.elementAt ( row1 ) ;
            Object v1 = row.elementAt ( column ) ;

            //Object v1 = data.getValueAt(row1, column);
            String s1 = v1.toString () ;
            row = ( Vector ) rows.elementAt ( row2 ) ;
            Object v2 = row.elementAt ( column ) ;

            //Object v2 = data.getValueAt(row2, column);
            String s2 = v2.toString () ;
            try
            {
              int i1 = Integer.valueOf ( s1 ).intValue () ;
              int i2 = Integer.valueOf ( s2 ).intValue () ;
              if ( i1 < i2 )
              {
                return -1 ;
              }
              else
              {
                if ( i1 > i2 )
                {
                  return 1 ;
                }
                else
                {
                  return 0 ;
                }
              }
            }
            catch ( Exception e )
            {
              int result = s1.compareTo ( s2 ) ;
              if ( result < 0 )
              {
                return -1 ;
              }
              else
              {
                if ( result > 0 )
                {
                  return 1 ;
                }
                else
                {
                  return 0 ;
                }
              }
            }
          }
        }
      }
    }
  }

  public void setColumnClassesForSorting ( Class[] colTypes )
  {
    areColumnClassesSpecified = true ;
    columnTypes = colTypes ;
  }

  private int getPreferredWidthForColumn ( int v , TableColumn col )
  {
    int w1 = getWidestCellInColumn ( v , col ) ;

    /*int w2 = getColumnHeaderWidth(v);
             if(w2 > w1)
             {
             //if header width is within 5 pixels of column data width use header width else use column data width
             return w2 > (w1 + 5) ? w1 + 5 : w2;
             }
             else*/
    return w1 ;
  }

  private int getWidestCellInColumn ( int viewIndex , TableColumn col )
  {
    int c = col.getModelIndex () ;

    //Debug.debugLog("ViewIndex = " + viewIndex + " modelIndex = " + c);
    if ( c != -1 )
    {
      int width = 0 , maxw = 0 , minw = 0 ;
      for ( int i = 0 ; i < tableView.getRowCount () ; i++ )
      {
        TableCellRenderer tcr = tableView.getCellRenderer ( i , viewIndex ) ;
        Object data = getValueAt ( i , c ) ; //second arg must be model index
        Component comp = tcr.getTableCellRendererComponent ( tableView , data , false , false ,
            i , c ) ;

        //width = comp.getPreferredSize().width;
        FontMetrics fm = comp.getFontMetrics ( comp.getFont () ) ;

        //Debug.debugLog("getValueAt(i,c) =  " + getValueAt(i,c));

        //Debug.debugLog("Checking string width of " + getValueAt(i,c).toString().trim());
        if ( minw == 0 )
        {
          minw = fm.stringWidth ( "ABCDE" ) + 10 ;
        }
        if ( data != null )
        {
          String s = data.toString ().trim () ;
          if ( s.length () > 40 )
          {
            s = s.substring ( 0 , 40 ) ;
          }

          //Debug.debugLog("Data =  " + s);
          width = fm.stringWidth ( s ) + 10 ;

          //Debug.debugLog("String Width  = " + width);
          if ( width < minw )
          {
            width = minw ;
          }
        }
        else
        {
          width = minw ;
        }

        //Debug.debugLog("Max Width  = " + width);
        maxw = width > maxw ? width : maxw ;
        comp = null ;
        fm = null ;
        data = null ;
        tcr = null ;
      }
      return maxw ;
    }
    else
    {
    	log.debug ( "ERROR" ) ;
      return 0 ;
    }
  }

  /* Code Clean-up : Dhwanil */
//  private void adjustColumnWidths ()
//  {
//    if ( tableView != null )
//    {
//      TableColumnModel model = tableView.getColumnModel () ;
//      TableColumn tcbi = null ;
//      int width = 0 , totalWidth = 0 ;
//      for ( int i = 0 ; i < model.getColumnCount () ; i++ )
//      {
//        tcbi = model.getColumn ( i ) ;
//        width = getPreferredWidthForColumn ( i , tcbi ) ;
//        totalWidth += width ;
//        tcbi.setPreferredWidth ( width ) ;
//      }
//      checkForTotalWidth ( totalWidth ) ;
//    }
//  }
  /**/

/* Code Clean-up : Dhwanil */
//  private int getColumnHeaderWidth ( int v )
//  {
//    int c = tableView.convertColumnIndexToModel ( v ) ;
//    FontMetrics fm = tableView.getTableHeader ().getFontMetrics ( tableView.
//        getTableHeader ().getFont () ) ;
//    int width = fm.stringWidth ( columnNames[ c ] ) + 5 ;
//    return width ;
//  }
  /**/
  private void checkForTotalWidth ( int totalWidth )
  {
    if ( totalWidth == -1 )
    {
      totalWidth = getTotalPreferredWidthForColumns () ;
    }
    int allowedWidth = -1 ;
    if ( jspTable == null )
    {
      getScrollPane () ;
      if ( jspTable == null )
      {
    	  log.debug ( "ERROR : System could not get the instance of the scroll pane from the instance of the JTable. If you table is not contained in a scrollPane, please use tableModel.setTable(jtbl,false) and do not call tableModel.addMouseListenerToHeaderInTable() and tableModel.setTable(jtbl)" ) ;
        allowedWidth = tableView.getWidth () ;
      }
    }

    //int allowedWidth = jspTable.getWidth() - jspTable.getVerticalScrollBar().getWidth();
    if ( jspTable != null )
    {
      allowedWidth = jspTable.getWidth () ;
      if ( jspTable.getVerticalScrollBar ().isShowing () )
      {
        allowedWidth -= jspTable.getVerticalScrollBar ().getWidth () ;

        //Debug.debugLog("Vertical scrollbar is showing");
      }
    }

    //Debug.debugLog("Allowed width = " + allowedWidth + " totalWidth = " + totalWidth);
    if ( totalWidth < allowedWidth )
    {
      int diff = allowedWidth - totalWidth ;
      int colCount = tableView.getColumnModel ().getColumnCount () ;
      if ( colCount > 0 )
      {
        if ( diff > colCount )
        {
          int addValue = ( new Double ( diff / colCount ) ).intValue () ;

          //Debug.debugLog("Addvalue = " + addValue);
          TableColumnModel model = tableView.getColumnModel () ;
          TableColumn tc = null ;
          for ( int i = 0 ; i < colCount ; i++ )
          {
            tc = model.getColumn ( i ) ;
            tc.setPreferredWidth ( tc.getPreferredWidth () + addValue ) ;
          }
          int leftover = allowedWidth - ( totalWidth + ( addValue * colCount ) ) ;

          //Debug.debugLog("Leftover = " + leftover);
          tc.setPreferredWidth ( tc.getPreferredWidth () + leftover ) ;
        }

        //Debug.debugLog("Total column size less than size of scrollpane");
        tableView.sizeColumnsToFit ( -1 ) ;

        //tableView.paintImmediately(tableView.getBounds());
        tableView.repaint () ;
      }
    }
  }

  private int getTotalPreferredWidthForColumns ()
  {
    TableColumnModel model = tableView.getColumnModel () ;
    int totalWidth = 0 ;
    for ( int i = 0 ; i < model.getColumnCount () ; i++ )
    {
      totalWidth += model.getColumn ( i ).getPreferredWidth () ;

      //Debug.debugLog("columns in column model = " + tableView.getColumnName(i));
    }
    return totalWidth ;
  }

  private void getScrollPane ()
  {
    if ( jspTable == null )
    {
      Container cont1 = tableView.getParent () ;
      jspTable = null ;
      if ( cont1 instanceof JViewport )
      {
        JViewport v = ( JViewport ) cont1 ;
        Container cont2 = v.getParent () ;
        jspTable = ( JScrollPane ) cont2 ;
      }
      if ( jspTable != null )
      {
//        jspTable.addComponentListener ( this ) ;
      }
    }
  }

  public void setTable ( JTable jt )
  {
    if ( tableView == null )
    {
      tableView = jt ;

      //Debug.debugLog("TableView is = " + tableView);
      tableView.getColumnModel ().addColumnModelListener ( tcml ) ;

      //Debug.debugLog("After setting table");
      tableView.addKeyListener ( this ) ;
      ActionListener TabKeyAction = new ActionListener ()
      {
        public void actionPerformed ( java.awt.event.ActionEvent anEvent )
        {

        }
      } ;
      tableView.registerKeyboardAction ( TabKeyAction ,
                                         KeyStroke.getKeyStroke ( KeyEvent.
          VK_TAB , 0 , false ) , JComponent.WHEN_FOCUSED ) ;
      getScrollPane () ;

      //Debug.debugLog("Leaving setTable");
    }
  }

  // Methods for implementation of ComponentListener

  public void componentResized ( java.awt.event.ComponentEvent e )
  {
    /* Code Clean-up : Dhwanil */
//    if ( e.getSource () == jspTable )
//    {

      //Debug.debugLog("Component resized called for scrollPane");

      //if( rows.size() > 0  && calculateColWidth)

      //adjustColumnWidths();
/**/
    }

//  }

  public void componentMoved ( java.awt.event.ComponentEvent e )
  {

  }

  public void componentShown ( java.awt.event.ComponentEvent e )
  {

  }

  public void componentHidden ( java.awt.event.ComponentEvent e )
  {

  }

  public void keyTyped ( java.awt.event.KeyEvent e )
  {

    /*
         //Debug.debugLog("tablemodel key typed, key code = " + e.getKeyCode());
         if(isSortingEnabled)
         {
         int row = tableView.getSelectedRow();
         int col = tableView.getSelectedColumn();
         //Debug.debugLog("Row = " + row + " Col = " + col);
         if(row == -1 || col == -1
         //|| (col > tableView.getColumnCount() - 1)
         )
         return;
         //Debug.debugLog("Rows = " + rows);
         if(Character.getType(e.getKeyChar()) != 15)
         {
         int modelColIndex = tableView.convertColumnIndexToModel(col);
         if(!columnsEditable[modelColIndex])
         {
         if(col != keyCol)
         sb.replace(0,sb.length(),"");
         keyCol = col;
         //Debug.debugLog("Row = " + row + " Col = " + col + " Col. index in model = " + modelColIndex);
         if(!(isAscendingOrder() && getLastSortColumnIndex() == modelColIndex))
         {
         setLastSortColumnIndex(modelColIndex);//model index
         setAscendingOrder(true);
         resetAllColumnHeaderRenderers(col);//view index
         resetSortOrder();
         }
         //Debug.debugLog("Key typed = " + e.getKeyChar() + ", keyCode = " + e.getKeyCode());
         sb.append(e.getKeyChar());
         for(int i = 0 ; i < getRowCount() ; i++)
         {
         String s = ( getValueAt(i,modelColIndex) == null ) ? "" : getValueAt(i,modelColIndex).toString().trim();
         //Debug.debugLog("Matching " + sb.toString() + " with  " + s);
         boolean matches = sb.toString().regionMatches(true,0,s,0,sb.toString().length());
         if(matches)
         {
         //Debug.debugLog("Match found with row = " + i);
         selindex = indexes[i];
         //tableView.setRowSelectionInterval(i,i);
         tableView.getSelectionModel().setSelectionInterval( i, i );
         Rectangle cellRect = tableView.getCellRect( i, col, false );
         tableView.scrollRectToVisible( cellRect );
         break;
         }
         }
         tableView.repaint();
         }
         }
         }
     */
  }

  public void keyPressed ( java.awt.event.KeyEvent e )
  {
    if ( isSortingEnabled )
    {
      if ( e.getKeyCode () == KeyEvent.VK_ESCAPE )
      {
        sb.replace ( 0 , sb.length () , "" ) ;
      }
    }
  }

  public void keyReleased ( java.awt.event.KeyEvent e )
  {
    if ( e.getKeyCode () == KeyEvent.VK_TAB && !e.isShiftDown () &&
         !e.isControlDown () )
    {
      handleTabEvent ( e ) ;
    }
  }

  public CustomAbstractTableModel ( JTable jtbl )
  {
    super () ;
    indexes = new int[ 0 ] ; // For consistency.
    setTable ( jtbl ) ;
  }

  public CustomAbstractTableModel ( String aColumnNames[] , Object aData[][] ,
                                    JTable jtbl )
  {
    super () ;
    setColumnName ( aColumnNames ) ;
    setTable ( jtbl ) ;
    if ( aData != null )
    {
      setData ( aData ) ;
    }
  }

  public CustomAbstractTableModel ( Vector aColumnNames , Vector aData ,
                                    JTable jtbl )
  {
    super () ;
    setColumnName ( aColumnNames ) ;
    setTable ( jtbl ) ;
    if ( aData != null )
    {
      setData ( aData ) ;
    }
  }

  public final void disableColWidthCalc ( boolean b )
  {
    this.calculateColWidth = b ;
  }

  public void setTable ( JTable jtbl , boolean isTableInScrollPane )
  {
    tableView = jtbl ;

    //Debug.debugLog("TableView is = " + tableView);
    calculateColWidth = isTableInScrollPane ;
  }

  public CustomAbstractTableModel ( String aColumnNames[] , Object aData[][] ,
                                    JTable jtbl , boolean enableColWidthSetting )
  {
    super () ;
    setColumnName ( aColumnNames ) ;
    if ( enableColWidthSetting )
    {
      setTable ( jtbl ) ;
    }
    else
    {
      setTable ( jtbl , false ) ;
    }
    if ( aData != null )
    {
      setData ( aData ) ;
    }
  }

  public CustomAbstractTableModel ( Vector aColumnNames , Vector aData ,
                                    JTable jtbl , boolean enableColWidthSetting )
  {
    super () ;
    setColumnName ( aColumnNames ) ;
    if ( enableColWidthSetting )
    {
      setTable ( jtbl ) ;
    }
    else
    {
      setTable ( jtbl , false ) ;
    }
    if ( aData != null )
    {
      setData ( aData ) ;
    }
  }

  private void resetAllColumnHeaderRenderers ( int col )
  {
    dtcr = tableView.getColumnModel ().getColumn ( col ).getHeaderRenderer () ;
    if ( ! ( dtcr instanceof SortTableHeaderRenderer ) )
    {
      for ( int i = 0 ; i < tableView.getColumnCount () ; i++ )
      {
        tableView.getColumnModel ().getColumn ( i ).setHeaderRenderer ( dtcr ) ;
      }
    }
  }

  private int findMatch ( int startIndex , int endIndex , int lastIndex )
  {
    String s = ( getValueAt ( endIndex , LastSortColumnIndex ) == null ) ? "" :
        getValueAt ( endIndex , LastSortColumnIndex ).toString ().trim () ;
    boolean matches = sb.toString ().regionMatches ( true , 0 , s , 0 ,
        sb.toString ().length () ) ;
    if ( matches )
    {
      int mid = ( new Float ( ( endIndex - startIndex ) / 2 ) ).intValue () +
          startIndex ;
      return findCloseMatch ( startIndex , mid , endIndex ) ; //first match lies between start and endIndex
    }
    else
    {
      if ( ( lastIndex - startIndex ) <= 1 )
      {
        return selindex ;
      }
      if ( sb.toString ().toUpperCase ().compareTo ( s.toUpperCase () ) < 0 )
      {
        int mid = ( new Float ( ( endIndex - startIndex ) / 2 ) ).intValue () ;
        return findMatch ( startIndex , mid , endIndex ) ;
      }
      else
      {
        if ( sb.toString ().toUpperCase ().compareTo ( s.toUpperCase () ) > 0 )
        {
          int mid = ( new Float ( ( lastIndex - endIndex - 1 ) / 2 ) ).intValue () +
              endIndex + 1 ;
          return findMatch ( endIndex + 1 , mid , lastIndex ) ;
        }
      }
    }
    return 0 ;
  }

  private int findCloseMatch ( int startIndex , int mid , int endIndex )
  {
    String s = ( getValueAt ( mid , LastSortColumnIndex ) == null ) ? "" :
        getValueAt ( mid , LastSortColumnIndex ).toString ().trim () ;
    boolean matches = sb.toString ().regionMatches ( true , 0 , s , 0 ,
        sb.toString ().length () ) ;
    if ( matches )
    {
      if ( startIndex == mid )
      {
        return mid ;
      }
      else
      {
        int newMid = ( new Float ( ( mid - startIndex ) / 2 ) ).intValue () +
            startIndex ;
        return findCloseMatch ( startIndex , newMid , mid ) ;
      }
    }
    else
    {
      int newMid = ( new Float ( ( endIndex - mid - 1 ) / 2 ) ).intValue () +
          mid + 1 ;
      return findCloseMatch ( mid + 1 , newMid , endIndex ) ;
    }
  }

  private void setLastSelectedRowData ()
  {

  }

  private void setLastSelectedRowIndexByData ()
  {

  }

  public void cleanup ()
  {
    if ( tableView != null )
    {
      tableView.getColumnModel ().removeColumnModelListener ( tcml ) ;
      tableView.getTableHeader ().removeMouseListener ( listMouseListener ) ;
      tableView.setTableHeader ( null ) ;
      tableView.removeKeyListener ( this ) ;
      tableView.removeNotify () ;
    }
    tableView = null ;
    tcml = null ;
    listMouseListener = null ;
    if ( jspTable != null )
    {
//      jspTable.removeComponentListener ( this ) ;
    }
    jspTable = null ;
    sb = null ;
    connection = null ;
    statement = null ;
    resultSet = null ;
    metaData = null ;
    indexes = null ;
    sortingColumns = null ;
    columnTypes = null ;
    rows = null ;
    unFilteredRow = null ;
    columnsEditable = null ;
    columnNames = null ;
    dtcr = null ;
  }

  protected void finalize () throws Throwable
  {
    cleanup () ;
    super.finalize () ;
  }

  private void handleTabEvent ( java.awt.event.InputEvent anEvent )
  {
    int row = tableView.getSelectedRow () ;
    if ( row == -1 )
    {
      row = 0 ;
      tableView.getSelectionModel ().setSelectionInterval ( 0 , 0 ) ;
    }
    int col = ( tableView.getSelectedColumn () == -1 ) ?
        tableView.getEditingColumn () : tableView.getSelectedColumn () ;
    if ( col == -1 || ( col == tableView.getColumnCount () - 1 ) )
    {
      if ( col != -1 )
      {
        if ( tableView.isEditing () )
        {
          tableView.getCellEditor ().stopCellEditing () ;
        }
        if ( ( row + 1 ) < tableView.getRowCount () )
        {
          row++ ;
          tableView.getSelectionModel ().setSelectionInterval ( row , row ) ;
        }
        else
        {
          tableView.getSelectionModel ().setSelectionInterval ( 0 , 0 ) ;
        }
        tableView.setColumnSelectionInterval ( 0 , 0 ) ;

        //Debug.debugLog("Returning");
        return ;
      }

      //tableView.getSelectionModel().setSelectionInterval(row,row);
      tableView.setColumnSelectionInterval ( 0 , 0 ) ;
    }
    if ( tableView.isEditing () )
    {
      if ( tableView.getCellEditor ().stopCellEditing () )
      {
        tableView.setColumnSelectionInterval ( col + 1 , col + 1 ) ;
      }
    }
    else
    {
      tableView.setColumnSelectionInterval ( col + 1 , col + 1 ) ;
    }
    if ( tableView.isCellEditable ( row , ( col + 1 ) ) )
    {

      //tableView.setColumnSelectionInterval(col+1,col+1);
      Rectangle cellRect = tableView.getCellRect ( row , ( col + 1 ) , false ) ;
      tableView.scrollRectToVisible ( cellRect ) ;

      //Debug.debugLog("setting Next cell is editable at (" + row + "," + (col+1) + ")");
      tableView.setEditingRow ( row ) ;
      tableView.setEditingColumn ( col + 1 ) ;
      tableView.editCellAt ( row , ( col + 1 ) , anEvent ) ;

      //tableView.removeColumnSelectionInterval(col,col);
    }

    //else

    //Debug.debugLog("cell not editable ");
  }

  // add your data members here
  EMouseAdapter listMouseListener = null ;
  private boolean[] columnsEditable ;
  private boolean areColumnClassesSpecified = false , calculateColWidth = true ,
      isMouseListenerAdded = false ;
  private boolean isSortingEnabled = false , dateFound = false ;
  protected String[] columnNames =
      {} ;
  private Class[] columnTypes =
      {} ;
  protected Vector rows = new Vector ( 1 ) ;
  protected int numRows ;
  protected int numColumns ;
  private int keyColumn = -1 ;
  private Vector unFilteredRow = new Vector ( 1 ) ;
  protected boolean QUERY = false ;
  private Connection connection ;
  private Statement statement ;
  protected ResultSet resultSet ;
  protected ResultSetMetaData metaData ;
  private int indexes[] ;
  private Vector sortingColumns = new Vector ( 1 ) ;
  private int compares ;
  private int selindex = 0 ; //keyCol = -1 ;
  private TableCellRenderer dtcr ;
  private JTable tableView ;
  private JScrollPane jspTable ;
  private StringBuffer sb = new StringBuffer () ;
  private TableColumnModelListener tcml = new TableColumnModelListener ()
  {
    public void columnAdded ( javax.swing.event.TableColumnModelEvent e )
    {

    }

    public void columnRemoved ( javax.swing.event.TableColumnModelEvent e )
    {
      if ( rows.size () > 0 && calculateColWidth )
      {
        checkForTotalWidth ( -1 ) ;
      }
    }

    public void columnMoved ( javax.swing.event.TableColumnModelEvent e )
    {

    }

    public void columnMarginChanged ( javax.swing.event.ChangeEvent e )
    {

    }

    public void columnSelectionChanged ( javax.swing.event.ListSelectionEvent e )
    {

    }
  } ;
  private class EMouseAdapter
      extends MouseAdapter
  {
    SortTableHeaderRenderer renderer = null ;
    JTableHeader header = null ;
    TableColumnModel model = null ;
    EMouseAdapter ( JTableHeader header , TableColumnModel model ,
                    SortTableHeaderRenderer renderer )
    {
      this.renderer = renderer ;
      this.header = header ;
      this.model = model ;
    }

    public void mousePressed ( MouseEvent e )
    {
      int col = header.columnAtPoint ( e.getPoint () ) ;
      int sortCol = header.getTable ().convertColumnIndexToModel ( col ) ;
      //FOLLOWING IF CONDITION MAKES SURE THAT WE ARE NOT DOING SORTING ON COLUMNS STORED IN hNosort HAeshtable... 6/27/2004 5:25PM
      if ( hNosort == null || !hNosort.containsKey ( String.valueOf ( sortCol ) ) )
      {
        if ( header.getResizingColumn () == null )
        {
          //Debug.debugLog("CustomAbstract Table model :: mouse pressed");
          resetAllColumnHeaderRenderers ( col ) ;
          renderer.setPressedColumn ( col ) ;
          renderer.setSelectedColumn ( col ) ;
          setLastSortColumnIndex ( sortCol ) ;
          tableView.getTableHeader ().repaint () ;
          if ( header.getTable ().isEditing () )
          {
            header.getTable ().getCellEditor ().stopCellEditing () ;
          }
          if ( AscendingOrder )
          {
            renderer.setState ( new Integer ( SortTableHeaderRenderer.UP ) ,
                                col ) ;
            setAscendingOrder ( false ) ;
          }
          else
          {
            renderer.setState ( new Integer ( SortTableHeaderRenderer.DOWN ) ,
                                col ) ;
            setAscendingOrder ( true ) ;
          }
          model.getColumn ( col ).setHeaderRenderer ( renderer ) ;
          if ( rows.size () > 0 )
          {
            int idx = tableView.getSelectedRow () ;
            if ( idx == -1 )
            {
              idx = 0 ;
            }
            selindex = indexes[ idx ] ;
            sortByColumn ( sortCol ) ;
          }
        }

        //Debug.debugLog("CustomAbstract Table model :: mouse released");
//        int col1 = header.columnAtPoint ( e.getPoint () ) ;
        renderer.setPressedColumn ( -1 ) ;
        header.repaint () ;
      } // THIS IF  CLOSING CORRESPONSING TO --->>>if(hNosort==null ||...)
    }

    public void mouseReleased ( MouseEvent e )
    {
      //Debug.debugLog("CustomAbstract Table model :: mouse released");
      //int col = header.columnAtPoint(e.getPoint());
      //renderer.setPressedColumn(-1);
      //header.repaint();
    }

    public SortTableHeaderRenderer getRenderer ()
    {
      return renderer ;
    }
  }

  //ADDED BY PRANAV TO STORE COLUMNS NUMBERS ON WHICH WE DON'T WANT SORTING 6/27/2004 5:28PM
  public void setUnsortedColumn ( Hashtable hNosort )
  {
    this.hNosort = hNosort ;
  }
  //FINSIH

}

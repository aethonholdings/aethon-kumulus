package com.scannerapp.apps.component ;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
import java.util.EventObject;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;

public class StringEditor
    extends JTextField
    implements javax.swing.table.TableCellEditor ,
    java.awt.event.ActionListener
{
  private String oldValue = "" ;
  private String newValue = "" ;
  protected ChangeEvent changeEvent = null ;
  protected EventListenerList listenerList = new EventListenerList () ;
  private JTable table = null ;
  private int row = 0 , col = 0 ;
  public void actionPerformed ( java.awt.event.ActionEvent anEvent )
  {

    //Debug.debugLog( "StringEditor: actionPerformed" );
    fireEditingStopped () ;
  }

  public java.awt.Component getTableCellEditorComponent ( javax.swing.JTable
      table , java.lang.Object value , boolean isSelected , int row , int col )
  {
    this.table = table ;
    this.row = row ;
    this.col = col ;

    //Debug.debugLog( "StringEditor: getTableCellEditorComponent: value = " + value );
    if ( value != null )
    {
      setText ( value.toString ().trim () ) ;
      oldValue = String.valueOf ( value ) ;
    }
    else
    {
//      setText("0");
//      oldValue = "0";
      setText ( "" ) ;
      oldValue = "" ;
    }
    this.selectAll () ;
    return this ;
  }

  public void addCellEditorListener ( CellEditorListener l )
  {
    listenerList.add ( CellEditorListener.class , l ) ;
  }

  public void cancelCellEditing ()
  {
    newValue = oldValue ;
  }

  public Object getCellEditorValue ()
  {
    this.selectAll () ;
    newValue = getText ().trim () ;

    //Debug.debugLog( "StringEditor: getCellEditorValue: returns |" + newValue + "|" );
    return newValue ;
  }

  public boolean isCellEditable ( EventObject eventobj )
  {
    this.selectAll () ;
    return true ;
  }

  public boolean shouldSelectCell ( EventObject evtobj )
  {
    return true ;
  }

  public boolean stopCellEditing ()
  {

    //Debug.debugLog( "StringEditor: stopCellEditing: value = |" + getText() + "|" );
    if ( getText ().trim ().length () == 0 )
    {
      setText ( "0" ) ;
      setText ( "" ) ;
    }
    Object[] listeners = listenerList.getListenerList () ;

    // Process the listeners last to first, notifying

    // those that are interested in this event
    for ( int i = listeners.length - 2 ; i >= 0 ; i -= 2 )
    {
      if ( listeners[ i ] == CellEditorListener.class )
      {

        // Lazily create the event:
        if ( changeEvent == null )
        {
          changeEvent = new ChangeEvent ( this ) ;
        }
        ( ( CellEditorListener ) listeners[ i +
            1 ] ).editingStopped ( changeEvent ) ;
      }
    }
    return true ;
  }

  public void removeCellEditorListener ( CellEditorListener l )
  {
    listenerList.remove ( CellEditorListener.class , l ) ;
  }

  public void fireEditingStopped ()
  {

    //Debug.debugLog( "StringEditor: fireEditingStopped: value = |" + getText() + "|" );

    // Guaranteed to return a non-null array
    Object[] listeners = listenerList.getListenerList () ;

    // Process the listeners last to first, notifying

    // those that are interested in this event
    for ( int i = listeners.length - 2 ; i >= 0 ; i -= 2 )
    {
      if ( listeners[ i ] == CellEditorListener.class )
      {

        // Lazily create the event:
        if ( changeEvent == null )
        {
          changeEvent = new ChangeEvent ( this ) ;
        }
        ( ( CellEditorListener ) listeners[ i +
            1 ] ).editingStopped ( changeEvent ) ;
      }
    }
  }

  public void fireEditingCanceled ()
  {

    // Guaranteed to return a non-null array
    Object[] listeners = listenerList.getListenerList () ;

    // Process the listeners last to first, notifying

    // those that are interested in this event
    for ( int i = listeners.length - 2 ; i >= 0 ; i -= 2 )
    {
      if ( listeners[ i ] == CellEditorListener.class )
      {

        // Lazily create the event:
        if ( changeEvent == null )
        {
          changeEvent = new ChangeEvent ( this ) ;
        }
        ( ( CellEditorListener ) listeners[ i +
            1 ] ).editingCanceled ( changeEvent ) ;
      }
    }
  }

  /**
   * Constructor of StringEditor class.
   * This registers the Enter key for editing stop.
   */
  public StringEditor ()
  {
    this ( 0 , 9 , true ) ;
  }

  public StringEditor ( int min , int max , boolean comp )
  {
//    super(min, max, 3, comp, 4);
    this.setHorizontalAlignment ( javax.swing.JTextField.RIGHT ) ;
    super.setBackground ( new java.awt.Color ( 255 , 255 , 225 ) ) ;
    setOpaque ( true ) ;
  }
}
package com.scannerapp.apps.component ;

import java.util.EventObject;

import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;

import org.apache.log4j.Logger;

import com.scannerapp.apps.framework.view.GeneralTextFieldPhyInv;

public class PhyInvCellEditor
    extends GeneralTextFieldPhyInv
    implements javax.swing.table.TableCellEditor ,
    java.awt.event.ActionListener
{
  private static Logger log = Logger.getLogger(PhyInvCellEditor.class);//For Log4j
  private String oldValue = "" ;
  private String newValue = "" ;
  protected ChangeEvent changeEvent = null ;
  protected EventListenerList listenerList = new EventListenerList () ;
  private JTable table = null ;
  private int row = 0 , col = 0 ;
  public void actionPerformed ( java.awt.event.ActionEvent anEvent )
  {
   log.debug("IntegerEditor: actionPerformed" ) ;
    fireEditingStopped () ;
  }

  public java.awt.Component getTableCellEditorComponent ( javax.swing.JTable
      table , java.lang.Object value , boolean isSelected , int row , int col )
  {
    this.table = table ;
    this.row = row ;
    this.col = col ;
    log.debug ( "IntegerEditor: getTableCellEditorComponent: value = " +
                     value ) ;
    if ( value != null )
    {
      setText ( value.toString ().trim () ) ;
      oldValue = String.valueOf ( value ) ;
    }
    else
    {
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
    log.debug( "IntegerEditor: getCellEditorValue: returns |" + newValue +"|" ) ;
    return new String ( newValue ) ;
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
	  log.debug ( "IntegerEditor: stopCellEditing: value = |" + getText () +"|" ) ;
    if ( getText ().trim ().length () == 0 )
    {
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
	  log.debug( "IntegerEditor: fireEditingStopped: value = |" + getText () +"|" ) ;

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
   * Constructor of IntegerEditor class.
   * This registers the Enter key for editing stop.
   */
  public PhyInvCellEditor ()
  {
    this ( 0 , 9 , true ) ;
  }

  public PhyInvCellEditor ( int min , int max , boolean comp )
  {
    super ( min , max , 3 , comp , 4 ) ;
    this.setHorizontalAlignment ( javax.swing.JTextField.RIGHT ) ;
    setOpaque ( true ) ;
  }
}

package com.scannerapp.apps.component ;

import java.util.EventObject;

import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;

import org.apache.log4j.Logger;

import com.scannerapp.apps.framework.view.ErrorMessage;
import com.scannerapp.common.ErrorConstants;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Systems Plus Pvt Limited</p>
 * @author Kanhaiya Samariya
 * @version 1.0
 */
public class DoubleEditor
    extends EdittedTextField
    implements javax.swing.table.TableCellEditor ,
    java.awt.event.ActionListener
{
  private static Logger log = Logger.getLogger(DoubleEditor.class);//For Log4j
  private String oldValue = "" ;
  private String newValue = "" ;
  protected ChangeEvent changeEvent = null ;
  protected EventListenerList listenerList = new EventListenerList () ;
  private JTable table = null ;
  private int row = 0 , col = 0 ;
  public void actionPerformed ( java.awt.event.ActionEvent anEvent )
  {
    log.debug("DoubleEditor: actionPerformed" ) ;
    fireEditingStopped () ;
  }

  public java.awt.Component getTableCellEditorComponent ( javax.swing.JTable
      table , java.lang.Object value , boolean isSelected , int row , int col )
  {
    this.table = table ;
    this.row = row ;
    this.col = col ;
    log.debug( "DoubleEditor: getTableCellEditorComponent: value = " +
                     value ) ;
    if ( value != null )
    {
      setText ( value.toString ().trim () ) ;
      oldValue = String.valueOf ( value ) ;
    }
    else
    {
      setText ( "0" ) ;
      oldValue = "0" ;
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
    //Added by jiten for handle error while user enter only "-"
    if(getText ().trim ().indexOf("-")>=0){
    	ErrorMessage.displayMessage('W', ErrorConstants.ENTER_NUMERIC_VALUE);//changed msg PRICE_NOT_MINUS  for Unit Test Issue3511 		
    	newValue = oldValue ;    	    	
    }
    else
    {
    //Finish by jiten  	
    newValue = getText ().trim () ;
    }//Added by jiten 
    
    log.debug( "DoubleEditor: getCellEditorValue: returns |" + newValue +
                     "|" ) ;
    return new Double ( newValue.replace(',', '.') ) ;
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
	  log.debug( "DoubleEditor: stopCellEditing: value = |" + getText () +
                     "|" ) ;
    if ( getText ().trim ().length () == 0 )
    {
      setText ( "0" ) ;
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
	  log.debug( "DoubleEditor: fireEditingStopped: value = |" + getText () +"|" ) ;

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
   * Constructor of DoubleEditor class.
   * This registers the Enter key for editing stop.
   */
  public DoubleEditor ()
  {
    this ( 6 ) ;
  }

  public DoubleEditor ( int max )
  {
    super ( max ) ;
    this.setHorizontalAlignment ( javax.swing.JTextField.RIGHT ) ;
    this.setAllowsOnlyNumbers ( true ) ;
    this.setAllowsNumericSeparators ( true ) ;
    setOpaque ( true ) ;
  }

  /*
   public DoubleEditor( int min, int max, boolean comp )
   {
   super( min, max, 3, comp, 4 );
   this.setHorizontalAlignment( javax.swing.JTextField.RIGHT );
   setOpaque( true );
   }
   */
}

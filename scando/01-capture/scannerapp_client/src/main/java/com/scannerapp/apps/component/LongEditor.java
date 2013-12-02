package com.scannerapp.apps.component;

import java.util.EventObject;

import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;

import com.scannerapp.apps.framework.view.GeneralTextField;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Manish Vithlani
 * @version 1.0
 */

public class LongEditor 
extends GeneralTextField
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

	       fireEditingStopped () ;
	  }

	  public java.awt.Component getTableCellEditorComponent ( javax.swing.JTable
	      table , java.lang.Object value , boolean isSelected , int row , int col )
	  {
	    this.table = table ;
	    this.row = row ;
	    this.col = col ;

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
	    newValue = getText ().trim () ;

	    return new Long ( newValue ) ;
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

	    if ( getText ().trim ().length () == 0 )
	    {
	      setText ( "0" ) ;
	    }
	    Object[] listeners = listenerList.getListenerList () ;

	    
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
	        ( ( CellEditorListener ) listeners[ i + 1 ] ).editingCanceled ( changeEvent ) ;
	      }
	    }
	  }

	  /**
	   * Constructor of  LongEditor class.
	   * This registers the Enter key for editing stop.
	   */
	  public LongEditor ()
	  {
	    this ( 0 , 19 , true ) ;
	    
	  }

	  public LongEditor ( int min , int max , boolean comp )
	  {
	    super ( min , max , 3 , comp , 4 ) ;
	    this.setHorizontalAlignment ( javax.swing.JTextField.RIGHT ) ;
	    setOpaque ( true ) ;
	  }
}

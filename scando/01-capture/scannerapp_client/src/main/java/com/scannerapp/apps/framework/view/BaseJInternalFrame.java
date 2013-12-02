package com.scannerapp.apps.framework.view ;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import org.apache.log4j.Logger;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Kanhaiya Samariya
 * @version 1.0
 */
public class BaseJInternalFrame
    extends JInternalFrame
{
  private static Logger log = Logger.getLogger(BaseJInternalFrame.class);//For Log4j 
  private BaseJInternalFrameController controller ;
  private VetoableChangeListener _changeListener ;
  private InternalFrameListener _internalFrameListener ;
  private String classNameWithoutPackage = "" ;
  public BaseJInternalFrame ()
  {
    this ( "" , true , true , true , true ) ;
    try
    {
      jbInit () ;
    }
    catch ( Exception e )
    {
      e.printStackTrace () ;
    }
  }

  public BaseJInternalFrame ( String aTitle )
  {
    this ( aTitle , true , true , true , true ) ;
  }

  public BaseJInternalFrame ( String aTitle , boolean resizable ,
                              boolean closable , boolean maximizable ,
                              boolean iconifiable )
  {
    super ( aTitle , resizable , closable , maximizable , iconifiable ) ;
    setController ( new BaseJInternalFrameController ( this ) ) ;
    setResizable ( resizable ) ;
    setClosable ( closable ) ;
    setMaximizable ( maximizable ) ;
    setIconifiable ( iconifiable ) ;
    setDefaultCloseOperation ( JInternalFrame.DISPOSE_ON_CLOSE ) ;
    try
    {
      addListeners () ;
    }
    catch ( Exception e )
    {
      e.printStackTrace () ;
    }
  }

  public BaseJInternalFrameController getController ()
  {
    return controller ;
  }

  public void setController ( BaseJInternalFrameController controller )
  {
    this.controller = controller ;
  }

  protected void addListeners ()
  {
    _internalFrameListener = new InternalFrameListener ()
    {
      public void internalFrameOpened ( InternalFrameEvent e )
      {
        if ( getController () != null )
        {
          getController ().windowOpened ( e.getSource () ) ;
        }
      }

      public void internalFrameClosed ( InternalFrameEvent e )
      {
        if ( getController () != null )
        {
          getController ().windowClosed ( e.getSource () ) ;
        }
      }

      public void internalFrameClosing ( InternalFrameEvent e )
      {
        if ( getController () != null )
        {
          getController ().windowClosing ( e.getSource () ) ;
        }
      }

      public void internalFrameActivated ( InternalFrameEvent e )
      {
        if ( getController () != null )
        {
          getController ().windowActivated ( e.getSource () ) ;
        }
      }

      public void internalFrameDeactivated ( InternalFrameEvent e )
      {
        if ( getController () != null )
        {
          getController ().windowDeactivated ( e.getSource () ) ;
        }
      }

      public void internalFrameIconified ( InternalFrameEvent e )
      {
        if ( getController () != null )
        {
          getController ().windowIconified ( e.getSource () ) ;
        }
      }

      public void internalFrameDeiconified ( InternalFrameEvent e )
      {
        if ( getController () != null )
        {
          getController ().windowDeiconified ( e.getSource () ) ;
        }
      }
    } ;
    addInternalFrameListener ( _internalFrameListener ) ;
    _changeListener = new VetoableChangeListener ()
    {
      public void vetoableChange ( PropertyChangeEvent e ) throws
          PropertyVetoException
      {
        String name = e.getPropertyName () ;
        if ( name.equals ( JInternalFrame.IS_CLOSED_PROPERTY ) )
        {
//          Component internalFrame = ( Component ) e.getSource () ;
          Boolean oldValue = ( Boolean ) e.getOldValue () ;
          Boolean newValue = ( Boolean ) e.getNewValue () ;
          if ( oldValue == Boolean.FALSE && newValue == Boolean.TRUE )
          {
            boolean close = getController ().confirmWindowClose () ;
            if ( !close )
            {
              throw new PropertyVetoException ( "close cancelled" , e ) ;
            }
          }
        }
      }
    } ;
    addVetoableChangeListener ( _changeListener ) ;
  }

  protected void removeListeners ()
  {
    removeInternalFrameListener ( _internalFrameListener ) ;
    removeVetoableChangeListener ( _changeListener ) ;
  }

  public void dispose ()
  {
    removeListeners () ;
    _internalFrameListener = null ;
    _changeListener = null ;
    setController ( null ) ;
    removeAll () ;
    removeNotify () ;
    setController ( null ) ;
    super.dispose () ;
  }

  protected void finalize () throws Throwable
  {
    dispose () ;
    super.finalize () ;
  }

  protected void debug ( String aMsg )
  {
    log.debug( getClassNameWithoutPackage () + " :: " +
                                    aMsg ) ;
  }

  protected void errorLog ( String message , Throwable th )
  {
	  log.debug( getClassNameWithoutPackage () + " :: " +
                                    message , th ) ;
  }

  protected String getClassNameWithoutPackage ()
  {
    String fullName = getClass ().getName () ;
    int lastIndex = fullName.lastIndexOf ( '.' ) ;
    if ( lastIndex > 0 )
    {
      classNameWithoutPackage = fullName.substring ( lastIndex + 1 ) ;
    }
    else
    {
      classNameWithoutPackage = fullName ;
    }
    return classNameWithoutPackage ;
  }

  private void jbInit () throws Exception
  {

  }
}

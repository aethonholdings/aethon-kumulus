/*
 com-fritolay-g2m-im-GeneralTextField.java
 NOTE: This file is a generated file.
 Do not modify it by hand!
 */
package com.scannerapp.apps.framework.view ;

// add your custom import statements here
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import org.apache.log4j.Logger;
public class GeneralTextField
    extends javax.swing.JTextField
    implements java.io.Serializable , java.awt.event.FocusListener ,
    java.awt.event.KeyListener
{

  /**
   * Max
   */
  private static Logger log = Logger.getLogger(GeneralTextField.class);//For Log4j	
  private int iMax = 0 ;
  public int getMax ()
  {
    return iMax ;
  }

  public void setMax ( int newValue )
  {
    iMax = newValue ;
    String text = getText () ;
    setTextCaseDocument ( iTextCase , iTextType ) ;
    setText ( text ) ;
  }

  /**
   * Min
   */
  private int iMin = 0 ;
  public int getMin ()
  {
    return iMin ;
  }

  public void setMin ( int newValue )
  {
    iMin = newValue ;
    String text = getText () ;
    setTextCaseDocument ( iTextCase , iTextType ) ;
    setText ( text ) ;
  }

  /**
   * NotNull
   */
  private boolean iNotNull = true ;
  public boolean isNotNull ()
  {
    return iNotNull ;
  }

  public void setNotNull ( boolean newValue )
  {
    iNotNull = newValue ;
    setFieldColor () ;
  }

  /**
   * TextCase
   */
  private int iTextCase ;
  public int getTextCase ()
  {
    return iTextCase ;
  }

  public void setTextCase ( int newValue )
  {
    iTextCase = newValue ;
    String text = getText () ;
    setTextCaseDocument ( iTextCase , iTextType ) ;
    setText ( text ) ;
  }

  /**
   * TextType
   */
  private int iTextType ;
  public int getTextType ()
  {
    return iTextType ;
  }

  public void setTextType ( int newValue )
  {
    iTextType = newValue ;
    String text = getText () ;
    setTextCaseDocument ( iTextCase , iTextType ) ;
    setText ( text ) ;
  }

  /**
   * setFieldColor Method
   */
  private void setFieldColor ()
  {
    if ( !isEditable () )
    {
      super.setBackground ( new java.awt.Color ( 230 , 230 , 230 ) ) ;
      return ;
    }
    if ( isNotNull () )
    {
      super.setBackground ( new java.awt.Color ( 255 , 255 , 225 ) ) ;
      return ;
    }
    super.setBackground ( javax.swing.UIManager.getColor ( "text" ) ) ;
  }

  /**
   * setTextCaseDocument Method
   */
  public void setTextCaseDocument ( int aTextCase , int aTextType )
  {

     setDocument ( new GeneralDocument ( iMin , iMax , aTextCase , aTextType ) ) ;

    /*
         switch( aTextCase )
         {
         case AS_TYPED:
         setDocument( new GeneralDocument( aMin, aMax, AS_TYPED ) );
         break;
         case ALL_UPPER:
         setDocument( new GeneralDocument( aMin, aMax, ALL_UPPER ) );
         break;
         case ALL_LOWER:
         setDocument( new GeneralDocument( aMin, aMax, ALL_LOWER ) );
         break;
         }
     */
  }

  /**
   * writeObject Method
   */
  private void writeObject ( java.io.ObjectOutputStream aStream ) throws java.
      io.IOException
  {
    aStream.defaultWriteObject () ;
    if ( ui != null )
    {
      ui.installUI ( this ) ;
    }
  }

  /**
   * readObject Method
   */
  private void readObject ( java.io.ObjectInputStream aStream ) throws java.io.
      IOException , java.lang.ClassNotFoundException ,
      java.io.NotActiveException
  {
    aStream.defaultReadObject () ;
  }

  /**
   * isDataValid Method
   */
  public boolean isDataValid ()
  {
    if ( isNotNull () )
    {
      if ( getText () == null || getText ().trim ().equals ( "" ) )
      {
        if ( iTextType == NUMBERS )
        {
          setText ( "0" ) ;
          return true ;
        }
        else
        {
          return false ;
        }
      }
      return true ;
    }
    else
    {
      if ( getText () == null || getText ().trim ().equals ( "" ) )
      {
        if ( iTextType == NUMBERS )
        {
          setText ( "0" ) ;
        }
      }
      return true ;
    }
  }

  /**
   * focusGained Method
   */
  public void focusGained ( FocusEvent anEvent )
  {
    selectAll () ;
    undoText = getText ().trim () ;

    //addToUndo = true;

    //FocusEvent fEvent = new FocusEvent( this, FocusEvent.FOCUS_GAINED );

    //_focusSupport.fireFocusGained( fEvent );
  }

  /**
   * focusLost Method
   */
  public void focusLost ( FocusEvent anEvent )
  {

    //select( 0, 0 );
    undoText = getText ().trim () ;

    //FocusEvent fEvent = new FocusEvent( this, FocusEvent.FOCUS_LOST );

    //_focusSupport.fireFocusLost( fEvent );
  }

  public GeneralTextField ()
  {
    this ( 0 , 0 , AS_TYPED , true , MIXED ) ;
  }

  public GeneralTextField ( boolean aNotNullFlag )
  {
    this ( 0 , 0 , AS_TYPED , aNotNullFlag , MIXED ) ;
  }

  public GeneralTextField ( int aTextCase )
  {
    this ( 0 , 0 , aTextCase , true , MIXED ) ;
  }

  public GeneralTextField ( int aMin , int aMax )
  {
    this ( aMin , aMax , AS_TYPED , true , MIXED ) ;
  }

  public GeneralTextField ( int aMin , int aMax , int aTextCase ,
                            boolean aNotNullFlag )
  {
    this ( aMin , aMax , aTextCase , aNotNullFlag , MIXED ) ;
  }

  public GeneralTextField ( int aMin , int aMax , int aTextCase ,
                            boolean aNotNullFlag , int aTextType )
  {
    super () ;
    iMin = aMin ;
    iMax = aMax ;
    iTextCase = aTextCase ;
    iTextType = aTextType ;
    setNotNull ( aNotNullFlag ) ;
    setTextCaseDocument ( aTextCase , aTextType ) ;

    //if( _focusSupport == null )

    //_focusSupport = new FocusSupport( this );
    addFocusListener ( this ) ;
    addKeyListener ( this ) ;
    registerKeyboardAction ( undoAction ,
                             KeyStroke.getKeyStroke ( KeyEvent.VK_ESCAPE , 0 , false ) ,
                             JComponent.WHEN_FOCUSED ) ;
    setCaret ( new ThickCaret () ) ;
  }

  public void keyPressed ( KeyEvent anEvent )
  {
    if ( anEvent.getKeyCode () == KeyEvent.VK_ALT )
    {
      altIsDown = true ;
    }
  }

  public void keyReleased ( KeyEvent anEvent )
  {
    if ( anEvent.getKeyCode () == KeyEvent.VK_ALT )
    {
      altIsDown = false ;
    }
  }

  public void keyTyped ( KeyEvent anEvent )
  {

  }

  protected void processInputMethodEvent ( java.awt.event.InputMethodEvent e )
  {
    if ( !altIsDown )
    {
      super.processInputMethodEvent ( e ) ;
    }
    altIsDown = false ;
  }

  // add your data members here
  public static final int ALL_UPPER = 1 ;
  public static final int ALL_LOWER = 2 ;
  public static final int AS_TYPED = 3 ;
  public static final int NUMBERS = 4 ;
  public static final int CHARACTERS = 5 ;
  public static final int MIXED = 6 ;
  private boolean altIsDown = false ;
  private UndoAction undoAction = new UndoAction () ;
  class UndoAction
      extends AbstractAction
  {
    public UndoAction ()
    {
      super ( "Undo" ) ;
    }

    public void actionPerformed ( ActionEvent e )
    {
      try
      {
        GeneralTextField.this.setText ( undoText ) ;
      }
      catch ( Exception ex )
      {
        log.debug( "Unable to undo: " + ex ) ;
        ex.printStackTrace () ;
      }
    }
  }

  String undoText ;
}

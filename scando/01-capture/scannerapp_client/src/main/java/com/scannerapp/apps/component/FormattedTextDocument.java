// Decompiled by DJ v2.8.8.54 Copyright 2000 Atanas Neshkov  Date: 12/27/2001 9:39:15 AM

// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!

// Decompiler options: packimports(3)

// Source File Name:   FormattedTextDocument.java
package com.scannerapp.apps.component ;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

// Referenced classes of package com.fritolay.oode.jfc.textField:

//            FormattedTextField
public class FormattedTextDocument
    extends PlainDocument
{
  private FormattedTextDocument ()
  {
    _first = true ;
  }

  public FormattedTextDocument ( FormattedTextField formattedtextfield ,
                                 String s , Vector vector )
  {
    this ( formattedtextfield , s , vector , false ) ;
  }

  public FormattedTextDocument ( FormattedTextField formattedtextfield ,
                                 String s )
  {
    this ( formattedtextfield , s , null , false ) ;
  }

  public FormattedTextDocument ( FormattedTextField formattedtextfield ,
                                 String s , boolean flag )
  {
    this ( formattedtextfield , s , null , flag ) ;
  }

  public FormattedTextDocument ( FormattedTextField formattedtextfield ,
                                 String s , Vector vector , boolean flag )
  {
    _first = true ;
    _field = formattedtextfield ;
    _template = s ;
    if ( vector == null )
    {
      _anchor = getAnchor ( s ) ;
    }
    else
    {
      _anchor = vector ;
    }
    _numeric = flag ;
    _field.addMouseListener ( new MouseListener ()
    {
      public void mouseClicked ( MouseEvent e )
      {
        setPosition () ;
      }

      public void mouseEntered ( MouseEvent event )
      {

      }

      public void mouseExited ( MouseEvent event )
      {

      }

      public void mousePressed ( MouseEvent event )
      {
        setPosition () ;
      }

      public void mouseReleased ( MouseEvent event )
      {

      }
    } ) ;
  }

  public void insertString ( int i , String s , AttributeSet attributeset ) throws
      BadLocationException
  {
    _attribute = attributeset ;
    if ( _first )
    {
      super.insertString ( i , s , attributeset ) ;
      _field.setCaretPosition ( moveCaret ( 0 ) ) ;
      _first = false ;
      return ;
    }
    else
    {
      insertText ( getText ( 0 , getLength () ) , getInput ( s ) , i ,
                   attributeset ) ;
      return ;
    }
  }

  public void remove ( int i , int j ) throws BadLocationException
  {
    int k = _field.getCaretPosition () ;
    if ( k >= 0 )
    {
      super.remove ( i , j ) ;
      if ( k == _field.getCaretPosition () && !_first )
      {
        insertText ( _template , getInput () , 0 , _attribute ) ;
        _field.setCaretPosition ( moveCaret ( k ) ) ;
        return ;
      }
      super.insertString ( i , _template.substring ( i , i + j ) , _attribute ) ;
      _field.setCaretPosition ( _field.getCaretPosition () - j ) ;
    }
  }

  public String getInput ()
  {
    String s = new String () ;
    try
    {
      s = getInput ( getText ( 0 , getLength () ) ) ;
    }
    catch ( BadLocationException _ex )
    {

    }
    return s ;
  }

  public String getInput ( String s )
  {
    String s1 = new String () ;
    if ( _numeric )
    {
      for ( int i = 0 ; i < s.length () ; i++ )
      {
        if ( Character.isDigit ( s.charAt ( i ) ) )
        {
          s1 = s1 + s.substring ( i , i + 1 ) ;
        }
      }
    }
    else
    {
      for ( int j = 0 ; j < s.length () ; j++ )
      {
        if ( Character.isLetterOrDigit ( s.charAt ( j ) ) )
        {
          s1 = s1 + s.substring ( j , j + 1 ) ;
        }
      }
    }
    return s1 ;
  }

  public void insertText ( String s , String s1 , int i ,
                           AttributeSet attributeset ) throws
      BadLocationException
  {
    String s2 = s ;
    try
    {
      for ( int j = 0 ; j < s1.length () ; j++ )
      {
        Character character = new Character ( s1.charAt ( j ) ) ;
        while ( isAnchor ( i ) )
        {
          if ( isAnchor ( i ) )
          {
            i++ ;
          }
        }
        s2 = s2.substring ( 0 , i ) + character.toString () + s.substring ( ++i ) ;
      }
      super.remove ( 0 , getLength () ) ;
      super.insertString ( 0 , s2 , attributeset ) ;
      _field.setCaretPosition ( moveCaret ( i ) ) ;
      return ;
    }
    catch ( StringIndexOutOfBoundsException _ex )
    {
      return ;
    }
  }

  protected boolean isAnchor ( int i )
  {
    for ( int j = 0 ; j < _anchor.size () ; j++ )
    {
      if ( Integer.parseInt ( ( String ) _anchor.elementAt ( j ) ) == i )
      {
        return true ;
      }
    }
    return false ;
  }

  protected Vector getAnchor ( String s )
  {
    Vector vector = new Vector () ;
    for ( int i = 0 ; i < s.length () ; i++ )
    {
      if ( !Character.isSpaceChar ( s.charAt ( i ) ) )
      {
        vector.addElement ( String.valueOf ( i ) ) ;
      }
    }
    vector.trimToSize () ;
    return vector ;
  }

  public AttributeSet getAttribute ()
  {
    return _attribute ;
  }

  protected int moveCaret ( int i )
  {
    while ( isAnchor ( i ) )
    {
      if ( isAnchor ( i ) )
      {
        i++ ;
      }
    }
    return i ;
  }

  protected String _template ;
  protected Vector _anchor ;
  protected AttributeSet _attribute ;
  protected FormattedTextField _field ;
  protected boolean _numeric ;
  private transient boolean _first ;
  public void setPosition ()
  {
    _field.setCaretPosition ( 0 ) ;
  }
}

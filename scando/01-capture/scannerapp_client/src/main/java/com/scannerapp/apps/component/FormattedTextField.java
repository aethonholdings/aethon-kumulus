// Decompiled by DJ v2.8.8.54 Copyright 2000 Atanas Neshkov  Date: 12/27/2001 9:41:46 AM

// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!

// Decompiler options: packimports(3)

// Source File Name:   FormattedTextField.java
package com.scannerapp.apps.component ;

import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

// Referenced classes of package com.fritolay.oode.jfc.textField:

//            FormattedTextDocument
public class FormattedTextField
    extends JTextField
{
  public FormattedTextField ()
  {

  }

  public FormattedTextField ( String s )
  {
    super ( s.length () ) ;
    _formattedTextDocument = new FormattedTextDocument ( this , s ) ;
    _template = s ;
    setDocument ( _formattedTextDocument ) ;
    setValue ( s ) ;
  }

  public FormattedTextField ( String s , boolean flag )
  {
    super ( s.length () ) ;
    _formattedTextDocument = new FormattedTextDocument ( this , s , flag ) ;
    _template = s ;
    setDocument ( _formattedTextDocument ) ;
    setValue ( s ) ;
  }

  public FormattedTextField ( String s , Vector vector )
  {
    super ( s.length () ) ;
    _formattedTextDocument = new FormattedTextDocument ( this , s , vector ) ;
    _template = s ;
    setDocument ( _formattedTextDocument ) ;
    setValue ( s ) ;
  }

  public FormattedTextField ( String s , Vector vector , boolean flag )
  {
    super ( s.length () ) ;
    _formattedTextDocument = new FormattedTextDocument ( this , s , vector ,
        flag ) ;
    _template = s ;
    setDocument ( _formattedTextDocument ) ;
    setValue ( s ) ;
  }

  public String getValue ()
  {
    return getText () ;
  }

  public void setValue ( String s )
  {
    setText ( s ) ;
  }

  public String getData ()
  {
    return _formattedTextDocument.getInput () ;
  }

  public void setData ( String s ) throws BadLocationException
  {
    _formattedTextDocument.insertText ( _template , s , 0 ,
                                        _formattedTextDocument.getAttribute () ) ;
  }

  private FormattedTextDocument _formattedTextDocument ;
  protected String _template ;
}

/*
 com-fritolay-g2m-im-GeneralDocument.java
 NOTE: This file is a generated file.
 Do not modify it by hand!
 */
package com.scannerapp.apps.framework.view ;

// add your custom import statements here
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class GeneralDocumentPhyInv
    extends javax.swing.text.PlainDocument
    implements java.io.Serializable
{

  /**
   * insertString Method
   */
  public void insertString ( int anOffset , String aString ,
                             AttributeSet anAttributeSet ) throws
      BadLocationException
  {
    String currentText = getText ( 0 , getLength () ) ;
    String beforeOffset = currentText.substring ( 0 , anOffset ) ;
    String afterOffset = currentText.substring ( anOffset , currentText.length () ) ;
    String proposedResult = beforeOffset + aString + afterOffset ;
    if ( proposedResult == null )
    {
      return ;
    }
    if ( max != 0 && proposedResult.length () > max )
    {
      return ;
    }
    else
    {
      switch ( textCase )
      {
        case GeneralTextFieldPhyInv.AS_TYPED:
          if ( isValidTextType ( aString ) )
          {
            super.insertString ( anOffset , aString , anAttributeSet ) ;
          }
          break ;

        case GeneralTextFieldPhyInv.ALL_UPPER:
          if ( isValidTextType ( aString ) )
          {
            super.insertString ( anOffset , aString.toUpperCase () ,
                                 anAttributeSet ) ;
          }
          break ;

        case GeneralTextFieldPhyInv.ALL_LOWER:
          if ( isValidTextType ( aString ) )
          {
            super.insertString ( anOffset , aString.toLowerCase () ,
                                 anAttributeSet ) ;
          }
          break ;
        default:
      }
    }
  }

  /**
   * remove Method
   */
  public void remove ( int offs , int len ) throws BadLocationException
  {
    String currentText = getText ( 0 , getLength () ) ;
    String beforeOffset = currentText.substring ( 0 , offs ) ;
    String afterOffset = currentText.substring ( len + offs ,
                                                 currentText.length () ) ;
    String proposedResult = beforeOffset + afterOffset ;
    if ( proposedResult == null )
    {
      return ;
    }
    if ( proposedResult.length () < min && min != 0 )
    {
      return ;
    }
    else
    {
      super.remove ( offs , len ) ;
    }
  }

  /**
   * isValidTextType Method
   */
  public boolean isValidTextType ( String aString )
  {
    if ( aString.equals ( " " ) )
    {
      return false ;
    }
    char[] cArray = aString.trim ().toCharArray () ;
    switch ( textType )
    {
      case GeneralTextFieldPhyInv.NUMBERS:
        for ( int i = 0 ; i < cArray.length ; i++ )
        {
          if ( strNUMBERS.indexOf ( cArray[ i ] ) == -1 )
          {

            //not a number so invalid string
            return false ;
          }
        }
        return true ;

      case GeneralTextFieldPhyInv.CHARACTERS:
        for ( int i = 0 ; i < cArray.length ; i++ )
        {
          if ( strNUMBERS.indexOf ( cArray[ i ] ) != -1 )
          {

            //not a character so invalid string
            return false ;
          }
        }
        return true ;

      case GeneralTextField.MIXED:
        return true ;
    }
    return true ;
  }

  public GeneralDocumentPhyInv ( int aMin , int aMax , int aTextCase ,
                                 int aTextType )
  {
    min = aMin ;
    max = aMax ;
    textCase = aTextCase ;
    textType = aTextType ;
  }

  // add your data members here
  int min , max , textCase , textType ;

  //char[] upper;
  public static final String strNUMBERS = "0123456789" ;
}

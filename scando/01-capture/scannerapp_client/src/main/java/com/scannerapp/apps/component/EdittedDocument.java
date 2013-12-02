/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    EdittedDocument
 * Author:  Anna Swinney
     * Purpose:  The purpose of the EdittedDocument class is to provide an extension
 * of Plaindocument that can exhibit additional behaviors such as 1) forcing all input
 * to upper case 2) forcing all input to lower case 3) limiting input characters to
 * numbers only 4) limiting input characters to alphabetic characters only 5) limiting
 * input to a particular number of characters, which defaults to the length of the
 * field specifiec in "columns".
 *
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 07/16/98 aswinn   Initial release
 * 08/30/00 D.Kruse  Added Float validation
 *
 **/
package com.scannerapp.apps.component ;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.scannerapp.apps.framework.common.interfaces.Disposable;

/**
 * EdittedDocument class is an extension
 * of PlainDocument that can exhibit additional behaviors such as 1) forcing all input
 * to upper case 2) forcing all input to lower case 3) limiting input characters to
 * numbers only 4) limiting input characters to alphabetic characters only 5) limiting
 * input to a particular number of characters, which defaults to the length of the
 * field specifiec in "columns".
 * @version     1.00 - July 16, 1998
 * @author      Anna Swinney
 **/
public class EdittedDocument
    extends PlainDocument
    implements Disposable
{

  /**
   * @serial
   **/
  private boolean _forceToUpperCase = false ;

  /**
   * @serial
   **/
  private boolean _forceToLowerCase = false ;

  /**
   * @serial
   **/
  private boolean _allowOnlyNumbers = false ;

  /**
   * @serial
   **/
  private boolean _allowOnlyLetters = false ;

  /**
   * @serial
   **/
  private boolean _allowNumericSeparators = false ;

  /**
   * @serial
   **/
  private int _maximumLength ;

  /**
   * @serial
   **/
  private char[] _numericSeparators ;

  /**
   **/
  public EdittedDocument ()
  {
    super () ;
    _numericSeparators = new char[ 2 ] ;
    _numericSeparators[ 0 ] = ( new String ( "." ) ).charAt ( 0 ) ;
    _numericSeparators[ 1 ] = ( new String ( "-" ) ).charAt ( 0 ) ;

    //_numericSeparators[1] = (new String(";")).charAt(0);

    //_numericSeparators[2] = (new String(",")).charAt(0);
  }

  /**
       * Inserts some content into the document.  Overrides insertString in superclass.
   * @param <em>offset</em> the starting offset
   * @param <em>string</em> the string to insert
   * @param <em>attributeSet</em> the attributes for the inserted content
   * @exception BadLocationException  the given insert position is not a valid
   * position within the document
   **/
  public void insertString ( int offset , String string ,
                             AttributeSet attributeSet ) throws
      BadLocationException
  {
    if ( string == null || string.equals ( "" ) )
    {
      return ;
    }
    if ( _maximumLength > 0 && offset + 1 > _maximumLength )
    {
      Toolkit.getDefaultToolkit ().beep () ;
      return ;
    }
    if ( _maximumLength > 0 && getLength () + string.length () > _maximumLength )
    {
      Toolkit.getDefaultToolkit ().beep () ;
      return ;
    }
    char[] characters = string.toCharArray () ;
//    int stringLength = characters.length ;
    if ( _forceToUpperCase )
    {
      characters = forceToUpperCase ( string ) ;
    }
    if ( _forceToLowerCase )
    {
      characters = forceToLowerCase ( string ) ;
    }
    if ( _allowOnlyNumbers )
    {
      try
      {
        characters = enforceOnlyNumbers ( offset , string ) ;
      }
      catch ( FieldEditException e )
      {
        return ;
      }
    }
    String textInserting = new String ( characters ) ;
    super.insertString ( offset , textInserting , attributeSet ) ;
    if ( _allowOnlyNumbers )
    {
      if ( !isValidFloat ( getText ( 0 , getLength () ) ) )
      {
        remove ( offset , textInserting.length () ) ;
        Toolkit.getDefaultToolkit ().beep () ;
      }
    }
  }

  /**
   * Test each inserted character to determine whether or not it is a "letter".  Beeps
   * and does not accept non-letter characters.
   * @param <em>offset</em> the starting offset
   * @param <em>string</em> the string to insert
   * @exception FieldEditException  a character to be inserted was not a letter.
   **/
/* Code Clean-up : Dhwanil */
//  private char[] enforceOnlyLetters ( int offset , String string ) throws
//      FieldEditException
//  {
//    char[] work = string.toCharArray () ;
//    char[] letters = new char[ work.length ] ;
//    int j = 0 ;
//    for ( int i = 0 ; i < work.length ; i++ )
//    {
//      if ( Character.isLetter ( work[ i ] ) )
//      {
//        letters[ j ] = work[ i ] ;
//        j++ ;
//      }
//      else
//      {
//        Toolkit.getDefaultToolkit ().beep () ;
//        throw new FieldEditException () ;
//      }
//    }
//    return letters ;
//  }
/**/
  /**
   * Test each inserted character to determine whether or not it is a "number".
   * May optionally accept numeric separators as "numbers". Beeps
   * and does not accept non-numeric characters.
   * @param <em>offset</em> the starting offset
   * @param <em>string</em> the string to insert
   * @exception FieldEditException  a character to be inserted was not a number
   * or a valid numeric separator (if allowed).
   * @see setAllowsNumericSeparators
   **/
  private char[] enforceOnlyNumbers ( int offset , String string ) throws
      FieldEditException
  {
    char[] work = string.toCharArray () ;
    char[] numbers = new char[ work.length ] ;
    int j = 0 ;
    for ( int i = 0 ; i < work.length ; i++ )
    {
      if ( Character.isDigit ( work[ i ] ) || isValidNumericSeparator ( work[ i ] ) )
      {
        numbers[ j ] = work[ i ] ;
        j++ ;
      }
      else
      {
        Toolkit.getDefaultToolkit ().beep () ;
        throw new FieldEditException () ;
      }
    }
    return numbers ;
  }

  /**
   * Test an input character to determine whether or not it is a valid numeric
   * separator.
   * @param <em>character</em> the character to be tested
   * @return boolean indicating whether or not the character is a valid numeric
   * separator
   **/
  private boolean isValidNumericSeparator ( char character )
  {
    if ( !_allowNumericSeparators )
    {
      return false ;
    }
    for ( int i = 0 ; i < _numericSeparators.length ; i++ )
    {
      if ( character == _numericSeparators[ i ] )
      {
        return true ;
      }
    }
    return false ;
  }

  /**
   * Test an input string to determine whether or not it is a valid float
   * @param <em>string</em> the string to be tested
   * @return boolean indicating whether or not the character is a valid numeric
   * separator.
   **/
  private boolean isValidFloat ( String string )
  {
    if ( !_allowNumericSeparators )
    {
      return true ;
    }

    // No reason to check a really short string
    if ( string.length () < 2 )
    {
      return true ;
    }
    try
    {
      Float.valueOf ( string ) ;
      return true ;
    }
    catch ( Exception e )
    {
      return false ;
    }
  }

  /**
   * Translate string to upper case.
   * @param <em>string</em> the string to be converted
   * @return converted character array
   **/
  private char[] forceToUpperCase ( String string )
  {
    char[] upper = string.toCharArray () ;
    for ( int i = 0 ; i < upper.length ; i++ )
    {
      upper[ i ] = Character.toUpperCase ( upper[ i ] ) ;
    }
    return upper ;
  }

  /**
   * Translate string to lower case.
   * @param <em>string</em> the string to be converted
   * @return converted character array
   **/
  private char[] forceToLowerCase ( String string )
  {
    char[] lower = string.toCharArray () ;
    for ( int i = 0 ; i < lower.length ; i++ )
    {
      lower[ i ] = Character.toLowerCase ( lower[ i ] ) ;
    }
    return lower ;
  }

  /**
   * Sets whether or not characters keyed into this field will be converted to
   * upper case.
       * @param <em>forceToUpperCase</em> boolean indicating whether or not characters
   * keyed into this field will be converted to upper case.
   **/
  protected void setForceToUpperCase ( boolean forceToUpperCase )
  {
    _forceToUpperCase = forceToUpperCase ;
    _forceToLowerCase = !forceToUpperCase ;
  }

  /**
   * Sets whether or not characters keyed into this field will be converted to
   * lower case.
       * @param <em>forceToLowerCase</em> boolean indicating whether or not characters
   * keyed into this field will be converted to lower case.
   **/
  protected void setForceToLowerCase ( boolean forceToLowerCase )
  {
    _forceToLowerCase = forceToLowerCase ;
    _forceToUpperCase = !forceToLowerCase ;
  }

  /**
   * Sets the maximum number of characters that this EdittedTextField  will be
   * allowed to contain.
   * @param <em>numberOfCharacter</em> the maximum number of characters allowed
   **/
  protected void setMaximumLength ( int numberOfCharacters , boolean force )
  {
    if ( force || _maximumLength == 0 )
    {
      _maximumLength = numberOfCharacters ;
    }
  }

  /**
       * Returns the maximum number of characters that this EdittedTextField  will be
   * allowed to contain.
   * @return The maximum number of characters allowed
   **/
  protected int getMaximumLength ()
  {
    return _maximumLength ;
  }

  /**
   * Sets whether or not this field will accept characters other than numbers.
       * @param <em>allowsOnlyNumbers</em> boolean indicating whether or not this field
   * accepts non-numeric characters.
   **/
  protected void setAllowsOnlyNumbers ( boolean allowsOnlyNumbers )
  {
    _allowOnlyNumbers = allowsOnlyNumbers ;
    _allowOnlyLetters = !allowsOnlyNumbers ;
  }

  /**
   * Sets whether or not this field will accept characters other than alphabetic letters.
       * @param <em>allowsOnlyLetters</em> boolean indicating whether or not this field
   * accepts non-alphabetic characters.
   **/
  protected void setAllowsOnlyLetters ( boolean allowsOnlyLetters )
  {
    _allowOnlyLetters = allowsOnlyLetters ;
    _allowOnlyNumbers = !allowsOnlyLetters ;
  }

  /**
   * For fields which "allow only numbers", sets whether or not separators (comma and
   * decimal point) are considered valid.
       * @param <em>allowsSeparators</em> boolean indicating whether or not this field
   * accepts non-alphabetic characters.
   **/
  public void setAllowsNumericSeparators ( boolean allowsSeparators )
  {
    _allowNumericSeparators = allowsSeparators ;
  }

  /**
   *
   **/
  public void dispose ()
  {
    try
    {
      super.finalize () ;
    }
    catch ( Throwable e )
    {

      // What the heck are you supposed to do if you can't finalize something?
    }
  } // end
}

/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    EdittedTextArea
 * Author:  Mohammed Tarrous
     * Purpose:  The purpose of the EdittedTextArea class is to provide an extension
 * of JTextArea that can exhibit additional behaviors such as 1) forcing all input
 * to upper case 2) forcing all input to lower case 3) limiting input characters to
 * numbers only 4) limiting input characters to alphabetic characters only 5) limiting
 * input to a particular number of characters, which defaults to the length of the
 * field specifiec in "columns".
 *
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 11/01/99 mtarro   Initial release
 *
 **/
package com.scannerapp.apps.component ;

import javax.swing.JTextArea;
import javax.swing.text.Document;

import com.scannerapp.apps.framework.common.interfaces.Disposable;

/**
 * EdittedTextArea class is an extension
 * of JTextArea that can exhibit additional behaviors such as 1) forcing all input
 * to upper case 2) forcing all input to lower case 3) limiting input characters to
 * numbers only 4) limiting input characters to alphabetic characters only 5) limiting
 * input to a particular number of characters, which defaults to the length of the
 * field specifiec in "columns".
 * @version
 * @author      mohammed tarrous
 **/
public class EdittedTextArea
    extends JTextArea
    implements Disposable  //CutCopyPasteInterface
{

  /**
   * @serial
   **/
  private EdittedDocument _document ;

  /**
   * default constructor
   **/
  public EdittedTextArea ()
  {
    super () ;
  }

  /**
   * @param <em>rows</em> the number of rows
   *  @param <em>columns</em> the number of columns
   * @param <em>length</em> the maximum length of characters the text
   *  area can hold
   **/
  public EdittedTextArea ( int rows , int columns , int length )
  {
    super ( rows , columns ) ;
    setMaximumLength ( length ) ;
  }

  /**
   * @param <em>rows</em> the number of rows
   * @param <em>columns</em> the number of columns
   **/
  public EdittedTextArea ( int rows , int columns )
  {
    super ( rows , columns ) ;
  }

  /**
   * Creates an override to the default implementation of the model
   *
   * @return the "EdittedDocument" model implementation
   **/
  protected Document createDefaultModel ()
  {
    _document = new EdittedDocument () ;
    return _document ;
  }

  /**
   * Sets the maximum number of characters that this EdittedTextField  will be
   * allowed to contain.
   * @param <em>numberOfCharacter</em> the maximum number of characters allowed
   **/
  public void setMaximumLength ( int numberOfCharacters )
  {
    _document.setMaximumLength ( numberOfCharacters , true ) ;
  }

  /**
       * Returns the maximum number of characters that this EdittedTextField  will be
   * allowed to contain.
   * @return The maximum number of characters allowed
   **/
  public int getMaximumLength ()
  {
    return _document.getMaximumLength () ;
  }

  /**
   * Sets whether or not characters keyed into this field will be converted to
   * upper case.
       * @param <em>forceToUpperCase</em> boolean indicating whether or not characters
   * keyed into this field will be converted to upper case.
   **/
  public void setForceToUpperCase ( boolean forceToUpperCase )
  {
    _document.setForceToUpperCase ( forceToUpperCase ) ;
  }

  /**
   * Sets whether or not characters keyed into this field will be converted to
   * lower case.
       * @param <em>forceToLowerCase</em> boolean indicating whether or not characters
   * keyed into this field will be converted to lower case.
   **/
  public void setForceToLowerCase ( boolean forceToLowerCase )
  {
    _document.setForceToLowerCase ( forceToLowerCase ) ;
  }

  /**
   * Sets whether or not this field will accept characters other than numbers.
       * @param <em>allowsOnlyNumbers</em> boolean indicating whether or not this field
   * accepts non-numeric characters.
   **/
  public void setAllowsOnlyNumbers ( boolean allowsOnlyNumbers )
  {
    _document.setAllowsOnlyNumbers ( allowsOnlyNumbers ) ;
  }

  /**
   * Sets whether or not this field will accept characters other than alphabetic letters.
       * @param <em>allowsOnlyLetters</em> boolean indicating whether or not this field
   * accepts non-alphabetic characters.
   **/
  public void setAllowsOnlyLetters ( boolean allowsOnlyLetters )
  {
    _document.setAllowsOnlyLetters ( allowsOnlyLetters ) ;
  }

  /**
   * For fields which "allow only numbers", sets whether or not separators (comma and
   * decimal point) are considered valid.
       * @param <em>allowsSeparators</em> boolean indicating whether or not this field
   * accepts non-alphabetic characters.
   **/
  public void setAllowsNumericSeparators ( boolean allowsSeparators )
  {
    _document.setAllowsNumericSeparators ( allowsSeparators ) ;
  }

  /**
   * Returns textfield to it uninitialized (blank) state
   **/
  public void clear ()
  {
    this.setText ( "" ) ;
  }

  /**
   *
   **/
  public void dispose ()
  {
    if ( _document instanceof Disposable )
    {
      _document.dispose () ;
    }
    _document = null ;
    try
    {
      super.finalize () ;
    }
    catch ( Throwable e )
    {

      // What the heck are you supposed to do if you can't finalize something?
    }
  } // end
} // end class

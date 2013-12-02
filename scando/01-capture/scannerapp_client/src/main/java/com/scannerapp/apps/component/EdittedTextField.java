/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    EdittedTextField
 * Author:  Anna Swinney
     * Purpose:  The purpose of the EdittedTextField class is to provide an extension
 * of JTextField that can exhibit additional behaviors such as 1) forcing all input
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
 *
 **/
package com.scannerapp.apps.component ;

import javax.swing.JTextField;
import javax.swing.text.Document;

import com.scannerapp.apps.framework.common.interfaces.Disposable;

/**
 * EdittedTextField class is an extension
 * of JTextField that can exhibit additional behaviors such as 1) forcing all input
 * to upper case 2) forcing all input to lower case 3) limiting input characters to
 * numbers only 4) limiting input characters to alphabetic characters only 5) limiting
 * input to a particular number of characters, which defaults to the length of the
 * field specifiec in "columns".
 * @version     1.00 - July 16, 1998
 * @author      Anna Swinney
 **/
public class EdittedTextField
    extends JTextField
    implements  Disposable//CutCopyPasteInterface
{

  /**
   * @serial
   **/
  private EdittedDocument _document ;

  /**
   **/
  public EdittedTextField ()
  {
    super () ;
    this.setCaret ( new BigCaret () ) ;
  }

  /**
   * @param <em>columns</em> the number of columns
   **/
  public EdittedTextField ( int columns )
  {
    super ( columns ) ;
    setMaximumLength ( columns ) ;
    this.setCaret ( new BigCaret () ) ;
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
   * Sets the number of columns in this EdittedTextField.
   * @param <em>columns</em> the number of columns
   **/
  public void setColumns ( int columns )
  {
    super.setColumns ( columns ) ;
    _document.setMaximumLength ( columns , false ) ;
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

/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    StyledUpperCaseTextArea
 * Author:  mohammed tarrous
 * Purpose:  The purpose of the StyledUpperCaseTextArea class is to provide an extension
     * of StyledTextArea that automatically converts input characters to upper case.
 *
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 11/01/99 mtarro   Initial release
 *
 **/
package com.scannerapp.apps.component ;

/**
 * StyledUpperCaseTextArea class is an extension
 * of StyledTextArea that converts input characters to upper case characters.
 * @version
 * @author      mohammed tarrous
 **/
public class StyledUpperCaseTextArea
    extends StyledTextArea
{

  /**
   * default contructor
   **/
  public StyledUpperCaseTextArea ()
  {
    super () ;
    setForceToUpperCase ( true ) ;
  }

  /**
   * @param <em>rows</em> the number of rows
   * @param <em>columns</em> the number of columns
   */
  public StyledUpperCaseTextArea ( int rows , int columns )
  {
    super ( rows , columns ) ;
    setForceToUpperCase ( true ) ;
  }

  /**
   * @param <em>rows</em> the number of rows
   *  @param <em>columns</em> the number of columns
   * @param <em>length</em> the maximum length of characters the text
   *  area can hold
   **/
  public StyledUpperCaseTextArea ( int rows , int columns , int length )
  {
    super ( rows , columns , length ) ;
    setForceToUpperCase ( true ) ;
  }
}

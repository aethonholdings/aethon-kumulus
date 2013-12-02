/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    StyledUpperCaseTextField
 * Author:  Anna Swinney
 * Purpose:  The purpose of the StyledUpperCaseTextField class is to provide an extension
     * of StyledTextField that automatically converts input characters to upper case.
 *
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 07/16/98 aswinn   Initial release
 *
 **/
package com.scannerapp.apps.component ;

/**
 * StyledUpperCaseTextField class is an extension
 * of StyledTextField that converts input characters to upper case characters.
 * @version     1.00 - July 16, 1998
 * @author      Anna Swinney
 **/
public class StyledUpperCaseTextField
    extends StyledTextField
{
  public StyledUpperCaseTextField ()
  {
    super () ;
    setForceToUpperCase ( true ) ;
  }

  /**
   * @param <em>columns</em> the number of columns
   **/
  public StyledUpperCaseTextField ( int columns )
  {
    super ( columns ) ;
    setForceToUpperCase ( true ) ;
  }

  /**
   * @param <em>columns</em> the number of columns
   * @param <em>ratio</em> the ratio of defined field size to reduce display to
   **/
  public StyledUpperCaseTextField ( int columns , double ratio )
  {
    super ( columns , ratio ) ;
    setForceToUpperCase ( true ) ;
  }
}

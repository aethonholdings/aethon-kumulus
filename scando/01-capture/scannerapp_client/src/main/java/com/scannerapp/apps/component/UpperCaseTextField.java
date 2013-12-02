/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    UpperCaseTextField
 * Author:  Anna Swinney
 * Purpose:  The purpose of the UpperCaseTextField class is to provide an extension
 * of EdittedTextField that automatically converts input characters to upper case.
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
 * UpperCaseTextField class is an extension
 * of EdittedTextField that converts input characters to upper case characters.
 * @version     1.00 - July 16, 1998
 * @author      Anna Swinney
 **/
public class UpperCaseTextField
    extends EdittedTextField
{
  public UpperCaseTextField ()
  {
    super () ;
    setForceToUpperCase ( true ) ;
  }

  /**
   * @param <em>columns</em> the number of columns
   **/
  public UpperCaseTextField ( int columns )
  {
    super ( columns ) ;
    setForceToUpperCase ( true ) ;
  }
}

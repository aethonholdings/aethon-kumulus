/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    NumericTextField
 * Author:  Anna Swinney
     * Purpose:  The purpose of the NumericTextField class is to provide an extension
 * of EdittedTextField that limits input characters to numeric characters only.
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
 * NumericTextField class is an extension
 * of EdittedTextField that limits input characters to numeric characters only.
 * @version     1.00 - July 16, 1998
 * @author      Anna Swinney
 **/
public class NumericTextField
    extends EdittedTextField
{
  public NumericTextField ()
  {
    super () ;
    setAllowsOnlyNumbers ( true ) ;
  }

  /**
   * @param <em>columns</em> the number of columns
   **/
  public NumericTextField ( int columns )
  {
    super ( columns ) ;
    setAllowsOnlyNumbers ( true ) ;    
  }
  /**
   * Added by jiten for costing
   * Allow decimal point in number text field
   * @param <em>columns</em> the number of columns
   **/
  public NumericTextField ( int columns ,boolean bAllowDecimalPoint)
  {
    super ( columns ) ;
    setAllowsOnlyNumbers ( true ) ;
    setAllowsNumericSeparators(bAllowDecimalPoint);
  }
}

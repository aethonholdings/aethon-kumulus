/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    StyledNumericTableTextField
 * Author:  Christy Allmon
 * Purpose:  The purpose of the StyledNumericTableTextField class is to provide
 * an extension of StyledNumericTextField that returns an Integer object for
 * the table model.
 *
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 12/22/98 callmo   Initial release
 *
 **/
package com.scannerapp.apps.component ;

/**
 * StyledNumericTableTextField class is an extension
     * of StyledNumericTextField that returns an Integer object for the table model.
 * @version     1.00 - December 22, 1998
 * @author      Christy Allmon
 **/
public class StyledNumericTableTextField
    extends StyledNumericTextField
{
  public StyledNumericTableTextField ()
  {
    super () ;
    setAllowsOnlyNumbers ( true ) ;
  }

  /**
   * @param <em>columns</em> the number of columns
   **/
  public StyledNumericTableTextField ( int columns )
  {
    super ( columns ) ;
    setAllowsOnlyNumbers ( true ) ;
  }

  /**
   * @return <em>value</em> the integer of the text
   **/
  public Integer getValue ()
  {
    try
    {
      return new Integer ( getText () ) ;
    }
    catch ( NumberFormatException exception )
    {
      return new Integer ( 0 ) ;
    }
  }

  /**
   * @return <em>value</em> the float of the text
   **/
  public Double getFloatValue ()
  {
    try
    {
      return new Double ( getText () ) ;
    }
    catch ( NumberFormatException exception )
    {
      return new Double ( 0 ) ;
    }
  }
}

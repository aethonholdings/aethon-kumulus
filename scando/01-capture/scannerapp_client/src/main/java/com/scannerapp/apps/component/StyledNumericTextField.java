/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    NumericTextField
 * Author:  Anna Swinney
 * Purpose:  The purpose of the StyledNumericTextField class is to provide an extension
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
 * StyledNumericTextField class is an extension
 * of StyledTextField that limits input characters to numeric characters only.
 * @version     1.00 - July 16, 1998
 * @author      Anna Swinney
 **/
public class StyledNumericTextField
    extends StyledTextField
{
  public StyledNumericTextField ()
  {
    super () ;
    setAllowsOnlyNumbers ( true ) ;
  }

  /**
   * @param <em>columns</em> the number of columns
   **/
  public StyledNumericTextField ( int columns )
  {
    super ( columns ) ;
    setAllowsOnlyNumbers ( true ) ;
  }

  /**
   * @param <em>columns</em> the number of columns
   * @param <em>ratio</em> the ratio of defined field size to reduce display to
   **/
  public StyledNumericTextField ( int columns , double ratio )
  {
    super ( columns , ratio ) ;
    setAllowsOnlyNumbers ( true ) ;
  }

  /**
   *  Allow creating a default implementation of SelectionCriteriaInterface
   *  @return newly created instance of DefaultSelectionCriteria
   *  @see DefaultSelectionCriteria
   **/

  /*public DefaultSelectionCriteria createDefaultSelectionCriteria() {
   return new DefaultSelectionCriteria(this);
   }*/
} // end class

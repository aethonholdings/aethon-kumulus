/**
 *	Copyright 1998 Frito-Lay, Inc.
 *	All Rights Reserved
 *
 *	Package:	mst.common.component
 *	File:			FieldEditException
 * Author:		Anna Swinney
     *	Purpose:	This FieldEditException object is a "throwable" exception object that
 * is thrown by EdittedDocument to indicate 1) that the maximum field length has been
 * exceeded, or 2) that non-alphabetic characters have been entered in a alphabetic
     * field, or 3) non-numeric characters have been entered in a numeric-only field.
 *
     *========================== Modification History ===============================
 *
 *	Date		Who				Description of Modifications
 *	----------	-------------   -----------------------------------------------
 *	7/17/98		A. Swinney		Original Code
 *
 **/
package com.scannerapp.apps.component ;

/**
 * FieldEditException object is a "throwable" exception object that
 * is thrown by EdittedDocument to indicate 1) that the maximum field length has been
 * exceeded, or 2) that non-alphabetic characters have been entered in a alphabetic
     * field, or 3) non-numeric characters have been entered in a numeric-only field.
 *	@version	1.00 -- July 17, 1998
 *	@author		Anna Swinney
 **/
public class FieldEditException
    extends Exception
{

  /**
   * @serial
   **/
  private String _message ;

  /**
   * @serial
   **/
  private String[] _msgVariables ;
  public FieldEditException ()
  {
    this ( "" ) ;
  }

  /**
   * @param <em>messageId</em> a message Id to be displayed
   **/
  public FieldEditException ( String messageId )
  {
    this ( messageId , null ) ;
  }

  /**
   * @param <em>messageId</em> a message Id to be displayed
       * @param <em>msgVariables</em> array of variable that can be substituted within
   * the text corresponding to the message id
   **/
  public FieldEditException ( String messageId , String[] msgVariables )
  {
    _message = messageId ;
    _msgVariables = msgVariables ;
  }

  /**
   * Returns the message associated with the exception
   * @return <em>messageId</em> a message Id to be displayed
   **/
  public String getMessage ()
  {
    return _message ;
  }

  /**
   * Returns the variables associated with the exception message
       * @return <em>msgVariables</em> array of variable that can be substituted within
   * the text corresponding to the message id
   **/
  public String[] getMessageVariables ()
  {
    return _msgVariables ;
  }
}

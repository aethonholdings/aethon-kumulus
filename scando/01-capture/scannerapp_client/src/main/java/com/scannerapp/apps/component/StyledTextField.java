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
 * 05/03/99 asetia   Add _error so that we know when the field has error
 *
 **/
package com.scannerapp.apps.component ;

import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import com.scannerapp.apps.framework.StyleGuide;
import com.scannerapp.apps.framework.common.interfaces.SelectionCriteriaInterface;

/**
 * StyledTextField class is an extension
 * of EdittedTextField that applies Masterfiles specific formatting.
 * @version     1.00 - July 29, 1998
 * @author      Anna Swinney
 * modified		 Bill Davis	January 13, 2000
 *		Problem:  Text Strings longer than the field's MaximumLength are not displayed
 *		at all.
 *		Solution: Modified setText(String text) so a Text String longer than the
 *   MaximumLength is shortened and will now be displayed
 *
 **/
public class StyledTextField
    extends EdittedTextField
//    implements ErrorComponentInterface
{
  protected StyledFocusAdapter _styledFocusAdapter = null ;
  protected SelectionCriteriaInterface _selectionCriteria = null ;
  private transient boolean _error = false ;
  public StyledTextField ()
  {
    super () ;
    setAttributes () ;
  }

  /**
   * @param <em>columns</em> the number of columns
   **/
  public StyledTextField ( int columns )
  {
    super ( columns ) ;
    setAttributes () ;
  }

  /**
   * @param <em>columns</em> the number of columns
   * @param <em>resizeRatio</em> the ratio between 0 and 1 of the field size;
   *		The field is being sized by a wide letter 'm', so it appears too large
   * onscreen for normal use.  The display size can be reduced, while still
   * allowing for the entry of <em>columns</em> number of chars
   **/
  public StyledTextField ( int columns , double resizeRatio )
  {
    super ( ( int ) ( ( ( double ) columns ) *
                      ( resizeRatio <= 0.0 ? 1.0 : resizeRatio ) ) ) ;
    setAttributes () ;
    setMaximumLength ( columns ) ;
  }

  /**
   * Sets the initial default attributes of the field
   **/
  protected void setAttributes ()
  {
    setErrorDisplayAttributes ( _error ) ;
    super.setPreferredSize ( new Dimension ( getWidth () , 22 ) ) ;
    setMinimumSize ( getPreferredSize () ) ;
  }

  /**
   * Set background and font attributes based on whether the field is in error or not
   * @param <em>error</em> boolean indicating whether or not this field
   * is currently in error
   **/
  public void setErrorDisplayAttributes ( boolean error )
  {
    _error = error ;
    super.setDisabledTextColor ( StyleGuide.LABEL_FOREGROUND_COLOR ) ;
    if ( error )
    {
      setBackground ( StyleGuide.TEXTFIELD_ERROR_BACKGROUND_COLOR ) ;
      setFont ( StyleGuide.TEXTFIELD_ERROR_FONT ) ;
    }
    else
    {
      setFont ( StyleGuide.TEXTFIELD_FONT ) ;
      if ( this.isEnabled () )
      {
        setBackground ( StyleGuide.TEXTFIELD_BACKGROUND_COLOR ) ;
      }
      else
      {
        setBackground ( StyleGuide.TEXTFIELD_DISABLED_BACKGROUND_COLOR ) ;
      }
    }
    this.repaint () ;
  }

  /**
   * Enables or disables this text field, depending on the value of the
   * parameter <code>enabled</code>. An enabled component can respond to user
   * input and generate events.
   *
   * @param     <em>enabled</em>   If <code>true</code>, this component is
   *            enabled; otherwise this component is disabled.
   */
  public void setEnabled ( boolean enabled )
  {
    super.setEnabled ( enabled ) ;
    if ( enabled )
    {
      setBackground ( StyleGuide.TEXTFIELD_BACKGROUND_COLOR ) ;
    }
    else
    {
      setBackground ( StyleGuide.TEXTFIELD_DISABLED_BACKGROUND_COLOR ) ;
    }
  }

  /**
   * Return boolean whether it has error or not
   * @return false if has no error true otherwise
   **/
  public boolean hasError ()
  {
    return _error ;
  }

  /**
   **/
  public void setText ( String text )
  {
    if ( getMaximumLength () > 0 && text.length () > getMaximumLength () )
    {
      text = text.substring ( 0 , getMaximumLength () ) ;
    }
    super.setText ( text ) ;
    if ( getMaximumLength () > getColumns () && text.length () > getColumns () )
    {
      setCaretPosition ( 0 ) ;
    }
  }

  /**
   *  This allows highliting the text based on a Criteria.
   *  @param selectionCriteria defines the Criteria
   *  @see com.scannerapp.apps.framework.common.interfaces.SelectionCriteriaInterface
   **/

  public void setSelectedOnFocus ( SelectionCriteriaInterface selectionCriteria )
  {
    _selectionCriteria = selectionCriteria ;
    if ( selectionCriteria != null && _styledFocusAdapter == null )
    {
      addFocusListener ( _styledFocusAdapter = new StyledFocusAdapter () ) ;
    }
    else
    {
      if ( _styledFocusAdapter != null )
      {
        removeFocusListener ( _styledFocusAdapter ) ;
        _styledFocusAdapter = null ;
      }
    }
  }
  /**
   *  Inner Class to Handle of focusGained event to highlite the text
   *  @see setSelectedOnFocus
   *  @see com.scannerapp.apps.framework.common.interfaces.SelectionCriteriaInterface
   **/
  protected class StyledFocusAdapter
      extends FocusAdapter
  {
    StyledFocusAdapter () { }
    public void focusGained ( FocusEvent event )
    {
      if ( _selectionCriteria != null && _selectionCriteria.shouldSelect () )
      {
        selectAll () ;
      }
    }
  }
} // end class

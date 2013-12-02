/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    StylePasswordField
 * Author:  Keyur Shah
 * Purpose:  The purpose of the StyledPasswordField class is to provide an extension
 * of JPasswordField that can exhibit additional behaviors such as 1) forcing all input
 * to upper case 2) forcing all input to lower case 3) limiting input characters to
 * numbers only 4) limiting input characters to alphabetic characters only 5) limiting
 * input to a particular number of characters, which defaults to the length of the
 * field specifiec in "columns".
 *
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 25/04/02 Keyur   Initial release
 *
 **/
package com.scannerapp.apps.component ;

import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import com.scannerapp.apps.framework.StyleGuide;
import com.scannerapp.apps.framework.common.interfaces.ErrorComponentInterface;
import com.scannerapp.apps.framework.common.interfaces.SelectionCriteriaInterface;


/**
 * StyledPasswordField class is an extension
 * of EdittedPasswordField that applies Masterfiles specific formatting.
 * @version     1.00 - April 24, 2002
 * @author      Keyur Shah
 **/
public class StyledPasswordField extends EdittedPasswordField implements ErrorComponentInterface
{
  protected StyledFocusAdapter _styledFocusAdapter = null ;
  protected SelectionCriteriaInterface _selectionCriteria = null ;
  private transient boolean _error = false ;
  public StyledPasswordField ()
  {
    super () ;
    setAttributes () ;
  }

  /**
   * @param <em>columns</em> the number of columns
   **/
  public StyledPasswordField ( int columns )
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
  public StyledPasswordField ( int columns , double resizeRatio )
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
   * Enables or disables this Password field, depending on the value of the
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
   *  This allows highliting the Password based on a Criteria.
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
   *  Inner Class to Handle of focusGained event to highlite the Password
   *  @see setSelectedOnFocus
   *  @see com.scannerapp.apps.framework.common.interfaces.SelectionCriteriaInterface
   **/
  protected class StyledFocusAdapter
      extends FocusAdapter
  {
    public void focusGained ( FocusEvent event )
    {
      if ( _selectionCriteria != null && _selectionCriteria.shouldSelect () )
      {
        selectAll () ;
      }
    }
  }
} // end class

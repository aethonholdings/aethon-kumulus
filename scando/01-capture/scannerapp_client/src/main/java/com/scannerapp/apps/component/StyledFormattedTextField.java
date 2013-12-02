/**
 *	Copyright 1998 Frito-Lay, Inc.
 *	All Rights Reserved
 *
 *	Package:  fl.jfc.textField
 *	File:			StyledFormattedTextField
 * Author:		Arief Setiawan
 *	Purpose:  This class allows user to format the text field.
 *         - The field is a fixed width column set by the template
     *         - The template is a blank template with the anchor, whenever user try
     *           to delete/clear the data in the text field, it always reset to this
     *           template. The elements of the template must NOT be digit or letter.
 *         - The anchor is the character that will not be able to be deleted
 *           (in the text field) and serves as a separator(for the data)
 *           creating a format; The anchor must be a non-digit and non-letter.
 *
 *         + user can get and set the input using getData() and setData()
 *         + user can get and set the textfield using getValue() and setValue()
 *
     *========================== Modification History ===============================
 *
 *	Date		    Who				      Description of Modifications
 *	----------	-------------   -----------------------------------------------
 *	10/05/98		  Asetia  		  Original Code
 *
 **/
package com.scannerapp.apps.component ;

import java.awt.Dimension;
import java.util.Vector;

import com.scannerapp.apps.framework.StyleGuide;
import com.scannerapp.apps.framework.common.interfaces.Disposable;

/**
 * StyledFormattedTextField class is a class that allows user to format the text field
     * to the style that they want. There are some restrictions/assumptions in using
 * this class in order to work appropriately.
 * The restrictions are:
 * - The text field must be a fixed length.
 * - The anchor must NOT be a digit or letter.
 * - Only digit and/or letter are allowed to be inserted to the field.
 *
 * The following example demonstrates of how this class is instantiated with minimum
 * parameter:
 * <p><hr><blockquote><pre>
 *     StyledFormattedTextField field = new StyledFormattedTextField("(   )   -    ");
 * <p>
 *     // Phone number field with only numeric value
 *     StyledFormattedTextField field = new StyledFormattedTextField("(   )   -    ",true);
 *
 * </pre></blockquote><hr>
 * <p>
 *
 * @author  Arief Setiawan
 * @version 1.00, 11/07/98
 * @see
 * @see FormattedTextDocument
 */
public class StyledFormattedTextField
    extends FormattedTextField
    implements Disposable
{

      /** The Following is for converting the variant value stored in the text file.
   *   Helpper statics
   **/
  public static final String NUMERIC_ONLY = "1" ;
  public static final String ALPHA_ONLY = "2" ;
  public static final String UPPER_CASE_ALPHA_ONLY = "3" ;
  public static final String ALPHA_NUMERIC = "4" ;
  public static final String UPPER_CASE_ALPHA_NUMERIC = "5" ;
  public static boolean isVariantNumeric ( String variantString )
  {
    if ( variantString.equals ( NUMERIC_ONLY ) )
    {
      return true ;
    }
    if ( variantString.equals ( ALPHA_ONLY ) ||
         variantString.equals ( UPPER_CASE_ALPHA_ONLY ) ||
         variantString.equals ( ALPHA_NUMERIC ) ||
         variantString.equals ( UPPER_CASE_ALPHA_NUMERIC ) )
    {
      return false ;
    }
    try
    {
      return Boolean.getBoolean ( variantString ) ;
    }
    catch ( Exception e )
    {
      e.printStackTrace();
    }
    return false ;
  }

  public static boolean isVariantUpperCase ( String variantString )
  {
    if ( variantString.equals ( UPPER_CASE_ALPHA_ONLY ) ||
         variantString.equals ( UPPER_CASE_ALPHA_NUMERIC ) )
    {
      return true ;
    }
    return false ;
  }

  private FormattedTextDocument _formattedTextDocument ;
  protected String _template ;

  /** Constructors
   * The following are description of each parameter:
   * @param StyledFormattedTextField - (this) the Text field that is to be formatted
   * @param String  - (template) the template for the text field (format style)
   * @param Vector  - (anchor) the anchor, character in the text field that cannot be
   *                  editted, must not be digit or letter
   * @param boolean - (numericOnly) When true, only digit are allowed to be inserted to the
   *                  field. When false, both digit and letter are allowed.
   **/

  /**
       * It is impossible to instantiate StyledFormattedTextField without parameter,
   * use other constructors instead
   **/
  public StyledFormattedTextField ( String template )
  {
    super ( template ) ;
    setAttributes () ;
    this.setCaret ( new BigCaret () ) ;
  }

  public StyledFormattedTextField ( String template , boolean numericOnly )
  {
    super ( template , numericOnly ) ;
    setAttributes () ;
    this.setCaret ( new BigCaret () ) ;
  }

  /* public StyledFormattedTextField(String template, boolean numericOnly, boolean upperCase) {
       super(template,numericOnly, upperCase);
       setAttributes();
       this.setCaret(new BigCaret());
       }*/
  public StyledFormattedTextField ( String template , Vector anchor )
  {
    super ( template , anchor ) ;
    setAttributes () ;
    this.setCaret ( new BigCaret () ) ;
  }

  public StyledFormattedTextField ( String template , Vector anchor ,
                                    boolean numericOnly )
  {
    super ( template , anchor , numericOnly ) ;
    setAttributes () ;
    this.setCaret ( new BigCaret () ) ;
  }

  /* public StyledFormattedTextField(String template, Vector anchor, boolean numericOnly, boolean upperCase) {
       super(template,anchor,numericOnly, upperCase);
       setAttributes();
       this.setCaret(new BigCaret());
       }*/

  /**
   * Sets the initial default attributes of the field
   **/
  protected void setAttributes ()
  {
    setErrorDisplayAttributes ( false ) ;
    super.setPreferredSize ( new Dimension ( getWidth () , 22 ) ) ;
  }

  /**
   * Set background and font attributes based on whether the field is in error or not
   * @param <em>error</em> boolean indicating whether or not this field
   * is currently in error
   **/
  public void setErrorDisplayAttributes ( boolean error )
  {
    super.setDisabledTextColor ( StyleGuide.LABEL_FOREGROUND_COLOR ) ;
    if ( error )
    {
      setBackground ( StyleGuide.TEXTFIELD_ERROR_BACKGROUND_COLOR ) ;
      setFont ( StyleGuide.TEXTFIELD_ERROR_FONT ) ;
    }
    else
    {
      setBackground ( StyleGuide.TEXTFIELD_BACKGROUND_COLOR ) ;
      setFont ( StyleGuide.TEXTFIELD_FONT ) ;
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
   *
   **/
  public void dispose ()
  {
    _formattedTextDocument = null ;
    _template = null ; //in superclass
    try
    {
      super.finalize () ;
    }
    catch ( Throwable e )
    {

      // What the heck are you supposed to do if you can't finalize something?
    }
  } // end
} //StyledFormattedTextField

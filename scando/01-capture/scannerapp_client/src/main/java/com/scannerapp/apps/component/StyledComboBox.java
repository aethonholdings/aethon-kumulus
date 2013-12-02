/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    StyledComboBox
 * Author:  Anna Swinney
 * Purpose:  The purpose of the StyledComboBox class is to provide an extension
 * of JComboBox that exhibits Masterfiles-specific Behaviors.
 *
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 07/28/98 aswinn   Initial release
 * 09/11/00 D.Kruse  added process on enter
 *
 **/
package com.scannerapp.apps.component ;

import java.io.Serializable;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import org.apache.log4j.Logger;

import com.scannerapp.apps.framework.StyleGuide;

/**
 * StyledComboBox class is an extension
 * of JComboBox that exhibits Masterfiles-specific behaviors.
 * @version     1.00 - July 28, 1998
 * @author      Anna Swinney
 **/
public class StyledComboBox
    extends JComboBox
//    implements ErrorComponentInterface
{
  protected StyledKeySelectionManager _overrideKeySelectionManager = null ;
  private static Logger log = Logger.getLogger(StyledComboBox.class);//For Log4j
  public StyledComboBox ()
  {
    super () ;
    setErrorDisplayAttributes ( false ) ;
    setStyledKeySelectionManager () ;
  } // end constructor

  /**
       * @param <em>useDefaultKeySelection</em> A Boolean indicating whether to use the
   * default key selection manager provided by Swing (matches a single character) (use true) or
       * the StyledKeySelectionManager which matches on multiple characters (use false)
   **/
  public StyledComboBox ( boolean useDefaultKeySelection )
  {
    super () ;
    setErrorDisplayAttributes ( false ) ;
    if ( !useDefaultKeySelection )
    {
      setStyledKeySelectionManager () ;
    }
  } // end constructor

  /**
   * @param <em>model</em> A combo box data model
   **/
  public StyledComboBox ( ComboBoxModel model )
  {
    super ( model ) ;
    setErrorDisplayAttributes ( false ) ;
    setStyledKeySelectionManager () ;
    //setFont(new Font("Dialog", Font.PLAIN, 11));
    //setBounds();
  } // end constructor

  /**
   * @param <em>model</em> A combo box data model
       * @param <em>useDefaultKeySelection</em> A Boolean indicating whether to use the
   * default key selection manager provided by Swing (matches a single character) (use true) or
       * the StyledKeySelectionManager which matches on multiple characters (use false)
   **/
  public StyledComboBox ( ComboBoxModel model , boolean useDefaultKeySelection )
  {
    super ( model ) ;
    setErrorDisplayAttributes ( false ) ;
    if ( !useDefaultKeySelection )
    {
      setStyledKeySelectionManager () ;
    }
  } // end constructor

  /**
   * Returns the list model if it is of type ArrayOfPairsComboBoxModel.
   * @return The data model, if it is of type ArrayOfPairsComboBoxModel.  Otherwise,
   * null
   **/

  /* public ArrayOfPairsComboBoxModel getPairModel() {
       if (super.getModel() instanceof ArrayOfPairsComboBoxModel) {
       return (ArrayOfPairsComboBoxModel)super.getModel();
       } // end if
       return null;
       } // end method*/

  /**
   * Sets background and font attributes based on whether the field is in error or not
   * @param <em>error</em> boolean indicating whether or not this field
   * is currently in error
   **/
  public void setErrorDisplayAttributes ( boolean error )
  {

    // good place to thread this back to the system event queue
    if ( error )
    {
      setBackground ( StyleGuide.LISTBOX_ERROR_BACKGROUND_COLOR ) ;
      setFont ( StyleGuide.LISTBOX_ERROR_FONT ) ;
    }
    else
    {
      setBackground ( StyleGuide.LISTBOX_BACKGROUND_COLOR ) ;
      setForeground ( StyleGuide.LISTBOX_FOREGROUND_COLOR ) ;
      setFont ( StyleGuide.LISTBOX_FONT ) ;
    } // end if else
  } //end method

  /**
   * Enforces appropriate background colors when disabled.
   * This was done for the metal look&feel
   * @param <em>enabled</em> A boolean indicating whether the field is to be enabled
   * (true) or disabled (false)
   **/
  public void setEnabled ( boolean enabled )
  {
    super.setEnabled ( enabled ) ;
    if ( enabled )
    {
      setBackground ( StyleGuide.LISTBOX_BACKGROUND_COLOR ) ;
    }
    else
    {
      setBackground ( StyleGuide.TEXTFIELD_DISABLED_BACKGROUND_COLOR ) ;
    }
  } // end method

  /**
   **/
  public void setStyledKeySelectionManager ()
  {
    _overrideKeySelectionManager = new StyledKeySelectionManager () ;
    this.setKeySelectionManager ( _overrideKeySelectionManager ) ;
  }

  /**
   **/
  public void setKeyDelay ( long delay )
  {
    if ( _overrideKeySelectionManager != null )
    {
      _overrideKeySelectionManager.setDelay ( delay ) ;
    }
  }

  /**
   **/
  public void dispose ()
  {

    //if (_overrideKeySelectionManager != null) {
    this.setKeySelectionManager ( null ) ;
    _overrideKeySelectionManager = null ;

    //this.dataModel = null;

    /*
         try {
         super.finalize();
         }
         catch (Throwable e) {
         //ignore it
         }
     */
  }

  //  This is used by StyledComboBoxWithProcessOnEnter
  public void processEnter ()
  {

  }

  /**
   * Inner class to handle revised keyboard entry (multiple letter matching vs.
       * the default single letter matching).  Must be here because KeySelectionManager
   * is a public interface but is only defined within the JComboBox class.
   **/
  class StyledKeySelectionManager
      implements KeySelectionManager , Serializable
  {
    /* Code Clean-up : Dhwanil */
    StyledKeySelectionManager () {}
    /**/
    private String _priorPattern = "" ;
    private long _priorTime = System.currentTimeMillis () ;
    private long _delay = 400 ;
    public int selectionForKey ( char aKey , ComboBoxModel aModel )
    {
      int returnI = doSelectionForKey ( aKey , aModel ) ;
      if ( aKey == '\n' || aKey == '\r' )
      {
        processEnter () ;
      }
      return returnI ;
    }

    public int doSelectionForKey ( char aKey , ComboBoxModel aModel )
    {
      int i , c ;
      int currentSelection = 0 ;
      Object selectedItem = aModel.getSelectedItem () ;
      String v ;
      String pattern ;
      if ( selectedItem != null )
      { //start where you are
        selectedItem = selectedItem.toString () ;
        for ( i = 0 , c = aModel.getSize () ; i < c ; i++ )
        { // find position in model
          if ( selectedItem.equals ( aModel.getElementAt ( i ).toString () ) )
          {
            currentSelection = i ;
            break ;
          }
        }
      }
      long newTime = System.currentTimeMillis () ;
      long difference = newTime - _priorTime ;
      if ( difference > _delay )
      {
        _priorPattern = "" ;
        currentSelection++ ;
      }
      pattern = ( _priorPattern + aKey ).toLowerCase () ;
      _priorPattern = pattern ;
      _priorTime = newTime ;

      //log.debug("Pattern = " + pattern + "  Delay = " + difference);
      aKey = pattern.charAt ( 0 ) ;
      boolean matched = true ;
      for ( i = currentSelection++ , c = aModel.getSize () ; i < c ; i++ )
      { //from here to end of model
        v = aModel.getElementAt ( i ).toString ().toLowerCase () ;

        //
        matched = true ;
        for ( int k = 0 ; k < pattern.length () ; k++ )
        {

          //
          if ( v.length () > k && v.charAt ( k ) == pattern.charAt ( k ) )
          {

          }
          else
          {
            matched = false ;
          }
        }
        if ( matched )
        {
          return i ;
        }
      }
      for ( i = 0 ; i < currentSelection ; i++ )
      { // start at the top again
        if ( i < aModel.getSize () )
        { //01958-CW   F1 on the last item of the combobox

          //throws null pointer exception
          v = aModel.getElementAt ( i ).toString ().toLowerCase () ;

          //
          matched = true ;
          for ( int k = 0 ; k < pattern.length () ; k++ )
          {

            //
            if ( v.length () > k && v.charAt ( k ) == pattern.charAt ( k ) )
            {

            }
            else
            {
              matched = false ;
            }
          }
          if ( matched )
          {
            return i ;
          }
        }
      }
      return -1 ;
    }

    protected void setDelay ( long delay )
    {
      _delay = delay ;
    }
  }
} // end class

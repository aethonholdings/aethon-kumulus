package com.scannerapp.apps.component ;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;

import com.scannerapp.apps.framework.StyleGuide;

public class StyledListBox
    extends JList
    implements KeyListener , FocusListener
{
  public StyledListBox ()
  {
    super () ;
    setErrorDisplayAttributes ( false ) ;
  }

  public StyledListBox ( ListModel model )
  {
    super ( model ) ;
    setErrorDisplayAttributes ( false ) ;
  }

  public StyledListBox ( Vector data )
  {
    super ( data ) ;
    setErrorDisplayAttributes ( false ) ;
  }

  public StyledListBox ( DefaultListModel model )
  {
    super ( model ) ;
    setErrorDisplayAttributes ( false ) ;
  }

  public void setErrorDisplayAttributes ( boolean error )
  {
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
    }
  }

  public int getNumberOfSelectedItems ()
  {
    return super.getSelectedValues ().length ;
  }

  /**
   * It is <code>true</code> when the 'Enter' key is in pressed condition.
   */
  private boolean ENTER_KEY_PRESSED = false ;

  /**
   * FocusedComponent - holds the reference for the last focused component.
   * will be used to perform copy operation.
   */
  private static StyledListBox focusedComponent ;

  /**
   * Returns the last focused EJList.
   */
  public static final StyledListBox getFocusedComponent ()
  {
    return focusedComponent ;
  }

  /**
   * Invoked when a key has been typed. This event occurs when a key press is
   * followed by a key release.
   *
   * @param event the KeyEvent
   * @see #selectionForKey
   * @see #ENTER_KEY_PRESSED
   * @see #searchString
   */
  public void keyTyped ( java.awt.event.KeyEvent event )
  {
    int i = 0 ;
    if ( getModel ().getSize () > 0 )
    {
      selection = getSelectedIndex () ;
      i = selectionForKey ( event.getKeyChar () ) ;
      setSelectedIndex ( i ) ;
      ensureIndexIsVisible ( i ) ;
    }
  }

  /**
   * Invoked when a key has been pressed.
   *
   * @param event the KeyEvent
   * @see #ENTER_KEY_PRESSED
   * @see #start
   */
  public void keyPressed ( java.awt.event.KeyEvent event )
  {
    ENTER_KEY_PRESSED = ( event.getKeyChar () == KeyEvent.VK_ENTER ) ;
  }

  /**
   * Invoked when a key has been released.
   *
   * @param event the KeyEvent
   * @see #ENTER_KEY_PRESSED
   */
  public void keyReleased ( java.awt.event.KeyEvent event )
  {
    if ( event.getKeyChar () == KeyEvent.VK_ENTER )
    {
      ENTER_KEY_PRESSED = false ;
    }
  }

  private int selectionForKey ( char aKey )
  {
    updateSearchString ( aKey ) ;
    if ( ESCPressed )
    {
      start = 0 ;
      ESCPressed = false ;
    }
    selection = search () ;
    return selection ;
  }

  /**
   * Called when the user types character other than ther very first
   * character of the Search String . This code is used for optimization
   * purpose to avoid search the entire list contents again and again as the
   * user goes on typing the other characters of the search string
   *
   * @return index for the matching value in this list starting with
   * #searchString.
   * @see #searchString
   * @see #ENTER_KEY_PRESSED
   * @see #start
   * @see #selection
   */
  private int search ()
  {
    int searchLength = searchString.length () ;
    if ( !ENTER_KEY_PRESSED )
    {
      for ( int i = start ; i < getModel ().getSize () ; i++ )
      {
        String s = getModel ().getElementAt ( i ).toString () ;
        if ( s.regionMatches ( true , 0 , searchString , 0 , searchLength ) )
        {
          selection = i ;
          break ;
        }
      }
      start = selection ;
    }
    else
    {
      start = 0 ;
    }
    return selection ;
  }

  public void focusGained ( FocusEvent event )
  {

    // set the focused component.
    focusedComponent = this ;
  }

  /**
   * Invoked when a component loses the keyboard focus.
   *
   * @param event the FocusEvent object
   */
  public void focusLost ( FocusEvent event )
  {

    //focusedComponent = null;
  }

  /**
   * Used to update #searchString as the user types the word. If any special
   * keys like 'Escape', 'Enter' is pressed the #searchString is reset to an
   * empty string.
   *
   * @param key the character with which #searchString should be updated
   * @see #ESCPressed
   * @see #ENTER_KEY_PRESSED
   * @see #searchString
   */
  private void updateSearchString ( char key )
  {
    if ( Character.getNumericValue ( key ) == -1 )
    { //if not alpha numeric.
      searchString = "" ;
      if ( ( Character.getType ( key ) == 15 ) &&
           !ENTER_KEY_PRESSED ) /* for the escape key*/
      {
        ESCPressed = true ;
      }
      else
      {
        ESCPressed = false ;
      }
    }
    else
    {
      searchString += key ;
    }
  }

  /**
   * Contains the string to search.
   */
  private String searchString = "" ;

  /**
   * It is <code>true</code> when the 'Escape' key is in pressed condition.
   */
  private boolean ESCPressed = false ;

  /**
   * starting index for the list to search a matching value starting with
   * #searchString.
   */
  private int start = 0 ;

  /**
   * index for the matching value in the list starting with #searchString.
   */
  private int selection = 0 ;
}

/**
 *	Copyright 1998 Frito-Lay, Inc.
 *	All Rights Reserved
 *
 *	Package:	mst.common.component
 *	File:			StyledRadioToolBar.java
 * Author:		Chris Kohle
 *	Purpose:  StyledRadioToolBar extends JToolBar and provides a method to add
 *           actions in the form of radio buttons.  The button group is also
 *           contained within this object.
 *
     *========================== Modification History ===============================
 *
 *	Date		Who				Description of Modifications
 *	----------	-------------   -----------------------------------------------
 *	8/30/98		  C. Kohle        Initial release
 **/
package com.scannerapp.apps.component ;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import com.scannerapp.apps.framework.StyleGuide;

/**
 * StyledRadioToolBar extends JToolBar and provides a method to add
 * actions in the form of radio buttons.  The button group is also
 * contained within this object.
 *	@version	1.00 -- August 30, 1998
 *	@author		Chris Kohle
 **/
public class StyledRadioToolBar
    extends JToolBar
{

  // attributes
  private ButtonGroup _group = new ButtonGroup () ;

  /**
   *	Constructor.
   **/
  public StyledRadioToolBar ()
  {
    super () ;
//  commented by Manish Vithlani for changing Look
    //this.setBackground ( StyleGuide.PANEL_BACKGROUND_COLOR ) ;
    //this.setFloatable ( false ) ;
  } // end constructor

  /**
   * adds an action to this toolbar.
   * @param action the Action
   * @param selected a boolean value that tells weither or not this action is selected.
   **/
  public JRadioButton addRadioAction ( Action action , boolean selected )
  {
    JRadioButton button = new JRadioButton ( ( String ) action.getValue (
        Action.NAME ) , ( Icon ) action.getValue ( Action.SMALL_ICON ) ) ;
    _group.add ( button ) ;
    button.setSelected ( selected ) ;
    button.setEnabled ( action.isEnabled () ) ;
    button.addActionListener ( action ) ;
    super.add ( button ) ;
    PropertyChangeListener actionPropertyChangeListener =
        createActionChangeListener ( button ) ;
    action.addPropertyChangeListener ( actionPropertyChangeListener ) ;
    button.setFont ( StyleGuide.BUTTON_FONT ) ;
    button.setBackground ( StyleGuide.BUTTON_BACKGROUND_COLOR ) ;
    button.setForeground ( StyleGuide.BUTTON_FOREGROUND_COLOR ) ;
    button.setRequestFocusEnabled ( false ) ;
    button.setOpaque ( true ) ;
    button.revalidate () ;
    return button ;
  } // end method

  /**
   * created and returns a new PropertyChangeListener
   * @param JRadioButton b
   * @return PropertyChangeListener
   **/
  protected PropertyChangeListener createActionChangeListener ( JRadioButton b )
  {
    return new ActionChangedListener ( b ) ;
  } // end method

  /**
   * IMBEDDED CLASS USED TO NOTIFY ALL RADIO BUTTON OF A CHANGE
   **/
  private class ActionChangedListener
      implements PropertyChangeListener
  {
    JRadioButton button ;
    ActionChangedListener ( JRadioButton b )
    {
      super () ;
      this.button = b ;
    } // end constructor

    public void propertyChange ( PropertyChangeEvent e )
    {
      String propertyName = e.getPropertyName () ;
      if ( e.getPropertyName ().equals ( Action.NAME ) )
      {
        String text = ( String ) e.getNewValue () ;
        button.setText ( text ) ;
        button.revalidate () ;

        //^^        button.repaint();
      }
      else
      {
        if ( propertyName.equals ( "enabled" ) )
        {
          Boolean enabledState = ( Boolean ) e.getNewValue () ;
          button.setEnabled ( enabledState.booleanValue () ) ;
          button.revalidate () ;

          //^^        button.repaint();
        }
        else
        {
          if ( e.getPropertyName ().equals ( Action.SMALL_ICON ) )
          {
            Icon icon = ( Icon ) e.getNewValue () ;
            button.setIcon ( icon ) ;
            button.revalidate () ;

            //^^        button.repaint();
          } // end if
        }
      }
    } // end method
  } // end imbeded class
} // end class

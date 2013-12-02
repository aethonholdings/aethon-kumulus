package com.scannerapp.apps.component ;

import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class StyledMenu
    extends JMenu
{
  private ButtonGroup _group = new ButtonGroup () ;
  public StyledMenu ()
  {
    super () ;
//  commented by Manish Vithlani for changing Look
    //this.setBackground ( StyleGuide.SUB_MENU_TAB_BACKGROUND_COLOR ) ;
    //this.setFont ( StyleGuide.MENU_TAB_FONT ) ;
    this.setRequestFocusEnabled ( false ) ;
  }

  public JMenuItem addMenuAction ( Action action )
  {
    StyledMenuItem item = new StyledMenuItem ( action ) ;
    setAction ( action , item ) ;
    return item ;
  }

  public JCheckBoxMenuItem addCheckBoxAction ( Action action , boolean selected )
  {
    StyledCheckBoxMenuItem item = new StyledCheckBoxMenuItem ( action ) ;
    _group.add ( item ) ;
    setAction ( action , item ) ;
    item.setSelected ( selected ) ;
    return item ;
  }

  public JRadioButtonMenuItem addRadioAction ( Action action )
  {
    StyledRadioButtonMenuItem item = new StyledRadioButtonMenuItem ( ( String )
        action.getValue ( Action.NAME ) ,
        ( Icon ) action.getValue ( Action.SMALL_ICON ) ) ;
    _group.add ( item ) ;
    setAction ( action , item ) ;
    return item ;
  }

  public void menuSelectionChanged ( boolean isIncluded )
  {
    if ( super.isRequestFocusEnabled () )
    {
      setSelected ( isIncluded ) ;
    }
  }

  private void setAction ( Action action , JMenuItem item )
  {
    item.setHorizontalTextPosition ( JButton.RIGHT ) ;
    item.setVerticalTextPosition ( JButton.CENTER ) ;
    item.setEnabled ( action.isEnabled () ) ;
    item.addActionListener ( action ) ;
    super.add ( item ) ;
    PropertyChangeListener actionPropertyChangeListener =
        createActionChangeListener ( item ) ;
    action.addPropertyChangeListener ( actionPropertyChangeListener ) ;
  }

  public void setStyledMenuEnabled ( boolean enabled , String tabName )
  {
    super.setEnabled ( enabled ) ;
  }
}

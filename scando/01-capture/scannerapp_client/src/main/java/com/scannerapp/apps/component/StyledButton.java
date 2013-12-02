package com.scannerapp.apps.component ;

import javax.swing.Icon;
import javax.swing.JButton;

import com.scannerapp.apps.framework.StyleGuide;

public class StyledButton
    extends JButton
{
  public StyledButton ()
  {
    super () ;
    //commented by Manish Vithlani for changing Look
    //setAttributes () ;
  }

  public StyledButton ( String title )
  {
    super ( title ) ;
    //commented by Manish Vithlani for changing Look
    //setAttributes () ;
  }

  public StyledButton ( Icon icon )
  {
    super ( icon ) ;
    //commented by Manish Vithlani for changing Look
    //setAttributes () ;
  }

  private void setAttributes ()
  {
    this.setFont ( StyleGuide.BUTTON_FONT ) ;
    this.setBackground ( StyleGuide.BUTTON_BACKGROUND_COLOR ) ;
    this.setForeground ( StyleGuide.BUTTON_FOREGROUND_COLOR ) ;
    this.setRequestFocusEnabled ( true ) ;
    this.setOpaque ( true ) ;
  }

  public void setStyledButtonEnabled ( boolean enabled , String tabName )
  {
    super.setEnabled ( enabled ) ;
  }
}

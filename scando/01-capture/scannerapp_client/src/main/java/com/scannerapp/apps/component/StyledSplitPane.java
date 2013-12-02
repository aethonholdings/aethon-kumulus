/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    StyledScrollPane.java
 * Author:  Anna Swinney
 * Purpose: StyledScrollPane extends JScrollPane initialize method to set the default
 *          style.
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 10/01/98 aswinn   Initial release
 **/
package com.scannerapp.apps.component ;

import java.awt.Component;

import javax.swing.JSplitPane;

import com.scannerapp.apps.framework.StyleGuide;
import com.scannerapp.apps.framework.common.interfaces.Disposable;

public class StyledSplitPane
    extends JSplitPane
    implements Disposable
{

  /**
   * Constructor
   **/
  public StyledSplitPane ()
  {
    super () ;
//  commented by Manish Vithlani for changing Look
    //setAttributes () ;
  } //end constructor

  /**
   * @param <em>orientation</em> An int specifying the horizontal or vertical
   * orientation
   * @param <em>continuousLayout</em>  a boolean, true for the components to
   * redraw continuously as the divider changes position, false
   * to wait until the divider position stops changing to redraw
   **/
  public StyledSplitPane ( int orientation , boolean continuousLayout )
  {
    super ( orientation , continuousLayout ) ;
//  commented by Manish Vithlani for changing Look
    //setAttributes () ;
  } //end constructor

  /**
   * @param <em>orientation</em> An int specifying the horizontal or vertical
   * orientation
   * @param <em>continuousLayout</em>  a boolean, true for the components to
   * redraw continuously as the divider changes position, false
   * to wait until the divider position stops changing to redraw
       * @param <em>newLeftComponent</em> the Component that will appear on the left
   * of a horizontally-split pane, or at the top of a
   * vertically-split pane.
       * @param <em>newRightComponent</em> the Component that will appear on the right
   * of a horizontally-split pane, or at the bottom of a
   * vertically-split pane.
   *
   **/
  public StyledSplitPane ( int orientation , boolean continuousLayout ,
                           Component newLeftComponent ,
                           Component newRightComponent )
  {
    super ( orientation , continuousLayout , newLeftComponent ,
            newRightComponent ) ;
//  commented by Manish Vithlani for changing Look
    //setAttributes () ;
  } //end constructor

  /**
   * Sets the default look & behaviors of StyledSplitPane that differ from its
   * superclass
   **/
  protected void setAttributes ()
  {
    setBackground ( StyleGuide.PANEL_BACKGROUND_COLOR ) ;

    //setOneTouchExpandable(false);
  }

  /**
   * Returns a minimum divider location equal to 90% of the height of the panel
   * @return The minimum divider location (int)
   **/
  public int getMinimumDividerLocation ()
  {
    Double location = new Double ( getHeight () * .9 ) ;
    return location.intValue () ;
  }

  /**
   * Removes all components from the component
   **/
  public void dispose ()
  {
    if ( this.getTopComponent ()instanceof Disposable )
    {
      ( ( Disposable ) getTopComponent () ).dispose () ;
    }
    if ( this.getBottomComponent ()instanceof Disposable )
    {
      ( ( Disposable ) getBottomComponent () ).dispose () ;
    }
    removeAll () ;
    try
    {
      super.finalize () ;
    }
    catch ( Throwable e )
    {

      // What the heck are you supposed to do if you can't finalize something?
    }
  }
} // StylePanel

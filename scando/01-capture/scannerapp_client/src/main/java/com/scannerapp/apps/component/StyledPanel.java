/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    StyledPanel.java
 * Author:  Arief Setiawan
 * Purpose: StyledPanel extend JPanel initialize method to set the default
 *          style for the menu.
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 08/05/98 asetia   Initial release
 **/
package com.scannerapp.apps.component ;

import java.awt.LayoutManager;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.scannerapp.apps.framework.StyleGuide;
import com.scannerapp.apps.framework.common.interfaces.Disposable;
public class StyledPanel
    extends JPanel
    implements Disposable
{

  /**
   * Constructor
   **/
  private static Logger log = Logger.getLogger(StyledPanel.class);//For Log4j	
  public StyledPanel ()
  {
    super () ;
//  commented by Manish Vithlani for changing Look
    //setAttributes () ;
  } //end constructor

  public void forceFocus ()
  {

  }

  public StyledPanel ( LayoutManager layout )
  {
    super ( layout ) ;
//  commented by Manish Vithlani for changing Look
    //setAttributes () ;
  } //end constructor

  private void setAttributes ()
  {
    this.setBackground ( StyleGuide.PANEL_BACKGROUND_COLOR ) ;
  }

  /**
   * returns the help key  (panel sensitive help)
   * @return string
   **/
  public String getHelpKey ()
  {
    return null ;
  } // end method

  /**
       * This method overrides setLocation in the superclass.  It is a hack required to
   * catch the situation where a "page up" command would show a blank screen that is
   * before the beginning of the panel. <BR><BR>
   * Moves this component to a new location. The top-left corner of
   * the new location is specified by the <code>x</code> and <code>y</code>
   * parameters in the coordinate space of this component's parent.
   * @param <code>x</code> The <i>x</i>-coordinate of the new location's
   * top-left corner in the parent's coordinate space.
   * @param <code>y</code> The <i>y</i>-coordinate of the new location's
   * top-left corner in the parent's coordinate space.
   * @since JDK1.1.1
   */
  public void setLocation ( int x , int y )
  {

    //   log.debug("StyledPanel.setLocation x,y " + x + " " + y +

    //     " bounds = " + this.getBounds() +"\n" +

    //     " visible Rectangle = " + this.getVisibleRect());
    int adjustedY = y ;
    int visibleRectangleHeight = this.getVisibleRect ().height ;
    if ( adjustedY > visibleRectangleHeight - 1 )
    {
      adjustedY = adjustedY - visibleRectangleHeight ;
    }

    // while the above hack works for the y (up down) direction, the below doesn't seem

    // to work for the x (left right) direction.  Don't bother to do it since it doesn't

    // work anyway.  You use Control-PageUp to go left and Control-PageDown to go right.

    // Page left has the same problem that the above fixed with page up, however, the

    // key sequence that causes it is less frequently used so it is not as big of a

    // problem.

    //    int adjustedX = x;

    //    int visibleRectangleWidth = this.getVisibleRect().width;

    //    if (adjustedX > visibleRectangleWidth-1) {

    //      adjustedX=adjustedX-visibleRectangleWidth;

    //    }

    //   log.debug("StyledPanel.setLocation adjustedX,adjustedY " + adjustedX + " " + adjustedY);
    super.setLocation ( x , adjustedY ) ;
  }

  /**
   * Removes all components from the panel
   **/
  public void dispose ()
  {
    setLayout ( null ) ;
    removeAll () ;
    try
    {
      super.finalize () ;
    }
    catch ( Throwable e )
    {

      // What the heck are you supposed to do if you can't finalize something?
    }
  } // end
} // StylePanel

/*
 com-fritolay-g2m-im-ThickCaret.java
 NOTE: This file is a generated file.
 Do not modify it by hand!
 */
package com.scannerapp.apps.framework.view ;

// add your custom import statements here
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.plaf.TextUI;
import javax.swing.text.BadLocationException;

public class ThickCaret
    extends javax.swing.text.DefaultCaret
{

  /**
   * CaretWidth
   */
  private int iCaretWidth ;
  public int getCaretWidth ()
  {
    return iCaretWidth ;
  }

  public void setCaretWidth ( int newValue )
  {
    iCaretWidth = newValue ;
  }

  public ThickCaret ()
  {
    this ( 3 ) ;
  }

  public ThickCaret ( int aWidth )
  {
    super () ;
    iCaretWidth = aWidth ;
    setBlinkRate ( 500 ) ;
  }

  public void paint ( Graphics g )
  {
    if ( isVisible () )
    {
      try
      {
        TextUI mapper = getComponent ().getUI () ;
        Rectangle r = mapper.modelToView ( getComponent () , getDot () ) ;
        g.setColor ( getComponent ().getCaretColor () ) ;

        //g.drawLine(r.x, r.y, r.x, r.y + r.height - 1);
        g.drawRect ( r.x , r.y + 1 , iCaretWidth - 1 , r.height - 2 ) ;
        g.fillRect ( r.x , r.y + 1 , iCaretWidth - 1 , r.height - 2 ) ;
      }
      catch ( BadLocationException e )
      {

        // can't render I guess

        //System.err.println("Can't render cursor");
      }
    }
  }

  protected synchronized void damage ( Rectangle r )
  {
    if ( r != null )
    {
      x = r.x - 4 ;
      y = r.y ;
      width = iCaretWidth + 10 ;
      height = r.height ;
      repaint () ;
    }
  }

// add your data members here
}

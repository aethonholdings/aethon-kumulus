package com.scannerapp.apps.component ;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;

public class BigCaret
    extends DefaultCaret
{

  // draw the caret
  public BigCaret ()
  {
    super () ;
    this.setBlinkRate ( 500 ) ;
  }

  public void paint ( Graphics g )
  {
    if ( !isVisible () )
    {
      return ;
    }
    try
    {
      JTextComponent c = getComponent () ;
      int dot = getDot () ;
      Rectangle r = c.modelToView ( dot ) ;
      g.setColor ( java.awt.Color.black ) ;
      g.fill3DRect ( r.x , r.y , 2 , 16 , true ) ;
      this.setBlinkRate ( 500 ) ;
    }
    catch ( BadLocationException e )
    {
      System.err.println ( e ) ;
    }
  }

  // specify the size of the caret for redrawing

  // and do repaint() -- this is called when the

  // caret moves
  protected synchronized void damage ( Rectangle r )
  {
    if ( r == null )
    {
      return ;
    }

    //x = r.x;
    x = r.x ;

    //y = r.y + r.height - 1;
    y = r.y ;

    //width = 4;
    width = 2 ;

    //height = 1;
    height = 16 ;
    repaint () ;
  }
}

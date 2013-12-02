package com.scannerapp.apps.component ;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.AbstractButton;

public class TableHeaderBorder
    implements javax.swing.border.Border , java.io.Serializable
{
  public Insets getBorderInsets ( Component aComp )
  {
    return new Insets ( 1 , 1 , 2 , 1 ) ;
  }

  public boolean isBorderOpaque ()
  {
    return true ;
  }

  public void paintBorder ( Component aComp , Graphics aG , int x , int y ,
                            int width , int height )
  {
    Color saveColor = aG.getColor () ;
    if ( ( aComp instanceof AbstractButton &&
           ( ( AbstractButton ) aComp ).getModel ().isPressed () ) ||
         ( aComp instanceof AbstractButton &&
           ( ( AbstractButton ) aComp ).getModel ().isSelected () ) )
    {
      aG.setColor ( darkShadow.darker () ) ;
      aG.drawRect ( x , y , width - 1 , height - 1 ) ;
    }
    else
    {
      aG.setColor ( lightShadow ) ;
      aG.drawLine ( x , y , x + width , y ) ;
      aG.drawLine ( x , y , x , y + height - 1 ) ;
      aG.setColor ( darkShadow.darker () ) ;
      aG.drawLine ( x + width - 1 , y , x + width - 1 , y + height - 2 ) ;
      aG.drawLine ( x , y + height - 2 , x + width - 1 , y + height - 2 ) ;
      aG.setColor ( darkShadow ) ;
      aG.drawLine ( x , y + height - 1 , x + width - 1 , y + height - 1 ) ;
    }
    aG.setColor ( saveColor ) ;
  }

  public TableHeaderBorder ( Color aColor )
  {
    lightShadow = aColor.brighter () ;
    darkShadow = aColor.darker () ;
  }

  private Color lightShadow ;
  private Color darkShadow ;
}

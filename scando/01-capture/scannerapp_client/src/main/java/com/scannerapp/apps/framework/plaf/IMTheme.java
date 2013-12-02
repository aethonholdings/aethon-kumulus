package com.scannerapp.apps.framework.plaf ;

import javax.swing.UIDefaults;
import javax.swing.plaf.ColorUIResource;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: System Plus P. Ltd</p>
 * @author Kanhaiya Samariya
 * @version 1.0
 */
public class IMTheme
    extends javax.swing.plaf.metal.DefaultMetalTheme
{
  private final ColorUIResource focusColor = new ColorUIResource ( 2 , 2 , 53 ) ;
  private final ColorUIResource primary1 = new ColorUIResource ( 102 , 102 ,
      153 ) ;
  private final ColorUIResource primary2 = new ColorUIResource ( 153 , 153 ,
      204 ) ;
  private final ColorUIResource primary3 = new ColorUIResource ( 204 , 204 ,
      255 ) ;
  /* Code Clean-up : Dhwanil */
//  private final ColorUIResource secondary1 = new ColorUIResource ( 102 , 102 ,
//      102 ) ;
//  private final ColorUIResource secondary2 = new ColorUIResource ( 153 , 153 ,
//      153 ) ;
//  private final ColorUIResource secondary3 = new ColorUIResource ( 204 , 204 ,
//      204 ) ;
  /**/
  public IMTheme ()
  {
    super () ;
  }

  public ColorUIResource getFocusColor ()
  {
    return focusColor ;
  }

  protected ColorUIResource getPrimary1 ()
  {
    return primary1 ;
  }

  protected ColorUIResource getPrimary2 ()
  {
    return primary2 ;
  }

  protected ColorUIResource getPrimary3 ()
  {
    return primary3 ;
  }

  public void addCustomEntriesToTable ( UIDefaults table )
  {
    super.addCustomEntriesToTable ( table ) ;

    //table.put( "Label.foreground", getBlack() );
  }

  public String getName ()
  {
    return "IM" ;
  }
}

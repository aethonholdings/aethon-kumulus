/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    StyledTitledBorder.java
 * Author:  Arief Setiawan
 * Purpose: StyledPanel extend JPanel initialize method to set the default
 *          style for the menu.
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 08/13/98 asetia   Initial release
 **/
package com.scannerapp.apps.component ;

import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class StyledTitledBorder
    extends TitledBorder
{

  /**
   * Constructor
   **/
  public StyledTitledBorder ( String title )
  {
    super ( title ) ;
//  commented by Manish Vithlani for changing Look
    //this.setTitleColor ( StyleGuide.TITLED_BORDER_COLOR ) ;
    //this.setTitleFont ( StyleGuide.TITLED_BORDER_FONT ) ;
  }

  public StyledTitledBorder ( Border border )
  {
    super ( border ) ;
//  commented by Manish Vithlani for changing Look
    //this.setTitleColor ( StyleGuide.TITLED_BORDER_COLOR ) ;
    //this.setTitleFont ( StyleGuide.TITLED_BORDER_FONT ) ;
  }

  public StyledTitledBorder ( Border border , String title )
  {
    super ( border , title ) ;
//  commented by Manish Vithlani for changing Look
    //this.setTitleColor ( StyleGuide.TITLED_BORDER_COLOR ) ;
    //this.setTitleFont ( StyleGuide.TITLED_BORDER_FONT ) ;
  }

  public StyledTitledBorder ( Border border , String title ,
                              int titleJustification , int titlePosition )
  {
    super ( border , title , titleJustification , titlePosition ) ;
//  commented by Manish Vithlani for changing Look
    //this.setTitleColor ( StyleGuide.TITLED_BORDER_COLOR ) ;
    //this.setTitleFont ( StyleGuide.TITLED_BORDER_FONT ) ;
  }

  /**
   *
   **/
  public void dispose ()
  {

  }
} // StyledTitledBorder

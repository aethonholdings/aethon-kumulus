/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    StyledDataLabel.java
 * Author:  Steve Bradford
 * Purpose: StyledDataLabel extend JLabel initialize method to set the default
 *          style for the data label.
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 09/29/98 sbradf   Initial release
 **/
package com.scannerapp.apps.component ;

import javax.swing.JLabel;

public class StyledDataLabel
    extends JLabel
{

  /**
   * Constructor
   **/
  public StyledDataLabel ()
  {
    super () ;
    //commented by Manish Vithlani for changing Look
    //this.setForeground ( StyleGuide.DATALABEL_FOREGROUND_COLOR ) ;
    //this.setFont ( StyleGuide.DATALABEL_FONT ) ;
    //this.setBorder ( StyleGuide.DATALABEL_BORDER ) ;
  }

  public StyledDataLabel ( String text )
  {
    super ( text ) ;
   //commented by Manish Vithlani for changing Look
    //this.setForeground ( StyleGuide.DATALABEL_FOREGROUND_COLOR ) ;
    //this.setFont ( StyleGuide.DATALABEL_FONT ) ;
    //this.setBorder ( StyleGuide.DATALABEL_BORDER ) ;
  }
} //SytledLabel

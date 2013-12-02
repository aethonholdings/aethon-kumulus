package com.scannerapp.apps.utils ;

import javax.swing.ImageIcon;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: System Plus P. Ltd</p>
 * @author Kanhaiya Samariya
 * @version 1.0
 */
public class FileLoader
{
  public FileLoader ()
  {

  }

  public static ImageIcon loadImage ( String aFileName )
  {
    return ( new ImageIcon ( aFileName ) ) ;
  }
}

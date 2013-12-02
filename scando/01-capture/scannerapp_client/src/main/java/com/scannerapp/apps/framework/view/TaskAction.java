package com.scannerapp.apps.framework.view ;

import java.awt.Cursor;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import com.scannerapp.apps.desktop.view.DeskTopFrame;
import com.scannerapp.apps.desktop.view.DeskTopPanel;
import com.scannerapp.apps.utils.FileLoader;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: System Plus P. Ltd</p>
 * @author Kanhaiya Samariya
 * @version 1.0
 */
public class TaskAction
    extends AbstractAction
{
  public TaskAction ()
  {
    super () ;
  }

  public TaskAction ( String name , String longDescription ,
                      ImageIcon imageIcon )
  {
    this () ;

    // Add Image Path if not present

    /*String file = "" + imageFile;
             if(!file.startsWith(IMAGE_PKG)) {
             file = IMAGE_PKG + imageFile;
             }*/

    //ImageIcon imageIcon = getImageIcon(imageFile);
    putValue ( Action.NAME , name ) ;
    putValue ( Action.LONG_DESCRIPTION , longDescription ) ;
    putValue ( Action.SHORT_DESCRIPTION , longDescription ) ;
    if ( imageIcon != null )
    {
      putValue ( Action.SMALL_ICON , imageIcon ) ;
    }
  }

  public TaskAction ( String name , String longDescription ,
                      ImageIcon imageIcon , String shortDescription )
  {
    this ( name , longDescription , imageIcon ) ;
  }

  protected void addInternalFrame ( BaseJInternalFrame frame )
  {
    try
    {
      DeskTopPanel.getInstance ().controller ().addInternalFrame ( frame ) ;
    }
    catch ( Exception e )
    {
      e.printStackTrace () ;
    }
  }

  public void setWaitCursor ()
  {
    Cursor cursor = new Cursor ( Cursor.WAIT_CURSOR ) ;
    DeskTopFrame.getInstance ().setCursor ( cursor ) ;
  }

  public void setNormalCursor ()
  {
    Cursor cursor = new Cursor ( Cursor.DEFAULT_CURSOR ) ;
    DeskTopFrame.getInstance ().setCursor ( cursor ) ;
  }

  public void actionPerformed ( ActionEvent e )
  {

  }

  public ImageIcon getImageIcon ( String filename )
  {

    //return FileLoader.loadImage( filename);

    //return FileLoader.loadImage("images/getskus20.gif");
    return FileLoader.loadImage ( "images/Notes.gif" ) ;
  }

  public void setTaskActionEnabled ( boolean enabled , String tabName )
  {
    super.setEnabled ( enabled ) ;
  }
}

package com.scannerapp.apps.component ;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.scannerapp.apps.utils.GeneralUtils;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Shuchi Sharma
 * @version 1.0
 */
public class ErrorRenderer
    extends DefaultTableCellRenderer
{
  public ErrorRenderer ()
  {
    super () ;
  }

  public Component getTableCellRendererComponent ( JTable table , Object value ,
      boolean isSelected , boolean hasFocus , int row , int column )
  {
    String error = GeneralUtils.getError () ;
    CustomAbstractTableModel model = ( CustomAbstractTableModel ) table.
        getModel () ;
    String status = ( String ) model.getValueAt ( row ,
                                                  model.getColumnCount () - 1 ) ;
    int count = -1 ;
    if ( status.equals ( "N" ) || status.equals ( "U" ) )
    {
      for ( int i = 0 ; i <= row ; i++ )
      {
        String lineStatus = ( String ) model.getValueAt ( i ,
            model.getColumnCount () - 1 ) ;
        if ( lineStatus.equals ( "N" ) || lineStatus.equals ( "U" ) )
        {
          count++ ;
        }
      }
      char[] errorBits = error.toCharArray () ;
      if ( count < errorBits.length )
      {
        char currentBit = errorBits[ count ] ;
        if ( currentBit == '1' )
        {
          setBackground ( Color.red ) ;
        }
        else
        {
          if ( currentBit == '2' )
          {
            setBackground ( Color.yellow ) ;
          }
          else
          {
            if ( currentBit == '0' )
            {
              setBackground ( table.getBackground () ) ;
            }
          }
        }
      }
      else
      {

        //setBackground(Color.cyan);
      }
    }
    else
    {
      setBackground ( table.getBackground () ) ;
    }
    super.getTableCellRendererComponent ( table , value , isSelected , hasFocus ,
                                          row , column ) ;
    return this ;
  }

  public void setUpColorRenderer ( JTable table )
  {
    table.setDefaultRenderer ( java.awt.Color.class , new ErrorRenderer () ) ;
  }

  protected void setValue ( Object obj )
  {

    //        Debug.debugLog("Object Class :: " + obj.getClass().toString());
    if ( obj.getClass ().toString ().equals ( "class java.lang.Integer" ) ||
         obj.getClass ().toString ().equals ( "class java.lang.Double" ) )
    {
      setHorizontalAlignment ( javax.swing.SwingConstants.RIGHT ) ;
      setText ( obj.toString () ) ;
    }
    else
    {
      if ( obj.getClass ().toString ().equals ( "class java.lang.String" ) )
      {
        setHorizontalAlignment ( javax.swing.SwingConstants.LEFT ) ;
        setText ( obj.toString () ) ;
      }
    }

    //        super.setValue(obj);
  }
}

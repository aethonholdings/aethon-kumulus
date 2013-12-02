package com.scannerapp.apps.component ;

import java.awt.Color;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.scannerapp.resources.IconRepository;

public class SortTableHeaderRenderer
    extends JLabel
    implements javax.swing.table.TableCellRenderer
{
  private int pressedColumn ;
  public void setPressedColumn ( int newValue )
  {
    pressedColumn = newValue ;
  }

  public void setState ( Integer obj , int col )
  {
    Integer value = null ;
    if ( ( ( Integer ) obj ).intValue () == DOWN )
    {
      value = new Integer ( UP ) ;
    }
    else
    {
      value = new Integer ( DOWN ) ;
    }
    state.put ( new Integer ( col ) , value ) ;
  }

  public java.awt.Component getTableCellRendererComponent ( JTable table ,
      Object value , boolean isSelected , boolean hasFocus , int row ,
      int column )
  {
    Object obj = state.get ( new Integer ( column ) ) ;
    if ( obj != null )
    {
      if ( ( ( Integer ) obj ).intValue () == DOWN )
      {
        setIcon ( IconRepository.DESCEND_ICON ) ;

        //setBackground(Color.blue);
        setBackground ( new Color ( 177 , 243 , 190 ) ) ;
      }
      else
      {
        setIcon ( IconRepository.ASCEND_ICON ) ;

        //setBackground(Color.blue);
        setBackground ( new Color ( 177 , 243 , 190 ) ) ;
      }
    }
    setText ( ( value == null ) ? "" : value.toString () ) ;
    return this ;
  }

  public SortTableHeaderRenderer ()
  {
    super () ;
    setForeground ( Color.black ) ;
    setOpaque ( true ) ;
    setHorizontalAlignment ( SwingConstants.CENTER ) ;
    setHorizontalTextPosition ( SwingConstants.LEFT ) ;
    setVerticalTextPosition ( SwingConstants.CENTER ) ;
    setBorder ( new TableHeaderBorder ( new Color ( 204 , 204 , 204 ) ) ) ;
    state = new Hashtable () ;
  }

  public void setSelectedColumn ( int col )
  {
    if ( col < 0 )
    {
      return ;
    }
    Integer value = null ;
    Object obj = state.get ( new Integer ( col ) ) ;
    if ( obj == null )
    {
      value = new Integer ( DOWN ) ;
    }
    else
    {
      if ( ( ( Integer ) obj ).intValue () == DOWN )
      {
        value = new Integer ( UP ) ;
      }
      else
      {
        value = new Integer ( DOWN ) ;
      }
    }
    state.clear () ;
    state.put ( new Integer ( col ) , value ) ;
  }

  public int getState ( int col )
  {
    int retValue ;
    Object obj = state.get ( new Integer ( col ) ) ;
    if ( obj == null )
    {
      retValue = NONE ;
    }
    else
    {
      if ( ( ( Integer ) obj ).intValue () == DOWN )
      {
        retValue = DOWN ;
      }
      else
      {
        retValue = UP ;
      }
    }
    return retValue ;
  }

  public static final int NONE = 0 ;
  public static final int DOWN = 1 ;
  public static final int UP = 2 ;
  private Hashtable state = null ;
}

package com.scannerapp.apps.component ;

//import com.fritolay.oode.log.ApplicationLog;//import com.fritolay.oode.util.date.FLDate;
//import com.fritolay.oode.log.ApplicationLog;
//import com.fritolay.oode.util.date.FLDate;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

//import com.fritolay.im.apps.framework.MSTLogManager;
public class StyledTableHeaderCellRenderer
    extends JTextArea
    implements TableCellRenderer
{
  public StyledTableHeaderCellRenderer ()
  {
    this ( true ) ;
  }

  /**
       * @param <em>wrapLines</em> a boolean indicating whether or not the lines should
   * wrap if there is more text than fits in the cell
   **/
  public StyledTableHeaderCellRenderer ( boolean wrapLines )
  {
    super () ;
    setOpaque ( true ) ;
    setLineWrap ( wrapLines ) ;
    setWrapStyleWord ( true ) ; // wrap on word boundaries
  }

  /**
   * draws each item based on what its value is.
   * @param table - JTable the table
   * @param value - Object the object
   * @param isSelected - boolean if the object is selected
   * @param hasFocus - boolean if the object has focus
   * @param row - int
   * @param column - int
   * @return Component - This component.  (JTextArea)
   *
   **/
  public Component getTableCellRendererComponent ( JTable table , Object value ,
      boolean isSelected , boolean hasFocus , int row , int column )
  {
    if ( table != null )
    {
      JTableHeader header = table.getTableHeader () ;
      if ( header != null )
      {
        super.setForeground ( header.getForeground () ) ;
        super.setBackground ( header.getBackground () ) ;
        super.setFont ( header.getFont () ) ;
      } // end if
    } // end if

    //this.setHorizontalAlignment(JLabel.CENTER);
    this.setBorder ( UIManager.getBorder ( "TableHeader.cellBorder" ) ) ;
    if ( value != null )
    {
      super.setText ( value.toString () ) ;
    }
    return this ;
  } // end getTableCellRendererComponent
}

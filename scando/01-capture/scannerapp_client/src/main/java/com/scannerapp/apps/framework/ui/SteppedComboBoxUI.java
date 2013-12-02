package com.scannerapp.apps.framework.ui ;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JRootPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

import org.apache.log4j.Logger;

import com.scannerapp.apps.framework.utils.JComboKeySelectionModel;
import com.scannerapp.apps.framework.utils.MultyLineListCellRenderer;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: System Plus P. Ltd</p>
 * @author Kanhaiya Samariya
 * @version 1.0
 */
public class SteppedComboBoxUI
    extends javax.swing.plaf.metal.MetalComboBoxUI
{
 private static Logger log = Logger.getLogger(SteppedComboBoxUI.class);//For Log4j
  private static int lastKeyTyped = -1 ;
  protected ComboPopup createPopup ()
  {
    if ( comboBox != null )
    {
      BasicComboPopup popup = new BasicComboPopup ( comboBox )
      {
        protected JList createList ()
        {
          EJList ejlst = new EJList ( comboBox.getModel () ) ;
          ejlst.setComboBox ( comboBox ) ;
          return ejlst ;
        }

        public void show ()
        {
          Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize () ;
          Dimension popupSize = new Dimension ( getMaxWidthOfItems () ,
                                                comboBox.getSize ().width ) ;
          popupSize.setSize ( popupSize.width ,
                              getPopupHeightForRowCount ( comboBox.
              getMaximumRowCount () ) ) ;
          Rectangle popupBounds = computePopupBounds ( 0 ,
              comboBox.getBounds ().height , popupSize.width , popupSize.height ) ;
          scroller.setMaximumSize ( popupBounds.getSize () ) ;
          scroller.setPreferredSize ( popupBounds.getSize () ) ;
          scroller.setMinimumSize ( popupBounds.getSize () ) ;
          list.invalidate () ;
          int selectedIndex = comboBox.getSelectedIndex () ;
          if ( selectedIndex == -1 )
          {
            list.clearSelection () ;
          }
          else
          {
            list.setSelectedIndex ( selectedIndex ) ;
          }
          list.ensureIndexIsVisible ( list.getSelectedIndex () ) ;
          setLightWeightPopupEnabled ( comboBox.isLightWeightPopupEnabled () ) ;
          if ( comboBox.isShowing () )
          {
            show ( comboBox , popupBounds.x , popupBounds.y ) ;
          }
        }

        public void hide ()
        {
          try
          {
            super.hide () ;
          }
          catch ( NullPointerException ex )
          {
            ex.printStackTrace();
          }
        }

        protected Rectangle computePopupBounds ( int px , int py , int pw ,
                                                 int ph )
        {
          Rectangle absBounds ;
          Rectangle r = new Rectangle ( px , py , pw , ph ) ;
          boolean inModalDialog = inModalDialog () ;

          /** Workaround for modal dialogs. See also JPopupMenu.java **/
          if ( inModalDialog )
          {
            Dialog dlg = getDialog () ;
            Point p ;
            if ( dlg instanceof JDialog )
            {
              JRootPane rp = ( ( JDialog ) dlg ).getRootPane () ;
              p = rp.getLocationOnScreen () ;
              absBounds = rp.getBounds () ;
              absBounds.x = p.x ;
              absBounds.y = p.y ;
            }
            else
            {
              absBounds = dlg.getBounds () ;
            }
            p = new Point ( absBounds.x , absBounds.y ) ;
            SwingUtilities.convertPointFromScreen ( p , comboBox ) ;
            absBounds.x = p.x ;
            absBounds.y = p.y ;
          }
          else
          {
            Point p ;
            Dimension scrSize = Toolkit.getDefaultToolkit ().getScreenSize () ;
            absBounds = new Rectangle () ;
            p = new Point ( 0 , 0 ) ;
            SwingUtilities.convertPointFromScreen ( p , comboBox ) ;
            absBounds.x = p.x ;
            absBounds.y = p.y ;
            absBounds.width = scrSize.width ;
            absBounds.height = scrSize.height ;
          }
          if ( SwingUtilities.isRectangleContainingRectangle ( absBounds , r ) )
          {
            return r ;
          }
          else
          {
            int scrX = Math.abs ( absBounds.x ) ;
            int scrY = Math.abs ( absBounds.y ) ;
            if ( ( scrX + r.width ) > absBounds.width )
            {
              r.x = ( absBounds.width ) - ( scrX + r.width + 5 ) ;
            }
            if ( ( scrY + r.height ) > absBounds.height )
            {
              r.y = -r.height ;
            }
            return r ;
          }
        }

        private Dialog getDialog ()
        {
          Container parent ;
          for ( parent = comboBox.getParent () ;
                parent != null && ! ( parent instanceof Dialog ) &&
                ! ( parent instanceof Window ) ; parent = parent.getParent () )
          {
            ;
          }
          if ( parent instanceof Dialog )
          {
            return ( Dialog ) parent ;
          }
          else
          {
            return null ;
          }
        }

        private boolean inModalDialog ()
        {
          return ( getDialog () != null ) ;
        }
      } ;
      EPopupMenuListener pml = new EPopupMenuListener ( comboBox ) ;
      popup.addPopupMenuListener ( pml ) ;
      popup.getAccessibleContext ().setAccessibleParent ( comboBox ) ;
      return popup ;
    }
    else
    {
      return super.createPopup () ;
    }
  }

  /**
   * getLastKeyTyped Method
   */
  public int getLastKeyTyped ()
  {
    return lastKeyTyped ;
  }

  /**
   * createKeyListener Method
   */
  protected KeyListener createKeyListener ()
  {
    return new KeyHandler ()
    {
      public void keyPressed ( KeyEvent e )
      {
        lastKeyTyped = e.getKeyCode () ;

        //log.debug( "createKeyListener:lastKeyTyped = " + lastKeyTyped );
        super.keyPressed ( e ) ;
      }
    } ;
  }

  public SteppedComboBoxUI ()
  {
    super () ;
    if ( comboBox != null )
    {
      comboBox.setToolTipText ( ( comboBox.getSelectedItem () != null ) ?
                                comboBox.getSelectedItem ().toString ().trim () :
                                "" ) ;
    }
  }

  private int getMaxWidthOfItems ()
  {
    int w1 = comboBox.getSize ().width ;
    int w2 = 0 ;
    int total = comboBox.getItemCount () ;
    FontMetrics fm = comboBox.getFontMetrics ( comboBox.getFont () ) ;
    for ( int i = 0 ; i < total ; i++ )
    {
      w2 = fm.stringWidth ( comboBox.getItemAt ( i ).toString ().trim () ) ;
      if ( w1 <= w2 )
      {
        w1 = w2 ;
      }
    }
    int w3 = Toolkit.getDefaultToolkit ().getScreenSize ().width ;
    if ( total > comboBox.getMaximumRowCount () )
    {
      w1 = w1 + fm.stringWidth ( "WW" ) ;
    }
    else
    {
      w1 = w1 + fm.charWidth ( 'W' ) ;
    }
    return ( w1 < w3 ) ? w1 : ( w3 - fm.charWidth ( 'W' ) ) ;
  }

  protected ItemListener createItemListener ()
  {
    return new EItemHandler ( comboBox ) ;
  }

  protected FocusListener createFocusListener ()
  {
    return new EFocusHandler ( comboBox ) ;
  }

  // add your data members here
  static class EJList
      extends JList
  {
    private JComboBox jcmb ;
    public EJList ( ListModel model )
    {
      super ( model ) ;
      MouseListener mouseListener = new MouseAdapter ()
      {
        public void mousePressed ( MouseEvent e )
        {
          SteppedComboBoxUI.lastKeyTyped = -1 ;
        }

        public void mouseReleased ( MouseEvent e )
        {
          SteppedComboBoxUI.lastKeyTyped = -1 ;
        }
      } ;
      addMouseListener ( mouseListener ) ;
      setCellRenderer ( new MultyLineListCellRenderer () ) ;
    }

    public void setComboBox ( JComboBox aComboBox )
    {
      jcmb = aComboBox ;
    }

    public void ensureIndexIsVisible ( int index )
    {
      int reqIndex = index + jcmb.getMaximumRowCount () - 1 ;
      reqIndex = ( reqIndex >= jcmb.getItemCount () ) ?
          ( jcmb.getItemCount () - 1 ) : reqIndex ;

      //log.debug( "index = " + index + ", required index = " + reqIndex );
      Rectangle cellBounds = getCellBounds ( index , reqIndex ) ;
      if ( cellBounds != null )
      {
        scrollRectToVisible ( cellBounds ) ;
      }
    }
  }

  static class EPopupMenuListener
      implements PopupMenuListener
  {
    private JComboBox comboBox ;
    public EPopupMenuListener ( JComboBox aComboBox )
    {
      comboBox = aComboBox ;
    }

    public void popupMenuCanceled ( PopupMenuEvent anEvent )
    {

    }

    public void popupMenuWillBecomeInvisible ( PopupMenuEvent anEvent )
    {

      if ( ! ( lastKeyTyped == KeyEvent.VK_ENTER ||
               lastKeyTyped == KeyEvent.VK_SPACE || lastKeyTyped == -1 ) )
      {
        lastKeyTyped = KeyEvent.VK_ENTER ;

        comboBox.setSelectedItem ( comboBox.getSelectedItem () ) ;
      }
      lastKeyTyped = -1 ;
    }

    public void popupMenuWillBecomeVisible ( PopupMenuEvent anEvent )
    {

    }
  }

  public class EItemHandler
      extends ItemHandler
  {
    private JComboBox comboBox = null ;
    public EItemHandler ( JComboBox aComboBox )
    {
      super () ;
      comboBox = aComboBox ;
    }

    public void itemStateChanged ( ItemEvent e )
    {
      if ( comboBox != null )
      {
        ComboBoxModel model = comboBox.getModel () ;
        Object v = model.getSelectedItem () ;
        comboBox.setToolTipText ( ( v != null ) ? v.toString ().trim () : "" ) ;
      }
      super.itemStateChanged ( e ) ;
    }
  }

  public class EFocusHandler
      extends FocusHandler
  {
    private JComboBox comboBox ;
    public EFocusHandler ( JComboBox aComboBox )
    {
      super () ;
      comboBox = aComboBox ;
    }

    public void focusLost ( FocusEvent event )
    {
      if ( comboBox != null )
      {
        JComboBox.KeySelectionManager km = comboBox.getKeySelectionManager () ;
        if ( km instanceof JComboKeySelectionModel )
        {
          ( ( JComboKeySelectionModel ) km ).reset () ;
        }
      }
      super.focusLost ( event ) ;
    }
  }
}

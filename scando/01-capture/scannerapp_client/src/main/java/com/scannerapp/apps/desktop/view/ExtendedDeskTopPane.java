package com.scannerapp.apps.desktop.view;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.DesktopManager;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.SwingConstants;

import com.scannerapp.apps.framework.view.BaseJInternalFrame;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: System Plus P. Ltd</p>
 * @author Kanhaiya Samariya
 * @version 1.0
 */
/*
*======================Modification History======================
*
 * Date      	Author           		 Code #        			Description of Modifications
* --------  	----------------  --------  					---------------------------
* 27/04/2005  Hardik Shah	    ExDeskTopPane#1    Change getInternalFrames() method, as it was not 
																						 returning minimised internal Frames, in Pane.
																						 
**/
public class ExtendedDeskTopPane extends JDesktopPane {
    protected int nextX; // Next X position
    protected int nextY; // Next Y position
    protected int offsetX = DEFAULT_OFFSETX;
    protected int offsetY = DEFAULT_OFFSETY;
    protected static final int DEFAULT_OFFSETX = 24;
    protected static final int DEFAULT_OFFSETY = 24;
    protected static final int UNUSED_HEIGHT = 0; //48;
    
    /**
     * getOpenInternalFrameCount Method
     */
    public int getOpenInternalFrameCount() {
        int countif = 0;
        Component[] comps = getComponents();
        int count = comps.length;
        for(int i = count - 1;i >= 0;i--) {
            Component comp = comps[i];
            if(comp instanceof JInternalFrame && comp.isVisible()) {
                countif++;
            }
        }
        return countif;
    }
    
    /**
     * closeActiveFrame Method
     */
    public void closeActiveFrame() {
        Component[] comps = getComponents();
        int count = comps.length;
        try {
            for(int i = count - 1;i >= 0;i--) {
                Component comp = comps[i];
                if(comp instanceof JInternalFrame && comp.isVisible()) {
                    if(((JInternalFrame)comp).isSelected()) {
                        ((JInternalFrame)comp).setClosed(true);
                        return ;
                    }
                }
            }
        }
        catch(java.beans.PropertyVetoException pve) {
            
        }
    }
    
    /**
     * activateTopInternalFrame Method
     */
    public void activateTopInternalFrame() {
        Component[] comps = getComponents();
        Component c = null;
        int count = comps.length;
        int pos = Integer.MAX_VALUE;
        int ifPos;
        try {
            for(int i = count - 1;i >= 0;i--) {
                Component comp = comps[i];
                if(comp instanceof JInternalFrame && comp.isVisible()) {
                    
                    //Debug.debugLog( "ExtendedDesktopPane:activateTopInternalFrame: " + comp.getClass().getName() );
                    
                    //Debug.debugLog( "getPosition( comp )" + getPosition( comp ) + ", isClosed(): " + ((JInternalFrame)comp).isClosed() );
                    ifPos = getPosition(comp);
                    if(pos > ifPos && !((JInternalFrame)comp).isClosed()) {
                        pos = ifPos;
                        c = comp;
                    }
                }
            }
            if(c != null) {
                ((JInternalFrame)c).setSelected(true);
            }
        }
        catch(java.beans.PropertyVetoException pve) {
            
        }
    }
    
    // This method allows child frames to be added with automatic cascading
    public void addCascaded(Component comp, Integer layer) {
        
        // First add the component in the correct layer
        this.add(comp, layer);
        
        // Now do the cascading
        if(comp instanceof JInternalFrame) {
            this.cascade(comp);
        }
        
        // Move it to the front
        this.moveToFront(comp);
    }
    
    // Layout all of the children of this container so that they are cascaded.
    public void cascadeAll() {
        Component[] comps = getComponents();
        int count = comps.length;
        nextX = 0;
        nextY = 0;
        for(int i = count - 1;i >= 0;i--) {
            Component comp = comps[i];
            if(comp instanceof JInternalFrame && comp.isVisible()) {
                
                //if frame is maximized set it to false
                if(((JInternalFrame)comp).isMaximum()) {
                    try {
                        ((JInternalFrame)comp).setMaximum(false);
                    }
                    catch(java.beans.PropertyVetoException pve) {
                        
                    }
                }
                if(!((JInternalFrame)comp).isClosed()) {
                    cascade(comp);
                }
            }
        }
    }
    
    // Layout all of the children of this container so that they are tiled.
    public void tileAll(int anOrientation) {
        DesktopManager manager = getDesktopManager();
        if(manager == null) {
            
            // No desktop manager - do nothing
            return ;
        }
        Component[] comps = getComponents();
        Component comp;
        int count = 0;
        
        // Count and handle only the internal frames
        for(int i = 0;i < comps.length;i++) {
            comp = comps[i];
            if(comp instanceof JInternalFrame && comp.isVisible()) {
                
                //if frame is maximized set it to false
                if(((JInternalFrame)comp).isMaximum()) {
                    try {
                        ((JInternalFrame)comp).setMaximum(false);
                    }
                    catch(java.beans.PropertyVetoException pve) {
                        
                    }
                }
                if(!((JInternalFrame)comp).isClosed()) {
                    count++;
                }
            }
        }
        if(count != 0) {
            int rows;
            int columns;
            double root = Math.sqrt((double)count);
            if(anOrientation == SwingConstants.VERTICAL) { //vertical
                rows = (int)root;
                columns = count / rows;
            }
            else { //horizontal
                columns = (int)root;
                rows = count / columns;
            }
            int spares = count - (columns * rows);
            Dimension paneSize = getSize();
            int columnWidth = paneSize.width / columns;
            
            // We leave some space at the bottom that doesn't get covered
            int availableHeight = paneSize.height - UNUSED_HEIGHT;
            int mainHeight = availableHeight / rows;
            int smallerHeight = availableHeight / (rows + 1);
            int rowHeight = mainHeight;
            int x = 0;
            int y = 0;
            int thisRow = rows;
            int normalColumns = columns - spares;
            for(int i = comps.length - 1;i >= 0;i--) {
                comp = comps[i];
                if(comp instanceof JInternalFrame && comp.isVisible()) {
                    if(!((JInternalFrame)comp).isClosed()) {
                        manager.setBoundsForFrame((JComponent)comp, x, y, columnWidth, rowHeight);
                        y += rowHeight;
                        if(--thisRow == 0) {
                            
                            // Filled the row
                            y = 0;
                            x += columnWidth;
                            
                            // Switch to smaller rows if necessary
                            if(--normalColumns <= 0) {
                                thisRow = rows + 1;
                                rowHeight = smallerHeight;
                            }
                            else {
                                thisRow = rows;
                            }
                        }
                    }
                }
            }
        }
    }
    public void setCascadeOffsets(int offsetX, int offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }
    public void setCascadeOffsets(Point pt) {
        this.offsetX = pt.x;
        this.offsetY = pt.y;
    }
    public Point getCascadeOffsets() {
        return new Point(offsetX, offsetY);
    }
    
    // Place a component so that it is cascaded relative to the previous one
    protected void cascade(Component comp) {
        Dimension paneSize = getSize();
        int targetWidth = 3 * paneSize.width / 4;
        int targetHeight = 3 * paneSize.height / 4;
        DesktopManager manager = getDesktopManager();
        if(manager == null) {
            comp.setBounds(0, 0, targetWidth, targetHeight);
            return ;
        }
        if(nextX + targetWidth > paneSize.width || nextY + targetHeight > paneSize.height) {
            nextX = 0;
            nextY = 0;
        }
        manager.setBoundsForFrame((JComponent)comp, nextX, nextY, targetWidth, targetHeight);
        nextX += offsetX;
        nextY += offsetY;
    }
    
    // Layout all of the children of this container so that they are maximized.
    public void maximizeAll() {
        Dimension paneSize = getSize();
        int targetWidth = paneSize.width;
        int targetHeight = paneSize.height - UNUSED_HEIGHT;
        Component[] comps = getComponents();
        int count = comps.length;
        DesktopManager manager = getDesktopManager();
        for(int i = count - 1;i >= 0;i--) {
            Component comp = comps[i];
            if(comp instanceof JInternalFrame && comp.isVisible()) {
                if(!((JInternalFrame)comp).isClosed()) {
                    if(manager == null) {
                        comp.setBounds(0, 0, targetWidth, targetHeight);
                        return ;
                    }
                    manager.setBoundsForFrame((JComponent)comp, 0, 0, targetWidth, targetHeight);
                }
            }
        }
    }
    public void closeAll() {
        Component[] comps = getComponents();
        int count = comps.length;
        try {
            for(int i = count - 1;i >= 0;i--) {
                Component comp = comps[i];
                if(comp instanceof JInternalFrame && comp.isVisible()) {
                    if(!((JInternalFrame)comp).isClosed()) {
                        ((JInternalFrame)comp).setClosed(true);
                    }
                }
            }
        }
        catch(java.beans.PropertyVetoException pve) {
            
        }
    }
    public JInternalFrame getJInternalFrameFor(Component aComponent) {
        Component[] comps = getComponents();
        JInternalFrame jif = null;
        int count = comps.length;
        for(int i = count - 1;i >= 0;i--) {
            Component comp = comps[i];
            if(comp instanceof JInternalFrame && comp.isVisible()) {
                jif = (JInternalFrame)comp;
                if(jif.isAncestorOf(aComponent)) {
                    break;
                }
            }
        }
        return jif;
    }
    //Start ExDeskTopPane#1
    public BaseJInternalFrame[] getInternalFrames() {
       /*Component[] components = super.getComponents();
        Vector frames = new Vector();
        for(int i = 0;i < components.length;i++) {
            if(components[i] instanceof BaseJInternalFrame) {
                frames.addElement(components[i]);
            }
        }
        BaseJInternalFrame[] internalFrames = new BaseJInternalFrame[frames.size()];
        for(int i = 0;i < frames.size();i++) {
            if(frames.elementAt(i) instanceof BaseJInternalFrame) {
                internalFrames[i] = (BaseJInternalFrame)frames.elementAt(i);
            }
        }
        return internalFrames;
       */
        JInternalFrame[] frames = getAllFrames();
        BaseJInternalFrame[] internalFrames = new BaseJInternalFrame[frames.length];
        for(int i = 0;i < frames.length;i++) {
            if(frames[i] instanceof BaseJInternalFrame) {
                internalFrames[i] = (BaseJInternalFrame)frames[i];
            }
        }
        return internalFrames;
    }
    //End ExDeskTopPane#1
    public BaseJInternalFrame getSelectedInternalFrame() {
        JInternalFrame[] frames = getAllFrames();
        for(int i = 0;i < frames.length;i++) {
            if(frames[i].isSelected() && !frames[i].isClosed() && !frames[i].isIcon()) {
                return (BaseJInternalFrame)frames[i];
            }
        }
        return null;
    }
    public BaseJInternalFrame getFirstInternalFrame() {
        JInternalFrame[] frames = getAllFrames();
        for(int i = 0;i < frames.length;i++) {
            if(!frames[i].isClosed() && !frames[i].isIcon()) {
                return (BaseJInternalFrame)frames[i];
            }
        }
        return null;
    }
    public void selectNextInternalFrame() {
        try {
            JInternalFrame[] frames = getAllFrames();
            if(frames.length > 1) {
                for(int i = frames.length - 1;i != 0;i--) {
                    if(!frames[i].isClosed() && !frames[i].isIcon()) {
                        ((JInternalFrame)frames[i]).setSelected(true);
                        return ;
                    }
                }
            }
        }
        catch(java.beans.PropertyVetoException pve) {
            
        }
    }
    public void selectFirstInternalFrame() {
        try {
            BaseJInternalFrame frame = getFirstInternalFrame();
            if(frame != null) {
                frame.setSelected(true);
            }
        }
        catch(java.beans.PropertyVetoException pve) {
            
        }
    }
}

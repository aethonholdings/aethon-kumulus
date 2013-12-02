package com.scannerapp.apps.framework.utils;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: System Plus P. Ltd</p>
 * @author Kanhaiya Samariya
 * @version 1.0
 */
public class MultyLineListCellRenderer extends javax.swing.JTextArea implements javax.swing.ListCellRenderer {
    protected EmptyBorder noFocusBorder;
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setComponentOrientation(list.getComponentOrientation());
        if(isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }
        else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        
        //if (value instanceof Icon) {
        
        //setIcon((Icon)value);
        
        //}
        
        //else {
        setText((value == null) ? "" : value.toString());
        
        //}
        setEnabled(list.isEnabled());
        setFont(list.getFont());
        setBorder((cellHasFocus) ? UIManager.getBorder("List.focusCellHighlightBorder") : noFocusBorder);
        return this;
    }
    public MultyLineListCellRenderer() {
        super();
        noFocusBorder = new EmptyBorder(1, 1, 1, 1);
        setOpaque(true);
        setBorder(noFocusBorder);
        setWrapStyleWord(true);
        setLineWrap(true);
        setEditable(false);
    }
}

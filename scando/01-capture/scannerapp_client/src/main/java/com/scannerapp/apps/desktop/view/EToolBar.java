package com.scannerapp.apps.desktop.view;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author Kanhaiya Samariya
 * @version 1.0
 */
public class EToolBar extends JToolBar {
    public EToolBar() {
        super();
        try {
            jbInit();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public JButton addAction(Action action) {
        JButton button = super.add(action);
        
        //button.setFont(StyleGuide.BUTTON_FONT);
        
        //button.setBackground(StyleGuide.BUTTON_BACKGROUND_COLOR);
        
        //button.setForeground(StyleGuide.BUTTON_FOREGROUND_COLOR);
        button.setRequestFocusEnabled(false);
        button.setOpaque(true);
        boolean enabled = this.isEnabled() & action.isEnabled();
        button.setEnabled(enabled);
        button.revalidate();
        return button;
    }
    private void jbInit() throws Exception {
        this.setFloatable(false);
    
    //this.setEnabled(true);
    }
}

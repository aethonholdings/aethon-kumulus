package com.scannerapp.apps.desktop.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.scannerapp.apps.component.StyledLabel;
import com.scannerapp.apps.component.StyledPanel;

//import com.fritolay.oode.jfc.statusPanel.DelayTimer;

//import com.fritolay.oode.jfc.statusPanel.HintTimer;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: System Plus P. Ltd</p>
 * @author Kanhaiya Samariya
 * @version 1.0
 */
public class StatusJPanel extends StyledPanel implements Runnable {
    public static final int TELETYPE_MODE = 0;
    public static final int IMMEDIATE_MODE = 1;
    private String statusText;
    private String hintText;
    private String holdText;
    
    //private HintTimer hintTimer;
    private int hintTime;
    
    //private DelayTimer delayTimer;
    private int delayTime;
    protected int percent;
    protected Color statusTextColor;
    protected Color hintTextColor;
    protected int statusTextMode;
    protected StyledLabel text;
    protected StyledLabel locationText;
    protected JProgressBar progress;
    Thread updateThread;
    public StatusJPanel() {
        statusText = "";
        hintText = "";
        holdText = "";
        hintTime = 3000;
        delayTime = 1000;
        statusTextColor = Color.black;
        hintTextColor = Color.black;
        text = new StyledLabel();
        text.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 12));
        locationText = new StyledLabel();
        locationText.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 12));
        progress = new JProgressBar();
        try {
            jbInit();
            return ;
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
    }
    private void jbInit() throws Exception {
        text.setText("                                                            ");
        text.setBorder(new EmptyBorder(3, 5, 3, 5));
        progress.setBorder(new BevelBorder(1));
        setLayout(new BorderLayout());
        setBorder(new CompoundBorder(new BevelBorder(0), new EtchedBorder(1)));
        add(text, BorderLayout.WEST);
        add(locationText, BorderLayout.EAST);
        locationText.setText("");
    }
    public void setLoginText(String text) {
        locationText.setText(text);
    }
    public void clearLoginText() {
        locationText.setText("");
    }
    public String getText() {
        return text.getText();
    }
    public Dimension getPreferredSize() {
        Font font = text.getFont();
        int i = getParent().getSize().width;
        int j = font.getSize() + 10;
        return new Dimension(i, j);
    }
    public void setStatusTextColor(Color color) {
        statusTextColor = color;
        text.setForeground(color);
        repaint();
    }
    public Color getStatusTextColor() {
        return statusTextColor;
    }
    public void setHintTextColor(Color color) {
        statusTextColor = color;
        text.setForeground(color);
        invalidate();
    }
    public Color getHintTextColor() {
        return statusTextColor;
    }
    public void setStatusTextMode(int i) {
        statusTextMode = i;
    }
    public int getStatusTextMode() {
        return statusTextMode;
    }
    public void clearStatusText() {
        setStatusText("                                                          ");
    }
    
    public void setText(String s) {
        setStatusText(s);
    }
    public void setStatusText(String s) {
        statusText = s;
        updateStatusTextDisplay();
    }
    public void setStatusText(String s, boolean flag) {
        statusText = s;
        startDelayTimer();
    }
    public void setStatusText(String s, int i) {
        setPercent(i);
        statusText = s;
        updateStatusTextDisplay();
    }
    public String getStatusText() {
        return statusText;
    }
    public void setFont(Font font) {
        super.setFont(font);
        if(text != null) {
            text.setFont(font);
        }
    }
    public void setForeground(Color color) {
        super.setForeground(color);
        if(text != null) {
            text.setForeground(color);
        }
    }
    public boolean statusTextUnchanged(String s) {
        return s.equals(statusText) || statusTextEmpty();
    }
    public boolean statusTextEmpty() {
        return statusText.equals("");
    }
    public void setHintText(String s) {
        hintText = s;
        updateHintTextDisplay();
    }
    public String getHintText() {
        return hintText;
    }
    public void setHintTime(int i) {
        hintTime = i;
    }
    public int getHintTime() {
        return hintTime;
    }
    public void setDisplayTime(int i) {
        delayTime = i;
    }
    public int getDisplayTime() {
        return delayTime;
    }
    public void setPercent(int i) {
        if(isValidPercent(i)) {
            percent = i;
        }
        updateProgress();
    }
    public int getPercent() {
        return percent;
    }
    public void setPercent(int i, String s) {
        text.setText(s);
        setPercent(i);
    }
    public void increase(int i) {
        if(isValidPercent(percent + i)) {
            percent += i;
        }
        updateProgress();
    }
    private void addProgressMeter() {
        progress.setBackground(getBackground());
        add(progress, "East");
        validate();
    }
    private void removeProgressMeter() {
        delay(500);
        remove(progress);
        validate();
    }
    private boolean isValidPercent(int i) {
        return i >= 0 && i <= 100;
    }
    private void updateProgress() {
        if(!isAncestorOf(progress)) {
            addProgressMeter();
        }
        progress.setValue(percent);
        repaintImmediately();
        if(percent >= 100) {
            removeProgressMeter();
        }
    }
    
    private void repaintImmediately() {
        Rectangle rectangle = getBounds();
        rectangle.x = 0;
        rectangle.y = 0;
        paintImmediately(rectangle);
    }
    
    private void updateStatusTextDisplay() {
        if(statusText.equals(text.getText())) {
            return ;
        }
        if(statusTextMode == 0) {
            updateThread = new Thread(this);
            updateThread.start();
            return ;
        }
        else {
            text.setText(statusText);
            return ;
        }
    }
    private void updateHintTextDisplay() {
        holdText = getStatusText();
        text.setText(hintText);
        startHintTimer();
    }
    private void startHintTimer() {
        
    
    /*if(hintTimer != null)
    hintTimer.kill();
    hintTimer = new HintTimer(this, hintTime);*/
    }
    protected void hintTimedOut() {
        
        // hintTimer = null;
        text.setText(holdText);
    }
    private void startDelayTimer() {
        
    
    /* if(delayTimer != null)
    delayTimer.kill();
    delayTimer = new DelayTimer(this, delayTime);*/
    }
    protected void delayComplete() {
        
        //delayTimer = null;
        updateStatusTextDisplay();
    }
    public void run() {
        String s = new String();
        char ac[] = statusText.toCharArray();
        for(int i = 0;i < ac.length;i++) {
            s = s + ac[i];
            text.setText(s);
            delay(6);
        }
    }
    private void delay(int i) {
        try {
            Thread.currentThread();
            Thread.sleep(i);
            return ;
        }
        catch(InterruptedException interruptedexception) {
            System.err.println("Thread interrupted!");
            interruptedexception.printStackTrace();
            return ;
        }
    }
    
    public void setStatusBarText(String s) {
        text.setText(s);
        repaintImmediately();
    }
    
    
}

package com.scannerapp.apps.framework;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Kanhaiya Samariya
 * @version 1.0
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
public class StyleGuide {
    private static final Color __lavender = new Color(180, 170, 240);
    private static final Color __navy = new Color(36, 0, 135);
    private static final Color __darkGreen = new Color(0, 150, 124);
    private static final Color __paleGreen = new Color(120, 230, 174);
//    private static final Color __paleYellow = new Color(248, 255, 120);
    private static final Color __paleGray = new Color(210, 210, 210);
    public static final Insets LABEL_TO_INPUT_INSETS = new Insets(0, 5, 0, 0);
    public static final Insets LABEL_TO_PANEL_INSETS = new Insets(0, 0, 5, 0);
    public static final Insets BUTTON_TO_BUTTON_INSETS = new Insets(0, 5, 3, 0);
    public static final Border EMPTY_BORDER = new EmptyBorder(5, 5, 5, 5);
    public static final SoftBevelBorder DATALABEL_BORDER = new SoftBevelBorder(1);
    public static Font BUTTON_FONT;
    public static Font CHECKBOX_FONT;
    public static Font DIALOG_FONT;
    public static Font LABEL_FONT;
    public static Font DATALABEL_FONT;
    public static Font LISTBOX_FONT;
    public static Font LISTBOX_ERROR_FONT;
    public static Font MENU_FONT;
    public static Font MENU_ITEM_FONT;
    public static Font MENU_TAB_FONT;
    public static Font RADIO_BUTTON_FONT;
    public static Font SUB_MENU_TAB_FONT;
    public static Font TABLE_INPUT_FONT;
    public static Font TABLE_OUTPUT_FONT;
    public static Font TABLE_INACTIVE_FONT;
    public static Font TABLE_ACTIVE_FONT;
    public static Font TABLE_LABEL_FONT;
    public static Font TEXTAREA_FONT;
    public static Font TEXTFIELD_FONT;
    public static Font TEXTFIELD_ERROR_FONT;
    public static Font TITLED_BORDER_FONT;
    public static Font TOOL_TIP_FONT;
    public static Font JLIST_FONT;
    public static Font TREE_BASIC_FONT;
    public static final Color BUTTON_COLOR = Color.lightGray;
    public static final Color BUTTON_BACKGROUND_COLOR = Color.lightGray;
    public static final Color BUTTON_FOREGROUND_COLOR = Color.black;
    public static final Color CHECKBOX_BACKGROUND_COLOR = Color.lightGray;
    public static final Color CHECKBOX_FOREGROUND_COLOR = Color.black;
    public static final Color CHECKBOX_ERROR_FOREGROUND_COLOR = Color.red;
    public static final Color CARET_COLOR = Color.black;
    public static final Color CARET_ERROR_COLOR = Color.red;
    public static final Color DESKTOP_BACKGROUND_COLOR = __lavender;
    public static final Color FRAMEWORK_PANEL_BACKGROUND_COLOR = Color.lightGray;
    public static final Color LABEL_BACKGROUND_COLOR = Color.lightGray;
    public static final Color LABEL_FOREGROUND_COLOR = Color.black;
    public static final Color DATALABEL_FOREGROUND_COLOR = Color.black;
    public static final Color LISTBOX_BACKGROUND_COLOR = Color.white;
    public static final Color LISTBOX_ERROR_BACKGROUND_COLOR = Color.pink;
    public static final Color LISTBOX_FOREGROUND_COLOR = Color.black;
    public static final Color MENU_BACKGROUND_COLOR = Color.lightGray;
    public static final Color MENU_FOREGROUND_COLOR = Color.black;
    public static final Color MENU_ITEM_BACKGROUND_COLOR = Color.lightGray;
    public static final Color MENU_ITEM_FOREGROUND_COLOR = Color.black;
    public static final Color PANEL_BACKGROUND_COLOR = Color.lightGray;
    public static final Color RADIO_BUTTON_FOREGROUND_COLOR = Color.black;
    public static final Color SUB_MENU_TAB_BACKGROUND_COLOR = Color.lightGray;
    public static final Color TABLE_ERROR_BACKGROUND_COLOR = Color.pink;
    public static final Color TABLE_FOREGROUND_COLOR = Color.black;
    public static final Color TABLE_LABEL_BACKGROUND_COLOR = Color.lightGray;
    public static final Color TABLE_LABEL_FOREGROUND_COLOR = Color.black;
    public static final Color TABLE_SELECTED_BACKGROUND_COLOR = __navy;
    public static final Color TABLE_SELECTED_FOREGROUND_COLOR = Color.white;
    public static final Color TABLE_ACTIVE_BACKGROUND_COLOR = __paleGreen;
    public static final Color TABLE_ACTIVE_FOREGROUND_COLOR = Color.black;
    public static final Color TABLE_ACTIVE2_BACKGROUND_COLOR = __darkGreen;
    public static final Color TABLE_ACTIVE2_FOREGROUND_COLOR = Color.black;
    public static final Color TABLE_INPUT_BACKGROUND_COLOR = Color.white;
    public static final Color TABLE_INPUT_FOREGROUND_COLOR = Color.black;
    public static final Color TABLE_INPUT_ACTIVE_BACKGROUND_COLOR = __paleGreen;
    public static final Color TABLE_INPUT_ACTIVE_FOREGROUND_COLOR = Color.black;
    public static final Color TABLE_OUTPUT_BACKGROUND_COLOR = Color.lightGray;
    public static final Color TABLE_OUTPUT_FOREGROUND_COLOR = Color.black;
    public static final Color TABLE_TEXTFIELD_BACKGROUND_COLOR = Color.white;
    public static final Color TABLE_TEXTFIELD_FOREGROUND_COLOR = Color.black;
    public static final Color TABLE_SELECTED_TEXTFIELD_BACKGROUND_COLOR = __navy;
    public static final Color TABLE_SELECTED_TEXTFIELD_FOREGROUND_COLOR = Color.white;
    public static final Color TEXTAREA_BACKGROUND_COLOR = Color.white;
    public static final Color TEXTAREA_FOREGROUND_COLOR = Color.black;
    public static final Color TEXTFIELD_BACKGROUND_COLOR = Color.white;
    public static final Color TEXTFIELD_DISABLED_BACKGROUND_COLOR = __paleGray;
    public static final Color TEXTFIELD_ERROR_BACKGROUND_COLOR = Color.pink;
    public static final Color TEXTFIELD_FOREGROUND_COLOR = Color.black;
    public static final Color TITLED_BORDER_COLOR = __navy;
    public static final Color TREE_SELECTED_BACKGROUND_COLOR = __navy;
    public static final Color TREE_SELECTED_FOREGROUND_COLOR = Color.white;
    public static final Color TREE_OUTPUT_BACKGROUND_COLOR = Color.lightGray;
    public static final Color TREE_OUTPUT_FOREGROUND_COLOR = Color.black;
    public static final int TREE_COMBO_OFFSET = 16;
    static {
        UIManager.put("Table.focusCellForeground", TABLE_SELECTED_FOREGROUND_COLOR);
        UIManager.put("Table.focusCellBackground", TABLE_SELECTED_BACKGROUND_COLOR);
        UIManager.put("Table.inputCellForeground", TABLE_SELECTED_FOREGROUND_COLOR);
        UIManager.put("Table.inputCellBackground", TABLE_SELECTED_BACKGROUND_COLOR);
        UIManager.put("ComboBox.disabledForeground", TEXTFIELD_FOREGROUND_COLOR);
        UIManager.put("ComboBox.disabledBackground", TEXTFIELD_DISABLED_BACKGROUND_COLOR);
    }
    public static final int DEFAULT_FRAME_LAYER = 0;
    public static final int MODAL_FRAME_LAYER = 200;
    public static void initialize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if(screenSize.width < 801) {
            setFonts("Dialog", 11);
        }
        else {
            setFonts("Dialog", 12);
        }
    }
    private static void setFonts(String face, int basicSize) {
        BUTTON_FONT = new Font(face, Font.PLAIN, basicSize);
        CHECKBOX_FONT = new Font(face, Font.PLAIN, basicSize);
        DIALOG_FONT = new Font(face, Font.PLAIN, basicSize + 1);
        LABEL_FONT = new Font(face, Font.PLAIN, basicSize);
        DATALABEL_FONT = new Font(face, Font.PLAIN, basicSize);
        LISTBOX_FONT = new Font(face, Font.PLAIN, basicSize);
        LISTBOX_ERROR_FONT = new Font(face, Font.ITALIC, basicSize);
        MENU_FONT = new Font(face, Font.PLAIN, basicSize + 1);
        MENU_ITEM_FONT = new Font(face, Font.PLAIN, basicSize);
        MENU_TAB_FONT = new Font(face, Font.BOLD, basicSize);
        RADIO_BUTTON_FONT = new Font(face, Font.PLAIN, basicSize);
        SUB_MENU_TAB_FONT = new Font(face, Font.PLAIN, basicSize + 1);
        TABLE_INPUT_FONT = new Font(face, Font.PLAIN, basicSize);
        TABLE_OUTPUT_FONT = new Font(face, Font.PLAIN, basicSize);
        TABLE_INACTIVE_FONT = new Font(face, Font.PLAIN, basicSize);
        TABLE_ACTIVE_FONT = new Font(face, Font.BOLD, basicSize);
        TABLE_LABEL_FONT = new Font(face, Font.BOLD, basicSize);
        TEXTAREA_FONT = new Font(face, Font.BOLD, basicSize);
        TEXTFIELD_FONT = new Font(face, Font.PLAIN, basicSize);
        TEXTFIELD_ERROR_FONT = new Font(face, Font.ITALIC, basicSize);
        TITLED_BORDER_FONT = new Font(face, Font.BOLD, basicSize + 1);
        TOOL_TIP_FONT = new Font(face, Font.BOLD, basicSize + 1);
        JLIST_FONT = new Font(face, Font.PLAIN, basicSize);
        TREE_BASIC_FONT = new Font(face, Font.PLAIN, basicSize + 1);
    }
}

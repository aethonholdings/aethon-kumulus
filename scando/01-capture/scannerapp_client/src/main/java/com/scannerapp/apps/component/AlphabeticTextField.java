
/**
 * © Copyright 1998 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.component
 * File:    AlphabeticTextField
 * Author:  Anna Swinney
 * Purpose:  The purpose of the AlphabeticTextField class is to provide an extension
 * of EdittedTextField that limits input characters to alphabetic characters only.
 *
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 07/16/98 aswinn   Initial release
 *
 **/
package com.scannerapp.apps.component;

/**
 * AlphabeticTextField class is an extension
 * of EdittedTextField that limits input characters to alphabetic characters only.
 * @version     1.00 - July 16, 1998
 * @author      Anna Swinney
 **/
public class AlphabeticTextField extends EdittedTextField {
    public AlphabeticTextField() {
        super();
        setAllowsOnlyLetters(true);
    }
    
    /**
     * @param <em>columns</em> the number of columns
     **/
    public AlphabeticTextField(int columns) {
        super(columns);
        setAllowsOnlyLetters(true);
    }
}

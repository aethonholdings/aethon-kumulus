
/**
 * © Copyright 1999 Frito-Lay, Inc.
 *   All Rights Reserved
 *
 * Package: mst.common.interfaces
 * File:    SelectionCriteriaInterface.java
 * Author:  Zul Khoja
 * Purpose: SelectionCriteriaInterface is the interface that specifies a criteria
 * for selection
 *
 *======================Modification History======================
 *
 * Date     ID       Description of Modifications
 * -------- ------   ----------------------------
 * 03/09/2000 zkhoja  Initial release
 **/
package com.scannerapp.apps.framework.common.interfaces;
public interface SelectionCriteriaInterface {
    
    /**
     *  This method must be Implemented to create a SelectionCriteria
     *  @return boolean, true to highlite false otherwise.
     **/
    public boolean shouldSelect();
} // end class

package com.scannerapp.apps.framework.utils;
import javax.swing.ComboBoxModel;

import org.apache.log4j.Logger;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: System Plus P. Ltd</p>
 * @author Kanhaiya Samariya
 * @version 1.0
 */
public class JComboKeySelectionModel implements javax.swing.JComboBox.KeySelectionManager {
	private static Logger log = Logger.getLogger(JComboKeySelectionModel.class);//For Log4j
    private int currentSelection = -1;
    private String searchString = "";
    boolean ESCPressed = false;
    private static final boolean DEBUG = false;
    public void setSearchString(String str) {
        searchString = "";
    }
    public int selectionForKey(char key, ComboBoxModel model) {
        int selection = -1;
        currentSelection = findIndex(model, getSelectedString(model));
        if(DEBUG) {
            log.debug("\n\nKey :: " + key);
            log.debug("Numeric Value : " + Character.getNumericValue(key));
            log.debug("Type : " + Character.getType(key));
        }
        if(Character.getType(key) != 0) {
             
             /*
             if (Character.getNumericValue(key) == -1 )
             searchString = "";
             else
             */
             {
                updateSearchString(model, key);
                int start = findIndex(model, getSelectedString(model));
                selection = search(model, start);
                
                /*
                *   If last item then search will start with the first Item
                */
                if(selection == -1 && start != 0) {
                    selection = search(model, 0);
                }
                if(DEBUG) {
                	log.debug("Key : " + key + " Start " + start + " selection = " + selection);
                }
            }
        }
        
        //if (ESCPressed)
        
        //return selection - 1;
        
        //else
        if(DEBUG) {
        	log.debug("selectionForKey: returns: " + ((selection < 0) ? currentSelection : selection));
        }
        return (selection < 0) ? currentSelection : selection;
    }
    public String getSearchString() {
        if(DEBUG) {
        	log.debug("Search String = " + searchString);
        }
        return searchString;
    }
    private int search(ComboBoxModel model, int start) {
        int searchLength = searchString.length();
        for(int i = start;i < model.getSize();i++) {
            String s = getString(model, i);
            if(s.regionMatches(true, 0, searchString, 0, searchLength)) {
                if(DEBUG) {
                	log.debug("search: returns: " + i);
                }
                return i;
            }
        }
        if(DEBUG) {
        	log.debug("search: returns: -1");
        }
        return -1;
    }
    private int findIndex(ComboBoxModel model, String find) {
        int size = model.getSize();
        if(find != null) {
            for(int i = 0;i < size;i++) {
                String s = getString(model, i);
                if(s.compareToIgnoreCase(find) == 0) /* i.e. both are same*/ {
                    if(i == size) {
                        currentSelection = size;
                        if(DEBUG) {
                        	log.debug("findIndex: returns: 0");
                        }
                        return 0;
                    }
                    else {
                        currentSelection = i;
                        if(DEBUG) {
                        	log.debug("findIndex: returns: " + i);
                        }
                        return i;
                    }
                }
            }
        }
        if(DEBUG) {
        	log.debug("findIndex: returns: 0");
        }
        return 0;
    }
    private String getString(ComboBoxModel model, int index) {
        return model.getElementAt(index).toString();
    }
    private String getSelectedString(ComboBoxModel model) {
        return model.getSelectedItem().toString();
    }
    private void updateSearchString(ComboBoxModel model, char key) {
        if(DEBUG) {
        	log.debug("Before Update search String = " + searchString);
        }
        if(DEBUG) {
        	log.debug("Numeric Value: " + Character.getNumericValue(key));
        	log.debug("Type: " + Character.getType(key));
        }
        if(Character.getNumericValue(key) == -1) {
            searchString = "";
            if(Character.getType(key) == 15) {
                ESCPressed = true;
            }
        }
        else {
            ESCPressed = false;
            searchString += key;
        }
        if(DEBUG) {
        	log.debug("After Update search String = " + searchString);
        }
    }
    public void reset() {
        searchString = "";
    }
}

package com.scannerapp.apps.framework.view ;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Hashtable;

import com.scannerapp.apps.component.StyledButton;
import com.scannerapp.apps.component.StyledLabel;
import com.scannerapp.apps.component.StyledPanel;
import com.scannerapp.apps.utils.XMLBundleReader;
import com.scannerapp.common.IMModule;
import com.scannerapp.common.IMScreen;
import com.scannerapp.common.SessionInfo;
import com.scannerapp.common.XMLBundle;
import com.scannerapp.resources.IconRepository;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Systems Plus Pvt. Ltd.</p>
 * @author Kanhaiya Samariya
 * @version 1.0
 */

/*
======================Modification History======================
Date         Author            Code #                  Description of Modifications
====	    ==============    ===============         =====================================
22/12/2009  Jiten Patel       ButtonJPanel#1          Client Test Issue#813
28/12/2009  Jiten Patel       ButtonJPanel#2          Unit Test Issue#4687
12/09/2010  Jiten Patel       ButtonJPanel#3          client Issue#1492
*/

public class ButtonJPanel
    extends StyledPanel
    implements IconRepository
{
  private String language = SessionInfo.getInstance ().getLanguage () ;
  private XMLBundleReader xmlBundleReader = new XMLBundleReader ( XMLBundle.
      BUTTON_PANEL_XMLBUNDLE ) ;
  private Hashtable hBundle = xmlBundleReader.getObjectKeyValue ( IMModule.
      COMMON_BUTTON , IMScreen.BUTTON_PANEL , language ) ;
  public StyledButton jbtnAdd = new StyledButton () ;
  public StyledButton jbtnUpdate = new StyledButton () ;
  public StyledButton jbtnDelete = new StyledButton () ;
  public StyledButton jbtnFreight = new StyledButton () ;
  public StyledButton jbtnCommit = new StyledButton () ;
  public StyledButton jbtnClose = new StyledButton () ;
  private GridBagLayout gridBagLayout1 = new GridBagLayout () ;
  public StyledLabel jlblExtra = new StyledLabel () ;
  public StyledButton jbtnComponent = new StyledButton () ;
  public StyledButton jbtnClearAll = new StyledButton () ;
  public StyledButton jbtnPrint = new StyledButton () ;
  public StyledButton jbtnBOM = new StyledButton () ;//Added for ButtonJPanel#1
  public StyledButton jbtnEditHeader = new StyledButton () ;
  public StyledButton jbtnSave = new StyledButton () ;//Added by jiten for btnsave  
  public StyledButton jbtnProduceCost = new StyledButton () ;//Added by jiten for Greece Period closing
  public StyledButton jbtnRecalculate = new StyledButton () ;//Added for ButtonJPanel#3
  
  public ButtonJPanel ()
  {
    try
    {
      jbInit () ;
      setIcon () ;
      jbtnFreight.setVisible ( false ) ;
      jbtnComponent.setVisible ( false ) ;
      readFromBundles ( hBundle ) ;
    }
    catch ( Exception e )
    {
      e.printStackTrace () ;
    }
  }

  public ButtonJPanel ( int moreButtons )
  {
    try
    {
    	 if ( moreButtons == 3 )
         {
    		 jbInitProductionReceipt();
    		 
         }else if ( moreButtons == 4 ){
        	 
        	 jbInitPhysicalInv();
        	 
         }
         else if ( moreButtons == 5 ){
        	 
        	 jbInitMiscellaneous();
        	 
         }
         else if ( moreButtons == 6 ){
        	 
        	 jbInitInventoryTrans();
        	 
         }
         else if ( moreButtons == 7 ){
        	 
        	 jbInitProductActive();
        	 
         }
         else {
        	 
        	 jbInit () ;
         }
      
      setIcon () ;
      readFromBundles ( hBundle ) ;
      if ( moreButtons == IMConstants.FREIGHT_BUTTON )
      {
        jbtnComponent.setVisible ( false ) ;
      }
    }
    catch ( Exception e )
    {
      e.printStackTrace () ;
    }
  }

  public ButtonJPanel ( int moreButtons,String screenName )
  {
    try
    {
      if(SessionInfo.getInstance().getCountry().equals(SessionInfo.getInstance().getGreece_country_code())){
    	  jbInitGrc () ;
      }else{
    	  jbInit () ;  
      }
      
      setIcon () ;
      readFromBundles ( hBundle ) ;
      if ( moreButtons == IMConstants.FREIGHT_BUTTON )
      {
        jbtnComponent.setVisible ( false ) ;
      }
    }
    catch ( Exception e )
    {
      e.printStackTrace () ;
    }
  }

  
  public ButtonJPanel ( String more )
  {
    try
    {
      jbInit () ;
      jbtnFreight.setVisible ( false ) ;
      jbtnComponent.setVisible ( false ) ;
      readFromBundles ( hBundle ) ;
    }
    catch ( Exception e )
    {
      e.printStackTrace () ;
    }
  }

  public void jbInit () throws Exception
  {
    jbtnAdd.setText ( "Add" ) ;
    jbtnUpdate.setText ( "Update" ) ;
    jbtnDelete.setText ( "Delete (B)" ) ;//CHANGED BY MANISH FOR QA ISSUE#5495
    jbtnFreight.setText ( "Freight" ) ;
    jbtnCommit.setText ( "Commit" ) ;
    jbtnClose.setText ( "Close" ) ;
    jbtnComponent.setText ( "Components" ) ;
    jbtnClearAll.setText ( "Clear All" ) ;
    jbtnPrint.setText ( "Print" ) ;
    jbtnEditHeader.setText ( "Edit Header" ) ;
    jbtnSave.setText("Save");//Added by jiten for save
    
    //height of very button is changed from 0 to -5 by jiten  
    //changed coloumn from freight button by jiten for Greece Period closing   
    /*
    this.setLayout ( gridBagLayout1 ) ;
    this.add ( jbtnAdd ,
               new GridBagConstraints ( 0 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jbtnUpdate ,
               new GridBagConstraints ( 1 , 0 , 1 , 2 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jbtnDelete ,
               new GridBagConstraints ( 2 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jbtnFreight ,
               new GridBagConstraints ( 8 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
  //Added by jiten for Greece Period closing
    this.add ( jbtnProduceCost ,
            new GridBagConstraints ( 9 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
 
  //Finished by jiten for Greece Period closing
    this.add ( jbtnCommit ,
               new GridBagConstraints ( 11 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    this.add ( jbtnClose ,
               new GridBagConstraints ( 12 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    this.add ( jlblExtra ,
               new GridBagConstraints ( 3 , 0 , 1 , 1 , 1.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.HORIZONTAL ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    this.add ( jbtnComponent ,
               new GridBagConstraints ( 7 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    this.add ( jbtnClearAll ,
               new GridBagConstraints ( 6 , 0 , 1 , 2 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    this.add ( jbtnPrint ,
               new GridBagConstraints ( 10 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jbtnEditHeader ,
               new GridBagConstraints ( 5 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    
    //Added by jiten for freezing doc
    this.add ( jbtnSave ,
               new GridBagConstraints ( 4 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    //finish by jiten for freezing doc
    
    */
    
    
    
    
    
    //Changed by jiten  for Client Issue#616
    this.setLayout ( gridBagLayout1 ) ;
    this.add ( jbtnAdd ,
               new GridBagConstraints ( 0 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jbtnUpdate ,
               new GridBagConstraints ( 1 , 0 , 1 , 2 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jbtnDelete ,
               new GridBagConstraints ( 2 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jlblExtra ,
            new GridBagConstraints ( 3 , 0 , 1 , 1 , 1.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.HORIZONTAL ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    this.add ( jbtnEditHeader ,
               new GridBagConstraints ( 4 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
   /* commented for ButtonJPanel#2
    *  this.add ( jbtnFreight ,
            new GridBagConstraints ( 5 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;*/
    this.add ( jbtnSave ,
            new GridBagConstraints ( 6 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    this.add ( jbtnCommit ,
            new GridBagConstraints ( 7 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    this.add ( jbtnComponent ,
            new GridBagConstraints ( 8 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    this.add ( jbtnPrint ,
            new GridBagConstraints (9 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jbtnClose ,
            new GridBagConstraints ( 10 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    
    this.add ( jbtnProduceCost ,
            new GridBagConstraints ( 11 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jbtnClearAll ,
            new GridBagConstraints ( 12 , 0 , 1 , 2 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    //Finished by jiten  for Client Issue#616
    
    
    
    

    jbtnUpdate.setVisible ( false ) ;
    jbtnClearAll.setVisible ( false ) ;
    jbtnEditHeader.setVisible ( false ) ;
    jbtnSave.setVisible(false);//Added by jiten for freezing doc
    jbtnProduceCost.setVisible(false);//Added by jiten for Greece Period closing
  }
  
  //Added for Client Issue#616
  public void jbInitProductActive () throws Exception
  {
    jbtnAdd.setText ( "Add" ) ;
    jbtnUpdate.setText ( "Update" ) ;
    jbtnDelete.setText ( "Delete (B)" ) ;//CHANGED BY MANISH FOR QA ISSUE#5495
    jbtnFreight.setText ( "Freight" ) ;
    jbtnCommit.setText ( "Commit" ) ;
    jbtnClose.setText ( "Close" ) ;
    jbtnComponent.setText ( "Components" ) ;
    jbtnClearAll.setText ( "Clear All" ) ;
    jbtnPrint.setText ( "Print" ) ;
    jbtnEditHeader.setText ( "Edit Header" ) ;
    jbtnSave.setText("Save");//Added by jiten for save

    
    
    
    
    //Changed by jiten  for Client Issue#616
    this.setLayout ( gridBagLayout1 ) ;
    this.add ( jbtnAdd ,
               new GridBagConstraints ( 0 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jbtnUpdate ,
               new GridBagConstraints ( 1 , 0 , 1 , 2 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jbtnDelete ,
               new GridBagConstraints ( 2 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jlblExtra ,
            new GridBagConstraints ( 3 , 0 , 1 , 1 , 1.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.HORIZONTAL ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    this.add ( jbtnEditHeader ,
               new GridBagConstraints ( 4 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jbtnFreight ,
            new GridBagConstraints ( 5 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jbtnSave ,
            new GridBagConstraints ( 6 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    
    this.add ( jbtnComponent ,
            new GridBagConstraints ( 7 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    
    this.add ( jbtnCommit ,
            new GridBagConstraints ( 8 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    
    this.add ( jbtnPrint ,
            new GridBagConstraints (9 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jbtnClose ,
            new GridBagConstraints ( 10 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    
    this.add ( jbtnProduceCost ,
            new GridBagConstraints ( 11 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jbtnClearAll ,
            new GridBagConstraints ( 12 , 0 , 1 , 2 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    //Finished by jiten  for Client Issue#616
    
    
    
    

    jbtnUpdate.setVisible ( false ) ;
    jbtnClearAll.setVisible ( false ) ;
    jbtnEditHeader.setVisible ( false ) ;
    jbtnSave.setVisible(false);//Added by jiten for freezing doc
    jbtnProduceCost.setVisible(false);//Added by jiten for Greece Period closing
  }
  public void jbInitInventoryTrans () throws Exception
  {
	  jbtnAdd.setText ( "Add" ) ;
	    jbtnUpdate.setText ( "Update" ) ;
	    jbtnDelete.setText ( "Delete (B)" ) ;
	    jbtnFreight.setText ( "Freight" ) ;
	    jbtnCommit.setText ( "Commit" ) ;
	    jbtnClose.setText ( "Close" ) ;
	    jbtnComponent.setText ( "Components" ) ;
	    jbtnClearAll.setText ( "Clear All" ) ;
	    jbtnPrint.setText ( "Print" ) ;
	    jbtnEditHeader.setText ( "Edit Header" ) ;
	    jbtnSave.setText("Save");//Added by jiten for save
	    
	    
	    this.setLayout ( gridBagLayout1 ) ;
	    this.add ( jbtnAdd ,
	               new GridBagConstraints ( 0 , 0 , 1 , 1 , 0.0 , 0.0 ,
	                                        GridBagConstraints.CENTER ,
	                                        GridBagConstraints.NONE ,
	                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
	    this.add ( jbtnUpdate ,
	               new GridBagConstraints ( 1 , 0 , 1 , 2 , 0.0 , 0.0 ,
	                                        GridBagConstraints.CENTER ,
	                                        GridBagConstraints.NONE ,
	                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
	    this.add ( jbtnDelete ,
	               new GridBagConstraints ( 2 , 0 , 1 , 1 , 0.0 , 0.0 ,
	                                        GridBagConstraints.CENTER ,
	                                        GridBagConstraints.NONE ,
	                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
	    this.add ( jlblExtra ,
	            new GridBagConstraints ( 3 , 0 , 1 , 1 , 1.0 , 0.0 ,
	                                     GridBagConstraints.CENTER ,
	                                     GridBagConstraints.HORIZONTAL ,
	                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
	    this.add ( jbtnEditHeader ,
	               new GridBagConstraints ( 4 , 0 , 1 , 1 , 0.0 , 0.0 ,
	                                     GridBagConstraints.CENTER ,
	                                     GridBagConstraints.NONE ,
	                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
	   /* commented for ButtonJPanel#2
	    * this.add ( jbtnFreight ,
	            new GridBagConstraints ( 5 , 0 , 1 , 1 , 0.0 , 0.0 ,
	                                     GridBagConstraints.CENTER ,
	                                     GridBagConstraints.NONE ,
	                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;*/
	    
	    this.add ( jbtnSave ,
	            new GridBagConstraints ( 6 , 0 , 1 , 1 , 0.0 , 0.0 ,
	                                     GridBagConstraints.CENTER ,
	                                     GridBagConstraints.NONE ,
	                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
	   
	    this.add ( jbtnComponent ,
	            new GridBagConstraints ( 7, 0 , 1 , 1 , 0.0 , 0.0 ,
	                                     GridBagConstraints.CENTER ,
	                                     GridBagConstraints.NONE ,
	                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
	    
	    this.add ( jbtnCommit ,
	            new GridBagConstraints ( 8 , 0 , 1 , 1 , 0.0 , 0.0 ,
	                                     GridBagConstraints.CENTER ,
	                                     GridBagConstraints.NONE ,
	                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
	    
	    this.add ( jbtnProduceCost ,
	            new GridBagConstraints ( 9 , 0 , 1 , 1 , 0.0 , 0.0 ,
	                                     GridBagConstraints.CENTER ,
	                                     GridBagConstraints.NONE ,
	                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
	    this.add ( jbtnClearAll ,
	            new GridBagConstraints ( 10 , 0 , 1 , 2 , 0.0 , 0.0 ,
	                                     GridBagConstraints.CENTER ,
	                                     GridBagConstraints.NONE ,
	                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
	    
	    this.add ( jbtnPrint ,
	            new GridBagConstraints (11 , 0 , 1 , 1 , 0.0 , 0.0 ,
	                                     GridBagConstraints.CENTER ,
	                                     GridBagConstraints.NONE ,
	                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
	    this.add ( jbtnClose ,
	            new GridBagConstraints ( 12 , 0 , 1 , 1 , 0.0 , 0.0 ,
	                                     GridBagConstraints.CENTER ,
	                                     GridBagConstraints.NONE ,
	                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
	    
	    jbtnUpdate.setVisible ( false ) ;
	    jbtnClearAll.setVisible ( false ) ;
	    jbtnEditHeader.setVisible ( false ) ;
	    jbtnSave.setVisible(false);//Added by jiten for freezing doc
	    jbtnProduceCost.setVisible(false);//Added by jiten for Greece Period closing
	    
  }
  
  public void jbInitProductionReceipt () throws Exception
  {
    jbtnAdd.setText ( "Add" ) ;
    jbtnUpdate.setText ( "Update" ) ;
    jbtnDelete.setText ( "Delete (B)" ) ;
    jbtnFreight.setText ( "Freight" ) ;
    jbtnCommit.setText ( "Commit" ) ;
    jbtnClose.setText ( "Close" ) ;
    jbtnComponent.setText ( "Components" ) ;
    jbtnClearAll.setText ( "Clear All" ) ;
    jbtnPrint.setText ( "Print" ) ;
    jbtnEditHeader.setText ( "Edit Header" ) ;
    jbtnSave.setText("Save");//Added by jiten for save
    
    
    this.setLayout ( gridBagLayout1 ) ;
    this.add ( jbtnAdd ,
               new GridBagConstraints ( 0 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jbtnUpdate ,
               new GridBagConstraints ( 1 , 0 , 1 , 2 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jbtnDelete ,
               new GridBagConstraints ( 2 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jlblExtra ,
            new GridBagConstraints ( 3 , 0 , 1 , 1 , 1.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.HORIZONTAL ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    this.add ( jbtnEditHeader ,
               new GridBagConstraints ( 4 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
   /*  commented for ButtonJPanel#2
    * this.add ( jbtnFreight ,
            new GridBagConstraints ( 5 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;*/
    this.add ( jbtnClearAll ,
            new GridBagConstraints ( 6 , 0 , 1 , 2 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    
    this.add ( jbtnSave ,
            new GridBagConstraints ( 7 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    this.add ( jbtnCommit ,
            new GridBagConstraints ( 8 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    this.add ( jbtnComponent ,
            new GridBagConstraints ( 9 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    this.add ( jbtnProduceCost ,
            new GridBagConstraints ( 10 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    
    this.add ( jbtnPrint ,
            new GridBagConstraints (11 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jbtnClose ,
            new GridBagConstraints ( 12 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    
    jbtnUpdate.setVisible ( false ) ;
    jbtnClearAll.setVisible ( false ) ;
    jbtnEditHeader.setVisible ( false ) ;
    jbtnSave.setVisible(false);//Added by jiten for freezing doc
    jbtnProduceCost.setVisible(false);//Added by jiten for Greece Period closing
    
   }
  
  public void jbInitPhysicalInv () throws Exception
  {
    jbtnAdd.setText ( "Add" ) ;
    jbtnUpdate.setText ( "Update" ) ;
    jbtnDelete.setText ( "Delete (B)" ) ;
    jbtnFreight.setText ( "Freight" ) ;
    jbtnCommit.setText ( "Commit" ) ;
    jbtnClose.setText ( "Close" ) ;
    jbtnComponent.setText ( "Components" ) ;
    jbtnClearAll.setText ( "Clear All" ) ;
    jbtnPrint.setText ( "Print" ) ;
    jbtnEditHeader.setText ( "Edit Header" ) ;
    jbtnSave.setText("Save");//Added by jiten for save
    
    
    this.setLayout ( gridBagLayout1 ) ;
    this.add ( jbtnAdd ,
               new GridBagConstraints ( 0 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jbtnUpdate ,
               new GridBagConstraints ( 1 , 0 , 1 , 2 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jbtnDelete ,
               new GridBagConstraints ( 2 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jlblExtra ,
            new GridBagConstraints ( 3 , 0 , 1 , 1 , 1.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.HORIZONTAL ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    this.add ( jbtnEditHeader ,
               new GridBagConstraints ( 4 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
  
    this.add ( jbtnClearAll ,
            new GridBagConstraints ( 5 , 0 , 1 , 2 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    
    this.add ( jbtnSave ,
            new GridBagConstraints (6 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    
    this.add ( jbtnComponent ,
            new GridBagConstraints ( 8 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    
    this.add ( jbtnCommit ,
            new GridBagConstraints ( 7 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    
    this.add ( jbtnProduceCost ,
            new GridBagConstraints ( 9 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jbtnFreight ,
            new GridBagConstraints ( 10 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    
    this.add ( jbtnPrint ,
            new GridBagConstraints (11 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jbtnClose ,
            new GridBagConstraints ( 12 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    
    jbtnUpdate.setVisible ( false ) ;
    jbtnClearAll.setVisible ( false ) ;
    jbtnEditHeader.setVisible ( false ) ;
    jbtnSave.setVisible(false);//Added by jiten for freezing doc
    jbtnProduceCost.setVisible(false);//Added by jiten for Greece Period closing
    
   }
  public void jbInitMiscellaneous () throws Exception
  {
    jbtnAdd.setText ( "Add" ) ;
    jbtnUpdate.setText ( "Update" ) ;
    jbtnDelete.setText ( "Delete (B)" ) ;
    jbtnFreight.setText ( "Freight" ) ;
    jbtnCommit.setText ( "Commit" ) ;
    jbtnClose.setText ( "Close" ) ;
    jbtnComponent.setText ( "Components" ) ;
    jbtnClearAll.setText ( "Clear All" ) ;
    jbtnPrint.setText ( "Print" ) ;
    jbtnEditHeader.setText ( "Edit Header" ) ;
    jbtnSave.setText("Save");//Added by jiten for save
    
    
    this.setLayout ( gridBagLayout1 ) ;
    this.add ( jbtnAdd ,
               new GridBagConstraints ( 0 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jbtnUpdate ,
               new GridBagConstraints ( 1 , 0 , 1 , 2 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jbtnDelete ,
               new GridBagConstraints ( 2 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jlblExtra ,
            new GridBagConstraints ( 3 , 0 , 1 , 1 , 1.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.HORIZONTAL ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    this.add ( jbtnEditHeader ,
               new GridBagConstraints ( 4 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    /* commented for ButtonJPanel#2
     * this.add ( jbtnFreight ,
            new GridBagConstraints ( 5 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;*/
    
    this.add ( jbtnSave ,
            new GridBagConstraints ( 6 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
   
    this.add ( jbtnComponent ,
            new GridBagConstraints ( 7, 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    
    this.add ( jbtnCommit ,
            new GridBagConstraints ( 8 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    
    this.add ( jbtnProduceCost ,
            new GridBagConstraints ( 9 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jbtnClearAll ,
            new GridBagConstraints ( 10 , 0 , 1 , 2 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    
    this.add ( jbtnPrint ,
            new GridBagConstraints (11 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , -5 ) ) ;
    this.add ( jbtnClose ,
            new GridBagConstraints ( 12 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    
    jbtnUpdate.setVisible ( false ) ;
    jbtnClearAll.setVisible ( false ) ;
    jbtnEditHeader.setVisible ( false ) ;
    jbtnSave.setVisible(false);//Added by jiten for freezing doc
    jbtnProduceCost.setVisible(false);//Added by jiten for Greece Period closing
    
    
   }
  //finished for 
  //Added for Client Issue627
  public void jbInitGrc () throws Exception
  {
    jbtnAdd.setText ( "Add" ) ;
    jbtnUpdate.setText ( "Update" ) ;
    jbtnDelete.setText ( "Delete (B)" ) ;
    jbtnFreight.setText ( "Freight" ) ;
    jbtnCommit.setText ( "Commit" ) ;
    jbtnClose.setText ( "Close" ) ;
    jbtnComponent.setText ( "Components" ) ;
    jbtnClearAll.setText ( "Clear All" ) ;
    jbtnPrint.setText ( "Print" ) ;
    jbtnEditHeader.setText ( "Edit Header" ) ;
    jbtnSave.setText("Save");       
    
    this.setLayout ( gridBagLayout1 ) ;
    
    //Added for ButtonJPanel#3
    jbtnRecalculate.setText("Recalculate");  
    //finsihed for ButtonJPanel#3
   
    this.add ( jbtnRecalculate ,
            new GridBagConstraints ( 0 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    
    this.add ( jbtnFreight ,
               new GridBagConstraints ( 1 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
 
    this.add ( jbtnProduceCost ,
            new GridBagConstraints ( 2 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
   
  
    this.add ( jbtnClearAll ,
               new GridBagConstraints ( 3 , 0 , 1 , 2 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    
    this.add ( jbtnEditHeader ,
               new GridBagConstraints ( 4 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    
  
    this.add ( jbtnSave ,
               new GridBagConstraints ( 5 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                        GridBagConstraints.CENTER ,
                                        GridBagConstraints.NONE ,
                                        new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    
    this.add ( jbtnAdd ,
            new GridBagConstraints ( 6 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    
    this.add ( jbtnDelete ,
            new GridBagConstraints ( 7 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    
    
    this.add ( jlblExtra ,
    new GridBagConstraints ( 8 , 0 , 1 , 1 , 1.0 , 0.0 ,
                             GridBagConstraints.CENTER ,
                             GridBagConstraints.HORIZONTAL ,
                             new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;

    
    
    this.add ( jbtnComponent ,
            new GridBagConstraints ( 9 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    //Added for ButtonJPanel#1
    this.add ( jbtnBOM ,
            new GridBagConstraints ( 10, 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0) ) ;
    //finished for ButtonJPanel#1
    
    this.add ( jbtnPrint ,
            new GridBagConstraints ( 11, 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    
    this.add ( jbtnClose ,
            new GridBagConstraints ( 12 , 0 , 1 , 1 , 0.0 , 0.0 ,
                                     GridBagConstraints.CENTER ,
                                     GridBagConstraints.NONE ,
                                     new Insets ( 5 , 3 , 5 , 3 ) , 0 , 0 ) ) ;
    
    

    jbtnUpdate.setVisible ( false ) ;
    jbtnClearAll.setVisible ( false ) ;
    jbtnEditHeader.setVisible ( false ) ;
    jbtnSave.setVisible(false);
    jbtnProduceCost.setVisible(false);
    //jbtnRecalculate.setVisible(true);
  }
  //finished for Client Issue#627

  private void setIcon ()
  {
    jbtnCommit.setIcon ( SAVE_ICON ) ;
    jbtnClose.setIcon ( CANCEL_ICON ) ;
    jbtnAdd.setIcon ( ALL_ICON ) ;
    jbtnUpdate.setIcon ( REFRESH_ICON ) ;
    jbtnDelete.setIcon ( DELETE_ICON ) ;
    jbtnComponent.setIcon ( PRODUCT_ICON ) ;
    jbtnFreight.setIcon ( CASE_ICON ) ;
    jbtnPrint.setIcon ( PRINT_ICON ) ;
    jbtnEditHeader.setIcon ( IconRepository.NEW_ICON ) ;
    jbtnSave.setIcon(SAVE_ICON);//Added by jiten for freezing doc
    jbtnProduceCost.setIcon(SAVE_ICON);//Added by jiten for Greece Period closing
    jbtnBOM.setIcon(PRODUCT_ICON);//Added for ButtonJPanel#1
   
  }

  private void readFromBundles ( Hashtable hBundle )
  {
    setButtonCaption ( hBundle ) ;
    setToolTips ( hBundle ) ;
    setShortCuts ( hBundle ) ;
  }

  private void setButtonCaption ( Hashtable hBundle )
  {
    jbtnAdd.setText ( hBundle.get ( "jbtnAdd" ).toString () ) ;
    jbtnUpdate.setText ( hBundle.get ( "jbtnUpdate" ).toString () ) ;
    jbtnDelete.setText ( hBundle.get ( "jbtnDelete" ).toString () ) ;
    jbtnComponent.setText ( hBundle.get ( "jbtnComponent" ).toString () ) ;
    jbtnFreight.setText ( hBundle.get ( "jbtnFreight" ).toString () ) ;
    jbtnCommit.setText ( hBundle.get ( "jbtnCommit" ).toString () ) ;
    jbtnClose.setText ( hBundle.get ( "jbtnClose" ).toString () ) ;
    jbtnPrint.setText ( hBundle.get ( "jbtnPrint" ).toString () ) ;
    jbtnEditHeader.setText ( hBundle.get ( "jbtnEditHeader" ).toString () ) ;
    jbtnSave.setText ( hBundle.get ( "jbtnSave" ).toString () ) ;//Added by jiten freezing doc
    jbtnProduceCost.setText ( hBundle.get ( "jbtnProduceCost" ).toString () ) ;//Added by jiten for Greece Period closing
    
    jbtnBOM.setText(hBundle.get("jbtnBOM").toString());//Added for ButtonJPanel#1
    jbtnRecalculate.setText(hBundle.get("jbtnRecalculate").toString());//Added for ButtonJPanel#1
  }

  private void setShortCuts ( Hashtable hBundle )
  {
    jbtnAdd.setMnemonic ( hBundle.get ( "jbtnAddSC" ).toString ().charAt ( 0 ) ) ;
    jbtnUpdate.setMnemonic ( hBundle.get ( "jbtnUpdateSC" ).toString ().charAt (
        0 ) ) ;
    jbtnDelete.setMnemonic ( hBundle.get ( "jbtnDeleteSC" ).toString ().charAt (
        0 ) ) ;
    //jbtnComponent.setMnemonic ( hBundle.get ( "jbtnComponentSC" ).toString ().
                                //charAt ( 0 ) ) ;
    jbtnFreight.setMnemonic ( hBundle.get ( "jbtnFreightSC" ).toString ().
                              charAt ( 0 ) ) ;
    jbtnCommit.setMnemonic ( hBundle.get ( "jbtnCommitSC" ).toString ().charAt (
        0 ) ) ;
    jbtnClose.setMnemonic ( hBundle.get ( "jbtnCloseSC" ).toString ().charAt (
        0 ) ) ;
    jbtnPrint.setMnemonic ( hBundle.get ( "jbtnPrintSC" ).toString ().charAt (
        0 ) ) ;
    jbtnEditHeader.setMnemonic ( hBundle.get ( "jbtnEditHeaderSC" ).toString ().
                                 charAt ( 0 ) ) ;
    //Added by jiten for freezing doc
    jbtnSave.setMnemonic ( hBundle.get ( "jbtnSaveSC" ).toString ().
            charAt ( 0 ) ) ;
    //finish by jite 
    
  //Added by jiten for Greece Period closing
    jbtnProduceCost.setMnemonic ( hBundle.get ( "jbtnProduceCostSC" ).toString ().charAt ( 0 ) ) ;    
  //finished by jiten for Greece Period closing
    
    jbtnBOM.setMnemonic(hBundle.get("jbtnBOMSC").toString ().charAt ( 0 ));//Added for ButtonJPanel#1
    jbtnRecalculate.setText(hBundle.get("jbtnRecalculate").toString());//Added for ButtonJPanel#1
    
  }

  private void setToolTips ( Hashtable hBundle )
  {
    jbtnAdd.setToolTipText ( hBundle.get ( "jbtnAddTT" ).toString () ) ;
    jbtnUpdate.setToolTipText ( hBundle.get ( "jbtnUpdateTT" ).toString () ) ;
    jbtnDelete.setToolTipText ( hBundle.get ( "jbtnDeleteTT" ).toString () ) ;
    jbtnComponent.setToolTipText ( hBundle.get ( "jbtnComponentTT" ).toString () ) ;
    jbtnFreight.setToolTipText ( hBundle.get ( "jbtnFreightTT" ).toString () ) ;
    jbtnCommit.setToolTipText ( hBundle.get ( "jbtnCommitTT" ).toString () ) ;
    jbtnClose.setToolTipText ( hBundle.get ( "jbtnCloseTT" ).toString () ) ;
    jbtnPrint.setToolTipText ( hBundle.get ( "jbtnPrintTT" ).toString () ) ;
    jbtnEditHeader.setToolTipText ( hBundle.get ( "jbtnEditHeaderTT" ).toString () ) ;
    jbtnSave.setToolTipText ( hBundle.get ( "jbtnSaveTT" ).toString () ) ;//Added by jiten for Freezing doc
    jbtnProduceCost.setToolTipText ( hBundle.get ( "jbtnProduceCostTT" ).toString () ) ;//Added by jiten for Greece Period closing
    jbtnBOM.setToolTipText ( hBundle.get ( "jbtnBOMTT" ).toString () ) ;//Added for ButtonJPanel#1
  }
}

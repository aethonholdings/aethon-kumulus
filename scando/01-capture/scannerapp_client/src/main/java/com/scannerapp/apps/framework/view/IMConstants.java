package com.scannerapp.apps.framework.view ;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Systems Plus Pvt Ltd </p>
 * @author Kanhaiya Samariya
 * @version 1.0
 */

/*
  ======================Modification History======================
  Date        Author            Code #                  Description of Modifications
  ====				 ==============    ===============         =====================================
  09/08/2005  Pradeep Sharma    IMConst#1       				 HHC (RSH/RRS) Automatic Vs Manual & CORRUGATE Mark in RRS Change
  08/04/2006  Urvish Kothari    IMConst#2       				 To Display the PHY2 and TOA2 calls in Inv. Movement Detail Report (Ref issue 119)
  06/01/2009  Jiten Patel		IMConst#3                       To handle error of Pallate number duplication (Production Receipt) 
  17/02/2009  Jiten Patel       IMConst#4                       Greece Period closing changes
  29/07/2009  Manish Vithlani   IMConst#5                       Client Test Issue#629
  06/08/2009  Manish Vithalni   IMConst#6                       Physical Inventory Change(Client Test Change#(609,610,611,612,613,614)
  19/08/2009  Manish Vithlani   IMConst#7                       Unit Test Issue#3903
  25/08/2009  Jiten Patel       IMConst#8                       Client Test Issue#605
  09/09/2009  Manish Vithlani   IMConst#9                       Unit Test Issue#4046
  14/09/2009  Manish Vithlani   IMConst#10                       Unit Test Issue#4062
  07/01/2010  Jiten Patel       IMConst#11                       Unit Test Issue#4705 
  15/02/2010  Jiten Patel       IMConst#12                       Unit Test Issue#4965
  19/02/2010  Jiten Patel       IMConst#13                       Client Test Issue#938
  26/02/2010  Manish Vithlani   IMConst#14                       Unit Test Issue#5045
  11/03/2010  Jiten Patel       IMConst#15                       Client Test Issue#960
  22/03/2010  Jiten Patel       IMConst#16                       Client Test Issue#989
  01/04/2010  Jiten Patel       IMConst#17                       Client Test Issue#1034
  20/04/2010  Manish Vithlani   IMConst#18                       Added Billing System(Ref: Client Test Issue#1084,1085)
  19/05/2010  Manish Vithlani   IMConst#19          			 Added 2 new Constants for Greece Reports,(Unit Test Change#5531)
  04/06/2010  Jiten Patel       IMConst#20                       Client Test Issue#1295
  05/07/2010  Jiten Patel       IMConst#21                       Client Test Issue#1318
  19/10/2010  Shraddha Panchamiya IMConst#22					 Unit Test Issue#6114
  02/02/2011  Jiten Patel       IMConst#23                       QA Test Issue#13329
  09/03/2011  Jiten Patel       IMConst#24                       Client Prod Issue#73
  17/03/2011  Jiten Patel       IMConst#25                       MDB
  25-03-2011  vipul			 	IMConst#26   					 Text File Donload
  16-11-2011  Shalin Shah	 	IMConst#27						 Client Issue# 303
 */

public interface IMConstants
{
  final int ALLOW_PRECISION = 2 ;
  final int FREIGHT_BUTTON = 1 ;
  final int BOTH_BUTTON = 2 ;
  final String NEW = "NEW" ;
  final String EXISTING = "EXISTING" ;
  final String CASE = "CASE" ;
  final String EACH = "EACH" ;
  final String CADD = "CADDY" ;
  final String CHECK = "CHECK" ;
  final String CASE_CODE = "004" ;
  final String EACH_CODE = "017" ;
  final String CADD_CODE = "005" ;//Changed by manish for Changing caddy UOM as 005 from 018
  final String COMMIT = "COMMIT" ;
  final String RETRIEVE = "RETRIEVE" ;
  final String DIRECT = "DIRECT" ;

  /*these constants are defined for search criteria in document search*/
  final String IM = "IM" ;
  final String OM = "OM" ;
  final String HHC = "HHC" ;
  //start IMConst#18
  final String BILLING = "BL" ;
  //end IMConst#18
  final String NEW_FLAG = "N" ;
  final String UPDATE_FLAG = "U" ;
  final String ORIGINAL_FLAG = "O" ;
  final String TRANSFER_OUT = "TOT" ;
  final String TRANSFER_IN = "TIN" ;
  final String TRANSFER_IN_NEW = "TINW" ;
  final String ADJUST_ACKN = "TOA" ;
  final String ADJUST_ACKN_NEW = "TON" ;
  final String MISCELLANEOUS = "MI" ;
  //Added by jiten for gap2 (Auto and manual doc no.)
  final String MANUAL_MISCELLANEOUS = "MMI" ;
  final int DOC_ALREADY_EXISTS=8;
  final String MANUAL_InventoryPacking="MPCK";
  final String MANUAL_InventoryUnPacking="MUPK";
  
  final String MANUAL_TRANSFER_OUT="MTOT";
  
  //Added by manish for Non Invenotry Control TOT and TIN
  final String NONINVENTORY_TRANSFER_OUT="TOT:::TOT";
  final String MANUAL_NONINVENTORY_TRANSFER_OUT="MTOT:::TOT";
  final String NONINVENTORY_TRANSFER_IN = "TIN:::TIN" ;
  //Finish by manish for  Non Invenotry Control TOT and TIN
  
  final String MANUAL_COPACKER_TRANS_OUT = "MCTO" ;
  final String MANUAL_ITXTRANSFER_OUT="MITO";
  final String MANUAL_STALEWAREHOUSETOT = "MSTO" ;
  
  
  final String MANUAL_VOID_TRANSFER_OUT="MVTOT";
  final String MANUAL_VOID_COPACKER_TRANS_OUT = "MVCTO" ;
  final String MANUAL_VOID_ITXTRANSFER_OUT="MVITO";
  final String MANUAL_VOID_STALEWAREHOUSETOT = "MVSTO" ;
    
  //Added for IMConst#16  
  final String EXTERNAL_NUMBER_COPACKER_TRANS_OUT = "ENCTO" ;
  
  final String EXTERNAL_NUMBER_COPACKER_TRANS_IN = "ENCTI" ;
  //finished for IMConst#16
  
  final String VOID_TRANSFER_OUT="VTOT";
  final String VOID_COPACKER_TRANS_OUT = "VCTO" ;
  final String VOID_ITXTRANSFER_OUT="VITO";
  final String VOID_STALEWAREHOUSETOT = "VSTO" ;
  
  final String VOID_MISCELLANEOUS = "VMI" ;
  final String MANUAL_VOID_MISCELLANEOUS = "MVMI" ;
  //finish by jiten for gap2 (Auto and manual doc no.)
  
  //added by jiten for gap2 freezing doc
  final String MANUAL_VOID_InventoryPacking="MVPCK";
  final String MANUAL_VOID_InventoryUnPacking="MVUPK";
  final String VOID_InventoryPacking="VPCK";
  final String VOID_InventoryUnPacking="VUPK"; 
  //finish by jiten for gap2 freezing doc
  
  
  final String INVENTORYTRANSFORM = "ITX" ;
  //added by manish for gap2(Stale WareHouse)
  final String STALEWAREHOUSETOT = "STO" ;
  final String STALEWAREHOUSETIN="STI";
  final String STALEADJUST_ACKN="STA";
  final String AllADJUST_ACKN="ALLTOA";
  final String ALLTRANSFER_OUT="ALLTOT";
  final String COPACKER_ACKN = "CTA" ;  
  //added by manish for gap2(Stale WareHouse)
  //added by manish for gap4
  final String ITXTRANSFER_OUT="ITO";
  final String ITXTRANSFER_IN="ITI";
  final String ITXADJUST_ACKN="ITA";
  //added by manish for gap4
  final String COPACKER_TRANS_IN = "CTI" ;   
  final String COPACKER_TRANS_OUT = "CTO" ;
  final String ROUTEALLOCATION = "RAU" ;
  final String ROUTESHIP = "RSH" ;
  final String REVERSEROUTESHIP = "RRS" ;
  final String PRODUCTIONRECEIPT = "PRC" ;
  final String PRODUCTIONRETURN = "PRT" ;//Automatic PRT
  
  //added by manish for Manual Production Return
  final String MANUAL_PRODUCTIONRETURN = "MPRT" ;//Automatic PRT
  //Finish by manish for Manual Production Return
  //Added by jiten for gap23 Greece
  //Added by manish for Manual prc
  final String MANUAL_PRODUCTIONRECEIPT="MPRC";
  //Finish by manish for Manual prc
  final String PRODUCTIONRECEIPT_SUMMARY = "PRS" ;
  final String MANUAL_PRODUCTIONRECEIPT_SUMMARY = "MPRS" ;
  final String PRODUCTIONRECEIPTDOC = "PRO" ;
  //finish by jiten for gap23
  final String PURCHASERETURN = "PUT" ;
  final String PURCHASERECEIPT = "PUC" ;
  //added by manish for gap18
  final String InventoryTransformation="ITF";
  final String InventoryPacking="PCK";
  final String InventoryUnPacking="UPK";
  //finish by mansih for gap18
  
  //start IMConst#5
  final String InventoryPacking_samelocation="PCKL";
  final String MANUAL_InventoryPacking_samelocation="MPCKL";
  final String VOID_InventoryPacking_samelocation="VPCKL";
  final String MANUAL_VOID_InventoryPacking_samelocation="MVPCKL";
  
  final String InventoryUnPacking_samelocation="UPKL";
  final String MANUAL_InventoryUnPacking_samelocation="MUPKL";
  final String VOID_InventoryUnPacking_samelocation="VUPKL"; 
  final String MANUAL_VOID_InventoryUnPacking_samelocation="MVUPKL";
 
  //end IMConst#5
  
  //start IMConst#6
  final String GREECE_PHYSICAL_INVENTORY_CASE_VALUE="CS";
  final String GREECE_PHYSICAL_INVENTORY_CADDYEACH_VALUE="EACD";
  //end IMConst#6
  
  /*Starts : IMConst#2*/
  final String TOA2_CALL = "TOA2" ;
  final String PHY2_CALL = "PHY2" ;
  /*Ends : IMConst#2*/
  //Added by jiten for Gap35 Greece
  final String PHYSICALINVENTORY_GREECE = "LPHY" ;
  //Finish by jiten for Gap35
//added by manish for Greece Physical Inventory Implementation
  final String MANUAL_PHYSICALINVENTORY_GREECE = "MLPHY" ;
  final String MANUAL_PHYSICALINVENTORY = "MPHY" ;
//Finish by manish for Greece Physical Inventory Implementation
  final String PHYSICALINVENTORY = "PHY" ;
  final String TRANSFER_OUT_STR = "TRANSFER OUT" ;
  final String TRANSFER_IN_STR = "TRANSFER IN" ;
  final String ADJUST_ACKN_STR = "ADJUSTMENT ACKNOWLEDGEMENT" ;
  final String PURCHASE_RETURN_STR = "PURCHASE RETURN" ;
  final String PURCHASE_RECEIPT_STR = "PURCHASE RECEIPT" ;
  final String MISCELLANEOUS_STR = "MISCELLANEOUS" ;
  final String INVENTORYTRANSFORM_STR = "INVENTORY TRANSFORMATION" ;
  final String ROUTEALLOCATION_STR = "ROUTE ALLOCATION" ;
  final String ROUTESHIP_STR = "ROUTE SHIPMENT" ;
  final String REVERSEROUTESHIP_STR = "REVERSE ROUTE SHIPMENT" ;
  final String PRODUCTIONRECEIPT_STR = "PRODUCTION RECEIPT" ;
  final String PRODUCTIONRETURN_STR = "PRODUCTION RETURN" ;
  final String PHYSICALINVENTORY_STR = "PHYSICAL INVENTORY" ;
  final String ALL = "ALL" ;
  final String RECEIVED = "RECEIVED" ;
  final String PENDING_ACK = "PENDING ACK." ;
  final String SUNDAY = "SUNDAY" ;
  final String MONDAY = "MONDAY" ;
  final String TUESDAY = "TUESDAY" ;
  final String WEDNESDAY = "WEDNESDAY" ;
  final String THURSDAY = "THURSDAY" ;
  final String FRIDAY = "FRIDAY" ;
  final String SATURDAY = "SATURDAY" ;
  final String TRUE = "T" ;
  final String FALSE = "F" ;
  final String YES = "1" ;
  final String NO = "N" ;
  final String TIME_OUT = "QUERY_TIMEOUT" ;

  //Start : IMConst#1
  final String tYES = "YES" ;
  final String tMATCH = "0" ;
  final String tDIFFERENCE = "1" ;
  final String tNO = "NO" ;
  final String tCORRUGATE = "CORRUGATE" ;
  //End : IMConst#1

  /*these constants are defined for selection criteria in stock inquiry*/
  //Added by jiten for UOM
  final int ALL_NONE_CASE = 0 ;
  final int ALL_NONE_CADDY_EACH = 1 ;
  final int ALL_BRAND_CASE = 2 ;
  final int ALL_BRAND_CADDY_EACH = 3 ;
  final int ALL_SUBFAMILY_CASE = 4 ;
  final int ALL_SUBFAMILY_CADDY_EACH = 5 ;
  final int SELLCODE_NONE_CASE = 6 ;
  final int SELLCODE_NONE_CADDY_EACH = 7 ;
  final int LUCODE_NONE_CASE = 8 ;
  final int LUCODE_NONE_CADDY_EACH = 9 ;
  
  final int ALL_NONE_CASE_EACH = 10 ;
  final int ALL_BRAND_CASE_EACH = 11 ;
  final int ALL_SUBFAMILY_CASE_EACH = 12;
  final int SELLCODE_NONE_CASE_EACH = 13 ;
  final int LUCODE_NONE_CASE_EACH = 14 ;
  //Finish by jiten
  
  final int ALL_NONE = 0 ;
  final int ALL_BRAND = 1 ;
  final int ALL_SUBFAMILY = 2 ;
  final int SELLCODE_NONE = 3 ;
  final int LUCODE_NONE = 4 ;
  final String ALLOCATE = "1" ;
  final String UNALLOCATE = "2" ;
  //start IMConst#6
  final String INV_APPLY = "INV_APPLY" ;
  //end IMConst#6
  final String APPLY = "APPLY" ;
  final String CANCEL = "CANCEL" ;
  final String APPLIED = "Applied" ;
  final String CANCELLED = "Cancelled" ;
  final String COMMITTED = "Committed" ;
  final String SAVED = "Saved" ;
  final int SUCCESSFUL = 0 ;
  final int INTERNAL_ERROR = 2 ;
  final int OPMERR = 3 ;
  final int PRODUCT_ERROR = 4 ;
  final int DEADLOCK_ERROR = 23 ; // ADDED for IMConst#22

  //added by manish for negative quantity handling
  final int NEGATIVE_QUANTITY=7;
  //finish by manish for negative quantity handling
  final String SAVE_CONSTANT = "0";//Added by jiten for Gap35 greece
  final String COMMIT_CONSTANT = "1" ; //changed value from 0 to 1 by jiten for Gap35 greece
  final String APPLY_CONSTANT = "1" ;
  final String CANCEL_CONSTANT = "2" ;
  final String NOT_RECEIVED_CONSTANT = "1" ;
  final String PENDACK_CONSTANT = "2" ;
  final String RECEIVED_CONSTANT = "3" ;
  final String INV_CONTROLL_CONSTANT = "0" ;
  
  //Added by jiten for freezing doc
  final String SAVE_INV_CTRL_CONSTANT = "4" ;
  final String SAVE_NON_INV_CTRL_CONSTANT = "5" ;
  final String CANCEL_DELIVERY_NOTE_CONSTANT = "6" ;
  final String CANCEL_DELIVERY_NOTE_CONSTANT_NONINV = "7" ;
  final String CANCEL_DELIVERY_NOTE = "CDN" ;
  //finish by jiten
  
  //added by manish for qa issue#5527
  final int PHYSICAL_INVENTORY_DOCUMENT_NOT_FOUND=99;
  //Finish by manish for qa issue#5527
    
  //added by manish for Greece Physical Inventory Implementation 
  final int GREECE_AUTOMATIC_SAME_DATE_EXIST=100;
  final int GREECE_AUTOMATIC_DOES_NOT_EXIST=101;
  //start IMConst#6
  final int GREECE_MANUAL_WITH_TEAM_FINAL_CREATED=102;
  //end IMConst#6
  
  //start IMConst#7
  final int GREECE_PHY_EXIST_IN_COMMIT_STATUS=103;
  //end IMConst#7
  final int MANUAL_EXIST_IN_SAVE_STATUS=5;
  //finish by manish for Greece Physical Inventory Implementation
    
  final String FG3N = "FG3N" ;
  final String FG3Y = "FG3Y" ;
  final String EQ3Y = "EQ3Y" ;
  final String EQ3N = "EQ3N" ;
  final String INV_CTRL_FLAG = "1" ;
  final int RECORD_PER_PAGE = 30 ;
  final String JOIN_DELIMITER = "JOIN_DELIMITER" ;
  final String COST_CENTER = "CC" ;
  final String REASON_CODE = "RC" ;
  final String _PICKLIST = "01" ;
  final String _APPROVED = "02" ;
  final String _RECEIVED = "03" ;
  final String _CANCEL = "04" ;
  final String PICKLIST = "PICKLIST" ;
  final String APPROVED = "APPROVED" ;
  final int MAX_PICKSEQ = 9999 ;
  final String UNAUTHPRODUCT_FLAG = "1" ;
  final String AUTHPRODUCT_FLAG = "0" ;
  
  //Added by manish for Unit Test Issue#1804
  final String DELETE_FLAG="1";
  //Finihs by manish for Unit Test Issue#1804

  //Added by jiten for costing
  final String EXPECTED_PURCHASERECEIPT = "EPURC" ;
  final String PURCHASE_RECEIPT_QTY = "PURCQ" ;
  final String TEMPORARY_PURCHASE_VAL = "TPURCVL" ;
  final String PURCHASE_VALUE = "PURCVL" ;
  final String VOID_PURCHASE_RECEIPT_QTY = "VPURCQ" ;
  
  final String MANUAL_EXPECTED_PURCHASERECEIPT = "MEPURC" ;
  final String MANUAL_PURCHASE_RECEIPT_QTY = "MPURCQ" ;
  final String MANUAL_VOID_PURCHASE_RECEIPT_QTY = "MVPURCQ" ;
  final String VOID_TEMPORARY_PURCHASE_VAL = "VTPURCVL" ;
  
  final String REC_SPLR_DELIVERY_NT = "RCSDLNT" ;//Added for IMConst#13
  final String RET_SPLR_DELIVERY_NT = "RTSDLNT" ;//Added for IMConst#13

  final String EXPECTED_PURCHASERETURN = "EPURT" ;
  final String PURCHASE_RETURN_QTY = "ARETVQ" ;
  final String TEMP_RETURN_VALUE = "TRVL" ;
  final String RET_VENDOR_VALUE = "RETVVL" ;
  final String VOID_PURCHASE_RETURN_QTY = "VRETVQ" ;
  
  final String MANUAL_EXPECTED_PURCHASERETURN = "MEPURT" ;
  final String MANUAL_PURCHASE_RETURN_QTY = "MRETVQ" ;
  final String MANUAL_VOID_PURCHASE_RETURN_QTY = "MVRETVQ" ;
  final String VOID_TEMPORARY_PURCHASERETURN_VAL = "VTRVL" ;
  //finish by jiten for costing
  
   //Added by jiten for Period closing
   final String ROMANIA_PERIOD_CLOSING               ="RMNPC";
   final String GREECE_PERIOD_CLOSING                ="GRCPC";
   final String US_PERIOD_CLOSING                ="USPC";//Added for IMConst#4   
   final String SAVE_PREIOD_CLOSING_CONSTANT = "5" ;
   final String COMMIT_PREIOD_CLOSING_CONSTANT = "3" ;
   final String CANCEL_PREIOD_CLOSING_CONSTANT = "1" ;
   //finish by jiten 
   
   //Added for IMConst#3
   final int PALLATE_NBR_DUPLICATE=10;
   //finished for IMConst#3
   
   //ADDED BY MANISH FOR GREECE Miscellaneous Screen Implementation
   final int NEGATIVE_INVENTORY_AGINST_MISCELLANEOUS_SAVEDOCUMENT=6;
   //Finish BY MANISH FOR GREECE Miscellaneous Screen Implementation
   
   //added by manish for greece Report Implementation
   final String GRECCE_R8_REPORT = "R8_TRANSACTION_RPT" ;
   final String GRECCE_R9_REPORT = "R9_TRANSACTION_RPT" ;
   //Finish by manish for greece Report Implementation

   //Added by jiten for Reconciliation 
   final String CREDIT_RECONCILIATION_COST="CRJC";
   final String DEBIT_RECONCILIATION_COST="DRJC";
   
   final String MANUAL_PRODUCTIONRECEIPT_SUMMARY_DOC="MPRCS";    //Added for IMConst#8
   final int PRODUCTION_RECEIPT_DOC_NOT_EXIST=12;
   final int PRODUCTION_SUMMARY_IN_SAVE=13;
   final int PRODUCTION_SUMMARY_IN_COMMIT=11;
   final int PRODUCTION_RECEIPT_EXIST=14;
   
   final int PREV_DAY_PROD_SUMM_IN_SAVE=20;	//ADDED By SHALIN SHAH For IMConst#27 --- CLIENT ISSUE 303
   
   //start IMConst#9
   final int GREECE_ANOTHER_MANUAL_DOCUMENT_CREATED=15;
   // end IMConst#9
   
   //start IMConst#10
   final int NO_MANUAL_DOCUMENT_CREATED=16;
   //end IMConst#10
   
   final String HHC_BAD_UNLOAD="HBU";//Added for IMConst#11
   
   final int PRODUCTION_SUMMARY_NOT_EXIST=17;//Added for IMConst#12
   
   final String NONE_STRING="NONE"; //Added for IMConst#14
   final String ALL_STRING="ALL"; //Added for IMConst#14
   
   final int FILE_NOT_UPLOADED=18;//Added for IMConst#15
   final int FILE_NOT_VALID=19;//Added for IMConst#15
   
   final int NEGATIVE_TRANSACTION_QTY_AFTER_SELECTED_DATE=20;//Added for IMConst#17
   final int PRODUCTION_COST_PRODUCT_MESSAGE=21;
   final int PRODUCT_MESSAGE=22;
   
   //start IMConst#19
   final String REPORT_YES = "Y";
   final String REPORT_NO = "N";
	   //end IMConst#19

  final int CONCURRENCY_DOCUMENT_UPDATED_BY_USER=31;//Added for IMConst#20
  
  final int SESSION_PROCESS_IN_HHC_IM_CYCLE=32;//Added for IMConst#20
  
  final int MISCELLANEOUS_SAVEDOCUMENT=9;//Added for IMConst#21
   
  final int PRODUCT_NOT_EFFECTIVE_ON_PERIOD_START_DATE=33;//aDDED FOR IMConst#23
  
  final int UNAUTHORIZE_PRODUCT_FOUND=34;//IMConst#24
  
  final int FILE_NOT_FOUND=35;//Added for IMConst#25
  
  final int MDB_PATH_NOT_FOUND=36;//Added for IMConst#25
  
  //IMConst#26
  final int TEXT_FILE_PATH_NOT_FOUND = 37;
  final int NO_DOCUMENT_FOUND = 38;
  final int TEXT_FILE_GENERATION_INTERNAL_ERROR = 39;
  //IMConst#26
 
}
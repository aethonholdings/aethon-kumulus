package com.scannerapp.common;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Kanhaiya Samariya
 * @author Shuchi Sharma
 * @version 1.0
 */

/*
*======================Modification History======================
*
 * Date       Author           		 Code #        			Description of Modifications
* --------    ----------------  --------  			--------------------------------------
* 2-3-2005    Hardik Shah  			IMScreen#1    	New Screen Right added as FAST_ENTRY_ON_LU Ref. Issue#35,44
* 26-08-2005  Pradeep Sharma		IMScreen#2    	Consolidated IR Reports by Company(Issue#2826)
* 29-04-2008  Jiten Patel    		IMScreen#3    	set screen on user right
* 20-01-2009  Cinni Shah    		IMScreen#4    	Production Receipt Shift wise report and Physical Inventory Item to Manual Document Mapping Report for greece only
* 09-02-2009  Jiten Patel           IMScreen#5      Add new screen of Reconciliation for greece closing   
* 09-02-2009  Jiten Patel           IMScreen#6      for Production Cost Information
* 17-02-2009  Jiten Patel           IMScreen#7      Greece Period Closing Rights
* 13-03-2009  Jiten Patel           IMScreen#8      Temporary Purchase Value Report
* 19-03-2009  Manish Vithlani       IMScreen#9      Client Test Issue#29
* 25-03-2009  Jiten Patel           IMScreen#10     Client Test Issue#86
* 25-03-2009  Jiten Patel           IMScreen#11     Client Test Issue#84,64
* 13-04-2009  Jiten Patel           IMScreen#12     Romania 20a and 20b report
* 20-04-2009  Rinku Vagadia			IMScreen#13     Daily Inv. Mov. Report
* 18-05-2009  Jiten Patel           IMScreen#14     Unit Test Issue#3226
* 09-06-2009  Jiten Patel           IMScreen#15     Client Test Issue#344
* 17-06-2009  Jiten Patel           IMScreen#16     Unit Test Issue#3467
* 13-07-2009  Jiten Patel           IMScreen#17     Unit Test Issue#3546 
* 18-06-2009  Rinku Vagadia	   		IMScreen#18     Unit test Issue#3501
* 17-07-2009  Manish Vithlani       IMScreen#19     Unit Test Issue#3696
* 01-07-2009  Rinku Vagadia	        IMScreen#20     Unit test Issue#3502
* 23-07-2009  Rinku Vagadia			IMScreen#21     Client Issue#623
* 28-07-2009  Rinku Vagadia			IMScreen#22     Client Issue#608 Greece Item break down Report
* 29-07-2009  Rinku Vagadia			IMScreen#23     Unit test Issue#3789 Greece Warehouse Trail Balance Report
* 09-08-2009  Manish Vithlani       IMScreen#24     Physical Inventory Change(Client Test Change#(609,610,611,612,613,614)
* 19-08-2009  Manish Vithlani       IMScreen#25     Unit Test Change#3893
* 24-08-2009  Jiten Patel           IMScreen#26     Client Test Issue#660
* 25-08-2009  Jiten Patel           IMScreen#27     Client Test Issue#605
* 31-08-2009  Jiten Patel           IMScreen#28     Client Test Issue#603
* 02-09-2009  Manish Vithalni       IMScreen#29     Unit Test Change#3998
* 09-09-2009  Jiten Patel           IMScreen#30     Greece Report 36 and 37
* 14-09-2009  Jiten Patel           IMScreen#31     Unit Test Issue#4063
* 08-10-2009  Jiten Patel           IMScreen#32     Unit Test Issue#4259
* 08-10-2009  Jiten Patel           IMScreen#33     Unit Test Issue#4263
* 04-11-2009  Jiten Patel           IMScreen#34     Unit Test Issue#4345
* 08-12-2009  Jiten Patel           IMScreen#35     Client Issue#808
* 12-03-2010  Jiten Patel           IMScreen#36     Client Issue#960
* 31-03-2010  Jiten Patel           IMScreen#37     Client Issue#1033
* 31-03-2010  Jiten Patel           IMScreen#38     Unit Issue#4913
* 29-05-2010  Jiten Patel           IMScreen#39     Allow to create automated base on user right and PDYS back
* 19-11-2010  Jiten Patel           IMScreen#40     Client Issue#1544 
* */ 
 
public interface IMScreen {
    String DESKTOP_IM = "DeskTopJPanel";
    String LOGIN = "Login";
    String ERROR_MESSAGE = "ErrorMessage";
    String BUTTON_PANEL = "ButtonJPanel";
    String TEXTFIELD_PANEL = "TextFieldJPanel";
    String INFOFIELD_PANEL = "InfoFieldJPanel";
    String SCREEN_REPORT = "ScreenReport";
    //setting the uoa new
    /*String TRANSFER_OUT = "TransferOutJPanel";
    String INVENTORY_TRANSFORMATION = "InventoryTransformationJPanel";
    String TRANSFER_IN = "TransferInJPanel";
    String ADJUST_ACKNOWLEDGE = "AdjustAcknowledgeJPanel";
    String CO_PACKER_T_OUT = "CoPackerTOutJPanel";
    String CO_PACKER_T_IN = "CoPackerTInJPanel";
    String MISCELLANEOUS = "MiscellaneousJPanel";
    String PRODUCTION_RECEIPT = "ProductionReceiptJPanel";
    String PRODUCTION_RETURN = "ProductionReturnJPanel";
    String PHYSICAL_INVENTORY = "PhysicalInventoryJPanel";
    String PICKING_SEQUENCE = "PickingSequenceJPanel";
    String DOCUMENT_SEARCH = "DocumentSearchJPanel";
    String STOCK_INQUIRY = "StockInquiryJPanel";
    String TCOMMS_CHECKING = "TCOMMsCheckingJPanel";
    String ROUTE_ALLOCATION = "RouteAllocationJPanel";
    String ROUTE_SHIPMENT = "RouteShipmentJPanel";
    String REV_ROUTE_SHIPMENT = "ReverseRouteShipmentJPanel";*/
    String TRANSFER_OUT = "TRANSFEROUTJPANEL";
    String INVENTORY_TRANSFORMATION = "INVENTORYTRANSFORMATIONJPANEL";
    String TRANSFER_IN = "TRANSFERINJPANEL";
    String ADJUST_ACKNOWLEDGE = "ADJUSTACKNOWLEDGEJPANEL";
    String PURCHASE_RETURN = "PURCHASERETURNJPANEL";
    String PURCHASE_RECEIPT = "PURCHASERECEIPTJPANEL";
    String MISCELLANEOUS = "MISCELLANEOUSJPANEL";
    String PRODUCTION_RECEIPT = "PRODUCTIONRECEIPTJPANEL";
    String PRODUCTION_RETURN = "PRODUCTIONRETURNJPANEL";
    String PHYSICAL_INVENTORY = "PHYSICALINVENTORYJPANEL";
    String PICKING_SEQUENCE = "PICKINGSEQUENCEJPANEL";
    String DOCUMENT_SEARCH = "DOCUMENTSEARCHJPANEL";
    String STOCK_INQUIRY = "STOCKINQUIRYJPANEL";
    String TCOMMS_CHECKING = "TCOMMSCHECKINGJPANEL";
    String HHC_IM_ERROR_MANAGEMENT = "HHCIMERRORMGTJPANEL";//Added for IMScreen#40
    String LEGAL_DOCUMENT_PRINT = "LEGALDOCUMENTPRINT";//Added for IMScreen#40
    String ROUTE_ALLOCATION = "RouteAllocationJPanel";
    String ROUTE_SHIPMENT = "ROUTESHIPMENTJPANEL";
    String REV_ROUTE_SHIPMENT = "REVERSEROUTESHIPMENTJPANEL";
    String PROD_ACTIVE_INACTIVE = "ProdActiveInactiveJPanel";
    String COMMON = "Common";
    String REPORT = "Report";
    String LOCAL_MAINTENANCE = "LOCALMAINTENANCEJPANEL";
    String REASON_CODE = "REASONCODEJPANEL";
    String ACC_NO = "ACCNOJPANEL";
    String COST_CENTER = "COSTCENTERJPANEL";
    String CARRIER = "CARRIERJPANEL";
    String DEFAULT_ACC_CC = "DEFAULTACCCCJPANEL";
    String DELIVERY_NOTE = "DeliveryNoteReport";
    String FAST_ENTRY_ON_LU = "FAST_ENTRY_ON_LU"; //IMScreen#1
        //Start : IMScreen#2
        String INV_MOVEMENT_REPORT_COMPANY = "INV_MOVEMENT_REPORT_COMPANY";
		String TRANS_SUMMARY_REPORT_COMPANY = "TRANS_SUMMARY_REPORT_COMPANY";
		String MISC_SUMMARY_REPORT_COMPANY = "MISC_SUMMARY_REPORT_COMPANY";
		//End : IMScreen#2
		
		
		//Start : IMScreen#3	
		//FOR ALL TOT START
		String INV_TRANSFER_OUT = "INVTRANSFEROUTJPANEL";
	    String NONINV_TRANSFER_OUT = "NONINVTRANSFEROUTJPANEL";
	    String COPACKER_TRANSFER_OUT = "COPACKERTRANSFEROUTJPANEL";
	    String ITX_TRANSFER_OUT = "ITXTRANSFEROUTJPANEL";
	    String STALE_TRANSFER_OUT = "STALETRANSFEROUTJPANEL";
	    
	    
	    String MANUAL_TRANSFER_OUT = "MANUAL_TRANSFEROUTJPANEL";//CHANGED FOR IMScreen#32
		String MANUAL_MISCELLANEOUSJPANEL = "MANUAL_MISCELLANEOUSJPANEL";//changed for IMScreen#34
		String FINAL_PHYSICAL_INVENTORY = "FINALPHYSICALINVENTORYJPANEL";
		//FOR ALL TOT ENDS
		
		//FOR ALL TIN STARTS
		String INV_TRANSFER_IN = "INVTRANSFERINJPANEL";
	    String NONINV_TRANSFER_IN = "NONINVTRANSFERINJPANEL";
	    String COPACKER_TRANSFER_IN = "COPACKERTRANSFERINJPANEL";
	    String ITX_TRANSFER_IN = "ITXTRANSFERINJPANEL";
	    String STALE_TRANSFER_IN = "STALETRANSFERINJPANEL";
		//FOR ALL TIN ENDS
	   
	    //added by manish for costing
	    String AVERAGE_ACTUAL_COST="AVERAGEACTUALCOSTJPANEL";
	    //finish by manish for costing
	    
	    //added by manish for rights
	    String MANUAL_PRODUCTION_RECEIPT = "MANUALPRODUCTIONRECEIPTJPANEL";
	    String MANUAL_PRODUCTION_RETURN = "MANUALPRODUCTIONRETURNJPANEL";
	    String PRODUCTION_RECEIPT_SUMMARY= "PRODUCTIONRECEIPTSUMMARYJPANEL";
	    String MANUAL_PRODUCTION_RECEIPT_SUMMARY="MANUALPRODUCTIONRECEIPTSUMMARYJPNL";
	    //Finish by manish for rights

	    //added by manish for date change right
	    final String ALLOWTOCHANGEDATE="ALLOWTOCHANGEDATE";
	    //finish by manish for date change right

	    //Added by jiten for costing
	    String EXPECTED_PURCHASERECEIPT = "EXPECTEDPURCHASERECEIPT";
	    String PURCHASE_RECEIPT_QTY = "PURCHASERECEIPTQUANTITY" ;
	    String TEMPORARY_PURCHASE_VAL = "TEMPORARYPURCHASERECEIPTVALUE";
	    String PURCHASE_VALUE = "PURCHASERECEIPTVALUE";
	    String VOID_PURCHASE_RECEIPT_QTY = "VOIDPURCHASERECEIPTQUANITY";	    
	    String PURCHASERECEIPT_COMMIT = "PURCHASERECEIPTCOMMIT"; 
	  
	    String EXPECTED_PURCHASERETURN = "EXPECTEDPURCHASERETURN";
	    String PURCHASE_RETURN_QTY = "PURCHASERETURNQUANTITY" ;
	    String TEMP_RETURN_VALUE = "TEMPORARYPURCHASERETURNVALUE";
	    String PURCHASERETURN_VALUE = "PURCHASERETURNVALUE";
	    String VOID_PURCHASE_RETURN_QTY = "VOIDPURCHASERETURNQUANITY";	    
	    String PURCHASERETURN_COMMIT = "PURCHASERETURNCOMMIT"; 
	    //finish by jiten for costing
	    
	    
	    //Added by jiten for AverageActualCosting 
	    String AVERAGEACTUALCOST_COMMIT="AVERAGEACTUALCOSTCOMMIT";
	    //Finished by jiten 
	    
	    //Added by jiten for Period closing report 
	    String PERIO_CLOSING_REPORT="PERIODCLOSINGREPORT";
	    //finished by jiten 
	    
        //Added by jiten for Inventory Movement  detail report
	    //String INV_MOV_DETAIL_REPORT="INVMOVDETAILREPORT";//commented forIMScreen#38
	    //Finshed by jiten 
	    
	    //Added by jite for Inventory Sales Forecasting
	    String INV_SALES_FORECASTING_REPORT="INVSALESFORECASTINGREPORT";
	    //Finished by jiten 
	    
	    //Added by jiten for TEST#2315 
	    String AVERAGEACTUALCOST_CANCEL="AVERAGEACTUALCOSTCANCEL";
	    //Finished by jiten 
	    
	    //Added by jiten for purchase summary report
	    String PURCHASE_SUMMARY_REPORT="PURCHASESUMMARYREPORT";
	    //finished by jiten 
	    
	    //end : IMScreen#3

            //Start : IMScreen#4
	    String PRC_SHIFT_WISE_REPORT="PRCSHIFTWISEREPORT";
	    String PHY_INV_REPORT="PHYINVENTORYREPORT";
	    //End : IMScreen#4


	    //added by manish for greece physical inventory implementation
	    String PHYSICAL_INVENTORY_RECALCULATE="PHYSICALRECALCULATE";
	    //Finish by manish for greece physical inventory implementation
	    
	    //Added by jiten for Production detail report
	    String PRODUCTION_RECEIPT_DETAIL_REPORT="PRODUCTIONRECEIPTDETAILREPORT";
	    //finished by jiten 

            //Added by jiten for Manual rights
	    String ALLOW_MANUAL_DOCUMENT="ALLOWMANUALDOCUMENT";
	    //finished by jiten 

	    //added by manish for Greece Reports
	    String GREECE_REPORTS="Greports";
	    String GREECE_ITEM_TRANSACTION_REPORT="ITM_TRANS_RPT";
	    String GREECE_ITEM_TRANSACTION_REPORT_PER_TRANSACTION_TOTAL="ITM_TRANS_RPT_PER_TRANS_TOTAL";
	    String GREECE_ITEM_TRANSACTION_REPORT_PER_TRANSACTION_DETAIL="ITM_TRANS_RPT_PER_TRANS_DETAIL";
	    String GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_COPACKER="INTRANSIT_RPT_LOC_TO_COPACKER";
	    //String GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_DSDVANS_DETAILS="INTRANSIT_RPT_LOC_TO_DSDVANS_DTL";
	    //String GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_EXCLUDEDSDVANS_DETAILS="INTRANSIT_RPT_LOC_EXCLUDE_DSD_DTL";
	    //String GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_EXCLUDEDSDVANS_TOTALS="INTRANSIT_RPT_LOC_EXCLUDE_DSD_TOTAL";
	    String GREECE_ITEM_TRANSACTION_REPORT_PER_ITEM_AND_TRANSACTION_TYPE="ITM_TRANS_RPT_PER_ITM_TRANS_TYPE";
	    
	    //ON Hand Quantity Reports Rights
	    String GREECE_ON_HAND_QTY_RPT_WITH_COST="OH_QTY_RPT_PER_LOCT_WITHCOST";
	    String GREECE_ON_HAND_QTY_RPT_WITHOUT_COST="OH_QTY_RPT_PER_LOCT_WITHOUTCOST";
	    String GREECE_CUREENT_QTY_RPT_FOR_SELECTED_LOCATION="CUR_QTY_RPT_SELECTED_LOCT";
	    String GREECE_PAST_DATE_QTY_RPT_FOR_SELECTED_LOCATION="PAST_QTY_RPT_SELECTED_LOCT";
	    String GREECE_INVENTORY_COUNT_DIFFERANCE_RPT="INV_CNT_DIFFERANCE_RPT";
	    String GREECE_INVENTORY_COUNT_DIFFERANCE_RPT_TOTALS="INV_CNT_DIFFERANCE_TOTALS_RPT";
	    String GREECE_INVENTORY_COUNT_VALUE_PER_ITEM_RPT="INV_CNT_VAL_RPT_PER_ITEM";
	    String GREECE_INVENTORY_COUNT_VALUE_PER_GROUP_RPT="INV_CNT_VAL_RPT_PER_GROUP";
	    String GREECE_INVENTORY_COUNT_VALUE_PER_ITEM_PER_LOCATION_RPT="INV_CNT_VAL_RPT_PER_ITEM_PER_LOCT";
	    
	    //Stale Transaction Reports
	    String GREECE_TRANSACTION_TO_STALEWAREHOUSE_PRT_PER_LOCATION="TRANS_TO_STALE_WH_PRT_PER_LOCT";
	    String GREECE_TRANSACTION_TO_STALEWAREHOUSE_PRT_TOTALS="TRANS_TO_STALE_WH_PRT_TOTAL";
	    
	    // Transaction Book Reports
	    String GREECE_DAILY_PURCHASES_BOOK_RPT="DAILY_PURCHASES_BOOK_RPT";
	    String GREECE_DAILY_PRODUCTION_BOOK_RPT="DAILY_PRODUCTION_BOOK_RPT";
	    String GREECE_PURCHASE_VALUE_CALCULATION_RPT="PURCHASE_VALUE_CACL_RPT";
	    
	    //Trial Balance Reports
	    String GREECE_INVENTORY_TRIAL_BALANCE_RPT_WITHOUT_BALANCE="INV_TRIAL_BAL_RPT_WITHOUT_BALANCE";
	    String GREECE_INVENTORY_TRIAL_BALANCE_RPT_WITH_BALANCE="INV_TRIAL_BAL_RPT_WITH_BALANCE";
	    String GREECE_PURCHASE_TRIAL_BALANCE_RPT="PURCHASE_TRIAL_BAL_RPT";
	    String GREECE_INVENTORY_DAILY_BOOK_RPT="INVENTORY_DAILY_BOOK";
	    String GREECE_NEGATIVE_BALANCE_CHECKING_RPT="NEGATIVE_BALANCE_CHECKING_RPT";
	    
	    //COSTING Reports
	    String GREECE_INVENTORY_VALUATION_RPT="INV_VALUATION_RPT";
	    String GREECE_INVENTORY_VALUATION_RPT_PER_ITEM_CATEGORY="INV_VALUATION_RPT_PER_ITEM";
	    
	    
	    //Finish by manish for Greece Reports

	    //Added by jiten for puchase receipt greece gaps 
	    String PURCHASERECEIPT_SAVE="PURCHASERECEIPTSAVE";
	    //finished by jiten 
	    
	    //Added for IMScreen#5 
	    String RECONCILIATIONJPANEL="RECONCILIATIONJPANEL";
	    String RECONCILIATIONJPANEL_COMMIT="RECONCILIATIONJPANEL_COMMIT";
	    String RECONCILIATIONJPANEL_CANCEL="RECONCILIATIONJPANEL_CANCEL";
	    //finished for IMScreen#5
	    
	    //Added by jiten for puchase receipt greece gaps 
	    String PURCHASERETURN_SAVE="PURCHASERETURNSAVE";
	    //finished by jiten 

           // Added IMScreen#6 
	    String PRODUCTION_COST = "PRODUCTIONCOSTINFORMATIONJPANEL"; 
	    //finished IMScreen#6

	    //Added for IMScreen#7
	    String GREECE_PEROID_CLOSING="GREEKPERIODCLOSING";
	    String US_PEROID_CLOSING="USPERIODCLOSING";
	    //finished for IMScreen#7

	    //Added for IMScreen#8
	    String TEMP_PURCHASE_VALUE_REPORT="TEMPORARYPURCHASEVALUEREPORT";
	    //Finished for IMScreen#8
	    
	    //start IMScreen#9
	    String ROMANIA_MISC_DOCUMENT_PICKLIST="MISCELLANEOUS_PICKLIST";
	    String ROMANIA_MISC_DOCUMENT_COMMIT  ="MISCELLANEOUS_COMMIT";
	    //end IMScreen#9 
	    
	    //Added for IMScreen#10
	    String PRODUCTIONSUMMARYREPORT="PRODUCTIONSUMMARYREPORT";
	    //finished for IMScreen#10
	    
	    //Added for IMScreen#11
	    String PENDINGTRANSACTIONREPORT="PENDINGTRANSACTIONREPORT";
	    String PHYSICALINVSUPPORTREPORT="PHYSICALINVSUPPORTREPORT";
	    //finished for IMScreen#11
	    
	    //Added for IMScreen#12
	    String LOCATION_WISE_INV_DTL_MOV_REPORT="LOCATIONWISEINVDTLMOVREPORT";
	    //finished for IMScreen#12
	    
	    //Added for IMScreen#13
	    //String DAILY_SHIFT_REPORT="DAILYSHIFTREPORT";//commented for IMScreen#38
	    //finished for IMScreen#13
	    
	    //Added for IMScreen#14
	    String PRODUCTION_SUMMARY_SAVE="PRCSUMMARYSAVE";
	    String PRODUCTION_SUMMARY_COMMIT="PRCSUMMARYCOMMIT";
	    //finished for IMScreen#14
	    
	    //Added for IMScreen#15
	    String PRODUCTION_RECEIPT_CREATE="PRODUCTIONRECEIPTCREATE";
	    String PRODUCTION_RECEIPT_MODIFY="PRODUCTIONRECEIPTMODIFY";	    
	    //finished for IMScreen#15
	    
	    //Added for IMScreen#16
	    String RE_PRINT_DLV_NOTE="REPRINTDELIVERYNOTE";
	    //finished for IMScreen#16
	    
	    String DETAIL_STOCK_INQUIRY = "STOCKINQUIRYJPANEL";//Added for IMScreen#17
	    
  	   //Added for IMScreen#18
	    String TRUCK_MASTER = "TRUCKMASTERJPANEL";
	    //finished for IMScreen#18
	    
	    //start IMScreen#19
	    String MISCELLANEOUS_TRANSACTION_DATE_CHANGE="MISCELLANEOUSTRANSACTIONDATECHANGE";//change name for IMScreen#37
	    //end IMScreen#19

            //Added for IMScreen#20
	    String CARRIER_TRUCK_ASSIGNMENT = "CARRIERTRUCKASSIGNMENTJPANEL";
	    //finished for IMScreen#20
	    
	    //Added for IMScreen#21
	    String PERIOD_COST = "PERIODCOSTJPANEL";
	    //Finished for IMScreen#21
	    
	    //Added for IMScreen#22
	    //String GREECE_ITEM_BREAK_DOWN_RPT = "ITEM_BREAK_DOWN_RPT";//commented for IMScreen#38
	    //Finished for IMScreen#22
	    
	    //Added for IMScreen#23
	    String GREECE_STATUTORY_RPT = "GRCSTATUTORYRPT";
	    String GREECE_WHSE_BOOK_ANALYTICAL_RPT = "GRCWHSEBOOKANALYTICALRPT";
	    String GREECE_WHSE_BOOK_SUMMARY_RPT = "GRCWHSEBOOKSUMMARYRPT";
	    String GREECE_WAREHOUSE_TRAIL_BALANCE_RPT = "GRCWAREHOUSETRAILBALANCERPT";
	    //Finished for IMScreen#23
	    
	    //start IMScreen#24
	    String ALLOW_THEORYTICAL_QTY_TO_CHANGE = "ALLOWTHEORYQTYCHANGE";
	    String ALLOW_TO_POST_PHY_TRANSACTION = "ALLOWTOPOSTPHYTRANSACTION";
	    //end IMScreen#24
	    
	    //Start IMScreen#25
	    String ALLOW_TEAM_FINAL = "ALLOWTEAMFINALINPHYSICALINVENTORY";
	    //end IMScreen#25
	    
	    //start IMScreen#25
	    String ALLOW_TEAM1 = "ALLOWTEAM1INPHYSICALINVENTORY";
	    String ALLOW_TEAM2 = "ALLOWTEAM2INPHYSICALINVENTORY";
	    //end IMScreen#25
	    	    
	  //Start IMScreen#26
	    String SUMMANRY_PRODUCTION_INFO = "SUMMARYPRODUCTIONINFO";
	    //end IMScreen#26
	    
	    String MANUAL_PRODUCTION_RECEIPT_SUMMARY_DOC="MANUALPRODRCPTSUMMARYDOCJPNL";//Start IMScreen#27
	    
	    //Added for IMScreen#28
	    String DYNAMIC_TRANSACTION_CODE="ALLOWDYNAMICTRANSACTIONREPORT";//changed for IMScreen#31
	    //Finshed for IMScreen#28
	    
	    //Added for IMScreen#30
	    String GREECE_REPACKAGING_BILL_OF_MATERAIL_RPT="REPACKAGING_BILL_OF_MATERAIL_RPT";
	    String GREECE_REPACKAGING_TRANSACTION_TOTAL_RPT="REPACKAGING_TRANSACTION_TOTAL_ITEM";
	    //finished for IMScreen#30
	    
	    //Added for IMScreen#32
	    String MANUAL_PURCHASERECEIPTJPANEL="MANUAL_PURCHASERECEIPTJPANEL";
	    String MANUAL_PURCHASERETURNJPANEL="MANUAL_PURCHASERETURNJPANEL";
	    String MANUAL_INVTRANSFORMATIONJPANEL="MANUAL_INVTRANSFORMATIONJPANEL";
        String MANUAL_PHYSICALINVENTORYJPANEL="MANUAL_PHYSICALINVENTORYJPANEL";
	    //finsihed for IMScreen#32
        

        //Added for IMScreen#33
        String GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_DSDVANS_DETAILS="INTRANSIT_RPT_LOC_TO_DSDVANS_DTL";
	    String GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_EXCLUDEDSDVANS_DETAILS="INTRANSIT_RPT_LOC_EXCLUDE_DSD";
	    
	    String GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_DSDVANS_WITH_COST="INTRANSIT_LOC_TO_DSD_COST";
	    String GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_EXCLUDEDSDVANS_WITH_COST="INTRANSIT_LOC_EXCLUDE_DSD_COST";
	            
	    String GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_DSDVANS_WITH_COST_PER_ITEM="INTRANSIT_LOC_TO_DSD_COST_PER_ITEM";
	    String GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_EXCLUDEDSDVANS_WITH_COST_PER_ITEM="INTRANSIT_LOC_EXCLUD_DSD_COST_ITEM";
	    
                
        //finished for IMScreen#33
	    
	    //Added for IMScreen#35
	    String EXPECTED_PUC_PRICE_VALUE="EXPECTED_PUC_PRICE_VALUE";
	    String EXPECTED_PUT_PRICE_VALUE="EXPECTED_PUT_PRICE_VALUE";
	    //finished for IMScreen#35
	    
	    //Added for IMScreen#36
	    String PRODUCTION_COST_FILE="PRODUCTION_COST_FILE";
	    //finished for IMScreen#36
	    
	    //Added for IMScreen#39
	    final String ALLOWTOCHANGEAUTOMATEDDATE="ALLOWTOCHANGEAUTOMATEDDATE";
	    //finished for IMScreen#39
	    
	    String REASONCODESAVE = "REASONCODESAVE";
}

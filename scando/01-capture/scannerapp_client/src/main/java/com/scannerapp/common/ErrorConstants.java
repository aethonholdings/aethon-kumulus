package com.scannerapp.common;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2002
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author Kanhaiya Samariya
 * @version 1.0
 */

/*
 * ======================Modification History======================
 * 
 * Date ID Code# Description of Modifications ---------- -------------
 * --------------- -----------------------------
 * 
 * 21/08/2007 Nikunj Maniar ERROR_CONST#1 P2M Change:Prints the Company Reports
 * for passing the value as Year/Period or Month/Week.(Issue#4897) 03/03/2009
 * Jiten Patel ERROR_CONST#2 QA Issue#7818 05/03/2009 Rinku Vagadia
 * ERROR_CONST#3 No validation for item brand and item id.(Unit Testing Issue
 * 2800) 07/03/2009 Jiten Patel ERROR_CONST#4 Added for Trial Balance reports
 * 17/03/2009 Jiten Patel ERROR_CONST#5 Client Issue#68 26/03/2009 Jiten Patel
 * ERROR_CONST#6 Client Issue#74 27/03/2009 Jiten Patel ERROR_CONST#7 Test
 * Issue#2898 10-04-2009 Manish Vithlani ERROR_CONST#8 Implementing Item Brand
 * Selection From Listbox 20/05/2009 Rinku Vagadia ERROR_CONST#9 Client
 * Issue#271 18/06/2009 Jiten Patel ERROR_CONST#10 Unit Test Issue#3510
 * 19/06/2009 Jiten Patel ERROR_CONST#11 Unit Test Issue#3511 03/07/2009 Jiten
 * Patel ERROR_CONST#12 Unit Test Issue#3611 15/07/2009 Jiten Patel
 * ERROR_CONST#13 Unit Test Issue#3698 15/07/2009 Jiten Patel ERROR_CONST#14
 * Unit Test Issue#3699 16/07/2009 Jiten Patel ERROR_CONST#15 Unit Test
 * Issue#3700 30/06/2009 Rinku Vagadia ERROR_CONST#16 Unit test Issue#3501
 * 16/07/2009 Rinku Vagadia ERROR_CONST#17 Unit test Issue#3502 16/07/2009
 * Manish Vithlani ERROR_CONST#18 Unit Test Issue#3696 16/07/2009 Jiten Patel
 * ERROR_CONST#19 Unit Test Issue#3701 28/07/2009 Manish Vithlani ERROR_CONST#20
 * Client Test Issue#639 27/07/2009 Rinku Vagadia ERROR_CONST#21 Client
 * Issue#623 29/07/2009 Rinku Vagadia ERROR_CONST#22 Client Issue#608 Greece
 * Item Break Down Report 03/08/2009 Jiten Patel ERROR_CONST#23 Unit Test
 * Issue#3808 03/08/2009 Manish Vithlani ERROR_CONST#24 Unit Test Issue#3813
 * 09/08/2009 Manish Vithlani ERROR_CONST#25 Physical Inventory Change(Client
 * Test Change#(609,610,611,612,613,614) 19/08/2009 Manish Vithlani
 * ERROR_CONST#26 Unit Test Issue#3903 20/08/2009 Jiten Patel ERROR_CONST#27
 * Client Issue#648 25/08/2009 Jiten Patel ERROR_CONST#28 Client Issue#605
 * 01/09/2009 Jiten Patel ERROR_CONST#29 Client Issue#603 01/09/2009 Manish
 * Vithlani ERROR_CONST#30 Unit Test Change#3954 03/09/2009 Manish Vithlani
 * ERROR_CONST#31 Unit Test Change#3998 09/09/2009 Manish Vithlani
 * ERROR_CONST#32 Unit Test Issue#4046 13/10/2009 Jiten Patel ERROR_CONST#33 QA
 * Issue#10003 16/11/2009 Manish Vithlani ERROR_CONST#34 Unit Test Change#4400
 * 16/11/2009 Jiten Patel ERROR_CONST#35 Client Issue#801 18/11/2009 Manish
 * Vithlani ERROR_CONST#36 Client Test Change#811 19/11/2009 Jiten Patel
 * ERROR_CONST#37 Unit Test Issue#4430 23/11/2009 Jiten Patel ERROR_CONST#38
 * Client Issue#805 02/12/2009 Rinku Vagadia ERROR_CONST#39 (Change) Client
 * Issue 815 02/12/2009 Manish Vithlani ERROR_CONST#40 Qa Issue#10275 03/12/2009
 * Naimish Gohil ERROR_CONST#41 Add message when user click on close button
 * after change 08/12/2009 Jiten Patel ERROR_CONST#39 Client Issue#808
 * 11/12/2009 Jiten Patel ERROR_CONST#40 QA Issue#10360 12/12/2009 Manish
 * Vithlani ERROR_CONST#41 Client Test Change#812 13/12/2009 Manish Vithlani
 * ERROR_CONST#42 Qa Issue#10370 14/12/2009 Manish Vithlani ERROR_CONST#43 Unit
 * Test Issue#4578 08/01/2010 Jiten Patel ERROR_CONST#44 Unit Test Issue#4754
 * 08/01/2010 Jiten Patel ERROR_CONST#45 Unit Test Issue#4736 11/01/2010 Jiten
 * Patel ERROR_CONST#46 Unit Test Issue#4755 13/01/2010 Jiten Patel
 * ERROR_CONST#47 Unit Test Issue#4774 15/02/2010 Jiten Patel ERROR_CONST#48
 * Unit Test Issue#4965 24/02/2010 Jiten Patel ERROR_CONST#49 Client Test
 * Issue#946 27/02/2010 Jiten Patel ERROR_CONST#50 Client Test Issue#935
 * 27/02/2010 Manish Vithlani ERROR_CONST#51 Unit Test Issue#5045 10/03/2010
 * Jiten Patel ERROR_CONST#52 Unit Test Issue#5149 11/03/2010 Jiten Patel
 * ERROR_CONST#53 Client Test Issue#960 31/03/2010 Jiten Patel ERROR_CONST#54
 * Client Test Issue#1033 01/04/2010 Jiten Patel ERROR_CONST#55 Client Test
 * Issue#1034 21/04/2010 Jiten Patel ERROR_CONST#56 Client Test Issue#1107
 * 28/04/2010 Jiten Patel ERROR_CONST#57 Client Test Issue#628 11/03/2010 Jiten
 * Patel ERROR_CONST#58 Unit Test Issue#5511 19/05/2010 Vipul ERROR_CONST#59
 * SSCC Change (Serial Shipping Container Code) 28/05/2010 Manish Vithlani
 * ERROR_CONST#60 Client Test Change#1231 04/06/2010 Jiten Patel ERROR_CONST#61
 * Client Test Issue#1295 17/06/2010 Jiten Patel ERROR_CONST#62 Client Test
 * Issue#1220 29/06/2010 Jiten Patel ERROR_CONST#63 Unit Test Issue#5683
 * 28/07/2010 Shraddha Panchamiya ERROR_CONST#64 QA Issue#10271 12/09/2010 Jiten
 * Patel ERROR_CONST#65 Client Issue#1492 21/10/2010 Shraddha Panchamiya
 * ERROR_CONST#66 Unit Test Issue#6114 23/11/2010 Jiten Patel ERROR_CONST#67
 * Client Issue#1544 21/12/2010 Jiten Patel ERROR_CONST#68 Client Issue#1650
 * 30/12/2010 Manish Vithlani ERROR_CONST#69 Password 1/11/2011 Jiten Patel
 * ERROR_CONST#70 Client Issue#1598 20/01/2011 Jiten Patel ERROR_CONST#71 Unit
 * Issue#6379 25/01/2011 Jiten Patel ERROR_CONST#72 Client Issue#1741 28/01/2011
 * Shraddha Panchamiya ERROR_CONST#73 Client Issue#1751 02/02/2011 Jiten Patel
 * ERROR_CONST#74 QA Issue#13329 14/03/2011 Jiten Patel ERROR_CONST#75 Client
 * Pro Issue#73 17/03/2011 Jiten Patel ERROR_CONST#76 MDB 14/03/2012 Shalin Shah
 * ERROR_CONST#79 Client Issue# 486
 */

public interface ErrorConstants {
	public static final String DAO_ERROR = "Error getting DAO";

	// Error Messages Starts with 1000
	public static final int DAO_ERROR_CODE = 1000;
	public static final int SELECT_ERROR_CODE = 1001;
	public static final int INSERT_ERROR_CODE = 1002;
	public static final int UPDATE_ERROR_CODE = 1003;
	public static final int DELETE_ERROR_CODE = 1004;
	public static final int INVALID_PRODUCT_CODE = 1005;
	public static final int PROCEDURE_RUN_ERROR_CODE = 1006;
	public static final int UNABLE_INIT_SCREEN = 1007;

	// public static final int TOTAL_PERCENT_NOT_HUNDRED = 1007;
	public static final int MISSING_DETAILS = 1008;
	public static final int MISSING_SOURCE_DESTINY = 1009;
	public static final int ERROR_IN_SSCOVERAGE = 1010;
	public static final int MISSING_DESTINY = 1011;
	public static final int QTY_EXCEEDED_CAPACITY = 1012;
	public static final int COULD_NOT_REFREESH = 1013;
	public static final int ZERO_SEQUENCE_NOT_ALLOWED = 1014;

	// Information Messages Starts with 2000
	public static final int NO_PRODUCT_FOUND = 2000;
	public static final int ENTER_USERID_PASSWORD = 2001;
	public static final int INVALID_USERID_PASSWORD = 2002;
	public static final int NO_DOCUMENT_EXISTS = 2003;
	public static final int DOCUMENT_NOT_SAVED = 2004;
	public static final int DOCUMENT_SAVED_SUCCESSFULLY = 2005;
	public static final int FROM_DATE_NOT_FUTURE_DATE = 2006;
	public static final int DATE_NOT_IN_INV_PERIOD = 2007;
	public static final int MATCH_CONSTRAINT = 2008;
	public static final int ERROR_IN_DELETE = 2009;
	public static final int CONCURRENCY_NOT_ALLOWED = 2010;
	public static final int QTY_IS_ZERO = 2011;
	public static final int QTY_IS_NULL = 2012;
	public static final int PRODUCT_IS_NULL = 2013;
	public static final int TO_DATE_NOT_FUTURE_DATE = 2014;
	public static final int TO_DATE_LESS_THAN_FROM_DATE = 2030;
	public static final int DELETE_CONSTRAINT = 2015;
	public static final int INSERT_SUCCESSFUL = 2016;
	public static final int FORECAST_DETAILS_NOT_FOUND = 2017;
	public static final int UPDATE_SUCCESSFUL = 2018;
	public static final int NO_SUBSTITUTE_FOUND = 2019;
	public static final int SELECT_ATLEAST_ONE_PRODUCT = 2020;
	public static final int SUCCESSFUL_RUNPLAN = 2021;
	public static final int VOL_NOT_NULL = 2022;
	public static final int FILL_DETAILS = 2023;
	public static final int DELETE_SUCCESSFUL = 2024;
	public static final int CURRDATE_NOT_FUTURE = 2031;
	public static final int TO_DATE_NOT_CURRENT_DATE = 2032;
	public static final int PERCENT_CANNOT_EXCEED_HUNDRED = 2033;
	public static final int DUPLICATE_TEMPLATES_NOT_ALLOWED = 2034;
	public static final int DOCUMENT_NUMBER = 2035;
	public static final int SAVE_SUCCESS = 2036;
	public static final int DOCUMENT_NOT_ENTER = 2037;
	public static final int SEQUENCE_NOT_FOUND = 2038;
	public static final int SELECT_PROPER_SEQUENCE = 2039;
	public static final int CANNOT_DELETE_ROW = 2040;
	public static final int SELLING_CODE_NOT_EXISTS = 2041;
	public static final int LUCODE_NOT_EXIST = 2042;
	public static final int LOC_NOT_EXIST = 2043;
	public static final int QTY_EXCEEDED = 2044;
	public static final int LOC_NOT_CONTROLL = 2045;
	public static final int REFREESHED_SUCCESSFULLY = 2046;
	public static final int PENDING_INVENTORY = 2047;
	public static final int APPLY_SUCCESS = 2048;
	public static final int CANCEL_SUCCESS = 2049;
	public static final int INVENTORY_CLOSED = 2050;
	public static final int NO_ROUTE_DEFINED = 2051;
	public static final int PRODUCT_NOT_FOUND = 2052;
	public static final int NO_PHYSICAL_INVENTORY_EXISTS = 2053;
	public static final int NO_DATA_SAVE = 2054;
	public static final int INTERNAL_ERROR = 2055;
	public static final int SHIP_DATE_NOT_RECD_DATE = 2056;
	public static final int DOCUMENT_NOT_RETRIEVED = 2057;
	public static final int NO_DOCUMENT_FOUND = 2058;
	public static final int RECD_COMP_SYSDATE = 2059;
	public static final int PHYSICAL_INVENTORY_EXISTS = 2060;
	// ERROR_CONST#1 Starts
	public static final int INVALID_YEAR = 3056;
	public static final int INVALID_PERIOD = 3057;
	public static final int INVALID_WEEK = 3058;
	// ERROR_CONST#1 Ends

	// Warning Messages Starts with 3000
	public static final int TOTAL_NOT_HUNDRED = 3001;
	public static final int DAILY_ADJUSTMENT_LOST = 3002;
	public static final int HISTORY_WEEKS_NOT_NULL = 3003;
	public static final int FORECAST_WEEKS_NOT_NULL = 3004;
	public static final int PROUDCT_PERCENT_NOT_HUNDRED = 3005;
	public static final int QUANTITY_NOT_NULL = 3006;
	public static final int DESTINATION_NULL = 3007;
	public static final int TABLE_NULL = 3008;
	public static final int SEQUENCE_NOT_NULL = 3009;
	public static final int SELLING_EMPTY = 3010;
	public static final int LUCODE_EMPTY = 3011;
	public static final int BRAND_EMPTY = 3012;
	public static final int SUBFAMILY_EMPTY = 3013;
	public static final int SOURCE_DESTINATION = 3014;
	public static final int CANNOT_COMMIT = 3015;
	public static final int OPEN_DOCUMENT = 3016;
	public static final int NO_ROW_CANNOT_DELETE = 3017;
	public static final int NO_DEFAULT_SEQUENCE = 3018;
	public static final int NO_CHANGES_MADE = 3019;
	public static final int UOM_NOT_NULL = 3020;
	public static final int DATE_LESS_THEN_INVCONTROL_DATE = 3021;
	public static final int OPM_DATA_NOT_SAVED = 3022;
	public static final int REF_DOC_NOT_AVAIL = 3023;
	public static final int NOTHING_TO_APPLY = 3024;
	public static final int DOC_NOT_ENTER = 3025;
	public static final int REPORT_CANNOT_PRINT = 3026;
	public static final int ROUTE_NOT_DEFINED = 3027;
	public static final int QUANTITY_NOT_ZERO = 3028;
	public static final int TIME_LESS_THEN_CURRENT_TIME = 3029;
	public static final int SHIPMENTTIME_LESS_THEN_RECEIVEDTIME_TIME = 3030;
	public static final int REF_DOC_ALREADY_AVAILABLE_AVAIL = 3031;
	public static final int COMMIT_UNSUCCESSFUL = 3032;
	public static final int SOURCE_NULL = 3033;
	public static final int TINS = 3034;
	public static final int TOAS = 3035;
	public static final int RECD_DATE_LESS_THEN_INVCONTROL_DATE = 3036;
	public static final int SHIP_DATE_LESS_THEN_INVCONTROL_DATE = 3037;
	public static final int PRODUCT_NULL = 3038;
	public static final int PRODUCT_DATE_MISMATCH = 3039;
	public static final int HEAD_SAVE_SUCCESS = 3040;
	public static final int HEAD_SAVE_UNSUCCESS = 3041;
	public static final int SELECT_ROW_DEL = 3042;
	public static final int NO_ROWS_DEL = 3043;
	public static final int INVALID_USER = 3044;
	public static final int CANNOT_CHANGE_UOM = 3045;
	public static final int PRODUCT_NOT_FOUND_FOR_CRIETERIA = 3046;
	public static final int DATE_DIFF_MAX_10 = 3047;
	public static final int DATE_DIFF_MAX_7 = 3048;
	public static final int DATE_DIFF_MAX_1 = 3049;
	public static final int LAST_INV_MAX_10 = 3050;
	public static final int PRODUCT_ENTERED = 3051;
	public static final int ALREADY_ENTERED_DB = 3052;
	public static final int PHYINV_OF_SAMEDATETIME_EXIST = 3053;
	public static final int PHYINV_OF_FUTUREDATE_EXIST = 3054;
	public static final int UNAUTHORIZED_FOR_FREIGHT = 3055;

	// Added by jiten for Gap35
	public static final int LPHINV_OF_SAMEDATETIME_EXIST = 3059;
	public static final int LPHINV_OF_FUTUREDATE_EXIST = 3060;
	// Finish by jiten for Gap35

	// Added by jiten for Gap23 greece
	public static final int DOCUMENT_EXISTS = 3061;
	public static final int PRO_NO_DOCUMENT_EXISTS = 3062;
	// finish by jiten for gap23

	// Added by manish for stale warehouse gap2
	public static final int NO_STALEWAREHOUSE = 3063;
	// finish by manish for stale warehouse gap2

	// Added by jiten for inventory trans
	public static final int PACKED_PRODUCT_NOT_ENOUGH_1 = 3064;
	public static final int PACKED_PRODUCT_NOT_ENOUGH_2 = 3065;

	public static final int SELECT_COMPONENT_ITEM = 3066;
	public static final int PACKED_PRODUCT_NOT_AVAILABLE = 3067;

	public static final int ENTERED_QTY_GREATER_THAN_REQUIRE_QTY = 3068;
	// finish by jiten for inventory trans

	// Added by jiten for gap2 (Auto and manual doc no.)
	public static final int MANAUL_DOCUMENT_EXISTS = 3069;
	// finish by jiten for gap2 (Auto and manual doc no.)

	// Added by jiten for set screen right
	public static final int NO_DOCUMENT_RIGHT = 3070;
	// finish by jiten for set screen right

	// Added by manish for new Greece requirement
	public static final int SAVED_MISCELLANEOUS_EXIST = 3071;
	// FInish by manish for new Greece requirement

	// Added by jiten for costing
	public static final int RECV_QTY_NOT_GREATER_THAN_EXP_QTY = 3072;
	public static final int RECV_QTY_NOT_ZERO = 3073;
	public static final int PO_NOT_ENTER = 3074;
	public static final int EXPECTED_DOCUMENT_NOT_ENTER = 3075;
	public static final int RECEIVED_TIME_LESS_THAN_EXPECTED_TIME = 3076;
	public static final int CONVERSION_RATE_NOT_ENTER = 3077;
	public static final int PRICE_NOT_ZERO = 3078;
	public static final int PRICE_NOT_NULL = 3079;
	public static final int NO_MORE_REMAINING_QTY = 3080;
	public static final int EXPECTED_DOCUMENT_FINALLIZE = 3081;
	public static final int PURHCASECOST_DATE_NOT_LESS_THAN_RECEIVED_DATE = 3082;
	public static final int PRICE_NOT_GREATER_THAN_EXPECTED_PRICE = 3083;
	public static final int RECEIVED_DATE_LESS_THAN_EXPECTED_DATE = 3084;

	public static final int COST_DATE_NOT_FUTURE_DATE = 3085;
	public static final int COST_DATE_NOT_LESS_THAN_RECEIVED_DATE = 3086;
	public static final int COST_DATE_LESS_THAN_INVENTORY_DATE = 3087;

	public static final int COST_TIME_NOT_FUTURE_TIME = 3088;
	public static final int COST_TIME_NOT_LESS_THAN_RECEIVED_TIME = 3089;

	// Finish by jiten for costing

	// added by manish for Date not less than allowed no of days as defined in
	// parameter table
	public static final int DOCUMENT_DATE_NOT_LESS_ALLOWEDNOOFDAYS = 3090;
	// finish by manish for Date not less than allowed no of days as defined in
	// parameter table

	// Added by manish for new Greece requirement
	public static final int SAVED_MISCELLANEOUS_EXIST_1 = 3091;
	public static final int INVENTORY_CHANGED = 3092;
	// FInish by manish for new Greece requirement

	// added by manish for Co-packer Delivery Note Ref
	public static final int CO_PACKER_DELIVERY_DOCUMENT_NOT_ENTER = 3093;
	// Finish by manish for Co-packer Delivery Note Ref

	// Added by jiten for costing
	public static final int PURCHASECOST_TIME_LESS_THAN_RECEIVED_TIME = 3094;
	public static final int ENTER_LOCATION_SERIES = 3095;
	// Finish by jiten for costing

	// Added by manish for changing document number from 12 to 14 digit
	public static final int INVALID_DOCUMENT_NUMBER = 3096;
	// Finish by manish for changing document number from 12 to 14 digit

	// Added by jiten for costing
	public static final int TRANSACTION_DATE_NOT_FUTURE_DATE = 3097;
	public static final int TRANSACTION_DATE_NOT_LESS_THAN_RECEIVED_DATE = 3098;
	public static final int TRANSACTION_DATE_LESS_THAN_INVENTORY_DATE = 3099;
	public static final int TRANSACTION_TIME_NOT_FUTURE_TIME = 3100;
	public static final int TRANSACTION_TIME_NOT_LESS_THAN_RECEIVED_TIME = 3101;
	public static final int PRICE_NOT_MINUS = 3102;
	public static final int PRICE_NOT_VALID = 3103;
	public static final int ENTER_INVOICE_NO = 3104;
	public static final int RET_QTY_NOT_GREATER_THAN_EXP_QTY = 3106;
	public static final int RET_QTY_NOT_ZERO = 3107;
	public static final int RETURN_TIME_LESS_THAN_EXPECTED_TIME = 3105;
	public static final int PURHCASECOST_DATE_NOT_LESS_THAN_RETURN_DATE = 3108;
	public static final int RETURN_DATE_LESS_THAN_EXPECTED_DATE = 3109;
	public static final int PURCHASECOST_TIME_LESS_THAN_RETURN_TIME = 3110;
	public static final int TEMPORARY_COST_ENTERED = 3111;
	public static final int SUPPLIER_NULL = 3112;
	public static final int SAVE_COUNT = 3113;
	public static final int DOCREVIEW_COUNT = 3114;
	public static final int DELV_NOT_ENTER = 3115;
	public static final int PENDING_ACK_COUNT = 3118;
	public static final int PENDING_RECEIVED_COUNT = 3119;
	public static final int FUTURE_PERIOD_NOT_ALLOW = 3120;
	public static final int PRICE_IN_TEMPV_NOT_GREATER_THAN_EXPECTED_PRICE = 3121;// Added
																					// by
																					// jiten
																					// for
																					// QA
																					// Issue#6422
	public static final int FROM_PERIOD_NOT_GREATER = 3122;
	public static final int FROM_PERIOD_NOT_ZERO = 3123;
	public static final int TO_PERIOD_NOT_GREATER = 3124;
	public static final int TO_PERIOD_NOT_ZERO = 3125;
	public static final int FROM_PERIOD_NOT_GREATER_THAN_TO_PERIOD = 3126;
	public static final int FROM_YEAR_NOT_GREATER_THAN_TO_YEAR = 3127;
	public static final int ENTER_ALL_VALUES = 3128;
	// Added by jiten for TEST Issue#2275
	public static final int DOCUMENT_REVIEWED = 3129;
	// finished by jiten for TEST Issue#2275
	// Added by jiten for Period closing Report
	public static final int SELECT_LOCATION_TYPE = 3130;
	// finished by jiten for Period closing Report
	public static final int FROM_YEAR_NOT_LESS_THAN_FOUR_DIGIT = 3131;
	public static final int TO_YEAR_NOT_LESS_THAN_FOUR_DIGIT = 3132;
	// finish by jiten for costing
	// Added by jiten for Issue#2353
	public static final int FROM_PERIOD_NOT_FIRST_CLOSING_PERIOD = 3133;
	public static final int FROM_PERIOD_NOT_AVAILABLE = 3134;
	public static final int TO_PERIOD_NOT_AVAILABLE = 3135;
	// Finished by jiten for Issue#2353
	// Finish by jiten for costing

	// added by manish for stock ageing report
	public static final int FROM_DATE_LESS_THAN_TO_DATE = 3136;
	public static final int DIFF_FIRST_PERIOD_ZERO = 3137;
	public static final int AGING_VALUE_LESS_THAN_ZERO = 3138;
	// finish by manish for stock ageing report

	// added by manish for Greece Physical Inventory Implementation
	public static final int PHINV_OF_CURRENTDATE_EXIST = 3139;
	public static final int LPHINV_OF_CURRENTDATE_EXIST = 3140;
	public static final int AUTOMATIC_PHINV_OF_CURRENTDATE_NOTCREATED = 3141;
	public static final int AUTOMATIC_LPHINV_OF_CURRENTDATE_NOTCREATED = 3142;
	// Finish by manish for Greece Physical Inventory Implementation

	public static final int REPORT_FOR_ALL_EXP_PO_NO = 3143;
	public static final int REPORT_FOR_ALL_SUP_DLV_NO = 3144;
	public static final int REPORT_FOR_ALL_SUP_INV_NO = 3145;

	// finish by jiten for costing

	// Added by jiten for Issue#2438
	public static final int PO_NO_IS_DUPLICATED = 3146;

	// added by manish for Greece Physical Inventory Implementation
	public static final int GREECE_PHY_ALREADY_EXIST_FOR_THIS_YEAR = 3148;
	public static final int CANNOT_CHANGE_GREECE_FINAL_PHY_YEAR = 3149;
	public static final int MANUAL_VALIDATION_1 = 3150;
	public static final int MANUAL_VALIDATION_2 = 3151;
	public static final int RECALCULATE_SUCCESSFULL = 3152;
	public static final int GRECCE_PHYISCAL_DOCUMENT_EXIST = 3165;
	public static final int GRECCE_LOGICAL_PHYISCAL_DOCUMENT_EXIST = 3166;
	public static final int GRECCE_PRODUCT_NULL = 3167;
	// Finish by manish for Greece Physical Inventory Implementation

	// Added for ERROR_CONST#2
	public static final int PALLATE_NUMBER_NOT_EMPTY = 3154;
	public static final int PALLATE_NUMBER_DUPLICATE = 3147;
	public static final int PRODUCTION_SUMMARY_DOC_EXIST = 3155;
	public static final int FROM_PALLATE_NUMBER_NOT_EMPTY = 3156;
	public static final int TO_PALLATE_NUMBER_NOT_EMPTY = 3157;
	public static final int FROM_PALLATE_NUMBER_NOT_GREATER_THAN_TO_PALLATE_NO = 3153;
	public static final int PALLATE_NUMBER_BETWEEN_0_TO_9 = 3158;

	// ADDED BY MANISH FOR GREECE REPORT development
	public static final int NO_GREECE_TRANSACTION_REPORT_RIGHT = 3159;
	public static final int FROMLUCODE_TOLUCODE_CANNOT_SAME = 3160;
	public static final int FROMLUCODE_CANNOT_BIGGER_TOLUCODE = 3161;
	public static final int TOLUCODE_CANNOT_SMALLER_FROMLUCODE = 3162;
	public static final int FROMLUCODE_CANNOT_EMPTY = 3163;
	public static final int TOLUCODE_CANNOT_EMPTY = 3164;

	// added by jiten for Production Cost Information
	public static final int INVALID_PERIODYEAR = 3171;
	public static final int PERIOD_INVALID = 3172;
	public static final int YEAR_INVALID = 3173;
	// Finished by Jiten for Production Cost Information

	// added by Rinku for Greece Reports
	public static final int INVALID_SELECTED_LOCATION = 3174;
	// finished by Rinku for Greece Reports

	// Sankit[
	public static final int MORE_THAN_5_SELECTED_LOCATION = 3374;
	// Sankit]
	// Added for ERROR_CONST#2
	public static final int PALLATE_NUMBER_NOT_ZERO = 3175;
	// finished for ERROR_CONST#2

	// Added for ERROR_CONST#3
	public static final int FROMBRAND_CANNOT_BIGGER_TOBRAND = 3176;
	public static final int TOBRAND_CANNOT_SMALLER_FROMBRAND = 3177;
	public static final int FROMBRAND_CANNOT_EMPTY = 3178;
	public static final int TOBRAND_CANNOT_EMPTY = 3179;
	// finished for ERROR_CONST#3

	// Added for ERROR_CONST#4
	public static final int FROM_BRAND_NOT_EMPTY = 3180;
	public static final int TO_BRAND_NOT_EMPTY = 3181;
	// finished for ERROR_CONST#4

	// Added for ERROR_CONST#4
	public static final int INVOICE_NBR_NOT_EMPTY = 3182;
	// finished for ERROR_CONST#4

	// Added for ERROR_CONST#6
	public static final int PENDING_TO_ACK = 3183;
	// finished for ERROR_CONST#6

	// start ERROR_CONST#7
	public static final int ZERO_PRINTING_SEQUENCE_NOT_ALLOWED = 3184;
	public static final int PRINTING_SEQUENCE_NOT_NULL = 3185;
	public static final int SELECT_PROPER_PRINTING_SEQUENCE = 3186;
	// end ERROR_CONST#7

	// Added for ERROR_CONST#7
	public static final int FROM_TIME_NOT_GREATER_THAN_TO_TIME = 3187;
	// finished for ERROR_CONST#7

	// start ERROR_CONST#8
	public static final int SELECT_ATLEAST_ONE_BRAND = 3188;
	public static final int SELECT_ATLEAST_ONE_TRANSACTION = 3189;
	// end ERROR_CONST#8

	// start ERROR_CONST#9
	public static final int FILE_VALID_NAME = 3190;
	public static final int FILE_OVERWRITE = 3191;
	public static final int FILE_EXPORTED_TO_EXCEL = 3192;
	public static final int FILE_NOT_FOUND = 3193;
	public static final int FILE_EXPORT_FAIL = 3194;

	// end ERROR_CONST#9

	// Start ERROR_CONST#10
	public static final int RECV_DATE_NOT_LESSTHAN_LAST_RECV_DATE = 3195;
	public static final int RECV_TIME_NOT_LESSTHAN_LAST_RECV_TIME = 3196;
	public static final int RET_DATE_NOT_LESSTHAN_LAST_RET_DATE = 3197;
	public static final int RET_TIME_NOT_LESSTHAN_LAST_RET_TIME = 3198;
	// Finished ERROR_CONST#10

	// Added for ERROR_CONST#11
	public static final int VALUE_NOT_NULL = 3199;
	public static final int VALUE_NOT_VALID = 3200;
	public static final int VALUE_NOT_MINUS = 3201;
	public static final int ENTER_NUMERIC_VALUE = 3202;
	// finished for ERROR_CONST#11

	public static final int EXCEL_FILE_NOT_FOUND = 3203;// Added for
														// ERROR_CONST#12
	public static final int NO_DATA_EXIST = 3204;// Added for ERROR_CONST#12

	// Added for ERROR_CONST#13
	public static final int SRCE_DEST_SAME_CONTINUE = 3205;
	public static final int SRCE_DEST_SAME = 3206;

	// Added for ERROR_CONST#14
	public static final int GOODS_STALE_TO_GOODS_LOCATION_CONTINUE = 3207;
	public static final int GOODS_STALE_TO_GOODS_LOCATION = 3208;

	// Added for ERROR_CONST#15
	public static final int INVALID_TRUCK_PLATE = 3209;// changed for
														// ERROR_CONST#35

	// start ERROR_CONST#18
	public static final int SHIP_DATE_LESS_THAN_LAST_MIS_DOC = 3221;
	public static final int SHIP_DATE_LESS_THAN_LAST_MIS_DOC1 = 3222;
	public static final int SHIP_DATE_LESS_THAN_LAST_MIS_DOC2 = 3223;
	public static final int SHIP_TIME_LESS_THAN_LAST_MIS_DOC = 3224;
	public static final int SHIP_TIME_LESS_THAN_LAST_MIS_DOC1 = 3225;
	public static final int SHIP_TIME_LESS_THAN_LAST_MIS_DOC2 = 3226;
	public static final int ALL_PRODUCTS_ZERO_OF_MIS_DOCUMENT = 3230;
	public static final int TRANSACTION_DATE_LESS_STOCK_DATE = 3231;
	public static final int TRANSACTION_TIME_LESS_STOCK_TIME = 3232;
	public static final int TRANSACTION_TIME_FUTURE_TIME = 3239;
	// end ERROR_CONST#18

	// start ERROR_CONST#24
	public static final int VOID_TRANSACTION_TIME_LESS_APPROVED_TRANSACTION_TIME = 3240;
	public static final int VOID_TRANSACTION_DATE_LESS_APPROVED_TRANSACTION_DATE = 3241;
	// end ERROR_CONST#24

	// Added for ERROR_CONST#19
	public static final int CREATE_AUTO_TIN = 3227;
	public static final int DO_YOU_WANT_CREATE_AUTO_TIN = 3228;

	// start ERROR_CONST#16
	public static final int TRUCK_NO = 3210;
	public static final int NOT_EMPTY_TRUCK_DESC = 3211;
	public static final int NOT_EMPTY_PLATE_NO = 3212;
	public static final int NOT_EMPTY_VOLUME = 3213;
	public static final int ENTER_VALID_INFO = 3214;
	public static final int TRUCK_NO_NOT_ENTER = 3215;
	public static final int TRUCK_NO_NOT_FOUND = 3216;
	// public static final int TRUCK_NO=3208;

	// end ERROR_CONST#16

	// Start ERROR_CONST#17
	public static final int CARRIER_NO = 3217;
	public static final int CARRIER_NO_NOT_ENTER = 3218;
	public static final int CARRIER_NO_NOT_FOUND = 3219;
	public static final int LOCATION_NO_NOT_FOUND = 3220;
	public static final int ENTER_VALID_DEFAULT_TRUCK = 3229;
	// End ERROR_CONST#17

	// start ERROR_CONST#20
	public static final int COPACKER_DELIVERY_NOTE_NUMBER_DOES_NOT_EXIST = 3235;
	public static final int ENTERED_COPACKER_DELIVERY_NOTE_NUMBER_DOES_NOT_EXIST = 3236;
	public static final int ENTERED_COPACKER_LOCATION_DOES_NOT_EXIST = 3237;
	// end ERROR_CONST#20
	// Start ERROR_CONST#21
	public static final int NO_DATA_FOUND = 3233;
	// End ERROR_CONST#21
	// Start ERROR_CONST#22
	public static final int LUCODE_NOT_FOUND = 3234;
	// End ERROR_CONST#22

	// Added for ERROR_CONST#23
	public static final int ENTER_VALID_PERIOD = 3238;
	// finished for ERROR_CONST#23

	// start ERROR_CONST#25
	public static final int PHYSICAL_QUANTITY_NOT_VALID = 3242;
	public static final int PHYSICAL_QUANTITY_NOT_MINUS = 3243;
	public static final int GREECE_MANUAL_CREATED_WITH_TEAM_FINAL = 3244;
	public static final int TRANSACTION_DATE_LESS_DOCUMENT_DATE = 3245;
	public static final int TRANSACTION_YEAR_GREATER_DOCUMENT_YEAR = 3246;
	public static final int COMMIT_SUCCESS = 3247;
	// end ERROR_CONST#25

	// start ERROR_CONST#26
	public static final int PHY_EXIST_IN_COMMIT_STATUS = 3248;
	// end ERROR_CONST#26

	// Added for ERROR_CONST#27
	public static final int ENTER_QTY_LESS_THAN_PALLATE = 3249;
	public static final int ENTER_QTY_MORE_THAN_PALLATE = 3250;
	public static final int ENTER_REASON_CODE = 3251;
	public static final int REASON_CODE_NOT_REQUIRE = 3262;
	// finihsed for ERROR_CONST#27

	// Added for ERROR_CONST#28
	public static final int PRODUCTION_SUMMARY = 3252;
	public static final int PRODUCTION_SUMMARY_IN_SAVE = 3253;
	public static final int PRODUCTION_SUMMARY_IN_COMMIT = 3254;
	public static final int PRODUCTION_RECEIPT_EXIST = 3255;
	public static final int PALLATE_NUMBER_GREATER_THEN_7_DIGIT = 3256;
	public static final int MANUAL_PRODUCTION_SUMMARY_DOC_EXIST = 3257;
	public static final int RECALCULATE_PROCCESS_COMPLETED = 3258;
	// Finished for ERROR_CONST#28

	// Added for ERROR_CONST#29
	public static final int SELECTED_TRANSACTION_NOT_MORE_THAN_10 = 3259;
	// finished for ERROR_CONST#29

	// start ERROR_CONST#30
	public static final int MIS_DOC_ALL_PRODUCT_ZERO = 3260;
	// end ERROR_CONST#29

	// start ERROR_CONST#31
	public static final int NO_TEAM_RIGHT_FOR_MANUAL_PHYSICAL_DOCUMENT = 3261;
	// end ERROR_CONST#31

	// start ERROR_CONST#32
	public static final int GREECE_ANOTHER_MANUAL_DOCUMENT_CREATED = 3263;// CHANGED
																			// FOR
																			// ERROR_CONST#33
	// end ERROR_CONST#32

	// start ERROR_CONST#34
	public static final int TIN_REASON_CODE_NOT_REQUIRE = 3264;
	public static final int ENTER_TIN_REASON_CODE = 3265;
	public static final int TIN_RECEIVED_QTY_CANNOT_UPDATED = 3266;
	// end ERROR_CONST#34

	// start ERROR_CONST#36
	public static final int MANUAL_PHY_REASON_CODE_NOT_ENTERED = 3267;
	// end ERROR_CONST#36

	public static final int SELECT_REASON_CODE = 3268;// Added for
														// ERROR_CONST#37

	public static final int PALLATE_NUMBER_CONSTANT_NOT_EMPTY = 3269;// Added
																		// for
																		// ERROR_CONST#38
	public static final int PALLATE_CONSTANT_GREATER_THEN_11_DIGIT = 3270;// Added
																			// for
																			// ERROR_CONST#38
	public static final int PALLATE_CONSTANT_NOT_ZERO = 3271;// Added for
																// ERROR_CONST#38
	public static final int PALLATE_CONSTANT_BETWEEN_0_TO_9 = 3272;// Added for
																	// ERROR_CONST#38

	// start ERROR_CONST#36
	public static final int MANUAL_PHY_PALLET_CASE_EACH_EMPTY = 3273;
	// end ERROR_CONST#36

	public static final int FROM_PALLATE_NUMBER_CONSTANT_NOT_EMPTY = 3274;// Added
																			// for
																			// ERROR_CONST#39
	public static final int TO_PALLATE_NUMBER_CONSTANT_NOT_EMPTY = 3276;// Added
																		// for
																		// ERROR_CONST#39

	public static final int FROM_PALLATE_CONSTANT_BETWEEN_0_TO_9 = 3275;// Added
																		// for
																		// ERROR_CONST#39
																		// //changed
																		// for
																		// ERROR_CONST#40
	public static final int TO_PALLATE_CONSTANT_BETWEEN_0_TO_9 = 3277;// Added
																		// for
																		// ERROR_CONST#39
																		// //changed
																		// for
																		// ERROR_CONST#40

	public static final int FROM_PALLATE_CONSTANT_NOT_GREATER_THAN_TO_PALLATE_CONSTANT = 3278;// Added
																								// for
																								// ERROR_CONST#39

	public static final int FROM_PALLATE_CONSTANT_LESS_THEN_11_DIGIT = 3279;// Added
																			// for
																			// ERROR_CONST#38
	public static final int TO_PALLATE_CONSTANT_LESS_THEN_11_DIGIT = 3280;// Added
																			// for
																			// ERROR_CONST#38

	// start ERROR_CONST#36
	public static final int THEO_EACH_NOT_NULL = 3281;
	// end ERROR_CONST#36

	// Start for ERROR_CONST#39
	public static final int REASSIGNED_DEFAULT_TRUCK = 3282;
	public static final int EMPTY_CARRIER_ID = 3283;
	// End for ERROR_CONST#39

	// start ERROR_CONST#40
	public static final int MANUAL_PHY_PALLET_CASE_EACH_ZERO = 3284;
	// end ERROR_CONST#40

	// start ERROR_CONST#41
	public static final int DOCUMENT_CLOSE_WITH_CHANGE = 3285;
	// end ERROR_CONST#41

	// Added for ERROR_CONST#39
	public static final int RECEIVED_DATE_FUTURE_OR_PAST = 3286;
	public static final int RECEIVED_TIME_FUTURE_OR_PAST = 3287;
	// finished for ERROR_CONST#39

	// start ERROR_CONST#41
	public static final int MISCELLANEOUS_SOURCE1_INVALID = 3288;
	public static final int MISCELLANEOUS_SOURCE2_INVALID = 3289;

	public static final int MISCELLANEOUS_SOURCE1_NULL = 3290;
	public static final int MISCELLANEOUS_SOURCE2_NULL = 3291;
	public static final int MISCELLANEOUS_TRUCKID_NULL = 3292;
	// end ERROR_CONST#41

	// start ERROR_CONST#42

	public static final int PHY_INVENTORY_FROM_LUCODE_CANNOT_EMPTY = 3293;
	public static final int PHY_INVENTORY_TO_LUCODE_CANNOT_EMPTY = 3294;

	// end ERROR_CONST#42

	// start ERROR_CONST#43
	public static final int MISCELLANEOUS_LOCATION_NULL = 3295;
	// end ERROR_CONST#43

	// start ERROR_CONST#42
	public static final int PHYPRINT_FROM_LUCODE_CANNOT_BIGGER_TOLUCODE = 3296;
	public static final int PHYPRINT_TO_LUCODE_CANNOT_SMALLER_FROMLUCODE = 3297;
	// end ERROR_CONST#42

	public static final int HHC_DOC_NOT_AVAIL = 3298; // Added for
														// ERROR_CONST#44

	public static final int SELECT_LOCATION_SERIES = 3299;// Added for
															// ERROR_CONST#45

	public static final int SAVED_MISCELLANEOUS_EXIST_2 = 3300;// Added for
																// ERROR_CONST#46

	public static final int TRUCKS_NOT_AVAILABLE = 3301;// Added for
														// ERROR_CONST#47

	public static final int PRODUCTION_SUMMARY_NOT_EXIST = 3302;// Added for
																// ERROR_CONST#48

	// Added for ERROR_CONST#49
	public static final int TEMPORARY_DATE_NOT_LESS_THAN_RECEIVED_DATE = 3303;
	public static final int TEMPORARY_TIME_NOT_LESS_THAN_RECEIVED_TIME = 3304;
	public static final int TEMPORARY_DATE_ON_SAME_MONTH_RECEIVED_DATE = 3305;
	public static final int ACCRUAL_VALUE_SHOULD_NOT_EMPTY = 3306;

	public static final int TEMPORARY_DATE_NOT_LESS_THAN_RETURN_DATE = 3307;
	public static final int TEMPORARY_TIME_NOT_LESS_THAN_RETURN_TIME = 3308;
	public static final int TEMPORARY_DATE_ON_SAME_MONTH_RETURN_DATE = 3309;

	// finished for ERROR_CONST#49

	// Added for ERROR_CONST#50
	public static final int DOCUMENT_EXIST_FOR_DELIVERY_NOT_NUMBER_LAST_YEAR = 3310;
	public static final int DOCUMENT_EXIST_FOR_DELIVERY_NOT_NUMBER_CUR_YEAR = 3311;
	// finsihed for ERROR_CONST#50

	// start ERROR_CONST#51
	public static final int INVALID_LU_CODES = 3312;
	// end ERROR_CONST#51

	// Added for ERROR_CONST#52
	public static final int COST_NOT_NULL = 3313;
	public static final int COST_NOT_MINUS = 3314;
	public static final int COST_NOT_ZERO = 3315;
	// finished for ERROR_CONST#52

	// Added for ERROR_CONST#53
	public static final int FILE_NOT_UPLOADED = 3316;
	public static final int FILE_IS_PROCESSED = 3317;
	public static final int FILE_IS_NOT_VALID = 3318;
	// finsihed for ERROR_CONST#53

	public static final int TRANS_DATE_SHOULD_NOT_LESS_THAN_ALLOW_DAYS = 3319;// Added
																				// for
																				// ERROR_CONST#54

	public static final int NEGATIVE_TRANSACTION_QTY_AFTER_SELECTED_DATE = 3320;// Added
																				// for
																				// ERROR_CONST#55

	public static final int PRODUCTION_SUMMARY_DOC_EXIST_FOR_FUTURE = 3321;// Added
																			// for
																			// ERROR_CONST#56

	public static final int LEGAL_PRINTER_NOT_FOUND = 3322;// Added for
															// ERROR_CONST#57

	public static final int HOST_INFO_NOT_FOUND = 3323;// Added for
														// ERROR_CONST#58

	public static final int INVALID_CHECK_DIGIT = 3324;// Added for
														// ERROR_CONST#59

	// start ERROR_CONST#60
	public static final int ENTER_ATLEAST_ONE_MULTIPAKC_PRODUCT = 3325;
	public static final int FINALLIZED_OPEN_QTY = 3326;
	public static final int LOCATION_ALREADY_EXISTS = 3327;
	public static final int PLEASE_SELECT_ROW = 3328;
	public static final int FILE_NOT_VALID = 3329;
	public static final int PENDING_FINAL_PRODUCTION_SUMMARY_DOCUMENT = 3330;
	public static final int DELIVERY_NOTE_RECEIPT_NOTE_DOCUMENT_NOT_INVOICED_CREDITED = 3331;

	// end ERROR_CONST#60

	public static final int ITEM_NOT_PROCESSED = 3377;

	public static final int DOCUMENT_UPDATED_BY_OTHER_USER = 3380;// Added for
																	// ERROR_CONST#61

	public static final int REPRINT_NOT_ALLOWED = 3381;// Added for
														// ERROR_CONST#62

	public static final int EXPORT_TO_EXCEL_LIMIT_EXCEED = 3382;// Added for
																// ERROR_CONST#62//Added
																// for
																// ERROR_CONST#63

	public static final int SAVED_MISCELLANEOUS_EXIST_ON_DESTINATION_LOCATION = 3383; // Added
																						// for
																						// ERROR_CONST#62

	public static final int VOID_DOCUMENT_NUMBER = 3384; // Added for
															// ERROR_CONST#64
	public static final int CLOSE_PERIOD_SUCCESS = 3385; // Added for
															// ERROR_CONST#64

	public static final int OPENNING_QTY_NOT_UPDATED = 3386;// Added for
															// ERROR_CONST#65
	public static final int OPENNING_QTY_UPDATED = 3387;// Added for
														// ERROR_CONST#65

	public static final int DEADLOCK_ERROR = 3388; // Added for ERROR_CONST#66

	public static final int SESSION_PROCESS_IN_HHC_IM_CYCLE = 3389; // Added for
																	// ERROR_CONST#67

	public static final int TRUCK_NO_IS_BLANK_CONTINUE = 3390;
	public static final int TRUCK_PLATE_NUMBER = 3391;

	public static final int MDB_FILE_CREATED = 3392;// Added for ERROR_CONST#67

	// start ERROR_CONST#69
	public static final int PWD_CHANGE_BY_ADMIN = 3393;
	// end ERROR_CONST#69

	public static final int ENTER_SELLING_OR_LUCODE = 3394;// Adde for
															// ERROR_CONST#70
	public static final int DO_YOU_WANT_UPDATE_QUANTITY = 3395;// Adde for
																// ERROR_CONST#70

	public static final int VALUE_ZERO_FOR_RECEIVE_ZERO = 3396;// Adde for
																// ERROR_CONST#71
	public static final int FINAL_VALUE_ZERO_FOR_RECEIVE_ZERO = 3397;// Adde for
																		// ERROR_CONST#71

	public static final int VALUE_ZERO_FOR_RETURN_ZERO = 3398;// Adde for
																// ERROR_CONST#71
	public static final int FINAL_VALUE_ZERO_FOR_RETURN_ZERO = 3399;// Adde for
																	// ERROR_CONST#71

	public static final int DOCUMENT_DATE_NOT_LESS_ALLOWEDNOOFDAYS_FROM_SHIPPMENT = 3400; // Added
																							// for
																							// ERROR_CONST#72
	public static final int VOID_DOCUMENT_DATE_NOT_LESS_SHIPPMENT_DATE = 3401; // added
																				// for
																				// ERROR_CONST#73

	public static final int PRODUCT_NOT_EFFECTIVE_ON_PERIOD_START_DATE = 3402; // Added
																				// for
																				// ERROR_CONST#74

	public static final int PRODUCT_NOT_AUTHORIZE = 3403;// Added for
															// ERROR_CONST#75

	public static final int FILE_NOT_GENERATED = 3404;// Added for
														// ERROR_CONST#76
	public static final int MDB_PATH_NOT_FOUND = 3405;// finished for
														// ERROR_CONST#76

	public static final int INVALID_FTP_USERID_PASSWORD = 3406; // finished for
																// ERROR_CONST#76

	public static final int TXT_FILE_CREATED = 3407; // finished for
														// ERROR_CONST#77

	public static final int PDF_FILE_CREATED = 3408; // finished for
														// ERROR_CONST#78

	public static final int FTP_INFO_NOT_FOUND = 3409;

	public static final int DOC_UPDATED_BY_OTHER_USER = 3410; // ADDED By SHALIN
																// SHAH For
																// ERROR_CONST#79
																// --- Client
																// Issue# 486

	public static final int SELECT_PROJECT = 3411;
}
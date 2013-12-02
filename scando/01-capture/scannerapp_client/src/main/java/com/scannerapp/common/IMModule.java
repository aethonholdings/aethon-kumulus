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
public interface IMModule {
    String DESKTOP_IM = "DeskTop";
    String LOGIN = "Login";
    String ERROR_MESSAGE = "ErrorMessage";
    String COMMON_BUTTON = "ButtonJPanel";
    String COMMON_INFOFIELD = "InfoFieldJPanel";
    String COMMON_TEXTFIELD = "TextFieldJPanel";
    String SCREEN_REPORT = "ScreenReport";
    String TRANSFER_OUT = "TransferOut";
    String INVENTORY_TRANSFORMATION = "InventoryTransformation";
    String TRANSFER_IN = "TransferIn";
    String ADJUST_ACKNOWLEDGE = "AdjustAcknowledge";
    String PURCHASE_RETURN = "PurchaseReturn";
    String PURCHASE_RECEIPT = "PurchaseReceipt";
    String MISCELLANEOUS = "Miscellaneous";
    String PRODUCTION_RECEIPT = "ProductionReceipt";
    String PRODUCTION_RETURN = "ProductionReturn";
    String PHYSICAL_INVENTORY = "PhysicalInventory";
    String PICKING_SEQUENCE = "PickingSequence";
    String DOCUMENT_SEARCH = "DocumentSearch";
    String STOCK_INQUIRY = "StockInquiry";
    String TCOMMS_CHECKING = "TCOMMsChecking";
    String ROUTE_ALLOCATION = "RouteAllocation";
    String ROUTE_SHIPMENT = "RouteShipment";
    String REV_ROUTE_SHIPMENT = "ReverseRouteShipment";
    String COMMON = "Common";
    String REPORT = "Report";
    String PROD_ACTIVE_INACTIVE = "ProdActiveInactive";
    String LOCAL_MAINTENANCE = "LocalMaintenance";
    String DELIVERY_NOTE = "DeliveryNoteReport";
    //added by manish for costing
    String AVERAGEACTUALCOST = "Averageactualcost";
    //finish by manish for costing
    
    //added by manish for Greece Reports
    String GREECE_REPORTS="Greports";
    //Finish by manish for Greece Reports

    //Added by jiten for costing
    String RECONCILIATION = "Reconciliation";
    //finished by jiten for costing 

   //added by Jiten Patel for Production Cost Information
    String PRODUCTION_COST = "ProductionCostInformation";
    //finish by Jiten Patel for Production Cost Information
    
    //Added by rinku for Truck Master Issue#3501
    String TRUCK_MASTER="TruckMaster";
    //Finish by rinku for Truck Master Issue#3501
    
    //Added by rinku for CARRIER TRUCK ASSIGNMENT Issue#3502
    String CARRIER_TRUCK_ASSIGNMENT="CarrierTruckAssignment";
    //Finish by rinku for CARRIER TRUCK ASSIGNMENT Issue#3502
    
    //Added by rinku for Period cost
    String PERIOD_COST="PeriodCost";
    //Finished by rinku for Period cost
}

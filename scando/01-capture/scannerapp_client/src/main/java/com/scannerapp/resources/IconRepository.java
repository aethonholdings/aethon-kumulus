package com.scannerapp.resources;

import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.scannerapp.apps.application.ApplicationConstants;
import com.scannerapp.apps.utils.FileLoader;

public interface IconRepository {
	
	ImageIcon PREVIOUS_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "previousitem20.gif");
	ImageIcon NEXT_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "nextitem20.gif");
	ImageIcon NEW_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "reports20.gif");
	ImageIcon DESCEND_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "descend16.gif");
	ImageIcon ASCEND_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "ascend16.gif");
	ImageIcon SAVE_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "Save.gif");
	ImageIcon CANCEL_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "CANCEL.GIF");
	ImageIcon SEARCH_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "find.gif");
	ImageIcon LIST_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "Doc1.gif");
	ImageIcon PEPSICOINTERNATIONAL_ICON = FileLoader
			.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "PepsiCo_Int_New.jpg");

	ImageIcon HIDEGRAPH_ICON = FileLoader
			.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "itemmaintenance20.gif");
	ImageIcon SHOWGRAPH_ICON = FileLoader
			.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "forecastadjustment20.gif");
	ImageIcon PRODUCT_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "viewall20.gif");
	ImageIcon CASE_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "codebtn.gif");
	ImageIcon APPLY_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "OK.gif");
	ImageIcon RUNPLAN_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "getskus20.gif");
	ImageIcon ADD_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "add.png");
	ImageIcon ADD_ALL_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "addall.gif");
	ImageIcon REMOVE_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "remove.gif");
	ImageIcon REMOVE_ALL_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "removeall.gif");

	ImageIcon DELETE_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "deleterow20.gif");
	ImageIcon ALL_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "selectall20.gif");
	ImageIcon WEEK_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "calc.gif");
	ImageIcon REFRESH_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "refresh.png");
	ImageIcon EXIT_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "exit16x16.gif");
	ImageIcon BLINKBOX_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "pickupani.gif");
	ImageIcon BOX_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "dispatch16x16.gif");
	ImageIcon TABLE_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "ratematrix16.gif");
	ImageIcon HORIZON_ICON = FileLoader
			.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "IntFrameTopLeftImage.gif");
	ImageIcon PRINT_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "Print.gif");
	ImageIcon NB_FIRST2_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "nbfirst1.gif");
	ImageIcon NB_PREVIOUS2_ICON = FileLoader
			.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "nbprevious1.gif");
	ImageIcon NB_NEXT2_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "nbnext1.gif");
	ImageIcon NB_LAST2_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "nblast1.gif");
	ImageIcon ZOOMIN_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "zoomin.gif");
	ImageIcon ZOOMOUT_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "zoomout.gif");

	ImageIcon TRANSFER_OUT = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "to1.gif");
	ImageIcon TRANSFER_IN = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "ti1.gif");
	ImageIcon COPACKER_TRANS_OUT = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "copackto.gif");
	ImageIcon COPACKER_TRANS_IN = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "copackti1.gif");
	ImageIcon ADJUST_ACKNOWLEDGE = FileLoader
			.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "Adjustment.gif");
	ImageIcon DOCUMENT_SEARCH = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "search2.gif");
	ImageIcon INV_TRANSFORMATION = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "invtrans1.gif");
	ImageIcon MISCELLINEOUS = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "miscellaneous.gif");
	ImageIcon PHYSICAL_INVENTORY = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "physical.gif");
	ImageIcon PICKING_SEQUENCE = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "picking.gif");
	ImageIcon PROD_RECEIPT = FileLoader
			.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "productionreceipt.gif");
	ImageIcon PROD_RETURN = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "productionreturn.gif");
	ImageIcon ROUTE_ALLOCATION = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "routealloc1.gif");
	ImageIcon ROUTE_SHIPMENT = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "rs1.gif");
	ImageIcon REV_ROUTE_SHIPMENT = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "rs2.gif");
	ImageIcon STOCK_INQUIRY = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "stock1.gif");
	ImageIcon DETAIL_STOCK_INQUIRY = FileLoader
			.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "Detailstock.gif");
													
	ImageIcon TCOMM_CHECKING = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "tcomm2.gif");
	ImageIcon HHC_IM_ERROR_MANAGEMENT = FileLoader
			.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "hhcIMErrorMgt.gif");
	ImageIcon REFRESH = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "refresh1.gif");
	ImageIcon SEBU_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "Sebu.gif");
	ImageIcon HELP_ICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "Help.png");

	ImageIcon Averageactualcost = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "period.gif");

	ImageIcon CLEARALL = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "Clearall.gif");
	ImageIcon RECALCULATE = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "Recalculate.gif");

	ImageIcon PRODUCTION_COST = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "prodcostInfo.gif");

	ImageIcon RECONCILIATION_COST = FileLoader
			.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "reconciliationCost.gif");

	ImageIcon TRUCK_MASTER = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "truck_master.gif");

	ImageIcon CARRIER_TRUCK_ASSIGNMENT = FileLoader
			.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "carrierAssignment.gif");

	ImageIcon PERIOD_COST = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "periodcost.gif");

	ImageIcon MDB_DOWN_LOAD = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "access.jpg");
	ImageIcon TEXT_DOWN_LOAD = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "notepad.jpg");
	ImageIcon PDF_DOWN_LOAD = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "PDF.jpg");
	
	ImageIcon ICON_BOX = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "iconBox.png");
	ImageIcon ICON_FOLDER = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "iconFolder.png");
	ImageIcon ICON_DOCUMENT = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "iconDocument.png");
	ImageIcon ICON_CONTAINER = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "iconContainer.png");
	
	ImageIcon ICON_CUT= FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "icon_cut.png");
	ImageIcon ICON_PASTE= FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "icon-paste.png");
	
	ImageIcon ICON_VIEW_THUMBNAIL= FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "viewThumbnail.png");
	ImageIcon ICON_IMPORT= FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "import.png");
	ImageIcon ICON_SPLIT= FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "split.png");
	ImageIcon ICON_MOVE= FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "move.png");
	ImageIcon ICON_REVERSE= FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "reverse.png");
	ImageIcon ICON_DELETE= FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "iconDelete.png");
	ImageIcon ICON_CHANGE = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "iconChange.png");
	ImageIcon ICON_FULL_SCREEN = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "iconFullScreen.png");
	ImageIcon ICON_NEXT = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "iconNext.png");
	ImageIcon ICON_PREVIOUS = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "iconPrevious.png");
	ImageIcon ICON_ZOOMIN = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "iconZoomIn.png");
	ImageIcon ICON_ZOOMOUT = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "iconZoomOut.png");
	
	ImageIcon AETHON_LOGO = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "Aethon.png");
	ImageIcon AETHON_FEVICON = FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "AethonFevicon.png");
		
	ImageIcon UPLOAD_PROGRESS= FileLoader.loadImage(ApplicationConstants.IMAGE_DIR + File.separator + "upload_progress.gif");

}

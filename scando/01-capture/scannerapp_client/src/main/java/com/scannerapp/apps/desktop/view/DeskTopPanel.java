package com.scannerapp.apps.desktop.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;

import com.scannerapp.apps.component.StyledButton;
import com.scannerapp.apps.component.StyledPanel;
import com.scannerapp.apps.framework.view.BaseJInternalFrame;
import com.scannerapp.apps.framework.view.BaseJPanel;
import com.scannerapp.apps.framework.view.ClientSessionInfo;
import com.scannerapp.apps.framework.view.TaskAction;
import com.scannerapp.apps.utils.GeneralUtils;
import com.scannerapp.resources.IconRepository;

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
 * Company: System Plus P. Ltd
 * </p>
 * 
 * @author Kanhaiya Samariya
 * @author Shuchi Sharma
 * @version 1.0
 */
public class DeskTopPanel extends BaseJPanel {
	private BorderLayout borderLayout1 = new BorderLayout();
	private StyledPanel jpnlMain = new StyledPanel();
	public StyledPanel jpnlMenu = new StyledPanel();
	public StyledPanel jpnlToolBar = new StyledPanel();
	private StatusJPanel jpnlStatus = new StatusJPanel();
	public ExtendedDeskTopPane eDesktopPane;
	private EToolBar eToolbar;
	private Action refreshAction;
	public static DeskTopPanel desktopPanel;
	private BorderLayout borderLayout2 = new BorderLayout();
	private Hashtable hBundle = null;

	// start DestTopJPanle#13
	private StyledButton jToolButtonTransferOut = new StyledButton();
	private StyledButton jToolButtonTransferIn = new StyledButton();
	private StyledButton jToolButtonPurchaseReceipt = new StyledButton();
	private StyledButton jToolButtonPurchaseReturn = new StyledButton();
	private StyledButton jToolButtonInventoryTransformation = new StyledButton();
	private StyledButton jToolButtonMiscellneous = new StyledButton();
	private StyledButton jToolButtonRouteShipment = new StyledButton();
	private StyledButton jToolButtonReverseRouteShipment = new StyledButton();
	private StyledButton jToolButtonProductionReceipt = new StyledButton();
	private StyledButton jToolButtonProductionReturn = new StyledButton();
	private StyledButton jToolButtonPhysicalInventory = new StyledButton();
	private StyledButton jToolButtonPickingSequence = new StyledButton();
	private StyledButton jToolButtonDocumentSearch = new StyledButton();
	private StyledButton jToolButtonStockInquiry = new StyledButton();

	private StyledButton jToolButtontcommsChecking = new StyledButton();// Added
																		// for
																		// DestTopJPanle#15
	private StyledButton jToolButtonHHCIMErrorMgt = new StyledButton();// Added
																		// for
																		// DestTopJPanle#15

	private StyledButton jToolButtonProductionCostInformation = new StyledButton();
	private StyledButton jToolButtonReconsilation = new StyledButton();
	private StyledButton jToolButtonPeriodClosing = new StyledButton();
	private StyledButton jToolButtonPeriodCost = new StyledButton();
	private StyledButton jToolButtonTruckMaster = new StyledButton();
	private StyledButton jToolButtonCarrierTruckAssignement = new StyledButton();
	private StyledButton jToolButtonRefresh = new StyledButton();

	// end DestTopJPanle#13

	public DeskTopPanel() {
		try {
			setController(new DeskTopPanelController(this));
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void jbInit() throws Exception {
		hBundle = GeneralUtils.getDesktopBundle();

		this.setLayout(borderLayout1);
		this.add(jpnlMenu, BorderLayout.NORTH);
		this.add(jpnlMain, BorderLayout.CENTER);
		this.add(jpnlStatus, BorderLayout.SOUTH);
		jpnlMenu.setLayout(borderLayout2);
		jpnlMain.setLayout(new BorderLayout());
		eDesktopPane = new ExtendedDeskTopPane();
		jpnlMain.add(jpnlToolBar, BorderLayout.NORTH);
		eToolbar = new EToolBar();

		// eToolbar.setLayout(new FlowLayout());
		Hashtable hScreenRights = ClientSessionInfo.getInstance()
				.getScreenRights();

		refreshAction = new TaskAction(
				hBundle.get("jmnuitmRefresh").toString(), hBundle.get(
						"jmnuitmRefresh").toString(), IconRepository.REFRESH) {
			public void actionPerformed(ActionEvent anEvent) {
				DeskTopPanel.getInstance().setCursor(
						Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); // added
																			// for
																			// DestTopJPanle#18
				GeneralUtils.refresh();
				DeskTopPanel.getInstance().setCursor(
						Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));// added
																			// for
																			// DestTopJPanle#18
			}
		};

		// end DestTopJPanle#13
		eToolbar.addSeparator();

		jpnlToolBar.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 2));
		jpnlToolBar.add(eToolbar);
		eDesktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		jpnlMain.add(eDesktopPane, BorderLayout.CENTER);

	}

	public DeskTopPanelController controller() {
		return (DeskTopPanelController) getController();
	}

	public static DeskTopPanel getInstance() {
		if (desktopPanel == null) {
			desktopPanel = new DeskTopPanel();
		}
		return desktopPanel;
	}

	public StatusJPanel getStatusPanel() {
		return jpnlStatus;
	}

	public BaseJInternalFrame[] getInternalFrames() {
		Component[] components = eDesktopPane.getComponents();
		Vector frames = new Vector();
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof ExtendedDeskTopPane) {
				ExtendedDeskTopPane panel = (ExtendedDeskTopPane) components[i];
				BaseJInternalFrame[] internalFrames = panel.getInternalFrames();
				for (int f = 0; f < internalFrames.length; f++) {
					frames.addElement(internalFrames[f]);
				}
			}
		}
		BaseJInternalFrame[] internalFrames = new BaseJInternalFrame[frames
				.size()];
		for (int i = 0; i < frames.size(); i++) {
			if (frames.elementAt(i) instanceof BaseJInternalFrame) {
				internalFrames[i] = (BaseJInternalFrame) frames.elementAt(i);
			}
		}
		debug("internalFrames count : " + internalFrames.length);
		return internalFrames;
	}

	public ExtendedDeskTopPane getDesktopPane() {
		return eDesktopPane;
	}

	public void cleanup() {
		borderLayout1 = null;
		jpnlMenu = null;
		jpnlToolBar = null;
		jpnlStatus = null;
		eDesktopPane = null;
		desktopPanel = null;
		borderLayout2 = null;
	}

	public void setNewDeskTopPanel() {
		hBundle = GeneralUtils.getDesktopBundle();

		jpnlMain.add(jpnlToolBar, BorderLayout.NORTH);
		eToolbar = new EToolBar();
		Hashtable hScreenRights = ClientSessionInfo.getInstance()
				.getScreenRights();

		refreshAction = new TaskAction(
				hBundle.get("jmnuitmRefresh").toString(), hBundle.get(
						"jmnuitmRefresh").toString(), IconRepository.REFRESH) {
			public void actionPerformed(ActionEvent anEvent) {
				DeskTopPanel.getInstance().setCursor(
						Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); // added
																			// for
																			// DestTopJPanle#18
				GeneralUtils.refresh();
				DeskTopPanel.getInstance().setCursor(
						Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); // added
																			// for
																			// DestTopJPanle#18
			}
		};

		jToolButtonTransferOut = new StyledButton();
		jToolButtonTransferIn = new StyledButton();
		jToolButtonPurchaseReceipt = new StyledButton();
		jToolButtonPurchaseReturn = new StyledButton();
		jToolButtonInventoryTransformation = new StyledButton();
		jToolButtonMiscellneous = new StyledButton();
		jToolButtonRouteShipment = new StyledButton();
		jToolButtonReverseRouteShipment = new StyledButton();
		jToolButtonProductionReceipt = new StyledButton();
		jToolButtonProductionReturn = new StyledButton();
		jToolButtonPhysicalInventory = new StyledButton();
		jToolButtonPickingSequence = new StyledButton();
		jToolButtonDocumentSearch = new StyledButton();
		jToolButtonStockInquiry = new StyledButton();
		jToolButtonProductionCostInformation = new StyledButton();
		jToolButtonReconsilation = new StyledButton();
		jToolButtonPeriodClosing = new StyledButton();
		jToolButtonPeriodCost = new StyledButton();
		jToolButtonTruckMaster = new StyledButton();
		jToolButtonCarrierTruckAssignement = new StyledButton();
		jToolButtonRefresh = new StyledButton();
		jToolButtontcommsChecking = new StyledButton();// Added for
														// DestTopJPanle#15
		jToolButtonHHCIMErrorMgt = new StyledButton();// Added for
														// DestTopJPanle#15

		eToolbar.add(jToolButtonTransferOut);
		jToolButtonTransferOut.setText(hBundle.get("JbuttonTransferOutText")
				.toString());
		jToolButtonTransferOut.setHorizontalTextPosition(JLabel.CENTER);
		jToolButtonTransferOut.setVerticalTextPosition(JLabel.BOTTOM);

		eToolbar.add(jToolButtonTransferIn);
		jToolButtonTransferIn.setText(hBundle.get("JbuttonTransferInText")
				.toString());
		jToolButtonTransferIn.setHorizontalTextPosition(JLabel.CENTER);
		jToolButtonTransferIn.setVerticalTextPosition(JLabel.BOTTOM);

		eToolbar.add(jToolButtonPurchaseReceipt);
		jToolButtonPurchaseReceipt.setText(hBundle.get(
				"JbuttonPurchaseReceiptText").toString());
		jToolButtonPurchaseReceipt.setHorizontalTextPosition(JLabel.CENTER);
		jToolButtonPurchaseReceipt.setVerticalTextPosition(JLabel.BOTTOM);

		eToolbar.add(jToolButtonPurchaseReturn);
		jToolButtonPurchaseReturn.setText(hBundle.get(
				"JbuttonPurchaseReturnText").toString()); 
		jToolButtonPurchaseReturn.setHorizontalTextPosition(JLabel.CENTER);
		jToolButtonPurchaseReturn.setVerticalTextPosition(JLabel.BOTTOM);

		eToolbar.add(jToolButtonInventoryTransformation);
		jToolButtonInventoryTransformation.setText(hBundle.get(
				"JbuttonInventoryTransformationText").toString());
		jToolButtonInventoryTransformation
				.setHorizontalTextPosition(JLabel.CENTER);
		jToolButtonInventoryTransformation
				.setVerticalTextPosition(JLabel.BOTTOM);

		eToolbar.add(jToolButtonMiscellneous);
		jToolButtonMiscellneous.setText(hBundle.get("JbuttonMiscellaneousText")
				.toString());
		jToolButtonMiscellneous.setHorizontalTextPosition(JLabel.CENTER);
		jToolButtonMiscellneous.setVerticalTextPosition(JLabel.BOTTOM);

		eToolbar.add(jToolButtonRouteShipment);
		jToolButtonRouteShipment.setText(hBundle
				.get("JbuttonRouteShipmentText").toString());
		jToolButtonRouteShipment.setHorizontalTextPosition(JLabel.CENTER);
		jToolButtonRouteShipment.setVerticalTextPosition(JLabel.BOTTOM);

		eToolbar.add(jToolButtonReverseRouteShipment);
		jToolButtonReverseRouteShipment.setText(hBundle.get(
				"JbuttonReverseRouteShipmentText").toString());
		jToolButtonReverseRouteShipment
				.setHorizontalTextPosition(JLabel.CENTER);
		jToolButtonReverseRouteShipment.setVerticalTextPosition(JLabel.BOTTOM);

		eToolbar.add(jToolButtonProductionReceipt);
		jToolButtonProductionReceipt.setText(hBundle.get(
				"JbuttonProductionReceiptText").toString());
		jToolButtonProductionReceipt.setHorizontalTextPosition(JLabel.CENTER);
		jToolButtonProductionReceipt.setVerticalTextPosition(JLabel.BOTTOM);

		eToolbar.add(jToolButtonProductionReturn);
		jToolButtonProductionReturn.setText(hBundle.get(
				"JbuttonProductionReturnText").toString());
		jToolButtonProductionReturn.setHorizontalTextPosition(JLabel.CENTER);
		jToolButtonProductionReturn.setVerticalTextPosition(JLabel.BOTTOM);

		eToolbar.add(jToolButtonPhysicalInventory);
		jToolButtonPhysicalInventory.setText(hBundle.get(
				"JbuttonPhysicalInventoryText").toString());
		jToolButtonPhysicalInventory.setHorizontalTextPosition(JLabel.CENTER);
		jToolButtonPhysicalInventory.setVerticalTextPosition(JLabel.BOTTOM);

		// Added for DestTopJPanle#14
		eToolbar.add(jToolButtonPickingSequence);
		jToolButtonPickingSequence.setText(hBundle.get(
				"JbuttonPickingSequenceText").toString());
		jToolButtonPickingSequence.setHorizontalTextPosition(JLabel.CENTER);
		jToolButtonPickingSequence.setVerticalTextPosition(JLabel.BOTTOM);

		// finished for DestTopJPanle#14

		eToolbar.add(jToolButtonDocumentSearch);
		jToolButtonDocumentSearch.setText(hBundle.get(
				"JbuttonDocumentSearchText").toString());
		jToolButtonDocumentSearch.setHorizontalTextPosition(JLabel.CENTER);
		jToolButtonDocumentSearch.setVerticalTextPosition(JLabel.BOTTOM);

		eToolbar.add(jToolButtonPeriodClosing);
		jToolButtonPeriodClosing.setText(hBundle.get("JbuttonPeriodcloseText")
				.toString());
		jToolButtonPeriodClosing.setHorizontalTextPosition(JLabel.CENTER);
		jToolButtonPeriodClosing.setVerticalTextPosition(JLabel.BOTTOM);

		jToolButtonRefresh.setAction(refreshAction);
		eToolbar.add(jToolButtonRefresh);
		jToolButtonRefresh
				.setText(hBundle.get("JbuttonRefershText").toString());
		jToolButtonRefresh.setHorizontalTextPosition(JLabel.CENTER);
		jToolButtonRefresh.setVerticalTextPosition(JLabel.BOTTOM);

		// end DestTopJPanle#13
		eToolbar.addSeparator();
		jpnlToolBar.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 2));
		jpnlToolBar.add(eToolbar);
	}

	public void setComponentsNull() {
		eToolbar = null;

		hBundle = null;

		// start DestTopJPanle#13
		jToolButtonTransferOut = null;
		jToolButtonTransferIn = null;
		jToolButtonPurchaseReceipt = null;
		jToolButtonPurchaseReturn = null;
		jToolButtonInventoryTransformation = null;
		jToolButtonMiscellneous = null;
		jToolButtonRouteShipment = null;
		jToolButtonReverseRouteShipment = null;
		jToolButtonProductionReceipt = null;
		jToolButtonProductionReturn = null;
		jToolButtonPhysicalInventory = null;
		jToolButtonPickingSequence = null;
		jToolButtonDocumentSearch = null;
		jToolButtonStockInquiry = null;
		jToolButtonProductionCostInformation = null;
		jToolButtonReconsilation = null;
		jToolButtonPeriodClosing = null;
		jToolButtonPeriodCost = null;
		jToolButtonTruckMaster = null;
		jToolButtonCarrierTruckAssignement = null;
		jToolButtonRefresh = null;
		jToolButtontcommsChecking = null;
		jToolButtonHHCIMErrorMgt = null;
		// end DestTopJPanle#13
	}
}

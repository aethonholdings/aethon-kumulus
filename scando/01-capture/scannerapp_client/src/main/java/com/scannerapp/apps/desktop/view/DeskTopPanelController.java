package com.scannerapp.apps.desktop.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.Hashtable;

import javax.swing.AbstractAction;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import com.scannerapp.apps.framework.view.BaseController;
import com.scannerapp.apps.framework.view.BaseJInternalFrame;
import com.scannerapp.apps.login.view.LoginHelper;
import com.scannerapp.apps.login.view.LoginJPanel;
import com.scannerapp.common.SessionInfo;

/*
 *======================Modification History======================
 *
 * 	 Date      		 Author          Code #        			 Description of Modifications
 * --------  	----------------  	--------  			--------------------------------------
 * 21/11/2011	Shalin Shah	      DeskPanCtrl#1		Client Issue# 335 (OMS) --- AUTO LOGOFF AFTER 10 MINUTES IF SYSTEM REMAINS IDLE.
 **/

public class DeskTopPanelController extends BaseController {
	private Hashtable _frameListeners = new Hashtable();

	public DeskTopPanelController() {
		super();
	}

	public DeskTopPanelController(Component view) {
		super(view);
	}

	public DeskTopPanel view() {
		return (DeskTopPanel) getView();
	}

	public void addInternalFrame(BaseJInternalFrame frame) {

		// Add a listener for the frame's events
		InternalFrameListener frameListener = new InternalFrameAdapter() {
			public void internalFrameActivated(InternalFrameEvent e) {

				// internalFrame_Activated(e);
			}

			public void internalFrameClosed(InternalFrameEvent e) {

				// internalFrame_Closed(e);
				removeFromWindowMenu();
			}
		};
		frame.addInternalFrameListener(frameListener);

		// Save frame/listener pair for removal later
		_frameListeners.put(frame, frameListener);
		view().eDesktopPane.add(frame, JLayeredPane.PALETTE_LAYER);
		JMenuItem mi = new JMenuItem(frame.getTitle());
		mi.addActionListener(new SelectFrameAction(frame));
	}

	public void removeFromWindowMenu() {

		System.runFinalization();
		System.gc();
	}

	private static class SelectFrameAction extends AbstractAction {
		BaseJInternalFrame frame = null;

		SelectFrameAction(BaseJInternalFrame frame) {
			super(frame.getName());
			this.frame = frame;
		}

		public void actionPerformed(ActionEvent event) {
			try {
				frame.setIcon(false);
				frame.getDesktopPane().getDesktopManager().activateFrame(frame);
				frame.setSelected(true);
			} catch (Exception pe) {

			}
		}
	}

	// ADDED By SHALIN SHAH For DeskPanCtrl#1 --- CLIENT ISSUE 335 (OMS)

	public void autoLogoff() {
		BaseJInternalFrame internalFrame[] = view().eDesktopPane
				.getInternalFrames();
		// Hashtable hBundle = GeneralUtils.getDesktopBundle();

		for (int i = 0; i < internalFrame.length; i++) {
			internalFrame[i].dispose();
		}

		SessionInfo sInfo = SessionInfo.getInstance();
		try {
			LoginHelper helper = new LoginHelper();
		} catch (Exception e) {
		}

		sInfo.EmptyOriginalPackProducts();
		System.runFinalization();
		System.gc();

		DeskTopFrame.getInstance().getContentPane().removeAll();
		LoginJPanel jpnlLogin = new LoginJPanel();
		DeskTopFrame.getInstance().getContentPane()
				.setLayout(new BorderLayout());
		DeskTopFrame.getInstance().getContentPane()
				.add(jpnlLogin, BorderLayout.CENTER);
		DeskTopFrame.getInstance().repaint();
		DeskTopFrame.getInstance().validate();
		jpnlLogin.resetForLogin();
		DeskTopFrame.getInstance().getDesktopPanel().getStatusPanel()
				.clearStatusText();
		/*
		 * DeskTopFrame .getInstance() .getDesktopPanel() .getStatusPanel()
		 * .setLoginText( hBundle.get("jlblLanguage").toString() +
		 * hBundle.get("language").toString() + "     " +
		 * hBundle.get("jlblLocation").toString() +
		 * SessionInfo.getInstance().getLocation() + "-" +
		 * SessionInfo.getInstance().getLocationDesc());
		 */

		// DeskTopFrame.getInstance().setTitle("Scanner App v1.0 Admin Warehouse");

		// MyAspect.inactivityListener.stop();
		// MyAspect.inactivityListener = null;

		SessionInfo.setForceLogout(true);
	}

	// FINISHED By SHALIN SHAH For DeskPanCtrl#1 --- CLIENT ISSUE 335 (OMS)

}

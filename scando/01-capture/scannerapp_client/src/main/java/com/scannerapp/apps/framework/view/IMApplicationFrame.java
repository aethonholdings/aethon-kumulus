package com.scannerapp.apps.framework.view;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.scannerapp.apps.utils.ConstantUtil;

public class IMApplicationFrame extends JFrame {
	private BaseController controller = null;
	private GridBagLayout gridBagLayout1 = new GridBagLayout();

	// BorderLayout _frameLayout = new BorderLayout();

	// Constructor
	public IMApplicationFrame() {
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			setController(new IMApplicationFrameController(this));
			jbInit();
			init();
			addListeners();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Component initialization
	private void init() throws Exception {

		this.setSize(new Dimension(800, 700));

		this.setTitle(ConstantUtil.getApplicationConstant("applicationName")
				+ "(" + ConstantUtil.getApplicationConstant("clientVersion")
				+ ")");
	}

	// Return frame's controller
	public BaseController getController() {
		return controller;
	}

	// Set frame's controller
	public void setController(BaseController controller) {
		this.controller = controller;
	}

	// Overriden so we can exit on System Close
	protected void processWindowEvent(WindowEvent e) {
		if (getController() != null) {
			if (e.getID() == WindowEvent.WINDOW_CLOSING) {
				boolean close = getController().confirmWindowClose();
				if (!close) {
					return;
				}
				if (close) {
					getController().windowClosing(e.getWindow());
				}
			} // end method
		}
		super.processWindowEvent(e);
	}

	// Override to add listeners
	protected void addListeners() {

	}

	// Override to dispose frame resources
	public void dispose() {
		setController(null);
		removeAll();
		removeNotify();
		super.dispose();
	}

	private void jbInit() throws Exception {
		this.getContentPane().setLayout(gridBagLayout1);
	}
}

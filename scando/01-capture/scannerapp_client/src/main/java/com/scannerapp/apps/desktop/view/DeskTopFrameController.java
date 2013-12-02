package com.scannerapp.apps.desktop.view;

import java.awt.Component;

import com.scannerapp.apps.framework.view.ErrorMessage;
import com.scannerapp.apps.framework.view.IMApplicationFrameController;

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
 * @version 1.0
 */
public class DeskTopFrameController extends IMApplicationFrameController {
	public DeskTopFrameController() {
		super();
		initialize();
	}

	public DeskTopFrameController(Component view) {
		this();
		setView(view);
	}

	public DeskTopFrame view() {
		return (DeskTopFrame) getView();
	}

	public void initialize() {
		super.initialize();
		ErrorMessage.readMessageBundles();

		// Set the Minimized Icon for the Application

		// ImageIcon icon =
		// getProperties().getImageLoader().getImageIcon(IMAGE_PKG + DSW_ICON);

		// view().setIconImage(icon.getImage());

		// Initialize the Frame's panel

		// view().desktopPanel.getController().initialize();
	}

	public void windowClosing(Object window) {
		super.windowClosing(window);
		((java.awt.Window) (java.awt.Container) getView()).dispose();
		ErrorMessage.clearMessageBundles();
		System.gc();
		System.runFinalization();
		System.exit(0);
	}
}

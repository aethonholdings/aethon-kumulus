package com.scannerapp.apps.desktop.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import org.apache.log4j.Logger;

import com.scannerapp.apps.framework.view.ErrorMessage;
import com.scannerapp.apps.framework.view.IMApplicationFrame;
import com.scannerapp.apps.login.view.LoginJPanel;
import com.scannerapp.resources.IconRepository;

public class DeskTopFrame extends IMApplicationFrame {
	
	private static DeskTopFrame desktopFrame = null;
	
	private DeskTopFrameController controller;
	private DeskTopPanel desktopPanel = null;
	private LoginJPanel jpnlLogin = null;
	
	private static Logger log = Logger.getLogger(DeskTopFrame.class);

	public DeskTopFrame() {
		super();
		
		try {
			controller = new DeskTopFrameController(this);
			doLogin();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Return the frame's controller
	public DeskTopFrameController controller() {

		return controller;
	}

	// Return the frame's singleton instance
	public static DeskTopFrame getInstance() {
		if (desktopFrame == null) {
			desktopFrame = new DeskTopFrame();
		}
		return desktopFrame;
	}

	// Return the frame's panel
	public DeskTopPanel getDesktopPanel() {
		if (desktopPanel == null) {
			desktopPanel = DeskTopPanel.getInstance();
		}
		return desktopPanel;
	}

	public void setDesktopPanel(DeskTopPanel desktopPanel) {
		this.desktopPanel = desktopPanel;
	}

	// Return the frame's preferred size.
	public Dimension getPreferredSize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return new Dimension(screenSize.width, screenSize.height - 20);
	}

	public void doLogin() {
		
		if (jpnlLogin == null) {
			jpnlLogin = new LoginJPanel();
		}
		
		getContentPane().setLayout(new BorderLayout());
		
		this.getContentPane().add(jpnlLogin, BorderLayout.CENTER);
		this.setIconImage(IconRepository.AETHON_FEVICON.getImage());
		
		repaint();
		validate();
	}

	public void createDeskTop() {
		
		this.getContentPane().removeAll();
		
		if (jpnlLogin != null) {
			jpnlLogin.cleanup();
		}
		
		jpnlLogin = null;
		getContentPane().setLayout(new BorderLayout());

		this.getContentPane().add(getDesktopPanel(), BorderLayout.CENTER);
	}

	public LoginJPanel getJpnlLogin() {
		return jpnlLogin;
	}

	// Method to be called 1st on all window event.
	protected void processWindowEvent(WindowEvent e) {

		if (e.getID() == WindowEvent.WINDOW_CLOSING) {

			if (jpnlLogin != null && jpnlLogin.controller() != null) {

				boolean isImageUploadingInProcess = jpnlLogin.controller()
						.isImageUploadingInProcess();

				if (isImageUploadingInProcess) {
					ErrorMessage.displayMessage('I',
							"canNotCloseWindowImageUploadingInProcess");
					return;
				}
			}
		}

		super.processWindowEvent(e);
	}
}

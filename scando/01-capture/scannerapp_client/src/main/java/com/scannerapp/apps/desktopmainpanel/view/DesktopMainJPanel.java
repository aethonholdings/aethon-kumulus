package com.scannerapp.apps.desktopmainpanel.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.apache.log4j.Logger;

import com.scannerapp.apps.desktopleftpanel.view.DesktopLeftJPanel;
import com.scannerapp.apps.desktoprightpanel.view.DesktopRightJPanel;
import com.scannerapp.apps.utils.SessionUtil;
import com.scannerapp.resources.IconRepository;

//public class DesktopMainJPanel extends BaseJPanel implements IconRepository, FocusListener, java.awt.event.ActionListener {
public class DesktopMainJPanel extends JPanel implements IconRepository,
		FocusListener, java.awt.event.ActionListener {

	private DesktopLeftJPanel jLeftPanel = new DesktopLeftJPanel(this);
	private DesktopRightJPanel jRightPanel = new DesktopRightJPanel(this);
	private static Logger log = Logger.getLogger(DesktopMainJPanel.class);

	public DesktopMainJPanel() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	void jbInit() throws Exception {
		JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				true, jLeftPanel, jRightPanel);

		setLayout(new BorderLayout());

		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		// int ySize = ((int) tk.getScreenSize().getHeight());

		int x = (int) xSize * 25 / 100;

		splitPanel.setDividerLocation(x);

		splitPanel.setOneTouchExpandable(true);

		this.add(splitPanel);

                // RAJ COMMENTS OUT
		// jLeftPanel.fetchChildNodes(SessionUtil.getSessionData().getProjectId(),	null);
                // END RAJ COMMENTS OUT
	}
        
       

	// Return the frame's preferred size.
	public Dimension getPreferredSize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return new Dimension(screenSize.width, screenSize.height - 20);
	}

	/**
	 * Method to check if Image Uploading (Import or Resume Import) in process.
	 * 
	 * @return flag to indicate if image uploading (Import or Resume Import) in
	 *         process
	 */
	public boolean isImageUploadingInProcess() {

		if (jRightPanel != null
				&& jRightPanel.getImportAndSepPanel() != null
				&& jRightPanel.getImportAndSepPanel().getImageUploader() != null
				&& (jRightPanel.getImportAndSepPanel().getImageUploader()
						.isImportInProcess() || jRightPanel
						.getImportAndSepPanel().getImageUploader()
						.isResumeImportInProcess())) {

			return true;

		} else {

			return false;
		}
	}

	public void actionPerformed(ActionEvent e) {

	}

	public void cleanup() {
		jLeftPanel = null;
	}

	// @Override
	public void focusGained(FocusEvent arg0) {

	}

	// @Override
	public void focusLost(FocusEvent e) {

	}

	public DesktopLeftJPanel getjLeftPanel() {
		return jLeftPanel;
	}

	public DesktopRightJPanel getjRightPanel() {
		return jRightPanel;
	}

    public void updatejleftPanel() {
       jLeftPanel.fetchChildNodes(SessionUtil.getSessionData().getProjectId(),	null); //To change body of generated methods, choose Tools | Templates.
    }
}

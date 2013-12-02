package com.scannerapp.apps.framework.view;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

import com.scannerapp.apps.login.view.LoginHelper;
import com.scannerapp.apps.utils.GeneralUtils;
import com.scannerapp.apps.utils.SessionUtil;

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
public class IMApplicationFrameController extends BaseController {

	private LoginHelper loginHelper;

	public IMApplicationFrameController() {
		super();
		if (loginHelper == null)
			loginHelper = new LoginHelper();
	}

	public IMApplicationFrameController(Component view) {
		this();
		setView(view);
		if (loginHelper == null)
			loginHelper = new LoginHelper();
	}

	/*
	 * public IMApplicationFrame view() { return (IMApplicationFrame)getView();
	 * }
	 */
	public boolean confirmWindowClose() {
		String arStr[] = new String[2];
		arStr[0] = GeneralUtils.getDesktopBundle().get("jlblYES").toString();
		arStr[1] = GeneralUtils.getDesktopBundle().get("jlblNO").toString();
		int answer = JOptionPane.showOptionDialog(getView(), GeneralUtils
				.getDesktopBundle().get("jmsgExit").toString(), GeneralUtils
				.getDesktopBundle().get("jmsgExitHeader").toString(),
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				arStr, arStr);
		// int answer = JOptionPane.showConfirmDialog(getView(),
		// GeneralUtils.getDesktopBundle().get("jmsgExit").toString(),
		// GeneralUtils.getDesktopBundle().get("jmsgExitHeader").toString(),
		// JOptionPane.YES_NO_OPTION);
		switch (answer) {
		case JOptionPane.YES_OPTION:
			if (SessionUtil.getSessionData() != null) {
				updateLogoutTime();
				try 
				{
					removeTemporaryDirectory();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
					System.out.println("Error Generated While Cleaning Temporary Storage Directory.");
				}
			}
			((java.awt.Window) (java.awt.Container) getView()).dispose();
			System.gc();
			System.runFinalization();
			System.exit(0);

			return true;

		case JOptionPane.NO_OPTION:
			return false;

		case JOptionPane.CANCEL_OPTION:
			return false;
		}
		return false;
	}

	private void removeTemporaryDirectory() throws IOException 
	{
		File localDirectory = new File(SessionUtil.getSessionData().getLocalStoragePath());
		File localThumbnailDirectory = new File(SessionUtil.getSessionData().getLocalThumbnailDirPath());
		
		if(localDirectory.exists())
		{
			FileUtils.cleanDirectory(localDirectory);
		}
		
		if(localThumbnailDirectory.exists())
		{
			FileUtils.cleanDirectory(localThumbnailDirectory);
		}
	}

	private void updateLogoutTime() {		

		Timer updateAttendance = new Timer(false);
		UpdateAttendance timerTask = new UpdateAttendance();
		updateAttendance.schedule(timerTask, 0, 1000);
	}

	// Inner Class For Timer...
	private class UpdateAttendance extends TimerTask {

		@Override
		public void run() {

			System.out.println("LogOut Time: " + new Date());

			ArrayList<String> loginCredentials = new ArrayList<String>();

			loginCredentials.add(SessionUtil.getSessionData().getUserId());
			loginCredentials.add(SessionUtil.getSessionData().getProjectId());
			// Added o(Zero) as a BREATH_INTERVAL because no need to add breath_Interval at logout time
			loginCredentials.add("0");

			if (!loginHelper.updateAttendance(loginCredentials)) {
				ErrorMessage
						.displayMessage('E', "errorWhileUpdatingAttendance");
			}
		}
	}
}

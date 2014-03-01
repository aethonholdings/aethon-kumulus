package com.scannerapp.apps.login.view;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.scannerapp.apps.desktop.view.DeskTopFrame;
import com.scannerapp.apps.desktopmainpanel.view.DesktopMainJPanel;
import com.scannerapp.apps.desktoprightpanel.view.ImportSaparationPanel;
import com.scannerapp.apps.framework.view.BaseController;
import com.scannerapp.apps.framework.view.ErrorMessage;
import com.scannerapp.apps.utils.ConstantUtil;
import com.scannerapp.apps.utils.SessionUtil;
import com.scannerapp.shared.SessionData;

public class LoginJPanelController extends BaseController {

	private static Logger log = Logger.getLogger(LoginJPanelController.class); // For
																				// Log4j
	private LoginHelper loginHelper;
	private HashMap<String, String> projectList;
	private DesktopMainJPanel mainPanel;

	public LoginJPanelController(LoginJPanel jPanel) {
		super(jPanel);
		loginHelper = new LoginHelper();
		initializeScreen();
	}
        
        public LoginJPanelController() {
		
	}

	public void initialize() {
		super.initialize();
	}

	public void initializeScreen() {

		/*
		 * // Default Item will be blank value.
		 * view().getJcmbProject().addItem("");
		 */
//		//projectList = loginHelper.getProjectList();
//
//		if (projectList == null) {
//			// TODO : Show popup about error while fetch project list.
//			return;
//		}
//
//		ArrayList<String> projectIdList = new ArrayList<String>(
//				projectList.keySet());
//
//		// Checking Condition If only One Project Is Active, It is Preselected
//		if (projectIdList.size() == 1) {
//			view().getJcmbProject().addItem(
//					projectList.get(projectIdList.get(0)));
//		} else {
//			// Default Item will be blank value.
//			view().getJcmbProject().addItem("");
//
//			for (int index = 0; index < projectIdList.size(); index++) {
//				view().getJcmbProject().addItem(
//						projectList.get(projectIdList.get(index)));
//			}
//		}
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		// log.info("Result : " + result);
		formatter.close();
		return result;
	}

	private String encryptPasswordUsingSha1(String password) {
		String sha1 = "";
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(password.getBytes("UTF-8"));
			sha1 = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sha1;
	}

	/**
	 * Action performed on login button click.
	 */
	public void jbtnLogon_actionPerformed() {

		String username = view().getJtxtLogin().getText().trim();
                System.out.println(username);
		String password = view().getJpsdPassword().getText();
//		String projectName = view().getJcmbProject().getSelectedItem()
//				.toString().trim();
//		String projecId = null;
//
//		// Iterating through project list to get project id.
//		ArrayList<String> projectIdList = new ArrayList<String>(
//				projectList.keySet());
//
//		for (int index = 0; index < projectIdList.size(); index++) {
//
//			if (projectName == projectList.get(projectIdList.get(index))) {
//				projecId = projectIdList.get(index);
//				break;
//			}
//		}

		// Calling to authorize login...
		try {

//			if (!username.equals("") && !password.trim().equals("")
//					&& !projectName.toString().trim().equals("")) {
                    if (!username.equals("") && !password.trim().equals("")
					) {

				try {

					if (loginHelper.authorizeLogin(username, password)) {           
                                           
						SessionUtil.setSessionData(loginHelper
						.fetchSessionData());
                                         
                                                setClientVersionInSession();

						//boolean totalUnAuthorizeParam = loginHelper.isAuthorizeApplicationParam(projecId);
                                                boolean totalUnAuthorizeParam =true;
						if (!totalUnAuthorizeParam) {
							ErrorMessage.displayMessage('I',
									"invalidApplicationParameterConfiguration");
							return;
						}

						// Checking Condition If User has No Rights Then Not
						// Allow To Login
						if (SessionUtil.getSessionData().getCollectionRight()
								.equalsIgnoreCase("N")
								&& SessionUtil.getSessionData()
										.getImportRight().equalsIgnoreCase("N")
								&& SessionUtil.getSessionData()
										.getSeparationRight()
										.equalsIgnoreCase("N")) {
							ErrorMessage.displayMessage('I',
									"loginNotPossibleUserHasNoRights");
							view().getJtxtLogin().setText("");
							view().getJpsdPassword().setText("");

//							if (projectList.size() == 1) {
//								view().getJcmbProject().setSelectedItem(
//										projectList.get(projectIdList.get(0)));
//							} else {
//								// Default Item will be blank value.
//								view().getJcmbProject().setSelectedItem("");
//							}

							return;
						}

						// Method to create local directory to store temporary
						// thumbnails, fullscreen images and local path to store
						// images while import
                                                
                                               // new ImportSaparationPanel();
                                                
						createLocalDirectory();

						DeskTopFrame.getInstance().getContentPane().removeAll();

						mainPanel = new DesktopMainJPanel();

						DeskTopFrame.getInstance().getContentPane()
								.add(mainPanel, BorderLayout.WEST);
						DeskTopFrame.getInstance().getContentPane().repaint();
						DeskTopFrame.getInstance().getContentPane().validate();

						DeskTopFrame
								.getInstance()
								.setTitle(
								// DeskTopFrame.getInstance().getTitle()
										ConstantUtil
												.getApplicationConstant("applicationName")
												+ "("
												+ SessionUtil.getSessionData()
														.getVersion()
												+ ")"
												+ "       "
												+ ConstantUtil
														.getApplicationConstant("loginIdLabel")
												+ " : "
												+ username
												+ "       "
												+ ConstantUtil
														.getApplicationConstant("projectLabel")
											);

						//initThreadStartToUpdateAttendanceDetail();     // commented BY Raj
					}

					else {
						ErrorMessage.displayMessage('I',
								"invalidUseridPassword");
					}

				} catch (Exception e) {
					log.debug("Exception occurs while trying to check User is blocked or not"
							+ e);
					e.printStackTrace();
				}

			}

			else if (username.trim().equals("") || password.trim().equals("")) {
				ErrorMessage.displayMessage('I', "enterUsernamePassword");
			}

//			else if (projectName.trim().equals("")) {
//				ErrorMessage.displayMessage('I', "selectProject");
//			}

			else {
				ErrorMessage.displayMessage('I', "invalidUseridPassword");
			}

		} catch (Exception e) {
			log.debug("Comes in main exception block of jbtnLogon_actionPerformed "
					+ e);

		}
	}

	/**
	 * Method to set the client version and set in session data.
	 */
	private void setClientVersionInSession() {

		String clientVersion = ConstantUtil
				.getApplicationConstant("clientVersion");

		SessionUtil.getSessionData().setVersion(clientVersion);

		System.out.println("Welcome To ScanDo Application (Version : "
				+ clientVersion + ")\n\n");

		log.info("Start Executing --- ScanDo Application --- Version : "
				+ clientVersion);
	}

	public void createLocalDirectory() throws IOException {
		log.info("Cleaning Local Directory for Thumbnail...");
		File localThumbnailDirectory = new File(
				ConstantUtil.getApplicationConstant("local_thumbnail_dir_name"));
		String localThumbnailDirectoryPath = localThumbnailDirectory
				.getAbsolutePath();

		log.info("Local Directory Path To Store Thumbnails Images : "
				+ localThumbnailDirectoryPath);
		if (localThumbnailDirectory.exists()) {
			FileUtils.cleanDirectory(localThumbnailDirectory);
		}

		log.info("Cleaning Local Directory for Storage...");
		File localStorageDirectory = new File(
				ConstantUtil.getApplicationConstant("local_storage_dir_name"));
		String localStorageDirectoryPath = localStorageDirectory
				.getAbsolutePath() + File.separator;
		log.info("Local Directory Path for Storage: "
				+ localStorageDirectoryPath);
		if (localStorageDirectory.exists()) {
			FileUtils.cleanDirectory(localStorageDirectory);
		}

		log.info("Creating Local Directory for View Thumbnail....");
		File thumbnailDirectory = new File(localThumbnailDirectoryPath);
		thumbnailDirectory.mkdirs();

		log.info("Creating Local Directory for Storage....");
		localStorageDirectory.mkdirs();

		SessionUtil.getSessionData().setLocalThumbnailDirPath(
				localThumbnailDirectoryPath);
		SessionUtil.getSessionData().setLocalStoragePath(
				localStorageDirectoryPath);

		log.info("Local Thumbnail Path: "
				+ SessionUtil.getSessionData().getLocalThumbnailDirPath());
		log.info("Local Storage Path: "
				+ SessionUtil.getSessionData().getLocalStoragePath());

	}

	private void initThreadStartToUpdateAttendanceDetail() {

		long interval = Long.parseLong(SessionUtil.getSessionData()
				.getRefreshInterval());

		Timer updateAttendance = new Timer(true);
		UpdateAttendance timerTask = new UpdateAttendance();
		updateAttendance.schedule(timerTask, interval, interval);
	}

	/**
	 * Method to check if Image Uploading (Import or Resume Import) in process.
	 * 
	 * @return flag to indicate if image uploading (Import or Resume Import) in
	 *         process
	 */
	public boolean isImageUploadingInProcess() {

		if (mainPanel != null) {
			return mainPanel.isImageUploadingInProcess();
		} else {
			return false;
		}
	}

	// Inner Class For Timer...
	private class UpdateAttendance extends TimerTask {

		@Override
		public void run() {

			updateUserAttendance();
			fetchKPIPerformance();
		}

		private void updateUserAttendance() {

			ArrayList<String> loginCredentials = new ArrayList<String>();

			loginCredentials.add(SessionUtil.getSessionData().getUserId());
			loginCredentials.add(SessionUtil.getSessionData().getProjectId());
			loginCredentials.add(SessionUtil.getSessionData()
					.getBreathInterval());

			log.info("Session Breath Time: "
					+ SessionUtil.getSessionData().getBreathInterval());

			if (!loginHelper.updateAttendance(loginCredentials)) {
				ErrorMessage
						.displayMessage('E', "errorWhileUpdatingAttendance");
			}
		}

		private void fetchKPIPerformance() {

			HashMap<String, String> performanceMap = loginHelper
					.fetchKPIPerformance(SessionUtil.getSessionData()
							.getUserId());

			if (mainPanel != null) {

				mainPanel.getjLeftPanel().setOverallKPILabel(
						performanceMap.get("overallKPIPerformance"));
				mainPanel
						.getjRightPanel()
						.getImportAndSepPanel()
						.setImportKPILabel(
								performanceMap.get("importKPIPerformance"));
				mainPanel
						.getjRightPanel()
						.getImportAndSepPanel()
						.setSeparationKPILabel(
								performanceMap.get("separationKPIPerformance"));
			}
		}
	}

	// ******* GETTER / SETTER METHODS...
	public LoginJPanel view() {
		return (LoginJPanel) getView();
	}
}
package com.scannerapp.apps.framework.view;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Kanhaiya Samariya
 * @version 1.0
 */
import java.util.Hashtable;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.scannerapp.apps.utils.ConstantUtil;
import com.scannerapp.apps.utils.GeneralUtils;
import com.scannerapp.apps.utils.XMLBundleReader;
import com.scannerapp.common.IMModule;
import com.scannerapp.common.IMScreen;
import com.scannerapp.common.SessionInfo;
import com.scannerapp.common.XMLBundle;

public class ErrorMessage extends java.lang.Object {
	protected static Hashtable hMessageBundles = new Hashtable();
	private static Logger log = Logger.getLogger(ErrorMessage.class);// For
																		// Log4j

	public ErrorMessage() {
		super();
		readMessageBundles();
	}

	public static void readMessageBundles() {
		String lang = SessionInfo.getInstance().getLanguage();

		log.debug("Language :: " + lang);
		XMLBundleReader xmlBundleReader = new XMLBundleReader(
				XMLBundle.IM_MESSAGE_XMLBUNDLE);
		hMessageBundles = xmlBundleReader.getObjectKeyValue(
				IMModule.ERROR_MESSAGE, IMScreen.ERROR_MESSAGE, lang);
	}

	public static void clearMessageBundles() {
		if (hMessageBundles != null) {
			hMessageBundles = null;
		}
	}

	public static int displayMessage(final String message, final String title,
			final int optionType, final int messageType, final Icon icon,
			final Object[] options, final Object initialValue) {

		return JOptionPane.showOptionDialog(null, message, title, optionType,
				messageType, icon, options, initialValue);
	}

	public static int displayMessage(char messageType, int messageId) {
		int errorid = 0;

		// Debug.debugLog("The message id is :"+messageId);

		// Debug.debugLog("The bundle id is :"+hMessageBundles);
		String id = "E" + String.valueOf(messageId);
		String message = hMessageBundles.get(id).toString().trim();
		Object[] options = { GeneralUtils.getDesktopBundle().get("jlblOK")
				.toString() };
		// Added for Client Issue#950 by jiten
		JLabel lbltemp = new JLabel();
		lbltemp.setVisible(true);
		lbltemp.setText("");
		Object[] optionsError = {
				GeneralUtils.getDesktopBundle().get("jlblOK").toString(),
				lbltemp };
		// Finished for Client Issue#950 by jiten

		switch (messageType) {
		case 'W':
			errorid = displayMessage(message, WARNING_MSG,
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);
			break;

		case 'Q':
			Object[] optionsYesNo = {
					GeneralUtils.getDesktopBundle().get("jlblYES").toString(),
					GeneralUtils.getDesktopBundle().get("jlblNO").toString() };
			errorid = displayMessage(message, QUESTION_MSG,
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, optionsYesNo, optionsYesNo[0]);
			break;

		case 'I':
			errorid = displayMessage(message, INFORMATION_MSG,
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			break;

		case 'E':
			errorid = displayMessage(message, ERROR_MSG,
					JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
					null, optionsError, optionsError[1]);// changed for Client
															// Issue#950 by
															// jiten
			break;

		case 'P':
			errorid = displayMessage(message, PLAIN_MSG,
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
					null, options, options[0]);
			break;

		default:
			log.debug("ErrorMessage: displayMessage: Invalid message type: "
					+ messageType);
		}
		return errorid;
	}

	public static int displayMessage(char messageType, String propertyKey) {

		int errorid = 0;

		String message = ConstantUtil.getErrorConstant(propertyKey);

		Object[] options = { GeneralUtils.getDesktopBundle().get("jlblOK")
				.toString() };

		JLabel lbltemp = new JLabel();
		lbltemp.setVisible(true);
		lbltemp.setText("");
		Object[] optionsError = {
				GeneralUtils.getDesktopBundle().get("jlblOK").toString(),
				lbltemp };

		switch (messageType) {
		case 'W':
			errorid = displayMessage(message, WARNING_MSG,
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);
			break;

		case 'Q':
			Object[] optionsYesNo = {
					GeneralUtils.getDesktopBundle().get("jlblYES").toString(),
					GeneralUtils.getDesktopBundle().get("jlblNO").toString() };
			errorid = displayMessage(message, QUESTION_MSG,
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, optionsYesNo, optionsYesNo[0]);
			break;

		case 'I':
			errorid = displayMessage(message, INFORMATION_MSG,
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			break;

		case 'E':
			errorid = displayMessage(message, ERROR_MSG,
					JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
					null, optionsError, optionsError[1]);// changed for Client
															// Issue#950 by
															// jiten
			break;

		case 'P':
			errorid = displayMessage(message, PLAIN_MSG,
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
					null, options, options[0]);
			break;

		default:
			log.debug("ErrorMessage: displayMessage: Invalid message type: "
					+ messageType);
		}

		return errorid;
	}
        
        //--RAJ CODE TO DISPLAY CUSTOM ERROR MESSAGE ON IMAGEUPLOAD FAIL
	public static int displayMessage(char messageType, String propertyKey, String propertyKeyMore, int fail, int total) {

		int errorid = 0;

		String message = ConstantUtil.getErrorConstant(propertyKey);
                String messagesuffix = ConstantUtil.getErrorConstant(propertyKeyMore);
                String finalMessage= message+"  " +fail+"/"+total+"  "+messagesuffix;

		Object[] options = { GeneralUtils.getDesktopBundle().get("jlblOK")
				.toString() };

		JLabel lbltemp = new JLabel();
		lbltemp.setVisible(true);
		lbltemp.setText("");
		Object[] optionsError = {
				GeneralUtils.getDesktopBundle().get("jlblOK").toString(),
				lbltemp };

		switch (messageType) {

		case 'E':
			errorid = displayMessage(finalMessage, ERROR_MSG,
					JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
					null, optionsError, optionsError[1]);// changed for Client
			break;
		
		default:
			log.debug("ErrorMessage: displayMessage: Invalid message type: "
					+ messageType);
		}

		return errorid;
	}
        //--RAJ CODE TO DISPLAY CUSTOM ERROR MESSAGE ON IMAGEUPLOAD FAIL
	public static String getMessage(int messageId) {
		String id = "E" + String.valueOf(messageId);
		String message = hMessageBundles.get(id).toString().trim();
		return message;
	}

	private static String ERROR_MSG = GeneralUtils.getDesktopBundle()
			.get("error").toString();
	private static String INFORMATION_MSG = GeneralUtils.getDesktopBundle()
			.get("information").toString();
	private static String WARNING_MSG = GeneralUtils.getDesktopBundle()
			.get("warning").toString();
	private static String QUESTION_MSG = GeneralUtils.getDesktopBundle()
			.get("question").toString();
	private static String PLAIN_MSG = GeneralUtils.getDesktopBundle()
			.get("plainMessage").toString();
}

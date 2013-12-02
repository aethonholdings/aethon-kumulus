package com.scannerapp.ws.common.exception;

import java.io.Serializable;

/**
 * Extension of the GenericException class. The security exceptions are related
 * to the pepsico.architecture.security package.
 * 
 * @author Niraj Salot <niraj.salot@spec-india.com>
 * @since Apr 27, 2011
 */
public class SecurityException extends GenericException implements Serializable {

	public SecurityException() {
	}
	public SecurityException(String errorMessage) {
		super(errorMessage);
	}
	/**
	 * Constructor for the class SecurityException
	 * 
	 * @param errorMessageBundle
	 *            indicates the bundle file where the key is located
	 * @param errorMessageKey
	 *            the key for the error message displayed to the user
	 * @param errorMessage
	 *            exception message (for logging purposes)
	 */
	public SecurityException(String errorMessageBundle, String errorMessageKey,
			String errorMessage) {
		super(errorMessageBundle, errorMessageKey, errorMessage);
	}

	/**
	 * Constructor for the class SecurityException
	 * 
	 * @param errorMessageBundle
	 *            indicates the bundle file where the key is located
	 * @param errorMessageKey
	 *            the key for the error message displayed to the user
	 * @param errorMessage
	 *            exception message (for logging purposes)
	 * @param originalException
	 *            original exception
	 */
	public SecurityException(String errorMessageBundle, String errorMessageKey,
			String errorMessage, Throwable originalException) {
		super(errorMessageBundle, errorMessageKey, errorMessage,
				originalException);
	}
}

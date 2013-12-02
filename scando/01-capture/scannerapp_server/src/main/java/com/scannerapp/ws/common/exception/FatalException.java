package com.scannerapp.ws.common.exception;

import java.io.Serializable;

/**
 * Extension of the GenericException class. The fatal exceptions represent
 * non-recoverable errors.
 * 
 * @version 1.0
 */
public class FatalException extends GenericException implements Serializable {

	public FatalException() {
	}

	public FatalException(String errorMessage) {
		super(errorMessage);
	}	
	/**
	 * Constructor for the class FatalException
	 * 
	 * @param errorMessageBundle
	 *            indicates the bundle file where the key is located
	 * @param errorMessageKey
	 *            the key for the error message displayed to the user
	 * @param errorMessage
	 *            exception message (for logging purposes)
	 */
	public FatalException(String errorMessageBundle, String errorMessageKey,
			String errorMessage) {
		super(errorMessageBundle, errorMessageKey, errorMessage);
	}

	/**
	 * Constructor for the class FatalException
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
	public FatalException(String errorMessageBundle, String errorMessageKey,
			String errorMessage, Throwable originalException) {
		super(errorMessageBundle, errorMessageKey, errorMessage,
				originalException);
	}

}

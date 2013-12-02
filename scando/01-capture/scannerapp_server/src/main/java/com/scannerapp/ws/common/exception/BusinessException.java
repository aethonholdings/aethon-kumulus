package com.scannerapp.ws.common.exception;

import java.io.Serializable;

/**
 * Extension of the GenericException class. The business exceptions represent
 * the business functional errors.
 * 
 * 
 * @author Niraj Salot <niraj.salot@spec-india.com>
 * @since Apr 27, 2011
 */
public class BusinessException extends GenericException implements Serializable {

	public BusinessException() {
	}
	public BusinessException(String errorMessage) {
		super(errorMessage);
	}	
	/**
	 * Constructor for the class BusinessException
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
	public BusinessException(String errorMessageBundle, String errorMessageKey,
			String errorMessage, Throwable originalException) {
		super(errorMessageBundle, errorMessageKey, errorMessage,
				originalException);

	}

	/**
	 * Constructor for the class BusinessException
	 * 
	 * @param errorMessageBundle
	 *            indicates the bundle file where the key is located
	 * @param errorMessageKey
	 *            the key for the error message displayed to the user
	 * @param errorMessage
	 *            exception message (for logging purposes)
	 */
	public BusinessException(String errorMessageBundle, String errorMessageKey,
			String errorMessage) {
		super(errorMessageBundle, errorMessageKey, errorMessage);
	}

}

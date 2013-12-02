package com.scannerapp.ws.common.exception;

import java.io.Serializable;

/**
 * Extension of the RuntimeGenericException class. The dao exceptions represent
 * the error captured in the dao objects. They are tipically database errors.
 * This errors can be considered recoverable or non-recoverable.
 * 
 * @author Niraj Salot <niraj.salot@spec-india.com>
 * @since Apr 27, 2011
 */
public class DaoException extends RuntimeGenericException implements
		Serializable {

	public DaoException() {
	}
	public DaoException(String errorMessage) {
		super(errorMessage);
	}	
	
	/**
	 * Constructor for the class DaoException
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
	public DaoException(String errorMessageBundle, String errorMessageKey,
			String errorMessage, Throwable originalException) {
		super(errorMessageBundle, errorMessageKey, errorMessage,
				originalException);

	}

	/**
	 * Constructor for the class DaoException
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
	public DaoException(String errorMessageBundle, String errorMessageKey,
			String errorMessage) {
		super(errorMessageBundle, errorMessageKey, errorMessage);
	}

}

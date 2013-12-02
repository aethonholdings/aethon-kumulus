package com.scannerapp.ws.common.exception;

import java.io.Serializable;
import java.util.Date;

/**
 * Extension of the RuntimeException class. Provides the automatic generation of
 * a unique identifier and stores the key of the bundle error message which will
 * be displayed to the user.
 * <p>
 * The unique identifier is based on the hashCode of the object class and a
 * timestamp (long).
 * <p>
 * Contains the same logic as the GenericException.
 * 
 * @author Niraj Salot <niraj.salot@spec-india.com>
 * @since Apr 27, 2011
 */
public class RuntimeGenericException extends RuntimeException implements
		Serializable {

	public RuntimeGenericException() {
	}

	private long uniqueErrorCode;
	private String errorMessageKey;
	private String errorMessageBundle;

	public RuntimeGenericException(String errorMessage) {
		super(errorMessage);
	}
	
	/**
	 * Constructor for the class RuntimeGenericException
	 * 
	 * @param errorMessageBundle
	 *            indicates the bundle file where the key is located
	 * @param errorMessageKey
	 *            the key for the error message displayed to the user
	 * @param errorMessage
	 *            exception message (for logging purposes)
	 */
	public RuntimeGenericException(String errorMessageBundle,
			String errorMessageKey, String errorMessage) {
		super(errorMessage);
		this.errorMessageKey = errorMessageKey;
		this.errorMessageBundle = errorMessageBundle;
		this.initUniqueErrorCode();
	}

	/**
	 * Constructor for the class RuntimeGenericException
	 * 
	 * @param errorMessageKey
	 *            the key for the error message displayed to the user
	 * @param errorMessage
	 *            exception message (for logging purposes)
	 */
	public RuntimeGenericException(String errorMessageKey, String errorMessage) {
		super(errorMessage);
		this.errorMessageKey = errorMessageKey;
		this.initUniqueErrorCode();
	}

	/**
	 * Constructor for the class RuntimeGenericException
	 * 
	 * @param errorMessageKey
	 *            the key for the error message displayed to the user
	 * @param errorMessage
	 *            exception message (for logging purposes)
	 * @param originalException
	 *            original exception
	 */
	public RuntimeGenericException(String errorMessageKey, String errorMessage,
			Throwable originalException) {
		super(errorMessage, originalException);
		this.errorMessageKey = errorMessageKey;
		this.initUniqueErrorCode();
	}

	/**
	 * Constructor for the class RuntimeGenericException
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
	public RuntimeGenericException(String errorMessageBundle,
			String errorMessageKey, String errorMessage,
			Throwable originalException) {
		super(errorMessage, originalException);
		this.errorMessageKey = errorMessageKey;
		this.errorMessageBundle = errorMessageBundle;
		this.initUniqueErrorCode();
	}

	/**
	 * Creates an unique identifier which identifies the exception.
	 */
	private void initUniqueErrorCode() {
		long uniqueID = this.hashCode() + new Date().getTime();
		this.uniqueErrorCode = uniqueID;
	}

	/**
	 * Returns the attribute errorMessageKey
	 * 
	 * @return the errorMessageKey
	 */
	public String getErrorMessageKey() {
		return errorMessageKey;
	}

	/**
	 * Returns the attribute uniqueErrorCode
	 * 
	 * @return the uniqueErrorCode
	 */
	public long getUniqueErrorCode() {
		return uniqueErrorCode;
	}

	/**
	 * Returns the attribute errorMessageBundle
	 * 
	 * @return the errorMessageBundle
	 */
	public String getErrorMessageBundle() {
		return errorMessageBundle;
	}

}

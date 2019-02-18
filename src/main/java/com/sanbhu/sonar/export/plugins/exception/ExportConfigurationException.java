package com.sanbhu.sonar.export.plugins.exception;

import com.sanbhu.sonar.export.plugins.constants.ExportErrorCodes;

/**
 * Class used for export configuration exception.
 * 
 * @author Lavanya
 */
public class ExportConfigurationException extends Exception {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** errorCode Integer. */
	private Integer errorCode;
	
	/** errorMessage string */
	private String errorMessage;
	
	/**
	 * Parameterized constructor.
	 * 
	 * @param exportErrorCodes ExportErrorCodes.
	 * @param errorMessage string.
	 */
	public ExportConfigurationException(final ExportErrorCodes exportErrorCodes, final String errorMessage) {
		super(errorMessage);
		this.errorCode = exportErrorCodes.getErrorCode();
		this.errorMessage = errorMessage;
	}
	
	/**
	 * Parameterized constructor.
	 * 
	 * @param exportErrorCodes ExportErrorCodes.
	 * @param exception Exception.
	 */
	public ExportConfigurationException(final ExportErrorCodes exportErrorCodes, final Exception exception) {
		super(exception);
		this.errorCode = exportErrorCodes.getErrorCode();
		this.errorMessage = exception.getLocalizedMessage();
	}

	/**
	 * Getter method for errorCode.
	 * 
	 * @return errorCode Integer.
	 */
	public Integer getErrorCode() {
		return errorCode;
	}

	/**
	 * Setter method for errorCode.
	 * 
	 * @param errorCode Integer.
	 */
	public void setErrorCode(final Integer errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Getter method for errorMessage.
	 * 
	 * @return errorMessage string.
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Setter method for error message.
	 * 
	 * @param errorMessage string.
	 */
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}
}

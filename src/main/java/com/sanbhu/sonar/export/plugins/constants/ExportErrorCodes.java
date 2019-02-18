package com.sanbhu.sonar.export.plugins.constants;

public enum ExportErrorCodes {
	/** SYSTEM_ERROR. */
	SYSTEM_ERROR(100),
	
	/** INTERNAL_SERVER_ERROR. */
	INTERNAL_SERVER_ERROR(101);
	
	/** errorCode */
	private final Integer errorCode;
	
	/**
	 * Parameterized constructor.
	 * 
	 * @param errorCode Integer.
	 */
	private ExportErrorCodes(final Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	/**
	 * Getter method for errorCode.
	 * 
	 * @return errorCode Integer.
	 */
	public Integer getErrorCode() {
		return this.errorCode;
	}
}

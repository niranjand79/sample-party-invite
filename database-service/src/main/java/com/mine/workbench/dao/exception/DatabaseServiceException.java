package com.mine.workbench.dao.exception;

public class DatabaseServiceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Parameterized constructor with error
	 * 
	 * @param throwable
	 *            error object
	 */
	public DatabaseServiceException(final Throwable throwable) {
		super(throwable);
	}

	/**
	 * Parameterized constructor with custom message and error
	 * 
	 * @param message
	 *            custom message
	 * @param throwable
	 *            error object
	 */
	public DatabaseServiceException(final String message, final Throwable throwable) {
		super(message, throwable);
	}

	/**
	 * Parameterized constructor with custom message
	 * 
	 * @param message
	 *            error message
	 */
	public DatabaseServiceException(final String message) {
		super(message);
	}

}

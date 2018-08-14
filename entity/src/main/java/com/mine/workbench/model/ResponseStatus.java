package com.mine.workbench.model;

/**
 * Enumeration for response status
 * @author niranjandeshpande
 *
 */
public class ResponseStatus {

	private String status;
	private String message;

	/**
	 * @return the errorMEssage
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param errorMEssage
	 *            the errorMEssage to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}

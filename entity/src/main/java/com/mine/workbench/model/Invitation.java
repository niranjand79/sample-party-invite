package com.mine.workbench.model;

public class Invitation {

	private Long id;
	private Long eventId;
	private String receipentFirstName;
	private String receipentLastName;
	private String recipientEmail;
	private InvitationStatus response;

	public Invitation() {
		this.response = InvitationStatus.NOT_RESPONDED;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the response
	 */
	public InvitationStatus getResponse() {
		return response;
	}

	/**
	 * @param response
	 *            the response to set
	 */
	public void setResponse(InvitationStatus response) {
		this.response = response;
	}

	/**
	 * @return the receipentFirstName
	 */
	public String getReceipentFirstName() {
		return receipentFirstName;
	}

	/**
	 * @param receipentFirstName
	 *            the receipentFirstName to set
	 */
	public void setReceipentFirstName(String receipentFirstName) {
		this.receipentFirstName = receipentFirstName;
	}

	/**
	 * @return the eventId
	 */
	public Long getEventId() {
		return eventId;
	}

	/**
	 * @param eventId
	 *            the eventId to set
	 */
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the receipentLastName
	 */
	public String getReceipentLastName() {
		return receipentLastName;
	}

	/**
	 * @param receipentLastName
	 *            the receipentLastName to set
	 */
	public void setReceipentLastName(String receipentLastName) {
		this.receipentLastName = receipentLastName;
	}

	/**
	 * @return the recipientEmail
	 */
	public String getRecipientEmail() {
		return recipientEmail;
	}

	/**
	 * @param recipientEmail the recipientEmail to set
	 */
	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

}

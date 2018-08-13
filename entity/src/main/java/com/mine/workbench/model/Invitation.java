package com.mine.workbench.model;

import java.util.Date;

public class Invitation {

	private Long id;
	private Long eventId;
	private String recipientFirstName;
	private String recipientLastName;
	private String recipientEmail;
	private InvitationStatus response;
	private Date responseDate;
	private String responseMessage;

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
	public String getRecipientFirstName() {
		return recipientFirstName;
	}

	/**
	 * @param receipentFirstName
	 *            the receipentFirstName to set
	 */
	public void setRecipientFirstName(String receipentFirstName) {
		this.recipientFirstName = receipentFirstName;
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
	public String getRecipientLastName() {
		return recipientLastName;
	}

	/**
	 * @param receipentLastName
	 *            the receipentLastName to set
	 */
	public void setRecipientLastName(String receipentLastName) {
		this.recipientLastName = receipentLastName;
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

	/**
	 * @return the responseDate
	 */
	public Date getResponseDate() {
		return responseDate;
	}

	/**
	 * @param responseDate the responseDate to set
	 */
	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}

	/**
	 * @return the responseMessage
	 */
	public String getResponseMessage() {
		return responseMessage;
	}

	/**
	 * @param responseMessage the responseMessage to set
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

}

package com.mine.workbench.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class EventResponse {
	private Event event;
	private int totalInvitations;
	private int attending;
	private int notAttending;
	private int tentative;
	private int notResponded;
	private String status;
	private String errorMEssage;

	public int getTotalInvitations() {
		return totalInvitations;
	}

	public void setTotalInvitations(int totalInvitations) {
		this.totalInvitations = totalInvitations;
	}

	public int getAttending() {
		return attending;
	}

	public void setAttending(int attending) {
		this.attending = attending;
	}

	public int getNotAttending() {
		return notAttending;
	}

	public void setNotAttending(int notAttending) {
		this.notAttending = notAttending;
	}

	public int getTentative() {
		return tentative;
	}

	public void setTentative(int tentative) {
		this.tentative = tentative;
	}

	public int getNotResponded() {
		return notResponded;
	}

	public void setNotResponded(int notResponded) {
		this.notResponded = notResponded;
	}

	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the errorMEssage
	 */
	public String getErrorMEssage() {
		return errorMEssage;
	}

	/**
	 * @param errorMEssage the errorMEssage to set
	 */
	public void setErrorMEssage(String errorMEssage) {
		this.errorMEssage = errorMEssage;
	}
}

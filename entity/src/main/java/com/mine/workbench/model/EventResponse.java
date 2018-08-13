package com.mine.workbench.model;

public class EventResponse {
	private Event event;
	private int totalInvitations;
	private int attending;
	private int notAttending;
	private int tentative;
	private int notResponded;

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
}

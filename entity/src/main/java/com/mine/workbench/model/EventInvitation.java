package com.mine.workbench.model;

import java.util.List;

public class EventInvitation {

	private Event event;
	private List<Invitation> invitations;

	public EventInvitation() {

	}

	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * @param event
	 *            the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	/**
	 * @return the invitations
	 */
	public List<Invitation> getInvitations() {
		return invitations;
	}

	/**
	 * @param invitations
	 *            the invitations to set
	 */
	public void setInvitations(List<Invitation> invitations) {
		this.invitations = invitations;
	}

}

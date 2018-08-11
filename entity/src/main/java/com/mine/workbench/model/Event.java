package com.mine.workbench.model;

import java.util.Date;

public class Event {
	private Long id;
	private String name;
	private String hostFirstName;
	private String hostLastName;
	private String location;
	private Date eventDate;
	private String eventMessage;
	private EventStatus status;
	
	public Event(){}

	/**
	 * Get the value of location
	 *
	 * @return the value of location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Set the value of location
	 *
	 * @param location
	 *            new value of location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get the value of name
	 *
	 * @return the value of name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the value of name
	 *
	 * @param name
	 *            new value of name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the eventDate
	 */
	public Date getEventDate() {
		return eventDate;
	}

	/**
	 * @param eventDate
	 *            the eventDate to set
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * @return the evenMessage
	 */
	public String getEventMessage() {
		return eventMessage;
	}

	/**
	 * @param evenMessage
	 *            the evenMessage to set
	 */
	public void setEventMessage(String eventMessage) {
		this.eventMessage = eventMessage;
	}

	/**
	 * @return the hostFirstName
	 */
	public String getHostFirstName() {
		return hostFirstName;
	}

	/**
	 * @param hostFirstName the hostFirstName to set
	 */
	public void setHostFirstName(String hostFirstName) {
		this.hostFirstName = hostFirstName;
	}

	/**
	 * @return the hostLastName
	 */
	public String getHostLastName() {
		return hostLastName;
	}

	/**
	 * @param hostLastName the hostLastName to set
	 */
	public void setHostLastName(String hostLastName) {
		this.hostLastName = hostLastName;
	}

	/**
	 * @return the status
	 */
	public EventStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(EventStatus status) {
		this.status = status;
	}

}

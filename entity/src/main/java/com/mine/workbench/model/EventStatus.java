package com.mine.workbench.model;

/**
 * Enumeration for event status
 * @author niranjandeshpande
 *
 */
public enum EventStatus {
	NEW(0, "New"), 
	COMPLETED(1, "Completed"), 
	CANCELLED(2, "Cancelled");

	private final int dbValue;
	private String displayName;

	private EventStatus(int dbValue, String displayName) {
		this.dbValue = dbValue;
		this.displayName = displayName;
	}

	public static EventStatus getEnumValue(int status) {
		return values()[status];
	}

	public int getDbValue() {
		return this.dbValue;
	}

	public String getDisplayName() {
		return this.displayName;
	}
}

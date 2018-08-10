package com.mine.workbench.model;

public enum InvitationStatus {

	ATTENDING(1), NOT_ATTENDING(2), MAYBE_ATTENDING(3), NOT_RESPONDED(0);

	private final int label;

	private InvitationStatus(int label) {
		this.label = label;
	}

	public int getLabel() {
		return this.label;
	}

}

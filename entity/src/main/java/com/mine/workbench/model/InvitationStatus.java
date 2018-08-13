package com.mine.workbench.model;

public enum InvitationStatus {

	NOT_RESPONDED(0,"Not responded"),
	ATTENDING(1,"Attending"), 
	NOT_ATTENDING(2,"Not attending"), 
	MAYBE_ATTENDING(3,"Tentative"); 

	private final int dbValue;
	private String displayName;

	private InvitationStatus(int dbValue, String displayName) {
		this.dbValue = dbValue;
		this.displayName=displayName;
	}
	
	public static InvitationStatus getEnumValue(int status){
		return values()[status];
	}

	public int getDbValue() {
		return this.dbValue;
	}
	
	public String getDisplayName(){
		return this.displayName;
	}

}

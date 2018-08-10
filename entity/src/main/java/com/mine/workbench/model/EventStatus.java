package com.mine.workbench.model;

public enum EventStatus {
	NEW(0),
	COMPLETED(1),
	CANCELLED(-1);
	
	private final int label;
	private EventStatus(int val){
		this.label=val;
	}
	
	public int getLabel(){
		return this.label;
	}
}

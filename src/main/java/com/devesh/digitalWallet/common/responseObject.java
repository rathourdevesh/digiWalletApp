package com.devesh.digitalWallet.common;

public class responseObject {

	private final boolean status; 
	private final String message;

	public responseObject(boolean status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public boolean getStatus() {
		return status;
	}
	public String getResponse() {
		return message;
	}
}
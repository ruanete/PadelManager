package com.ruanete.padelmanager.response;

public class ResponsePadelManager {
	private boolean success;
	private String message;
	
	public ResponsePadelManager(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}

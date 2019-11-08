package com.ruanete.padelmanager.config;

public class ErrorEndPointNotFound {
	private boolean success;
	private String message;
	
	public ErrorEndPointNotFound(boolean success, String message) {
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
	
	@Override
	public String toString() {
		return "Error [success=" + success + ", message=" + message + "]";
	}
}

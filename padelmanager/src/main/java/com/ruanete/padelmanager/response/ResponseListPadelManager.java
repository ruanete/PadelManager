package com.ruanete.padelmanager.response;

import java.util.List;

import com.ruanete.padelmanager.domain.Track;

public class ResponseListPadelManager {
	private boolean success;
	private String message;
	List<?> list;
	
	public ResponseListPadelManager(boolean success, String message, List<?> list) {
		super();
		this.success = success;
		this.message = message;
		this.list = list;
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

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}
}

package com.ruanete.padelmanager.config;

import java.util.Map;

public class Status {
	String status;
	Map<String, Map<String, Object>> endpoints;
	
	public Status(String status, Map<String, Map<String, Object>> endpoints) {
		super();
		this.status = status;
		this.endpoints = endpoints;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Map<String, Map<String, Object>> getEndPoints() {
		return endpoints;
	}
	
	public void setEndPoints(Map<String, Map<String, Object>> endpoints) {
		this.endpoints = endpoints;
	}
}

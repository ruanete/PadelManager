package com.ruanete.padelmanager.config;

import java.util.List;

public class Status {
	String status;
	List<Object> endpoints;
	
	public Status(String status, List<Object> endpoints) {
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
	
	public List<Object> getEndPoints() {
		return endpoints;
	}
	
	public void setEndPoints(List<Object> endpoints) {
		this.endpoints = endpoints;
	}
}

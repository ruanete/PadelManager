package com.ruanete.padelmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TrackErrorException extends RuntimeException {
	public TrackErrorException(String message) {
		super(message);
	}
}

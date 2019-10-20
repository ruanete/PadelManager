package com.ruanete.padelmanager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ruanete.padelmanager.config.Status;

@RestController
public class PadelManagerRestController {
	
	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public Status healthMicroservice() {
        return new Status("OK");
	}
}

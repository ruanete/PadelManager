package com.ruanete.padelmanager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ruanete.padelmanager.config.ErrorEndPointNotFound;
import com.ruanete.padelmanager.config.Status;

@RestController
public class PadelManagerRestController implements ErrorController {
	
	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public Status healthMicroservice() {
        return new Status("OK");
	}
	
	@RequestMapping("/error")
	@ResponseBody
	public ErrorEndPointNotFound handleError(HttpServletRequest request) {
		return new ErrorEndPointNotFound(false, "End point not found. Use end point /swagger-ui.html to see API REST Documentation.");
	}
	
	@Override
	public String getErrorPath() {
		return "/error";
	}
}

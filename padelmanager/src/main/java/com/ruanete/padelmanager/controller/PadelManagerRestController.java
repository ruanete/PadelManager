package com.ruanete.padelmanager.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ruanete.padelmanager.config.Status;
import com.ruanete.padelmanager.response.ResponseListPadelManager;

@RestController
public class PadelManagerRestController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Status healthMicroservice() {
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
		Map <String, Object> valueTrack = new HashMap<String, Object>();
        Map <String, Object> valuePlayer = new HashMap<String, Object>();
        Map <String, Object> valueMatch = new HashMap<String, Object>();
        Map <String, Object> valueReservation = new HashMap<String, Object>();
        
		valueTrack.put("valor",requestGet("track"));
        valuePlayer.put("valor", requestGet("player"));
        valueMatch.put("valor", requestGet("match"));
        valueReservation.put("valor", requestGet("reservation"));
        
		map.put("GET /api/track", valueTrack);
        map.put("GET /api/player", valuePlayer);
        map.put("GET /api/match", valueMatch);
        map.put("GET /api/reservation", valueReservation);
		
        return new Status("OK", map);
        
	}
	
	private ResponseListPadelManager requestGet(String domain) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> request = new HttpEntity<>(null, headers);

        HttpEntity<? extends ResponseListPadelManager> response = restTemplate.exchange("http://localhost:8080" + "/api/" + domain, HttpMethod.GET, request, ResponseListPadelManager.class);
        
        return response.getBody();
	}
}

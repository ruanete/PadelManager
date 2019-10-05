package com.ruanete.padelmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ruanete.padelmanager.domain.Match;
import com.ruanete.padelmanager.repository.MatchRepository;

@RestController
@RequestMapping("/api")
public class MatchRestController {

	@Autowired
	MatchRepository matchRepository;
	
	@RequestMapping(value = "/match", method = RequestMethod.GET)
	public List<Match> allMatches(){
		return matchRepository.findAll();
	}
	
	@RequestMapping(value = "/match", method = RequestMethod.POST)
	public void newMatch(@RequestBody Match newMatch){
		matchRepository.save(newMatch);
	}
}

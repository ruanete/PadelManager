package com.ruanete.padelmanager.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ruanete.padelmanager.domain.Match;
import com.ruanete.padelmanager.exception.TrackErrorException;
import com.ruanete.padelmanager.exception.TrackNotFoundException;
import com.ruanete.padelmanager.repository.MatchRepository;
import com.ruanete.padelmanager.response.ResponseListPadelManager;
import com.ruanete.padelmanager.response.ResponsePadelManager;

@RestController
@RequestMapping("/api")
public class MatchRestController {

	@Autowired
	MatchRepository matchRepository;
	
	@RequestMapping(value = "/match", method = RequestMethod.GET)
	public ResponseListPadelManager allMatches(){
		ResponseListPadelManager responseListPadelManager = null;
		List<Match> resultsMatchs = new ArrayList<Match>();
		resultsMatchs = matchRepository.findAll();
		
		if(resultsMatchs.isEmpty()) {
			responseListPadelManager = new ResponseListPadelManager(true, "Not exits matchs in database, you can test add one match.", resultsMatchs);
		}else {
			responseListPadelManager = new ResponseListPadelManager(true, "List of matchs got.", resultsMatchs);
		}
		
		return responseListPadelManager;
	}
	
	@RequestMapping(value = "/match", method = RequestMethod.POST)
	public ResponseListPadelManager newMatch(@RequestBody Match newMatch){
		ResponseListPadelManager responseListPadelManager = null;
		List<Match> resultsMatchs = new ArrayList<Match>();
		Match matchSaved = null;

		try {
			//En el futuro se comprobará que los jugadores existen previamente en la base de datos (solo con el ID) y luego se creara el partido
			matchSaved = matchRepository.save(newMatch);
			resultsMatchs.add(matchSaved);
			responseListPadelManager = new ResponseListPadelManager(true, "New match added.", resultsMatchs);
		}catch(DataIntegrityViolationException e) {
			throw new TrackErrorException("Server error, new values are not correct or missing values.");
		}
		
		return responseListPadelManager;
	}
	
	@RequestMapping(value = "/match/{id}", method = RequestMethod.PUT)
	public ResponseListPadelManager updateMatch(@RequestBody Match newMatch, @PathVariable int id){		
		Match searchMatch = null;
		ResponseListPadelManager responseListPadelManager = null;
		List<Match> resultsMatchs = new ArrayList<Match>();
		
		try {
			searchMatch = matchRepository.findById(id).get();
			
			if(newMatch.getSetsP12() >= 0 && newMatch.getSetsP12() <= 3) {
				searchMatch.setSetsP12(newMatch.getSetsP12());
			}
			
			if(newMatch.getSetsP34() >= 0 && newMatch.getSetsP34() <= 3) {
				searchMatch.setSetsP34(newMatch.getSetsP34());
			}
			
			//En el futuro se comprobará que los jugadores existen previamente en la base de datos y solo con el ID
			if(newMatch.getPlayers().size() == 4) {
				searchMatch.setPlayers(newMatch.getPlayers());
			}
			
			newMatch = searchMatch;
			
			matchRepository.save(newMatch);
			resultsMatchs.add(newMatch);
			responseListPadelManager = new ResponseListPadelManager(true, "Match updated correctly.", resultsMatchs);
		}catch(NoSuchElementException k) {
			throw new TrackNotFoundException("Not found Match with id " + id + " to update.");
		}catch(DataIntegrityViolationException e) {
			throw new TrackErrorException("Server error, probably match with id " + id + " not exists or new values are not correct.");
		}
		
		return responseListPadelManager;
	}
	
	@RequestMapping(value = "/match/{id}", method = RequestMethod.DELETE)
	public ResponsePadelManager deleteMatch(@PathVariable int id){
		ResponsePadelManager responsePadelManager = null;

		try {
			matchRepository.deleteById(id);
			responsePadelManager = new ResponsePadelManager(true, "Match deleted correctly.");
		}catch(NoSuchElementException k) {
			throw new TrackNotFoundException("Not found Match with id " + id + ".");
		}catch(EmptyResultDataAccessException e) {
			throw new TrackErrorException("Server error, probably match with id " + id + " not exists.");
		}
		
		return responsePadelManager;
	}
}

package com.ruanete.padelmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value = "/match/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Match> updateMatch(@RequestBody Match newMatch, @PathVariable int id){
		Optional<Match> searchMatch = matchRepository.findById(id);
		
		if(searchMatch.isPresent()) {
			newMatch.setId(id);
			matchRepository.save(newMatch);
			return new ResponseEntity<Match>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Match>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/match/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteMatch(@PathVariable int id){
		Optional<Match> searchMatch = matchRepository.findById(id);
		
		if(searchMatch.isPresent()) {
			matchRepository.delete(searchMatch.get());
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}

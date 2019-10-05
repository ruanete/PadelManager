package com.ruanete.padelmanager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ruanete.padelmanager.domain.Match;
import com.ruanete.padelmanager.domain.Player;
import com.ruanete.padelmanager.repository.MatchRepository;

@RestController
@RequestMapping("/api")
public class MatchRestController {

	@Autowired
	MatchRepository matchRepository;
	
	@RequestMapping(value = "/match", method = RequestMethod.GET)
	public List<Match> devuelveMatch(){
		return matchRepository.findAll();
	}
	
	@RequestMapping(value = "/newMatch", method = RequestMethod.GET)
	public void crearMatch(){
		List<Player> players = new ArrayList<>();
		players.add(new Player(1,"Raúl Ruano Narváez", "raulruanonarvaez@gmail.com"));
		matchRepository.save(new Match(1,0,0,players));
	}
}

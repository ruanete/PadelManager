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

import com.ruanete.padelmanager.domain.Player;
import com.ruanete.padelmanager.exception.TrackErrorException;
import com.ruanete.padelmanager.exception.TrackNotFoundException;
import com.ruanete.padelmanager.repository.PlayerRepository;
import com.ruanete.padelmanager.response.ResponseListPadelManager;
import com.ruanete.padelmanager.response.ResponsePadelManager;

@RestController
@RequestMapping("/api")
public class PlayerRestController {
	@Autowired
	PlayerRepository playerRepository;
	
	@RequestMapping(value = "/player", method = RequestMethod.GET)
	public ResponseListPadelManager allPlayers(){
		ResponseListPadelManager responseListPadelManager = null;
		List<Player> resultsPlayers = new ArrayList<Player>();
		
		resultsPlayers = playerRepository.findAll();
		
		if(resultsPlayers.isEmpty()) {
			responseListPadelManager = new ResponseListPadelManager(false, "Not exits players in database, you can test add one player.", resultsPlayers);
		}else {
			responseListPadelManager = new ResponseListPadelManager(true, "List of players got.", resultsPlayers);
		}
		
		return responseListPadelManager;
	}
	
	@RequestMapping(value = "/player", method = RequestMethod.POST)
	public ResponseListPadelManager newPlayer(@RequestBody Player newPlayer){
		ResponseListPadelManager responseListPadelManager = null;
		List<Player> resultsPlayers = new ArrayList<Player>();
		Player playerSaved = null;
		
		try {
			playerSaved = playerRepository.save(newPlayer);
			resultsPlayers.add(playerSaved);
			responseListPadelManager = new ResponseListPadelManager(true, "New player added.", resultsPlayers); 
		}catch(DataIntegrityViolationException e) {
			throw new TrackErrorException("Server error, probably player email used for other player, new values are not correct or missing values.");
		}
		
		return responseListPadelManager;
	}
	
	@RequestMapping(value = "/player/{id}", method = RequestMethod.PUT)
	public ResponseListPadelManager updatePlayer(@RequestBody Player newPlayer, @PathVariable int id){		
		Player searchPlayer = null;
		ResponseListPadelManager responseListPadelManager = null;
		List<Player> resultsPlayers = new ArrayList<Player>();
		
		try {
			searchPlayer = playerRepository.findById(id).get();
			
			if(newPlayer.getName()!=null && newPlayer.getEmail()!=null) {
				searchPlayer.setName(newPlayer.getName());
				searchPlayer.setEmail(newPlayer.getEmail());
				newPlayer = searchPlayer;
			}else if(newPlayer.getName()==null && newPlayer.getEmail()!=null) {
				searchPlayer.setEmail(newPlayer.getEmail());
				newPlayer = searchPlayer;
			}else if(newPlayer.getName()!=null && newPlayer.getEmail()==null) {
				searchPlayer.setName(newPlayer.getName());
				newPlayer = searchPlayer;
			}
			
			playerRepository.save(newPlayer);
			resultsPlayers.add(newPlayer);
			responseListPadelManager = new ResponseListPadelManager(true, "Player updated correctly.", resultsPlayers);
		}catch(NoSuchElementException k) {
			throw new TrackNotFoundException("Not found Player with id " + id + " to update.");
		}catch(DataIntegrityViolationException e) {
			throw new TrackErrorException("Server error, probably player with id " + id + " not exists, player email used for other player or new values are not correct.");
		}
		
		return responseListPadelManager;
	}
	
	@RequestMapping(value = "/player/{id}", method = RequestMethod.DELETE)
	public ResponsePadelManager deletePlayer(@PathVariable int id){
		ResponsePadelManager responsePadelManager = null;
		
		try {
			playerRepository.deleteById(id);
			responsePadelManager = new ResponsePadelManager(true, "Player deleted correctly.");
		}catch(NoSuchElementException k) {
			throw new TrackNotFoundException("Not found Player with id " + id + ".");
		}catch(EmptyResultDataAccessException e) {
			throw new TrackErrorException("Server error, probably player with id " + id + " not exists.");
		}
		
		return responsePadelManager;
	}
}

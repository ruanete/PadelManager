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

import com.ruanete.padelmanager.domain.Player;
import com.ruanete.padelmanager.repository.PlayerRepository;

@RestController
@RequestMapping("/api")
public class PlayerRestController {
	@Autowired
	PlayerRepository playerRepository;
	
	@RequestMapping(value = "/player", method = RequestMethod.GET)
	public List<Player> allPlayers(){
		return playerRepository.findAll();
	}
	
	@RequestMapping(value = "/player", method = RequestMethod.POST)
	public void newPlayer(@RequestBody Player newPlayer){
		playerRepository.save(newPlayer);
	}
	
	@RequestMapping(value = "/player/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Player> updatePlayer(@RequestBody Player newPlayer, @PathVariable int id){
		Optional<Player> searchPlayer = playerRepository.findById(id);
		
		if(searchPlayer.isPresent()) {
			newPlayer.setId(id);
			playerRepository.save(newPlayer);
			return new ResponseEntity<Player>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Player>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/player/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePlayer(@PathVariable int id){
		Optional<Player> searchPlayer = playerRepository.findById(id);
		
		if(searchPlayer.isPresent()) {
			playerRepository.delete(searchPlayer.get());
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}

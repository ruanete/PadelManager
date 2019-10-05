package com.ruanete.padelmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}

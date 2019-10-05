package com.ruanete.padelmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Player> devuelvePlayers(){
		return playerRepository.findAll();
	}
	
	@RequestMapping(value = "/newPlayer", method = RequestMethod.GET)
	public void crearPlayer(){
		playerRepository.save(new Player(1,"Raúl Ruano Narváez", "raulruanonarvaez@gmail.com"));
	}
}

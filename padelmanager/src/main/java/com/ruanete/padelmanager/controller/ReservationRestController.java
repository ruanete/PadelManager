package com.ruanete.padelmanager.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ruanete.padelmanager.domain.Match;
import com.ruanete.padelmanager.domain.Player;
import com.ruanete.padelmanager.domain.Reservation;
import com.ruanete.padelmanager.domain.Track;
import com.ruanete.padelmanager.repository.ReservationRepository;

@RestController
@RequestMapping("/api")
public class ReservationRestController {
	@Autowired
	ReservationRepository reservationRepository;
	
	@RequestMapping(value = "/reservation", method = RequestMethod.GET)
	public List<Reservation> devuelveReservation(){
		return reservationRepository.findAll();
	}
	
	@RequestMapping(value = "/newReservation", method = RequestMethod.GET)
	public void crearReservation(){
		List<Player> players = new ArrayList<>();
		players.add(new Player(1,"Raúl Ruano Narváez", "raulruanonarvaez@gmail.com"));
		reservationRepository.save(new Reservation(1, new Date(), new Date(), 20.00,new Match(1,0,0,players), new Track(1,1,false)));
	}
}

package com.ruanete.padelmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ruanete.padelmanager.domain.Reservation;
import com.ruanete.padelmanager.repository.ReservationRepository;

@RestController
@RequestMapping("/api")
public class ReservationRestController {
	@Autowired
	ReservationRepository reservationRepository;
	
	@RequestMapping(value = "/reservation", method = RequestMethod.GET)
	public List<Reservation> allReservations(){
		return reservationRepository.findAll();
	}
	
	@RequestMapping(value = "/reservation", method = RequestMethod.POST)
	public void newReservation(@RequestBody Reservation newReservation){
		reservationRepository.save(newReservation);
	}
}

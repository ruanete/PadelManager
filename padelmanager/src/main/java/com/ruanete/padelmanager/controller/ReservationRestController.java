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
	
	@RequestMapping(value = "/reservation/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation newReservation, @PathVariable int id){
		Optional<Reservation> searchReservation = reservationRepository.findById(id);
		
		if(searchReservation.isPresent()) {
			newReservation.setId(id);
			reservationRepository.save(newReservation);
			return new ResponseEntity<Reservation>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/reservation/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteReservation(@PathVariable int id){
		Optional<Reservation> searchReservation = reservationRepository.findById(id);
		
		if(searchReservation.isPresent()) {
			reservationRepository.delete(searchReservation.get());
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}

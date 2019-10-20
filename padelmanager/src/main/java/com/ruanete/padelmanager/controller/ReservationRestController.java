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

import com.ruanete.padelmanager.domain.Reservation;
import com.ruanete.padelmanager.exception.TrackErrorException;
import com.ruanete.padelmanager.exception.TrackNotFoundException;
import com.ruanete.padelmanager.repository.ReservationRepository;
import com.ruanete.padelmanager.response.ResponseListPadelManager;
import com.ruanete.padelmanager.response.ResponsePadelManager;

@RestController
@RequestMapping("/api")
public class ReservationRestController {
	@Autowired
	ReservationRepository reservationRepository;
	
	@RequestMapping(value = "/reservation", method = RequestMethod.GET)
	public ResponseListPadelManager allReservations(){
		ResponseListPadelManager responseListPadelManager = null;
		List<Reservation> resultsReservations = new ArrayList<Reservation>();
		resultsReservations = reservationRepository.findAll();
		
		if(resultsReservations.isEmpty()) {
			responseListPadelManager = new ResponseListPadelManager(false, "Not exits reservations in database, you can test add one reservation.", resultsReservations);
		}else {
			responseListPadelManager = new ResponseListPadelManager(true, "List of reservations got.", resultsReservations);
		}
		
		return responseListPadelManager;
	}
	
	@RequestMapping(value = "/reservation", method = RequestMethod.POST)
	public ResponseListPadelManager newReservation(@RequestBody Reservation newReservation){
		ResponseListPadelManager responseListPadelManager = null;
		List<Reservation> resultsReservations = new ArrayList<Reservation>();
		Reservation reservationSaved = null;

		try {
			reservationSaved = reservationRepository.save(newReservation);
			resultsReservations.add(reservationSaved);
			responseListPadelManager = new ResponseListPadelManager(true, "New reservation added.", resultsReservations);
		}catch(DataIntegrityViolationException e) {
			System.out.println("DEEEEEEEEEEEEEEEEEEP");
			throw new TrackErrorException("Server error probably new values are not correct or missing values.");
		}
		
		return responseListPadelManager;
	}
	
	@RequestMapping(value = "/reservation/{id}", method = RequestMethod.PUT)
	public ResponseListPadelManager updateReservation(@RequestBody Reservation newReservation, @PathVariable int id){
		Reservation searchReservation = null;
		ResponseListPadelManager responseListPadelManager = null;
		List<Reservation> resultsReservations = new ArrayList<Reservation>();
		
		//En el futuro se comprobaran todos los datos para que pistas, jugadores y partidos existan en la base de datos y solo usando sus respectivos ID
		try {
			searchReservation = reservationRepository.findById(id).get();
			
			if(newReservation.getCheckInDate()!=null) {
				searchReservation.setCheckInDate(newReservation.getCheckInDate());
			}
			
			if(newReservation.getCheckOutDate()!=null) {
				searchReservation.setCheckOutDate(newReservation.getCheckOutDate());
			}
			
			if(newReservation.getPrice()>0) {
				searchReservation.setPrice(newReservation.getPrice());
			}
			
			if(newReservation.getMatch()!=null) {
				searchReservation.setMatch(newReservation.getMatch());
			}
			
			if(newReservation.getTrack()!=null) {
				searchReservation.setTrack(newReservation.getTrack());
			}
			
			newReservation = searchReservation;
			
			reservationRepository.save(newReservation);
			resultsReservations.add(newReservation);
			responseListPadelManager = new ResponseListPadelManager(true, "Reservation updated correctly.", resultsReservations);
		}catch(NoSuchElementException k) {
			throw new TrackNotFoundException("Not found Reservation with id " + id + " to update.");
		}catch(DataIntegrityViolationException e) {
			throw new TrackErrorException("Server error, probably reservation with id " + id + " not exists or new values are not correct.");
		}
		
		return responseListPadelManager;
	}
	
	@RequestMapping(value = "/reservation/{id}", method = RequestMethod.DELETE)
	public ResponsePadelManager deleteReservation(@PathVariable int id){
		ResponsePadelManager responsePadelManager = null;

		try {
			reservationRepository.deleteById(id);
			responsePadelManager = new ResponsePadelManager(true, "Reservation deleted correctly.");
		}catch(NoSuchElementException k) {
			throw new TrackNotFoundException("Not found Reservation with id " + id + ".");
		}catch(EmptyResultDataAccessException e) {
			throw new TrackErrorException("Server error, probably reservation with id " + id + " not exists.");
		}
		
		return responsePadelManager;
	}
}

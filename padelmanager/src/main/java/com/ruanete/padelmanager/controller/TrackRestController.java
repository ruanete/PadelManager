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

import com.ruanete.padelmanager.domain.Track;
import com.ruanete.padelmanager.exception.TrackErrorException;
import com.ruanete.padelmanager.exception.TrackNotFoundException;
import com.ruanete.padelmanager.repository.TrackRepository;
import com.ruanete.padelmanager.response.ResponseListPadelManager;
import com.ruanete.padelmanager.response.ResponsePadelManager;


@RestController
@RequestMapping("/api")
public class TrackRestController {
	@Autowired
	TrackRepository trackRepository;
	
	@RequestMapping(value = "/track", method = RequestMethod.GET)
	public ResponseListPadelManager allTracks(){
		ResponseListPadelManager responseListPadelManager = null;
		List<Track> resultsTracks = new ArrayList<Track>();
		resultsTracks = trackRepository.findAll();
		
		if(resultsTracks.isEmpty()) {
			responseListPadelManager = new ResponseListPadelManager(true, "Not exits tracks in database, you can test add one track.", resultsTracks);
		}else {
			responseListPadelManager = new ResponseListPadelManager(true, "List of tracks got.", resultsTracks);
		}
		
		return responseListPadelManager;
	}
	
	@RequestMapping(value = "/track", method = RequestMethod.POST)
	public ResponseListPadelManager newTrack(@RequestBody Track newTrack){
		ResponseListPadelManager responseListPadelManager = null;
		List<Track> resultsTracks = new ArrayList<Track>();
		Track trackSaved = null;

		try {
			trackSaved = trackRepository.save(newTrack);
			resultsTracks.add(trackSaved);
			responseListPadelManager = new ResponseListPadelManager(true, "New track added.", resultsTracks);
		}catch(DataIntegrityViolationException e) {
			throw new TrackErrorException("Server error, probably track number used for other track, new values are not correct or missing values.");
		}
		
		return responseListPadelManager;
	}
	
	@RequestMapping(value = "/track/{id}", method = RequestMethod.PUT)
	public ResponseListPadelManager updateTrack(@RequestBody Track newTrack, @PathVariable int id){
		Track searchTrack = null;
		ResponseListPadelManager responseListPadelManager = null;
		List<Track> resultsTracks = new ArrayList<Track>();
				
		try {
			searchTrack = trackRepository.findById(id).get();
			
			if(newTrack.getTrackNumber()!=0 && newTrack.getWorking()!=null) {
				searchTrack.setTrackNumber(newTrack.getTrackNumber());
				searchTrack.setWorking(newTrack.getWorking());
				newTrack = searchTrack;
			}else if(newTrack.getTrackNumber()==0 && newTrack.getWorking()!=null) {
				searchTrack.setWorking(newTrack.getWorking());
				newTrack = searchTrack;
			}else if(newTrack.getTrackNumber()!=0 && newTrack.getWorking()==null) {
				searchTrack.setTrackNumber(newTrack.getTrackNumber());
				newTrack = searchTrack;
			}
			
			trackRepository.save(newTrack);
			resultsTracks.add(newTrack);
			responseListPadelManager = new ResponseListPadelManager(true, "Track updated correctly.", resultsTracks);
		}catch(NoSuchElementException k) {
			throw new TrackNotFoundException("Not found Track with id " + id + " to update.");
		}catch(DataIntegrityViolationException e) {
			throw new TrackErrorException("Server error, probably track with id " + id + " not exists, track number used for other track or new values are not correct.");
		}
		
		return responseListPadelManager;
	}
	
	@RequestMapping(value = "/track/{id}", method = RequestMethod.DELETE)
	public ResponsePadelManager deleteTrack(@PathVariable int id){
		ResponsePadelManager responsePadelManager = null;

		try {
			trackRepository.deleteById(id);
			responsePadelManager = new ResponsePadelManager(true, "Track deleted correctly.");
		}catch(NoSuchElementException k) {
			throw new TrackNotFoundException("Not found Track with id " + id + ".");
		}catch(EmptyResultDataAccessException e) {
			throw new TrackErrorException("Server error, probably track with id " + id + " not exists.");
		}
		
		return responsePadelManager;
	}
}

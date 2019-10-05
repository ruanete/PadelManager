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

import com.ruanete.padelmanager.domain.Track;
import com.ruanete.padelmanager.repository.TrackRepository;


@RestController
@RequestMapping("/api")
public class TrackRestController {
	@Autowired
	TrackRepository trackRepository;
	
	@RequestMapping(value = "/track", method = RequestMethod.GET)
	public List<Track> allTracks(){
		return trackRepository.findAll();
	}
	
	@RequestMapping(value = "/track", method = RequestMethod.POST)
	public void newTrack(@RequestBody Track newTrack){
		trackRepository.save(newTrack);
	}
	
	@RequestMapping(value = "/track/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Track> updateTrack(@RequestBody Track newTrack, @PathVariable int id){
		Optional<Track> searchTrack = trackRepository.findById(id);
		
		if(searchTrack.isPresent()) {
			newTrack.setId(id);
			trackRepository.save(newTrack);
			return new ResponseEntity<Track>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Track>(HttpStatus.NOT_FOUND);
		}
	}
}

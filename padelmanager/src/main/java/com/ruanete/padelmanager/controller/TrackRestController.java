package com.ruanete.padelmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}

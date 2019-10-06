package com.ruanete.padelmanager.controller;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.ruanete.padelmanager.domain.Reservation;
import com.ruanete.padelmanager.domain.Track;
import com.ruanete.padelmanager.repository.TrackRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql("/test-mysql.sql")
public class TrackRestControllerTests {
	
	@Autowired
	TrackRepository trackRepository;
	
	@Test
	public void getAllTracks() {
		assertEquals(3, trackRepository.findAll().size());
	}
	
	@Test
	public void newTrack() {
		Track track = new Track();
		track.setTrackNumber(4);
		track.setWorking(false);
		
		trackRepository.save(track);
		assertEquals(4, trackRepository.findAll().size());
	}
}

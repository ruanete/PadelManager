package com.ruanete.padelmanager.controller;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.ruanete.padelmanager.domain.Track;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql("/test-mysql.sql")
@ActiveProfiles("test")
public class TrackRestControllerTests {
	
	@Autowired
	TrackRestController trackController;
	
	@Test
	public void getAllTracks() {
		assertEquals(3, trackController.allTracks().getList().size());
	}
	
	@Test
	public void newTrack() {
		Track track = new Track();
		track.setTrackNumber(4);
		track.setWorking(false);
		
		trackController.newTrack(track);
		assertEquals(4, trackController.allTracks().getList().size());
		
		List<Track> list = (List<Track>) trackController.allTracks().getList();
		assertEquals(track.getTrackNumber(), list.get(list.size()-1).getTrackNumber());
		assertEquals(track.getWorking(), list.get(list.size()-1).getWorking());
	}
	
	@Test
	public void updateTrack() {
		List<Track> list = (List<Track>) trackController.allTracks().getList();
		Track track = list.get(list.size()-1);
		Track test = new Track();
		
		test.setTrackNumber(10);
		test.setWorking(true);
		
		trackController.updateTrack(test, track.getId());
		
		list = (List<Track>) trackController.allTracks().getList();
		assertEquals(test.getTrackNumber(), list.get(list.size()-1).getTrackNumber());
		assertEquals(test.getWorking(), list.get(list.size()-1).getWorking());
	}
	
	@Test
	public void deleteTrack() {
		List<Track> list = (List<Track>) trackController.allTracks().getList();
		Track track = list.get(list.size()-1);
		int tam = list.size();
		
		trackController.deleteTrack(track.getId());
		
		assertEquals(tam - 1, trackController.allTracks().getList().size());
	}
}

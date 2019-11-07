package com.ruanete.padelmanager.controller;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.ruanete.padelmanager.domain.Match;
import com.ruanete.padelmanager.domain.Player;
import com.ruanete.padelmanager.domain.Reservation;
import com.ruanete.padelmanager.domain.Track;
import com.ruanete.padelmanager.repository.ReservationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql("/test-mysql.sql")
@ActiveProfiles("test")
public class ReservationRestControllerTests {

	@Autowired
	ReservationRestController reservationController;
	
	@Autowired
	PlayerRestController playerController;
	
	@Autowired
	MatchRestController matchController;
	
	@Autowired
	TrackRestController trackController;
	
	@Test
	public void getAllReservations() {
		assertEquals(3, reservationController.allReservations().getList().size());
	}
	
	@Test
	public void newReservation() {
		List<Player> jugadores = (List<Player>) playerController.allPlayers().getList().subList(0, 4);
		assertEquals(3, matchController.allMatches().getList().size());
		
		List<Track> tracks = (List<Track>) trackController.allTracks().getList();
		assertEquals(3, matchController.allMatches().getList().size());
		
		Match match = new Match();
		Reservation reservation = new Reservation();
		
		match.setSetsP12(1);
		match.setSetsP34(3);
		match.setPlayers(jugadores);
		
		matchController.newMatch(match);
		assertEquals(4, matchController.allMatches().getList().size());
				
		reservation.setCheckInDate(new Date());
		reservation.setCheckOutDate(new Date());
		reservation.setPrice(12.0);
		reservation.setTrack(tracks.get(tracks.size()-1));
		
		reservationController.newReservation(reservation);
		assertEquals(4, reservationController.allReservations().getList().size());
	}
	
	@Test
	public void updateReservation() {
		List<Reservation> list = (List<Reservation>) reservationController.allReservations().getList();
		
		List<Track> tracks = (List<Track>) trackController.allTracks().getList();
		assertEquals(3, matchController.allMatches().getList().size());
		
		Reservation reservation = list.get(list.size()-1);
		Reservation test = new Reservation();
		
		test.setTrack(tracks.get(tracks.size()-2));
		
		reservationController.updateReservation(test, reservation.getId());
		list = (List<Reservation>) reservationController.allReservations().getList();
		
		list = (List<Reservation>) reservationController.allReservations().getList();
		assertEquals(test.getTrack().getTrackNumber(), list.get(list.size()-1).getTrack().getTrackNumber());
	}
	
	@Test
	public void deleteReservation() {
		List<Reservation> list = (List<Reservation>) reservationController.allReservations().getList();
		Reservation reservation = list.get(list.size()-1);
		int tam = list.size();
		
		reservationController.deleteReservation(reservation.getId());
		
		assertEquals(tam - 1, reservationController.allReservations().getList().size());
	}
}

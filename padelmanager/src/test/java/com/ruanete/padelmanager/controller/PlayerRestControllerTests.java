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

import com.ruanete.padelmanager.domain.Match;
import com.ruanete.padelmanager.domain.Player;
import com.ruanete.padelmanager.domain.Reservation;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql("/test-mysql.sql")
@ActiveProfiles("test")
public class PlayerRestControllerTests {
	@Autowired
	MatchRestController matchController;
	
	@Autowired
	ReservationRestController reservationController;

	@Autowired
	PlayerRestController playerController;
	
	@Test
	public void getAllPlayers() {
		assertEquals(4, playerController.allPlayers().getList().size());
	}
	
	@Test
	public void newPlayer() {
		Player player = new Player();
		player.setEmail("test@gmail.com");
		player.setName("Nombre Test");
		
		playerController.newPlayer(player);
		assertEquals(5, playerController.allPlayers().getList().size());
		
		List<Player> list = (List<Player>) playerController.allPlayers().getList();
		assertEquals(player.getEmail(), list.get(list.size()-1).getEmail());
		assertEquals(player.getName(), list.get(list.size()-1).getName());
	}
	
	@Test
	public void updatePlayer() {
		List<Player> list = (List<Player>) playerController.allPlayers().getList();
		Player player = list.get(list.size()-1);
		Player test = new Player();
		
		test.setEmail("prueba@correo.com");
		test.setName("Nombre Prueba Test");
		
		playerController.updatePlayer(test, player.getId());
		
		list = (List<Player>) playerController.allPlayers().getList();
		assertEquals(test.getEmail(), list.get(list.size()-1).getEmail());
		assertEquals(test.getName(), list.get(list.size()-1).getName());
	}
	
	@Test
	public void deletePlayer() {
		List<Reservation> listReservation = (List<Reservation>) reservationController.allReservations().getList();
		
		for(Reservation reservation:listReservation)
			reservationController.deleteReservation(reservation.getId());
		
		List<Match> listMatches = (List<Match>) matchController.allMatches().getList();
		
		for(Match match:listMatches)
			matchController.deleteMatch(match.getId());
		
		List<Player> list = (List<Player>) playerController.allPlayers().getList();
		Player player = list.get(list.size()-1);
		int tam = list.size();
		
		playerController.deletePlayer(player.getId());
		
		assertEquals(tam - 1, playerController.allPlayers().getList().size());
	}
}

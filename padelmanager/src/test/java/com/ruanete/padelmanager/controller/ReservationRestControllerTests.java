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

import com.ruanete.padelmanager.domain.Player;
import com.ruanete.padelmanager.domain.Reservation;
import com.ruanete.padelmanager.repository.ReservationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql("/test-mysql.sql")
public class ReservationRestControllerTests {

	@Autowired
	ReservationRepository reservationRepository;
	
	@Test
	public void getAllReservations() {
		assertEquals(3, reservationRepository.findAll().size());
	}
	
	@Test
	public void newReservation() {
		Reservation reservation = new Reservation();
		reservation.setCheckInDate(new Date());
		reservation.setCheckOutDate(new Date());
		reservation.setPrice(12.0);
		
		reservationRepository.save(reservation);
		assertEquals(4, reservationRepository.findAll().size());
	}
}

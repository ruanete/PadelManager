package com.ruanete.padelmanager.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

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
}

package com.ruanete.padelmanager.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.ruanete.padelmanager.repository.PlayerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql("/test-mysql.sql")
public class PlayerRestControllerTests {

	@Autowired
	PlayerRepository playerRepository;
	
	@Test
	public void getAllPlayers() {
		assertEquals(4, playerRepository.findAll().size());
	}
}

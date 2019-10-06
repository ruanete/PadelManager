package com.ruanete.padelmanager.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.ruanete.padelmanager.domain.Match;
import com.ruanete.padelmanager.repository.MatchRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql("/test-mysql.sql")
public class MatchRestControllerTests {

	@Autowired
	MatchRepository matchRepository;
	
	@Test
	public void getAllMatches() {
		assertEquals(3, matchRepository.findAll().size());
	}
	
	@Test
	public void newMatch() {
		Match match = new Match();
		match.setSetsP12(1);
		match.setSetsP34(2);
		
		matchRepository.save(match);
		assertEquals(4, matchRepository.findAll().size());
	}
}

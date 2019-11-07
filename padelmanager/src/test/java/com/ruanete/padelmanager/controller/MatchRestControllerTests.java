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

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql("/test-mysql.sql")
@ActiveProfiles("test")
public class MatchRestControllerTests {

	@Autowired
	MatchRestController matchController;
	
	@Autowired
	PlayerRestController playerController;
	
	@Test
	public void getAllMatches() {
		assertEquals(3, matchController.allMatches().getList().size());
	}
	
	@Test
	public void newMatch() {
		List<Player> list = (List<Player>) playerController.allPlayers().getList().subList(0, 4);
		Match match = new Match();
		match.setSetsP12(1);
		match.setSetsP34(3);
		match.setPlayers(list);
		
		matchController.newMatch(match);
		assertEquals(4, matchController.allMatches().getList().size());
		
		List<Match> listMatches = (List<Match>) matchController.allMatches().getList();
		assertEquals(match.getSetsP12(), listMatches.get(listMatches.size()-1).getSetsP12());
		assertEquals(match.getSetsP34(), listMatches.get(listMatches.size()-1).getSetsP34());
		assertEquals(match.getPlayers().get(0).getEmail(), listMatches.get(listMatches.size()-1).getPlayers().get(0).getEmail());
		assertEquals(match.getPlayers().get(1).getEmail(), listMatches.get(listMatches.size()-1).getPlayers().get(1).getEmail());
		assertEquals(match.getPlayers().get(2).getEmail(), listMatches.get(listMatches.size()-1).getPlayers().get(2).getEmail());
		assertEquals(match.getPlayers().get(3).getEmail(), listMatches.get(listMatches.size()-1).getPlayers().get(3).getEmail());
		assertEquals(match.getPlayers().get(0).getName(), listMatches.get(listMatches.size()-1).getPlayers().get(0).getName());
		assertEquals(match.getPlayers().get(1).getName(), listMatches.get(listMatches.size()-1).getPlayers().get(1).getName());
		assertEquals(match.getPlayers().get(2).getName(), listMatches.get(listMatches.size()-1).getPlayers().get(2).getName());
		assertEquals(match.getPlayers().get(3).getName(), listMatches.get(listMatches.size()-1).getPlayers().get(3).getName());
	}
	
	@Test
	public void updateMatch() {
		List<Match> list = (List<Match>) matchController.allMatches().getList();
		Match match = list.get(list.size()-1);
		Match test = new Match();
		
		test.setSetsP12(3);
		test.setSetsP34(2);
		
		matchController.updateMatch(test, match.getId());
		
		list = (List<Match>) matchController.allMatches().getList();
		assertEquals(test.getSetsP12(), list.get(list.size()-1).getSetsP12());
		assertEquals(test.getSetsP34(), list.get(list.size()-1).getSetsP34());
	}
	
	@Test
	public void deleteMatch() {
		List<Match> list = (List<Match>) matchController.allMatches().getList();
		Match match = list.get(list.size()-1);
		int tam = list.size();
		
		matchController.deleteMatch(match.getId());
		
		assertEquals(tam - 1, matchController.allMatches().getList().size());
	}
}

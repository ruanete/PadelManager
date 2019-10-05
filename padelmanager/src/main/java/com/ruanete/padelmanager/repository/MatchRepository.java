package com.ruanete.padelmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ruanete.padelmanager.domain.Match;

public interface MatchRepository extends JpaRepository<Match, Integer>{

}

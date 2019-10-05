package com.ruanete.padelmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ruanete.padelmanager.domain.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer>{

}

package com.ruanete.padelmanager.repository;

import com.ruanete.padelmanager.domain.Track;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends JpaRepository<Track, Integer>{

}

package com.ruanete.padelmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ruanete.padelmanager.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>{

}

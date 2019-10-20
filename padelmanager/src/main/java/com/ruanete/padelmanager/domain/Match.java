package com.ruanete.padelmanager.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;

@Entity
@Table(name="matches")
public class Match {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="setsP12")
	private int setsP12;
	
	@Column(name="setsP34")
	private int setsP34;
	
	@JoinTable(
			name = "rel_match_player",
	        joinColumns = @JoinColumn(name = "match_id", nullable = false),
	        inverseJoinColumns = @JoinColumn(name="player_id", nullable = false))
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Player> players = new ArrayList<>();
	
	@OneToOne(mappedBy = "match", cascade = CascadeType.ALL)
	@JsonIgnore
	private Reservation reservation;

	public Match() {}
	
	public Match(int id, int setsP12, int setsP34, List<Player> players) {
		this.id = id;
		this.setsP12 = setsP12;
		this.setsP34 = setsP34;
		this.players = players;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public int getSetsP12() {
		return setsP12;
	}

	public void setSetsP12(int setsP12) {
		this.setsP12 = setsP12;
	}

	public int getSetsP34() {
		return setsP34;
	}

	public void setSetsP34(int setsP34) {
		this.setsP34 = setsP34;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	@Override
	public String toString() {
		return "Match [id=" + id + ", players=" + players + ", setsP12=" + setsP12 + ", setsP34=" + setsP34 + "]";
	}
}

package com.ruanete.padelmanager.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Track")
public class Track {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="trackNumber")
	private int trackNumber;
	
	@Column(name="working")
	private Boolean working;
	
	public Track() {}

	public Track(int id, int trackNumber, Boolean working) {
		this.id = id;
		this.trackNumber = trackNumber;
		this.working = working;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}

	public Boolean getWorking() {
		return working;
	}

	public void setWorking(Boolean working) {
		this.working = working;
	}

	@Override
	public String toString() {
		return "Track [id=" + id + ", trackNumber=" + trackNumber + ", working=" + working + "]";
	}
}

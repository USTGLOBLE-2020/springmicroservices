package com.model;

import java.util.List;

public class FloorResponse {
	private long floor_id;
	private long floor_number;
	private String floor_name;
	private long seatCapacity;
	private long numOfSeatsOccupied;
	private long numOdSeatsEmpty;
	private List<String> occupiedSeats;
	public long getFloor_id() {
		return floor_id;
	}
	public void setFloor_id(long floor_id) {
		this.floor_id = floor_id;
	}
	public long getFloor_number() {
		return floor_number;
	}
	public void setFloor_number(long floor_number) {
		this.floor_number = floor_number;
	}
	public String getFloor_name() {
		return floor_name;
	}
	public void setFloor_name(String floor_name) {
		this.floor_name = floor_name;
	}
	public long getSeatCapacity() {
		return seatCapacity;
	}
	public void setSeatCapacity(long seatCapacity) {
		this.seatCapacity = seatCapacity;
	}
	public long getNumOfSeatsOccupied() {
		return numOfSeatsOccupied;
	}
	public void setNumOfSeatsOccupied(long numOfSeatsOccupied) {
		this.numOfSeatsOccupied = numOfSeatsOccupied;
	}
	public long getNumOdSeatsEmpty() {
		return numOdSeatsEmpty;
	}
	public void setNumOdSeatsEmpty(long numOdSeatsEmpty) {
		this.numOdSeatsEmpty = numOdSeatsEmpty;
	}
	public List<String> getOccupiedSeats() {
		return occupiedSeats;
	}
	public void setOccupiedSeats(List<String> occupiedSeats) {
		this.occupiedSeats = occupiedSeats;
	}	
}

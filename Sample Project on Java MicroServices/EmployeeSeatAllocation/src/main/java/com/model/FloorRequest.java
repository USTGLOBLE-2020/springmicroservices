package com.model;

public class FloorRequest {
	private long floorID;
	private long floorNumber;
	private String floorName;
	private long numOfSeats;
	public long getFloorID() {
		return floorID;
	}
	public void setFloorID(long floorID) {
		this.floorID = floorID;
	}
	public long getFloorNumber() {
		return floorNumber;
	}
	public void setFloorNumber(long floorNumber) {
		this.floorNumber = floorNumber;
	}
	public String getFloorName() {
		return floorName;
	}
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	public long getNumOfSeats() {
		return numOfSeats;
	}
	public void setNumOfSeats(long numOfSeats) {
		this.numOfSeats = numOfSeats;
	}
}

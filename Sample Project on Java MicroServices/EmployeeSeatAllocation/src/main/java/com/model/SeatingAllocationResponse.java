package com.model;

import java.util.List;

public class SeatingAllocationResponse {
	private List<FloorResponse> floorResponse;
	private long numOfFloorsAvailable;
	public List<FloorResponse> getFloorResponse() {
		return floorResponse;
	}
	public void setFloorResponse(List<FloorResponse> floorResponse) {
		this.floorResponse = floorResponse;
	}
	public long getNumOfFloorsAvailable() {
		return numOfFloorsAvailable;
	}
	public void setNumOfFloorsAvailable(long numOfFloorsAvailable) {
		this.numOfFloorsAvailable = numOfFloorsAvailable;
	}
	
}

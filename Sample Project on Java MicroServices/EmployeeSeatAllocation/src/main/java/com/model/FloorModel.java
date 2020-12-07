package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "floors_data")
public class FloorModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "The auto-generated version of the employee")
	@Column(name = "floor_id")
	private long floorID;

    @ApiModelProperty(notes = "The employee code")
	@Column(name = "floor_number")
	private long floorNumber;
	
    @ApiModelProperty(notes = "The employee name")
	@Column(name = "floor_name")
	private String floorName;
	
    @ApiModelProperty(notes = "The employee designation")
	@Column(name = "num_seat")
	private long numSeat;

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

	public long getNumSeat() {
		return numSeat;
	}

	public void setNumSeat(long numSeat) {
		this.numSeat = numSeat;
	}
   
}

package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

public class SeatingAllocationRequest {

	private long employeeID;

	private long floor_number;
	
	private String num_seat;

	public long getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(long employeeID) {
		this.employeeID = employeeID;
	}

	public long getFloor_number() {
		return floor_number;
	}

	public void setFloor_number(long floor_number) {
		this.floor_number = floor_number;
	}

	public String getNum_seat() {
		return num_seat;
	}

	public void setNum_seat(String num_seat) {
		this.num_seat = num_seat;
	}
}

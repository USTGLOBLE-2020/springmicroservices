package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "employeedata")
public class EmployeeModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "The auto-generated version of the employee")
	@Column(name = "employee_id")
	private long employeeID;

    @ApiModelProperty(notes = "The employee code")
	@Column(name = "employee_code")
	private String employeeCode;
	
    @ApiModelProperty(notes = "The employee name")
	@Column(name = "employee_name")
	private String employeeName;
	
    @ApiModelProperty(notes = "The employee designation")
	@Column(name = "employee_designation")
	private String employeeDesignation;
	
    @ApiModelProperty(notes = "The employee department")
	@Column(name = "employee_department")
	private String employeeDepartment;

    @ApiModelProperty(notes = "The employee address")
	@Column(name = "employee_address")
	private String employeeAddress;
	
    @ApiModelProperty(notes = "The employee seat number")
 	@Column(name = "seat_number")
 	private String seatNumber;
    
    @ApiModelProperty(notes = "The employee floor number")
 	@Column(name = "floor_number")
 	private Long floorNumber;
    
	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Long getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(Long floorNumber) {
		this.floorNumber = floorNumber;
	}

	public long getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(long employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeDesignation() {
		return employeeDesignation;
	}

	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}

	public String getEmployeeDepartment() {
		return employeeDepartment;
	}

	public void setEmployeeDepartment(String employeeDepartment) {
		this.employeeDepartment = employeeDepartment;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}
	
	public EmployeeModel() {
		
	}
	
	public EmployeeModel(String empCode, String empName, String empDes, String empDept, String empAddress) {
		this.employeeCode = empCode;
		this.employeeName = empName;
		this.employeeDesignation = empDes;
		this.employeeDepartment = empDept;
		this.employeeAddress = empAddress;
	}
}

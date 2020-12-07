package com.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.ResourceNotFoundException;
import com.model.EmployeeModel;
import com.repository.EmployeeRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/EmployeeAPI")
@Api(value = "Employee" , description = "Employee Details")
public class EmployeeController {
	private static final Logger LOGGER = LogManager.getLogger(EmployeeController.class);

	@Autowired
	EmployeeRepository employeeRepository;
	
	@ApiOperation(value = "Employee Data is inserted")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully inserted employee data"),
			@ApiResponse(code = 401, message = "You are not authorised to insert employee data"),
			@ApiResponse(code = 403, message = "Accessing the employee data is for away"),
			@ApiResponse(code = 404, message = "The employee data which you are trying to reach is not found")
	})
	@PostMapping("/insertEmployeeData")
	public ResponseEntity<EmployeeModel> createEmployee(@RequestBody EmployeeModel employeeModel) {
		try {
			LOGGER.info("Inserted data into Employee Table");
			EmployeeModel empModel = employeeRepository.save(new EmployeeModel(employeeModel.getEmployeeCode(),
					employeeModel.getEmployeeName(),
					employeeModel.getEmployeeDesignation(),
					employeeModel.getEmployeeDepartment(),
					employeeModel.getEmployeeAddress()));
			return new ResponseEntity<>(empModel, HttpStatus.CREATED);
		} catch(Exception e) {
			LOGGER.error("Exception occurred: "+e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getEmployeeList")
	public ResponseEntity<List<EmployeeModel>> getAllEmployees() {
		List<EmployeeModel> empModelList = employeeRepository.findAll();
		LOGGER.info("Employee details ::"+empModelList);
		return new ResponseEntity<>(empModelList, HttpStatus.OK);
	}
	
	
	@GetMapping("/getEmployeeByID/{id}")
	public ResponseEntity<EmployeeModel> getEmployeeByID(@PathVariable long id) throws Exception {
		EmployeeModel empModel = employeeRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
		return ResponseEntity.ok().body(empModel);
	}
	
	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<EmployeeModel> updateEmployee(@PathVariable long id, @RequestBody EmployeeModel employeeModel) {
		Optional<EmployeeModel> empModel = employeeRepository.findById(id);
		if (empModel.isPresent()) {
			EmployeeModel employee = empModel.get();
			employee.setEmployeeCode(employeeModel.getEmployeeCode());
			employee.setEmployeeName(employeeModel.getEmployeeName());
			employee.setEmployeeDepartment(employeeModel.getEmployeeDepartment());
			employee.setEmployeeDesignation(employeeModel.getEmployeeDesignation());

			LOGGER.info("Updated employee details of id "+id);
			return new ResponseEntity<>(employeeRepository.save(employeeModel), HttpStatus.OK);
		} else {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/deleteEmployeeByID/{id}")
	public ResponseEntity deleteEmployeeByID(@PathVariable long id) {
		Optional<EmployeeModel> employeeID = employeeRepository.findById(id);
		if(employeeID.isPresent()) {
			employeeRepository.deleteById(id);
			LOGGER.info("Employee with id "+id+" has been deleted");
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

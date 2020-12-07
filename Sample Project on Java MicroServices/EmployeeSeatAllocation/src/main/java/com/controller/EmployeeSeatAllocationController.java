package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.QueryParam;

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

import com.model.EmployeeModel;
import com.model.FloorModel;
import com.model.FloorRequest;
import com.model.FloorResponse;
import com.model.SeatingAllocationRequest;
import com.model.SeatingAllocationResponse;
import com.repository.EmployeeRepository;
import com.repository.FloorRepository;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/SeatAllocationAPI")
@Api(value = "Employee" , description = "Employee Seat Allocation Details")
public class EmployeeSeatAllocationController {
	private static final Logger LOGGER = LogManager.getLogger(EmployeeSeatAllocationController.class);

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	FloorRepository floorRepository;

	@PostMapping("/allocateSeatToEmployee")
	public ResponseEntity<String> seatAllocation(
			@RequestBody SeatingAllocationRequest seatAllocationRequest) {
		try {

			//Static validations

			//DB validations
			EmployeeModel empModel = employeeRepository.findEmployee(seatAllocationRequest.getEmployeeID());
			if(empModel == null) {
				//throw employee not found error
				return new ResponseEntity<String>("Employee not found", HttpStatus.NOT_FOUND);

			} else {
				//Floor exists check
				FloorModel floorModel = floorRepository.findFloor(seatAllocationRequest.getFloor_number());

				if(floorModel == null) {
					//throw floors not found error
					return new ResponseEntity<String>("floors not found", HttpStatus.NOT_FOUND);
				} else {
					int noOfSeatsAllocatedAtFloor = employeeRepository.noOfSeatsAllocated(seatAllocationRequest.getFloor_number());
					long maxSeatsAtFloor = floorRepository.maxNoOfSeatsAllocated(floorModel.getFloorNumber());
					//Service validations
					//1. Check whether the seat is allocated to some other employee or not!

					EmployeeModel employeeModel = employeeRepository.findEmployeeBySeatAndFloor(seatAllocationRequest.getFloor_number(), 
							seatAllocationRequest.getNum_seat());
					if(employeeModel != null) {
						return new ResponseEntity<String>("Seat is already allocated to employee",HttpStatus.BAD_REQUEST);
					}

					//2. check num of seats can be allocated against the floor
					if((int)maxSeatsAtFloor > noOfSeatsAllocatedAtFloor) {
						//DB operations
						empModel.setSeatNumber(seatAllocationRequest.getNum_seat());
						empModel.setFloorNumber(seatAllocationRequest.getFloor_number());

						employeeRepository.save(empModel);
						return new ResponseEntity<String>("Seat allocation done", HttpStatus.CREATED);

					}
					else {
						return new ResponseEntity<String>("Seat allocation cannot be done due to it reaches the max number", HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
					}
				}	
			}

		} catch(Exception e) {
			LOGGER.error("Exception occurred: "+e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/editAllocateSeatToEmployee")
	public ResponseEntity<String> editSeatAllocation(
			@RequestBody SeatingAllocationRequest seatAllocationRequest) {
		try {

			//Static validations

			//DB validations
			EmployeeModel empModel = employeeRepository.findEmployee(seatAllocationRequest.getEmployeeID());
			if(empModel == null) {
				//throw employee not found error
				return new ResponseEntity<String>("Employee not found", HttpStatus.NOT_FOUND);

			} else {
				//Floor exists check
				FloorModel floorModel = floorRepository.findFloor(seatAllocationRequest.getFloor_number());

				if(floorModel == null) {
					//throw floors not found error
					return new ResponseEntity<String>("floors not found", HttpStatus.NOT_FOUND);
				} else {
					int noOfSeatsAllocatedAtFloor = employeeRepository.noOfSeatsAllocated(seatAllocationRequest.getFloor_number());
					long maxSeatsAtFloor = floorRepository.maxNoOfSeatsAllocated(floorModel.getFloorNumber());
					//Service validations
					//1. Check whether the seat is allocated to some other employee or not!

					EmployeeModel employeeModel = employeeRepository.findEmployeeBySeatAndFloor(seatAllocationRequest.getFloor_number(), 
							seatAllocationRequest.getNum_seat());
					if(employeeModel != null) {
						return new ResponseEntity<String>("Seat is already allocated to employee",HttpStatus.BAD_REQUEST);
					}

					//2. check num of seats can be allocated against the floor
					if((int)maxSeatsAtFloor > noOfSeatsAllocatedAtFloor) {
						//DB operations
						empModel.setSeatNumber(seatAllocationRequest.getNum_seat());
						empModel.setFloorNumber(seatAllocationRequest.getFloor_number());

						employeeRepository.save(empModel);
						return new ResponseEntity<String>("Updated Seat allocation", HttpStatus.CREATED);

					}
					else {
						return new ResponseEntity<String>("Seat allocation cannot be done due to it reaches the max number", HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
					}
				}	
			}

		} catch(Exception e) {
			LOGGER.error("Exception occurred: "+e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getFloorDetails")
	public ResponseEntity<SeatingAllocationResponse> getFloorDetails(@QueryParam("floorID") Long floorID) {
		try {
			SeatingAllocationResponse seatResponse = new SeatingAllocationResponse();
			List<FloorModel> floorModelList = new ArrayList<>();
			if(floorID != null) {
				floorModelList = floorRepository.findByFloorID(floorID);
			}
			else {
				floorModelList = floorRepository.findAll();
			}
			seatResponse.setNumOfFloorsAvailable(floorModelList.size());
			List<FloorResponse> floorRespList = new ArrayList<FloorResponse>();
			for(FloorModel fm: floorModelList) {
				FloorResponse floorResp = new FloorResponse();
				floorResp.setFloor_id(fm.getFloorID());
				floorResp.setFloor_name(fm.getFloorName());
				floorResp.setFloor_number(fm.getFloorNumber());
				floorResp.setSeatCapacity(fm.getNumSeat());
				//1. Number of seats occupied
				int seatsOccupied = employeeRepository.noOfSeatsAllocated(fm.getFloorNumber());
				floorResp.setNumOfSeatsOccupied(seatsOccupied);
				
				//2. Number of seats empty
				int seatsEmpty = (int) (fm.getNumSeat() - seatsOccupied);
				floorResp.setNumOdSeatsEmpty(seatsEmpty);
				
				//3. Occupied seats
				List<String> occupiedSeatsBasedOnFloor = employeeRepository.FetchAllSeatNumbersBasedOnFloor(fm.getFloorNumber());
				floorResp.setOccupiedSeats(occupiedSeatsBasedOnFloor);
				
				floorRespList.add(floorResp);
			}
			seatResponse.setFloorResponse(floorRespList);
			return new ResponseEntity<SeatingAllocationResponse>(seatResponse, HttpStatus.OK);
		}
		catch(Exception e) {
			LOGGER.error("Exception caused: "+e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		
	@PutMapping("/deallocateSeatToEmployee")
	public ResponseEntity<String> deallocateSeatToEmployee(@RequestBody SeatingAllocationRequest seatAllocationRequest) {
		try {
			EmployeeModel empModel = employeeRepository.findEmployee(seatAllocationRequest.getEmployeeID());
			if(empModel == null) {
				return new ResponseEntity<String>("Employee not found", HttpStatus.NOT_FOUND);
			}else {
				
				empModel.setFloorNumber(null);
				empModel.setSeatNumber(null);
				employeeRepository.save(empModel);
			
				return new ResponseEntity<String>("Deallocated seat to Employee", HttpStatus.OK);
			}
		}
		catch(Exception e) {
			LOGGER.error("Exception :"+e);
			return new ResponseEntity<String>("Exception occurred while deallocating the seat",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	//Floor APIs
	@PostMapping("/AddFloorDetails")
	public ResponseEntity<FloorModel> addFloorDetails(@RequestBody FloorRequest floorRequest){
		try {
			
			FloorModel flrModelToBeSaved = new FloorModel();
			flrModelToBeSaved.setFloorID(floorRequest.getFloorID());
			flrModelToBeSaved.setFloorNumber(floorRequest.getFloorNumber());
			flrModelToBeSaved.setFloorName(floorRequest.getFloorName());
			flrModelToBeSaved.setNumSeat(floorRequest.getNumOfSeats());
			FloorModel floorModelAfterAdding = floorRepository.save(flrModelToBeSaved);
			return new ResponseEntity<FloorModel>(floorModelAfterAdding,HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/updateFloorDetails/{floorID}")
	public ResponseEntity<FloorModel> updateFloorDetails(@PathVariable long floorID,@RequestBody FloorRequest floorRequest){
		try {
			Optional<FloorModel> flrModel = floorRepository.findById(floorID);
			if(flrModel.isPresent()) {
				FloorModel floorMdl = flrModel.get();
				floorMdl.setFloorName(floorRequest.getFloorName());
				floorMdl.setNumSeat(floorRequest.getNumOfSeats());
				return new ResponseEntity<>(floorRepository.save(floorMdl),HttpStatus.OK);

			}
			else {
				return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

			}
		}
		catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping("/deleteFloorByID/{id}")
	public ResponseEntity deleteFloorByID(@PathVariable long floorId) {
		Optional<FloorModel> floorID = floorRepository.findById(floorId);
		if(floorID.isPresent()) {
			floorRepository.deleteById(floorId);
			LOGGER.info("Floor with id "+floorId+" has been deleted");
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}


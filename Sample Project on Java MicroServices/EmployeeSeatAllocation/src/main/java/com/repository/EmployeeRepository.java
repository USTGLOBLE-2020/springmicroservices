package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.model.EmployeeModel;
import com.model.FloorModel;

@EnableJpaRepositories
public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {
	@Query(value = "select * from employeedata b where b.employee_id = ?1", nativeQuery = true)
	EmployeeModel findEmployee(@Param("employeeID") long empID);
 
	@Query(value = "select count(*) from employeedata e where e.floor_number = ?1", nativeQuery = true)
	int noOfSeatsAllocated(@Param("floor_number") long floorNumber);

	@Query(value = "select * from employeedata b where b.floor_number = ?1 and b.seat_number = ?2", nativeQuery = true)
	EmployeeModel findEmployeeBySeatAndFloor(@Param("floor_number") long floorNumber, @Param("seat_number") String seatNumber);

	@Query(value = "select seat_number from employeedata b where b.floor_number = ?1", nativeQuery = true)
	List<String> FetchAllSeatNumbersBasedOnFloor(@Param("floor_number") long floorNumber);
	
	@Modifying
	@Query(value = "update employeedata b set b.floor_number = ?1 and b.seat_number = ?2 where b.employee_id = ?3", nativeQuery = true)
	List<String> deallocateSeatandFloorToEmployee(@Param("floor_number") long floorNumber, @Param("seat_number") String seatNumber,@Param("employeeID") long empID);
}

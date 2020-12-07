package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.model.EmployeeModel;
import com.model.FloorModel;

@EnableJpaRepositories
public interface FloorRepository extends JpaRepository<FloorModel, Long> {
	@Query(value = "select * from floors_data f where f.floor_number = ?1", nativeQuery = true)
	FloorModel findFloor(@Param("floor_number") long floorNumber);
	
	@Query(value = "select num_seat from floors_data f where f.floor_number = ?1", nativeQuery = true)
	Long maxNoOfSeatsAllocated(@Param("floor_number") long floorNumber);

	@Query(value = "select * from floors_data f where f.floor_id = ?1", nativeQuery = true)
	List<FloorModel> findByFloorID(@Param("floor_id") long floorID);
	
	
}

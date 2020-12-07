package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.model.EmployeeModel;

@EnableJpaRepositories
public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {

}

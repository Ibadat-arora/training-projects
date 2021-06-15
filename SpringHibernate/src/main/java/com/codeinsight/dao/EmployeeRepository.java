package com.codeinsight.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.codeinsight.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	Iterable<Employee> findByFirstName(String firstName) ;
	Iterable<Employee> findByLastName(String lastName) ;
}

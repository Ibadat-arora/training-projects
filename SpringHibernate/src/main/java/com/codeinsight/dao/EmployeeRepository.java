package com.codeinsight.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.codeinsight.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	
	@Query("SELECT e FROM Employee e WHERE e.firstName =:userName OR e.lastName =:userName")
	Iterable<Employee> getAllEmployeesByUserName(String userName);

	@Query("SELECT e FROM Employee e WHERE e.address =:address")
	Iterable<Employee> getAllEmployeesByAddress(String address);
	
	@Query("SELECT e FROM Employee e WHERE e.email =:email")
	Iterable<Employee> getAllEmployeesByEmail(String email);
	
	@Query("SELECT e FROM Employee e WHERE e.jobProfile.id =:jobProfileId")
	Iterable<Employee> getAllEmployeesByJobProfileId(Integer jobProfileId);
	
	@Query("SELECT e FROM Employee e WHERE e.dateOfJoining >=:startDate OR e.dateOfJoining <=:endDate")
	Iterable<Employee> getAllEmployeesByDate(Date startDate,Date endDate);
}

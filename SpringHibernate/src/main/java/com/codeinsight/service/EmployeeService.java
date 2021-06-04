package com.codeinsight.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codeinsight.bean.UiEmployee;
import com.codeinsight.dao.EmployeeRepository;
import com.codeinsight.entity.Employee;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	public UiEmployee getEmployeeById(Integer id) {
		Optional<Employee> employeeEntityOptional = employeeRepository.findById(id);

		if (employeeEntityOptional.isPresent()) {
			Employee employeeEntity = employeeEntityOptional.get();
			UiEmployee employeeBean = setUiClassAttributes(employeeEntity);

			return employeeBean;
		} else {
			return null;
		}
	}

	public List<UiEmployee> getAllEmployees() {
		Iterable<Employee> employeeList = employeeRepository.findAll();
		List<UiEmployee> employeeBeanList = new ArrayList<>();

		for (Employee employeeEntity : employeeList) {
			UiEmployee employeeBean = setUiClassAttributes(employeeEntity);
			employeeBeanList.add(employeeBean);
		}

		return employeeBeanList;
	}

	public Boolean updateEmployeeData(UiEmployee employeeBean) {
		Optional<Employee> employeeEntityOptional = employeeRepository.findById(employeeBean.getId()) ;
		Boolean isValueUpdated = false;
		
		if (employeeEntityOptional.isPresent()) {
			Employee employeeEntity = employeeEntityOptional.get();
			employeeEntity = setEntityClassAttributes(employeeBean,employeeEntity);
			employeeRepository.save(employeeEntity) ;

			isValueUpdated = true;
		} 
		return isValueUpdated ;
	}
	
	public Employee setEntityClassAttributes(UiEmployee employeeBean,Employee employeeEntity) {
		employeeEntity.setAddress(employeeBean.getAddress());
		employeeEntity.setEmail(employeeBean.getEmail());
		
		employeeEntity.setJobProfile(employeeBean.getJobProfile());
		
		return employeeEntity;
	}
	
	public UiEmployee setUiClassAttributes(Employee employeeEntity) {
		UiEmployee employeeBean = new UiEmployee();
		
		employeeBean.setId(employeeEntity.getId());
		employeeBean.setFirstName(employeeEntity.getFirstName());
		employeeBean.setLastName(employeeEntity.getLastName());
		employeeBean.setAddress(employeeEntity.getAddress());
		employeeBean.setEmail(employeeEntity.getEmail());
		employeeBean.setDateOfJoining(employeeEntity.getDateOfJoining());
		employeeBean.setJobProfile(employeeEntity.getJobProfile());
		
		return employeeBean;
	}
}
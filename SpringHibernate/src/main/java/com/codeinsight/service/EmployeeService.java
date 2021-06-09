package com.codeinsight.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codeinsight.bean.UiEmployee;
import com.codeinsight.bean.UiUserRole;
import com.codeinsight.dao.EmployeeRepository;
import com.codeinsight.dao.JobProfileRepository;
import com.codeinsight.dao.UserRoleRepository;
import com.codeinsight.entity.Employee;
import com.codeinsight.entity.JobProfile;
import com.codeinsight.entity.UserRole;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private JobProfileRepository jobProfileRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

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
		Optional<Employee> employeeEntityOptional = employeeRepository.findById(employeeBean.getId());
		Boolean isValueUpdated = false;
		Optional<UserRole> userRoleOptional = userRoleRepository.findById(employeeBean.getUserRoleId());
		UserRole userRole = userRoleOptional.get();

		if (employeeEntityOptional.isPresent() && userRole.getUserRole().equals("Admin")) {
			Employee employeeEntity = employeeEntityOptional.get();
			employeeEntity = setEntityClassAttributes(employeeBean, employeeEntity);
			employeeRepository.save(employeeEntity);

			isValueUpdated = true;
		}
		
		return isValueUpdated;
	}

	public Employee setEntityClassAttributes(UiEmployee employeeBean, Employee employeeEntity) {
		employeeEntity.setAddress(employeeBean.getAddress());
		employeeEntity.setEmail(employeeBean.getEmail());

		Optional<JobProfile> jobProfileEntityOptional = jobProfileRepository.findById(employeeBean.getJobProfileId());
		JobProfile jobProfileEntity = jobProfileEntityOptional.get();
		employeeEntity.setJobProfile(jobProfileEntity);

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

		JobProfile jobProfile = employeeEntity.getJobProfile();
		employeeBean.setJobProfileId(jobProfile.getId());

		UserRole userRole = employeeEntity.getUserRole();
		employeeBean.setUserRoleId(userRole.getId());

		return employeeBean;
	}
}

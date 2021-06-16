package com.codeinsight.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.codeinsight.bean.UiEmployee;
import com.codeinsight.entity.JobProfile;
import com.codeinsight.service.EmployeeService;
import org.apache.commons.lang3.StringUtils;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employee/{employeeId}")
	public @ResponseBody UiEmployee getEmployeeById(@PathVariable("employeeId") Integer employeeId) {
		UiEmployee employeebean = employeeService.getEmployeeById(employeeId);

		return employeebean;
	}

	@GetMapping("/employee")
	public @ResponseBody List<UiEmployee> getAllEmployees(@RequestParam(required = false) String userName,
			@RequestParam(required = false) String address,@RequestParam(required = false) String email,
			@RequestParam(required = false) Integer jobProfileId,@RequestParam(required = false) Date startDate,
			@RequestParam(required = false) Date endDate) {
		
		List<UiEmployee> employeeBeanList = new ArrayList<>();
		
		if(!StringUtils.isEmpty(userName)) {
			employeeBeanList = employeeService.getAllEmployeesByUserName(userName);
		}else if(!StringUtils.isEmpty(address)) {
			employeeBeanList = employeeService.getAllEmployeesByAddress(address);
		}else if(!StringUtils.isEmpty(email)) {
			employeeBeanList = employeeService.getAllEmployeesByEmail(email);
		}else if(jobProfileId!=null) {
			employeeBeanList = employeeService.getAllEmployeesByJobProfile(jobProfileId);
		}else if(startDate!=null || endDate!=null) {
			employeeBeanList = employeeService.getAllEmployeesByDate(startDate,endDate);
		}else{
			employeeBeanList = employeeService.getAllEmployees();
		}
		
		return employeeBeanList;
	}
	
	@PreAuthorize("hasAuthority('employee_Update')")
	@PutMapping("/employee/{employeeId}")
	public Boolean updateEmployeeData(@RequestBody UiEmployee employeeBean) {
		 Boolean isValueUpdated = employeeService.updateEmployeeData(employeeBean);

		return isValueUpdated;
	}
}

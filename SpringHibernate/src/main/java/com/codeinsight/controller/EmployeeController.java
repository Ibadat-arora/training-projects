package com.codeinsight.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.codeinsight.bean.UiEmployee;
import com.codeinsight.service.EmployeeService;

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
	public @ResponseBody List<UiEmployee> getAllEmployees() {
		List<UiEmployee> employeeBeanList = employeeService.getAllEmployees();

		return employeeBeanList;
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PutMapping("/employee/{employeeId}")
	public Boolean updateEmployeeData(@RequestBody UiEmployee employeeBean) {
		 Boolean isValueUpdated = employeeService.updateEmployeeData(employeeBean);

		return isValueUpdated;
	}
}

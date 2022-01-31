package com.repo.gbj.service.implementor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repo.gbj.dao.manager.EmployeeManager;
import com.repo.gbj.model.Employee;
import com.repo.gbj.service.EmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{

	private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	EmployeeManager employeeManager;
	
	@Autowired
	public void setEmployeeManager(EmployeeManager employeeManager) {
		this.employeeManager = employeeManager;
	}
	
	public Employee findByPhoneNumber(String phoneNumber) {
		return employeeManager.findByPhoneNumber(phoneNumber);
	}

	public Employee findByUsername(String username) {
		return employeeManager.findByUsername(username);
	}
}

package com.repo.gbj.service.implementor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repo.gbj.dao.manager.EmployeeLoginManager;
import com.repo.gbj.model.EmployeeCredentials;
import com.repo.gbj.service.LoginService;


@Service("loginService")
public class LoginServiceImpl implements LoginService{

	private final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	EmployeeLoginManager employeeLoginManager;
	
	@Autowired
	public void setEmployeeLoginManager(EmployeeLoginManager employeeLoginManager) {
		this.employeeLoginManager = employeeLoginManager;
	}
	
	
	public boolean isAuthenticated(EmployeeCredentials employeecredentials) {
		logger.debug("Employee Credentials |"+employeecredentials.toString());
		return employeeLoginManager.findEmployeeforThisCredential(employeecredentials);
	}

}

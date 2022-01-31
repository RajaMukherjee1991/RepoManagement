package com.repo.gbj.dao.manager;

import com.repo.gbj.model.EmployeeCredentials;

public interface EmployeeLoginManager {

	boolean findEmployeeforThisCredential(EmployeeCredentials employeeCredentials);
	
}

package com.repo.gbj.service;

import com.repo.gbj.model.EmployeeCredentials;

public interface LoginService {

	boolean isAuthenticated(EmployeeCredentials employeecredentials);
}

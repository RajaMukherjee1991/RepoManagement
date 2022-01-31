package com.repo.gbj.service;

import com.repo.gbj.model.Employee;

public interface EmployeeService {

	 Employee findByPhoneNumber(String phoneNumber);
	 Employee findByUsername(String username); 
	
	/* List<Employee> findAll();

	 void saveOrUpdate(Employee user);
	
	 void delete(int id);
	
	 void verify(int id);
*/}

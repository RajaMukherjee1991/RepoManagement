package com.repo.gbj.dao.manager;

import com.repo.gbj.model.Employee;

public interface EmployeeManager {

	Employee findByPhoneNumber(String phoneNumber);
	Employee findByUsername(String userame);

	/*List<Employee> findAll();

	void save(Employee employee);

	void update(Employee employee);

	void delete(Integer employeeid);*/
}

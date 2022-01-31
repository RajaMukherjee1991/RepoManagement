package com.repo.gbj.dao.manager.implementor;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.repo.gbj.dao.Queries;
import com.repo.gbj.dao.manager.EmployeeManager;
import com.repo.gbj.dao.mapper.EmployeeMapper;
import com.repo.gbj.model.Employee;

@Repository
public class EmployeeManagerImpl implements EmployeeManager {

	private final Logger logger = LoggerFactory.getLogger(EmployeeManagerImpl.class);
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	
	public Employee findByPhoneNumber(String phoneNumber) {
		logger.info("findEmployeeforThisCredential() : ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("EMP_PHNE", phoneNumber);
		
		Employee employeeResult = null;
		try{
			employeeResult = namedParameterJdbcTemplate.queryForObject(Queries.FIND_EMPLOYEE_BY_MOBILENUMBER, params, new EmployeeMapper());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return employeeResult;
	}

	@Override
	public Employee findByUsername(String username) {
		logger.info("findEmployeeforThisCredential() : ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("EMP_USERNAME", username);
		
		Employee employeeResult = null;
		try{
			employeeResult = namedParameterJdbcTemplate.queryForObject(Queries.FIND_EMPLOYEE_BY_USERNAME, params, new EmployeeMapper());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return employeeResult;
	}
}

package com.repo.gbj.dao.manager.implementor;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.repo.gbj.dao.Queries;
import com.repo.gbj.dao.manager.EmployeeLoginManager;
import com.repo.gbj.dao.mapper.EmployeeLoginMapper;
import com.repo.gbj.model.EmployeeCredentials;

@Repository
public class EmployeeLoginManagerImpl implements EmployeeLoginManager{

	private final Logger logger = LoggerFactory.getLogger(EmployeeLoginManagerImpl.class);
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	
	public boolean findEmployeeforThisCredential(EmployeeCredentials employeeCredentials) {
		logger.info("findEmployeeforThisCredential() : ");
		boolean isAnEmployee = false;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("EMP_USERNAME", employeeCredentials.getUsername());
		params.put("EMP_PHNE", employeeCredentials.getMobilenumber());
		params.put("EMP_PASS", employeeCredentials.getPassword());
		
		EmployeeCredentials employeeResult = null;
		try{
			employeeResult = namedParameterJdbcTemplate.queryForObject(Queries.FIND_EMPLOYEE_BY_CREDENTIALS, params, new EmployeeLoginMapper());
			if(employeeCredentials.equals(employeeResult)){
				isAnEmployee = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return isAnEmployee;
	}

}

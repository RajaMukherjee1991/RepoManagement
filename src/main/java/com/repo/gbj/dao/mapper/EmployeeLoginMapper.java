package com.repo.gbj.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.repo.gbj.model.EmployeeCredentials;

public class EmployeeLoginMapper implements RowMapper<EmployeeCredentials>  {

	public EmployeeCredentials mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmployeeCredentials employeeCredentials = new EmployeeCredentials();
		employeeCredentials.setMobilenumber(rs.getString("EMP_PHNE"));
		employeeCredentials.setUsername(rs.getString("EMP_USERNAME"));
		employeeCredentials.setPassword(rs.getString("EMP_PASS"));
		return employeeCredentials;
	}
	
}

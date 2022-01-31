package com.repo.gbj.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.repo.gbj.model.Employee;

public class EmployeeMapper implements RowMapper<Employee>{

	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setId(rs.getInt("EMP_ID"));
		employee.setFirstname(rs.getString("EMP_FNAME"));
		employee.setLastname(rs.getString("EMP_LNAME"));
		employee.setAddress(rs.getString("EMP_ADD"));
		employee.setMobilenumber(rs.getString("EMP_PHNE"));
		
		return employee;
	}

}

package com.repo.gbj.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.repo.gbj.model.Customer;

public class CustomerMapper implements RowMapper<Customer>{

	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setCustomerPKID(rs.getInt("CUS_PKID"));
		customer.setFname(rs.getString("CUS_FNAME"));
		customer.setMname(rs.getString("CUS_MNAME"));
		customer.setLname(rs.getString("CUS_LNAME"));
		customer.setAddress1(rs.getString("CUS_ADD_1"));
		customer.setAddress2(rs.getString("CUS_ADD_2"));
		customer.setPrimarymobilenumber(rs.getString("CUS_PHNE_1"));
		customer.setAlternatemobilenumber(rs.getString("CUS_PHNE_2"));
		customer.setCustomerisrelative(rs.getInt("CUS_REL_ID"));
		customer.setIsVerified(rs.getInt("ISVERIFIED"));
		customer.setAadharNo(rs.getString("CUS_AADHAR"));
		customer.setEmail(rs.getString("CUS_EMAIL"));
		customer.setFingerprintISOtemplate(rs.getString("CUS_FINGERPRINT"));
		customer.setPancard(rs.getString("CUS_PANCARD"));
		customer.setPincode(rs.getString("CUS_PINCODE"));
		customer.setLandlord(rs.getString("CUS_LANDLORD"));
		
		return customer;
	}
}
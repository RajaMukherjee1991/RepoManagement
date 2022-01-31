package com.repo.gbj.dao.manager.implementor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.repo.gbj.dao.Queries;
import com.repo.gbj.dao.manager.CustomerManager;
import com.repo.gbj.dao.mapper.CustomerMapper;
import com.repo.gbj.model.Customer;

@Repository
public class CustomerManagerImpl implements CustomerManager{

	private final static Logger logger = LoggerFactory.getLogger(CustomerManagerImpl.class);
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	
	public Customer findCustomerByNumber(String phoneNumber) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("CUS_PHNE_1", phoneNumber);
		
		Customer customerResult = null;
		try{
			customerResult = namedParameterJdbcTemplate.queryForObject(Queries.FIND_CUSTOMER_BY_MOBILENUMBER, params, new CustomerMapper());
		}catch(Exception e){
			e.printStackTrace();
		}
		return customerResult;
	}
	
	public ArrayList<Customer> findAllCustomerByLastName(String lastName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("CUS_LNAME", lastName);
		
		ArrayList<Customer> customerResult = null;
		try{
			customerResult = (ArrayList<Customer>)namedParameterJdbcTemplate.query(Queries.FIND_CUSTOMER_BY_LASTNAME,params, new CustomerMapper());
			logger.info("Customer result size "+customerResult.size());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return customerResult;
	}

	public void saveCustomer(Customer customer) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("CUS_FNAME", customer.getFname());
	    paramMap.put("CUS_MNAME", customer.getMname());
	    paramMap.put("CUS_LNAME", customer.getLname());
	    paramMap.put("CUS_ADD_1", customer.getAddress1());
	    paramMap.put("CUS_ADD_2", customer.getAddress2());
	    paramMap.put("CUS_PHNE_1", customer.getPrimarymobilenumber());
	    paramMap.put("CUS_PHNE_2", customer.getAlternatemobilenumber());
	    paramMap.put("CUS_REL_ID", 0);
	    paramMap.put("ISVERIFIED", 1);
	    paramMap.put("CUS_FINGERPRINT", customer.getFingerprintISOtemplate());
	    paramMap.put("CUS_AADHAR", customer.getAadharNo());
	    paramMap.put("CUS_EMAIL", customer.getEmail());
	    paramMap.put("CUS_PANCARD", customer.getPancard());
	    paramMap.put("CUS_PINCODE", customer.getPincode());
	    paramMap.put("CUS_LANDLORD", customer.getLandlord());
	    
	    int rowCount = namedParameterJdbcTemplate.update(Queries.SAVE_CUSTOMER, paramMap);
	    logger.info("Number of rows "+ rowCount);
	}
	
	public Customer findCustomerByID(int id){
		Map<String, Object> params = new HashMap<String, Object>();
		logger.debug("ID Value "+id);
		params.put("CUS_PKID", id);
		Customer customer = null;
		try{
			customer = namedParameterJdbcTemplate.queryForObject(Queries.FIND_CUSTOMER_BY_ID,params,new CustomerMapper());
		}catch(EmptyResultDataAccessException erae){
			logger.error("No record found in Database for "+id,erae);
		}catch(Exception e){
			logger.error(e.toString());
		}
		return customer;
	}

	public ArrayList<Customer> findAllSalesCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Customer> findAllMortgageCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Customer> findAllCustomer() {
		ArrayList<Customer> customerList = null;
		try{
			customerList = (ArrayList<Customer>) namedParameterJdbcTemplate.query(Queries.ALL_CUSTOMER,new CustomerMapper());
		}catch(Exception e){
			e.printStackTrace();
		}
		return customerList;
	}
	
	public ArrayList<Customer> findAllCustomerByKey(String searchKey) {
		String searchString = "%"+searchKey+"%";
		SqlParameterSource param = new MapSqlParameterSource("key",searchString);
		ArrayList<Customer> customerList = null;
		try{
			customerList = (ArrayList<Customer>) namedParameterJdbcTemplate.query(Queries.ALL_CUSTOMERS_BY_SEARCH_KEY,param,new CustomerMapper());
		}catch(Exception e){
			e.printStackTrace();
		}
		return customerList;
	}

	public void updateCustomer(Customer customer) {
		
		logger.info("Customer to be updated "+customer.toString());
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("CUS_PKID", customer.getCustomerPKID());
	    paramMap.put("CUS_FNAME", customer.getFname());
	    paramMap.put("CUS_MNAME", customer.getMname());
	    paramMap.put("CUS_LNAME", customer.getLname());
	    paramMap.put("CUS_ADD_1", customer.getAddress1());
	    paramMap.put("CUS_ADD_2", customer.getAddress2());
	    paramMap.put("CUS_PHNE_1", customer.getPrimarymobilenumber());
	    paramMap.put("CUS_PHNE_2", customer.getAlternatemobilenumber());
	    paramMap.put("CUS_REL_ID", 0);
	    paramMap.put("ISVERIFIED", 1);
	    paramMap.put("CUS_FINGERPRINT", customer.getFingerprintISOtemplate());
	    paramMap.put("CUS_AADHAR", customer.getAadharNo());
	    paramMap.put("CUS_EMAIL", customer.getEmail());
	    paramMap.put("CUS_PANCARD", customer.getPancard());
	    paramMap.put("CUS_PINCODE", customer.getPincode());
	    paramMap.put("CUS_LANDLORD", customer.getLandlord());
	    namedParameterJdbcTemplate.update(Queries.UPDATE_CUSTOMER, paramMap);
	}
}

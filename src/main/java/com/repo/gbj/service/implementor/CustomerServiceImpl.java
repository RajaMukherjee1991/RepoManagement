package com.repo.gbj.service.implementor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repo.gbj.dao.manager.CustomerManager;
import com.repo.gbj.model.Customer;
import com.repo.gbj.model.datatype.Aadhar;
import com.repo.gbj.service.CustomerService;
import com.repo.gbj.utils.TransactionalWithRollbackAndIsolation;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

	CustomerManager customerManager;
	private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	public Customer findCustomerByNumber(String phoneNumber) {
		return customerManager.findCustomerByNumber(phoneNumber);
	}
	
	public ArrayList<Customer> findAllCustomerByLastName(String lastName) {
		return customerManager.findAllCustomerByLastName(lastName);
	}
	
	@Autowired
	public void setCustomerManager(CustomerManager customerManager) {
		this.customerManager = customerManager;
	}

	@TransactionalWithRollbackAndIsolation
	public void saveCustomer(Customer customer){
			if(customer!=null){
				customerManager.saveCustomer(customer);
			}
	}

	public Customer findCustomerByID(int id){
		Customer customer = customerManager.findCustomerByID(id);
		return customer;
	}

	public List<Customer> findAllCustomer() {
		ArrayList<Customer> customerList = (ArrayList<Customer>)customerManager.findAllCustomer();
		return customerList;
	}

	@TransactionalWithRollbackAndIsolation
	public void updateCustomer(Customer customer) {
		customerManager.updateCustomer(customer);
	}

	public ArrayList<Customer> findAllCustomerByKey(String searchKey) {
		return customerManager.findAllCustomerByKey(searchKey);
	}
}

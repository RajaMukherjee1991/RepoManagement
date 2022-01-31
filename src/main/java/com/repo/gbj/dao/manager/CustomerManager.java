package com.repo.gbj.dao.manager;

import java.util.ArrayList;
import java.util.List;

import com.repo.gbj.model.Customer;

public interface CustomerManager {

	 Customer findCustomerByNumber(String phoneNumber);
	 ArrayList<Customer> findAllCustomerByLastName(String lastName);
	 void saveCustomer(Customer customer);
	 void updateCustomer(Customer customer);
	 Customer findCustomerByID(int id);
	 ArrayList<Customer> findAllSalesCustomer();
	 ArrayList<Customer> findAllMortgageCustomer();
	 List<Customer> findAllCustomer();
	 ArrayList<Customer> findAllCustomerByKey(String searchKey);
	
}

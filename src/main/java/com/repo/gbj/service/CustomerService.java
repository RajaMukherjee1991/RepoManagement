package com.repo.gbj.service;

import java.util.ArrayList;
import java.util.List;

import com.repo.gbj.model.Customer;

public interface CustomerService {

	 Customer findCustomerByNumber(String phoneNumber);
	 ArrayList<Customer> findAllCustomerByLastName(String lastName);
	 void saveCustomer(Customer customer);
	 void updateCustomer(Customer customer);
	 Customer findCustomerByID(int id);
	 List<Customer> findAllCustomer();
	 ArrayList<Customer> findAllCustomerByKey(String searchKey);
}

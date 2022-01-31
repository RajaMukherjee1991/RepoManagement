package com.repo.gbj.model.jasper.report.view;

import com.repo.gbj.model.Customer;
import com.repo.gbj.model.Stock;

public class CustomerMortgageInvoice {
	
	private Customer customer;
	private Stock mortgageStock;
	
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Stock getMortgageStock() {
		return mortgageStock;
	}
	public void setMortgageStock(Stock mortgageStock) {
		this.mortgageStock = mortgageStock;
	}
	
}
	

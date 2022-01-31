package com.repo.gbj.model.jasper.report.view;

import java.util.ArrayList;

import com.repo.gbj.model.Customer;
import com.repo.gbj.model.Stock;

public class CustomerSalesInvoice {

	private Customer customer;
	private ArrayList<Stock> salesStock = new ArrayList<Stock>();

	public ArrayList<Stock> getSalesStock() {
		return salesStock;
	}

	public void setSalesStock(ArrayList<Stock> salesStock) {
		this.salesStock = salesStock;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}

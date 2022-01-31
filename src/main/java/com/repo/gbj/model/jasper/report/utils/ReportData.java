package com.repo.gbj.model.jasper.report.utils;

import com.repo.gbj.model.Customer;
import com.repo.gbj.model.Stock;
import com.repo.gbj.model.jasper.report.view.CustomerData;
import com.repo.gbj.model.jasper.report.view.CustomerMortgageInvoice;
import com.repo.gbj.model.jasper.report.view.MortgageStockData;
import com.repo.gbj.model.jasper.report.view.SalesStockData;

public class ReportData {

	private SalesStockData salesStockList;
	private MortgageStockData mortgageStockList;
	private CustomerMortgageInvoice customerMortgageInvoice;
	private Stock selectedStockData;
	private CustomerData customerList;
	private Customer selectedCustomer;
	
	public SalesStockData getSalesStockList() {
		return salesStockList;
	}
	public void setSalesStockList(SalesStockData salesStockList) {
		this.salesStockList = salesStockList;
	}
	public MortgageStockData getMortgageStockList() {
		return mortgageStockList;
	}
	public void setMortgageStockList(MortgageStockData mortgageStockList) {
		this.mortgageStockList = mortgageStockList;
	}
	public CustomerMortgageInvoice getCustomerMortgageInvoice() {
		return customerMortgageInvoice;
	}
	public void setCustomerMortgageInvoice(CustomerMortgageInvoice customerMortgageInvoice) {
		this.customerMortgageInvoice = customerMortgageInvoice;
	}
	public Stock getSelectedStockData() {
		return selectedStockData;
	}
	public void setSelectedStockData(Stock selectedStockData) {
		this.selectedStockData = selectedStockData;
	}
	public CustomerData getCustomerList() {
		return customerList;
	}
	public void setCustomerList(CustomerData customerList) {
		this.customerList = customerList;
	}
	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}
	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}
	
	
	
}

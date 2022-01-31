package com.repo.gbj.model.viewobject;

import java.util.ArrayList;

import com.repo.gbj.model.Customer;
import com.repo.gbj.model.Employee;
import com.repo.gbj.model.Stock;

public class SalesBillVO {

	private Customer customerDetails;
	private Employee sellingEmployee;
	private String salesBillBarcode;
	private String stockbarcode;
	private ArrayList<Stock> salesStockList ;
	private String salesBillDate;
	
	
	public Employee getSellingEmployee() {
		return sellingEmployee;
	}
	public void setSellingEmployee(Employee sellingEmployee) {
		this.sellingEmployee = sellingEmployee;
	}
	public ArrayList<Stock> getSalesStockList() {
		return salesStockList;
	}
	public void setSalesStockList(ArrayList<Stock> salesStockList) {
		this.salesStockList = salesStockList;
	}
	public String getSalesBillBarcode() {
		return salesBillBarcode;
	}
	public void setSalesBillBarcode(String salesBillBarcode) {
		this.salesBillBarcode = salesBillBarcode;
	}
	public Customer getCustomerDetails() {
		return customerDetails;
	}
	public void setCustomerDetails(Customer customerDetails) {
		this.customerDetails = customerDetails;
	}
	public String getSalesBillDate() {
		return salesBillDate;
	}
	public void setSalesBillDate(String salesBillDate) {
		this.salesBillDate = salesBillDate;
	}
	public String getStockbarcode() {
		return stockbarcode;
	}
	public void setStockbarcode(String stockbarcode) {
		this.stockbarcode = stockbarcode;
	}
	
}

package com.repo.gbj.model.viewobject;

import java.util.ArrayList;

import com.repo.gbj.model.Billing;
import com.repo.gbj.model.Customer;
import com.repo.gbj.model.Stock;

public class BillSummaryVO {

	private Customer customer;
	private ArrayList<Stock> itemList= new ArrayList<Stock>();
	private Billing billDetails;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public ArrayList<Stock> getItemList() {
		return itemList;
	}
	public void setItemList(ArrayList<Stock> itemList) {
		this.itemList = itemList;
	}
	public Billing getBillDetails() {
		return billDetails;
	}
	public void setBillDetails(Billing billDetails) {
		this.billDetails = billDetails;
	}
	
	
}

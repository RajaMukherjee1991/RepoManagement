package com.repo.gbj.model.viewobject;

import com.repo.gbj.model.Billing.BillRangeType;
import com.repo.gbj.model.Customer;
import com.repo.gbj.model.Employee;

public class BasicOverviewVO {

	private String billDate;
	private BillRangeType billRangeType;
	private String billType;
	private String customersPKID;
	private String bill_no;
	private Customer customer;
	private Employee employee;
	
	
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public BillRangeType getBillRangeType() {
		return billRangeType;
	}
	public void setBillRangeType(BillRangeType billRangeType) {
		this.billRangeType = billRangeType;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getCustomersPKID() {
		return customersPKID;
	}
	public void setCustomersPKID(String customersPKID) {
		this.customersPKID = customersPKID;
	}
	public String getBill_no() {
		return bill_no;
	}
	public void setBill_no(String bill_no) {
		this.bill_no = bill_no;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}

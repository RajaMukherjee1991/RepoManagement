package com.repo.gbj.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Billing {

	private BillRangeType billrange_type;
	private Timestamp timestamp;
	private String bill_date;
	private Customer customer;
	private Employee employee;
	private BigDecimal bill_price;
	private String bill_barcode_no;
	private String bill_note;
	private String customerimage;
		
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
	
	public enum BillRangeType{
		GHOSH_BROTHERS_JEWELLERS("GBJ"),
		TIRUPATI_JEWELLERS("TJ"),
		BALAJI_JEWELLERS("BJ"),
		GHOSH_BROTHERS_JEWELLERS_SALES("GBJS");
		
		private String name;
		
		BillRangeType(String name){
			this.name= name;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
	}

	public String getBill_date() {
		return bill_date;
	}
	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}
	public BigDecimal getBill_price() {
		return bill_price;
	}
	public void setBill_price(BigDecimal bill_price) {
		this.bill_price = bill_price;
	}
	public String getBill_barcode_no() {
		return bill_barcode_no;
	}
	public void setBill_barcode_no(String bill_barcode_no) {
		this.bill_barcode_no = bill_barcode_no;
	}
	public String getBill_note() {
		return bill_note;
	}
	public void setBill_note(String bill_note) {
		this.bill_note = bill_note;
	}
	public BillRangeType getBillrange_type() {
		return billrange_type;
	}
	public void setBillrange_type(BillRangeType billrange_type) {
		this.billrange_type = billrange_type;
	}
	@Override
	public String toString() {
		return "Billing [billrange_type=" + billrange_type + ", bill_date=" + bill_date + ", customer=" + customer.getCustomerPKID()
				+ ", employee=" + employee.getId() + ", bill_price=" + bill_price + ", bill_barcode_no=" + bill_barcode_no
				+ ", bill_note=" + bill_note + "]";
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getCustomerimage() {
		return customerimage;
	}
	public void setCustomerimage(String customerimage) {
		this.customerimage = customerimage;
	}
}

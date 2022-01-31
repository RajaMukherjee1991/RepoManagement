package com.repo.gbj.model.viewobject;

import org.springframework.web.multipart.MultipartFile;

import com.repo.gbj.model.Billing.BillRangeType;
import com.repo.gbj.model.Customer;
import com.repo.gbj.model.Employee;
import com.repo.gbj.model.Stock;

public class MortgageBillVO {

	private BillRangeType billRangeType;
	private String mortgageBillBarcode;
	private Customer customerDetails;
	private Employee sellingEmployee;
	private Stock stock;
	private MultipartFile mortgageStockImage;
	
	public Customer getCustomerDetails() {
		return customerDetails;
	}
	public void setCustomerDetails(Customer customerDetails) {
		this.customerDetails = customerDetails;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	public String getMortgageBillBarcode() {
		return mortgageBillBarcode;
	}
	public void setMortgageBillBarcode(String mortgageBillBarcode) {
		this.mortgageBillBarcode = mortgageBillBarcode;
	}
	public MultipartFile getMortgageStockImage() {
		return mortgageStockImage;
	}
	public void setMortgageStockImage(MultipartFile mortgageStockImage) {
		this.mortgageStockImage = mortgageStockImage;
	}
	public BillRangeType getBillRangeType() {
		return billRangeType;
	}
	public void setBillRangeType(BillRangeType billRangeType) {
		this.billRangeType = billRangeType;
	}
	public Employee getSellingEmployee() {
		return sellingEmployee;
	}
	public void setSellingEmployee(Employee sellingEmployee) {
		this.sellingEmployee = sellingEmployee;
	}
}

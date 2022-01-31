package com.repo.gbj.model;

public class Customer {

	private Integer customerPKID;
	private Integer customerID;
	private String fname;
	private String mname;
	private String lname;
	private String fullname;
	private String address1;
	private String address2;
	private String pincode;
	private String landlord;
	private String primarymobilenumber;
	private String alternatemobilenumber;
	private Integer customerisrelative;
	private String email;
	private String pancard;
	private String aadharNo;
	private Integer isVerified;
	private String fingerprintISOtemplate;
	
	public Integer getCustomerPKID() {
		return customerPKID;
	}
	@Override
	public String toString() {
		return "Customer [customerPKID=" + customerPKID + ", customerID=" + customerID + ", fname=" + fname + ", mname="
				+ mname + ", lname=" + lname + ", address1=" + address1 + ", address2=" + address2 + ", pincode="
				+ pincode + ", landlord=" + landlord + ", primarymobilenumber=" + primarymobilenumber
				+ ", alternatemobilenumber=" + alternatemobilenumber + ", customerisrelative=" + customerisrelative
				+ ", email=" + email + ", pancard=" + pancard + ", aadharNo=" + aadharNo + ", isVerified=" + isVerified
				+ ", fingerprintISOtemplate=" + fingerprintISOtemplate + "]";
	}
	public void setCustomerPKID(Integer customerPKID) {
		this.customerPKID = customerPKID;
	}
	public Integer getCustomerID() {
		return customerID;
	}
	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getLandlord() {
		return landlord;
	}
	public void setLandlord(String landlord) {
		this.landlord = landlord;
	}
	public Integer getCustomerisrelative() {
		return customerisrelative;
	}
	public void setCustomerisrelative(Integer customerisrelative) {
		this.customerisrelative = customerisrelative;
	}
	public Integer getIsVerified() {
		return isVerified;
	}
	public void setIsVerified(Integer isVerified) {
		this.isVerified = isVerified;
	}
	public String getFingerprintISOtemplate() {
		return fingerprintISOtemplate;
	}
	public void setFingerprintISOtemplate(String fingerprintISOtemplate) {
		this.fingerprintISOtemplate = fingerprintISOtemplate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPancard() {
		return pancard;
	}
	public void setPancard(String pancard) {
		this.pancard = pancard;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public String getPrimarymobilenumber() {
		return primarymobilenumber;
	}
	public void setPrimarymobilenumber(String primarymobilenumber) {
		this.primarymobilenumber = primarymobilenumber;
	}
	public String getAlternatemobilenumber() {
		return alternatemobilenumber;
	}
	public void setAlternatemobilenumber(String alternatemobilenumber) {
		this.alternatemobilenumber = alternatemobilenumber;
	}
	public String getFullname() {
		return this.fname.concat(" ").concat(this.mname).concat(" ").concat(this.lname);
	}
	
	
}

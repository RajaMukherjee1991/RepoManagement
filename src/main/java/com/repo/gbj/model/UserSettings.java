package com.repo.gbj.model;

import java.math.BigDecimal;

public class UserSettings {

	private int settingID;
	private String shopAddress;
	private String licenseNo;
	private String gstNumber;
	private String shopPhone1;
	private String shopPhone2;
	private String barcodeSeriesNo;
	private BigDecimal goldRate;
	private String termsAndConditions;
	
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getGstNumber() {
		return gstNumber;
	}
	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}
	public String getShopPhone1() {
		return shopPhone1;
	}
	public void setShopPhone1(String shopPhone1) {
		this.shopPhone1 = shopPhone1;
	}
	public String getShopPhone2() {
		return shopPhone2;
	}
	public void setShopPhone2(String shopPhone2) {
		this.shopPhone2 = shopPhone2;
	}
	public int getSettingID() {
		return settingID;
	}
	public void setSettingID(int settingID) {
		this.settingID = settingID;
	}
	public String getBarcodeSeriesNo() {
		return barcodeSeriesNo;
	}
	public void setBarcodeSeriesNo(String barcodeSeriesNo) {
		this.barcodeSeriesNo = barcodeSeriesNo;
	}
	public BigDecimal getGoldRate() {
		return goldRate;
	}
	public void setGoldRate(BigDecimal goldRate) {
		this.goldRate = goldRate;
	}
	public String getTermsAndConditions() {
		return termsAndConditions;
	}
	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}
	
	
}

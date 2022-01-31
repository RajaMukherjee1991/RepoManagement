package com.repo.gbj.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class Payment {

	private String billno;
	private Timestamp timestamp;
	private Date payementdate;
	private BigDecimal interestAmount;
	private BigDecimal capitalAmount;
	private BigDecimal amountLeft;
	
	public Payment(){};
	
	public Payment(String billno, Timestamp timestamp, Date payementdate, BigDecimal interestAmount,
			BigDecimal capitalAmount, BigDecimal amountLeft) {
		super();
		this.billno = billno;
		this.timestamp = timestamp;
		this.payementdate = payementdate;
		this.interestAmount = interestAmount;
		this.capitalAmount = capitalAmount;
		this.amountLeft = amountLeft;
	}
	public String getBillno() {
		return billno;
	}
	public void setBillno(String billno) {
		this.billno = billno;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public Date getPayementdate() {
		return payementdate;
	}
	public void setPayementdate(Date payementdate) {
		this.payementdate = payementdate;
	}
	public BigDecimal getInterestAmount() {
		return interestAmount;
	}
	public void setInterestAmount(BigDecimal interestAmount) {
		this.interestAmount = interestAmount;
	}
	public BigDecimal getCapitalAmount() {
		return capitalAmount;
	}
	public void setCapitalAmount(BigDecimal capitalAmount) {
		this.capitalAmount = capitalAmount;
	}
	public BigDecimal getAmountLeft() {
		return amountLeft;
	}
	public void setAmountLeft(BigDecimal amountLeft) {
		this.amountLeft = amountLeft;
	}
	@Override
	public String toString() {
		return "Payment [billno=" + billno + ", timestamp=" + timestamp + ", payementdate=" + payementdate
				+ ", interestAmount=" + interestAmount + ", capitalAmount=" + capitalAmount + ", amountLeft="
				+ amountLeft + "]";
	}	
}

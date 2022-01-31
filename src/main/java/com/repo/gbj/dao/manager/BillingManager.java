package com.repo.gbj.dao.manager;

import java.util.ArrayList;
import java.util.List;

import com.repo.gbj.model.Billing;
import com.repo.gbj.model.Payment;

public interface BillingManager {

	List<Billing> findAllBills();
	boolean saveBill(Billing bill);
	ArrayList<Billing> findCustomerBill(int cus_pkid);
	Billing findBillByBillNo(String billno);
	boolean saveInterestPayment(Payment payment);
	public ArrayList<Payment> fetchInterestPayments(String billno);
}

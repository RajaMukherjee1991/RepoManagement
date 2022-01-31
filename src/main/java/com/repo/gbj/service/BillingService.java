package com.repo.gbj.service;

import java.util.ArrayList;

import com.repo.gbj.model.Billing;
import com.repo.gbj.model.Payment;

public interface BillingService {

	 ArrayList<Billing> findAllbills();
	 boolean saveBill(Billing billing);
	 ArrayList<Billing> findCustomerBill(int cus_pkid);
	 Billing findBillByBillNo(String billno);
	 ArrayList<Billing> findAllTodaysBills();
	 boolean saveInterestPayment(Payment payment);
	 ArrayList<Payment> fetchInterestPayments(String billno);
}

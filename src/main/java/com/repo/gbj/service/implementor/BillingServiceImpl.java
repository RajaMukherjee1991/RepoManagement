package com.repo.gbj.service.implementor;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repo.gbj.dao.manager.BillingManager;
import com.repo.gbj.model.Billing;
import com.repo.gbj.model.Billing.BillRangeType;
import com.repo.gbj.model.Payment;
import com.repo.gbj.service.BillingService;
import com.repo.gbj.utils.DateUtil;
import com.repo.gbj.utils.TransactionalWithRollbackAndIsolation;

@Service("billingService")
public class BillingServiceImpl implements BillingService{

	BillingManager billingManager;
	
	private ArrayList<Billing> salesBill = null;
	private ArrayList<Billing> mortgagebill = null;
	
	private final static Logger logger = LoggerFactory.getLogger(BillingServiceImpl.class);
	
	public ArrayList<Billing> findAllbills() {
		return (ArrayList<Billing>)billingManager.findAllBills();
	}

	@Autowired
	public void setBillingManager(BillingManager billingManager) {
		this.billingManager = billingManager;
	}

	@Override
	@TransactionalWithRollbackAndIsolation
	public boolean saveBill(Billing billing) {
		return  billingManager.saveBill(billing);
	}

	@Override
	public ArrayList<Billing> findCustomerBill(int cus_pkid) {
		return billingManager.findCustomerBill(cus_pkid);
	}

	@Override
	public Billing findBillByBillNo(String billno) {
		return billingManager.findBillByBillNo(billno);
	}
	
	public ArrayList<Billing> findAllTodaysBills(){
		ArrayList<Billing> allBills = findAllbills();
		ArrayList<Billing> todaysBills = new ArrayList<Billing>();
		for(Billing bill : allBills){
				Date transactionDate = new Date(bill.getTimestamp().getTime());
				logger.info("Transaction Date "+transactionDate);
				if(DateUtil.sameDay(transactionDate, new Date())){
					todaysBills.add(bill);
				}
		}
		
		//allBills.parallelStream().filter(bill -> DateUtility.givenDateToStringinDD_MM_YYYY(bill.getTimestamp()).compareTo(DateUtility.todaysDateinStringDD_MM_YYYY())==0).forEach(System.out::println);
		return todaysBills;
	}

	public ArrayList<Billing> getMortgagebill() {
		return mortgagebill;
	}
	public boolean isActive(Billing bill){
		if(bill.getBillrange_type().equals(BillRangeType.GHOSH_BROTHERS_JEWELLERS_SALES)){
			return false;
		}else{
			return true;
		}
	}
	

	@TransactionalWithRollbackAndIsolation
	public boolean saveInterestPayment(Payment payment){
		return billingManager.saveInterestPayment(payment);
	}
	
	public ArrayList<Payment> fetchInterestPayments(String billno){
		return billingManager.fetchInterestPayments(billno);
	}
	
}

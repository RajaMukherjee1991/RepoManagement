package com.repo.gbj.dao.manager.implementor;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.repo.gbj.dao.Queries;
import com.repo.gbj.dao.manager.BillingManager;
import com.repo.gbj.dao.mapper.BillingMapper;
import com.repo.gbj.dao.mapper.PaymentMapper;
import com.repo.gbj.model.Billing;
import com.repo.gbj.model.Payment;

@Repository
public class BillingManagerImpl implements BillingManager {

	private final static Logger logger = LoggerFactory.getLogger(BillingManagerImpl.class);
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public ArrayList<Billing> findAllBills() {

		logger.info("Fetch all bills");
		ArrayList<Billing> billList = null;
		try {
			billList = (ArrayList<Billing>) namedParameterJdbcTemplate.query(Queries.FETCH_ALL_BILL,
					new BillingMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return billList;
	}
	
	public Billing findBillByBillNo(String billno){
		logger.info("findBillByBillNo() : Bill No : "+ billno);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("TRSN_BILL_NO", billno.trim());
		Billing billing = null;
		try{
			billing = namedParameterJdbcTemplate.queryForObject(Queries.FIND_BILL_BY_BARCODE,params,new BillingMapper());
		}catch(EmptyResultDataAccessException erae){
			logger.error("No record found in Database for "+billno,erae);
		}catch(Exception e){
			logger.error(e.toString());
		}
		return billing;
	}

	public ArrayList<Billing> findCustomerBill(int cus_pkid){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("CUS_ID",cus_pkid);
		ArrayList<Billing> customerBills = null;
		try{
			customerBills = (ArrayList<Billing>) namedParameterJdbcTemplate.query(Queries.FETCH_CUSTOMER_BILLS, paramMap,new BillingMapper());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return customerBills;
	}
	
	
	public boolean saveBill(Billing billing) {
		boolean billSaveStatus = false;

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		//Map<String, Object> paramMap = new HashMap<String, Object>();
		paramSource.addValue("TRSN_TYPE", billing.getBillrange_type().toString());
		paramSource.addValue("TRSN_DATE", billing.getBill_date().toString());
		paramSource.addValue("CUS_ID", billing.getCustomer().getCustomerPKID());
		paramSource.addValue("EMP_ID", billing.getEmployee().getId());
		//paramMap.put("ST_ID", billing.getStock().getStockID());
		paramSource.addValue("TRSN_PRICE", billing.getBill_price());
		paramSource.addValue("TRSN_BILL_NO", billing.getBill_barcode_no());
		paramSource.addValue("TRSN_COMMENTS", billing.getBill_note());
		paramSource.addValue("TRSN_CUS_IMAGE",billing.getCustomerimage(),Types.BLOB);

		int rowCount = namedParameterJdbcTemplate.update(Queries.SAVE_BILL, paramSource);
		if (rowCount > 0) {
			billSaveStatus = true;
		}
		return billSaveStatus;
	}
	
	
	public ArrayList<Payment> fetchInterestPayments(String billno){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("int_paid_bill_no",billno);
		ArrayList<Payment> interestPayments = null;
		try{
			interestPayments = (ArrayList<Payment>) namedParameterJdbcTemplate.query(Queries.FIND_PAYMENTS_BY_BARCODE, paramMap,new PaymentMapper());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return interestPayments;
	}
	
	public boolean saveInterestPayment(Payment payment){
		boolean interestPaymentStatus = false;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("int_paid_bill_no", payment.getBillno());
		paramMap.put("int_paid_date",payment.getPayementdate().toString());
		paramMap.put("int_paid_amnt",payment.getInterestAmount());
		paramMap.put("int_paid_cap_amnt",payment.getCapitalAmount());
		paramMap.put("int_paid_leftpayment", payment.getAmountLeft());
		
		int rowCount = namedParameterJdbcTemplate.update(Queries.SAVE_INTEREST_PAYMENT, paramMap);
		if(rowCount>0){
			interestPaymentStatus = true;
		}
		return interestPaymentStatus;
	}
}
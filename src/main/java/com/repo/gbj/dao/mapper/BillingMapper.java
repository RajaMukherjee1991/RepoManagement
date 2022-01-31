package com.repo.gbj.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.repo.gbj.model.Billing;
import com.repo.gbj.model.Billing.BillRangeType;

public class BillingMapper implements RowMapper<Billing>{

	public Billing mapRow(ResultSet rs, int arg1) throws SQLException {
		Billing billing = new Billing();
		
		billing.setCustomer(new CustomerMapper().mapRow(rs, arg1));
		billing.setEmployee(new EmployeeMapper().mapRow(rs, arg1));
		
		billing.setBill_barcode_no(rs.getString("TRSN_BILL_NO"));
		billing.setBill_date(rs.getDate("TRSN_DATE").toString());
		billing.setBill_price(rs.getBigDecimal("TRSN_PRICE"));
		billing.setBillrange_type(BillRangeType.valueOf(rs.getString("TRSN_TYPE")));
		billing.setBill_note(rs.getString("TRSN_COMMENTS"));
		billing.setTimestamp(rs.getTimestamp("TRSN_TIMESTAMP"));
		billing.setCustomerimage(rs.getString("TRSN_CUS_IMAGE"));
		return billing;
	}

}

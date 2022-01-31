package com.repo.gbj.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.repo.gbj.model.Payment;

public class PaymentMapper implements RowMapper<Payment>{

	@Override
	public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Payment payment = new Payment(rs.getString("int_paid_bill_no"),
									  rs.getTimestamp("timestamp"),
									  rs.getDate("int_paid_date"),
									  rs.getBigDecimal("int_paid_amnt"),
									  rs.getBigDecimal("int_paid_cap_amnt"),
									  rs.getBigDecimal("int_paid_leftpayment")
									);
		return payment;
	}

}

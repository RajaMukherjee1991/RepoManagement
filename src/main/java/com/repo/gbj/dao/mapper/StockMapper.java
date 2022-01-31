package com.repo.gbj.dao.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.repo.gbj.model.Stock;
import com.repo.gbj.model.Stock.StockType;

public class StockMapper implements RowMapper<Stock>{

	public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
		Stock stock = new Stock();
		stock.setStockID(rs.getInt("ST_ID"));
		stock.setTimestamp(rs.getTimestamp("ST_TIMESTAMP"));
		stock.setStockPrice(BigDecimal.valueOf(rs.getDouble("ST_PRICE")));
		stock.setStockWeight(BigDecimal.valueOf(rs.getDouble("ST_WEIGHT")));
		stock.setStockEntryDate(String.valueOf(rs.getDate("ST_ENTRY_DATE")));
		stock.setStockExitDate(String.valueOf(rs.getDate("ST_EXIT_DATE")));
		stock.setStock_desc(rs.getString("ST_TYPE_DESC"));
		stock.setStockType(rs.getString("ST_TYPE").equals("S")?StockType.SALES:StockType.MORTGAGE);
		stock.setBase64stockimage(rs.getString("ST_IMAGE"));
		stock.setBarcode(rs.getString("ST_BARCODE"));
		stock.setQuantity(rs.getBigDecimal("ST_QTY"));
		stock.setStockDeductions(rs.getBigDecimal("ST_DEDUCTION"));
		stock.setStockGrossWeight(rs.getBigDecimal("ST_GROSS_WEIGHT"));
		stock.setBill_no(rs.getString("TRSN_BILL_NO"));
		stock.setSellingPrice(rs.getBigDecimal("ST_SELL_PRICE"));
		
		return stock;
	}
}

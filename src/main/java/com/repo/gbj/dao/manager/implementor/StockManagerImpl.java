package com.repo.gbj.dao.manager.implementor;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.repo.gbj.dao.Queries;
import com.repo.gbj.dao.manager.StockManager;
import com.repo.gbj.dao.mapper.StockMapper;
import com.repo.gbj.model.Stock;
import com.repo.gbj.model.Stock.StockType;

@Repository
public class StockManagerImpl implements StockManager{

	private final static Logger logger = LoggerFactory.getLogger(StockManagerImpl.class);
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	
	public ArrayList<Stock> getAllStock() {
		logger.info("Fetch all Stock");
		ArrayList<Stock> stockList = null;
		try{
			stockList = (ArrayList<Stock>) namedParameterJdbcTemplate.query(Queries.ALL_STOCK,new StockMapper());
		}catch(Exception e){
			e.printStackTrace();
		}
		return stockList;
	}
	
	public ArrayList<Stock> fetchStockByBillBarcode(String billBarcode){
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		logger.info("Barcode "+ billBarcode);
		paramSource.addValue("TRSN_BILL_NO",billBarcode);
		return (ArrayList<Stock>)namedParameterJdbcTemplate.query(Queries.FETCH_STOCK_BY_BILL_BARCODE, paramSource,new StockMapper());
	}
	

	public ArrayList<Stock> getAllStockByType(StockType stockType) {
		Map<String, Object> params = new HashMap<String, Object>();
		logger.info("Stock type to search " +stockType.getName());
		params.put("ST_TYPE", stockType.getName());
		ArrayList<Stock> stockListByType = null;
		try{
			stockListByType = (ArrayList<Stock>) namedParameterJdbcTemplate.query(Queries.ALL_ACTIVE_SALESSTOCK,params,new StockMapper());
		}catch(Exception e){
			e.printStackTrace();
		}
		return stockListByType;
	}
	
	public ArrayList<Stock> getAllStockByTypeIncludingExitDate(StockType stockType) {
		Map<String, Object> params = new HashMap<String, Object>();
		logger.info("Stock type to search " +stockType.getName());
		params.put("ST_TYPE", stockType.getName());
		ArrayList<Stock> stockListByType = null;
		try{
			stockListByType = (ArrayList<Stock>) namedParameterJdbcTemplate.query(Queries.ALL_STOCK_BY_TYPE,params,new StockMapper());
		}catch(Exception e){
			e.printStackTrace();
		}
		return stockListByType;
	}

	public boolean saveStock(Stock stock) {
		boolean saveStatus = false;
		logger.info(stock.getStockType().name());
	//	logger.info( "Entry Date " + DateUtility.StringToSQLDateConverter(stock.getStockEntryDate()) );
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("ST_PRICE",stock.getStockPrice());
		paramSource.addValue("ST_WEIGHT",stock.getStockWeight());
		paramSource.addValue("ST_ENTRY_DATE",stock.getStockEntryDate());
		/*paramSource.addValue("ST_EXIT_DATE",DateUtility.StringToSQLDateConverter(stock.getStockExitDate()));*/
		paramSource.addValue("ST_TYPE_DESC",stock.getStock_desc());
		paramSource.addValue("ST_BARCODE",stock.getBarcode());
		paramSource.addValue("ST_TYPE",stock.getStockType().getName());
		paramSource.addValue("ST_IMAGE", stock.getBase64stockimage(),Types.BLOB);
		paramSource.addValue("ST_QTY", stock.getQuantity());
		paramSource.addValue("ST_GROSS_WEIGHT", stock.getStockGrossWeight());
		paramSource.addValue("ST_DEDUCTION", stock.getStockDeductions());
		paramSource.addValue("TRSN_BILL_NO", stock.getBill_no());
		paramSource.addValue("ST_SELL_PRICE",stock.getSellingPrice() );
			
		int noOfrows = namedParameterJdbcTemplate.update(Queries.SAVE_STOCK, paramSource);
		if(noOfrows>0){
			saveStatus = true; 
		}
		return saveStatus;
	}
	
	public Stock fetchStockByBarcode(String barcode) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		logger.debug("Barcode "+ barcode);
		paramSource.addValue("ST_BARCODE",barcode);
		Stock stock  = namedParameterJdbcTemplate.queryForObject(Queries.FETCH_STOCK_BY_BARCODE, paramSource,new StockMapper());
		return stock;
	}

	@Override
	public boolean updateStock(Stock stock) {
		
		boolean updateStatus = false;
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("ST_EXIT_DATE",stock.getStockExitDate());
		paramSource.addValue("ST_BARCODE",stock.getBarcode());
		paramSource.addValue("ST_QTY", stock.getQuantity());
		paramSource.addValue("TRSN_BILL_NO", stock.getBill_no());
		paramSource.addValue("ORDER_ID", stock.getOrderId());
		paramSource.addValue("ST_SELL_PRICE", stock.getSellingPrice());
		
		int noOfrows = namedParameterJdbcTemplate.update(Queries.UPDATE_STOCK, paramSource);
		if(noOfrows>0){
			updateStatus = true; 
		}
		return updateStatus;
	}
	
	public String getPhotoById(String stockCode){
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("ST_BARCODE", stockCode);
		String image = namedParameterJdbcTemplate.queryForObject(Queries.FETCH_STOCK_IMAGE_BY_ID, paramSource, String.class);
		return image;
	}
}

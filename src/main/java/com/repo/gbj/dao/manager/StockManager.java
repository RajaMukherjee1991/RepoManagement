package com.repo.gbj.dao.manager;

import java.util.ArrayList;
import java.util.List;

import com.repo.gbj.model.Stock;
import com.repo.gbj.model.Stock.StockType;

public interface StockManager {

	List<Stock> getAllStock();
	ArrayList<Stock> getAllStockByType(StockType stockType);
	boolean saveStock(Stock stock);
	Stock fetchStockByBarcode(String barcode);
	boolean updateStock(Stock stock);
	ArrayList<Stock> getAllStockByTypeIncludingExitDate(StockType stockType);
	ArrayList<Stock> fetchStockByBillBarcode(String billBarcode);
	String getPhotoById(String stockCode);
}

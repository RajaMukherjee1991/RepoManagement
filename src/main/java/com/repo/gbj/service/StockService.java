package com.repo.gbj.service;

import java.util.ArrayList;
import java.util.List;

import com.repo.gbj.model.Stock;
import com.repo.gbj.model.Stock.StockType;

public interface StockService {

	 List<Stock> findAllStocks();
	 List<Stock> findAllStocks(StockType stockType);
	 boolean saveStock(Stock stock);
	 Stock fetchStockByBarcode(String currentBarcode);
	 void updateStockStatus(Stock stock) throws Exception;
	 List<Stock> getAllStockByTypeIncludingExitDate(StockType stockType);
	 ArrayList<Stock> fetchStockByBillBarcode(String billBarcode);
	 String getPhotoById(String stockCode);
}

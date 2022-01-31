package com.repo.gbj.service.implementor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repo.gbj.dao.manager.StockManager;
import com.repo.gbj.model.Stock;
import com.repo.gbj.model.Stock.StockType;
import com.repo.gbj.service.StockService;
import com.repo.gbj.utils.DateUtil;
import com.repo.gbj.utils.DateUtil.DateFormat;

@Service("stockService")
public class StockServiceImpl implements StockService{

	StockManager stockManager;
	private final static Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);
	
	public ArrayList<Stock> findAllStocks() {
		ArrayList<Stock> stockListings = (ArrayList<Stock>) stockManager.getAllStock();
		/*Collections.sort(stockListings,new StockEntryComparator());*/
		return stockListings;
	}

	@Autowired
	public void setStockManager(StockManager stockManager) {
		this.stockManager = stockManager;
	}
	
	public ArrayList<Stock> findAllStocks(StockType stockType){
		ArrayList<Stock> stockListbyType = (ArrayList<Stock>) stockManager.getAllStockByType(stockType);
		/*Collections.sort(stockListbyType,new StockEntryComparator());*/
		return stockListbyType;
	}
	
	

	public boolean saveStock(Stock stock) {
		return stockManager.saveStock(stock);
	}
	
	public boolean updateStock(Stock stock){
		return stockManager.updateStock(stock);
	}
	
	public Stock fetchStockByBarcode(String barcode){
		return stockManager.fetchStockByBarcode(barcode);
	}
	
	public ArrayList<Stock> fetchStockByBillBarcode(String billBarcode){
		return stockManager.fetchStockByBillBarcode(billBarcode);
	}
	
	
	/**
	 * This method divides a particular stock into 2 separate stocks.
	 * For example : if there are 10 items in the stock and the customer buys a 2 item 
	 * out of the 10 stocked items. 2 item will billed and (10-2= 8) 8 items will remain in the stock
	 * with a new barcode.
	 */
	@Override
	public void updateStockStatus(Stock currentStock) throws Exception {
		logger.info("Search Stock for barcode "+ currentStock.getBarcode());
			Stock originalStock = stockManager.fetchStockByBarcode(currentStock.getBarcode());
			if(originalStock == null){
				throw new Exception("Stock is invalid");
			}else{
				BigDecimal finalQuantity =originalStock.getQuantity().subtract(currentStock.getQuantity());
				logger.info("Final Quantity " + finalQuantity);
					originalStock.setQuantity(finalQuantity);
					originalStock.setStockExitDate(DateUtil.date_now(DateFormat.YYYY_MM_DD.toString()));
					originalStock.setBill_no(currentStock.getBill_no());
					originalStock.setSellingPrice(currentStock.getSellingPrice());
					updateStock(originalStock);
				
			}
		}

	@Override
	public List<Stock> getAllStockByTypeIncludingExitDate(StockType stockType) {
		ArrayList<Stock> stockListbyType = (ArrayList<Stock>) stockManager.getAllStockByTypeIncludingExitDate(stockType);
		return stockListbyType;
	}
	
	/*public List<Stock> fetchBilledSalesStock(){
		List<Stock> allsalesstock =  findAllStocks(StockType.SALES);
		List<Stock> billedStock = new ArrayList<Stock>();
		billedStock = allsalesstock.stream()
					.filter(stock -> stock.getStockExitDate() !=null && !stock.getStockExitDate().isEmpty())
					.collect(Collectors.toList());
		return billedStock;
	}
	
	public List<Stock> fetchUnBilledSalesStock(){
		List<Stock> allsalesstock =  findAllStocks(StockType.SALES);
		List<Stock> unbilledStock = new ArrayList<Stock>();
		unbilledStock = allsalesstock.stream()
					.filter(stock -> stock.getStockExitDate() ==null || stock.getStockExitDate().isEmpty())
					.collect(Collectors.toList());
		return unbilledStock;
	}
	
	public List<Stock> fetchInActiveMortgageStock(){
		List<Stock> allsalesstock =  findAllStocks(StockType.MORTGAGE);
		List<Stock> inactiveStock = new ArrayList<Stock>();
		inactiveStock = allsalesstock.stream()
					.filter(stock -> stock.getStockExitDate() !=null && !stock.getStockExitDate().isEmpty())
					.collect(Collectors.toList());
		return inactiveStock;
	}
	
	public List<Stock> fetchActiveMortgageStock(){
		List<Stock> allsalesstock =  findAllStocks(StockType.MORTGAGE);
		List<Stock> activeStock = new ArrayList<Stock>();
		activeStock = allsalesstock.stream()
					.filter(stock -> stock.getStockExitDate() ==null || stock.getStockExitDate().isEmpty())
					.collect(Collectors.toList());
		return activeStock;
	}*/
	
	public String getPhotoById(String stockCode){
		return stockManager.getPhotoById(stockCode);
	}
	
}

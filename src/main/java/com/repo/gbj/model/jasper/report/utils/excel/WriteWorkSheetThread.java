package com.repo.gbj.model.jasper.report.utils.excel;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.repo.gbj.model.Stock;
import com.repo.gbj.model.Stock.StockType;
import com.repo.gbj.utils.ConfigurationHolder.ExcelConstants;

public class WriteWorkSheetThread implements Runnable {

	private XSSFSheet sheet;
	private List<Stock> stockRecords;
	private StockType type;
	private final static Logger logger = LoggerFactory.getLogger(WriteWorkSheetThread.class);
	
	public WriteWorkSheetThread(List<Stock> stockRecords,StockType type){
		this.stockRecords = stockRecords;
		this.type=type;
	}

	@Override
	public void run() {
		logger.info("Inside run");
		try(XSSFWorkbook workbook = new XSSFWorkbook()){
			sheet = workbook.createSheet(type.toString());
			ExcelSheetTemplate exceltemplate = new ExcelSheetTemplate(sheet);
			
			List<Stock> active = new ArrayList<Stock>();
			List<Stock> inactive = new ArrayList<Stock>();
			
			inactive = stockRecords.stream()
					.filter(stock -> stock.getStockExitDate() !=null)
					.collect(Collectors.toList());
			
			active = stockRecords.stream()
					.filter(stock -> stock.getStockExitDate() == null )
					.collect(Collectors.toList());
			
			if(sheet.getSheetName().equals(type.toString())){
				logger.info("Sheet name Matched");
				
				BigDecimal totalPrice = stockRecords.stream().
						map(item -> item.getStockPrice()).
						reduce((a,b) ->a.add(b)).orElse(BigDecimal.ZERO);
				
				logger.info("total price "+totalPrice.toString());
				
				BigDecimal sellingPrice = stockRecords.stream().
						filter(stock ->stock.getSellingPrice()!=null).
						map(item -> item.getSellingPrice()).
						reduce((a,b) ->a.add(b)).orElse(BigDecimal.ZERO);
				
				exceltemplate.createReportData(totalPrice,sellingPrice,type);
				exceltemplate.addHeaderRow();
				inactive.forEach(exceltemplate::addRow);
			}
			exceltemplate.writeToFile(workbook,type);
		}catch (IOException e) {
			logger.error("",e);
		}
	}
}


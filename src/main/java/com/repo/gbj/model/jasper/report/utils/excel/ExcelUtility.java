package com.repo.gbj.model.jasper.report.utils.excel;

import java.util.Arrays;
import java.util.List;

import com.repo.gbj.model.Stock.StockType;
import com.repo.gbj.utils.ConfigurationHolder.ExcelConstants;
import com.repo.gbj.utils.DateUtil;

public class ExcelUtility {

	public static final String DELIMETER = ",";

	public static List<String> getHeaders(){
		String sheetheader = ExcelConstants.SHEET_HEADERS.toString();
		return Arrays.asList(sheetheader.split(DELIMETER));
	}
		
	class ExcelReport{
		public String ghoshBrotherHeader;
		public String blank;
		public String generationDateHeader;
		public String generationDate;
		public String totalSumHeader;
		public String totalSum;
		public String totalSellingPriceHeader;
		public String totalSellingPrice;
		
		public ExcelReport(StockType type){
			this.generationDate = DateUtil.date_now(DateUtil.DateFormat.DD_MM_YYYY.toString());
			this.blank="";
			this.generationDateHeader = type.equals(StockType.SALES) ? ExcelConstants.EXCEL_SALES_REPORTGENERATIONDATE.toString() : 
																	   ExcelConstants.EXCEL_MORTGAGE_REPORTGENERATIONDATE.toString();
			this.ghoshBrotherHeader = ExcelConstants.EXCELREPORTHEADER.toString();
			this.totalSumHeader = ExcelConstants.EXCELSALESREPORTSTATS_TOTALPRICE.toString();
			this.totalSellingPriceHeader = ExcelConstants.EXCELSALESREPORTSTATS_TOTALSELLINGPRICE.toString();
		}
		
		public List<String> getDataHeader() {
			return Arrays.asList(generationDateHeader,generationDate,
								totalSumHeader,totalSum, totalSellingPriceHeader,totalSellingPrice); 
		}

		public String getTotalSum() {
			return totalSum;
		}
		public void setTotalSum(String totalSum) {
			this.totalSum = totalSum;
		}
		public String getTotalSellingPrice() {
			return totalSellingPrice;
		}
		public void setTotalSellingPrice(String totalSellingPrice) {
			this.totalSellingPrice = totalSellingPrice;
		}
		
		
	}
}

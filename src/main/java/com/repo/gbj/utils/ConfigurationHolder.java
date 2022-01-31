package com.repo.gbj.utils;

import java.io.UnsupportedEncodingException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationHolder {

	private static ConfigurationHolder instance;

	private ResourceBundle resourceBundle;

	private final static Logger logger = LoggerFactory.getLogger(ConfigurationHolder.class);

	private ConfigurationHolder() {
		try {
			resourceBundle = ResourceBundle.getBundle("config"); 
		} catch (MissingResourceException mre) {
			logger.error("Missing Resource bundle ", mre);
			resourceBundle = null;
		}
	}

	public static ConfigurationHolder getInstance() {
		if (instance == null) {
			instance = new ConfigurationHolder();
		}
		return instance;
	}

	/**
	 * Bundle Holder class
	 * 
	 * @author Raja
	 *
	 */
	public static final class Holder {

		private final String holderKey;
		
		public Holder(String holderKey) {
			this.holderKey = holderKey;
		}

		@Override
		public String toString() {
			String key = null;
			try {
				if (ConfigurationHolder.getInstance().resourceBundle != null) {
					key = ConfigurationHolder.getInstance().resourceBundle.getString(holderKey);
				}
			} catch (MissingResourceException mre) {
				/* Ignored */
			}
			try{
				return new String(key.getBytes("ISO-8859-1"),"UTF-8");
			}catch(UnsupportedEncodingException uee ){
				logger.error("Error encoding value ",uee);
				return null;
			}

		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((holderKey == null) ? 0 : holderKey.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Holder other = (Holder) obj;
			if (holderKey == null) {
				if (other.holderKey != null)
					return false;
			} else if (!holderKey.equals(other.holderKey))
				return false;
			return true;
		}
			
	}
	
	
	public interface ExcelConstants {
		Holder SALES = new Holder("static.text.sales");
		Holder MORTGAGE = new Holder("static.text.mortgage");
		Holder SHEET_HEADERS = new Holder("excel.sheet.header");
		Holder EXCELFILEPATH = new Holder("excel.file.path");
		Holder EXCELFILENAME = new Holder("excel.file.name");
		Holder EXCELFILETYPE = new Holder("excel.file.type");
		
		Holder EXCELREPORTHEADER = new Holder("excel.report.header");
		Holder EXCEL_SALES_REPORTGENERATIONDATE = new Holder("excel.report.sales.generatedondate.text");
		Holder EXCELSALESREPORTSTATS_TOTALPRICE = new Holder("excel.report.stats.totalprice.text");
		Holder EXCELSALESREPORTSTATS_TOTALSELLINGPRICE = new Holder("excel.report.stats.totalsellingprice.text");
		Holder EXCEL_SALES_BILLEDSTOCK = new Holder("excel.report.billed.salesstock");
		Holder EXCEL_SALES_ACTIVESTOCK = new Holder("excel.report.active.salesstock");
		
		
		//Mortgage
		Holder EXCEL_MORTGAGE_REPORTGENERATIONDATE = new Holder("excel.report.mortgage.generatedondate.text");
		Holder EXCEL_MORTGAGE_RELEASED = new Holder("excel.report.released.mortgagestock");
		Holder EXCEL_MORTGAGE_ACTIVE = new Holder("excel.report.active.mortgagestock");
		
		
		
		
		
	}
	
	

}

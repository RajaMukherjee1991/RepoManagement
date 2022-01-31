package com.repo.gbj.model.comparator;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;

import com.repo.gbj.model.Stock;
import com.repo.gbj.utils.DateUtil;

public class StockEntryComparator implements Comparator<Stock>{

	public int compare(Stock o1, Stock o2) {
		
		Date stockentrydate_o1 = null;
		Date stockentrydate_o2 = null;
		try {
			stockentrydate_o1 = DateUtil.parse(o1.getStockEntryDate());
			stockentrydate_o2 = DateUtil.parse(o2.getStockEntryDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(stockentrydate_o1.after(stockentrydate_o2)){
			return 1;
		}else{
			return 0;
		}
	}
	
	
}

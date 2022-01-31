package com.repo.gbj.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.repo.gbj.model.datatype.BarcodeDatatype;
import com.repo.gbj.model.datatype.BarcodeDatatype.BarcodeType;
import com.repo.gbj.service.implementor.StockServiceImpl;
import com.repo.gbj.utils.DateUtil.DateFormat;

/**
 * Mortgage Bill Barcode 
 * 	1. Bill Barcode   --> MTB-<date-time>-<EMPID>-<CUSID>
 *  2. Mortgage Stock Barcode -->MTS-<date-time>
 * Sales Bill Barcode
 *  1. Bill Barcode -->SAB-<date-time>-<EMPID>-<CUSID>
 *  2. Sales Stock Barcode -->SAS-<date-time>
 * @author Raja
 *
 */
public class BarcodeGenerator {

	private final static Logger logger = LoggerFactory.getLogger(BarcodeGenerator.class);
	
	private String barcodeGenerated;
	public static final String DELIMETER="-";
	
	public BarcodeGenerator(BarcodeDatatype barcodeDatatype){
		if(barcodeDatatype.getBarcodetype().equals(BarcodeDatatype.BarcodeType.MTB)||barcodeDatatype.getBarcodetype().equals(BarcodeDatatype.BarcodeType.SAB)){
			System.out.println("----------------- "+ DateUtil.date_now(DateFormat.YYYYMMDDHHMMSS.toString()) +" ------------------------");
			this.barcodeGenerated = barcodeDatatype.getBarcodetype().name()
									.concat(DELIMETER)
									.concat(DateUtil.date_now(DateFormat.YYYYMMDDHHMMSS.toString()))
									.concat(DELIMETER)
									.concat(barcodeDatatype.getEmployeeId())
									.concat(DELIMETER)
									.concat(barcodeDatatype.getCustomerId());
		}else{
			this.barcodeGenerated = barcodeDatatype.getBarcodetype().name().concat(DELIMETER).concat(DateUtil.date_now(DateFormat.YYYYMMDDHHMMSS.toString()));
		}
	}
	
	public BarcodeGenerator(BarcodeType type){
		this.barcodeGenerated = type.name().concat(DELIMETER).concat(DateUtil.date_now(DateFormat.YYYYMMDDHHMMSS.toString()));
	}
	
	public String getBarcodeGenerated() {
		return barcodeGenerated;
	}
	
}

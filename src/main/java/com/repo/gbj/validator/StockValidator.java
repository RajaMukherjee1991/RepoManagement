package com.repo.gbj.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.repo.gbj.model.viewobject.StockVO;

@Component
public class StockValidator implements Validator{

	
	public boolean supports(Class<?> clazz) {
		return StockVO.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		StockVO stockVO = (StockVO) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stock.stockWeight", "mortgagebill.stockWeight.missing");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stock.stockPrice", "mortgagebill.stockPrice.missing");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stock.stockEntryDate", "mortgagebill.stockEntryDate.missing");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stock.stock_desc", "mortgagebill.stock_desc.missing");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stock.stockGrossWeight", "mortgagebill.stockGrossWeight.missing");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stock.stockDeductions", "mortgagebill.stockDeductions.missing");
	}
}

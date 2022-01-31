package com.repo.gbj.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.repo.gbj.model.viewobject.MortgageBillVO;

@Component
public class MortgageBillValidator implements Validator{

	
	public static final String DEFAULT_VALUE = "0.00";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return MortgageBillVO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		MortgageBillVO mortgagebillVO = (MortgageBillVO) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stock.stockWeight", "mortgagebill.stockWeight.missing");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stock.stockPrice", "mortgagebill.stockPrice.missing");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stock.stockEntryDate", "mortgagebill.stockEntryDate.missing");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stock.stock_desc", "mortgagebill.stock_desc.missing");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stock.stockGrossWeight", "mortgagebill.stockGrossWeight.missing");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stock.stockDeductions", "mortgagebill.stockDeductions.missing");
		
		if(mortgagebillVO.getStock().getStockWeight() != null){
			if(mortgagebillVO.getStock().getStockWeight().toString().equals(DEFAULT_VALUE)){
				errors.rejectValue("stock.stockWeight", "mortgagebill.stockWeight.default.value.error");
			}
		}
		
		if(mortgagebillVO.getStock().getStockPrice()!= null){
			if(mortgagebillVO.getStock().getStockPrice().toString().equals(DEFAULT_VALUE)){
				errors.rejectValue("stock.stockPrice", "mortgagebill.stockPrice.default.value.error");
			}
		}
		
		if(mortgagebillVO.getStock().getStockPrice() != null){
			if(mortgagebillVO.getStock().getStockPrice().toString().equals(DEFAULT_VALUE)){
				errors.rejectValue("stock.stockGrossWeight", "mortgagebill.stockGrossWeight.default.value.error");
			}
		}
		
	}

}

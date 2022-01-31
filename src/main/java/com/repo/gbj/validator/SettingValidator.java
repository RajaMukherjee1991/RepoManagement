package com.repo.gbj.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.repo.gbj.model.UserSettings;

@Component
public class SettingValidator implements Validator{

	@Autowired
	@Qualifier("mobileNumberValidator")
	MobileNumberValidator mobileNumberValidator;
	
	@Autowired
	@Qualifier("addressValidator")
	AddressValidator addressValidator;
	
	
	public boolean supports(Class<?> clazz) {
		return UserSettings.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		
		UserSettings userSetting = (UserSettings) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licenseNo", "setting.license.no.missing");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shopAddress", "setting.address.missing");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shopPhone1", "setting.phone1.missing");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shopPhone2", "setting.phone2.missing");
	
		
		if(!addressValidator.isValid(userSetting.getShopAddress())){
			errors.rejectValue("shopAddress","setting.address.invalid");
		}
		if(!StringUtils.isEmpty(userSetting.getShopPhone1())){
			if(!mobileNumberValidator.valid(userSetting.getShopPhone1())){
				errors.rejectValue("shopPhone1", "setting.phone1.invalid");
			}
		}
		if(!StringUtils.isEmpty(userSetting.getShopPhone2())){
			if(!mobileNumberValidator.valid(userSetting.getShopPhone2())){
				errors.rejectValue("shopPhone2", "setting.phone2.invalid");
			}
		}
	}


}

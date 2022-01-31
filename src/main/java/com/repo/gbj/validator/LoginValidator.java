package com.repo.gbj.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.repo.gbj.model.EmployeeCredentials;
import com.repo.gbj.service.LoginService;

@Component
public class LoginValidator implements Validator{

	@Autowired
	@Qualifier("mobileNumberValidator")
	MobileNumberValidator mobileNumberValidator;
	
	@Autowired
	LoginService loginService;

	public boolean supports(Class<?> clazz) {
		return EmployeeCredentials.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		
		EmployeeCredentials credentials = (EmployeeCredentials) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "login.username.missing");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobilenumber", "login.mobilenumber.missing");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "login.password.missing");
	
		if(!mobileNumberValidator.valid(credentials.getMobilenumber())){
			errors.rejectValue("mobilenumber","login.mobilenumber.invalid");
		}
	}
}

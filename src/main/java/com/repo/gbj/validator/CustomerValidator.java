package com.repo.gbj.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.repo.gbj.model.Customer;

@Component
public class CustomerValidator implements Validator{

	@Autowired
	@Qualifier("aadharValidator")
	AadhaarValidator aadharValidator;
	
	@Autowired
	@Qualifier("nameValidator")
	NameValidator nameValidator;
	
	@Autowired
	@Qualifier("addressValidator")
	AddressValidator addressValidator;
	
	@Autowired
	@Qualifier("mobileNumberValidator")
	MobileNumberValidator mobileNumberValidator;
	
	public boolean supports(Class<?> clazz) {
		return Customer.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		
		Customer customer = (Customer) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fname", "customer.fname.missing");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lname", "customer.lname.missing");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address1", "customer.address1.missing");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pincode", "customer.pincode.missing");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "landlord", "customer.landlord.missing");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "primarymobilenumber", "customer.primarymobilenumber.missing");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pancard", "customer.pancard.missing");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "aadharNo", "customer.aadharNo.missing");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fingerprintISOtemplate", "customer.fingerprintISOtemplate.missing");
		
		if(!mobileNumberValidator.valid(customer.getPrimarymobilenumber())){
			errors.rejectValue("primarymobilenumber","customer.mobilenumber.invalid");
		}
		if(!aadharValidator.isValid(customer.getAadharNo())){
			errors.rejectValue("aadharNo", "customer.aadharno.invalid");
		}
		if(!nameValidator.valid(customer.getFname())){
			errors.rejectValue("fname", "customer.fname.invalid");
		}
		if(!nameValidator.valid(customer.getLname())){
			errors.rejectValue("lname", "customer.lname.invalid");
		}
		if(!addressValidator.isValid(customer.getAddress1())){
			errors.rejectValue("address1", "customer.address.invalid");
		}
		
		if(!StringUtils.isEmpty(customer.getMname())){
			if(!nameValidator.valid(customer.getMname())){
				errors.rejectValue("mname", "customer.mname.invalid");
			}
		}
		if(!StringUtils.isEmpty(customer.getAddress2())){
			if(!addressValidator.isValid(customer.getAddress2())){
				errors.rejectValue("address2", "customer.address2.invalid");
			}
		}
		if(!StringUtils.isEmpty(customer.getAlternatemobilenumber())){
			if(!mobileNumberValidator.valid(customer.getAlternatemobilenumber())){
				errors.rejectValue("alternatemobilenumber", "customer.alternatemobilenumber.invalid");
			}
		}
	}

}

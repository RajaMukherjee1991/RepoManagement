package com.repo.gbj.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component("aadharValidator")
public class AadhaarValidator {

	private Pattern pattern;
	private Matcher matcher;
	
	private static final String AADHAR_NUMBER_VALIDATOR="^[2-9]{1}[0-9]{3}\\s[0-9]{4}\\s[0-9]{4}";
	
	public AadhaarValidator(){
		pattern = Pattern.compile(AADHAR_NUMBER_VALIDATOR);
	}
	
	public boolean isValid(final String aadharNumber){
		matcher = pattern.matcher(aadharNumber);
		return matcher.matches();
	}
	
}

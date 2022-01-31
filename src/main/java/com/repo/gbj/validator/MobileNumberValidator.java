package com.repo.gbj.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component("mobileNumberValidator")
public class MobileNumberValidator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String MOBILE_NUMBER_PATTERN = "(0/91)?[7-9][0-9]{9}";

	public MobileNumberValidator() {
		pattern = Pattern.compile(MOBILE_NUMBER_PATTERN);
	}

	public boolean valid(final String mobilenumber) {
		matcher = pattern.matcher(mobilenumber);
		return matcher.matches();
	}
	
	/*public static void main(String[] args) {
		MobileNumberValidator mb = new MobileNumberValidator();
		System.out.println(mb.valid("8017910828"));
	}*/
}

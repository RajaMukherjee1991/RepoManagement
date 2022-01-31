package com.repo.gbj.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component("addressValidator")
public class AddressValidator{

	private Pattern pattern;
	private Matcher matcher;
	
	private static final String ADDRESS_PATTERN="^[#.0-9a-zA-Z\\s,-]+$";
	
	
	public AddressValidator() {
		pattern = Pattern.compile(ADDRESS_PATTERN);
	}

	public boolean isValid(final String address){
		matcher = pattern.matcher(address);
		return matcher.matches();
	}
	
}

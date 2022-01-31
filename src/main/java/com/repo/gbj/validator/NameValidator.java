package com.repo.gbj.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component("nameValidator")
public class NameValidator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String NAME_PATTERN = "([A-ZÀ-ÿ][-,a-z.']+)+";

	public NameValidator() {
		pattern = Pattern.compile(NAME_PATTERN);
	}

	public boolean valid(final String name) {
		matcher = pattern.matcher(name);
		return matcher.matches();
	}
	
	/*public static void main(String[] args) {
		NameValidator mb = new NameValidator();
		System.out.println(mb.valid("Mukhe:rjee"));
	}*/
}

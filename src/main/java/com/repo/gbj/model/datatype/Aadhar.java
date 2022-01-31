package com.repo.gbj.model.datatype;

import java.io.Serializable;

public class Aadhar implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String aadharString;
	
	public Aadhar(String aadharno) {
		this.aadharString = aadharno;
	}

	public static Aadhar parse(String aadharString){
		StringBuilder str = new StringBuilder(aadharString);
		int idx = str.length() - 4;
		while (idx > 0){
		   str.insert(idx, " ");
		   idx = idx - 4;
		}
		return new Aadhar(str.toString());
	}

	public String getAadharString() {
		return aadharString;
	}

	public void setAadharString(String aadharString) {
		this.aadharString = aadharString;
	}
	
}
